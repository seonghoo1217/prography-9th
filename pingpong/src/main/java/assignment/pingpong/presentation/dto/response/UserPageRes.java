package assignment.pingpong.presentation.dto.response;

import assignment.pingpong.domain.user.User;

import java.util.List;

public record UserPageRes(long totalElements, int totalPages, List<User> userList) {

}

