package com.angular.demo.profile;

import static com.angular.demo.utils.security.Scopes.SCOPE_PROFILE_READ;
import static com.angular.demo.utils.security.Scopes.SCOPE_PROFILE_WRITE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import ch.qos.logback.core.util.StringUtil;
import com.angular.demo.profile.model.ProfileDto;
import com.angular.demo.profile.model.ProfileTypeDto;
import com.angular.demo.profile.model.ProfileUpdateDto;
import com.angular.demo.profile.model.mappers.ProfileMapper;
import com.angular.demo.profile.repository.filters.ProfileFilter;
import com.angular.demo.profile.service.ProfilesService;
import com.angular.demo.utils.pagination.mappers.PaginationMapper;
import com.angular.demo.utils.pagination.model.PageResultDto;
import com.angular.demo.utils.pagination.model.PaginationSettings;
import com.example.generated.api.ProfileApi;
import com.example.generated.model.ProfileCreateRequest;
import com.example.generated.model.ProfileResponse;
import com.example.generated.model.ProfileUpdateRequest;
import com.example.generated.model.ProfilesResponse;
import jakarta.ws.rs.ForbiddenException;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileApiImpl implements ProfileApi {

  private final Jwt currentRequestJwt;
  private final ProfilesService profilesService;
  private final ProfileMapper profileMapper;
  private final PaginationMapper paginationMapper;

  public ProfileApiImpl(
      Jwt currentRequestJwt,
      ProfilesService profilesService,
      ProfileMapper profileMapper,
      PaginationMapper paginationMapper) {
    this.currentRequestJwt = currentRequestJwt;
    this.profilesService = profilesService;
    this.profileMapper = profileMapper;
    this.paginationMapper = paginationMapper;
  }

  @PreAuthorize("hasAuthority('" + SCOPE_PROFILE_READ + "')")
  @Override
  public ResponseEntity<ProfilesResponse> getProfiles(
      final String profileType, final Integer page, final Integer pageSize) {
    ProfileFilter filter = new ProfileFilter();

    if (!StringUtil.isNullOrEmpty(profileType)) {
      filter.profileType = profileType;
    }

    filter.paginationSettings = new PaginationSettings(page, pageSize);

    final PageResultDto<ProfileDto> result = profilesService.getProfiles(filter);

    ProfilesResponse response = new ProfilesResponse();
    response.data(profileMapper.toResponse(result.data));
    response.pagination(paginationMapper.toResponse(result.pagination));
    return ResponseEntity.ok(response);
  }

  @PreAuthorize("hasAuthority('" + SCOPE_PROFILE_READ + "')")
  @Override
  public ResponseEntity<ProfileResponse> getProfile(UUID id) {
    return ResponseEntity.ok(profileMapper.toResponse(profilesService.getProfile(id)));
  }

  @PreAuthorize("hasAuthority('" + SCOPE_PROFILE_WRITE + "')")
  @Override
  public ResponseEntity<ProfileResponse> createProfile(ProfileCreateRequest profileCreateRequest) {
    final UUID profileId = UUID.fromString(currentRequestJwt.getSubject());
    final String userType = currentRequestJwt.getClaimAsString("userType");
    final ProfileTypeDto type = ProfileTypeDto.fromValue(userType);
    return ResponseEntity.status(CREATED)
        .body(
            profileMapper.toResponse(
                profilesService.createProfile(
                    profileMapper.toDto(profileId, type, profileCreateRequest))));
  }

  @PreAuthorize("hasAuthority('" + SCOPE_PROFILE_WRITE + "')")
  @Override
  public ResponseEntity<ProfileResponse> updateProfile(
      UUID id, ProfileUpdateRequest profileUpdateRequest) {
    validateProfileId(id);

    final ProfileUpdateDto profile = profileMapper.toDto(profileUpdateRequest);
    return ResponseEntity.ok(profileMapper.toResponse(profilesService.updateProfile(profile)));
  }

  @PreAuthorize("hasAuthority('" + SCOPE_PROFILE_WRITE + "')")
  @Override
  public ResponseEntity<Void> deleteProfile(UUID id) {
    validateProfileId(id);

    profilesService.deleteProfile(id);
    return ResponseEntity.status(NO_CONTENT).build();
  }

  private void validateProfileId(final UUID id) {
    final UUID profileId = UUID.fromString(currentRequestJwt.getSubject());
    if (!id.equals(profileId)) {
      throw new ForbiddenException(
          "Trying to write a different profile than the authenticated one.");
    }
  }
}
