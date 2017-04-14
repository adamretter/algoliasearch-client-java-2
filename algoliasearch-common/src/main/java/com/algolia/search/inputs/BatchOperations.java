package com.algolia.search.inputs;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BatchOperations {

  private final List<? extends BatchOperation> requests;

  public BatchOperations(List<? extends BatchOperation> requests) {
    this.requests = requests;
  }

  @SuppressWarnings("unused")
  public List<? extends BatchOperation> getRequests() {
    return requests;
  }
}
