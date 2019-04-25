package ua.donskykh.mybookstore.service;

import ua.donskykh.mybookstore.domain.ShoppingCart;

public interface ShoppingCartService {

    boolean saveShoppingCart(ShoppingCart shoppingCart);

    boolean updateShoppingCart(ShoppingCart shoppingCart);

    ShoppingCart findShoppingCart();
}
