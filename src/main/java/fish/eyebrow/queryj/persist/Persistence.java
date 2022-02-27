package fish.eyebrow.queryj.persist;

import java.nio.file.Path;

public class Persistence {
    public static final String SAVE_DIR_STRING = System.getProperty("user.home") + "/.local/share/queryj";
    public static final Path SAVE_DIR_PATH = Path.of(Persistence.SAVE_DIR_STRING);

    public static final Path SAVED_PATH = Path.of(SAVE_DIR_STRING, "saved.json");
    public static final Path STATE_PATH = Path.of(SAVE_DIR_STRING, "state.json");

    private Persistence() {
    }
}
