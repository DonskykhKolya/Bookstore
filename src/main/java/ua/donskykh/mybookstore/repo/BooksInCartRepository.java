package ua.donskykh.mybookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.donskykh.mybookstore.domain.BooksInCart;

import java.util.List;

@Repository("booksInCartRepository")
public interface BooksInCartRepository extends JpaRepository<BooksInCart, Long> {

    List<BooksInCart> findBooksInCartByCartId(long cartId);

    BooksInCart findBooksInCartByCartIdAndBookId(long cartId, long id);

}
