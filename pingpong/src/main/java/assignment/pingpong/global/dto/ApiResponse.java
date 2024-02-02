package assignment.pingpong.global.dto;

public record ApiResponse<T>(Integer code, String message, T result) {


    public static <T> ApiResponse<T> of(Integer code, String message, T result) {
        return new ApiResponse<>(code, message, result);
    }
}
