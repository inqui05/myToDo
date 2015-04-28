package main.java.DAO;

import main.java.entity.Log;
import main.java.entity.Task;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artsiom Tratsiuk
 */

public interface LogDAO {

    void addLog(Log log) throws SQLException;
    void updateLog(Log log) throws SQLException;
    void deleteLog(Log log) throws SQLException;
    List<Log> getLogByTask(Task task) throws SQLException;

}
