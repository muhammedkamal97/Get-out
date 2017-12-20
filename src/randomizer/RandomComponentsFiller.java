package randomizer;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class RandomComponentsFiller {

    private Random random = new Random();
    private Logger logger = Logger.getLogger(RandomComponentsFiller.class);


    public Object generateRandomArray(Class[] referenceArray, int maxNumberAllowed) {

        int enteredArraySize = referenceArray.length;
        ArrayList<Object> randomArray = new ArrayList<>();
        for (int i = 0; i < maxNumberAllowed; i++) {
            try {
                randomArray.add(referenceArray[generateRandomNumber(enteredArraySize)].getConstructor().newInstance());
            } catch (Exception e) {
                logger.error("Could not create instance of supplied reference array elements");
            }
        }
        return randomArray;
    }


    private int generateRandomNumber(int limit) {
        return random.nextInt(limit);
    }

}
