package assignment.pingpong.presentation.dto.response.room;

import java.util.List;

public record RoomPageRes(long totalElements, int totalPages, List<RoomPageDetailRes> roomList) {
}
