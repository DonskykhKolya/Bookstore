package ua.donskykh.mybookstore.service;

import ua.donskykh.mybookstore.domain.BooksInCart;

import java.util.List;

public interface BooksInCartService {

    public BooksInCart findBooksInCartById(long id);
    public boolean saveBooksInCart(BooksInCart booksInCart);
    public boolean updateBooksInCart(BooksInCart booksInCart);
    public boolean deleteBooksInCart(BooksInCart booksInCart);
    public List<BooksInCart> findAllBooksInCart(long cartId);

    public List<BooksInCart> findBooksInCarts();

    BooksInCart findBooksInCartByCartIdAndBookId(long cartId, long id);
}
