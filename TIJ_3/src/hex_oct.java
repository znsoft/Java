/**
 * Created by va.tkalin on 16.09.2016.
 */
public class hex_oct {
        public static void main(String[] args) {
            long hex = 0xFFFL;
            long oct = 0777L;
            System.out.println("hex: " + Long.toBinaryString(hex));
            System.out.println("oct: " + Long.toBinaryString(oct));
        }
}
