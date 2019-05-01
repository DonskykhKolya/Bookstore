package ua.donskykh.mybookstore.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "books_incart")
public class BooksInCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @OneToOne
    private Book book;

    @Column(name="cart_id")
    private long cartId;

    @Column(name="total")
    private double total;

    @Column(name="book_count")
    private int bookCount;

    @Column(name="buying_price")
    private double buyingPrice;

    @Column(name="is_available")
    private boolean isAvailable;

    public BooksInCart(Book book, long cartId, double total, int bookCount, double buyingPrice, boolean isAvailable) {
        this.book = book;
        this.cartId = cartId;
        this.total = total;
        this.bookCount = bookCount;
        this.buyingPrice = buyingPrice;
        this.isAvailable = isAvailable;
    }

    public BooksInCart() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "CartLine [id=" + id + ", cartId=" + cartId + ", total=" + total + ", bookCount=" + bookCount
                + ", buyingPrice=" + buyingPrice + ", isAvailable=" + isAvailable + "]";
    }
}
