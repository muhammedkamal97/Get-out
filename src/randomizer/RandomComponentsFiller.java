package randomizer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class RandomComponentsFiller {

    private Random random = new Random();


    public Object generateRandomArray(Class[] referenceArray, int maxNumberAllowed) {

        int enteredArraySize = referenceArray.length;
        ArrayList<Object> randomArray = new ArrayList<>();
        for (int i = 0; i < maxNumberAllowed; i++) {
            try {
                randomArray.add(referenceArray[generateRandomNumber(enteredArraySize)].getConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return randomArray;
    }


    private int generateRandomNumber(int limit) {
        return random.nextInt(limit);
    }

}
