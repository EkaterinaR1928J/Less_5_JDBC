package org.example.DAO;

import org.example.entity.Patient;

import java.util.List;

public interface IDao {
    // выгрузка всей базы данных
    List<Patient> getAll();
    // запрос по строке
    List<Patient> queryByString(String query);
    // закрытие соединения с базой данных
    void closeConnection();

}
