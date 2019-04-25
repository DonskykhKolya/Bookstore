package ua.donskykh.mybookstore.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.donskykh.mybookstore.domain.Author;
import ua.donskykh.mybookstore.domain.Book;
import ua.donskykh.mybookstore.domain.Category;
import ua.donskykh.mybookstore.service.AuthorService;
import ua.donskykh.mybookstore.service.BookService;
import ua.donskykh.mybookstore.service.CategoryService;
import ua.donskykh.mybookstore.validator.BookValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/control")
public class AdministrationController {

    @Qualifier("categoryService")
    private final CategoryService categoryService;
    @Qualifier("bookService")
    private final BookService bookService;
    @Qualifier("authorService")
    private final AuthorService authorService;

    public AdministrationController(CategoryService categoryService, BookService bookService, AuthorService authorService) {
        this.categoryService = categoryService;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping(value = "/books")
    public ModelAndView showControlBooks(@RequestParam(name = "operation", required = false) String operation) {
        ModelAndView modelAndView = new ModelAndView("page");
        modelAndView.addObject("userClickControlBooks", true);
        modelAndView.addObject("title", "Control books");
        Book aBook = new Book();
        aBook.setSupplierId(1);
        aBook.setActive(true);
        modelAndView.addObject("book", aBook);
        if (operation != null && operation.equals("book")) {
            modelAndView.addObject("message", "Book added sucessfully!");
        }
        else if (operation != null && operation.equals("category")) {
            modelAndView.addObject("message", "Category added sucessfully!");
        }
        else if (operation != null && operation.equals("author")) {
            modelAndView.addObject("message", "Author added sucessfully!");
        }
        else if (operation != null && operation.equals("updated")) {
            modelAndView.addObject("message", "Book updated sucessfully!");
        }
        return modelAndView;
    }

    @PostMapping(value = "/books")
    public String handleBookSubmission(@Valid @ModelAttribute("book") Book bBook, Model model,
                                       BindingResult bindingResult, @RequestParam("file") MultipartFile file,
                                          HttpServletRequest request) {
        if (bBook.getId() == null) {
            new BookValidator().validate(bBook, bindingResult);
            if (bindingResult.hasErrors()) {
                model.addAttribute("userClickControlBooks", true);
                model.addAttribute("title", "Control Books");
                model.addAttribute("message", "Validation failed for book submission!");
                return "page";
            }
            try {
                fileSaveInFolder(bBook, file, request);
                bookService.save(bBook);
                return "redirect:/control/books?operation=book";
            } catch (IOException e) {
                return getString(model, e);
            }
        } else {
            if (!bBook.getFile().getOriginalFilename().equals("")) {
                new BookValidator().validate(bBook, bindingResult);
                if (bindingResult.hasErrors()) {
                    model.addAttribute("userClickControlBooks", true);
                    model.addAttribute("title", "Control books");
                    model.addAttribute("message", "Validation failed for book submission(error)!");
                    return "page";
                }
                try {
                    fileSaveInFolder(bBook, file, request);
                    bookService.updateBook(bBook);
                    return "redirect:/control/books?operation=updated";
                } catch (IOException e) {
                    return getString(model, e);
                }
            } else {
                bookService.updateBook(bBook);
                return "redirect:/control/books?operation=updated";
            }
        }
    }

    private String getString(Model model, IOException e) {
        e.printStackTrace();
        model.addAttribute("userClickControlBooks", true);
        model.addAttribute("title", "Control books");
        model.addAttribute("message", e.getMessage());
        return "page";
    }

    private void fileSaveInFolder(Book bBook, MultipartFile file, HttpServletRequest request) throws IOException {
        byte[] bytes = file.getBytes();
        String p = request.getSession().getServletContext().getRealPath("/static/images/");
        System.out.println(p);
        Path path = Paths.get(p + bBook.getCode() + ".jpg");
        Files.write(path, bytes);
    }

    @PostMapping("/books/{id}/activation")
    @ResponseBody
    public String handleBookActivation(@PathVariable("id") long id) {
        Book book = bookService.findBookByIdForAdmin(id);
        boolean isActive = book.isActive();
        book.setActive(!isActive);
        bookService.updateBook(book);
        return (isActive) ? "You have successfully deactivated the book with Id : " + book.getId()
                : "You have successfully activated the book with Id : " + book.getId();
    }

    @GetMapping("{id}/books")
    public ModelAndView showEditBooks(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("page");
        modelAndView.addObject("userClickControlBooks", true);
        modelAndView.addObject("title", "Control books");
        Book aBook = bookService.findBookByIdForAdmin(id);
        modelAndView.addObject("book", aBook);
        return modelAndView;
    }

    @PostMapping("/category")
    public String handleCategorySubmission(@ModelAttribute("category") Category category) {
        categoryService.saveCategory(category);
        return "redirect:/control/books?operation=category";
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        List<Category> categories = categoryService.findAllCategories();
        return categories;
    }

    @ModelAttribute("category")
    public Category getCategory() {
        return new Category();
    }

    @PostMapping("/author")
    public String handleAuthorSubmission(@ModelAttribute("author") Author author) {
        authorService.saveAuthor(author);
        return "redirect:/control/books?operation=author";
    }

    @ModelAttribute("authors")
    public List<Author> getAuthors() {
        List<Author> authors = authorService.findAllAuthors();
        return authors;
    }

    @ModelAttribute("author")
    public Author getAuthor() {
        return new Author();
    }
}
