package ua.donskykh.mybookstore.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.donskykh.mybookstore.domain.Author;
import ua.donskykh.mybookstore.domain.Book;
import ua.donskykh.mybookstore.repo.AuthorRepository;
import ua.donskykh.mybookstore.repo.BookRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Qualifier("bookRepository")
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public Book save(Book book) {
        return bookRepository.saveAndFlush(book);
    }

    @Override
    @Transactional
    public List<Book> findAllBooks() {
        try {
            List<Book> books = new ArrayList<>();
            List<Book> booksCopy = new ArrayList<>();
            bookRepository.findAll().forEach(books::add);
            booksCopy.addAll(books);
            for (Book p : books) {
                if (!p.isActive()) {
                    booksCopy.remove(p);
                }
            }
            return booksCopy;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public Book findBookById(Long id) {
        Book book = bookRepository.findById(id).get();
        if (!book.isActive())
            book = null;
        return book;
    }

    @Override
    @Transactional
    public String deleteBook(Long id) {
        Book book = bookRepository.findById(id).get();
        if(!book.isActive()) {
            System.out.println("Book not found!");
        }
        book.setActive(false);
        bookRepository.saveAndFlush(book);
        return "Delete successfully";
    }

    @Override
    @Transactional
    public String updateBook(Book book) {
        bookRepository.saveAndFlush(book);
        return "Book update sucessfully";
    }

    @Override
    @Transactional
    public List<Book> findBookByCategoryId(Long categoryId) {
        List<Book> books = new ArrayList<>();
        List<Book> booksCopy = new ArrayList<>();
        bookRepository.findBooksByCategoryId(categoryId).forEach(books::add);
        booksCopy.addAll(books);
        for (Book b : books) {
            if (!b.isActive()) {
                booksCopy.remove(b);
            }
        }
        return booksCopy;
    }

    @Override
    @Transactional
    public List<Book> findBookByAuthorName(String authorName) {
        List<Book> books = new ArrayList<>();
        List<Book> booksCopy = new ArrayList<>();
        bookRepository.findBooksByAuthorName(authorName).forEach(books::add);
        booksCopy.addAll(books);
        for (Book b : books) {
            if (!b.isActive()) {
                booksCopy.remove(b);
            }
        }
        return booksCopy;
    }

    @Override
    @Transactional
    public List<Book> findAllBooksForAdmin() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    @Override
    @Transactional
    public Book findBookByIdForAdmin(long id) {
        Book book = bookRepository.findById(id).get();
        return book;
    }

}
