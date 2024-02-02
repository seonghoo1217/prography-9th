package assignment.pingpong.presentation;

import assignment.pingpong.global.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

    @GetMapping
    public ApiResponse<?> healthCheck() {
        return ApiResponse.of(200, "API 요청이 성공하였습니다");
    }
}
