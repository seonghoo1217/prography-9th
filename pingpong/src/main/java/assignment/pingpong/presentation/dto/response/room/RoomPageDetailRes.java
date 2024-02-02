package assignment.pingpong.presentation.dto.response.room;

import assignment.pingpong.domain.room.RoomType;
import assignment.pingpong.domain.room.Status;

public record RoomPageDetailRes(Integer id, String title, Integer hostId, RoomType roomType, Status status) {
}
