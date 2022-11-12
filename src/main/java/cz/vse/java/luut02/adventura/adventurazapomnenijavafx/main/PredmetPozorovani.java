package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.main;

/**
 * Interface PredmetPozorovani označuje něco, co má cenu sledovat.
 * Můžeme jej chápat, že to je nějaký influencer, celebrita na sociálních sítích, něco observable.
 * @author Trong Dat Luu
 * @version ZS 2022/23
 */

public interface PredmetPozorovani {
    /**
     * Dává možnost přihlásit se k "odběru" změn v předmětu který pozoruje.
     *
     * @param pozorovatel
     */
    void registruj(Pozorovatel pozorovatel);
}
