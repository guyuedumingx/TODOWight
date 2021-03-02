package service.history;

import model.Arrangement;

import java.util.List;

/**
 * 事务存储接口
 *
 * @author yohoyes
 */
public interface ArrangementService {

    void save(List<Arrangement> arrangements);

    List<Arrangement> read();
}
