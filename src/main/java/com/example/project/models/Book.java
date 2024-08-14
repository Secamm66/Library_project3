package com.example.project.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @NotEmpty(message = "Укажите название книги")
    @Size(min = 2, max = 100, message = "Название книги может содержать от 2 до 100 символов")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Укажите автора книги")
    @Size(min = 2, max = 100, message = "Имя автора может содержать от 2 до 100 символов")
    @Column(name = "author")
    private String author;

    @NotNull(message = "Укажите год издательства книги")
    @Positive(message = "Значение должно быть больше 0")
    @Column(name = "year")
    private Integer year;

    @Column(name = "issued_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issuedAt;

    @Transient
    private boolean isOverdue;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person owner;

    public Book(String title, String author, Integer year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {

    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getYear() {
        return year;
    }

    public Person getOwner() {
        return owner;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public boolean isOverdue() {
        return isOverdue;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public void setOverdue(boolean overdue) {
        this.isOverdue = overdue;
    }
}