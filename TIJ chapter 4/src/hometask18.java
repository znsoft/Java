public class hometask18 {

    public static void main(String[] args) {

        for (int i = 1; i <= 1000; i++) {
            boolean simple = true;
            for (int j = 2; j < i/2 +1; j++) {
                if (i%j == 0) {
                    simple = false;
                    break;
                }
            }
            if (simple) {
                System.out.println(i + " - simple");
            }
        }
    }
}
