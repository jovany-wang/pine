package org.pine.redisimpl;

import org.pine.api.PineLiker;
import org.pine.api.PineRuntime;
import redis.clients.jedis.Jedis;

public class PineRuntimeRedisImpl implements PineRuntime {
    /**
     * The Redis sync client.
     */
    private Jedis redisClient;

    @Override
    public void shutdown() {
        try {
            redisClient.disconnect();
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format("Failed shutdown the client : %s", e.getMessage()));
        }
    }

    @Override
    public PineLiker newLiker() {
        return new PineLikerRedisImpl(redisClient);
    }

    @Override
    public void connect(String address) {
        String host = address.split(":")[0];
        int port = Integer.parseInt(address.split(":")[1]);
        String password;
        try {
            password = address.split(":")[2];
        } catch (Exception e){
            password = null;
        }

        redisClient = new Jedis(host,port);
        redisClient.auth(password);
    }

    @Override
    public void select(int index) {
        redisClient.select(index);
    }
}
