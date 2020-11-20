import by.epam.mtlcwtchr.ecafe.controller.security.RequestScriptingFilter;
import org.junit.Assert;
import org.junit.Test;

public class RequestScriptingFilterTest {

    private static final String[] ATTACK_INPUT = {
            "<script>script</script>",
            "text<script>script</script>",
            "<script>script</script>text",
            "text<script>script</script>text"};
    private static final String[] NOT_ATTACK_INPUT = {
            "text",
            "<text>",
            "<text> text",
            "text <text>"};

    @Test
    public void filterTest_Script(){
        final int requiredAttacksCount = 4;
        int realAttacksCount = 0;
        for (String input : ATTACK_INPUT) {
            if(input.matches(RequestScriptingFilter.SCRIPT_REGULAR)) {
                ++realAttacksCount;
            }
        }
        Assert.assertEquals(requiredAttacksCount, realAttacksCount);
    }

    @Test
    public void filterTest_No_Script(){
        final int requiredAttacksCount = 0;
        int realAttacksCount = 0;
        for (String input : NOT_ATTACK_INPUT) {
            if(input.matches(RequestScriptingFilter.SCRIPT_REGULAR)) {
                ++realAttacksCount;
            }
        }
        Assert.assertEquals(requiredAttacksCount, realAttacksCount);
    }

}
