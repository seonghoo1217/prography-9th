package assignment.pingpong.presentation.dto.response;

import assignment.pingpong.domain.user.User;
import lombok.Getter;

import java.util.List;

@Getter
public class UserPageRes {
    private long totalElements;
    private int totalPages;
    private List<User> userList;

    public UserPageRes(long totalElements, int totalPages, List<User> userList) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.userList = userList;
    }
}
