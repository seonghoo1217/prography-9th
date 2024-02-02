package assignment.pingpong.presentation.dto.request.room;

import assignment.pingpong.domain.room.RoomType;

public record CreateRoomReq(int userId, RoomType roomType, String title) {
}
