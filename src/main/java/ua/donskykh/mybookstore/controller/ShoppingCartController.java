package ua.donskykh.mybookstore.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.donskykh.mybookstore.domain.Book;
import ua.donskykh.mybookstore.domain.BooksInCart;
import ua.donskykh.mybookstore.domain.ShoppingCart;
import ua.donskykh.mybookstore.service.BookService;
import ua.donskykh.mybookstore.service.BooksInCartService;
import ua.donskykh.mybookstore.service.ShoppingCartService;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Qualifier("cartLineService")
    private final BooksInCartService booksInCartService;
    @Qualifier("bookService")
    private final BookService bookService;
    @Qualifier("cartService")
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(BooksInCartService booksInCartService,
                                  BookService bookService, ShoppingCartService shoppingCartService) {
        this.booksInCartService = booksInCartService;
        this.bookService = bookService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/show")
    public ModelAndView showCart(@RequestParam(name = "result", required = false) String result) {
        ModelAndView modelAndView = new ModelAndView("page");
        if (result != null) {
            switch (result) {
                case "updated":
                    modelAndView.addObject("message", "Book in shopping cart has been updated sucessfully");
                    break;
                case "error":
                    modelAndView.addObject("message", "Something went wrong!!");
                    break;
                case "added":
                    modelAndView.addObject("message", "Book in shopping cart has been added sucessfully!");
                    break;
                case "deleted":
                    modelAndView.addObject("message", "Book in shopping cart has been removed sucessfully");
                    break;
                default:
                    break;
            }
        }
        modelAndView.addObject("title", "User shopping cart");
        modelAndView.addObject("userClickShowShoppingCart", true);
        modelAndView.addObject("booksInCart", booksInCartService.findBooksInCarts());
        return modelAndView;
    }

    @GetMapping("/{id}/update")
    public String updateShoppingCart(@PathVariable long id, @RequestParam int count) {
        BooksInCart booksInCart = booksInCartService.findBooksInCartById(id);
        if (booksInCart != null) {
            Book book = booksInCart.getBook();
            double oldTotal = booksInCart.getTotal();
            if (book.getQuantity() <= count) {
                count = book.getQuantity();
            }
            booksInCart.setBookCount(count);
            booksInCart.setBuyingPrice(book.getPrice());
            booksInCart.setTotal(book.getPrice() * count);
            String response = booksInCartService.updateBooksInCart(booksInCart) + "";
            ShoppingCart shoppingCart = shoppingCartService.findShoppingCart();
            shoppingCart.setTotal(shoppingCart.getTotal() - oldTotal + booksInCart.getTotal());
            shoppingCartService.updateShoppingCart(shoppingCart);
            return "redirect:/shoppingCart/show?result=updated";
        } else {
            return "redirect:/shoppingCart/show?result=error";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteCart(@PathVariable long id) {
        BooksInCart booksInCart = booksInCartService.findBooksInCartById(id);
        if (booksInCart != null) {
            ShoppingCart shoppingCart = shoppingCartService.findShoppingCart();
            shoppingCart.setTotal(shoppingCart.getTotal() - booksInCart.getTotal());
            shoppingCart.setBooksInCart(shoppingCart.getBooksInCart() - 1);
            shoppingCartService.updateShoppingCart(shoppingCart);
            booksInCartService.deleteBooksInCart(booksInCart);
            return "redirect:/shoppingCart/show?result=deleted";
        } else {
            return "redirect:/shoppingCart/show?result=error";
        }
    }

    @GetMapping("/add/{id}/books")
    public String addCart(@PathVariable long id) {
        ShoppingCart shoppingCart = shoppingCartService.findShoppingCart();
        BooksInCart booksInCart = booksInCartService.findBooksInCartByCartIdAndBookId(shoppingCart.getId(), id);
        if (booksInCart != null) {
            return "";
        } else {
            booksInCart = new BooksInCart();
            Book book = bookService.findBookById(id);
            booksInCart.setCartId(shoppingCart.getId());
            booksInCart.setBook(book);
            booksInCart.setBuyingPrice(book.getPrice());
            booksInCart.setBookCount(1);
            booksInCart.setTotal(book.getPrice());
            booksInCart.setAvailable(true);
            booksInCartService.saveBooksInCart(booksInCart);
            shoppingCart.setBooksInCart(shoppingCart.getBooksInCart() + 1);
            shoppingCart.setTotal(shoppingCart.getTotal() + booksInCart.getTotal());
            shoppingCartService.saveShoppingCart(shoppingCart);
            return "redirect:/shoppingCart/show?result=added";
        }
    }
}
