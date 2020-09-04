package com.managesys.permissions.tag;


import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 自定义标签
 * 控制页面按钮是否显示
 * @author Administrator
 *
 */
public class PrivShowTag  extends TagSupport{
	/*
	 * 操作按钮的路径
	 */
 private String action;

 public void setAction(String action) {
	this.action = action;
 }
 public int doStartTag() throws JspException{
	 if(action!=null){
		  List list=(List) this.pageContext.getSession().getAttribute("permissions");
		  if(list.contains(action)){
			  return EVAL_BODY_INCLUDE;  
		  }else {
			 return SKIP_BODY;
		}
	 }
	 return 	super.doStartTag();
 }
 
}
