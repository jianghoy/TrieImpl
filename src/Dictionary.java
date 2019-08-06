public interface Dictionary {
    boolean search(String word);
    boolean add(String word);
    boolean delete(String word);
    int size();
    boolean isEmpty();
}
