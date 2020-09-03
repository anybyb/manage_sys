package com.marsmob.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import redis.clients.jedis.JedisCluster;

/**
 * 集群不用j(edisCluster.close);  否则下次不能连接
 * @author wcc
 *
 */
public class JedisClientCluster implements JedisClient{ 
	private static final Logger logger = Logger.getLogger(JedisClientCluster.class);
	@Resource
	private JedisCluster jedisCluster;
	

    @Override
    public String get(String key) {
        String str = jedisCluster.get(key);
        return str;
    }

    @Override
    public String set(String key, String value) {
        String str = jedisCluster.set(key, value);
        return str;
    }

	@Override
	public Long del(String... keys) {
		Long l = jedisCluster.del(keys);
		return l;
	}

	@Override
	public Long append(String key, String str) {
		
		return jedisCluster.append(key, str);
	}

	@Override
	public Boolean exists(String key) {
		
		return jedisCluster.exists(key);
	}

	@Override
	public Long ttl(String key) {
		
		return jedisCluster.ttl(key);
	}

	@Override
	public void expire(String key, int seconds) {
		jedisCluster.expire(key, seconds);
		
	}

	@Override
	public void setex(String key, Integer seconds, String value) {
		jedisCluster.setex(key, seconds, value);
		
	}

	@Override
	public Long setnx(String key, String value) {
		
		return jedisCluster.setnx(key, value);
	}

	@Override
	public String setex(String key, String value, int seconds) {
		
		return jedisCluster.setex(key, seconds, value);
	}

	@Override
	public Long setrange(String key, String value, int offset) {
		
		return jedisCluster.setrange(key, offset, value);
	}

	@Override
	public List<String> mget(String... keys) {
		return jedisCluster.mget(keys);
	}

	@Override
	public String mset(String... keysvalues) {
		
		return jedisCluster.mset(keysvalues);
	}

	@Override
	public Long msetnx(String... keysvalues) {
		
		return jedisCluster.msetnx(keysvalues);
	}

	@Override
	public String getset(String key, String value) {
		
		return jedisCluster.getSet(key, value);
	}

	@Override
	public String getrange(String key, int startOffset, int endOffset) {
		
		return jedisCluster.getrange(key, startOffset, endOffset);
		
	}

	@Override
	public Long incr(String key) {
		
		return jedisCluster.incr(key);
	}

	@Override
	public Long incrBy(String key, Long integer) {
		return jedisCluster.incrBy(key, integer);
	}

	@Override
	public Long decr(String key) {
		return jedisCluster.decr(key);
	}

	@Override
	public Long hset(String key, String field, String value) {
		return jedisCluster.hset(key, field, value);
	}

	@Override
	public Long hsetnx(String key, String field, String value) {
		
		return jedisCluster.hsetnx(key, field, value);
	}

	@Override
	public String hmset(String key, Map<String, String> hash) {
		
		return jedisCluster.hmset(key, hash);
	}

	@Override
	public String hget(String key, String field) {
		
		return jedisCluster.hget(key, field);
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		return jedisCluster.hmget(key, fields);
	}

	@Override
	public Long hincrby(String key, String field, Long value) {
		return jedisCluster.hincrBy(key, field, value);
	}

	@Override
	public Boolean hexists(String key, String field) {
		
		return jedisCluster.hexists(key, field);
	}

	@Override
	public Long hlen(String key) {
		
		return jedisCluster.hlen(key);
	}

	@Override
	public Long hdel(String key, String... fields) {
		
		return jedisCluster.hdel(key, fields);
	}

	@Override
	public Set<String> hkeys(String key) {
		
		return jedisCluster.hkeys(key);
	}

	@Override
	public List<String> hvals(String key) {

		return jedisCluster.hvals(key);
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		
		return jedisCluster.hgetAll(key);
	}

	@Override
	public Long lpush(String key, String... strs) {
		
		return jedisCluster.lpush(key, strs);
	}

	@Override
	public Long rpush(String key, String... strs) {
		
		return jedisCluster.rpush(key, strs);
	}



	@Override
	public String lset(String key, Long index, String value) {
		
		return jedisCluster.lset(key, index, value);
	}

	@Override
	public Long lrem(String key, long count, String value) {
		
		return jedisCluster.lrem(key, count, value);
	}

	@Override
	public String ltrim(String key, long start, long end) {
		
		return jedisCluster.ltrim(key, start, end);
	}

	@Override
	public String lpop(String key) {
		
		return jedisCluster.lpop(key);
	}

	@Override
	public String rpop(String key) {
		
		return jedisCluster.rpop(key);
	}

	@Override
	public String lindex(String key, long index) {
		
		return jedisCluster.lindex(key, index);
	}

	@Override
	public Long llen(String key) {
	
		return jedisCluster.llen(key);
	}

	@Override
	public List<String> lrange(String key, long start, long end) {
		
		return jedisCluster.lrange(key, start, end);
	}

	@Override
	public Long sadd(String key, String... members) {
		
		return jedisCluster.sadd(key, members);
	}

	@Override
	public Long srem(String key, String... members) {
	
		return jedisCluster.srem(key, members);
	}

	@Override
	public Long scard(String key) {
		
		return jedisCluster.scard(key);
	}

	@Override
	public Boolean sismember(String key, String member) {
		
		return jedisCluster.sismember(key, member);
	}

	@Override
	public String srandmember(String key) {
		
		return jedisCluster.srandmember(key);
	}

	@Override
	public Set<String> smembers(String key) {
		
		return jedisCluster.smembers(key);
	}

	@Override
	public Long zadd(String key, Map<String, Double> scoreMembers) {
		
		return jedisCluster.zadd(key, scoreMembers);
	}

	@Override
	public Long zadd(String key, double score, String member) {
	
		return jedisCluster.zadd(key, score, member);
	}

	@Override
	public Long zrem(String key, String... members) {
		
		return jedisCluster.zrem(key, members);
	}

	@Override
	public Double zincrby(String key, double score, String member) {
		
		return jedisCluster.zincrby(key, score, member);
	}

	@Override
	public Long zrank(String key, String member) {
	
		return jedisCluster.zrank(key, member);
	}

	@Override
	public Long zrevrank(String key, String member) {
		
		return jedisCluster.zrevrank(key, member);
	}

	@Override
	public Set<String> zrevrange(String key, long start, long end) {
		
		return jedisCluster.zrevrange(key, start, end);
	}

	@Override
	public Set<String> zrangebyscore(String key, String max, String min) {
		
		return jedisCluster.zrangeByScore(key, min, max);
	}

	@Override
	public Set<String> zrangeByScore(String key, double max, double min) {
		
		return jedisCluster.zrangeByScore(key, min, max);
	}

	@Override
	public Long zcount(String key, String min, String max) {
		
		return jedisCluster.zcount(key, min, max);
	}

	@Override
	public Long zcard(String key) {
		
		return jedisCluster.zcard(key);
	}

	@Override
	public Double zscore(String key, String member) {
		
		return jedisCluster.zscore(key, member);
	}

	@Override
	public Long zremrangeByRank(String key, long start, long end) {

		return jedisCluster.zremrangeByRank(key, start, end);
	}

	@Override
	public Long zremrangeByScore(String key, double start, double end) {
		
		return jedisCluster.zremrangeByScore(key, start, end);
	}

	@Override
	public Set<String> keys(String pattern) {
		//TODO 集群不支持keys
		throw new RuntimeException("集群暂不支持redis的keys*");
	}

   

 
}