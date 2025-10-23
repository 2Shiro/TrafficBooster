package com.andgivemarketing.trafficboostercore.global.exception;

/**
 1. Enum을 이용해서 예외를 체계적으로 관리
 2. @RestControllerAdvice 를 이용해서 에러 반환을 전역으로 관리해 유지보수 및 재사용성 증가
 3. 근데 GlobalExceptionHandler의 @ExceptionHandler를 class를 파라미터로 받음
 4. enum을 직접 throw하면 @ExceptionHandler가 감지를 못함
 5. 그래서 CustomException 클래스를 만들어서 Enum을 감싸서 던짐
 */

// 코드, 에러 이름, 에러메세지 이 3개의 정보를 들고 있는 이넘
public enum ExceptionCode {

    // ALL
    INVALID_REQUEST(400, "INVALID_REQUEST", "올바르지 않은 요청입니다."),

    // PROJECT
    DUPLICATE_NAME(409,"DUPLICATE_NAME", "이미 사용 중인 이름입니다."),
    PROJECT_NOT_FOUND(404, "PROJECT_NOT_FOUND", "요청하신 프로젝트를 찾을 수 없습니다."),

    // TRAFFICSOURCE
    TRAFFIC_SOURCE_NOT_FOUND(404, "TRAFFIC_SOURCE_NOT_FOUND", "요청하신 유입경로를 찾을 수 없습니다."),

    // TRAFFICSOURCERECORD

    ;

    // 에러 속성(인스턴스 필드)
    private final int code;
    private final String name;
    private final String message;

    // 생성자(enum 상수가 생성될 때 호출되는 생성자)
    ExceptionCode(int code, String name, String message) {
        this.code = code;
        this.name = name;
        this.message = message;
    }

    // 접근자(getter)
    public int getCode() {return code;}
    public String getName() {return name;}
    public String getMessage() {return message;}
}
