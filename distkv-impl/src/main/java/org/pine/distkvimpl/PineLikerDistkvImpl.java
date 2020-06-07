package org.pine.distkvimpl;

import com.distkv.client.DistkvClient;
import org.pine.api.LikerTopic;
import org.pine.api.PineLiker;

public class PineLikerDistkvImpl extends AbstractPineHandle implements PineLiker {

  private static final String COMPONENT_TYPE = "LIKER";

  private DistkvClient distkvClient;

  public PineLikerDistkvImpl(DistkvClient distkvClient) {
    this.distkvClient = distkvClient;
  }

  protected String getComponentType() {
    return COMPONENT_TYPE;
  }


  public LikerTopic topic(String topic) {
    return new LikerTopicDistkvImpl.Builder()
        .setTopicKey(getKey(topic))
        .setDistkvClient(distkvClient)
        .build();
  }

  private String getKey(String topic) {
    return String.format("%s_%s", super.getKey(), topic);
  }
}
