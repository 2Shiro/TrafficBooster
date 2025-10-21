package com.andgivemarketing.trafficbooster.global.exception;


import com.andgivemarketing.trafficbooster.global.dto.ResponseDTO;
import jakarta.servlet.ServletException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice는 try catch 처럼 throw 던지면 코드가 끝나는 것이 아닌
// 그 다음 코드를 실행시켜 주는 어노테이션
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 사전에 정의되지 않은 예외 처리 핸들러
     * @param ex : 일반 예외 (사전 정의되지 않은 예외)
     * @return : 에러 코드, 에러 이름, 에러 메세지
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<?>> handleException(Exception ex) {
        String location = extractLocation(ex);
        
        return ResponseEntity
                .internalServerError()
                .body(ResponseDTO.failure(500, "UNDEFINED_ERROR", ex.getMessage(), location));
    }

    /**
     * 사전에 미리 정의된 예외 처리 핸들러
     * @param ex : 사전 정의 예외
     * @return : 에러 코드, 에러 이름, 에러 메세지
     */
    @ExceptionHandler(value=CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex) throws ServletException {
        String location = extractLocation(ex);

        return ResponseEntity
                .status(ex.getCode())
                .body(ResponseDTO.failure(ex.getCode(), ex.getName(), ex.getMessage(), location));
    }

    /**
     * 예외 발생 위치 추출
     * @param ex : 예외
     * @return : 예외 발생 위치
     */
    private String extractLocation(Exception ex) {
        if (ex.getStackTrace().length > 0) {
            StackTraceElement ste = ex.getStackTrace()[0];
            return ste.getClassName() + "." + ste.getMethodName() +
                    "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
        }
        return "unknown";
    }
}
