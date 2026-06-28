package com.angular.demo.dummy.error;

public class ResourceNotFoundException extends RuntimeException {
  private final Long resourceId;

  public ResourceNotFoundException(Long resourceId) {
    super("Resource with id " + resourceId + " not found.");
    this.resourceId = resourceId;
  }

  public Long getResourceId() {
    return resourceId;
  }
}
