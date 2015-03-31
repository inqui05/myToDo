package main.java.DAO;

import main.java.table.Log;
import main.java.table.Task;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artsiom Tratsiuk on 31.03.2015.
 */

public interface LogDAO {

    void addLog(Log log) throws SQLException;
    void updateLog(Log log) throws SQLException;
    void deleteLog(Log log) throws SQLException;
    List<Log> getLogByTask(Task task) throws SQLException;

}
