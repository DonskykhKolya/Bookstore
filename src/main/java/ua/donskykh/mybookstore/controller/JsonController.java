package ua.donskykh.mybookstore.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ua.donskykh.mybookstore.domain.Book;
import ua.donskykh.mybookstore.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/json/data")
public class JsonController {

    @Qualifier("bookService")
    private final BookService bookService;

    public JsonController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all/books")
    public @ResponseBody List<Book> getAllBooks() {
        List<Book> books = bookService.findAllBooks();
        return books;
    }

    @GetMapping("/admin/all/books")
    public List<Book> getAllBooksForAdmin() {
        List<Book> books = bookService.findAllBooksForAdmin();
        return books;
    }

    @GetMapping("/category/{categoryId}/books")
    public List<Book> getBooksByCategory(@PathVariable("categoryId") long categoryId) {
        List<Book> books = bookService.findBookByCategoryId(categoryId);
        return books;
    }

    @GetMapping("/author/{authorName}/books")
    public List<Book> getBooksByAuthor(@PathVariable("authorName") String authorName) {
        List<Book> books = bookService.findBookByAuthorName(authorName);
        return books;
    }
}
