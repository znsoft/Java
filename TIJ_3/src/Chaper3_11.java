/**
 * Created by va.tkalin on 21.09.2016.
 */
import java.math.*;

public class Chaper3_11 {

    public static void main(String[] args) {

        int n = 0x80000000;
        System.out.println(Integer.toBinaryString(n));
       // for (int i = 0; i < 32; i++) {
        int i = 0;
        while (n!=0){
            n >>>= 1;
            i++;
        }

        n = 0x80000000;
        for (int j = 0; j < i; j ++){

            System.out.println("i = " + i + "\tn = " + Integer.toBinaryString(n));
            n >>>= 1;
        }

    }
}
