package com.marsmob.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 流限制 即请求流泄洪【暂时没有用到】
 * @describe 
 * @author wcc
 * @data 2018年2月9日
 * @version v1.0
 * @copyright LH.GROUP
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FlowLimit {
      
}
