package org.example.DAO;

import org.example.DAO.IDao;
import org.example.entity.Patient;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBDao implements IDao {
    // DB_URL составляется так: тип драйвера:имя базы данных://адрес хоста:порт/имя базы
    // адрес хоста:порт - это сокет, который принимает подключение к базе данных
    private final String DB_URL = "jdbc:postgresql://localhost:5432/pats";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "postgres";
    private final String DB_DRIVER = "org.postgresql.Driver";
    private final Connection connection;

    public DBDao() {
        try {
            Class.forName(DB_DRIVER);
//            this.connection = DriverManager.getConnection(DB_URL);
            this.connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Не найдена БД");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Нет драйвера JDBC");
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Patient> getAll() {
        return queryByString("SELECT * FROM Patients");
    }

    @Override
    public List<Patient> queryByString(String query) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);  //список того, что по нашему запросу выдаст база

            List<Patient> patients = new ArrayList<>();

            while (result.next()) {
                patients.add(getFromResultEntry(result));
            }
            return patients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Patient getFromResultEntry(ResultSet result) throws SQLException {
        return new Patient(
                result.getString("fio"),
                getDateFromString(result.getString("birth_date")),
                Integer.parseInt(result.getString("sex")),
                Integer.parseInt(result.getString("num")),
                result.getString("smo"),
                result.getString("snils"),
                result.getString("policy"),
                Integer.parseInt(result.getString("fin_source"))
        );
   }

    private LocalDate getDateFromString (String date) {
        String[] birthDate = date.split("-");
        return LocalDate.of(Integer.parseInt(birthDate[0]), Integer.parseInt(birthDate[1]), Integer.parseInt(birthDate[2]));
    }

    @Override
    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





}
