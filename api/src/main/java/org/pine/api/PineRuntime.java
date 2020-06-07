package org.pine.api;

public interface PineRuntime {

  void shutdown();

  PineLiker newLiker();

  void connect(String address);
}
