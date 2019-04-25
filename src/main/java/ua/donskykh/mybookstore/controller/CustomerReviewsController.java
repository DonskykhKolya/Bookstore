package ua.donskykh.mybookstore.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.donskykh.mybookstore.domain.Book;
import ua.donskykh.mybookstore.domain.CustomerReviews;
import ua.donskykh.mybookstore.domain.User;
import ua.donskykh.mybookstore.service.CustomerReviewsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
public class CustomerReviewsController {

    static final int PAGE_DIVISION = 7;

    @Qualifier
    private final CustomerReviewsService customerReviewsService;

    public CustomerReviewsController(CustomerReviewsService customerReviewsService) {
        this.customerReviewsService = customerReviewsService;
    }

    @GetMapping(value = "/reviews")
    public ModelAndView showReviews(@RequestParam(required = false, defaultValue = "0") Integer page) {
        ModelAndView modelAndView = new ModelAndView("page");
        modelAndView.addObject("userClickReviews", true);
        modelAndView.addObject("title", "Reviews");
        if (page < 0) page = 0;
        modelAndView.addObject("reviews", customerReviewsService
                .findAllCustomerReviews(new PageRequest(page, PAGE_DIVISION, Sort.Direction.DESC, "id")));
        modelAndView.addObject("all", getPageCount());
        return modelAndView;
    }

    @PostMapping(value = "/addRewiew")
    @ResponseBody
    public String customerReviewSave(@RequestParam("name") String name,
                                     @RequestParam("review") String rev,
                                     @RequestParam("rating") int rating) {

        CustomerReviews customerReviews = new CustomerReviews();
        customerReviews.setName(name);
        customerReviews.setReview(rev);
        customerReviews.setRating(rating);
        customerReviewsService.save(customerReviews);
        return "true";
    }

    private long getPageCount() {
        long amount = customerReviewsService.count();
        return (amount / PAGE_DIVISION) + ((amount % PAGE_DIVISION > 0) ? 1 : 0);
    }
}
