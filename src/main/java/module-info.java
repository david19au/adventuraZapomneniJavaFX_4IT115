module cz.vse.java.luut02.adventura.adventurazapomnenijavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens cz.vse.java.luut02.adventura.adventurazapomnenijavafx to javafx.fxml;
    exports cz.vse.java.luut02.adventura.adventurazapomnenijavafx;
}