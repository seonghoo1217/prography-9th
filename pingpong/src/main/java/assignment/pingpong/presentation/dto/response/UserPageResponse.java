package assignment.pingpong.presentation.dto.response;

import assignment.pingpong.domain.user.User;
import lombok.Getter;

import java.util.List;

@Getter
public class UserPageResponse {
    private long totalElements;
    private int totalPages;
    private List<User> userList;

    public UserPageResponse(long totalElements, int totalPages, List<User> userList) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.userList = userList;
    }
}
