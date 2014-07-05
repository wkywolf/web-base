package lich.maven.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lich.maven.dao.AbstractHibernateDao;
import lich.maven.service.BaseService;

@Service
public class BaseServiceImpl<T extends Serializable> implements BaseService<T> {

	@Autowired
	private AbstractHibernateDao<T> abstractDao;
	
	@Override
	public T findById(Serializable id) {
		return abstractDao.get(id);
	}

	@Override
	public List<T> findByHql(String hql, Object[] objs) {
		return abstractDao.queryByHql(hql, objs);
	}

	@Override
	public void saveOrUpdate(T entity) {
		abstractDao.saveOrUpdate(entity);
	}

	@Override
	public List<T> findBySql(String sql, Map<String, Object> mapBean) {
		return abstractDao.queryBySql(sql, mapBean);
	}

}
