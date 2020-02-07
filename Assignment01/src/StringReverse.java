public class StringReverse {
    public static void main(String[] args) {
        System.out.println("Testing dingus: " + oddEvenStrings("dingus"));
        System.out.println("Testing 21: " + oddEvenStrings("21"));
        System.out.println("Testing \"\": " + oddEvenStrings(""));
        System.out.println("Testing llllllli: " + oddEvenStrings("llllllli"));
        System.out.println("Testing 123456789: " + oddEvenStrings("123456789"));
        System.out.println("Testing 0123456789: " + oddEvenStrings("0123456789"));
        System.out.println("Testing \"Hello World \": " + oddEvenStrings("Hello World "));
        System.out.println("Testing \"He ll oW or ld \": " + oddEvenStrings("He ll oW or ld "));
    }

    public static String reverseString(String input) {
        char[] inbetween = input.toCharArray();
        int count = inbetween.length;
        for (int i = count >> 1, j = count - i; --i >= 0; ++j) {
            char c = inbetween[i];
            inbetween[i] = inbetween[j];
            inbetween[j] = c;
        }
        String output = new String(inbetween);
        //StringBuilder inbetween = new StringBuilder(input);
        //inbetween.reverse();
        //output = inbetween.toString();
        return output;
    }

    public static String oddEvenStrings(String input) {
        char[] odds = new char[input.length() / 2];
        char[] evens;
        if (input.length() % 2 == 1) {
            evens = new char[(input.length() + 1) / 2];
        } else {
            evens = new char[input.length() / 2];
        }
        char[] source = input.toCharArray();
        for (int i = 0, j = 0, k = 0; i < source.length; ++i) {
            if (i % 2 == 0) {
                evens[j] = source[i];
                ++j;
            }
            if (i % 2 == 1) {
                odds[k] = source[i];
                ++k;
            }
        }
        String evenReverse = reverseString(new String(evens));
        String oddReverse = reverseString(new String(odds));
        return evenReverse + oddReverse;
    }

}