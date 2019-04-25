package ua.donskykh.mybookstore.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.donskykh.mybookstore.domain.BooksInCart;
import ua.donskykh.mybookstore.domain.ShoppingCart;
import ua.donskykh.mybookstore.repo.BooksInCartRepository;

import java.util.List;

@Service("booksInCartService")
public class BooksInCartServiceImpl implements BooksInCartService {


    private final ShoppingCartService shoppingCartService;
    private final BooksInCartRepository booksInCartRepository;

    public BooksInCartServiceImpl(ShoppingCartService shoppingCartService, BooksInCartRepository booksInCartRepository) {
        this.shoppingCartService = shoppingCartService;
        this.booksInCartRepository = booksInCartRepository;
    }

    @Override
    @Transactional
    public BooksInCart findBooksInCartById(long id) {
        return booksInCartRepository.findById(id).get();
    }

    @Override
    @Transactional
    public boolean saveBooksInCart(BooksInCart booksInCart){
        booksInCartRepository.saveAndFlush(booksInCart);
        return true;
    }

    @Override
    @Transactional
    public boolean updateBooksInCart(BooksInCart booksInCart) {
        booksInCartRepository.saveAndFlush(booksInCart);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteBooksInCart(BooksInCart booksInCart) {
        booksInCartRepository.delete(booksInCart);
        return false;
    }

    @Override
    @Transactional
    public List<BooksInCart> findAllBooksInCart(long cartId) {
        return booksInCartRepository.findAll();
    }

    @Override
    @Transactional
    public List<BooksInCart> findBooksInCarts() {
        ShoppingCart shoppingCart = shoppingCartService.findShoppingCart();
        return booksInCartRepository.findBooksInCartByCartId(shoppingCart.getId());
    }

    @Override
    @Transactional
    public BooksInCart findBooksInCartByCartIdAndBookId(long cartId, long id) {
        return booksInCartRepository.findBooksInCartByCartIdAndBookId(cartId, id);
    }

}