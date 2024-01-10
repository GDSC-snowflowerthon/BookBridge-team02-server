package team2.bookbridge.global.common.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@ResponseStatus(INTERNAL_SERVER_ERROR)
public abstract class InternalServerErrorException extends ApiException{
    public InternalServerErrorException (final String message) {
        super(message, INTERNAL_SERVER_ERROR);
    }
}
