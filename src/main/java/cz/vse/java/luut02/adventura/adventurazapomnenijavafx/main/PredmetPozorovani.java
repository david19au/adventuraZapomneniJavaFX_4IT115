package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.main;

/**
 * influencer, celebrita, observable
 */

public interface PredmetPozorovani {
    /**
     * možnost přihlásit se k "odběru" změn v předmětu který pozoruje
     *
     * @param pozorovatel
     */
    void registruj(Pozorovatel pozorovatel);
}
