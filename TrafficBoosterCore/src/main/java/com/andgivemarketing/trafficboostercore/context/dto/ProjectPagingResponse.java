package com.andgivemarketing.trafficboostercore.context.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPagingResponse {
    private List<ProjectListDTO> content;
    private int page;
    private int pageSize;
    private long totalElements;
    private int totalPages;
}