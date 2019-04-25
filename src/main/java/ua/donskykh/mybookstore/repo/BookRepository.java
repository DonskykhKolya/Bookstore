package ua.donskykh.mybookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.donskykh.mybookstore.domain.Author;
import ua.donskykh.mybookstore.domain.Book;

import java.util.List;

@Repository("bookRepository")
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBooksByCategoryId(Long categoryId);
    List<Book> findBooksByAuthorName (String authorName);
}
