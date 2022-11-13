/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.main;


import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika.Hra;
import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika.IHra;
import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.uiText.TextoveRozhrani;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;

/*******************************************************************************
 * Třída Main je hlavní třídou projektu,
 * který představuje jednoduchou adventuru určenou k dalším úpravám a rozšiřování.
 * Třída by default zapíná své grafické rozhraní.
 *
 * @author Jarmila Pavlíčková, Trong Dat Luu
 * @version ZS 2022/23
 */
public class Main extends Application {
    /***************************************************************************
     * Pravá metoda main, prostřednictvím níž se spouští celá aplikace.
     * Pokud se zjistí, že jsou napsané args a je první arg je "text", zapne se hra v textovém režimu.
     * Pokud ne, zapne se grafické rozhraní.
     *
     * @param args Parametry příkazového řádku / parametry spuštění
     */
    public static void main(String[] args) throws IOException {


        if (args.length > 0 && args[0].equals("-text")) {
            IHra hra = new Hra();
            TextoveRozhrani ui = new TextoveRozhrani(hra);
            ui.hraj();
        } else {
            launch();
        }

    }

    /**
     * Metoda start zapne hru v grafickém prostředí.
     * Použije se soubor home.fxml, který obsahuje, kde se nachází jaké prvky, vytvoří a nastaví se nová scene,
     * nastaví se jméno, ikonka okna a nastaví se tak, aby okno se zapnulo maximalizované.
     * @param stage stage GUI
     * @throws Exception vyhodí chybu
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        loader.load();
        Scene scene = new Scene(loader.getRoot());
        stage.setScene(scene);
        stage.setTitle("Zapomnění - luut02");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("hrac.png")));
        stage.show();
        stage.setMaximized(true);
    }
}
