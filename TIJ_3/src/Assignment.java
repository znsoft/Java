import static net.Print.*;
// совмещение имен 1
public class Assignment {

    public static void main(String[] args){
        Tank t1 = new Tank();
        Tank t2 = new Tank();
        t1.ammo_capacity = 1.1F;
        t2.ammo_capacity = 2.2F;
        print("До присваивания:");
        print("Танк 1: "+ t1.ammo_capacity+ "; Танк 2: "+ t2.ammo_capacity);
        t1 = t2;
        print("После присваивания:");
        print("Танк 1: "+ t1.ammo_capacity+ "; Танк 2: "+ t2.ammo_capacity);
        t1.ammo_capacity = 3.3F;
        print("После изменения поля одного объекта:");
        print("Танк 1: "+ t1.ammo_capacity+ "; Танк 2: "+ t2.ammo_capacity);
    }
}

class Tank{
    float ammo_capacity;
}