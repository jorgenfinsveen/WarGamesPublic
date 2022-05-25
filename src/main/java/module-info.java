module wargames {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.elusive;
    requires MaterialFX;

    opens idatx2001.jorgfi.wargamesApp.controllers to javafx.fxml, javafx.graphics;
    opens idatx2001.jorgfi.wargamesApp;

    exports idatx2001.jorgfi.wargamesApp;
    exports idatx2001.jorgfi.wargamesApp.model;
    exports idatx2001.jorgfi.wargamesApp.controllers;
    exports idatx2001.jorgfi.wargamesApp.tools;
    exports idatx2001.jorgfi.wargamesApp.other;
    opens idatx2001.jorgfi.wargamesApp.model;
}
