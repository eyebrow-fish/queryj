package fish.eyebrow.queryj.persist.item;

public interface ItemReader<T> {
    T read(String source);
}
