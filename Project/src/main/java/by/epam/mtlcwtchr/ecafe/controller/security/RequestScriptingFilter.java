package by.epam.mtlcwtchr.ecafe.controller.security;

import javax.servlet.ServletRequest;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author St.Anislav
 * Class for filtering request with boolean as a result
 */
public final class RequestScriptingFilter {

    private RequestScriptingFilter(){

    }

    public static final String SCRIPT_REGULAR = ".*<.*>.*</.*>.*";

    /**
     * @param servletRequest to be filtered
     * @return true if there's no attack, false otherwise
     */
    public static boolean filter(ServletRequest servletRequest){
        AtomicBoolean noAttack = new AtomicBoolean(true);
        servletRequest.getParameterMap().forEach(((key, values) -> {
            if (Arrays.stream(values).anyMatch(value -> value.matches(SCRIPT_REGULAR))) {
                noAttack.set(false);
            }
        }));
        servletRequest.getAttributeNames().asIterator().forEachRemaining(key -> {
            if(servletRequest.getAttribute(key).toString().matches(SCRIPT_REGULAR)) {
                noAttack.set(false);
            }
        });
        return noAttack.get();
    }

}
