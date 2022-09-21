package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tato třída InventarTest testuje funkčnost inventáře pro postavu.
 *
 * @author Trong Dat Luu
 * @version LS 2021/22
 */
public class InventarTest {

    private Inventar inventarTest;
    private Vec vecTest1;
    private Vec vecTest2;
    private Vec vecTest3;

    @BeforeEach
    public void setUp() {
        inventarTest = new Inventar();
        vecTest1 = new Vec("Vec1", true);
        vecTest2 = new Vec("Vec2", true);
        vecTest3 = new Vec("Vec3", true);
    }

    /**
     * Metoda testInventar kontroluje vložení 'Vec1' do inventáře a jestli metoda 'obsahujeVInventari' najde danou věc v
     * inventáři. To samé se udělá s přidáním druhé věci.
     * Přidání třeti věci se nepodaří a v inventáři jsou stále jenom 2 věci a 'Vec3' není přítomna v inventáři.
     * Když se ale odebere 'Vec2', udělá se místo pro 'Vec3' a věc půjde přidat.
     */
    @Test
    public void testInventar() {
        inventarTest.vlozeniVeciDoInv(vecTest1);
        assertEquals(1, inventarTest.getPocetVeci());
        assertTrue(inventarTest.obsahujeVInventari("Vec1"));
        inventarTest.vlozeniVeciDoInv(vecTest2);
        assertEquals(2, inventarTest.getPocetVeci());
        assertTrue(inventarTest.obsahujeVInventari("Vec1"));
        assertTrue(inventarTest.obsahujeVInventari("Vec2"));
        inventarTest.vlozeniVeciDoInv(vecTest3);
        assertEquals(2, inventarTest.getPocetVeci());
        assertTrue(inventarTest.obsahujeVInventari("Vec1"));
        assertTrue(inventarTest.obsahujeVInventari("Vec2"));
        assertFalse(inventarTest.obsahujeVInventari("Vec3"));
        inventarTest.odebraniVeciZInv("Vec2");
        assertEquals(1, inventarTest.getPocetVeci());
        inventarTest.vlozeniVeciDoInv(vecTest3);
        assertEquals(2, inventarTest.getPocetVeci());
        assertTrue(inventarTest.obsahujeVInventari("Vec3"));
        assertFalse(inventarTest.obsahujeVInventari("Vec2"));
        assertTrue(inventarTest.obsahujeVInventari("Vec1"));


    }
}
