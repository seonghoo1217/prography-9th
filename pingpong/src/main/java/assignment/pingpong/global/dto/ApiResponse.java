package assignment.pingpong.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Objects;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ApiResponse<T> {
    private final Integer code;
    private final String message;
    private T result;

    public ApiResponse(Integer code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public ApiResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> ApiResponse<T> of(Integer code, String message, T result) {
        return new ApiResponse<>(code, message, result);
    }

    public static <T> ApiResponse<T> of(Integer code, String message) {
        return new ApiResponse<>(code, message);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ApiResponse) obj;
        return Objects.equals(this.code, that.code) &&
                Objects.equals(this.message, that.message) &&
                Objects.equals(this.result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, result);
    }

    @Override
    public String toString() {
        return "ApiResponse[" +
                "code=" + code + ", " +
                "message=" + message + ", " +
                "result=" + result + ']';
    }

}
