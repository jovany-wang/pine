# pine
A service to provide many useful and out-of-the-box components with adapting many storages.

## Supportted Storages
- Distkv
- Redis

## Maven
basic Maven

        <dependency>
            <groupId>com.distkv</groupId>
            <artifactId>api</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        
If you want to use distkv,add the following to maven:

        <dependency>
            <groupId>com.distkv</groupId>
            <artifactId>distkv-impl</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        
If you want to use redis,add the following to maven:

        <dependency>
            <groupId>com.distkv</groupId>
            <artifactId>redis-impl</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>


## Basic usage (Redis and Distkv) 
```Java
    import org.pine.api.Pine;
    import org.pine.api.PineLiker;
    
    public class BasicUsage {
        public static void main(String[] args) {
            Pine.init("distkv", "127.0.0.1:8082");
            // Or use : Pine.init("redis", "host:port:password", databaseindex);
            // like : Pine.init("redis", "127.0.0.1:6379:password", 1);
    
            PineLiker liker = Pine.newLiker();
            liker.topic("nihao").likesFrom("zhangsan");
            liker.topic("nihao").likesFrom("zhangsan");
            System.out.println("topic 'nihao' likesFrom num  = " + liker.topic("nihao").count());
    
            liker.topic("nihao").likesFrom("lisi");
            System.out.println("topic 'nihao' likesFrom num  = " + liker.topic("nihao").count());
    
            System.out.println("is Ture? " + liker.topic("nihao").unlikesFrom("zhangsan"));
            System.out.println("is False? " + liker.topic("nihao2").unlikesFrom("zhangsan"));
    
            System.out.println("topic 'nihao' likesFrom num  = " + liker.topic("nihao").count());
    
            Pine.shutdown();
        }
    }
```