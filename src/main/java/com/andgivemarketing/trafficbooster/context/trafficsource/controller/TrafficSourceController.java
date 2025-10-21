package com.andgivemarketing.trafficbooster.context.trafficsource.controller;

import com.andgivemarketing.trafficbooster.context.trafficsource.dto.TrafficSourceDTO;
import com.andgivemarketing.trafficbooster.context.trafficsource.entity.TrafficSourceEntity;
import com.andgivemarketing.trafficbooster.context.trafficsource.service.TrafficSourceService;
import com.andgivemarketing.trafficbooster.global.dto.ErrorResponse;
import com.andgivemarketing.trafficbooster.global.utils.GsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TrafficSourceController {

    private final TrafficSourceService trafficSourceService;

    /**
     * 유입경로 생성
     * @param trafficSourceDTO  유입경로 정보
     * @return                  유입경로 id
     */
    @PostMapping("/trafficsource/create")
    public ResponseEntity<?> createTrafficSource(@RequestBody(required = false) TrafficSourceDTO trafficSourceDTO) {

        if (trafficSourceDTO == null
                || trafficSourceDTO.getProjectId() == null
                || trafficSourceDTO.getTrafficSourcePath() == null || trafficSourceDTO.getTrafficSourcePath().isBlank()
                || trafficSourceDTO.getStayDurationTime() == null) {
            return ResponseEntity.status(400).body(new ErrorResponse(new ErrorResponse.ErrorDetails("올바르지 않은 요청입니다.")));
        }

        trafficSourceService.createTrafficSource(trafficSourceDTO);

        return ResponseEntity.ok(trafficSourceDTO.getId());
    }

    /**
     * 유입경로 조회
     * @param id    유입경로 id
     * @return      유입경로 정보
     */
    @GetMapping("/trafficsource/get")
    public ResponseEntity<?> getTrafficSource(@RequestParam(name = "id") Long id) {

        TrafficSourceDTO trafficSourceDTO = trafficSourceService.getTrafficSource(id);

        if (trafficSourceDTO == null) {
            return ResponseEntity.status(404).body(new ErrorResponse(new ErrorResponse.ErrorDetails("요청하신 유입경로를 찾을 수 없습니다.")));
        }

        return ResponseEntity.ok(GsonUtils.gson.toJson(trafficSourceDTO));
    }

    /**
     * 유입경로 수정
     * @param trafficSourceDTO  유입경로 정보
     * @return                  유입경로 id
     */
    @PutMapping("/trafficsource/update")
    public ResponseEntity<?> updateTrafficSource(@RequestBody(required = false) TrafficSourceDTO trafficSourceDTO) {

        if (trafficSourceDTO == null
                || trafficSourceDTO.getProjectId() == null
                || trafficSourceDTO.getTrafficSourcePath() == null || trafficSourceDTO.getTrafficSourcePath().isBlank()
                || trafficSourceDTO.getStayDurationTime() == null) {
            return ResponseEntity.status(400).body(new ErrorResponse(new ErrorResponse.ErrorDetails("올바르지 않은 요청입니다.")));
        }

        // 수정할 유입경로 조회
        TrafficSourceEntity trafficSourceEntity = trafficSourceService.findTrafficSourceById(trafficSourceDTO.getId());
        if (trafficSourceEntity == null) {
            return ResponseEntity.status(404).body(new ErrorResponse(new ErrorResponse.ErrorDetails("요청하신 유입경로를 찾을 수 없습니다.")));
        }

        trafficSourceService.updateTrafficSource(trafficSourceDTO);

        return ResponseEntity.ok(trafficSourceDTO.getId());
    }

    /**
     * 유입경로 삭제
     * @param id    유입경로 id
     * @return
     */
    @DeleteMapping("/trafficsource/delete")
    public ResponseEntity<?> deleteTrafficSource(@RequestParam(name = "id") Long id) {

        // 삭제할 유입경로 요청 id 확인
        if (id == null) {
            return ResponseEntity.status(400).body(new ErrorResponse(new ErrorResponse.ErrorDetails("올바르지 않은 요청입니다.")));
        }

        // 유입경로 존재 여부 확인
        if (trafficSourceService.findTrafficSourceById(id) == null) {
            return ResponseEntity.status(404).body(new ErrorResponse(new ErrorResponse.ErrorDetails("요청하신 유입경로를 찾을 수 없습니다.")));
        }

        trafficSourceService.deleteTrafficSource(id);

        return ResponseEntity.ok().build();
    }

}
