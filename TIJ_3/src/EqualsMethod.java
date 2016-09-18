import static net.Print.print;

class Dog{
    String name, message;
}

public class EqualsMethod {

    public static void main(String[] args) {
        Dog dog_1 = new Dog();
        Dog dog_2 = new Dog();

        dog_1.name = "Spot";
        dog_2.name = "Scruffy";
        dog_1.message = "wuf-wuf";
        dog_2.message = "meow";

        print("Dog1: "+dog_1.name + " " +dog_1.message);
        print("Dog2: "+dog_2.name + " " +dog_2.message);

        Dog dog_3 = new Dog();
        dog_3 = dog_1;
        print(dog_1 == dog_3);
        print(dog_1.equals(dog_3));
    }

}
