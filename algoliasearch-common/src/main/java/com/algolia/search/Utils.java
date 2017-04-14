package com.algolia.search;

import com.algolia.search.exceptions.AlgoliaException;
import com.algolia.search.inputs.BatchOperation;
import com.algolia.search.objects.Query;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Nonnull;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class Utils {

  private static String hmac(String key, String msg) throws AlgoliaException {
    Mac hmac;
    try {
      hmac = Mac.getInstance("HmacSHA256");
      hmac.init(new SecretKeySpec(key.getBytes(), "HmacSHA256"));
    } catch (NoSuchAlgorithmException e) {
      throw new AlgoliaException("Can not find HmacSHA256 algorithm", e);
    } catch (InvalidKeyException e) {
      throw new AlgoliaException("Can not init HmacSHA256 algorithm", e);
    }
    byte[] rawHmac = hmac.doFinal(msg.getBytes());
    StringBuilder sb = new StringBuilder(rawHmac.length * 2);
    for (byte b : rawHmac) {
      sb.append(String.format("%02x", b & 0xff));
    }
    return sb.toString();
  }

  static String generateSecuredApiKey(@Nonnull String privateApiKey, @Nonnull Query query, String userToken) throws AlgoliaException {
    if (userToken != null && userToken.length() > 0) {
      query.setUserToken(userToken);
    }
    String queryStr = query.toParam();
    String key = hmac(privateApiKey, queryStr);

    return new String(Base64.getEncoder().encode(String.format("%s%s", key, queryStr).getBytes(Charset.forName("UTF8"))));
  }

  public static <T> T parseAs(ObjectMapper mapper, Reader content, Class<T> klass) throws IOException {
    return mapper.readValue(content, mapper.getTypeFactory().constructType(klass));
  }

  public static <T> T parseAs(ObjectMapper mapper, Reader content, JavaType type) throws IOException {
    return mapper.readValue(content, type);
  }

  public static <T> CompletableFuture<T> completeExceptionally(Throwable t) {
    CompletableFuture<T> future = new CompletableFuture<>();
    future.completeExceptionally(t);
    return future;
  }

  public static String join(String delimiter, List<String> list) {
    switch (list.size()) {
      case 0: return "";
      case 1: return list.get(0);
      default:
        StringBuilder result = new StringBuilder(list.get(0));
        for (int i = 1; i < list.size(); i++) {
          result.append(delimiter).append(list.get(i));
        }
        return result.toString();
    }
  }

  public static Long max(Collection<Long> values) {
    return null;
  }

  public static <T> List<T> filter(List<T> list, AlgoliaFunction<T, Boolean> filter) {
    List<T> result = new ArrayList<>();
    for (T t : list) {
      if(filter.apply(t)) {
        result.add(t);
      }
    }
    return result;
  }

  public static <T> Boolean anyMatch(List<T> list, AlgoliaFunction<T, Boolean> matcher) {
    for (T t : list) {
      if(matcher.apply(t)) {
        return true;
      }
    }
    return false;
  }

  public static <T> Boolean allMatch(List<T> list, AlgoliaFunction<T, Boolean> matcher) {
    for (T t : list) {
      if(!matcher.apply(t)) {
        return false;
      }
    }
    return true;
  }

  public static <T, R> List<R> map(Iterable<T> list, AlgoliaFunction<T, R> function) {
    List<R> result = new ArrayList<>();
    for (T t : list) {
      result.add(function.apply(t));
    }
    return result;
  }

}
