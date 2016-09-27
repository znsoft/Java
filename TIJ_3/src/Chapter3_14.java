public class Chapter3_14 {

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "123";
        MakeSmth(s1, s2);
    }

    public static void MakeSmth(String s1, String s2){
        System.out.println(s1 == s2);
        System.out.println(s1 != s2);
        System.out.println(s1.equals(s2));
        System.out.println(!s1.equals(s2));

    }


}
