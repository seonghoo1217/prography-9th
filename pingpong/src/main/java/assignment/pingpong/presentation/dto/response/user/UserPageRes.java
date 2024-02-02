package assignment.pingpong.presentation.dto.response.user;

import java.util.List;

public record UserPageRes(long totalElements, int totalPages, List<UserPageDetailRes> userList) {

}

