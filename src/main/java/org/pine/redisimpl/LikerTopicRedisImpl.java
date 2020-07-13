package org.pine.redisimpl;

import redis.clients.jedis.Jedis;
import org.pine.api.LikerTopic;


public class LikerTopicRedisImpl implements LikerTopic {
    private String topicName;

    private Jedis  redisClient;

    LikerTopicRedisImpl() {

    }

    public String getTopicName() {
        return topicName;
    }

    private void setTopicName(String topicName) {
        this.topicName = topicName;
    }
    public Jedis getRedisClient() {
        return redisClient;
    }

    private void setRedisClient(Jedis redisClient) {
        this.redisClient = redisClient;
    }

    static class Builder {
        private LikerTopicRedisImpl pineLikerTopic;

        public Builder() {
            pineLikerTopic = new LikerTopicRedisImpl();
        }

        public LikerTopicRedisImpl.Builder setTopicKey(String topicKey) {
            pineLikerTopic.setTopicName(topicKey);
            return this;
        }

        public LikerTopicRedisImpl.Builder setDistkvClient(Jedis redisClient) {
            pineLikerTopic.setRedisClient(redisClient);
            return this;
        }

        public LikerTopicRedisImpl build() {
            return pineLikerTopic;
        }
    }

    @Override
    public void likesFrom(String likee) {
        //HashSet<String> set1 = new HashSet<String>();


        try {
            redisClient.get(topicName);
        } catch (Exception e) {
            //new HashSet<String>().toArray().toString()
            redisClient.sadd(topicName,"");
        }
        redisClient.sadd(topicName, likee);
    }

    /**
     * Let people unlike the topic.
     * @param likee the unliked people.
     * @return false if this topic already did not exist,
     * true if the operation succeeded.
     */
    public boolean unlikesFrom(String likee) {
        try {
            redisClient.smembers(topicName);
            if (redisClient.smembers(topicName).equals(redisClient.smembers("0"))){
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        try {
            redisClient.srem(topicName, likee);
        } catch (Exception e) {
            // Should catch SetItemNotFoundException and throw PineLikerLikeeNotFoundException.
            throw new RuntimeException(
                    "This likee has never liked this topic.");
        }
        return true;
    }

    public int count() {
        return Math.toIntExact(redisClient.scard(topicName))-1;
    }
}
