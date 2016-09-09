package chapter2;
import java.util.*;

/**
 * Created by va.tkalin on 06.09.2016.
 */
public class HelloDate
{
    static int i = 5;
    public static void main(String[] args)
    {
        System.out.println("Hello, now:");
        System.out.println(new Date());
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(storage(s));

        HelloDate Increment = new HelloDate();
        System.out.println(i);
        Increment();
        System.out.println(i);

        HelloDate Increment2 = new HelloDate();
        System.out.println(i);

        System.out.println("test static: "+i);
        System.out.println(HelloDate.i);
        System.out.println(Increment.i);
        System.out.println(Increment2.i);

        System.out.println("Список аргументов командрой строки: ");
        for (String arg: args){
            System.out.println(arg);
        }

    }

    static int storage(String s) {
    return s.length();
    }


        static void Increment(){
            i++;
        }


}
