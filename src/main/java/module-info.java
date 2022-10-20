module com.varad.miniproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.swing;
    opens com.miniProject.carDealership to javafx.fxml;
    exports com.miniProject.carDealership;
}