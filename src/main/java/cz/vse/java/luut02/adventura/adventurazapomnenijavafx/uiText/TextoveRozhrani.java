package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.uiText;


import java.io.*;
import java.util.Scanner;

import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika.IHra;

/**
 * Class TextoveRozhrani
 * <p>
 * Toto je uživatelského rozhraní aplikace Adventura
 * Tato třída vytváří instanci třídy Hra, která představuje logiku aplikace.
 * Čte vstup zadaný uživatelem a předává tento řetězec logice a vypisuje odpověď logiky na konzoli.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Trong Dat Luu
 * @version LS 2021/22
 */

public class TextoveRozhrani {
    private IHra hra;

    /**
     * Vytváří hru.
     */
    public TextoveRozhrani(IHra hra) {
        this.hra = hra;
    }

    /**
     * Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     * příkazu od hráče do konce hry (dokud metoda konecHry() z logiky nevrátí
     * hodnotu true).
     * <p>
     * Nakonec vypíše text epilogu, přičemž hra se poté ukončí stisknutím libovolného tlačítka díky 'BufferedReader' a
     * 'in.readLine()', který čeká na stisk tlačítka.
     */
    public void hraj() throws IOException {
        System.out.println(hra.vratUvitani());

        // základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.

        while (!hra.konecHry()) {
            String radek = prectiString();
            System.out.println(hra.zpracujPrikaz(radek));
        }

        System.out.println(hra.vratEpilog());
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); //BufferedReader
        in.readLine(); //vyžaduje input, který může být libovolný, klidně i prázdný
    }

    /**
     * Metoda přečte příkaz z příkazového řádku
     *
     * @return Vrací přečtený příkaz jako instanci třídy String
     */
    private String prectiString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

}
