package com.angular.demo.company;

import static com.angular.demo.utils.security.Scopes.SCOPE_COMPANY_READ;
import static com.angular.demo.utils.security.Scopes.SCOPE_COMPANY_WRITE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.angular.demo.company.model.CompanyCreateDto;
import com.angular.demo.company.model.CompanyDto;
import com.angular.demo.company.model.CompanyUpdateDto;
import com.angular.demo.company.model.mappers.CompanyMapper;
import com.angular.demo.company.repository.filters.CompanyFilter;
import com.angular.demo.company.service.CompaniesService;
import com.angular.demo.utils.pagination.mappers.PaginationMapper;
import com.angular.demo.utils.pagination.model.PageResultDto;
import com.angular.demo.utils.pagination.model.PaginationSettings;
import com.example.generated.api.CompanyApi;
import com.example.generated.model.CompaniesResponse;
import com.example.generated.model.CompanyCreateRequest;
import com.example.generated.model.CompanyResponse;
import com.example.generated.model.CompanyUpdateRequest;
import java.util.Objects;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyApiImpl implements CompanyApi {

  private final Jwt currentRequestJwt;
  private final CompaniesService companiesService;
  private final CompanyMapper companyMapper;
  private final PaginationMapper paginationMapper;

  public CompanyApiImpl(
      Jwt currentRequestJwt,
      CompaniesService companiesService,
      CompanyMapper companyMapper,
      PaginationMapper paginationMapper) {
    this.currentRequestJwt = currentRequestJwt;
    this.companiesService = companiesService;
    this.companyMapper = companyMapper;
    this.paginationMapper = paginationMapper;
  }

  @PreAuthorize("hasAuthority('" + SCOPE_COMPANY_READ + "')")
  @Override
  public ResponseEntity<CompaniesResponse> getCompanies(Integer page, Integer pageSize) {
    CompanyFilter filter = new CompanyFilter();
    filter.paginationSettings = new PaginationSettings(page, pageSize);

    final PageResultDto<CompanyDto> result = companiesService.getCompanies(filter);

    CompaniesResponse response = new CompaniesResponse();
    response.setData(companyMapper.toResponse(result.data));
    response.setPagination(paginationMapper.toResponse(result.pagination));
    return ResponseEntity.ok(response);
  }

  @PreAuthorize("hasAuthority('" + SCOPE_COMPANY_READ + "')")
  @Override
  public ResponseEntity<CompanyResponse> getCompany(UUID id) {
    final UUID profileId = UUID.fromString(Objects.requireNonNull(currentRequestJwt.getSubject()));
    return ResponseEntity.ok(companyMapper.toResponse(companiesService.getCompany(id, profileId)));
  }

  @PreAuthorize("hasAuthority('" + SCOPE_COMPANY_WRITE + "')")
  @Override
  public ResponseEntity<CompanyResponse> createCompany(CompanyCreateRequest companyCreateRequest) {
    final UUID profileId = UUID.fromString(Objects.requireNonNull(currentRequestJwt.getSubject()));
    final CompanyCreateDto company = companyMapper.toDto(profileId, companyCreateRequest);

    return ResponseEntity.status(CREATED)
        .body(companyMapper.toResponse(companiesService.createCompany(company)));
  }

  @PreAuthorize("hasAuthority('" + SCOPE_COMPANY_WRITE + "')")
  @Override
  public ResponseEntity<CompanyResponse> updateCompany(
      UUID id, CompanyUpdateRequest companyUpdateRequest) {
    final UUID profileId = UUID.fromString(Objects.requireNonNull(currentRequestJwt.getSubject()));
    final CompanyUpdateDto company = companyMapper.toDto(profileId, companyUpdateRequest);

    return ResponseEntity.ok(companyMapper.toResponse(companiesService.updateCompany(company)));
  }

  @PreAuthorize("hasAuthority('" + SCOPE_COMPANY_WRITE + "')")
  @Override
  public ResponseEntity<Void> deleteCompany(UUID id) {
    final UUID profileId = UUID.fromString(Objects.requireNonNull(currentRequestJwt.getSubject()));
    companiesService.deleteCompany(id, profileId);
    return ResponseEntity.status(NO_CONTENT).build();
  }
}
