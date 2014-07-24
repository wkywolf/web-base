package com.lich.base.dao;

import java.io.Serializable;

import org.hibernate.Session;

/**
 * 基础CRUD Dao接口
 * @author Lich
 * 2014年7月6日 下午5:55:38
 */
public interface BaseDao<T extends Serializable> {
	
	/**
	 * 获取当前Session
	 * @return Session
	 */
	public Session getCurrentSession();
	/**
	 * 通过类与ID获得实体信息
	 * @param clazz
	 * 如： Entity.class
	 * @param id
	 * @return Entity
	 */
	public T getById(Class<T> clazz, Serializable id);
	/**
	 * 通过实体名称与ID获得实体信息
	 * @param entityName
	 * 如： "com.lich.common.model.Entity"
	 * @param id
	 * @return Entity
	 */
	public T getById(String entityName, Serializable id);
	/**
	 * 保存对象
	 * @param model
	 */
    public void saveObject(T model);
    /**
     * 更新对象
     * @param model
     */
    public void updateObject(T model);
    /**
     * 刷新对象
     * @param model
     */
    public void refreshObject(T model);
    /**
     * 根据类与ID删除对象
     * @param clazz
     * @param id
     */
    public void removeObject(Class<T> clazz, Serializable id);
    /**
     * 根据对象删除对象
     * @param model
     */
    public void removeObject(T model);

}
