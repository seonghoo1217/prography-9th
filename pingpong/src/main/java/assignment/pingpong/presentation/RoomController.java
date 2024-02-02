package assignment.pingpong.presentation;

import assignment.pingpong.application.room.RoomCommandService;
import assignment.pingpong.application.room.RoomQueryService;
import assignment.pingpong.global.dto.ApiResponse;
import assignment.pingpong.presentation.dto.request.*;
import assignment.pingpong.presentation.dto.response.room.RoomPageRes;
import assignment.pingpong.presentation.dto.response.room.RoomRes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomCommandService roomCommandService;
    private final RoomQueryService roomQueryService;

    @PostMapping
    public ApiResponse<?> createRoom(@RequestBody CreateRoomReq createRoomReq) {
        Integer roomId = roomCommandService.createRoom(createRoomReq.userId(), createRoomReq.roomType(), createRoomReq.title());

        return ApiResponse.of(200, "API 요청이 성공했습니다.");
    }

    @GetMapping
    public ApiResponse<?> findAllRoomByPage(@Valid PageReq pageReq) {
        RoomPageRes roomPageRes = roomQueryService.findAllRoomByPage(pageReq.size(), pageReq.page());

        return ApiResponse.of(200, "API 요청이 성공했습니다.", roomPageRes);
    }

    @GetMapping("/{id}")
    public ApiResponse<?> findRoomById(@PathVariable Integer id) {
        RoomRes roomRes = roomQueryService.findRoomById(id);

        return ApiResponse.of(200, "API 요청이 성공했습니다.", roomRes);
    }

    @PostMapping("/attention/{roomId}")
    public ApiResponse<?> joinRoom(@PathVariable Integer roomId, @RequestBody JoinRoomReq joinRoomReq) {
        return roomCommandService.joinRoom(roomId, joinRoomReq.userId());
    }

    @PostMapping("/out/{roomId}")
    public ApiResponse<?> leaveRoom(@PathVariable Integer roomId, @RequestBody LeaveRoomReq leaveRoomReq) {
        return roomCommandService.leaveRoom(roomId, leaveRoomReq.userId());
    }

    @PostMapping("/start/{roomId}")
    public ApiResponse<?> startGamePingPong(@PathVariable Integer roomId, @RequestBody GameRoomReq gameRoomReq) {
        return roomCommandService.gameStartPingPong(roomId, gameRoomReq.userId());
    }
}
