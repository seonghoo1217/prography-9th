package assignment.pingpong.application.room;

import assignment.pingpong.domain.Exception.BadAPIRequestException;
import assignment.pingpong.domain.UserRoom;
import assignment.pingpong.domain.repository.RoomRepository;
import assignment.pingpong.domain.repository.UserRepository;
import assignment.pingpong.domain.repository.UserRoomRepository;
import assignment.pingpong.domain.room.Room;
import assignment.pingpong.domain.room.RoomType;
import assignment.pingpong.domain.room.Status;
import assignment.pingpong.domain.user.User;
import assignment.pingpong.global.dto.ApiResponse;
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

    public ApiResponse<?> joinRoom(int roomId, int userId) {
        User user = userRepository.findById(userId).orElseThrow(BadAPIRequestException::new);

        Room room = roomRepository.findById(roomId).orElseThrow(BadAPIRequestException::new);

        if (!user.isActive()) {
            throw new BadAPIRequestException();
        }

        if (!room.isWait()) {
            throw new BadAPIRequestException();
        }

        if (userRoomRepository.existsByUser(user)) {
            throw new BadAPIRequestException();
        }

        if (room.isOverCapacity()) {
            throw new BadAPIRequestException();
        }

        UserRoom userRoom = new UserRoom(user, room);
        userRoomRepository.save(userRoom);

        return new ApiResponse(200, "API 요청이 성공했습니다.");
    }

}
