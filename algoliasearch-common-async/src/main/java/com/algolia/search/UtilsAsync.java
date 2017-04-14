package com.algolia.search;

import java.util.concurrent.CompletableFuture;

public class UtilsAsync {

  public static <T> CompletableFuture<T> completeExceptionally(Throwable t) {
    CompletableFuture<T> future = new CompletableFuture<>();
    future.completeExceptionally(t);
    return future;
  }

}
