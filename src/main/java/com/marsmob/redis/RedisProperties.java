package com.marsmob.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * redis配置文件 可以将所有的配置文件生成一个Bean。 在@Configuration中注入即可
 * 
 * @describe
 * @author wcc
 * @data 2018年1月18日
 * @version v1.0
 * @copyright LH.GROUP
 */
@Component
@PropertySource("classpath:/redis.properties")
public class RedisProperties {
	// redis是单机、哨兵还是集群模式
	@Value("${redis.model}")
	private String redisModel;
	
	/*哨兵模式*/	
	@Value("${redis.sentinel.hostAndPort}")
	private String sentinelHostAndPort;
	@Value("${redis.sentinel.masterName}")
	private String sentinelMasterName;
	@Value("${redis.sentinel.password}")
	private String sentinelPassword;
	
	
	
	
	public String getSentinelHostAndPort() {
		return sentinelHostAndPort;
	}

	public void setSentinelHostAndPort(String sentinelHostAndPort) {
		this.sentinelHostAndPort = sentinelHostAndPort;
	}

	public String getSentinelMasterName() {
		return sentinelMasterName;
	}

	public void setSentinelMasterName(String sentinelMasterName) {
		this.sentinelMasterName = sentinelMasterName;
	}

	public String getSentinelPassword() {
		return sentinelPassword;
	}

	public void setSentinelPassword(String sentinelPassword) {
		this.sentinelPassword = sentinelPassword;
	}

	/*单机配置*/
	@Value("${redis.single.ip}")
	private String host;
	@Value("${redis.single.port}")
	private Integer port;
	@Value("${redis.single.password}")
	private String password;
    
	

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRedisModel() {
		return redisModel;
	}

	public void setRedisModel(String redisModel) {
		this.redisModel = redisModel;
	}
   
	
	/** redis集群节点 */
	@Value("${redis.cluster.nodes}")
	private String nodes;
	public String getNodes() {
		return nodes;
	}

	public void setNodes(String nodes) {
		this.nodes = nodes;
	}

}