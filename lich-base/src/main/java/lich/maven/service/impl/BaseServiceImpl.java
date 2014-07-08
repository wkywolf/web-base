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
@SuppressWarnings("unchecked")
public class BaseServiceImpl implements BaseService {

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public <T> T findById(Class<T> clazz, Serializable id) {
		return (T) commonDao.getObject(clazz, id);
	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		commonDao.saveObject(entity);
	}

	@Override
	public <T> List<T> findBySql(String sql, Map<String, Object> paramsMap) {
		return commonDao.findBySql(sql, paramsMap);
	}

	@Override
	public <T> List<T> find(String hql) {
		return commonDao.find(hql);
	}

	@Override
	public <T> void update(T entity) {
		commonDao.updateObject(entity);
	}

	@Override
	public <T> T getEntityById(String entityName, Integer id) {
		return (T) commonDao.loadById(entityName, id);
	}

}
