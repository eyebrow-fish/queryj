package fish.eyebrow.queryj.persist;

import java.nio.file.Path;

public class Persistence {
    public static final String SAVE_PATH_STRING = System.getProperty("user.home") + "/.local/share/queryj/saved.json";
    public static final Path SAVE_PATH = Path.of(SAVE_PATH_STRING);

    private Persistence() {}
}
