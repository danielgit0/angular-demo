package com.angular.demo.company.repository;

import com.angular.demo.company.repository.entities.Company;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class CompaniesRepository {

  private final Map<UUID, Company> store = Collections.synchronizedMap(new LinkedHashMap<>());

  public List<Company> findAll() {
    return List.copyOf(store.values());
  }

  public Optional<Company> findById(UUID id) {
    return Optional.ofNullable(store.get(id));
  }

  public List<Company> findByProfileId(UUID profileId) {
    return store.values().stream()
        .filter(c -> c.getProfile() != null && profileId.equals(c.getProfile().getId()))
        .toList();
  }

  public Optional<Company> findByIdAndProfileId(UUID id, UUID profileId) {
    return findById(id)
        .filter(c -> c.getProfile() != null && profileId.equals(c.getProfile().getId()));
  }

  public Company persist(Company company) {
    if (company.getId() == null) {
      company.setId(UUID.randomUUID());
    }
    store.put(company.getId(), company);
    return company;
  }

  public void delete(Company company) {
    store.remove(company.getId());
  }

  public boolean isPersistent(Company company) {
    return company.getId() != null && store.containsKey(company.getId());
  }

  public long count() {
    return store.size();
  }

  public List<Company> findAll(int page, int pageSize) {
    List<Company> all = new ArrayList<>(store.values());
    int from = Math.min(page * pageSize, all.size());
    int to = Math.min(from + pageSize, all.size());
    return all.subList(from, to);
  }
}
