package org.pine.redisimpl;


import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import org.pine.api.PineLiker;
import org.pine.api.PineRuntime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PineRuntimeDistkvImpl implements PineRuntime {

  private static final Logger LOG = LoggerFactory.getLogger(PineRuntimeDistkvImpl.class);
  /**
   * The redis client.
   */
  private RedisClient client;


  public void shutdown() {
    try {
      client.shutdown();
    } catch (RuntimeException e) {
      LOG.error("Failed shutdown the redis client : {}", e.getMessage());
    }
  }

  public PineLiker newLiker() {
    return new PineLikerRedisImpl(client);
  }

  public void connect(String address) {
    new RedisClient.create("redis://localhost");
    StatefulRedisConnection<String, String> connection = client.connect();
    RedisStringCommands sync = connection.sync();
  }
}
