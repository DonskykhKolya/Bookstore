package ua.donskykh.mybookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.donskykh.mybookstore.domain.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

}
