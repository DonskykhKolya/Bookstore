package ua.donskykh.mybookstore.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.donskykh.mybookstore.domain.ShoppingCart;
import ua.donskykh.mybookstore.domain.UserModel;
import ua.donskykh.mybookstore.repo.ShoppingCartRepository;

import javax.servlet.http.HttpSession;

@Service("shoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Qualifier("cartRepository")
    private final ShoppingCartRepository cartRepository;

    private final HttpSession httpSession;

    public ShoppingCartServiceImpl(ShoppingCartRepository cartRepository, HttpSession httpSession) {
        this.cartRepository = cartRepository;
        this.httpSession = httpSession;
    }

    @Override
    @Transactional
    public boolean saveShoppingCart(ShoppingCart shoppingCart) {
        cartRepository.saveAndFlush(shoppingCart);
        return true;
    }

    @Override
    @Transactional
    public boolean updateShoppingCart(ShoppingCart shoppingCart) {
        cartRepository.saveAndFlush(shoppingCart);
        return true;
    }

    @Override
    @Transactional
    public ShoppingCart findShoppingCart() {
        return ((UserModel) httpSession.getAttribute("userModel")).getShoppingCart();
    }

}
