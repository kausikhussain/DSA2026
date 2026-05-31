package Day15_Tries;

public class LongestWord {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
        boolean isEnd;
    }

    private TrieNode root = new TrieNode();
    private String result = "";

    public String longestWord(String[] words) {
        // Build the Trie
        for (String word : words) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (curr.children[c - 'a'] == null) {
                    curr.children[c - 'a'] = new TrieNode();
                }
                curr = curr.children[c - 'a'];
            }
            curr.word = word;
            curr.isEnd = true;
        }

        // DFS to find the longest valid word
        dfs(root);
        return result;
    }

    private void dfs(TrieNode node) {
        if (node == null) return;
        
        // Update the result if we find a longer valid word
        if (node.word != null) {
            if (node.word.length() > result.length() || 
               (node.word.length() == result.length() && node.word.compareTo(result) < 0)) {
                result = node.word;
            }
        }

        // Only traverse children that are the end of a valid dictionary word
        // (as per problem rules: a word can be built one character at a time)
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null && node.children[i].isEnd) {
                dfs(node.children[i]);
            }
        }
    }
}
