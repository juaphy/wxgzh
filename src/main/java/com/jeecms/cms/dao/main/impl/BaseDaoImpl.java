package com.jeecms.cms.dao.main.impl;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.util.Assert;

import com.jeecms.cms.dao.main.BaseDao;
import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.page.Pagination;

//import com.tjsoft.base.SystemConstant;

public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {

	public static Logger log = Logger.getLogger("info");
	/**数据库序列名称**/
	private String systemSequence = "SYSTEM_SEQUENCE";
	/**序列生成器**/
	private static OracleSequenceMaxValueIncrementer incr;

	/**
	 * constructor
	 */
	public BaseDaoImpl() {

	}

	/**
	 * 获取数据库序列
	 * @param dataSource
	 */
	public BaseDaoImpl(DataSource dataSource) {
		incr = new OracleSequenceMaxValueIncrementer(dataSource,
				this.systemSequence);
	}

	public void save(Object object) {

		getSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().save(object);
		getSession().flush();

	}

	public void saveOrUpdate(Object object) {
		getSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(object);
		getSession().flush();
	}
 
	public void update(Object object) {

		getHibernateTemplate().saveOrUpdate(object);
	}

	public void updateByQuery(final String queryString,
			final Object[] parameters) {

		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) {

				Query query = session.createQuery(queryString);
				if (parameters != null) {
					for (int i = 0; i < parameters.length; i++) {
						query.setParameter(i, parameters[i]);
					}
				}
				query.executeUpdate();
				return null;
			}
		});
	}

	public void delete(Object object) {

		getHibernateTemplate().delete(object);
	}

	public void delete(Class clazz, Serializable id) {

		getHibernateTemplate().delete(load(clazz, id));
	}

	public Integer deleteAll(final Class clazz) {

		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session) {

						Query query = session.createQuery("delete "
								+ clazz.getName());
						return new Integer(query.executeUpdate());
					}
				});
	}

	public List findAll(Class clazz) {

		return getHibernateTemplate().find("from " + clazz.getName());
	}

	public Object load(Class clazz, Serializable id) {

		return getHibernateTemplate().load(clazz, id);
	}

	public Object get(Class clazz, Serializable id) {

		return getHibernateTemplate().get(clazz, id);
	}

	public List findByNamedQuery(final String namedQuery) {

		return getHibernateTemplate().findByNamedQuery(namedQuery);
	}

	public List findByNamedQuery(final String query, final Object parameter) {

		return getHibernateTemplate().findByNamedQuery(query, parameter);
	}

	public List findByNamedQuery(final String query, final Object[] parameters) {

		return getHibernateTemplate().findByNamedQuery(query, parameters);
	}

	public List find(final String query) {

		return getHibernateTemplate().find(query);
	}

	public List find(final String query, final Object ... parameter) {

		return getHibernateTemplate().find(query, parameter);
	}

	public List find(final String query, final Object parameter,
			final int start, final int end) {

		return getHibernateTemplate().find(query, parameter);
	}

	/**
	 * 普通hsql查询
	 * 
	 * @param query
	 *                hsql语句
	 * @return 对象列表
	 */
	public List query(final String querysql, int start, int pageSize) {

		Session session = getSession();
		Query query = session.createQuery(querysql);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		List list = query.list();
		session.close();
		return list;
	}

	// 查询的记录总数
	public long getCountFindPageByQuery(final String queryString,
			final Object[] parameters) {

		Integer returnvalue = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {

						Query query = session.createQuery(queryString);
						// log.info("run sql:" + queryString);
						if (parameters != null) {
							for (int i = 0; i < parameters.length; i++) {
								query.setParameter(i, parameters[i]);
							}
						}
						query.setMaxResults(1);
						// Integer i = (Integer) query.list().get(0);
						Integer i = new Integer("" + query.list().get(0));
						// log.info("i:"+query.list().get(0));
						return new Integer("" + query.list().get(0));
					}
				});
		return returnvalue.longValue();
	}

	public Integer deleteByQuery(final String queryString,
			final Object[] parameters) {

		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session) {

						Query query = session.createQuery(queryString);
						if (parameters != null) {
							for (int i = 0; i < parameters.length; i++) {
								query.setParameter(i, parameters[i]);
							}
						}
						return new Integer(query.executeUpdate());
					}
				});
	}

	public TreeMap findTreeMap(final String query) {

		TreeMap tmGuide = new TreeMap();
		List list = getHibernateTemplate().find(query);
		for (Iterator it = list.iterator(); it.hasNext();) {
			Object[] os = (Object[]) it.next();
			tmGuide.put(os[0], os[1]);
		}
		return tmGuide;

	}

	public List findTreeMap1(final String query) {

		List tmGuide = new ArrayList();
		tmGuide = getHibernateTemplate().find(query);
		return tmGuide;

	}

	public List getResultSetByJdbc(String sql, JdbcTemplate jt, int start,
			int end) {

		RowCountCallbackHandler rcch = new RowCountCallbackHandler();
		List list = jt.queryForList(sql);
		return list;
	}

	public long getCountByJdbc(String sql, JdbcTemplate jt) {

		RowCountCallbackHandler rcch = new RowCountCallbackHandler();
		jt.query(sql, rcch);
		return rcch.getRowCount();
	}

	public void getExecuteByJdbc(String sql, JdbcTemplate jt) {

		jt.execute(sql);
	}

	public List findXMLSql(final String name) {

		Session session = getSession();
		Query query = session.getNamedQuery(name);
		return query.list();
	}

	/**
	 * 调用存储过程
	 * @throws SQLException 
	 */
	public void TestProcedure() throws SQLException {
		Session session = getSession();
		//    	Transaction tx = session.beginTransaction();
		//    	Connection con = session.connection();
		//    	 String procedure = "{call updateUser(?, ?, ?)}";
		//         CallableStatement cstmt = con.prepareCall(procedure);
		//         cstmt.setString(1, "陈xx");
		//         cstmt.setString(2, "http://www.blogjava.net/sterningChen");
		//         cstmt.setString(3, "sterning");
		//         cstmt.executeUpdate();
		//         tx.commit();
		System.out.println("get session:" + session);
	}

	/**
	 * 设置数据库系统序列名称
	 * @param systemSequence
	 */
	public void setSystemSequence(String systemSequence) {
		this.systemSequence = systemSequence;
	}

	/**
	 * @category 产生10位的序列编号
	 * @return 序列编号
	 * CREATE SEQUENCE或者CREATE ANY SEQUENCE权限， 
	 * CREATE SEQUENCE emp_sequence 
	 * INCREMENT BY 1 -- 每次加几个 
	 * START WITH 1 -- 从1开始计数 
	 * NOMAXVALUE -- 不设置最大值 
	 * NOCYCLE -- 一直累加，不循环 
	 * CACHE 10;
	 */
	public static synchronized Serializable generateSequenceID(String seqName) {
		String sequence = String.valueOf(incr.nextLongValue());
		//计算得到的主键长度 
		int prefixLength = 10 - sequence.length();
		//不够长度则用0补足前缀 
		for (int i = 0; i < prefixLength; i++) {
			sequence = "0" + sequence;
		}
		return sequence;
	}

	public Session getCurrentSession() {
		return getSession();
	}

	/**********************************************************************************************
	 * 执行本地SQL查询
	 **********************************************************************************************/
	/**
	 * 执行本地SQL
	 * 
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List executeNativeSql(final String sql) {
		return getSession().createSQLQuery(sql).list();
	}

	/**
	 * 支持sql查询
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List executeSQL(final String sql, final Object... values) {
		Query query = getSession().createSQLQuery(sql);
		if (sql.indexOf(":p") >= 0 || sql.indexOf(":in") >= 0) {
			if (sql.indexOf(":in0") >= 0 && values.length == 1) {
				query.setParameterList("in0", new Object[] { values[0] });
			} else {
				for (int i = 0; i < values.length; i++) {
					Object obj = values[i];
					if (obj.getClass().isArray()) {
						Object[] pobj = (Object[]) obj;
						query.setParameterList("in" + i, pobj);
					} else {
						query.setParameter("p" + i, obj);
					}
				}
			}
		} else {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}

		return query.list();
	}

	/**
	 * 执行本地SQL
	 * 
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List executeNativeSql(String sql, Object... values) {
		Assert.hasText(sql);
		SQLQuery query = getSession().createSQLQuery(sql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query.list();

	}

	/**
	 * 执行增、删、改SQL语句
	 * 
	 * @param sql
	 * @param values
	 */
	public void executeNativeUpdate(String sql, Object... values)
			throws Exception {
		Assert.hasText(sql);
		SQLQuery query = getSession().createSQLQuery(sql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		query.executeUpdate();
	}

	/**
	 * 执行增、删、改SQL语句
	 * @param sql
	 */
	public void executeNativeUpdate(String sql) throws Exception {
		SQLQuery query = getSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	/**
	 * 执行本地存储过程
	 * @param procedureName
	 * @param objects
	 * @throws SQLException 
	 */
	@SuppressWarnings("deprecation")
	public void executeNativeProcedure(String procedureName, Object... values)
			throws SQLException {
		Connection con = null;
		CallableStatement cstmt = null;
		try {
			con = SessionFactoryUtils.getDataSource(this.getSessionFactory()).getConnection();
			String procedure = "{call " + procedureName + "(";
			for (int k = 0; k < values.length; k++) {
				procedure += "?,";
			}
			procedure = procedure.substring(0, procedure.length() - 1) + ")}";
			cstmt = con.prepareCall(procedure);
			for (int i = 1; i < values.length + 1; i++) {
				cstmt.setObject(i, values[i]);
			}
			cstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != cstmt) {
				cstmt.close();
			}
			if (null != con) {
				con.close();
			}
		}

	}
	public Pagination find(Finder finder, int pageNo, int pageSize) {
		int totalCount = countQueryResult(finder);
		Pagination p = new Pagination(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			p.setList(new ArrayList());
			return p;
		}
		Query query = getSession().createQuery(finder.getOrigHql());
		finder.setParamsToQuery(query);
		query.setFirstResult(p.getFirstResult());
		query.setMaxResults(p.getPageSize());
		if (finder.isCacheable()) {
			query.setCacheable(true);
		}
		List list = query.list();
		p.setList(list);
		return p;
	}
	/**
	 * 获得Finder的记录总数
	 * 
	 * @param finder
	 * @return
	 */
	protected int countQueryResult(Finder finder) {
		Query query = getSession().createQuery(finder.getRowCountHql());
		finder.setParamsToQuery(query);
		if (finder.isCacheable()) {
			query.setCacheable(true);
		}
		return ((Number) query.iterate().next()).intValue();
	}

	public static void main(String[] args) throws SQLException {

		BaseDaoImpl test = new BaseDaoImpl();
		test.TestProcedure();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List queryPager(String querysql, int pageNo, int pageSize) {
		int start  = (pageNo -1) * pageSize;
		return query(querysql, start, pageSize);
	}

	@Override
	public Connection getConnection() throws Exception {
	    return SessionFactoryUtils.getDataSource(this.getSessionFactory()).getConnection();
	}
	
	protected Session getSession() {
	    return currentSession();
	}

}
