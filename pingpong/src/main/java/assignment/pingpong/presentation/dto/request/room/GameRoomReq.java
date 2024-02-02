package assignment.pingpong.presentation.dto.request.room;

public record GameRoomReq(Integer userId) { //Game, Join, Leave 모두 UserId만을 담고있지만 확장성을 고려하여 분리하여 관리
}
