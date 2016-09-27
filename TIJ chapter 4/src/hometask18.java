public class hometask18 {

    public static void main(String[] args) {

        for (int i = 1; i <= 1000; i++) {
            boolean simple = true;
            for (int j = 2; j < i/2 +1; j++) { // ну не может число поделица нацело на число которое больше половины самого числа, будет 0,Х
                if (i%j == 0) {  // остаток от деления дробный 0, значит поделилось нацело
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
