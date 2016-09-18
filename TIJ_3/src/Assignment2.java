// совмещение имен при вызове метода

import static net.Print.print;

class Number{
    float a;
}

public class Assignment2 {

    static void change_a(Number num){
        num.a = 666f;
    }

    public static void main(String[] args){
        Number num = new Number();
        num.a = 555f;
        print("1: "+num.a);
        change_a(num);
        print("2: "+ num.a);
    }

}


