package assignment.pingpong.domain.repository;

import assignment.pingpong.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
