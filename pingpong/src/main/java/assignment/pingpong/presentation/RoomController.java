package assignment.pingpong.presentation;

import assignment.pingpong.application.room.RoomCommandService;
import assignment.pingpong.application.room.RoomQueryService;
import assignment.pingpong.global.dto.ApiResponse;
import assignment.pingpong.presentation.dto.request.CreateRoomReq;
import assignment.pingpong.presentation.dto.request.PageReq;
import assignment.pingpong.presentation.dto.response.RoomPageRes;
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
}
