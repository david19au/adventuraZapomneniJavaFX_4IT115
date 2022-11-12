package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.main;

import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Třída HomeController ovládá grafické prvky hry.
 *
 * @author Trong Dat Luu
 * @version ZS 2022/23
 */
public class HomeController implements Pozorovatel {

    public Button novaHra;
    @FXML
    private ImageView hrac;
    @FXML
    private TextArea vystup;

    @FXML
    private ListView<Prostor> panelVychodu;

    @FXML
    private ListView<Vec> panelInventar;

    @FXML
    private TextField vstup;

    @FXML
    private Button proved;

    private Map<String, Point2D> souradniceProstoru = new HashMap<>();

    private IHra hra = new Hra();

    /**
     * Metoda initialize inicializuje grafické prostředí.
     * Nastavuje aktuální herní plán a inventář spolu s pozorovateli, nastuvuje, aby se výstup dal do správného místa,
     * provede naplnění panelů inventáře a východů a zajistí, aby prvky na vstup, proveď a panely byly zpřístupněné.
     * Nastaví dále kde jsou určité lokality v mapě a posouvá hráče na počáteční místo.
     * Vytvoří panely východů a inventáře pomocí cellfactory, spolu s doprovodnými obrázky.
     *
     */
    @FXML
    private void initialize() {
        hra.getHerniPlan().registruj(this);
        hra.getInventar().registruj(this);
        vystup.appendText(hra.vratUvitani() + "\n\n");
        Platform.runLater(() -> vstup.requestFocus());
        naplneniPaneluVychodu();
        naplneniPaneluInventare();
        vstup.setDisable(false);
        proved.setDisable(false);
        panelVychodu.setDisable(false);
        panelInventar.setDisable(false);

        souradniceProstoru.put("kuchyn", new Point2D(595, 14));
        souradniceProstoru.put("domov", new Point2D(460, 76));
        souradniceProstoru.put("ulice", new Point2D(341, 128));
        souradniceProstoru.put("kamaraduv_byt", new Point2D(329, 29));
        souradniceProstoru.put("koleje", new Point2D(72, 35));
        souradniceProstoru.put("skola", new Point2D(266, 215));
        souradniceProstoru.put("hlavni_nadrazi", new Point2D(193, 111));
        souradniceProstoru.put("karluv_most", new Point2D(594, 161));
        souradniceProstoru.put("drogovy_dealer", new Point2D(499, 294));
        souradniceProstoru.put("psycholog", new Point2D(141, 217));
        souradniceProstoru.put("strecha_skoly", new Point2D(312, 300));
        souradniceProstoru.put("vaclavske_namesti", new Point2D(454, 192));
        posunHrace();

        panelVychodu.setCellFactory(prostorListView -> new ListCell<>() {
            @Override
            protected void updateItem(Prostor prostor, boolean empty) {
                super.updateItem(prostor, empty);
                if (!empty) {
                    setText(prostor.getNazev());
                    URL urlObrazku = getClass().getResource(prostor.getNazev() + ".jpg");
                    if (urlObrazku == null) return;
                    ImageView iw = new ImageView(urlObrazku.toString());
                    iw.setFitHeight(100);
                    iw.setPreserveRatio(true);
                    setGraphic(iw);

                } else {
                    setText(null);
                    setGraphic(null);
                }
            }

        });

        panelInventar.setCellFactory(inventarListView -> new ListCell<>() {
            @Override
            protected void updateItem(Vec vec, boolean empty) {
                super.updateItem(vec, empty);
                if (!empty) {
                    String nazevVeci = vec.getNazev();
                    setText(nazevVeci);
                    URL urlObrazku = getClass().getResource(vec.getNazev() + ".jpg");
                    if (urlObrazku == null) return;
                    ImageView iw = new ImageView(urlObrazku.toString());
                    iw.setFitHeight(50);
                    iw.setPreserveRatio(true);
                    setGraphic(iw);

                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        });


    }

    /**
     * Metoda plní panel východů dostupnými východy pro hráče.
     */
    private void naplneniPaneluVychodu() {
        panelVychodu.getItems().clear(); //aby se po každém pohybu clearnul ten seznam a nebyly by tam old entries
        Collection<Prostor> vychody = hra.getHerniPlan().getAktualniProstor().getVychody();
        panelVychodu.getItems().addAll(vychody);

    }

    /**
     * Metoda plní panel inventáře, pokud hráč má nějaké předměty v něm.
     */
    private void naplneniPaneluInventare() {
        panelInventar.getItems().clear();
        Collection<Vec> collectionVeci = hra.getInventar().returnVeci();
        panelInventar.getItems().addAll(collectionVeci);
    }


    /**
     * Metoda zpracuje příkaz hráče a zároveň v okně maže ">" které se zobrazují z konzolového zobrazení.
     * Pokud metoda zjistí, že je konec hry, zablokuje hráči možnost klikat na pole vstupu, znemožní se panely inventáře
     * a východů, zablokuje se tlačítko vstupu.
     * @param prikaz příkaz zadaný hráčem
     */
    private void zpracujPrikaz(String prikaz) {
        if (prikaz.isBlank())
            return;
        vystup.appendText("> " + prikaz + "\n");
        String vysledek = hra.zpracujPrikaz(prikaz);
        vystup.appendText(vysledek + "\n\n");

        if (hra.konecHry()) {
            vystup.appendText(hra.vratEpilog());
            vstup.setDisable(true);
            proved.setDisable(true);
            panelVychodu.setDisable(true);
            panelInventar.setDisable(true);
        }

    }

    /**
     * Metoda převezme text z pole vstupů a zpracuje jej.
     */
    @FXML
    private void zpracujVstup() {
        String prikaz = vstup.getText();
        vstup.clear();
        zpracujPrikaz(prikaz);
    }

    /**
     * Metoda volá další metody, které mění polohu hráče, jeho inventář a panel východů.
     * Metoda převzata z intefacu Pozorovatel.
     */
    @Override
    public void update() {
        naplneniPaneluVychodu();
        naplneniPaneluInventare();
        posunHrace();

    }

    /**
     * Metoda nastavuje polohu hráče na mapě na základě v jakém prostoru se nachází.
     */
    private void posunHrace() {
        String nazevProstoru = hra.getHerniPlan().getAktualniProstor().getNazev();
        Point2D souradnice = souradniceProstoru.get(nazevProstoru);
        hrac.setLayoutX(souradnice.getX());
        hrac.setLayoutY(souradnice.getY());
    }

    /**
     * Metoda zpracovává klikání na východy a podle nich se může hráč posouvat.
     * @param mouseEvent kliknutí myši
     */
    public void clickPanelVychodu(MouseEvent mouseEvent) {
        Prostor cilovyProstor = panelVychodu.getSelectionModel().getSelectedItem();
        if (cilovyProstor == null)
            return;
        zpracujPrikaz(PrikazJdi.NAZEV + " " + cilovyProstor);
    }

    /**
     * Metoda zobrazí nápovědu z HTML souboru, když hráč klikne na tlačítko nápovědy v liště.
     * Vytvoří se nové okno s webview, kde se HTML soubor načte.
     * Nastaví se dále jméno okna a ikona hry.
     * @param actionEvent kliknutí tlačítka
     */
    public void clickNapoveda(ActionEvent actionEvent) {
        Stage stage = new Stage();
        String url = getClass().getResource("help.html").toExternalForm();
        WebView webView = new WebView();
        webView.getEngine().load(url);
        Scene scene = new Scene(webView);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("hrac.png")));
        stage.setScene(scene);
        stage.setTitle("Zapomnění - luut02 - nápověda ke hře");
        stage.show();
    }

    /**
     * Metoda vyčistí panel s výstupem a začne novou hru. Zavolá také metodu initialize, která nastaví počáteční
     * sekvenci hry.
     * @param actionEvent kliknutí tlačítka
     */
    public void clickNovaHra(ActionEvent actionEvent) {
        vystup.clear();
        hra = new Hra();
        initialize();
    }


}