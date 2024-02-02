package assignment.pingpong.application.user;

import assignment.pingpong.domain.repository.UserRepository;
import assignment.pingpong.domain.user.User;
import assignment.pingpong.presentation.dto.request.PageRequestDTO;
import assignment.pingpong.presentation.dto.response.UserPageResponseDTO;
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

    public UserPageResponseDTO findAllUserPage(PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.page(), pageRequestDTO.size(), Sort.by("id"));
        Page<User> userPage = userRepository.findAll(pageable);

        List<User> userList = userPage.stream().toList();

        return new UserPageResponseDTO(userPage.getTotalElements(), userPage.getTotalPages(), userList);
    }
}
