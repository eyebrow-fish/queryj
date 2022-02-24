package fish.eyebrow.queryj.persist;

import java.nio.file.Path;

public class Persistence {
    public static final Path SAVE_PATH = Path.of(System.getProperty("user.home") + "/.local/share/queryj/saved");

    private Persistence() {}
}
