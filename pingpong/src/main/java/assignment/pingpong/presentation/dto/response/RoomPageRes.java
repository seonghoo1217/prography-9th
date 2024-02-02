package assignment.pingpong.presentation.dto.response;

import assignment.pingpong.domain.room.Room;

import java.util.List;

public record RoomPageRes(long totalElements, int totalPages, List<Room> roomList) {
}
