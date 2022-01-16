module fish.eyebrow.queryj {
    requires javafx.fxml;
    requires javafx.controls;

    exports fish.eyebrow.queryj;
    opens fish.eyebrow.queryj;
    exports fish.eyebrow.queryj.querytree.util;
    opens fish.eyebrow.queryj.querytree.util;
    exports fish.eyebrow.queryj.querytree;
    opens fish.eyebrow.queryj.querytree;
    exports fish.eyebrow.queryj.querypane;
    opens fish.eyebrow.queryj.querypane;
}
