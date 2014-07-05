package lich.maven.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>BaseService 业务基础类</p>
 * 
 * @author Lich 2014年7月4日 下午2:39:48
 */
public interface BaseService<T extends Serializable> {
	/**
	 * <p>通过ID获取该实体的信息</p>
	 * @param id
	 * @return Entity
	 */
	public T findById(Serializable id);
	/**
	 * <p>通过HQL与参数查询结果集</p>
	 * @param hql
	 * @param objs
	 * @return List
	 */
	public List<T> findByHql(String hql, Object[] objs);
	/**
	 * <p>保存与更新</p>
	 * @param entity
	 */
	public void saveOrUpdate(T entity);
	/**
	 * <p>通过SQL与参数查询结果集</p>
	 * @param sql
	 * @param mapBean
	 * @return List
	 */
	public List<T> findBySql(String sql, Map<String, Object> mapBean);
	
}
