package org.example;

import org.example.DAO.DBDao;
import org.example.DAO.IDao;
import org.example.entity.Patient;

import java.util.List;

// Взаимодействие с источниками данных.
//-	Реализовать функционал с урока
//-	Сделать 3-4 выборки на Ваше усмотрение – просто чтобы самим потрогать.
// Пояснения по базе данных указаны в файле Temp_for_DataBase

public class Main {
    public static void main(String[] args) {
        System.out.println("======== РАБОТА С БАЗАМИ ДАННЫХ ========");
        IDao dao = new DBDao();

        System.out.println("\nПример 1.\n" + "Выводим всю информацию по всем пациентам\n");
        List<Patient> all = dao.getAll();
        System.out.println("Всего пациентов из выборки: " + all.stream().count());
        all.forEach(System.out::println);

        System.out.println("\nПример 2.\n" + "Выводим всю информацию по пациентам, у которых пол \"жен\" (в базе жен = 2)\n");
        List<Patient> women = dao.queryByString("SELECT * FROM Patients WHERE SEX = 2");
        System.out.println("Всего пациентов из выборки: " + women.stream().count());
        women.forEach(System.out::println);

        System.out.println("\nПример 3.\n" +
                "Выводим всю информацию по первым 10 пациентам (сортировка по ФИО), у которых страхования компания ВСК и пол - женский\n");
        List<Patient> patientsBySmo = dao.queryByString("SELECT * FROM Patients WHERE smo LIKE 'ВСК' AND sex = 2 ORDER BY fio LIMIT 10;");
        System.out.println("Всего пациентов из выборки: " + patientsBySmo.stream().count());
        patientsBySmo.stream().forEach(patient -> System.out.printf(patient.toString() + ": Страх компания: " + patient.getSmo() + "\n"));

        System.out.println("\nПример 4.\n" +
                "Выводим всю информацию по пациентам (сортировка по дате рождения), у которых год рождения > 2000 и месяц рождения = 6\n");
        List<Patient> patientsByBirthDate = dao.queryByString(
                "SELECT * " +
                        "FROM Patients " +
                        "WHERE EXTRACT (year from birth_date) >= 2000 AND EXTRACT (month from birth_date) = 6 " +
                        "ORDER BY birth_date;");
        System.out.println("Всего пациентов из выборки: " + patientsByBirthDate.stream().count());
        patientsByBirthDate.stream().forEach(patient -> System.out.printf(patient.toString() + ": Страх компания: " + patient.getSmo() + "\n"));

        System.out.println("\nПример 5.\n" +
                "Выводим всю информацию по пациентам (сортировка по ФИО), у которых фамилия начинается с буквы \"К\"\n");
        List<Patient> patientsByFio = dao.queryByString(
                "SELECT * " +
                        "FROM Patients " +
                        "WHERE fio LIKE 'К%'" +
                        "ORDER BY fio");
        System.out.println("Всего пациентов из выборки: " + patientsByFio.stream().count());
        patientsByFio.forEach(System.out::println);

        dao.closeConnection();
    }
}