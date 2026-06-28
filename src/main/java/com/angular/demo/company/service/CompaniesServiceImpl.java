package com.angular.demo.company.service;

import com.angular.demo.company.model.CompanyCreateDto;
import com.angular.demo.company.model.CompanyDto;
import com.angular.demo.company.model.CompanyUpdateDto;
import com.angular.demo.company.model.mappers.CompanyMapper;
import com.angular.demo.company.repository.CompaniesRepository;
import com.angular.demo.company.repository.entities.Company;
import com.angular.demo.company.repository.filters.CompanyFilter;
import com.angular.demo.profile.repository.ProfilesRepository;
import com.angular.demo.profile.repository.entities.Profile;
import com.angular.demo.user.model.UserDto;
import com.angular.demo.user.service.UsersService;
import com.angular.demo.utils.pagination.mappers.PaginationMapper;
import com.angular.demo.utils.pagination.model.PageResultDto;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class CompaniesServiceImpl implements CompaniesService {

  private final CompaniesRepository companiesRepository;
  private final ProfilesRepository profilesRepository;
  private final UsersService usersService;
  private final CompanyMapper companyMapper;
  private final PaginationMapper paginationMapper;

  public CompaniesServiceImpl(
      CompaniesRepository companiesRepository,
      ProfilesRepository profilesRepository,
      UsersService usersService,
      CompanyMapper companyMapper,
      PaginationMapper paginationMapper) {
    this.companiesRepository = companiesRepository;
    this.profilesRepository = profilesRepository;
    this.usersService = usersService;
    this.companyMapper = companyMapper;
    this.paginationMapper = paginationMapper;
  }

  @Override
  public PageResultDto<CompanyDto> getCompanies(CompanyFilter filter) {
    final int page = filter.paginationSettings.page();
    final int pageSize = filter.paginationSettings.pageSize();

    final List<Company> companies = companiesRepository.findAll(page, pageSize);
    final long totalCount = companiesRepository.count();
    final int pageCount = (int) Math.ceil((double) totalCount / pageSize);

    final List<UUID> profilesIds =
        companies.stream().map(company -> company.getProfile().getId()).toList();
    final Map<UUID, UserDto> users = usersService.getUsersMap(profilesIds);

    return new PageResultDto<>(
        companyMapper.toDto(companies, users), paginationMapper.toDto(totalCount, pageCount, page));
  }

  @Override
  public CompanyDto getCompany(UUID id, UUID profileId) throws NotFoundException {
    final Company company =
        companiesRepository
            .findByIdAndProfileId(id, profileId)
            .orElseThrow(() -> new NotFoundException("Company [%s] not found".formatted(id)));

    final UserDto user = usersService.getUser(company.getProfile().getId());
    return companyMapper.toDto(company, user);
  }

  @Override
  public CompanyDto createCompany(CompanyCreateDto companyDto) {
    final Profile profile =
        profilesRepository
            .findById(companyDto.profileId())
            .orElseThrow(
                () ->
                    new NotFoundException(
                        "Profile [%s] not found".formatted(companyDto.profileId())));

    Company newCompany = companyMapper.toEntity(profile, companyDto);
    companiesRepository.persist(newCompany);

    final UserDto user = usersService.getUser(newCompany.getProfile().getId());
    return companyMapper.toDto(newCompany, user);
  }

  @Override
  public CompanyDto updateCompany(CompanyUpdateDto companyDto) {
    final Company companyUpdate =
        companiesRepository
            .findByIdAndProfileId(companyDto.id(), companyDto.profileId())
            .orElseThrow(
                () -> new NotFoundException("Company [%s] not found".formatted(companyDto.id())));

    final Profile profile =
        profilesRepository
            .findById(companyDto.profileId())
            .orElseThrow(
                () ->
                    new NotFoundException(
                        "Profile [%s] not found".formatted(companyDto.profileId())));

    companyUpdate.setName(companyDto.name());
    companyUpdate.setProfile(profile);
    companyUpdate.setAddress(companyDto.address());

    final UserDto user = usersService.getUser(companyUpdate.getProfile().getId());
    return companyMapper.toDto(companyUpdate, user);
  }

  @Override
  public void deleteCompany(UUID id, UUID profileId) {
    final Company company =
        companiesRepository
            .findByIdAndProfileId(id, profileId)
            .orElseThrow(() -> new NotFoundException("Company [%s] not found".formatted(id)));

    if (companiesRepository.isPersistent(company)) {
      companiesRepository.delete(company);
    } else {
      throw new RuntimeException("The company [%s] is not persistent".formatted(id));
    }
  }

  @Override
  public void deleteProfileCompany(UUID profileId) {
    companiesRepository
        .findByProfileId(profileId)
        .forEach(company -> deleteCompany(company.getId(), profileId));
  }
}
