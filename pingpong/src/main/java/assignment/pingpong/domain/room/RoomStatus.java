package assignment.pingpong.domain.room;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class RoomStatus {

    @Enumerated(EnumType.STRING)
    private Status status;

    public RoomStatus(Status status) {
        this.status = status;
    }

    public boolean isWait() {
        return this.status == Status.WAIT;
    }

    public boolean isProgress() {
        return this.status == Status.PROGRESS;
    }

    public boolean isFinish() {
        return this.status == Status.FINISH;
    }

    public void updateRoomStatus(Status status) {
        this.status = status;
    }
}
