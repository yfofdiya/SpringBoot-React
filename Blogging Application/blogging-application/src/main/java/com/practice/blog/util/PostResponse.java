package com.practice.blog.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {
    private Object response;
    private int pageNumber;
    private int pageSize;
    private int totalElementsOnPage;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;
    private boolean firstPage;
}
