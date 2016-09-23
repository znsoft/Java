import java.util.Random;

public class hometask16 {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 1; i <=25; i++) {
            int a = random.nextInt(50);
            int b = random.nextInt(50);
            if (a > b) {
                System.out.println(a + ">" + b);
            }
            else if (a < b) {
                System.out.println(a + "<" + b);
            }
            else {
                System.out.println(a + "=" + b);
            }
        }
    }
}
