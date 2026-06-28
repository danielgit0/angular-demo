package com.angular.demo.profile.service;

import com.angular.demo.profile.model.ProfileCreateDto;
import com.angular.demo.profile.model.ProfileDto;
import com.angular.demo.profile.model.ProfileUpdateDto;
import com.angular.demo.profile.repository.filters.ProfileFilter;
import com.angular.demo.utils.pagination.model.PageResultDto;
import java.util.UUID;

public interface ProfilesService {

  PageResultDto<ProfileDto> getProfiles(ProfileFilter filter);

  ProfileDto getProfile(UUID id);

  ProfileDto createProfile(ProfileCreateDto profile);

  ProfileDto updateProfile(ProfileUpdateDto profile);

  void deleteProfile(UUID id);
}
