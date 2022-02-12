module threadrunner2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
	requires javafx.base;

    opens fi.utu.tech.threadrunner2 to javafx.fxml;
    opens fi.utu.tech.threadrunner2.ui to javafx.fxml;
    opens fi.utu.tech.threadrunner2.works to javafx.base;
    exports fi.utu.tech.threadrunner2.assignment;
    exports fi.utu.tech.threadrunner2.works;
    exports fi.utu.tech.threadrunner2.mediator;
    exports fi.utu.tech.threadrunner2.ui;
    exports fi.utu.tech.threadrunner2;
}
