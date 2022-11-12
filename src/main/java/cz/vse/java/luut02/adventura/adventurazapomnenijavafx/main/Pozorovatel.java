package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.main;

/**
 * Interface Pozorovatel je jednoduchý Observer, který sleduje dění v určitých věcech, ke kterým je zaregistrovaný.
 * Dá se chápat jako "follower" na sociálních sítích.
 * @author Trong Dat Luu
 * @version ZS 2022/23
 */

public interface Pozorovatel {
    /**
     * Aktualizační metoda, umožňuje předmětu pozorování aby poslal pozorovateli zprávu
     */
    void update();
}
