package org.example.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

    public class Patient {
        private final String fio;
        private final LocalDate birthDate;
        private final int sex;
        private final Integer num; // номер мед карты
        private final String smo; // название страховой компании
        private final String snils;
        private final String policy;
        private final Integer finSource;
        private final List<Integer> expenses = new ArrayList<>();

        // медоты get
        public String getFio() {
            return fio;
        }

        public LocalDate getBirthDate() {
            return birthDate;
        }

        public List<Integer> getExpenses() {
            return expenses;
        }

        public int getSex() {
            return sex;
        }

        public Integer getNum() {
            return num;
        }

        public String getSnils() {
            return snils;
        }

        public String getPolicy() {
            return policy;
        }

        public int getFinSource() {
            return finSource;
        }
        public String getSmo() {
            return smo;
        }



    // конструктор
    public Patient(String fio, LocalDate birthDate, Integer sex, Integer num,
                   String smo, String snils, String policy, Integer finSource) {
        this.fio = fio;
        this.birthDate = birthDate;
        this.sex = sex;
        this.num = num;
        this.smo = smo;
        this.snils = snils;
        this.policy = policy;
        this.finSource = finSource;
    }


        @Override
        public String toString() {
            return "Patient{" +
                    " num=" + num +
                    ", snils='" + snils + '\'' +
                    ", sex='" + sex + '\'' +
                    ", fio='" + fio + '\'' +
                    ", birthDate=" + birthDate + '\'' +
                    ", policy='" + policy + '\'' +
                    ", finSource='" + finSource + '\'' +
                    "}";
        }

    }

