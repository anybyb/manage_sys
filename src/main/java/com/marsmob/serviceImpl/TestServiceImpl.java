package com.marsmob.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marsmob.dao.ITestDao;
import com.marsmob.entity.Primarys;
import com.marsmob.entity.Test;
import com.marsmob.redis.JedisClient;
import com.marsmob.redis.RedisClientUtil;
import com.marsmob.service.ITestService;
import com.marsmob.util.Commons;

@Service("testService")
public class TestServiceImpl  implements ITestService {
	@Resource
	private ITestDao  testDao;
	//@Resource
    //private 	RedisClientUtil  redisUtil;
     
	/**
	 * 测试分页
	 */
	@Override
	public List testPage() {
		//redisUtil.getJedis().set("springbootkey", "测试通dsda过");
		System.out.println("page");
		List<Test> test = testDao.findObjByPage();
		System.out.print(test.size());
		//System.out.println(redisUtil.getJedis().get("springbootkey"));
		return test;
	}
    
	/**
	 * 测试事务回滚
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void save() {
		Test test = new Test();
		//Primarys primarys = new Primarys();
	    //testDao.insertPrimary(primarys);
		//test.setId(primarys.getId());
		test.setName("吴长城");
		test.setNote("测试单库分表");
		testDao.insert(test);
	}
	
	@Override
	public int findTest() {
		 return   testDao.findTest();
		
	}
}
