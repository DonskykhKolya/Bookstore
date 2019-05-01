package ua.donskykh.mybookstore.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.donskykh.mybookstore.domain.Author;
import ua.donskykh.mybookstore.domain.Book;
import ua.donskykh.mybookstore.domain.Category;
import ua.donskykh.mybookstore.service.AuthorService;
import ua.donskykh.mybookstore.service.BookService;
import ua.donskykh.mybookstore.service.CategoryService;
import ua.donskykh.mybookstore.service.CustomerReviewsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class StartController {

    @Qualifier("categoryService")
    private final CategoryService categoryService;
    @Qualifier("productService")
    private final BookService bookService;
    @Qualifier("authorService")
    private final AuthorService authorService;
    @Qualifier("customerReviewsService")
    private final CustomerReviewsService customerReviewsService;

    public StartController(CategoryService categoryService, BookService bookService, AuthorService authorService, CustomerReviewsService customerReviewsService) {
        this.categoryService = categoryService;
        this.bookService = bookService;
        this.authorService = authorService;
        this.customerReviewsService = customerReviewsService;
    }

    @GetMapping(value = { "/", "/home" })
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("page");
        modelAndView.addObject("userClickHome", true);
        modelAndView.addObject("title", "Home");
        modelAndView.addObject("categories", categoryService.findAllCategories());
        modelAndView.addObject("authors", authorService.findAllAuthors());
        return modelAndView;
    }

    @GetMapping("/contact")
    public ModelAndView contact() {
        ModelAndView modelAndView = new ModelAndView("page");
        modelAndView.addObject("userClickContact", true);
        modelAndView.addObject("title", "Contact Us");
        return modelAndView;
    }

    @GetMapping("/about")
    public ModelAndView about() {
        ModelAndView modelAndView = new ModelAndView("page");
        modelAndView.addObject("userClickAbout", true);
        modelAndView.addObject("title", "About Us");

        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(name = "error", required = false) String error,
                              @RequestParam(name = "logout", required = false) String logout) {
        ModelAndView modelAndView = new ModelAndView("login");
        if (error != null) {
            modelAndView.addObject("message", "Invalid user name or password!");
        }
        if (logout != null) {
            modelAndView.addObject("logout", "User has successfully logged out!");
        }
        modelAndView.addObject("title", "Login");
        return modelAndView;
    }

    @GetMapping("/show/all/books")
    public ModelAndView showAllBooks() {
        ModelAndView modelAndView = new ModelAndView("page");
        modelAndView.addObject("userClickAllBooks", true);
        modelAndView.addObject("title", "All Books");
        modelAndView.addObject("categories", categoryService.findAllCategories());
        modelAndView.addObject("authors", authorService.findAllAuthors());
        return modelAndView;
    }

    @GetMapping("/show/category/{id}/books")
    public ModelAndView showCategoryBooks(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("page");
        Category category = null;
        category = categoryService.findCategoryById(id);
        modelAndView.addObject("userClickCategoryBooks", true);
        modelAndView.addObject("title", category.getName());
        modelAndView.addObject("categories", categoryService.findAllCategories());
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    @GetMapping("/show/author/{name}/books")
    public ModelAndView showAuthorsBooks(@PathVariable("name") String name) {
        ModelAndView modelAndView = new ModelAndView("page");
        Author author = null;
        author = authorService.findAuthorByName(name);
        modelAndView.addObject("userClickAuthorBooks", true);
        modelAndView.addObject("title", author.getName());
        modelAndView.addObject("authors", authorService.findAllAuthors());
        modelAndView.addObject("author", author);
        return modelAndView;
    }

    @GetMapping("/show/{id}/books")
    public ModelAndView showSingleBook(@PathVariable("id") long id) throws BookNotFoundExeption {
        ModelAndView modelAndView = new ModelAndView("page");
        Book book = bookService.findBookById(id);
        if (book == null)
            throw new BookNotFoundExeption();
        bookService.updateBook(book);
        modelAndView.addObject("title", book.getTitle());
        modelAndView.addObject("book", book);
        modelAndView.addObject("userClickShowBook", true);
        return modelAndView;
    }

    @GetMapping("/access-denied")
    public ModelAndView accessDenied() {
        ModelAndView modelAndView = new ModelAndView("404");
        modelAndView.addObject("title", "403 - Access Denied");
        modelAndView.addObject("errorTitle", "Aha! Caught You");
        modelAndView.addObject("errorDescription", "You Are not authorized to Access this Page");
        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout";
    }
}
