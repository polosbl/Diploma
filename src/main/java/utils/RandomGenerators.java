package utils;

import java.util.HashSet;
import java.util.Set;

public class RandomGenerators {

    // class variable
    final static String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
    final static String codeLexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    final static java.util.Random rand = new java.util.Random();

    // consider using a Map<String,Boolean> to say whether the identifier is being used or not
    final static Set<String> identifiers = new HashSet<>();

    public static String randomId() {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }

    public static String randomCode() {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(codeLexicon.charAt(rand.nextInt(codeLexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }
}
