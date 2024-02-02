package assignment.pingpong.presentation;

import assignment.pingpong.application.room.RoomCommandService;
import assignment.pingpong.global.dto.ApiResponse;
import assignment.pingpong.presentation.dto.request.ModifyTeamReq;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {

    private final RoomCommandService roomCommandService;

    @PutMapping("/{roomId}")
    public ApiResponse<?> modifyTeam(@PathVariable Integer roomId, @RequestBody ModifyTeamReq modifyTeamReq) {
        return roomCommandService.modifyTeam(roomId, modifyTeamReq.userId());
    }
}
