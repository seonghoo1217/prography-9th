package assignment.pingpong.domain.room;

import lombok.Getter;

@Getter
public enum RoomType {
    SINGLE(2), DOUBLE(4);


    private int capacity;

    RoomType(int capacity) {
        this.capacity = capacity;
    }
}
