package fish.eyebrow.queryj.persist.item;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Supplier;

public record ItemPersistenceWriter<T>(ItemWriter<T> writer, Path path, Supplier<Optional<T>> supplier) {
    public void write() throws IOException {
        if (Files.notExists(path)) Files.createFile(path);

        JsonObject jsonObject = supplier.get().map(writer::write).orElse(new JsonObject());

        Files.writeString(path, jsonObject.toString());
    }
}
