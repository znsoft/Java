package chapter2;

/**
 * Создать класс с полями int & char которые не инициализируются в программе.
 * Вывести их на экран чтобы убедиться что Джава выполняет инициализацию по умолчанию
 */
public class dz11 {

    int i;
    char c;

    public static void main(String[] args) {
        dz11 classdz11 = new dz11();
        System.out.println("i = " + classdz11.i + "; c = " + classdz11.c);
    }

}
