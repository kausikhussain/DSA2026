package Day15_Tries;

import java.util.List;

public class ReplaceWords {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word; // Store the word here to easily retrieve the root
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        TrieNode root = new TrieNode();
        
        // Build the Trie with dictionary roots
        for (String word : dictionary) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (curr.children[c - 'a'] == null) {
                    curr.children[c - 'a'] = new TrieNode();
                }
                curr = curr.children[c - 'a'];
            }
            curr.word = word; // Mark the end of the root
        }
        
        // Process the sentence
        StringBuilder result = new StringBuilder();
        String[] words = sentence.split(" ");
        
        for (int i = 0; i < words.length; i++) {
            if (i > 0) result.append(" ");
            
            TrieNode curr = root;
            String word = words[i];
            
            for (char c : word.toCharArray()) {
                if (curr.children[c - 'a'] == null || curr.word != null) {
                    break;
                }
                curr = curr.children[c - 'a'];
            }
            
            // If we found a valid root, use it; otherwise use the original word
            if (curr.word != null) {
                result.append(curr.word);
            } else {
                result.append(word);
            }
        }
        
        return result.toString();
    }
}
