package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用来识别用户自定义的label 的 attr
 *
 * @author yohoyes
 */
public class RegexUtil {
    private static String regex = "(?<=\\@\\{)(.+?)(?=\\})";
    private static Pattern pattern = Pattern.compile(regex);

    public static String getRule(String content) {
        Matcher matcher = pattern.matcher(content);
        boolean find = matcher.find();
        if(find) {
            return matcher.group();
        }
        return "";
    }

    public static String buildAttr(String content, String rule, String value) {
        String replaceRule = "@\\{" + rule + "}";
        return content.replaceAll(replaceRule, value);
    }
}
