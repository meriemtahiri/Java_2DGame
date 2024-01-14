module com.example.stage2_jeu2d {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.stage2_jeu2d to javafx.fxml;
    exports com.example.stage2_jeu2d;
}