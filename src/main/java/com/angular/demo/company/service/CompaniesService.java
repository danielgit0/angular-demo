package com.angular.demo.company.service;

import com.angular.demo.company.model.CompanyCreateDto;
import com.angular.demo.company.model.CompanyDto;
import com.angular.demo.company.model.CompanyUpdateDto;
import com.angular.demo.company.repository.filters.CompanyFilter;
import com.angular.demo.utils.pagination.model.PageResultDto;
import java.util.UUID;

public interface CompaniesService {

  PageResultDto<CompanyDto> getCompanies(CompanyFilter filter);

  CompanyDto getCompany(UUID id, UUID profileId);

  CompanyDto createCompany(CompanyCreateDto company);

  CompanyDto updateCompany(CompanyUpdateDto company);

  void deleteCompany(UUID id, UUID profileId);

  void deleteProfileCompany(UUID profileId);
}
