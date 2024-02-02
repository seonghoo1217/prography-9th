package assignment.pingpong.presentation.dto.request;

import assignment.pingpong.domain.room.RoomType;

public record CreateRoomReq(int userId, RoomType roomType, String title) {
}
