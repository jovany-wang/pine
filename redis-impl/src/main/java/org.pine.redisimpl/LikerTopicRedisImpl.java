package org.pine.redisimpl;

import io.lettuce.core.RedisClient;
import java.util.HashSet;
import org.pine.api.LikerTopic;


public class LikerTopicRedisImpl implements LikerTopic {
  private String topicName;

  private RedisClient redisClient;

  private LikerTopicRedisImpl() {

  }

  public String getTopicName() {
    return topicName;
  }

  private void setTopicName(String topicName) {
    this.topicName = topicName;
  }

  public RedisClient getRedisClient() {
    return redisClient;
  }

  private void setRedisClient(RedisClient redisClient) {
    this.redisClient = redisClient;
  }

  static class Builder {
    private LikerTopicRedisImpl pineLikerTopic;

    public Builder() {
      pineLikerTopic = new LikerTopicRedisImpl();
    }

    public Builder setTopicKey(String topicKey) {
      pineLikerTopic.setTopicName(topicKey);
      return this;
    }

    public Builder setRedisClient(RedisClient RedisClient) {
      pineLikerTopic.setRedisClient(RedisClient);
      return this;
    }

    public LikerTopicRedisImpl build() {
      return pineLikerTopic;
    }
  }

  public void likesFrom(String likee) {
   // try {
      //RedisClient.sets().get(topicName);
   /* } catch (KeyNotFoundException e) {
      RedisClient.sets().put(topicName, new HashSet<String>());
    }
    RedisClient.sets().putItem(topicName, likee);*/

  }

  /**
   * Let people unlike the topic.
   * @param likee the unliked people.
   * @return false if this topic already did not exist,
   * true if the operation succeeded.
   */
  public boolean unlikesFrom(String likee) {

    return true;
  }

  public int count() {
    return 1;
  }
}
