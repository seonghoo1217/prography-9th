package assignment.pingpong.application.room;

import assignment.pingpong.domain.repository.RoomRepository;
import assignment.pingpong.domain.room.Room;
import assignment.pingpong.presentation.dto.response.RoomPageRes;
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
public class RoomQueryService {

    private final RoomRepository roomRepository;

    public RoomPageRes findAllRoomByPage(int size, int page) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        Page<Room> roomPage = roomRepository.findAll(pageable);

        List<Room> roomList = roomPage.stream().toList();

        return new RoomPageRes(roomPage.getTotalElements(), roomPage.getTotalPages(), roomList);
    }
}
