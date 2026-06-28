package com.angular.demo.company.model.mappers;

import com.angular.demo.company.model.CompanyContactPersonDto;
import com.angular.demo.company.model.CompanyCreateDto;
import com.angular.demo.company.model.CompanyDto;
import com.angular.demo.company.model.CompanyUpdateDto;
import com.angular.demo.company.repository.entities.Company;
import com.angular.demo.profile.repository.entities.Profile;
import com.angular.demo.user.model.UserDto;
import com.angular.demo.user.model.mappers.UserMapper;
import com.example.generated.model.CompanyCreateRequest;
import com.example.generated.model.CompanyResponse;
import com.example.generated.model.CompanyUpdateRequest;
import com.oracle.svm.core.annotate.Inject;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {UserMapper.class})
public abstract class CompanyMapper {

  @Inject UserMapper userMapper;

  public CompanyDto toDto(Company entity, UserDto user) {
    return new CompanyDto(
        entity.getId(),
        entity.getName(),
        entity.getAddress(),
        new CompanyContactPersonDto(
            entity.getProfile().getId(),
            entity.getProfile().getFirstName() + " " + entity.getProfile().getLastName(),
            entity.getProfile().getNumber(),
            user.email()));
  }

  public List<CompanyDto> toDto(List<Company> entities, Map<UUID, UserDto> usersMapDto) {
    return entities.stream()
        .map(company -> toDto(company, usersMapDto.get(company.getProfile().getId())))
        .toList();
  }

  public abstract CompanyResponse toResponse(CompanyDto dto);

  public abstract List<CompanyResponse> toResponse(List<CompanyDto> dtos);

  public CompanyCreateDto toDto(UUID profileId, CompanyCreateRequest request) {
    return new CompanyCreateDto(profileId, request.getName(), request.getAddress());
  }

  public CompanyUpdateDto toDto(UUID profileId, CompanyUpdateRequest request) {
    return new CompanyUpdateDto(
        profileId, request.getId(), request.getName(), request.getAddress());
  }

  public Company toEntity(Profile profile, CompanyCreateDto dto) {
    Company entity = new Company();
    entity.setName(dto.name());
    entity.setAddress(dto.address());
    entity.setProfile(profile);
    return entity;
  }

  public Company toEntity(Profile profile, CompanyUpdateDto dto) {
    Company entity = new Company();
    entity.setId(dto.id());
    entity.setName(dto.name());
    entity.setAddress(dto.address());
    entity.setProfile(profile);
    return entity;
  }
}
