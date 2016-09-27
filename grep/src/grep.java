

import java.util.Scanner;

public class grep
{
    // точка входа в программу
    public static void main(String[] args)
    {
//проверяем кол-во аргументов
        if (args.length == 0)
        {
            System.err.print("Invalid parametrs for program. ");
            System.err.println("Program must have one parametr.");
            return;
        }
        Scanner in = new Scanner(System.in);
//начинаем разбор по строчкам
        while (in.hasNext())
        {
            String str = in.nextLine();
            Scanner s = new Scanner(str);
            for (String iterStr: args)
            {
                if ((str.toLowerCase().indexOf(iterStr.toLowerCase()) != -1) || (s.findInLine(iterStr) != null))
                {
                    System.out.println(str);
                    break;
                }
            }
            s.close();
        }
        in.close();
    }
}