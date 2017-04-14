package com.algolia.search.responses;

import com.algolia.search.Index;

import java.util.Collections;
import java.util.List;

public class Indices {

  private List<Index.Attributes> items;

  public List<Index.Attributes> getItems() {
    return items == null ? Collections.<Index.Attributes>emptyList() : items;
  }

  @SuppressWarnings("unused")
  public Indices setItems(List<Index.Attributes> items) {
    this.items = items;
    return this;
  }
}

