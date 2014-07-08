package lich.maven.dao;

import java.io.Serializable;
import java.util.List;

import lich.maven.model.Pagination;

import org.hibernate.type.Type;

/**
 * 对hsql和sql语法封闭接口
 * @author Lich
 * 2014年7月6日 下午5:56:32
 */
public interface CommonDao<T extends Serializable> extends BaseDao<T> {
	
	/**
	 * 基于HQL语句分页查询列表
	 * @param hql
	 * @param pagination
	 * @return 列表
	 */
	public List<T> find(String hql, Pagination<T> pagination);
	/**
	 * 基于HQL语句分页查询列表
	 * @param hql
	 * @param pagination
	 * @return 列表
	 */
	public List<T> find(String hql, Object[] objs, Type[] atype, Pagination<T> pagination);
	/**
	 * 基于HQL语句分页查询列表
	 * @param hql
	 * @param pagination
	 * @return 列表
	 */
	public List<T> find(String hql, Object obj, Pagination<T> pagination);
	/**
	 * 基于HQL语句分页查询列表
	 * @param hql
	 * @param pagination
	 * @return 列表
	 */
	public List<T> find(String hql, Object[] objs, Pagination<T> pagination);
	/**
	 * 基于HQL语句查询列表
	 * @param hql
	 * @return 列表
	 */
	public List<T> findByHsql(String hql);
	/**
	 * 基于HQL语句附带参数查询列表
	 * @param hql
	 * 如： from Table where id=?
	 * @param parameter
	 * 如： Object obj
	 * @return 列表
	 */
	public List<T> findByHsql(String hql, Object parameter);
	/**
	 * 基于HQL语句附带参数查询列表
	 * @param hql
	 * 如： 1. from Table where id=? <br>
	 *    2. from Table where id=? and name=?
	 * @param parameters
	 * 如： 1. new Object[]{ 1 }
	 *    2. new Object[]{ 1, "Lich" }
	 * @return 列表
	 */
	public List<T> findByHsql(String hql, Object[] parameters);
	/**
	 * 根据实体获得该实体列表
	 * @param clazz
	 * 如： Entity.class
	 * @return 列表
	 */
	public List<T> findAll(Class<T> clazz);
	/**
	 * 基于SQL语句查询列表
	 * @param sql
	 * 如： <br>
	 * 1. select * from table <br>
	 * 2. select * from table where id=1
	 * @param clazz
	 * 如： Entity.class
	 * @return 列表
	 */
	public List<T> findBySql(String sql, Class<T> clazz);
	/**
	 * 基于SQL语句附带参数查询列表
	 * @param sql
	 * 如：  select * from table where id=? <br>
	 * @param parameter
	 * 如：  new Object[]{ 1 }
	 * @param clazz
	 * 如：
	 * Entity.class
	 * @return 列表
	 */
	public List<T> findBySql(String sql, Object parameter, Class<T> clazz);
	/**
	 * 基于SQL语句附带参数查询列表
	 * @param sql
	 * 如： <br>
	 * 1. select * from table where id=? <br>
	 * 2. select * from table where id=? and name=?
	 * @param parameters
	 * 如： <br>
	 * 1. new Object[]{ 1 } <br>
	 * 2. new Object[]{ 1, "Lich" }
	 * @param clazz
	 * 如：
	 * Entity.class
	 * @return 列表
	 */
	public List<T> findBySql(String sql, Object[] parameters, Class<T> clazz);

}
