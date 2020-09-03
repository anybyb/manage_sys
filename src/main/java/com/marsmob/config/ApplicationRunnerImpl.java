package com.marsmob.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 项目初始成功后TODO
 * @author wcc
 * @Date 2020年9月3日
 * @Description 
 *
 */
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
	
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("===========》spring boot  application started...《==========");
    }
}