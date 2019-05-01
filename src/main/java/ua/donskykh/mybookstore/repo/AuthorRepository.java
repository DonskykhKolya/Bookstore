package ua.donskykh.mybookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;
import ua.donskykh.mybookstore.domain.Author;

import java.util.List;

@Repository("authorRepository")
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByName(String name);
}
