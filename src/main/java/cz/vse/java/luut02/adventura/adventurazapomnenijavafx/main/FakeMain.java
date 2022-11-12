package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.main;

import java.io.IOException;

/*******************************************************************************
 * Třída Main je (falešnou) hlavní třídou projektu.
 * Napomáhá ke spuštění fat jaru, aby JRE si myslelo, že se nejedná o JFX aplikaci a spustila se správně.
 *
 * @author Trong Dat Luu
 * @version ZS 2022/23
 */
public class FakeMain {
    /**
     * Metoda, která zavolá pravou třídu Main a předá jí případné args.
     * @param args Parametry příkazového řádku / parametry spuštění, které se předají pravému Main
     */
    public static void main(String[] args) throws IOException {
        Main.main(args);
    }
}