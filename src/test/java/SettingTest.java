import model.LabelType;
import model.Setting;
import org.junit.Test;

import java.util.List;

public class SettingTest {
    @Test
    public void ReadXmlTest() {
        Setting setting = Setting.getSetting();
        setting.addLabel(new LabelType("TODO"));
        setting.addLabel(new LabelType("DONE"));
    }
}
