package assignment.pingpong.domain.repository;

import assignment.pingpong.domain.UserRoom;
import assignment.pingpong.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoomRepository extends JpaRepository<UserRoom, Integer> {
    boolean existsByUser(User user);
}
