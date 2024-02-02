package assignment.pingpong.presentation;

import assignment.pingpong.application.room.RoomCommandService;
import assignment.pingpong.global.dto.ApiResponse;
import assignment.pingpong.presentation.dto.request.ModifyTeamReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {

    private final RoomCommandService roomCommandService;

    @PutMapping("/{roomId}")
    @Operation(summary = "Team 정보 수정")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "유저 ID 또는 방 ID가 없음"),
    })
    public ApiResponse<?> modifyTeam(@PathVariable Integer roomId, @RequestBody ModifyTeamReq modifyTeamReq) {
        return roomCommandService.modifyTeam(roomId, modifyTeamReq.userId());
    }
}
