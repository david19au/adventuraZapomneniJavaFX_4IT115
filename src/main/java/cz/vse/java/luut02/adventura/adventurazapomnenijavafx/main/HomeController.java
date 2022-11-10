package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.main;

import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HomeController implements Pozorovatel {

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
    private Inventar inventar = new Inventar();


    @FXML
    private void initialize() {
        hra.getHerniPlan().registruj(this);
        vystup.appendText(hra.vratUvitani() + "\n\n");
        Platform.runLater(() -> vstup.requestFocus());
        naplneniPaneluVychodu();

        souradniceProstoru.put("kuchyn", new Point2D(529, 34));
        souradniceProstoru.put("domov", new Point2D(425, 84));
        souradniceProstoru.put("ulice", new Point2D(333, 123));
        souradniceProstoru.put("kamaraduv_byt", new Point2D(326, 43));
        souradniceProstoru.put("koleje", new Point2D(116, 64));
        souradniceProstoru.put("skola", new Point2D(289, 197));
        souradniceProstoru.put("hlavni_nadrazi", new Point2D(202, 118));
        souradniceProstoru.put("karluv_most", new Point2D(537, 175));
        souradniceProstoru.put("drogovy_dealer", new Point2D(453, 248));
        souradniceProstoru.put("psycholog", new Point2D(168, 212));
        souradniceProstoru.put("strecha_skoly", new Point2D(284, 277));
        souradniceProstoru.put("vaclavske_namesti", new Point2D(416, 174));

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
    }

    private void naplneniPaneluVychodu() {
        panelVychodu.getItems().clear(); //aby se po každém pohybu clearnul ten seznam a nebyly by tam old entries
        Collection<Prostor> vychody = hra.getHerniPlan().getAktualniProstor().getVychody();
        panelVychodu.getItems().addAll(vychody);

    }

    //TODO
    //panel inventáře
/*    private void naplneniPanelInventar() {
        panelInventar.getItems().clear(); //aby se po každém pohybu clearnul ten seznam a nebyly by tam old entries
        Collection<Vec> veci = inventar.get;
        panelInventar.getItems().addAll(veci);

    }*/



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
        }

    }

    @FXML
    private void zpracujVstup() {
        String prikaz = vstup.getText();
        vstup.clear();
        zpracujPrikaz(prikaz);
    }


    @Override
    public void update() {
        naplneniPaneluVychodu();
        posunHrace();
    }

    private void posunHrace() {
        String nazevProstoru = hra.getHerniPlan().getAktualniProstor().getNazev();
        Point2D souradnice = souradniceProstoru.get(nazevProstoru);
        hrac.setLayoutX(souradnice.getX());
        hrac.setLayoutY(souradnice.getY());
    }

    public void clickPanelVychodu(MouseEvent mouseEvent) {
        Prostor cilovyProstor = panelVychodu.getSelectionModel().getSelectedItem();
        if (cilovyProstor == null)
            return;
        zpracujPrikaz(PrikazJdi.NAZEV + " " + cilovyProstor);
    }

    public void clickNapoveda(ActionEvent actionEvent) {
        Stage stage = new Stage();
        String url = getClass().getResource("help.html").toExternalForm();
        WebView webView = new WebView();
        webView.getEngine().load(url);
        Scene scene = new Scene(webView);
        stage.setScene(scene);
        stage.show();
    }


}
