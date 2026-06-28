package com.angular.demo.profile.model.mappers;

import com.angular.demo.profile.model.ProfileCreateDto;
import com.angular.demo.profile.model.ProfileDto;
import com.angular.demo.profile.model.ProfileTypeDto;
import com.angular.demo.profile.model.ProfileUpdateDto;
import com.angular.demo.profile.repository.entities.Profile;
import com.angular.demo.user.model.UserDto;
import com.example.generated.model.ProfileCreateRequest;
import com.example.generated.model.ProfileResponse;
import com.example.generated.model.ProfileUpdateRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ProfileMapper {

  public ProfileDto toDto(Profile entity, UserDto user) {

    return new ProfileDto(
        entity.getId(),
        entity.getFirstName(),
        entity.getLastName(),
        entity.getNumber(),
        ProfileTypeDto.fromValue(user.type()),
        user.email());
  }

  public List<ProfileDto> toDto(List<Profile> entities, Map<UUID, UserDto> usersMapDto) {
    return entities.stream()
        .map(profile -> toDto(profile, usersMapDto.get(profile.getId())))
        .toList();
  }

  public abstract ProfileResponse toResponse(ProfileDto dto);

  public abstract List<ProfileResponse> toResponse(List<ProfileDto> dtos);

  public abstract ProfileCreateDto toDto(
      UUID id, ProfileTypeDto type, ProfileCreateRequest request);

  public abstract Profile toEntity(ProfileCreateDto dto);

  public abstract ProfileUpdateDto toDto(ProfileUpdateRequest profile);

  public abstract Profile toEntity(ProfileUpdateDto profile);
}
