package com.angular.demo.profile.service;

import com.angular.demo.profile.model.ProfileCreateDto;
import com.angular.demo.profile.model.ProfileDto;
import com.angular.demo.profile.model.ProfileUpdateDto;
import com.angular.demo.profile.model.mappers.ProfileMapper;
import com.angular.demo.profile.repository.ProfilesRepository;
import com.angular.demo.profile.repository.entities.Profile;
import com.angular.demo.profile.repository.filters.ProfileFilter;
import com.angular.demo.user.model.UserDto;
import com.angular.demo.user.service.UsersService;
import com.angular.demo.utils.pagination.mappers.PaginationMapper;
import com.angular.demo.utils.pagination.model.PageResultDto;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ProfilesServiceImpl implements ProfilesService {

  private final ProfilesRepository profilesRepository;
  private final UsersService usersService;
  private final ProfileMapper profileMapper;
  private final PaginationMapper paginationMapper;

  public ProfilesServiceImpl(
      ProfilesRepository profilesRepository,
      UsersService usersService,
      ProfileMapper profileMapper,
      PaginationMapper paginationMapper) {
    this.profilesRepository = profilesRepository;
    this.usersService = usersService;
    this.profileMapper = profileMapper;
    this.paginationMapper = paginationMapper;
  }

  @Override
  public PageResultDto<ProfileDto> getProfiles(final ProfileFilter filter) {
    final int page = filter.paginationSettings.page();
    final int pageSize = filter.paginationSettings.pageSize();

    final List<Profile> profiles = profilesRepository.findFiltered(filter, page, pageSize);
    final long totalCount = profilesRepository.countFiltered(filter);
    final int pageCount = (int) Math.ceil((double) totalCount / pageSize);

    final List<UUID> profilesIds = profiles.stream().map(Profile::getId).toList();
    final List<ProfileDto> profileDtos =
        profileMapper.toDto(profiles, usersService.getUsersMap(profilesIds));

    return new PageResultDto<>(profileDtos, paginationMapper.toDto(totalCount, pageCount, page));
  }

  @Override
  public ProfileDto getProfile(UUID id) {
    final Profile profile =
        profilesRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Profile [%s] not found".formatted(id)));

    final UserDto userDto = usersService.getUser(id);
    return profileMapper.toDto(profile, userDto);
  }

  @Override
  public ProfileDto createProfile(ProfileCreateDto profile) {
    final UUID id = profile.id();
    final UserDto userDto = usersService.getUser(id);
    final Profile newProfile = profileMapper.toEntity(profile);

    profilesRepository.persist(newProfile);

    return profileMapper.toDto(
        profilesRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Profile [%s] not found".formatted(id))),
        userDto);
  }

  @Override
  public ProfileDto updateProfile(ProfileUpdateDto profile) {
    final UUID id = profile.id();

    final Profile profileUpdate =
        profilesRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Profile [%s] not found".formatted(id)));

    final Profile updated = profileMapper.toEntity(profile);
    profileUpdate.setId(updated.getId());
    profileUpdate.setFirstName(updated.getFirstName());
    profileUpdate.setLastName(updated.getLastName());
    profileUpdate.setNumber(updated.getNumber());
    profileUpdate.setType(updated.getType());

    profilesRepository.persist(profileUpdate);

    final UserDto user = usersService.getUser(id);
    return profileMapper.toDto(profileUpdate, user);
  }

  @Override
  public void deleteProfile(UUID id) {
    final Profile profile =
        profilesRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Profile [%s] not found".formatted(id)));

    if (profilesRepository.isPersistent(profile)) {
      profilesRepository.delete(profile);
    } else {
      throw new RuntimeException("The profile [%s] is not persistent".formatted(id));
    }
  }
}
