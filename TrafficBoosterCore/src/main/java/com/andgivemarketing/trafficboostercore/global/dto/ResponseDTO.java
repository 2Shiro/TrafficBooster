package com.andgivemarketing.trafficboostercore.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.time.ZoneId;

/**
 * API 요청의 반환값을 일관성 있게 관리하기 위한 객체
 * [사용법]
 * 성공 응답 : ResponseEntity.ok.body(ResponseDTO.success(반환값));
 * 실패 응답 : ResponseEntity.status(응답코드).body(ResponseDTO.failure(응답코드, 에러 이름, 에러 메세지));
 */

@Getter
@Builder
@RequiredArgsConstructor(staticName = "of")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> {
    private final T data;          // 성공 시 응답 데이터
    private final ErrorInfo error; // 실패 시 에러 정보
    private final Meta meta;       // 공통 메타 정보

    @Getter
    @Builder
    @RequiredArgsConstructor(staticName = "of")
    public static class ErrorInfo {
        private final int code;         // HTTP status code
        private final String name;      // 비즈니스 에러 코드
        private final String message;   // 에러 메시지
    }

    @Getter
    @Builder
    public static class Meta {
        private final String serverTime;
        private final String location;

        public static Meta of(String serverTime) {
            return new Meta(serverTime, null);
        }

        public static Meta of(String serverTime, String location) {
            return new Meta(serverTime, location);
        }
    }

    // 성공 응답
    public static <T> ResponseDTO<T> success(T data) {
        return ResponseDTO.<T>builder()
                .data(data)
                .meta(Meta.of(OffsetDateTime.now(ZoneId.of("Asia/Seoul")).toString()))
                .build();
    }

    // 실패 응답
    public static <T> ResponseDTO<T> failure(int code, String name, String message, String location) {
        return ResponseDTO.<T>builder()
                .error(ErrorInfo.of(code, name, message))
                .meta(Meta.of(
                        OffsetDateTime.now(ZoneId.of("Asia/Seoul")).toString(),
                        location
                ))
                .build();
    }
}