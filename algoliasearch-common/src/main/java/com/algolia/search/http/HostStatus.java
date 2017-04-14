package com.algolia.search.http;

import java.util.Date;

class HostStatus {

  private final boolean isUp;
  private final long lastModifiedTimestamp;
  private final long hostDownTimeout;

  HostStatus(long hostDownTimeout, boolean isUp, Date lastModified) {
    this.hostDownTimeout = hostDownTimeout;
    this.isUp = isUp;
    this.lastModifiedTimestamp = lastModified.getTime();
  }

  boolean isUpOrCouldBeRetried(Date now) {
    return isUp || Math.abs(now.getTime() - lastModifiedTimestamp) >= hostDownTimeout;
  }

}
