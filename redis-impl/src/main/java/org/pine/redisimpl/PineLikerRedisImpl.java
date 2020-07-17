package org.pine.redisimpl;

import org.pine.api.LikerTopic;
import org.pine.api.PineLiker;
import redis.clients.jedis.Jedis;

public class PineLikerRedisImpl extends AbstractPineHandle implements PineLiker {

    private static final String COMPONENT_TYPE = "LIKER";

    private Jedis  redisClient;

    public PineLikerRedisImpl(Jedis redisClient) {
        this.redisClient = redisClient;
    }

    @Override
    protected String getComponentType() {
        return COMPONENT_TYPE;
    }


    @Override
    public LikerTopic topic(String topic) {
        return new LikerTopicRedisImpl.Builder()
                .setTopicKey(getKey(topic))
                .setDistkvClient(redisClient)
                .build();
    }
    private String getKey(String topic) {
        return String.format("%s_%s", super.getKey(), topic);
    }
}
