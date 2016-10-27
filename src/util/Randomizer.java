package util;

import easylantransfer.Core.ConstantConfig;
import java.util.Random;

public class Randomizer {
    public static String nextUserTag() {
        StringBuilder sb = new StringBuilder();
        Random r = new Random(System.currentTimeMillis());
        
        int numCharacters = r.nextInt(ConstantConfig.USER_TAG_NUMBER_LENGTH);
        for (int i = 0; i < numCharacters; i++) {
            sb.append((char) (65 + r.nextInt(26)));
        }
        
        
        int numNumbers = ConstantConfig.USER_TAG_NUMBER_LENGTH - numCharacters;
        for (int i = 0; i < numNumbers; i++) {
            sb.append(r.nextInt(9));
        }
        
        return sb.toString();
    }
}
