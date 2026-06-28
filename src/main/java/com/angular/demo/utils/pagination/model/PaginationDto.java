package com.angular.demo.utils.pagination.model;

public record PaginationDto(
    Long totalRecords,
    Integer totalPages,
    Integer previousPage,
    Integer currentPage,
    Integer nextPage) {}
