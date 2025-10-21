package com.andgivemarketing.trafficbooster.global.exception;

/**
 * @ExceptionHandler(value=CustomException.class)의 어노테이션 파라미터는
 * Enum을 넣어주면 감지 못한다.
 * .class를 보면 알겠지만 class로 넣어줘야 한다.
 * 하지만 에러 코드에 대한 정보는 Enum에 있고 Enum을 class로 변환시켜 주는 클래스
 */
public class CustomException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public CustomException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    public ExceptionCode getExceptionCode() {return exceptionCode;}
    public int getCode() {return exceptionCode.getCode();}
    public String getName() {return exceptionCode.getName();}
    public String getMessage() {return exceptionCode.getMessage();}
}
