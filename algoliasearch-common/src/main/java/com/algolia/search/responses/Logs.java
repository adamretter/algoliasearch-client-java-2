package com.algolia.search.responses;

import com.algolia.search.objects.Log;

import java.util.Collections;
import java.util.List;

public class Logs {

  private List<Log> logs;

  public List<Log> getLogs() {
    return logs == null ? Collections.<Log>emptyList() : logs;
  }

  @SuppressWarnings("unused")
  public Logs setLogs(List<Log> logs) {
    this.logs = logs;
    return this;
  }
}
