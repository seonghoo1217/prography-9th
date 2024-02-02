package assignment.pingpong.domain.repository;

import assignment.pingpong.domain.Team;
import assignment.pingpong.domain.UserRoom;
import assignment.pingpong.domain.room.Room;
import assignment.pingpong.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoomRepository extends JpaRepository<UserRoom, Integer> {
    boolean existsByUser(User user);

    Optional<UserRoom> findByUserAndRoom(User user, Room room);

    Long countByRoomAndTeam(Room room, Team team);
}
