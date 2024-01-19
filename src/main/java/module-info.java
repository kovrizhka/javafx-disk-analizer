module ru.kovrizhkin.study.javafxdiskanalyzer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens ru.kovrizhkin.study.javafxdiskanalyzer to javafx.fxml;
    exports ru.kovrizhkin.study.javafxdiskanalyzer;
}