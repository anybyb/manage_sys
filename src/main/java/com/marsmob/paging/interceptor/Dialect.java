package com.marsmob.paging.interceptor;



/**
 * 
 * <p>Title: Dialect.java</p>
 * <p>Description: </p>
 * <p>Copyright: Niehui Copyright(c)2014</p>
 * <p>Company: </p>
 * <p>CreateTime:2014年7月4日 上午10:44:25</p>
 * @author wcc
 * @version 1.0 
 *
 */
public abstract class Dialect {    
 /**
  *  
  * <p>Title: Dialect.java</p>
  * <p>Description: </p>
  * <p>Copyright: Niehui Copyright(c)2014</p>
  * <p>Company:</p>
  * <p>CreateTime:2014年7月4日 上午10:44:18</p>
  * @author wcc
  * @version 1.0  
  *
  */
    public static enum Type {    
        MYSQL,    
        ORACLE    
    }    
        
    public abstract String getPageSql(String sql, int offset, int limit);
    public abstract String getPageSql(String sql);
}