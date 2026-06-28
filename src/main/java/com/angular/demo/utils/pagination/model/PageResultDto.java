package com.angular.demo.utils.pagination.model;

import java.util.List;

public class PageResultDto<T> {

  public List<T> data;

  public PaginationDto pagination;

  public PageResultDto(final List<T> data, final PaginationDto pagination) {
    this.data = data;
    this.pagination = pagination;
  }
}
