import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieDictionary implements Dictionary {
    private static class TrieNode {
        boolean isWord;
        int wordCount;
        HashMap<Character, TrieNode> edges;

        private TrieNode() {
            isWord = false;
            edges = new HashMap<>();
            wordCount = 0;
        }
    }

    private TrieNode root;

    public TrieDictionary() {
        root = new TrieNode();
    }

    // return false if word is already at TrieDictionary
    @Override
    public boolean add(String word) {
        if (search(word)) {
            return false;
        }
        TrieNode searchNode = root;
        searchNode.wordCount++;
        for (int i = 0; i < word.length(); i++) {
            TrieNode postNode = searchNode.edges.get(word.charAt(i));
            if (postNode == null) {
                postNode = new TrieNode();
                searchNode.edges.put(word.charAt(i), postNode);
            }
            searchNode = postNode;
            searchNode.wordCount++;
        }
        searchNode.isWord = true;
        return true;
    }

    // return false if word is not in TrieDictionary
    @Override
    public boolean delete(String word) {
        if (!search(word)) {
            return false;
        }
        TrieNode searchNode = root;
        searchNode.wordCount--;
        for (int i = 0; i < word.length(); i++) {
            TrieNode postNode = searchNode.edges.get(word.charAt(i));
            postNode.wordCount--;
            if (postNode.wordCount == 0) {
                searchNode.edges.remove(word.charAt(i));
                return true;
            }
            searchNode = postNode;
        }

        return true;

    }

    @Override
    public boolean search(String word) {
        TrieNode searchNode = root;
        for (int i = 0; i < word.length(); i++) {
            searchNode = searchNode.edges.get(word.charAt(i));
            if (searchNode == null) {
                return false;
            }
        }
        return searchNode.isWord;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return root.wordCount;
    }

    public List<String> findAllWordsWithPrefix(String prefix){
        List<String> words = new ArrayList<>();
        TrieNode searchNode = root;

        for (int i = 0; i < prefix.length(); i++) {
            searchNode = searchNode.edges.get(prefix.charAt(i));
            if (searchNode == null) {
                return words;
            }
        }
        findWords(searchNode,new StringBuilder(prefix),words);
        return words;
    }

    public List<String> findAllWords(){
        List<String> words = new ArrayList<>();
        findWords(root,new StringBuilder(), words);
        return words;
    }

    private void findWords(TrieNode root, StringBuilder sb, List<String> result) {
        if (root.isWord) {
            result.add(sb.toString());
        }
        for (Map.Entry<Character,TrieNode> edge : root.edges.entrySet()) {
            sb.append(edge.getKey());
            findWords(edge.getValue(),sb,result);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
