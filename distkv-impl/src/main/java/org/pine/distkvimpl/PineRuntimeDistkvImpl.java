package org.pine.distkvimpl;

import org.pine.api.PineLiker;
import org.pine.api.PineRuntime;

public class PineRuntimeDistkvImpl implements PineRuntime {

  public void shutdown() {

  }

  public PineLiker newLiker() {
    return new PineLikerDistkvImpl();
  }
}
