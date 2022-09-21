/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.main;


import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika.Hra;
import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika.IHra;
import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.uiText.TextoveRozhrani;


import java.io.IOException;

/*******************************************************************************
 * Třída Main je hlavní třídou projektu,
 * který představuje jednoduchou textovou adventuru určenou k dalším úpravám a rozšiřování
 *
 * @author Jarmila Pavlíčková, Trong Dat Luu
 * @version LS 2021/22
 */
public class Main {
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args) throws IOException {

        IHra hra = new Hra();
        TextoveRozhrani ui = new TextoveRozhrani(hra);
        ui.hraj();
    }
}
