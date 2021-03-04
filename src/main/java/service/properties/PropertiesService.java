package service.properties;

import model.label.LabelType;

import java.util.List;


/**
 * 配置信息持久化接口
 *
 * @author yohoyes
 */
public interface PropertiesService {

    void addLabelType(LabelType label) ;

    List<LabelType> readLabelType();

    void setPath(String url);

    String getPath();

}
