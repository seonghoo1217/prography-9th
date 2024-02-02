package assignment.pingpong.presentation;

import assignment.pingpong.application.InitCommandService;
import assignment.pingpong.global.dto.ApiResponse;
import assignment.pingpong.presentation.dto.request.InitReq;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/init")
@RequiredArgsConstructor
public class InitController {

    private final InitCommandService initCommandService;

    @PostMapping
    public ApiResponse<?> initData(@RequestBody InitReq initReq) {
        return initCommandService.init(initReq.seed(), initReq.quantity());
    }
}
