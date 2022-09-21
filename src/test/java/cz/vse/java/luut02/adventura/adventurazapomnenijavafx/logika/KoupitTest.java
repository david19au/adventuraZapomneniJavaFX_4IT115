package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tato třída KoupitTest testuje funkčnost příkazu 'koupit'.
 *
 * @author Trong Dat Luu
 * @version LS 2021/22
 */
public class KoupitTest {

    private Hra hraTest;

    @BeforeEach
    public void setUp() {
        hraTest = new Hra();
    }

    /**
     * Tato metoda 'testNepovedenyNakupDrog' kontroluje, že se nedají koupit drogy bez peněz.
     * Hra se v tomto případě neukončí a postava se může vrátit od drogového dealera zpět na Václavské náměstí.
     */
    @Test
    public void testNepovedenyNakupDrog() {
        assertEquals("domov", hraTest.getHerniPlan().getAktualniProstor().getNazev());
        hraTest.zpracujPrikaz("jdi ulice");
        assertFalse(hraTest.konecHry());
        hraTest.zpracujPrikaz("jdi vaclavske_namesti");
        hraTest.zpracujPrikaz("jdi drogovy_dealer");
        assertEquals("drogovy_dealer", hraTest.getHerniPlan().getAktualniProstor().getNazev());
        hraTest.zpracujPrikaz("koupit");
        assertFalse(hraTest.konecHry());
        hraTest.zpracujPrikaz("jdi vaclavske_namesti");
        assertEquals("vaclavske_namesti", hraTest.getHerniPlan().getAktualniProstor().getNazev());
    }

    /**
     * Metoda 'testPovedenyNakupDrog' prověřuje, že se drogy nedají nakoupit mimo prostor 'drogovy_dealer'.
     * Dále testují, že se drogy dají koupit, pokud má postava v inventáři peníze a pokud je u drogového dealera.
     * V tomto případě se hra ukončí.
     */
    @Test
    public void testPovedenyNakupDrog() {
        assertEquals("domov", hraTest.getHerniPlan().getAktualniProstor().getNazev());
        hraTest.zpracujPrikaz("jdi kuchyn");
        hraTest.zpracujPrikaz("seber penize");
        hraTest.zpracujPrikaz("jdi domov");
        hraTest.zpracujPrikaz("koupit");
        assertFalse(hraTest.konecHry());
        hraTest.zpracujPrikaz("jdi ulice");
        hraTest.zpracujPrikaz("jdi vaclavske_namesti");
        hraTest.zpracujPrikaz("jdi drogovy_dealer");
        assertEquals("drogovy_dealer", hraTest.getHerniPlan().getAktualniProstor().getNazev());
        hraTest.zpracujPrikaz("koupit");
        assertTrue(hraTest.konecHry());
    }

}
