package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy  
 * SeznamPrikazu
 *
 * @author Luboš Pavlíček
 * @version pro školní rok 2016/2017
 */
public class SeznamPrikazuTest {
    private Hra hra;
    private PrikazKonec prKonec;
    private PrikazJdi prJdi;

    @BeforeEach
    public void setUp() {
        hra = new Hra();
        prKonec = new PrikazKonec(hra);
        prJdi = new PrikazJdi(hra.getHerniPlan());
    }

    @Test
    public void testVlozeniVybrani() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        assertEquals(prKonec, seznPrikazu.vratPrikaz("konec"));
        assertEquals(prJdi, seznPrikazu.vratPrikaz("jdi"));
        assertNull(seznPrikazu.vratPrikaz("nápověda"));
    }

    @Test
    public void testJePlatnyPrikaz() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        assertTrue(seznPrikazu.jePlatnyPrikaz("konec"));
        assertTrue(seznPrikazu.jePlatnyPrikaz("jdi"));
        assertFalse(seznPrikazu.jePlatnyPrikaz("nápověda"));
        assertFalse(seznPrikazu.jePlatnyPrikaz("Konec"));
    }

    @Test
    public void testNazvyPrikazu() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        String nazvy = seznPrikazu.vratNazvyPrikazu();
        assertTrue(nazvy.contains("konec"));
        assertTrue(nazvy.contains("jdi"));
        assertFalse(nazvy.contains("nápověda"));
        assertFalse(nazvy.contains("Konec"));
    }

}
