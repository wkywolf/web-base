package com.lich.base.service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>BaseService 业务基础类</p>
 * 
 * @author Lich 2014年7月4日 下午2:39:48
 */
@SuppressWarnings({ "rawtypes" })
public interface BaseService<T extends Serializable> {
	
	public T findById(String entityName, Serializable id);
	
	public List<?> findByHsql(String hql);
	/**
	 * <p>通过ID获取该实体的信息</p>
	 * @param id
	 * @return com.pet.admin.model
	 */
	public T findById(Class clazz, Serializable id);
	/**
	 * <p>保存</p>
	 * @param com.pet.admin.model
	 * @return com.pet.admin.model
	 */
	public T save(T model);
	/**
	 * <p>保存与更新</p>
	 * @param com.pet.admin.model
	 */
	public void saveOrUpdate(T model);
	/**
	 * <p>更新</p>
	 * @param com.pet.admin.model
	 */
	public void update(T model);
	
	public void delete(T model);
	
	public void deleteById(Class clazz, Serializable id);
	
}
