package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import model.Arrangement;
import model.label.LabelType;
import model.Setting;
import util.AttrUtil;

import java.time.LocalDate;
import java.util.List;

/**
 * 详情页面的控制器
 * @author yohoyes
 */
public class DetailController {
    @FXML
    private Button editBut;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private DatePicker deadlinePicker;

    @FXML
    private TextArea content;

    private Arrangement arrangement = null;
    private Label itemLabel = null;

    /**
     * 自定义的出始化方法
     *
     * @param itemLabel
     * @param arrangement
     */
    public void init(Label itemLabel, Arrangement arrangement) {
        this.itemLabel = itemLabel;
        this.arrangement = arrangement;
        //把界面中的textArea中的文本设置成代办的内容
        content.textProperty().bindBidirectional(arrangement.getValueProperty());
        //获取标签的种类
        List<LabelType> labelTypes = Setting.getSetting().getLabelTypes();
        addChangeListenerForDatePicker();
        fillChoiceBox(labelTypes);
    }

    @FXML
    private void addChangeListenerForDatePicker() {
        deadlinePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                arrangement.setDeadline(newValue.atStartOfDay().getNano());
            }
        });
    }

    /**
     * 把所有的标签种类填入选择框中
     */
    @FXML
    private void fillChoiceBox (List<LabelType> labelTypes) {
        for(LabelType label : labelTypes) {
            choiceBox.getItems().add(label.getName());
        }
        //把选择框的初始值设置成当前具体代办的标签名
        choiceBox.getSelectionModel().select(arrangement.getLabel().getName());
        choiceBox.getSelectionModel().selectedItemProperty().addListener(new
             ChangeListener<String>() {
                 @Override
                 public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                     LabelType label = getLabelsByName(labelTypes, newValue);
                     arrangement.setLabel(label);
                     itemLabel.setTooltip(AttrUtil.getTooltipbyLabel(label));
                     itemLabel.textProperty().bind(arrangement.getLabel().getNameProperty());

                 }
             }
        );
    }

    private LabelType getLabelsByName(List<LabelType> labelTypes, String name) {
        for(LabelType label : labelTypes) {
            if(name.equals(label.getName())) {
                return label;
            }
        }
        return null;
    }

    /**
     * 详情页面右上角的笔的按键绑定
     * 用于切换中间显示界面的可编辑状态
     */
    @FXML
    public void addEditButtonAction() {
        editBut.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                if(content.isEditable()) {
                    content.setEditable(false);
                }else {
                    content.setEditable(true);
                    content.positionCaret(content.getText().length());
                }
            }
        });
    }

    @FXML
    public void initialize() {
        //设置textArea自动换行
        content.setWrapText(true);
        addEditButtonAction();
    }
}
