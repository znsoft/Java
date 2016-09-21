/**
 * Created by va.tkalin on 21.09.2016.
 */
public class CharToBinaryString {

    public static void main(String[] args) {
        // bad
        char x = 'a';
        System.out.println("x = " + x + "; binary: " + Integer.toBinaryString(x));
        x = 'z';
        System.out.println("x = " + x + "; binary: " + Integer.toBinaryString(x));
        //ok
        printCharInBinary('a');
        printCharInBinary('z');


    }

    static void printCharInBinary(char x) {
        System.out.println("x = " + x + "; binary: " + Integer.toBinaryString(x));
    }

}
