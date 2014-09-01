package com.lich.base.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lich.base.dao.CommonDao;
import com.lich.base.service.BaseService;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Service("BaseServiceImpl")
public class BaseServiceImpl<T extends Serializable> implements BaseService<T> {

	@Autowired
	@Qualifier("CommonDaoHibernate")
	public CommonDao<T> commonDao;
	
	@Override
	public T findById(Class clazz, Serializable id) {
		try{
			return (T) commonDao.getById(clazz, id);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public T save(T model) {
		return commonDao.saveObject(model);
	}
	
	@Override
	public void saveOrUpdate(T model) {
		commonDao.updateObject(model);
	}

	@Override
	public List<T> findByHsql(String hql) {
		return commonDao.findByHsql(hql);
	}

	@Override
	public void update(T entity) {
		commonDao.updateObject(entity);
	}

	@Override
	public T findById(String entityName, Serializable id) {
		return (T) commonDao.getById(entityName, id);
	}

	@Override
	public void delete(T model) {
		commonDao.removeObject(model);
	}

	@Override
	public void deleteById(Class clazz, Serializable id) {
		commonDao.removeObject(clazz, id);
	}

}
