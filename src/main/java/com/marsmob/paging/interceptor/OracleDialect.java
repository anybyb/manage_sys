package com.marsmob.paging.interceptor;


/**
 * <p>
 * Title: OracleDialect.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Niehui Copyright(c)2014
 * </p>
 * <p>
 * Company:
 * </p>
 * <p>
 * CreateTime:2014年7月4日 上午10:48:42
 * </p>
 * 
 * @author Administrator
 * @version 1.0
 */
public class OracleDialect extends Dialect {
	/**
	 * 只需要传入sql。下面我们会重载一个方法。
	 * @param sql
	 * @return
	 */
	public String getPageSql(String sql) {
		int curPage = PageThreadLocal.getCurPage();
		int pageSize = PageThreadLocal.getPageSize();
		String sqlTrim = sql.trim();
		// 拼接分页语句
		int startPage = (curPage - 1) * pageSize + 1;
		int endPage = curPage * pageSize;

		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from (select u.*, rownum r from (")
				.append(sqlTrim).append(")").append(" u where rownum <= ")
				.append(endPage).append(") where r >=").append(startPage);
		return sqlBuffer.toString();
	}
   
	/**
	 * sql 原始的sql语句 curPage 当前第几页 pageSize 每页显示的条目数
	 */
	@Override
	public String getPageSql(String sql, int curPage, int pageSize) {

		String sqlTrim = sql.trim();
		// 拼接分页语句
		int startPage = (curPage - 1) * pageSize + 1;
		int endPage = curPage * pageSize;

		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from (select u.*, rownum r from (")
				.append(sqlTrim).append(")").append(" u where rownum <= ")
				.append(endPage).append(") where r >=").append(startPage);
		return sqlBuffer.toString();
	}
}
