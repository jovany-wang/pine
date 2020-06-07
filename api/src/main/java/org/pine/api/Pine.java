package org.pine.api;

public final class Pine {

  private static PineRuntime runtime;

  public static void init(String mode) {

    String factoryName;

    if (mode.equals("distkv")) {
      factoryName = "com.distkv.pine.distkvimpl.DistkvImplPineRuntimeFactory";
    } else {
      factoryName = "com.distkv.pine.redisimpl.RedisImplPineRuntimeFactory";
    }

    try {
      Class clz = Class.forName(factoryName);
      PineRuntimeFactory factory = (PineRuntimeFactory) clz.getDeclaredConstructor().newInstance();
      if (runtime == null) {
        runtime = factory.createPineRuntime();
      }
    } catch (Exception e) {
      throw new RuntimeException("Failed to initialize Pine runtime.", e);
    }

  }

  public static void shutdown() {
    if (runtime != null) {
      runtime.shutdown();
    }
  }

  public static PineLiker newLiker() {
    return runtime.newLiker();
  }

}
