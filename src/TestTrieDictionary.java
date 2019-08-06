import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class TestTrieDictionary {

    @Test
    public void testAdd(){
        Dictionary dictionary = new TrieDictionary();
        dictionary.add("word");
        assertTrue(dictionary.search("word"));
        dictionary.add("apple");
        assertTrue(dictionary.search("word"));
        assertTrue(dictionary.search("apple"));
    }

    @Test
    public void testSearch(){
        Dictionary dictionary = new TrieDictionary();
        assertFalse(dictionary.search("word"));
        dictionary.add("word");
        assertTrue(dictionary.search("word"));
    }

    @Test
    public void testSizeAndIsEmpty() {
        Dictionary dictionary = new TrieDictionary();
        assertTrue(dictionary.isEmpty());
        dictionary.add("word");
        assertFalse(dictionary.isEmpty());
        assertEquals(1,dictionary.size());
        dictionary.add("word");
        assertEquals(1,dictionary.size());
    }

    @Test
    public void testDelete() {
        Dictionary dictionary = new TrieDictionary();
        dictionary.add("word");
        assertEquals(1,dictionary.size());
        dictionary.delete("word");
        assertTrue(dictionary.isEmpty());
        assertFalse(dictionary.search("word"));
    }

    @Test
    public void testMultiAddAndDel(){
        Dictionary dictionary = new TrieDictionary();
        dictionary.add("word");
        dictionary.add("apple");
        dictionary.add("facebook");
        dictionary.add("banana");
        assertTrue(dictionary.search("word"));
        assertTrue(dictionary.search("apple"));
        assertTrue(dictionary.search("facebook"));
        assertTrue(dictionary.search("banana"));
        dictionary.delete("apple");
        assertTrue(dictionary.search("word"));
        assertTrue(dictionary.search("facebook"));
        assertTrue(dictionary.search("banana"));
        assertFalse(dictionary.search("apple"));
    }

    @Test
    public void testFindAllWords() {
        List<String> list = new ArrayList<>();
        list.add("word");
        list.add("facebook");
        list.add("banana");
        TrieDictionary dictionary = new TrieDictionary();
        for (String word:list){
            dictionary.add(word);
        }
        assertEquals(new HashSet<>(list),new HashSet<>(dictionary.findAllWords()));
    }

    @Test
    public void testFindAllWordsWithPrefix() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("app");
        List<String> newList = new ArrayList<>();
        newList.add("ass");
        newList.add("banana");
        newList.add("frequency");
        TrieDictionary trieDictionary = new TrieDictionary();
        for (String word: list){
            trieDictionary.add(word);
        }
        for (String word: newList) {
            trieDictionary.add(word);
        }
        assertEquals(new HashSet<>(list),new HashSet<>(trieDictionary.findAllWordsWithPrefix("app")));
    }
}
