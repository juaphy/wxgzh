package com.jeecms.cms.dao.main;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;

import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.page.Pagination;

public interface BaseDao {

    /**
     * 保存（持久化）一个对象
     * 
     * @param object
     *                要保存的对象
     */
    public void save(Object object);

    /**
     * 保存或修改持久化对象
     * 
     * @param object
     */
    public void saveOrUpdate(Object object);

    /**
     * 更新一个对象
     * 
     * @param object
     *                要修改的对象
     */

    public void update(Object object);

    /**
     * 用语句更新记录
     * 
     * @param queryString
     * @param parameters
     */
    public void updateByQuery(final String queryString,
	    final Object[] parameters);

    /**
     * 删除一个对象
     * 
     * @param object
     *                要删除的对象
     */
    public void delete(Object object);

    /**
     * 根据类型和对象id删除一个对象
     * 
     * @param clazz
     *                类型
     * @param id
     *                对象id
     */
    public void delete(Class clazz, Serializable id);

    /**
     * 根据类型删除全部对象
     * 
     * @param clazz
     *                类型
     * @return
     */
    public Integer deleteAll(final Class clazz);

    /**
     * 根据查询和参数删除全部对象
     * 
     * @param clazz
     *                类型
     * @return
     */
    public Integer deleteByQuery(final String queryString,
	    final Object[] parameters);

    /**
     * 获得某个类型的全部对象列表
     * 
     * @param clazz
     *                类型
     * @return 对象集合
     */
    public List findAll(Class clazz);

    /**
     * 根据类型和对象id载入一个对象
     * 
     * @param clazz
     *                类型
     * @param id
     *                对象id
     * @return 目标对象
     */
    public Object load(Class clazz, Serializable id);

    /**
     * 根据类型和对象id从数据库取得一个对象
     * 
     * @param clazz
     *                类
     * 
     * 
     * @param id
     *                对象id
     * @return 目标对象
     */
    public Object get(Class clazz, Serializable id);

    /**
     * 命名查询
     * 
     * @param namedQuery
     *                命名查询语句
     * @return 对象列表
     */
    public List findByNamedQuery(final String namedQuery);

    /**
     * 依据单个参数做命名查询
     * 
     * @param query
     *                命名查询语句
     * @param parameter
     *                单个查询参数
     * @return 对象列表
     */
    public List findByNamedQuery(final String query, final Object parameter);

    /**
     * 依据参数数组做命名查询
     * 
     * @param query
     *                命名查询语句
     * @param parameters
     *                查询参数数组
     * @return 对象列表
     */
    public List findByNamedQuery(final String query, final Object[] parameters);

    /**
     * 普通hsql查询
     * 
     * @param query
     *                hsql语句
     * @return 对象列表
     */
    public List find(final String query);

    /**
     * 普通hsql查询
     * 
     * @param query
     *                hsql语句
     * @return 对象列表
     */
    public List query(final String querysql, int start, int pageSize);

    /**
     * 普通hsql 带参数查询
     * 
     * @param query
     *                hsql语句
     * @param parameter
     *                单个查询参数
     * @return 对象列表
     */
    public List find(final String query, final Object ... parameter);

    /**
     * 返回记录总条数
     * 
     * @param queryString
     * @param parameters
     * @param pageIndex
     * @param pageSize
     * @param pageInfo
     * @return
     */
    public long getCountFindPageByQuery(final String queryString,
	    final Object[] parameters);

    /**
     * 返回TreeMap键值对
     * 
     * @param detachedCriteria
     * @return
     */
    public TreeMap findTreeMap(final String query);

    public List findTreeMap1(final String query);

    /**
     * jdbc操作 返回ResultSet对象 进行jdbc操作
     * 
     * @param sql
     * @param parameters
     * @param start
     * @param end
     * @return
     */

    public List getResultSetByJdbc(String sql, JdbcTemplate jt, int start,
	    int end);

    /**
     * jdbc操作 取记录总数 执行的sql，在继承的客户端程序拼写
     * 
     * @param sql
     * @param jt
     * @return
     */
    public long getCountByJdbc(String sql, JdbcTemplate jt);

    /**
     * jdbc操作 对数据库进行添加和修改的工作
     * 
     * @param sql
     * @param jt
     */
    public void getExecuteByJdbc(String sql, JdbcTemplate jt);

    /**
     * jdbc操作 读取配置文件中得SQL语句
     * 
     * @param name
     * @return
     */
    public List findXMLSql(final String name);
    
    public Session getCurrentSession();
    
    public Pagination find(Finder finder, int pageNo, int pageSize);
    
    /**
     * 普通hsql查询 进行分页
     * @param querysql hql语句
     * @param pageNo 第几页
     * @param pageSize 每页显示多少
     * @return
     */
    @SuppressWarnings("unchecked")
	public List queryPager(final String querysql, int pageNo, int pageSize);
    /**
     * 执行本地SQL 
     * @param sql
     * @return
     */
    @SuppressWarnings("unchecked") 
    public List executeNativeSql(String sql,Object... values); 
    
    /**
     * 执行增、删、改SQL语句
     * @param sql
     * @param values
     * @throws Exception 
     */
    public void executeNativeUpdate(String sql,Object... values) throws Exception;
    
    /**
     * 执行增、删、改SQL语句
     * @param sql
     * @throws Exception 
     */
    public void executeNativeUpdate(String sql) throws Exception;
    /**
     * 执行本地存储过程
     * @param procedureName
     * @param values
     * @throws SQLException
     */
	public void executeNativeProcedure(String procedureName,Object... values) throws SQLException;
	
    /**
     * 获取Hibernate模板
     * @return
     */
    public HibernateTemplate getHibernateTemplate(); 
    
    /**
     * 获取数据链接
     */
    public Connection getConnection() throws Exception;
    
    /**
	 * 支持sql查询
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List executeSQL(final String sql, final Object... values);
	
    
}