package util;

import javafx.scene.control.Tooltip;
import model.label.LabelType;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * label 属性处理
 */
public class AttrUtil {
    private static LabelType label = null;
    private static String rule = null;
    private static String attr = null;

    public static String build() {
        if("Time".equalsIgnoreCase(rule)) {
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            attr = RegexUtil.buildAttr(attr, rule, time);
        }else if("Name".equalsIgnoreCase(rule)) {
            attr = RegexUtil.buildAttr(attr, rule, label.getName());
        }
        return attr;
    }

    public static Tooltip getTooltipbyLabel(LabelType labelType) {
        label = labelType;
        attr = label.getAttr();
        rule = RegexUtil.getRule(label.getAttr());

        //专门为DONE标签优化
        if(!"".equals(rule)) {
            attr = build();
        }
        Tooltip tooltip = new Tooltip(attr);
        tooltip.setStyle("-fx-font-size: 15px; -fx-background-color: #464547; -fx-background-radius: 20px; -fx-border-size: 0px");
        return tooltip;
    }
}
