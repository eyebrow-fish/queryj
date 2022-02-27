package fish.eyebrow.queryj.persist.item;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

public record ItemPersistenceReader<T>(ItemReader<T> reader, Path path, Consumer<T> consumer) {
    public void read() throws IOException {
        if (Files.notExists(path)) return;
        consumer.accept(reader.read(Files.readString(path)));
    }
}
