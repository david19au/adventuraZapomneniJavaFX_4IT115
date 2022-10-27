package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.main;

import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika.Hra;
import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika.IHra;
import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika.PrikazJdi;
import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika.Prostor;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HomeController implements Observer {

    @FXML
    private ImageView hrac;
    @FXML
    private TextArea vystup;

    @FXML
    private ListView<Prostor> panelVychodu;

    @FXML
    private TextField vstup;

    @FXML
    private Button proved;

    private Map<String, Point2D> souradniceProstoru = new HashMap<>();

    private IHra hra = new Hra();

    @FXML
    private void initialize() {
        hra.getHerniPlan().register(this);
        vystup.appendText(hra.vratUvitani() + "\n\n");
        Platform.runLater(() -> vstup.requestFocus());
        naplneniPaneluVychodu();

        //TODO dodělat tyhle prostory a souřadnice pomocí scenebuilderu
        souradniceProstoru.put("kuchyň", new Point2D(14, 73));
        souradniceProstoru.put("domov", new Point2D(20, 73));
        souradniceProstoru.put("ulice", new Point2D(10, 73));
        souradniceProstoru.put("kamaraduvByt", new Point2D(50, 73));
        souradniceProstoru.put("koleje", new Point2D(30, 73));
        souradniceProstoru.put("skola", new Point2D(74, 73));
        souradniceProstoru.put("hlavniNadrazi", new Point2D(24, 73));
        souradniceProstoru.put("karluvMost", new Point2D(34, 73));
        souradniceProstoru.put("drogovydealer", new Point2D(84, 73));
    }

    private void naplneniPaneluVychodu() {
        panelVychodu.getItems().clear(); //aby se po každém pohybu clearnul ten seznam a nebyly by tam old entries
        Collection<Prostor> vychody = hra.getHerniPlan().getAktualniProstor().getVychody();
        panelVychodu.getItems().addAll(vychody);
    }


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
        //FIXME
        //Opravit můj způsob jak ukončit hru
        //tuna exceptions lmao
        //

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
}
