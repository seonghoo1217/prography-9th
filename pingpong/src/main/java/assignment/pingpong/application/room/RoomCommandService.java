package assignment.pingpong.application.room;

import assignment.pingpong.domain.Exception.BadAPIRequestException;
import assignment.pingpong.domain.repository.RoomRepository;
import assignment.pingpong.domain.repository.UserRepository;
import assignment.pingpong.domain.repository.UserRoomRepository;
import assignment.pingpong.domain.room.Room;
import assignment.pingpong.domain.room.RoomType;
import assignment.pingpong.domain.room.Status;
import assignment.pingpong.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class RoomCommandService {

    private final UserRepository userRepository;

    private final RoomRepository roomRepository;

    private final UserRoomRepository userRoomRepository;

    public Integer createRoom(int userId, RoomType roomType, String title) {
        User user = userRepository.findById(userId).orElseThrow(BadAPIRequestException::new);
        if (!user.isActive()) {
            throw new BadAPIRequestException();
        }

        if (userRoomRepository.existsByUser(user)) {
            throw new BadAPIRequestException();
        }

        Room room = new Room(title, userId, Status.WAIT, roomType);
        roomRepository.save(room);

        return room.getId();
    }
}
