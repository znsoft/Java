public class hometask17 {
    static boolean condidtion() {
        boolean result = Math.random() < 0.99;
        System.out.print(result + ", ");
        return true;
    }

    public static void main(String[] args) {
        while (condidtion()) {
            System.out.println("Inside while!");
        }
    }
}
