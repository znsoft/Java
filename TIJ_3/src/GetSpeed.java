// получает скорость по пути и времени

import java.util.Random;

import static net.Print.print;

public class GetSpeed {

    public static void main(String[] args){
        Random rand = new Random(47);
        float time,way;
        way = rand.nextFloat();
        time = rand.nextFloat();
        print("Way: "+way+"; time: "+time);
        print("Speed: "+way/time);
    }

}
