package assignment.pingpong.presentation.dto.response.room;

import assignment.pingpong.domain.room.Room;
import assignment.pingpong.domain.room.RoomType;
import assignment.pingpong.domain.room.Status;

import java.time.LocalDateTime;

public record RoomRes(int id, String title, int hostId, RoomType roomType, Status status, LocalDateTime createAt,
                      LocalDateTime updateAt) {
    public RoomRes(Room room) {
        this(room.getId(), room.getTitle(), room.getHost(), room.getRoomType(), room.getRoomStatus().getStatus(), room.getCreatedAt(), room.getUpdatedAt());
    }
}
