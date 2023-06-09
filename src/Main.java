import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
//        load();

        //VCHVOABCQIH is example of double encrypted message
        // a message that must be decrypted twice to receive intended message:
        // in this case it needs a shift of 20
        String cipherText = "VCHVOABCQIH";
        String decrypt1 = Cipher.decrypt(cipherText, 20);
        String decrypt2 = Cipher.decrypt(decrypt1, 20);

        System.out.println();
        System.out.println("cipher text: " + cipherText);
        System.out.println("first decryption: " + decrypt1);
        System.out.println("second decryption: " + decrypt2);

        //
        //read readme.txt
        //

        //the goal of this program was to find
        // english words that encrypt/decrypt to other english words
        // so that I can find double decryptable messages
        // results.txt holds all english words that encrypt & decrypt to another word
        // based on word_list.txt
        // usable.txt is some words I've collected that are the most usable for messages
        // the load function goes through all words in word_list.txt and
        // decrypts/encrypts them with shifts from 1-25, brute-force checking if any
        // decrypt/encrypt to another word in word_list, if so
        // write that word in results.txt
    }

    public static void load() throws IOException {
        List<List<String>>  matches = new ArrayList<>();
        for (int i = 0; i < 25; i++) matches.add(new ArrayList<>());

        Set<String> words = new HashSet<>();

        //a list of words encrypted for each shift (1-25)
        List<ArrayList<String>> encryptedWords = new ArrayList<>();
        for (int i = 0; i < 25; i++) encryptedWords.add(new ArrayList<>());

        //fill words list & fill encrypted for all shifts
        Scanner sc = new Scanner(new File("word_list.txt"));
        int l = 0;
        while (sc.hasNextLine()) {
            l++;
            if (l % 10000 == 0) System.out.println(l + " / 370000");
            String s = sc.nextLine().strip().toLowerCase(Locale.ROOT);
            words.add(s);

            for (int i = 0; i < 25; i++) {
                encryptedWords.get(i).add(Cipher.encrypt(s, i + 1));
            }
        }

        l = 0;
        for (int i = 0; i < 25; i++) {
            l++;
            if (l % 10000 == 0) System.out.println(l + " / 370000");

            for (int j = 0; j < encryptedWords.get(i).size(); j++) {
                String encr = encryptedWords.get(i).get(j);
                if (words.contains(encr)) {
                    matches.get(i).add(encr);
                    System.out.println("match!");
                }
            }
        }

        //word length <= cutoff not printed
        int cutoff = 0;

        BufferedWriter bf = new BufferedWriter(new FileWriter("results.txt"));
        bf.write("Words greater than length " + cutoff + " that encrypt to other words:");
        bf.newLine();

        List<Integer> wordLengths = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < matches.get(i).size(); j++) {
                String w = matches.get(i).get(j);
                if (w.length() <= cutoff) continue;

                if (w.length() > wordLengths.size()) {
                    wordLengths.add(1);
                } else {
                    wordLengths.set(w.length() - 1, wordLengths.get(w.length() - 1) + 1);
                }

                bf.write("shift: " + (i + 1) + " " + Cipher.decrypt(w, i + 1) + " -> " + w);
                bf.newLine();
            }
        }

        bf.newLine();

        for (int i = 0; i < wordLengths.size(); i++) {
            bf.write("num of words with " + (i + 1) + " letter(s): " + wordLengths.get(i));
            bf.newLine();
            if (i == wordLengths.size() - 1) {
                bf.newLine();
                bf.write("longest:");
                bf.newLine();
                for (int j = 0; j < matches.size(); j++) {
                    for (int k = 0; k < matches.get(j).size(); k++) {
                        String w = matches.get(j).get(k);
                        if (w.length() == wordLengths.size()) {
                            bf.write("shift: " + (j + 1) + " " + Cipher.decrypt(w, j + 1) + " -> " + w);
                            bf.newLine();
                        }
                    }
                }
            }
        }

        bf.close();

        System.out.println("complete!");
        System.out.println();
        System.out.println("top & bottom of results.txt (contains \"all\" english words that encrypt/decrypt to other english words)");
        System.out.println("usable.txt is words/sentences I've collected that seem to be the best out of the data");
    }
}
