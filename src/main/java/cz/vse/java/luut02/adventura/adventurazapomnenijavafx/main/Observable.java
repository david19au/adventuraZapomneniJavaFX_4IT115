package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.main;

/**
 * influencer, celebrita, observable
 */

public interface Observable {
    /**
     * možnost přihlásit se k "odběru" změn v předmětu který pozoruje
     *
     * @param observer
     */
    void register(Observer observer);
}
