package org.pine.api;

public interface LikerTopic {

    void likesFrom(String from);

    boolean unlikesFrom(String from);

    int count();
}
