package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.main;

import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika.Hra;
import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika.IHra;
import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika.Prostor;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.Collection;

public class HomeController implements Observer {

    @FXML
    private TextArea vystup;

    @FXML
    private ListView<Prostor> panelVychodu;

    @FXML
    private TextField vstup;

    @FXML
    private Button proved;

    private IHra hra = new Hra();

    @FXML
    private void initialize() {
        hra.getHerniPlan().register(this);
        vystup.appendText(hra.vratUvitani() + "\n\n");
        Platform.runLater(() -> vstup.requestFocus());
        naplneniPaneluVychodu();
    }

    private void naplneniPaneluVychodu() {
        panelVychodu.getItems().clear(); //aby se po každém pohybu clearnul ten seznam a nebyly by tam old entries
        Collection<Prostor> vychody = hra.getHerniPlan().getAktualniProstor().getVychody();
        panelVychodu.getItems().addAll(vychody);
    }


    private void zpracujPrikaz(String prikaz) {
        if(prikaz.isBlank()) return;
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
    }

    public void clickPanelVychodu(MouseEvent mouseEvent) {
        Prostor cilovyProstor = panelVychodu.getSelectionModel().getSelectedItem();
        if
            (cilovyProstor==null)
            return;
        zpracujPrikaz("jdi " +cilovyProstor);
    }
}
