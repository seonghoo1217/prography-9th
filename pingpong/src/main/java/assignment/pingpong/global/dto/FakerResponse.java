package assignment.pingpong.global.dto;

import java.util.List;

public record FakerResponse(String status, int code, int total, List<FakerUser> data) {
}
