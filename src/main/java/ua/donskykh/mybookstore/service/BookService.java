package ua.donskykh.mybookstore.service;

import org.springframework.transaction.annotation.Transactional;
import ua.donskykh.mybookstore.domain.Author;
import ua.donskykh.mybookstore.domain.Book;

import java.util.List;

public interface BookService {

    Book save(Book book);

    List<Book> findAllBooks();

    List<Book> findAllBooksForAdmin();

    Book findBookById(Long id);

    String deleteBook(Long id);

    String updateBook(Book book);

    List<Book> findBookByCategoryId(Long categoryId);

    List<Book> findBookByAuthorName(String authorName);

    Book findBookByIdForAdmin(long id);
}
