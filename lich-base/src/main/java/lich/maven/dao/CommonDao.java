package lich.maven.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lich.maven.model.Pagination;

import org.hibernate.Session;
import org.hibernate.type.Type;

/**
 * 对hsql和sql语法封闭接口
 * @author Lich
 * 2014年7月6日 下午5:56:32
 */
@SuppressWarnings("rawtypes")
public interface CommonDao<T extends Serializable> extends Dao {
	
	public abstract List<T> find(String hql, Pagination pagination) throws Exception;

	public abstract List<T> find(String hql, Object objs[], Type atype[], Pagination pagination) throws Exception;

	public abstract List<T> find(String hql, Object obj, Pagination pagination) throws Exception;

	public abstract List<T> find(String hql, Object objs[], Pagination pagination) throws Exception;

	public abstract List<T> find(String hql);

	public abstract List<T> findbyhsql(String hql, Object parameter);

	public abstract List<T> findbyhsql(String hql, Object parameters[]);

	public abstract Object loadById(String entityName, Serializable id);

	public abstract List<T> findAll();
	
	public abstract List<T> findBySql(String sql);
	
	public abstract List<T> findBySql(String sql, Map<String, Object> paramsMap);

	public abstract Session getCurrentSession();

}
