package assignment.pingpong.presentation;

import assignment.pingpong.application.user.UserQueryService;
import assignment.pingpong.global.dto.ApiResponse;
import assignment.pingpong.presentation.dto.request.PageReq;
import assignment.pingpong.presentation.dto.response.user.UserPageRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @Operation(summary = "유저 전체 정보 조회 Page")
    public ApiResponse<?> findAllUserByPage(@Parameter(name = "size, page", description = "페이징처리를 위한 size, page값")
                                            @Valid PageReq pageReq) {
        UserPageRes userPageRes = userQueryService.findAllUserByPage(pageReq);

        return ApiResponse.of(200, "API 요청이 성공했습니다.", userPageRes);
    }
}
