package com.algolia.search;

//While waiting for a full support of java8
public interface AlgoliaFunction<T, R> {

  /**
   * Applies this function to the given argument.
   *
   * @param t the function argument
   * @return the function result
   */
  R apply(T t);
}
