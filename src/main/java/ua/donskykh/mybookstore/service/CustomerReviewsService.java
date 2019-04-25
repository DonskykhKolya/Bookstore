package ua.donskykh.mybookstore.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import ua.donskykh.mybookstore.domain.CustomerReviews;

import java.util.List;

public interface CustomerReviewsService {

    List<CustomerReviews> findAllCustomerReviews(Pageable pageable);

    String save(CustomerReviews review);

    long count();
}
