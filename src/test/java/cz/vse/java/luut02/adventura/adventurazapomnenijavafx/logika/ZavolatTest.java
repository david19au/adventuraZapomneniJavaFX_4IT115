package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Třída ZavolatTest testuje funkčnost příkazu 'zavolat'.
 *
 * @author Trong Dat Luu
 * @version LS 2021/22
 */
public class ZavolatTest {

    private Hra hraTest;

    @BeforeEach
    public void setUp() {
        hraTest = new Hra();
    }

    /**
     * Metoda 'testZavolatRodicum' kontroluje, že se příkaz neprovede, pokud není postava není v prostoru 'domov'.
     * Pokud postava je, příkaz vypíše daný text.
     */
    @Test
    public void testZavolatRodicum() {
        assertEquals("domov", hraTest.getHerniPlan().getAktualniProstor().getNazev());
        hraTest.zpracujPrikaz("jdi kuchyn");
        assertEquals("Nemohu zde volat.", hraTest.zpracujPrikaz("zavolat"));
        hraTest.zpracujPrikaz("jdi domov");
        assertEquals("Postava zavolala svým rodičům a nebyla to příjemná povídaná.\nRodiče nezajímaly problémy postavy a vinu shazovaly na postavu, že si neumí správně zařídit čas.\nPostava se nyní cítí ještě více bezcenná, než předtím.", hraTest.zpracujPrikaz("zavolat"));
    }

}
