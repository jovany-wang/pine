package org.pine.distkvimpl;

import com.distkv.client.DefaultDistkvClient;
import com.distkv.client.DistkvClient;
import com.distkv.common.exception.DistkvException;
import org.pine.api.PineLiker;
import org.pine.api.PineRuntime;

public class PineRuntimeDistkvImpl implements PineRuntime {

  /**
   * The distkv sync client.
   */
  private DistkvClient distkvClient;


  public void shutdown() {
    try {
      distkvClient.disconnect();
    } catch (DistkvException e) {
      throw new RuntimeException(
          String.format("Failed shutdown the client : %s", e.getMessage()));
    }
  }

  public PineLiker newLiker() {
    return new PineLikerDistkvImpl(distkvClient);
  }

  public void connect(String address) {
    distkvClient = new DefaultDistkvClient(address);
  }
}
