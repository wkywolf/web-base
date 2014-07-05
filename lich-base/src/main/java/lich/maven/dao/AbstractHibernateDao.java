package lich.maven.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
/**
 * <p>Dao基础类</p>
 * 对访问数据库操作进行封装
 * 
 * @author Lich 2014年7月4日 下午2:39:48
 */
@Repository
public abstract class AbstractHibernateDao<T extends Serializable> {
	
	private Class<T> entityClass;
	
	private SessionFactory sessionFactory;

	protected AbstractHibernateDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		return (T) getCurrentSession().get(entityClass, id);
	}

	public void saveOrUpdate(T t) {
		getCurrentSession().save(t);
	}

	public void delete(T t) {
		getCurrentSession().delete(t);
	}

	@SuppressWarnings("unchecked")
	public List<T> queryByHql(String hql, Object[] args) {
		Query query = getCurrentSession().createQuery(hql);
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<T> queryBySql(String sql, Map<String, Object> mapBean) {
		Query query = getCurrentSession().createSQLQuery(sql).setProperties(mapBean);
		return query.list();
	}
	
	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
