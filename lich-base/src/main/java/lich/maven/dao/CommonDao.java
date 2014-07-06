package lich.maven.dao;

import java.io.Serializable;
import java.util.List;

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
	
	public abstract List find(String sql, Pagination pagination) throws Exception;

	public abstract List find(String sql, Object objs[], Type atype[], Pagination pagination) throws Exception;

	public abstract List find(String sql, Object obj, Pagination pagination) throws Exception;

	public abstract List find(String sql, Object objs[], Pagination pagination) throws Exception;

	public abstract List find(String sql);

	public abstract List findbyhsql(String sql, Object parameter);

	public abstract List findbyhsql(String sql, Object parameters[]);

	public abstract Object loadById(String sql, Long id);

	public abstract Object loadByName(String name);

	public abstract Object loadByNamedQuery(String sql, Object obj);

	public abstract List findAll();

	public abstract List findByNamedQuery(String sql);

	public abstract List findByNamedQuery(String sql, Object obj);

	public abstract List findByNamedQuery(String sql, Object objs[]);

	public abstract Session getCurrentSession();

}
