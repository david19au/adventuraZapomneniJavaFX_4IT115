package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.main;

/**
 * follower, observer
 */

public interface Observer {
    /**
     * aktualizační metoda, umožňuje předmětu pozorování aby poslal pozorovateli zprávu
     */
    void update();
}
