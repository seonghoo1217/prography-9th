package assignment.pingpong.application;

import assignment.pingpong.domain.repository.RoomRepository;
import assignment.pingpong.domain.repository.UserRepository;
import assignment.pingpong.domain.repository.UserRoomRepository;
import assignment.pingpong.domain.user.Status;
import assignment.pingpong.domain.user.User;
import assignment.pingpong.global.dto.ApiResponse;
import assignment.pingpong.global.dto.FakerResponse;
import assignment.pingpong.global.dto.FakerUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InitCommandService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final UserRoomRepository userRoomRepository;

    public ApiResponse<?> init(int seed, int quantity) {
        userRepository.deleteAll();
        roomRepository.deleteAll();
        userRoomRepository.deleteAll();

        // faker API 호출
        RestTemplate restTemplate = new RestTemplate();
        String fakerApiUrl = "https://fakerapi.it/api/v1/users?_seed=" + seed +
                "&_quantity=" + quantity + "&_locale=ko_KR";
        System.out.println(fakerApiUrl);
        ResponseEntity<FakerResponse> responseEntity = restTemplate.getForEntity(fakerApiUrl, FakerResponse.class);
        FakerResponse fakerResponse = responseEntity.getBody();

        for (FakerUser fakerUser : fakerResponse.data()) {
            Status status = determineStatus(fakerUser.id());
            User user = new User(fakerUser.id(), fakerUser.username(), fakerUser.email(), status, LocalDateTime.now(), LocalDateTime.now());
            userRepository.save(user);
        }

        return new ApiResponse<>(200, "API 요청이 성공했습니다.");
    }

    private Status determineStatus(int id) {
        if (id <= 30) {
            return Status.ACTIVE;
        } else if (id <= 60) {
            return Status.WAIT;
        } else {
            return Status.NON_ACTIVE;
        }
    }
}
