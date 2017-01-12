package main.java;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by gabe.barcelos on 1/11/17.
 */
public class Concordance {
    private static int lineIndex = 1;
    private static boolean incrementLineIndex = false;

    public static void main(String[] args) {

        if (args.length < 0) {
            System.err.println("Please supply document path as an argument");
            System.exit(0);
        }

        TreeMap<String, Word> documentAttributes = new TreeMap<String, Word>();


        handleDocument(args[0], documentAttributes);
        displayMetadata(documentAttributes);
    }

    private static class Word {
        int count;
        ArrayList lineIndeces = new ArrayList();


        public Word(int count, int lineIndex) {
            this.count = count;
            lineIndeces.add(lineIndex);
        }

        public void incrementCount() {
            count++;
        }

        public int getWordCount() {
            return count;
        }

        public void augmentIndeces(int lineIndex) {
            lineIndeces.add(lineIndex);
        }

        public List<Integer> getLineIndeces() {
            return lineIndeces;
        }

    }

    private static void handleDocument(String document, TreeMap<String, Word> documentAttributes) {
        Scanner scanner;
        String word;

        try {
            scanner = new Scanner(new FileReader(document));
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return;
        }

        while (scanner.hasNext()) {
            word = scanner.next();
            offerWord(documentAttributes, word);
        }
    }


    private static void offerWord(TreeMap<String, Word> documentAttributes, String word) {
        String cononicalWord = getConnonicalWord(word);
        Word wordAttributes = documentAttributes.get(cononicalWord);
        if (wordAttributes == null){
            documentAttributes.put(cononicalWord, new Word(1 , lineIndex));
        } else {
            wordAttributes.incrementCount();
            wordAttributes.augmentIndeces(lineIndex);
            documentAttributes.put(cononicalWord, wordAttributes);
        }

        if (incrementLineIndex) {
            incrementLineIndex = false;
            lineIndex++;
        }
    }

    private static String getConnonicalWord(String word) {
        word = word.toLowerCase();
        if (word.matches("([a-zA-Z]+\\.+[(a-z)]+\\.)")) {
            return word;
        }
        if (word.matches("[a-zA-Z]+[.!?]+") ) {
            incrementLineIndex = true;
        }

        word = word.replaceAll("[.!?:,-]", "");
        return word;
    }

    private static void displayMetadata(TreeMap<String, Word> documentAttributes) {
        System.out.println("##################################");

        for (String word : documentAttributes.keySet()) {
            System.out.printf("%s \t{%d:%s}\n", word,  documentAttributes.get(word).getWordCount(), documentAttributes.get(word).getLineIndeces());
        }

        System.out.println("##################################");
    }

}
