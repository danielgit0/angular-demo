package com.angular.demo.profile.repository;

import com.angular.demo.profile.repository.entities.Profile;
import com.angular.demo.profile.repository.entities.ProfileType;
import com.angular.demo.profile.repository.filters.ProfileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class ProfilesRepository {

  private final Map<UUID, Profile> store = Collections.synchronizedMap(new LinkedHashMap<>());

  public List<Profile> findAll() {
    return List.copyOf(store.values());
  }

  public Optional<Profile> findById(UUID id) {
    return Optional.ofNullable(store.get(id));
  }

  public List<Profile> findFiltered(ProfileFilter filter) {
    return store.values().stream()
        .filter(
            p -> {
              if (filter.profileType == null) {
                return true;
              }
              ProfileType type = ProfileType.valueOf(filter.profileType.toUpperCase());
              return type.equals(p.getType());
            })
        .toList();
  }

  public List<Profile> findFiltered(ProfileFilter filter, int page, int pageSize) {
    List<Profile> filtered = new ArrayList<>(findFiltered(filter));
    int from = Math.min(page * pageSize, filtered.size());
    int to = Math.min(from + pageSize, filtered.size());
    return filtered.subList(from, to);
  }

  public long countFiltered(ProfileFilter filter) {
    return findFiltered(filter).size();
  }

  public Profile persist(Profile profile) {
    if (profile.getId() == null) {
      profile.setId(UUID.randomUUID());
    }
    store.put(profile.getId(), profile);
    return profile;
  }

  public void delete(Profile profile) {
    store.remove(profile.getId());
  }

  public boolean isPersistent(Profile profile) {
    return profile.getId() != null && store.containsKey(profile.getId());
  }

  public long count() {
    return store.size();
  }

  public List<Profile> findAll(int page, int pageSize) {
    List<Profile> all = new ArrayList<>(store.values());
    int from = Math.min(page * pageSize, all.size());
    int to = Math.min(from + pageSize, all.size());
    return all.subList(from, to);
  }
}
