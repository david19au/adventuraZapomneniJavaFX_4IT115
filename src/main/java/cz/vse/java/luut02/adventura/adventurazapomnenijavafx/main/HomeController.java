package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.main;

import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika.Hra;
import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika.IHra;
import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika.Prostor;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Collection;

public class HomeController {

    @FXML
    private TextArea output;

    @FXML
    private ListView panelVychodu;

    @FXML
    private TextField input;

    private IHra hra = new Hra();

    @FXML
    private void initialize() {
        output.appendText(hra.vratUvitani() + "\n\n");
        Platform.runLater(() -> input.requestFocus());
        naplneniPaneluVychodu();
    }

    private void naplneniPaneluVychodu() {
        Collection<Prostor> vychody = hra.getHerniPlan().getAktualniProstor().getVychody();
        panelVychodu.getItems().addAll(vychody);
    }

    @FXML
    private void processCommand() {
        String prikaz = input.getText();
        input.clear();
        output.appendText("> " + prikaz + "\n");

        String vysledek = hra.zpracujPrikaz(prikaz);
        output.appendText(vysledek + "\n\n");

        if (hra.konecHry()) {
            output.appendText(hra.vratEpilog());
            input.setDisable(true);
        }
        //FIXME
        //Opravit můj způsob jak ukončit hru
        //

    }


}
