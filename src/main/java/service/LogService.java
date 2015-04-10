package main.java.service;

import main.java.DAO.DAOImpl.LogDAOImpl;
import main.java.DAO.LogDAO;
import main.java.entity.Log;
import main.java.entity.Task;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artsiom Tratsiuk on 10.04.2015.
 */
public class LogService {
    private LogDAO logDAO = new LogDAOImpl();

    void addLog(Log log) throws SQLException{
        logDAO.addLog(log);
    }

    void updateLog(Log log) throws SQLException{
        logDAO.updateLog(log);
    }

    void deleteLog(Log log) throws SQLException{
        logDAO.deleteLog(log);
    }

    List<Log> getLogByTask(Task task) throws SQLException{
        return logDAO.getLogByTask(task);
    }
}
