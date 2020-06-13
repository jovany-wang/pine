package org.pine.redisimpl;

import io.lettuce.core.RedisClient;
import org.pine.api.LikerTopic;
import org.pine.api.PineLiker;

public class PineLikerRedisImpl extends AbstractPineHandle implements PineLiker {

  private static final String COMPONENT_TYPE = "LIKER";

  private RedisClient redisClient;

  public PineLikerRedisImpl(RedisClient redisClient) {
    this.redisClient = redisClient;
  }

  protected String getComponentType() {
    return COMPONENT_TYPE;
  }


  public LikerTopic topic(String topic) {
    return new LikerTopicRedisImpl.Builder()
        .setTopicKey(getKey(topic))
        //.setDistkvClient(redisClient)
        .build();
  }

  private String getKey(String topic) {
    return String.format("%s_%s", super.getKey(), topic);
  }
}
