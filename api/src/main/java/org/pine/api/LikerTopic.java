package org.pine.api;

public abstract class LikerTopic {

  public abstract void likesFrom(String from);

  public abstract boolean unlikesFrom(String from);

  public abstract int count();
}
