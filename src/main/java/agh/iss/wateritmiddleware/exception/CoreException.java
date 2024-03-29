package agh.iss.wateritmiddleware.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND)
@Getter
@AllArgsConstructor
public class CoreException extends RuntimeException {

    private final ErrorCode errorCode;
    private final ErrorSubcode errorSubcode;

    @Override
    public String toString() {
        return "CoreException{" +
                "errorCode=" + errorCode.getHttpStatus() +
                ", errorSubcode=" + errorSubcode.name() +
                '}';
    }
}
