import java.util.HashMap;

import java.util.*;

public class strip {
    public static void main(String[] args) {
        String text = "apples,# pears # and bananas\ngrapes\nbananas !apples";
        String[] commentSymbols = new String[2];
        commentSymbols[0] = "#";
        commentSymbols[1] = "!";
        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            // Check if the character is a letter
            if (Character.isLetter(c)) {
                // Convert the letter to lowercase to avoid counting upper and lower case separately
                c = Character.toLowerCase(c);

                // Check if the letter is already in the map
                if (hashMap.containsKey(c)) {
                    // If it is, increment the count
                    hashMap.put(c, hashMap.get(c) + 1);
                } else {
                    // If it's not, add it to the map with a count of 1
                    hashMap.put(c, 1);
                }
            }
        }
        for (Map.Entry<Character, Integer> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    public static String stripComments(String text, String[] commentMarkers) {
        StringBuilder result = new StringBuilder();
        String[] lines = text.split("\n");
        for (String line : lines) {
            int commentIndex = -1;
            for (String marker : commentMarkers) {
                int index = line.indexOf(marker);
                if (index >= 0 && (commentIndex < 0 || index < commentIndex)) {
                    commentIndex = index;
                }
            }
            if (commentIndex >= 0) {
                line = line.substring(0, commentIndex);
            }
            line = line.trim(); // remove trailing whitespace
            if (!line.isEmpty()) {
                result.append(line).append("\n");
            }
        }
        if (result.length() > 0) {
            result.setLength(result.length() - 1); // remove last newline
        }
        return result.toString();
    }
}
