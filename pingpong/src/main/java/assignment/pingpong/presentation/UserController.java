package assignment.pingpong.presentation;

import assignment.pingpong.application.user.UserQueryService;
import assignment.pingpong.global.dto.ApiResponse;
import assignment.pingpong.presentation.dto.request.PageReq;
import assignment.pingpong.presentation.dto.response.UserPageRes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserQueryService userQueryService;

    @GetMapping
    public ApiResponse<?> findAllUserByPage(@Valid PageReq pageReq) {
        UserPageRes userPageRes = userQueryService.findAllUserByPage(pageReq);

        return ApiResponse.of(200, "API 요청이 성공했습니다.", userPageRes);
    }
}
