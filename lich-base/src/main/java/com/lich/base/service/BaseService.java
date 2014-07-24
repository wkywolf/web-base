package com.lich.base.service;

import java.io.Serializable;
import java.util.List;

import com.lich.common.model.Pagination;

/**
 * <p>BaseService 业务基础类</p>
 * 
 * @author Lich 2014年7月4日 下午2:39:48
 */
public interface BaseService<T extends Serializable> {
	
	public T findById(String entityName, Integer id);
	
	public List<T> findByHsql(String hql);
	/**
	 * <p>通过ID获取该实体的信息</p>
	 * @param id
	 * @return model
	 */
	public T findById(Class<T> clazz, Integer id);
	/**
	 * <p>保存与更新</p>
	 * @param model
	 */
	public void saveOrUpdate(T model);
	/**
	 * <p>更新</p>
	 * @param model
	 */
	public void update(T model);
	
	public void delete(T model);
	
	public void deleteById(Class<T> clazz, Integer id);
	
	public List<T> find(String hql, Pagination<T> pagination) throws Exception;
	
}
