package assignment.pingpong.application.room;

import assignment.pingpong.domain.Exception.BadAPIRequestException;
import assignment.pingpong.domain.repository.RoomRepository;
import assignment.pingpong.domain.room.Room;
import assignment.pingpong.presentation.dto.response.room.RoomPageDetailRes;
import assignment.pingpong.presentation.dto.response.room.RoomPageRes;
import assignment.pingpong.presentation.dto.response.room.RoomRes;
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

        List<RoomPageDetailRes> roomList = roomPage.stream()
                .map(room -> new RoomPageDetailRes(room.getId(), room.getTitle(), room.getHost(), room.getRoomType(), room.getStatus()))
                .toList();

        return new RoomPageRes(roomPage.getTotalElements(), roomPage.getTotalPages(), roomList);
    }

    public RoomRes findRoomById(Integer roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(BadAPIRequestException::new);

        return new RoomRes(room);
    }
}
