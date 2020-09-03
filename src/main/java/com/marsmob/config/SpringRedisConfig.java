package com.marsmob.config;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.marsmob.redis.RedisClientUtil;
import com.marsmob.redis.RedisProperties;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

/**
 * spring和redis的整合，类似于xml文件
 * 手动集成
 * 提springboot供了更简单集成方式
 * @describe 
 * @author wcc
 * @data 2018年1月18日
 * @version v1.0
 * @copyright LH.GROUP
 * @Configuration
 */

public class SpringRedisConfig {
	private static final Logger logger = Logger.getLogger(SpringRedisConfig.class);
	
	 @Resource
	 private RedisProperties  redisProperties;
	
	   /**
		 * 向容器注入一个RedisClientUtil ==xml 
		 * <bean id="redisUtil" class="xx.xx.xx.RedisClientUtil" scope="singleton"></bean>
		 * @return
		 */
		@Bean
		@Lazy
		@Scope("singleton")
	    public RedisClientUtil redisClientUtil() {
			return new RedisClientUtil(redisProperties.getRedisModel());
	    }
		
		
		/**
		 * 向容器注入一个JedisPool bean  redis 主从集群哨兵模式版使用
		 * @Lazy 明确模式时才会加载具体的对象，故系统中只会存在三种模式中的一种对象。
		 * @return
		 */
		@Bean
		@Lazy
	    public JedisSentinelPool jedisSentinelPool() {
			JedisPoolConfig config = new JedisPoolConfig();//JedisPoolConfig config 也可以像配置一样 用一个Bean来生成 
			// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
			// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
			config.setMaxTotal(50);
			// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
			config.setMaxIdle(5);
			// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
			config.setMaxWaitMillis(1000 * 60);
			// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
			config.setTestOnBorrow(true);
			
			 //哨兵地址和端口集合
			 Set <String> sentinels = new HashSet<String>();
			 String hostAndPort1 = redisProperties.getSentinelHostAndPort();
			 sentinels.add(hostAndPort1);
			 JedisSentinelPool jedisSentinelPool = new JedisSentinelPool
					    (redisProperties.getSentinelMasterName(),sentinels,config,redisProperties.getSentinelPassword());
			 return jedisSentinelPool;
	    }
		
		
	
		
		
		
		
	 
	/**
	 * 向容器注入一个JedisPool bean  redis 单机版使用
	 * @return
	 */
	@Bean
	@Lazy
    public JedisPool jedisPool() {
		JedisPoolConfig config = new JedisPoolConfig();//JedisPoolConfig config 也可以像配置一样 用一个Bean来生成 
		// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
		// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
		config.setMaxTotal(50);
		// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
		config.setMaxIdle(5);
		// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
		config.setMaxWaitMillis(1000 * 60);
		// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
		config.setTestOnBorrow(true);
		//jedisPool = new JedisPool(config, ip, prot, 100000, pwd); //有密码
		JedisPool pool =  new JedisPool(config, redisProperties.getHost(), redisProperties.getPort(), 100000,redisProperties.getPassword());
        return pool;
    }
	
	
	
	
	
	/**
	 * 向容器注入集群 JedisCluster
	 * 集群redis使用
	 * @return
	 * 
	 */
	@Bean 
	@Lazy
    public JedisCluster jedisCluster() {  
        String[] serverArray = redisProperties.getNodes().split(",");//获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)  
        Set<HostAndPort> nodes = new HashSet<>();  
  
        for (String ipPort : serverArray) {  
            String[] ipPortPair = ipPort.split(":");  
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));  
        }  
        //return new JedisCluster(nodes,poolConfig);//需要poolConfig的话 也可以注入为一个Bean
        return new JedisCluster(nodes);  
    }  
	

	
	

	
	
	
	
}
