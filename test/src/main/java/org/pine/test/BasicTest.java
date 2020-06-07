package org.pine.test;



import org.pine.api.Pine;
import org.pine.api.PineLiker;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicTest {

  @Test
  public void basicTest() {
    Pine.init("distkv", "127.0.0.1:34222");

    PineLiker liker = Pine.newLiker();
    liker.topic("nihao").likesFrom("zhangsan");
    liker.topic("nihao").likesFrom("zhangsan");
    Assert.assertEquals(liker.topic("nihao").count(), 1);
    liker.topic("nihao").likesFrom("lisi");
    Assert.assertEquals(liker.topic("nihao").count(), 2);

    Assert.assertTrue(liker.topic("nihao").unlikesFrom("zhangsan"));
    Assert.assertFalse(liker.topic("nihao2").unlikesFrom("zhangsan"));
    Assert.assertEquals(liker.topic("nihao").count(), 1);

    Pine.shutdown();
  }
}
