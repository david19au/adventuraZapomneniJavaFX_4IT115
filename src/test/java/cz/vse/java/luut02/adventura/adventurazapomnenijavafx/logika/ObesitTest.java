package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Třída ObesitTest testuje funkčnost příkazu 'obesit'.
 *
 * @author Trong Dat Luu
 * @version LS 2021/22
 */
public class ObesitTest {

    private Hra hraTest;

    @BeforeEach
    public void setUp() {
        hraTest = new Hra();
    }

    /**
     * Metoda 'testObeseni' kontroluje, že se postava nemůže oběsit nikde jinde než v prostoru 'domov'.
     * Pokud je v prostoru 'domov', může se oběsit a příkaz ukončí hru s daným endingem.
     */
    @Test
    public void testObeseni() {
        assertEquals("domov", hraTest.getHerniPlan().getAktualniProstor().getNazev());
        hraTest.zpracujPrikaz("jdi kuchyn");
        hraTest.zpracujPrikaz("obesit");
        assertFalse(hraTest.konecHry());
        hraTest.zpracujPrikaz("jdi domov");
        hraTest.zpracujPrikaz("obesit");
        assertTrue(hraTest.konecHry());
    }


}
