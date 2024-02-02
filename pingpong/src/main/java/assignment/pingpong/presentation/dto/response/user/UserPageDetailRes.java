package assignment.pingpong.presentation.dto.response.user;

import assignment.pingpong.domain.user.Status;

import java.time.LocalDateTime;

public record UserPageDetailRes(Integer id, Integer fakerId, String name, String email, Status status,
                                LocalDateTime createAt,
                                LocalDateTime updateAt) {
}
