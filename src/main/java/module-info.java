module me.mirat1618.pdfinvoice {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires itextpdf;
    requires org.apache.commons.io;

    opens me.mirat1618.pdfinvoice to javafx.fxml;
    exports me.mirat1618.pdfinvoice;
}