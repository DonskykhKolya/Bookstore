package ua.donskykh.mybookstore.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.donskykh.mybookstore.domain.Category;
import ua.donskykh.mybookstore.domain.CustomerReviews;
import ua.donskykh.mybookstore.repo.CustomerReviewsRepository;

import java.util.ArrayList;
import java.util.List;

@Service("customerReviewsService")
public class CustomerReviewsServiceImpl implements CustomerReviewsService {

    @Qualifier("customerReviewRepository")
    private final CustomerReviewsRepository customerReviewsRepository;

    public CustomerReviewsServiceImpl(CustomerReviewsRepository customerReviewsRepository) {
        this.customerReviewsRepository = customerReviewsRepository;
    }

    @Override
    @Transactional
    public List<CustomerReviews> findAllCustomerReviews(Pageable pageable) {
        return customerReviewsRepository.findAll(pageable).getContent();
    }

    @Override
    @Transactional
    public String save(CustomerReviews review) {
        customerReviewsRepository.saveAndFlush(review);
        return "Customer review added sucessfully";
    }

    @Override
    @Transactional()
    public long count() {
        return customerReviewsRepository.count();
    }
}
