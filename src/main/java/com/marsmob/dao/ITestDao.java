package com.marsmob.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.marsmob.entity.Primarys;
import com.marsmob.entity.Test;

@Repository
public interface ITestDao {
    
	void test();
	Test findObjById(Integer id);
	List<Test> findObjByPage();
	void delete(int i);
	void insert(Test test);
	int findTest();
	/**
	 * 获取全局的主键
	 * @param primarys 
	 * @return 
	 * @return
	 */
	void insertPrimary(Primarys primarys);

}
