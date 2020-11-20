package by.epam.mtlcwtchr.ecafe.dao.builder.limiter;

import by.epam.mtlcwtchr.ecafe.verification.annotation.CheckedArguments;

import java.util.HashMap;
import java.util.Map;

public final class LimiterMapGenerator {

    private LimiterMapGenerator() {

    }

    @CheckedArguments
    public static Map<String, Limiter> generateOfSingleType(Limiter limiterType, String... args){
        HashMap<String, Limiter> limiterHashMap = new HashMap<>();
        for (String arg : args) {
            limiterHashMap.put(arg, limiterType);
        }
        return limiterHashMap;
    }

}
