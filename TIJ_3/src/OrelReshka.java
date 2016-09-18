import java.util.Random;

import static net.Print.print;

/**
 * Created by va.tkalin on 16.09.2016.
 */
public class OrelReshka {

    public static void main(String[] args){
        Random rand = new Random();
        boolean n = rand.nextBoolean();
        // 0 - орел, 1 - решка
        if (n){
            print("Решка");
        }
        else{
            print("Орел");
        }
    }
}
