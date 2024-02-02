package assignment.pingpong.application.room;

import assignment.pingpong.application.room.event.ProgressStatusEvent;
import assignment.pingpong.domain.Exception.BadAPIRequestException;
import assignment.pingpong.domain.Team;
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
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
@Transactional
public class RoomCommandService {

    private final UserRepository userRepository;

    private final RoomRepository roomRepository;

    private final UserRoomRepository userRoomRepository;

    private final ApplicationEventPublisher eventPublisher;

    private final TransactionTemplate transactionTemplate;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


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

    @Transactional
    public ApiResponse<?> joinRoom(int roomId, int userId) {
        User user = userRepository.findById(userId).orElseThrow(BadAPIRequestException::new);

        Room room = roomRepository.findById(roomId).orElseThrow(BadAPIRequestException::new);

        if (!user.isActive()) {
            throw new BadAPIRequestException();
        }

        if (!room.getRoomStatus().isWait() || room.isOverCapacity()) {
            throw new BadAPIRequestException();
        }

        if (userRoomRepository.existsByUser(user)) {
            throw new BadAPIRequestException();
        }

        UserRoom userRoom = new UserRoom(user, room);
        userRoomRepository.save(userRoom);

        return new ApiResponse(200, "API 요청이 성공했습니다.");
    }

    @Transactional
    public ApiResponse<?> leaveRoom(int roomId, int userId) {
        User user = userRepository.findById(userId).orElseThrow(BadAPIRequestException::new);

        Room room = roomRepository.findById(roomId).orElseThrow(BadAPIRequestException::new);

        UserRoom userRoom = userRoomRepository.findByUserAndRoom(user, room).orElseThrow(BadAPIRequestException::new);

        if (room.getRoomStatus().isProgress() || room.getRoomStatus().isFinish()) {
            throw new BadAPIRequestException();
        }

        if (room.isHost(userId)) {
            room.getRoomStatus().updateRoomStatus(Status.FINISH);
        }

        userRoomRepository.delete(userRoom);

        return new ApiResponse(200, "API 요청이 성공했습니다.");
    }

    @Transactional
    public ApiResponse<?> gameStartPingPong(Integer roomId, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(BadAPIRequestException::new);

        Room room = roomRepository.findById(roomId).orElseThrow(BadAPIRequestException::new);

        if (!room.isHost(userId) || !room.isRoomReady() || !room.getRoomStatus().isWait()) {
            throw new BadAPIRequestException();
        }

//        room.getRoomStatus().updateRoomStatus(Status.PROGRESS);

        eventPublisher.publishEvent(new ProgressStatusEvent(roomId, Status.PROGRESS));
        return ApiResponse.of(200, "API 요청이 성공했습니다.");
    }

    @Transactional
    public ApiResponse<?> modifyTeam(int roomId, int userId) {
        User user = userRepository.findById(userId).orElseThrow(BadAPIRequestException::new);

        Room room = roomRepository.findById(roomId).orElseThrow(BadAPIRequestException::new);

        UserRoom userRoom = userRoomRepository.findByUserAndRoom(user, room).orElseThrow(BadAPIRequestException::new);

        if (!room.getRoomStatus().isWait() || !room.isRoomReady()) {
            throw new BadAPIRequestException();
        }

        Team oppositeTeam = userRoom.getTeam() == Team.RED ? Team.BLUE : Team.RED;

        long count = userRoomRepository.countByRoomAndTeam(room, oppositeTeam);
        if (count >= room.getRoomType().getCapacity() / 2) {
            throw new BadAPIRequestException();
        }

        userRoom.modifyTeam(oppositeTeam);

        return new ApiResponse<>(200, "API 요청이 성공했습니다.");
    }

    @EventListener
    public void handleProgressStatusEvent(ProgressStatusEvent event) {
        Room room = roomRepository.findById(event.roomId()).orElseThrow(BadAPIRequestException::new);
        room.getRoomStatus().updateRoomStatus(event.status());

        if (event.status() == Status.PROGRESS) {
            scheduler.schedule(() -> {
                transactionTemplate.execute(status -> {
                    finishRoomStatus(event.roomId());
                    return null;
                });
            }, 1, TimeUnit.MINUTES);
        }
    }

    @Transactional
    public void finishRoomStatus(Integer roomId) {
        Room findRoom = roomRepository.findById(roomId).orElseThrow(BadAPIRequestException::new);
        findRoom.getRoomStatus().updateRoomStatus(Status.FINISH);
    }
}
