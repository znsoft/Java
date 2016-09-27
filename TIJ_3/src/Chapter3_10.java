import static net.Print.print;

/**
 * Created by va.tkalin on 21.09.2016.
 */
public class Chapter3_10 {

    public static void main(String[] args){

        int a = 0xAAAAAAAA;
        int b = 0x55555555;
        System.out.println("a = " + Integer.toBinaryString(a));
        System.out.println("b = " + Integer.toBinaryString(b));
        System.out.println("a & b = " + Integer.toBinaryString(a & b));
        System.out.println("a | b = " + Integer.toBinaryString(a | b));
        System.out.println("a ^ b = " + Integer.toBinaryString(a ^ b));
        System.out.println("~a = " + Integer.toBinaryString(~a));
        System.out.println("~b = " + Integer.toBinaryString(~b));
    }

}
