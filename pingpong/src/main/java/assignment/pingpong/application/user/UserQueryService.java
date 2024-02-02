package assignment.pingpong.application.user;

import assignment.pingpong.domain.repository.UserRepository;
import assignment.pingpong.domain.user.User;
import assignment.pingpong.presentation.dto.request.PageReq;
import assignment.pingpong.presentation.dto.response.user.UserPageDetailRes;
import assignment.pingpong.presentation.dto.response.user.UserPageRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryService {

    private final UserRepository userRepository;

    public UserPageRes findAllUserByPage(PageReq pageReq) {
        Pageable pageable = PageRequest.of(pageReq.page(), pageReq.size(), Sort.by("id"));
        Page<User> userPage = userRepository.findAll(pageable);

        List<UserPageDetailRes> userList = userPage.stream()
                .map(user -> new UserPageDetailRes(user.getId(), user.getFakerId(), user.getName(), user.getEmail(), user.getStatus(), user.getCreatedAt(), user.getUpdatedAt()))
                .toList();

        return new UserPageRes(userPage.getTotalElements(), userPage.getTotalPages(), userList);
    }
}
