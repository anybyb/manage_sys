package com.marsmob.redis;

import javax.annotation.Resource;

/**
 * RedisClientUtil 
 * @describe:  操作redis的工具类
 * @author: wcc
 * @Time:2017年7月7日
 * @version 1.0
 * @company:
 */

public class RedisClientUtil {
	private String redisModel;
	private static final String  SINGLE="single";
	private static final String  CLUSTER="cluster";
	private static final String  SENTINEL="sentinel";
	@Resource
	private  JedisClientSingle  jedisClientSingle;
	@Resource
	private  JedisClientSentinel  jedisClientSentinel;
	@Resource
	private JedisClientCluster  jedisClientCluster;
	
	public  RedisClientUtil(String redisModel1){
		redisModel = redisModel1;
	}
	
	/**
	 * 获取jedis实例,单机或者集群模式
	 * @return
	 */
	public  JedisClient getJedis(){
		if(SINGLE.equals(redisModel)){
			//单机模式
			return	jedisClientSingle;
		}else if(SENTINEL.equals(redisModel)){
			//哨兵
			return	jedisClientSentinel;
		}else if(CLUSTER.equals(redisModel)){
			//集群
			return	jedisClientCluster;
		}else{
			//未知模式-->可给予默认一个模式[这里默认给集群模式]
			return	jedisClientCluster;
		}
	}
	
	
}
