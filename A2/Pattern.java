import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pattern {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String pattern = br.readLine();
        String text = br.readLine();
        int occurrences = 0;

        // Calculate the hash code of the pattern
        int patternHash = StringHash(pattern);

        // Calculate the initial hash code of the first substring in the text
        int textHash = StringHash(text.substring(0, pattern.length()));

        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            if (textHash == patternHash) {
                boolean isMatch = true;
                for (int j = 0; j < pattern.length(); j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        isMatch = false;
                        break;
                    }
                }
                if (isMatch) {
                    occurrences++;
                }
            }
        }
        System.out.println(occurrences);
    }

    // Function to calculate the hash code of a string
    public static int StringHash(String str) {
        int hash = 0;

        for (int i = 0; i < str.length(); i++) {
            hash = (31 * hash + str.charAt(i));
        }

        return hash;
    }
}
