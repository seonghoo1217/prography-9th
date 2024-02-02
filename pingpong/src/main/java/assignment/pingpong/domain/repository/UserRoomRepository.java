package assignment.pingpong.domain.repository;

import assignment.pingpong.domain.UserRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoomRepository extends JpaRepository<UserRoom, Integer> {
}
