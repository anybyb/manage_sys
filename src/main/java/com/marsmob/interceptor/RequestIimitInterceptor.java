package com.marsmob.interceptor;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

/**
 * 请求限制拦截器
 * 配置@FlowLimi使用。【暂时没有使用】
 * @describe 
 * @author wcc
 * @data 2018年2月9日
 * @version v1.0
 * @copyright LH.GROUP
 */
public class RequestIimitInterceptor  implements HandlerInterceptor{
	/**
	 * 即timeSeconds秒内可以请求1次。
	 * 当达到timeSecondsNumber次后，给予提示，否则直接丢掉请求 
	 */
	 private static int timeSeconds =  2;//timeSeconds秒内限制只能请求一次
	 private static int timeSecondsNumber =  5;//timeSeconds秒内请求次数超过timeSecondsNumber时给予提示
	
	@SuppressWarnings("unused")
	@Override  
	 public boolean preHandle(HttpServletRequest request,  HttpServletResponse response,  Object handler) throws Exception {  
			response.setContentType("text/json;charset=utf-8");
		    PrintWriter  out = response.getWriter();//返回给页面显示
	        Map<String,Object> resultMap = new HashMap<String,Object>();
	        //取用户的真实IP
	        String ip  =  request.getHeader("x-forwarded-for");  

	        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
	            ip = request.getHeader("Proxy-Client-IP");
	        }
	        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
	            ip = request.getHeader("WL-Proxy-Client-IP ");
	        }
	        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
	            ip = request.getRemoteAddr();
	        }
	        System.out.println("请求限制拦截器拦截到请求：ip"+ip);
	        //取session中的IP对象
	        RequestIp re = (RequestIp) request.getSession().getAttribute(ip);
	        //为空说明是第一次请求
	        if(null == re){
	            //放入到session中 当前时间和请求次数
	            RequestIp reIp = new RequestIp();
	            reIp.setCreateTime(System.currentTimeMillis());
	            reIp.setReCount(1);
	            request.getSession().setAttribute(ip,reIp);
	     	    return  true;
	        }else{
	        	//非第一次请求 session中有时间
	            Long createTime = re.getCreateTime();
	            if(null == createTime){
	                //时间请求为空
	                resultMap.put("code", 502);
	                resultMap.put("message", "请求太过频繁，请稍后再试！");
	                writerDataOut(out, resultMap);
	                return false;
	            }else{
	            	//有时间 
	                if(((System.currentTimeMillis() - createTime)/1000) > timeSeconds){
	                    System.out.println("通过请求间隔秒数！"+((System.currentTimeMillis() - createTime)/1000));
	                    //当前时间离上一次请求时间大于5秒，可以直接通过,处理这次的请求
	                    RequestIp reIp = new RequestIp();
	                    reIp.setCreateTime(System.currentTimeMillis());
	                    reIp.setReCount(1);
	                    request.getSession().setAttribute(ip,reIp);
	                    return  true;
	                }else{
	                	 System.out.println("5秒内请求了"+re.getReCount()+"次");
	                    //当前时间离上一次请求时间 小于5秒，并且5秒之内请求了10次，返回提示
	                    if(re.getReCount() > timeSecondsNumber){
	                        resultMap.put("code", 503);
	                        resultMap.put("message", "请求太过频繁，请稍后再试！");
	                        re.setCreateTime(System.currentTimeMillis());//重新设置请求时间.这样用户不停刷新都会显示请求频繁，只有停留3秒后才可以处理
	                        writerDataOut(out, resultMap);
	                        return false;
	                    }else{
	                        //小于5秒，但请求数小于10次，请求数累加，不提示也不处理，直接扔掉请求
	                    	 System.out.println("服务器拒绝处理本次请求");
	                        re.setCreateTime(System.currentTimeMillis());
	                        re.setReCount(re.getReCount()+1);
	                        request.getSession().setAttribute(ip,re);
	                        return false;
	                    }
	                }
	            }
	        }
	     	
	 }  
	 
		private  void   writerDataOut(PrintWriter out,Map resultMap){
			String json =  JSONObject.toJSONString(resultMap);
			//可以在这里对返回的信息进行加密处理 writer.write(DES3.encryptThreeDES(json));
			out.write(json);
			out.flush();
			out.close();
		}

	 //在业务处理器处理请求执行完成后,生成视图之前执行的动作   
	 @Override  
	 public void postHandle(HttpServletRequest request,   HttpServletResponse response, Object handler,   ModelAndView modelAndView) throws Exception {   
	 }  

	 /** 
	  * 在DispatcherServlet完全处理完请求后被调用 
	  * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
	  */  
	 @Override  
	 public void afterCompletion(HttpServletRequest request,  
	         HttpServletResponse response, Object handler, Exception ex)  
	         throws Exception {  
	     }  
	 
	 

}
