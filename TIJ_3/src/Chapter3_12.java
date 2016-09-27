/**
 * Created by va.tkalin on 21.09.2016.
 */
public class Chapter3_12 {

        public static void main(String[] args) {

            int n = 0xFFFFFFFF;
            n <<= 1;
            int n1 = n;
            int i = 0;
            while (n!=0){
                n >>>= 1;
                i++;
            }
            System.out.println("i = " + i);
            for (int j = 0; j < i; j ++){

                System.out.println("j = " + j + "\tn = " + Integer.toBinaryString(n1));
                n1 >>>= 1;
            }

        }
    }
