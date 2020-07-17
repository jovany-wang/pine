package org.pine.redisimpl;

import org.pine.api.PineRuntime;
import org.pine.api.PineRuntimeFactory;

public class RedisImplPineRuntimeFactory implements PineRuntimeFactory {

    public PineRuntime createPineRuntime() {
        return new PineRuntimeRedisImpl();
    }
}
