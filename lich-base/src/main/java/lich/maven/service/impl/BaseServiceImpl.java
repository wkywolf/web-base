package lich.maven.service.impl;

import java.io.Serializable;
import java.util.List;

import lich.maven.dao.CommonDao;
import lich.maven.model.Pagination;
import lich.maven.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("BaseServiceImpl")
public class BaseServiceImpl<T extends Serializable> implements BaseService<T> {

	@Autowired
	@Qualifier("CommonDaoHibernate")
	private CommonDao<T> commonDao;
	
	@Override
	public T findById(Class<T> clazz, Integer id) {
		try{
			return (T) commonDao.getById(clazz, id);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void saveOrUpdate(T model) {
		commonDao.saveObject(model);
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
	public T findById(String entityName, Integer id) {
		return (T) commonDao.getById(entityName, id);
	}

	@Override
	public void delete(T model) {
		commonDao.removeObject(model);
	}

	@Override
	public void deleteById(Class<T> clazz, Integer id) {
		commonDao.removeObject(clazz, id);
	}

	@Override
	public List<T> find(String hql, Pagination<T> pagination) throws Exception {
		return commonDao.find(hql, pagination);
	}

}
