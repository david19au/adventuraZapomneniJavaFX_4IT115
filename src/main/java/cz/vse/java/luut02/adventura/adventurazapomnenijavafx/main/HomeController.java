package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.main;

import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika.Hra;
import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika.IHra;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HomeController {

    @FXML
    private TextArea vystup;

    @FXML
    private TextField vstup;

    private IHra hra = new Hra();

    @FXML
    private void initialize() {
        vystup.appendText(hra.vratUvitani()+"\n\n");
        Platform.runLater(() -> vstup.requestFocus());
    }

    @FXML
    private void processCommand() {
        String prikaz = vstup.getText();
        vstup.clear();
        vystup.appendText("> "+prikaz+"\n");

        String vysledek = hra.zpracujPrikaz(prikaz);
        vystup.appendText(vysledek+"\n\n");

        if (hra.konecHry()) {
            vystup.appendText(hra.vratEpilog());
            vstup.setDisable(true);
        }
    }

}
