package lich.maven.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lich.maven.dao.CommonDao;
import lich.maven.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BaseServiceImpl<T extends Serializable> implements BaseService<T> {

	@Autowired
	private CommonDao<Serializable> commonDao;
	
	@Override
	public T findById(Serializable id) {
		return (T) commonDao.getObject(id);
	}

	@Override
	public void saveOrUpdate(T entity) {
		commonDao.saveObject(entity);
	}

	@Override
	public List<T> findBySql(String sql, Map<String, Object> paramsMap) {
		return (List<T>) commonDao.findBySql(sql, paramsMap);
	}

	@Override
	public List<T> find(String hql) {
		return (List<T>) commonDao.find(hql);
	}

	@Override
	public void update(T entity) {
		commonDao.updateObject(entity);
	}

	@Override
	public T getEntityById(String entityName, Integer id) {
		return (T) commonDao.loadById(entityName, id);
	}

}
