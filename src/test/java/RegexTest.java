import org.junit.Test;
import util.RegexUtil;

public class RegexTest {

    @Test
    public void matchTest() {
        String content = "Closed at: {Time} at night";
        String rule = RegexUtil.getRule(content);
        String attr = "google";
        String s = RegexUtil.buildAttr(content, rule, attr);
        System.out.println(s);
    }
}
