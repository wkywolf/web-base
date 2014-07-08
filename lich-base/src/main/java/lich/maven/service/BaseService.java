package lich.maven.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lich.maven.dao.Dao;

/**
 * <p>BaseService 业务基础类</p>
 * 
 * @author Lich 2014年7月4日 下午2:39:48
 */
public interface BaseService {
	
	public <T> T getEntityById(String entityName, Integer id);
	
	public <T> List<T> find(String hql);
	/**
	 * <p>通过ID获取该实体的信息</p>
	 * @param id
	 * @return Entity
	 */
	public <T> T findById(Class<T> clazz, Serializable id);
	/**
	 * <p>保存与更新</p>
	 * @param entity
	 */
	public <T> void saveOrUpdate(T entity);
	/**
	 * <p>通过SQL与参数查询结果集</p>
	 * @param sql
	 * @param paramsMap
	 * @return List
	 */
	public <T> List<T> findBySql(String sql, Map<String, Object> paramsMap);
	/**
	 * <p>更新</p>
	 * @param entity
	 */
	public <T> void update(T entity);
	
}
