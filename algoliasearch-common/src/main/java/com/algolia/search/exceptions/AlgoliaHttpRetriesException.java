package com.algolia.search.exceptions;

import com.algolia.search.AlgoliaFunction;
import com.algolia.search.Utils;

import java.util.List;

/**
 * Algolia Exception if all retries failed
 */
public class AlgoliaHttpRetriesException extends AlgoliaException {

  /**
   * List of exception if all retries failed
   */
  private List<AlgoliaIOException> ioExceptionList;

  public AlgoliaHttpRetriesException(String message, List<AlgoliaIOException> ioExceptionList) {
    super(message + ", exceptions: [" + Utils.join(",", Utils.map(ioExceptionList, new AlgoliaFunction<AlgoliaIOException, String>() {
      @Override
      public String apply(AlgoliaIOException e) {
        return e.getMessage();
      }
    })) + "]");
    this.ioExceptionList = ioExceptionList;
  }

  @SuppressWarnings("unused")
  public List<AlgoliaIOException> getIoExceptionList() {
    return ioExceptionList;
  }
}
