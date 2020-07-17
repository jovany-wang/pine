package org.pine.api;

public final class Pine {

  private static PineRuntime runtime;

  @SuppressWarnings("unchecked")
  public static void init(String mode, String address,int index) {

    String factoryName;

    if (mode.equals("distkv")) {
      factoryName = "org.pine.distkvimpl.DistkvImplPineRuntimeFactory";
    } else if(mode.equals("redis")) {
      factoryName = "org.pine.redisimpl.RedisImplPineRuntimeFactory";
    } else {
      factoryName = "org.pine.distkvimpl.RedisImplPineRuntimeFactory";
    }

    try {
      Class clz = Class.forName(factoryName);
      PineRuntimeFactory factory = (PineRuntimeFactory) clz.getDeclaredConstructor().newInstance();
      if (runtime == null) {
        runtime = factory.createPineRuntime();
        runtime.connect(address);
        runtime.select(index);
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
