package com.ssafy.homfit.api;

import io.lettuce.core.*;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class ConnectToRedis {
    private static final String REDIS_URL = "redis://ssafyC107@i4C107.p.ssafy.io:6379/";

    public void setCache(String key, String value, long time){
        RedisClient redisClient = RedisClient.create(REDIS_URL);
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();
        
        syncCommands.set(key, value, SetArgs.Builder.ex(time));
        connection.close();
        redisClient.shutdown();
    }
    
    public String getCache(String key){
        RedisClient redisClient = RedisClient.create(REDIS_URL);
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();
        
        String result = syncCommands.get(key);

        connection.close();
        redisClient.shutdown();
        return result;
    }
}
