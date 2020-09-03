package com.marsmob.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.marsmob.annotation.FlowLimit;
import com.marsmob.annotation.Permissions;
import com.marsmob.service.ITestService;

@Controller
@RequestMapping("/test")
@ResponseBody
@FlowLimit
public class TestController {
    
	private static final Logger logger = Logger.getLogger(TestController.class);
	
	@Resource
	private  ITestService  testService;
	

	

	@RequestMapping("/testPage.do")
	@Permissions("/test/testPage.do")
	public  List testPage(){
		  logger.info("测试日志");
		  return   testService.testPage();
	}
	

	@RequestMapping("/save.do")
	@Permissions("/test/save.do")//自定义的权限注解 类似于shiro的使用方式
	public  void save(){
		for(int i =0;i<1000000;i++){
			testService.save();
		}    
	
	}
	
	

	@RequestMapping("/findTest.do")
	public  int   findTest(){
		    return  testService.findTest();
	}
	



	
	

	
	@RequestMapping("/index")
	public ModelAndView getIndex(){
		ModelAndView modelAndView=new ModelAndView("/login");
		Map  map = new HashMap();
		map.put("name", "123455");
		modelAndView.addObject("map", map);
		return modelAndView;
	}
	
	public static void main(String[] args) {
		//String   aliPay = "15982184382";//大陆手机号测试
		//String aliPay = "w@163.com"; //@前小于四位的支付宝测试
		//String aliPay = "504744973@qq.com"; //@前大于四位的支付宝测试
		String aliPay = "784";//非大陆支付宝测试
		 try {
			if(aliPay.contains("@")){
				//邮箱
				 String sub = aliPay.substring(0,aliPay.indexOf("@"));
				 if(sub.length()<4){
					 aliPay =  aliPay.replaceAll("(\\w)(\\w+)(@\\w+\\.[a-z]+(\\.[a-z]+)?)","$1**$3");
				 }else{
					 aliPay =  aliPay.replaceAll("(\\w{2})(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)","$1***$3$4");
				 }
				 System.out.println(aliPay);
			 }else if (aliPay.matches("[0-9]{11}")){
				 //大陆手机号
				 aliPay =  aliPay.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");  
				 System.out.println(aliPay);
			 }else{
				 //港澳台或者国外支付宝[也可以是微信QQ号]
				 if(aliPay.length()>6){
					 //QQ6位作为分界
					 aliPay =  aliPay.replaceAll("(\\w{2})(\\w+)(\\w{1})", "$1***$3");  
					 
				 }else{
					 aliPay =  aliPay.replaceAll("(\\w{2})(\\w+)", "$1****");  
				 }
				 
				 System.out.println(aliPay);
			 }
		} catch (Exception e) {
			//异常 处理不了的支付宝账号
			aliPay =  aliPay.replaceAll("(\\w{1})(\\w+)", "$1****");  
			System.out.println(aliPay);
		}
	}

}
