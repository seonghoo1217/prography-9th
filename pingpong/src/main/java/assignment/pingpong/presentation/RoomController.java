package assignment.pingpong.presentation;

import assignment.pingpong.application.room.RoomCommandService;
import assignment.pingpong.global.dto.ApiResponse;
import assignment.pingpong.presentation.dto.request.CreateRoomReq;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomCommandService roomCommandService;

    @PostMapping
    public ApiResponse<?> createRoom(@RequestBody CreateRoomReq createRoomReq) {
        Integer roomId = roomCommandService.createRoom(createRoomReq.userId(), createRoomReq.roomType(), createRoomReq.title());

        return ApiResponse.of(200, "API 요청이 성공했습니다.");
    }
}
