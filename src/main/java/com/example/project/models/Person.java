package com.example.project.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int personId;

    @NotEmpty(message = "Укажите ФИО читателя")
    @Size(min = 8, max = 100, message = "ФИО может содержать от 8 до 100 символов")
    @Pattern(regexp = "^[А-ЯЁ][а-яё]+\\s[А-ЯЁ][а-яё]+\\s[А-ЯЁ][а-яё]+$",
            message = "ФИО необходимо указывать в формате: Иванов Иван Иванович")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Укажите год рождения")
    @Min(value = 1900, message = "Некорректно указан год рождения")
    @Max(value = 2024, message = "Некорректно указан год рождения")
    @Column(name = "year_of_birth")
    private Integer yearOfBirth;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    @Valid
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private PersonAccount personAccount;

    public Person(String name, Integer yearOfBirth) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public Person() {
    }

    public PersonAccount getPersonAccount() {
        return personAccount;
    }

    public int getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setPersonAccount(PersonAccount personAccount) {
        this.personAccount = personAccount;
        personAccount.setPerson(this);
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", books=" + books +
                '}';
    }
}
