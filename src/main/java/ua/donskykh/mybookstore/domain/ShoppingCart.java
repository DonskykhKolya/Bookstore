package ua.donskykh.mybookstore.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne
    private User user;

    @Column(name = "total")
    private double total;

    @Column(name = "books_in_cart")
    private int booksInCart;

    public ShoppingCart(User user, double total, int booksInCart) {
        this.user = user;
        this.total = total;
        this.booksInCart = booksInCart;
    }

    public ShoppingCart() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getBooksInCart() {
        return booksInCart;
    }

    public void setBooksInCart(int booksInCart) {
        this.booksInCart = booksInCart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cart [id=" + id + ", total=" + total + ", booksInCart=" + booksInCart + "]";
    }
}
