package assignment.pingpong.application.room.event;

import assignment.pingpong.domain.room.Status;

public record ProgressStatusEvent(int roomId, Status status) {
}
