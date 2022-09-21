package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Třída ZazvonitTest testuje funkčnost příkazu 'zazvonit'.
 *
 * @author Trong Dat Luu
 * @version LS 2021/22
 */
public class ZazvonitTest {

    private Hra hraTest;

    @BeforeEach
    public void setUp() {
        hraTest = new Hra();
    }

    /**
     * Metoda 'testZazvoneni' kontroluje, zda je postava v prostoru 'kamaraduv_byt'.
     * Pokud není, vypíše se text, že postava nemá kde zazvonit.
     * Pokud je, vypíše se daný text a hra se ukončí.
     */
    @Test
    public void testZazvoneni() {
        assertEquals("domov", hraTest.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("Postava nemá kde zazvonit.", hraTest.zpracujPrikaz("zazvonit"));
        assertFalse(hraTest.konecHry());
        hraTest.zpracujPrikaz("jdi ulice");
        hraTest.zpracujPrikaz("jdi kamaraduv_byt");
        hraTest.zpracujPrikaz("zazvonit");
        assertTrue(hraTest.konecHry());

    }
}
