package team2.bookbridge.global.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss",
            locale = "Asia/Seoul"
    )
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String path;
    private String remote;

    @Builder
    public ErrorResponse(LocalDateTime timestamp, int status, String message, String path, String remote) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.path = path;
        this.remote = remote;
    }
}
