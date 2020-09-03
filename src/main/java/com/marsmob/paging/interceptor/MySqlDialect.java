package com.marsmob.paging.interceptor;


/**
 * 
 * <p>Title: MySqlDialect.java</p>
 * <p>Description: </p>
 * <p>Copyright: Niehui Copyright(c)2014</p>
 * <p>Company:</p>
 * <p>CreateTime:2014年7月4日 上午10:44:45</p>
 * @author Administrator
 * @version 1.0
 *
 */
public class MySqlDialect extends Dialect {

    @Override
    public String getPageSql(String sql, int offset, int limit) {

        StringBuffer sb = new StringBuffer();
        sb.append(sql.trim());
        sb.append(" limit " + offset);
        sb.append(", " + limit);
        return sb.toString();
    }

	public String getPageSql(String sql) {
		    int curPage = PageThreadLocal.getCurPage();
		    int pageSize = PageThreadLocal.getPageSize();
		    int startIndex = (curPage-1)*pageSize;//起始位置
		    StringBuffer sb = new StringBuffer();
	        sb.append(sql.trim());
	        sb.append(" limit " + startIndex);
	        sb.append(", " + pageSize);
	        return sb.toString();
	}

}
