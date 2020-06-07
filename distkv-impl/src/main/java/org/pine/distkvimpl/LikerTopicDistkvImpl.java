package org.pine.distkvimpl;

import com.distkv.client.DistkvClient;
import com.distkv.common.exception.KeyNotFoundException;
import org.pine.api.LikerTopic;

import java.util.HashSet;


public class LikerTopicDistkvImpl implements LikerTopic {
  private String topicName;

  private DistkvClient distkvClient;

  private LikerTopicDistkvImpl() {

  }

  public String getTopicName() {
    return topicName;
  }

  private void setTopicName(String topicName) {
    this.topicName = topicName;
  }

  public DistkvClient getDistkvClient() {
    return distkvClient;
  }

  private void setDistkvClient(DistkvClient distkvClient) {
    this.distkvClient = distkvClient;
  }

  static class Builder {
    private LikerTopicDistkvImpl pineLikerTopic;

    public Builder() {
      pineLikerTopic = new LikerTopicDistkvImpl();
    }

    public Builder setTopicKey(String topicKey) {
      pineLikerTopic.setTopicName(topicKey);
      return this;
    }

    public Builder setDistkvClient(DistkvClient distkvClient) {
      pineLikerTopic.setDistkvClient(distkvClient);
      return this;
    }

    public LikerTopicDistkvImpl build() {
      return pineLikerTopic;
    }
  }

  public void likesFrom(String likee) {
    try {
      distkvClient.sets().get(topicName);
    } catch (KeyNotFoundException e) {
      distkvClient.sets().put(topicName, new HashSet<String>());
    }
    distkvClient.sets().putItem(topicName, likee);
  }

  /**
   * Let people unlike the topic.
   * @param likee the unliked people.
   * @return false if this topic already did not exist,
   * true if the operation succeeded.
   */
  public boolean unlikesFrom(String likee) {
    try {
      distkvClient.sets().get(topicName);
    } catch (KeyNotFoundException e) {
      return false;
    }
    try {
      distkvClient.sets().removeItem(topicName, likee);
    } catch (Exception e) {
      // Should catch SetItemNotFoundException and throw PineLikerLikeeNotFoundException.
      throw new RuntimeException(
          "This likee has never liked this topic.");
    }
    return true;
  }

  public int count() {
    return distkvClient.sets().get(topicName).size();
  }
}
