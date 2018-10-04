import java.util.Scanner;
public class Crypto {
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        System.out.println("Input word: ");
        String word = input.nextLine();
        System.out.println("");
        System.out.println("Input shift: ");
        int shift = input.nextInt();
        System.out.println("Input group size: ");
        int groupSize = input.nextInt();
        System.out.println(encryptString(word,shift,groupSize));
    }

    public static String encryptString (String text, int shift, int groupSize){
        text = normalizeText1(text);
        text = obifyText1(text);
        text = ceaserifyText(text, shift);
        text = groupifyText(text, groupSize);
        return  text;
    }

    public static String normalizeText1(String text){
        String normalizedText = text.replace(" ","").replaceAll("\\p{Punct}","").toUpperCase();
        return normalizedText;
    }

    public static String obifyText1(String text){
        String obifiedText = "";
        int len = text.length();
        for (int i = 0; i < len; i++){
            char ch = text.charAt(i);
            if (ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U' || ch == 'Y') {
                obifiedText += "OB" + ch;
            } obifiedText += ch;
        }
        return obifiedText;
    }

    public static String ceaserifyText(String text, int shift) {
        String shiftedAlphabet = shiftAlphabet(shift);
        String alphabet = shiftAlphabet(0);
        String ceaserifiedText = "";

        for (int i = 0; i < text.length(); i++){
            int charPositionInAlphabet;
            charPositionInAlphabet = alphabet.indexOf(text.charAt(i));

            if(charPositionInAlphabet == -1){
                System.out.println("Please do not use numbers!");
                throw new IllegalArgumentException();
            }

            ceaserifiedText += shiftedAlphabet.charAt(charPositionInAlphabet);
        }

        return ceaserifiedText;
    }


    // ==================================================
    public static String shiftAlphabet(int shift) {
        int start = 0;

        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }

        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }

        return result;
    }


    public static String groupifyText(String text, int groupSize){
        text = text.replaceAll("\\s","");
        String groupifiedText = "";

        for (int i = 0; i < text.length();i++){
            if (i == 0 || i % groupSize != 0){
                groupifiedText += text.charAt(i);
            } else if (i % groupSize == 0) {
                groupifiedText += " " + text.charAt(i);
            }
        }

        return groupifiedText;
    }
}