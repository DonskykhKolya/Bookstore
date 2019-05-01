package ua.donskykh.mybookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.donskykh.mybookstore.domain.CustomerReviews;

@Repository("customerReviewRepository")
public interface CustomerReviewsRepository extends JpaRepository<CustomerReviews, Long> {
}
