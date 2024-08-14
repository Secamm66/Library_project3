package com.example.project.services;

import com.example.project.models.Book;
import com.example.project.models.Person;
import com.example.project.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(boolean sortByYear) {
        if (sortByYear)
            return booksRepository.findAll(Sort.by("year"));
        else
            return booksRepository.findAll();
    }

    public Book findById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public List<Book> findAllWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
        if (sortByYear)
            return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        else
            return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public List<Book> findByTitleStartingWith(String title) {
        return booksRepository.findByTitleStartingWith(title);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(int id, Book updatedBook) {
        updatedBook.setBookId(id);
        updatedBook.setOwner(findById(id).getOwner());
        updatedBook.setIssuedAt(findById(id).getIssuedAt());
        booksRepository.save(updatedBook);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public Person getBookOwner(int id) {
        return findById(id).getOwner();
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void releaseBook(int id) {
        findById(id).setOwner(null);
        findById(id).setIssuedAt(null);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void assignBook(int id, Person selectedPerson) {
        findById(id).setOwner(selectedPerson);
        findById(id).setIssuedAt(new Date());
    }
}
