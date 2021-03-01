package service.history;

import model.Arrangement;

import java.util.List;

public interface ArrangementService {

    void save(List<Arrangement> arrangements);

    List<Arrangement> read();
}
