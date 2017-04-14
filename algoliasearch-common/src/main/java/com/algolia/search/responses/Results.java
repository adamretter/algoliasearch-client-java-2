package com.algolia.search.responses;

import java.util.Collections;
import java.util.List;

public class Results<T> {

  private List<T> results;

  public List<T> getResults() {
    return results == null ? Collections.<T>emptyList() : results;
  }

  @SuppressWarnings("unused")
  public Results setResults(List<T> results) {
    this.results = results;
    return this;
  }
}
