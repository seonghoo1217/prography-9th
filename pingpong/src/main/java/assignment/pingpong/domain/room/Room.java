package assignment.pingpong.domain.room;

import assignment.pingpong.domain.BaseEntity;
import assignment.pingpong.domain.UserRoom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Room extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private Integer host;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @OneToMany(mappedBy = "room")
    private List<UserRoom> userRooms = new ArrayList<>();

    private RoomStatus roomStatus;

    public Room(String title, Integer host, Status status, RoomType roomType) {
        this.title = title;
        this.host = host;
        this.roomStatus = new RoomStatus(status);
        this.roomType = roomType;
    }

    public boolean isOverCapacity() {
        return this.userRooms.size() >= this.roomType.getCapacity();
    }

    public boolean isRoomReady() {
        return this.userRooms.size() == this.roomType.getCapacity();
    }

    public boolean isHost(int hostId) {
        return this.host == hostId;
    }
}
