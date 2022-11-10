module cz.vse.java.luut02.adventura.adventurazapomnenijavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens cz.vse.java.luut02.adventura.adventurazapomnenijavafx.main to javafx.fxml, javafx.web;
    exports cz.vse.java.luut02.adventura.adventurazapomnenijavafx.main;
}