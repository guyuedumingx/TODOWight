package service.properties;

import model.LabelType;

import java.util.List;


public interface PropertiesService {

    void addLabelType(LabelType label) ;

    List<LabelType> readLabelType();

}
