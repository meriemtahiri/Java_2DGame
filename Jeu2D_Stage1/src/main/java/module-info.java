module ma.enset.jeu2d_stage1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;



    opens ma.enset.jeu2d_stage1 to javafx.fxml;
    exports ma.enset.jeu2d_stage1;
}