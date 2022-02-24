module fish.eyebrow.queryj {
    requires javafx.fxml;
    requires javafx.controls;
    requires unirest.java;
    requires com.google.gson;

    exports fish.eyebrow.queryj;
    opens fish.eyebrow.queryj;
    exports fish.eyebrow.queryj.querytree.util;
    opens fish.eyebrow.queryj.querytree.util;
    exports fish.eyebrow.queryj.querytree;
    opens fish.eyebrow.queryj.querytree;
    exports fish.eyebrow.queryj.querypane;
    opens fish.eyebrow.queryj.querypane;
    exports fish.eyebrow.queryj.querypane.headersbox;
    opens fish.eyebrow.queryj.querypane.headersbox;
    exports fish.eyebrow.queryj.querytree.renamedialog;
    opens fish.eyebrow.queryj.querytree.renamedialog;
    exports fish.eyebrow.queryj.persist;
    opens fish.eyebrow.queryj.persist;
    exports fish.eyebrow.queryj.persist.item;
    opens fish.eyebrow.queryj.persist.item;
}
