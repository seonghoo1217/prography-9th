package assignment.pingpong.domain.repository;

import assignment.pingpong.domain.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
