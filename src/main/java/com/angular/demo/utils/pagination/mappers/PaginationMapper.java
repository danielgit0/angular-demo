package com.angular.demo.utils.pagination.mappers;

import com.angular.demo.utils.pagination.model.PaginationDto;
import com.example.generated.model.PaginationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaginationMapper {

  default PaginationDto toDto(Long count, Integer pageCount, Integer page) {
    return new PaginationDto(count, pageCount, page - 1, page, page + 1);
  }

  PaginationResponse toResponse(PaginationDto dto);
}
