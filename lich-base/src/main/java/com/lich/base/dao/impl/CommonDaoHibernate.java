package com.lich.base.dao.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lich.common.model.Pagination;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Component;

import com.lich.base.dao.CommonDao;

@SuppressWarnings("unchecked")
@Component("CommonDaoHibernate")
public class CommonDaoHibernate<T extends Serializable> implements CommonDao<T> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public T getById(Class<T> clazz, Serializable id) {
        T t = (T) getCurrentSession().get(clazz, id);
        if(t == null)
            throw new ObjectRetrievalFailureException(clazz, id);
        return t;
	}

	@Override
	public T getById(String entityName, Serializable id) {
        return (T) getCurrentSession().get(entityName, id);
	}

	@Override
	public void saveObject(T model) {
		getCurrentSession().save(model);
	}

	@Override
	public void updateObject(T model) {
		getCurrentSession().saveOrUpdate(model);
	}

	@Override
	public void refreshObject(T model) {
		getCurrentSession().refresh(model);
	}
	
	@Override
	public void removeObject(Class<T> clazz, Serializable id) {
		getCurrentSession().delete(getById(clazz, id));
	}

	@Override
	public void removeObject(T model) {
		getCurrentSession().delete(model);
	}

	@Override
	public List<T> find(String hql, Pagination<T> pagination) {
		try {
			if (pagination == null) {
				return findByHsql(hql);
			}else{
				return find(hql, new Object[0], new Type[0], pagination);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<T> find(String hql, Object[] objs, Type[] atype, Pagination<T> pagination) {
		 return find(hql, objs, pagination);
	}

	@Override
	public List<T> find(String hql, Object obj, Pagination<T> pagination) {
        if(pagination == null)
            return findByHsql(hql, obj);
        else
        	return find(hql, new Object[] { obj }, pagination);
	}

	@Override
	public List<T> find(String hql, Object[] objs, Pagination<T> pagination) {
        Map<String, Object> paraMap = getParaMap(hql, objs);
        hql = (String)paraMap.get("hql");
        objs = (Object[])paraMap.get("paraObjects");
        if(pagination == null)
            return findByHsql(hql, objs);
        pagination.setTotalCount(getTotalSize(hql, objs));
        Query sqlQuery = null;
        List<T> ls = null;
		try {
			sqlQuery = getCurrentSession().createQuery(hql);
			this.setProperties(sqlQuery, objs);
			sqlQuery.setFirstResult((int) pagination.getStartIndex() - 1).setMaxResults((int)pagination.getPageSize());
			ls = sqlQuery.list();
			if (ls == null)
				ls = new ArrayList<T>(0);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		pagination.setResultList(ls);
		return ls;
	}

	@Override
	public List<T> findByHsql(String hql) {
        return findByHsql(hql, new Object[0]);
	}

	@Override
	public List<T> findByHsql(String hql, Object parameter) {
        Object parameters[] = new Object[]{ parameter };
        return findByHsql(hql, parameters);
	}

	@Override
	public List<T> findByHsql(String hql, Object[] parameters) {
		Map<String, Object> paraMap = getParaMap(hql, parameters);
        hql = (String)paraMap.get("hql");
        parameters = (Object[])paraMap.get("paraObjects");
        Query query = getCurrentSession().createQuery(hql);
        this.setProperties(query, parameters);
        return query.list();
	}

	@Override
	public List<T> findAll(Class<T> clazz) {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
	}

	public static Map<String, Object> getParaMap(String hql, Object[] objs) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (hql.toLowerCase().indexOf("to_date") >= 0) {
			try {
				String leftSql = hql.toLowerCase();
				String mySql = hql;
				int loc = 0;
				int old_index = 0;
				List<Object> paraList = new ArrayList<Object>();
				while (leftSql.indexOf("to_date") > 0) {
					String rightStr = leftSql.substring(leftSql.indexOf("to_date"));
					String myrightStr = mySql.substring(leftSql.indexOf("to_date"));
					loc = rightStr.indexOf("')");
					int loc2 = leftSql.indexOf("to_date");
					for (String tmps = leftSql.substring(0, loc2); tmps.indexOf("?") > 0; tmps = tmps.substring(tmps.indexOf("?") + 1)) {
						paraList.add(objs[old_index]);
						old_index++;
						leftSql = (new StringBuilder())
								.append(leftSql.substring(0, tmps.indexOf("?") - 1))
								.append("|")
								.append(leftSql.substring(tmps.indexOf("?") + 1))
								.toString();
					}

					mySql = (new StringBuilder())
							.append(mySql.substring(0, loc2)).append("?")
							.append(myrightStr.substring(loc + 2)).toString();
					leftSql = (new StringBuilder())
							.append(leftSql.substring(0, loc2)).append("|")
							.append(rightStr.substring(loc + 2)).toString();
					rightStr = rightStr.substring(rightStr.indexOf("'") + 1);
					String valueStr = rightStr.substring(0, rightStr.indexOf("'"));
					rightStr = rightStr.substring(rightStr.indexOf(","));
					rightStr = rightStr.substring(rightStr.indexOf("'") + 1);
					String formatStr = rightStr.substring(0,
							rightStr.indexOf("'"));
					if ("yyyy-mm-dd hh24:mi:ss".equals(formatStr)) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						paraList.add(sdf.parse(valueStr));
					} else {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						paraList.add(sdf.parse(valueStr));
					}
				}
				if (old_index < objs.length) {
					for (int i = old_index; i < objs.length; i++)
						paraList.add(objs[i]);

				}
				paraMap.put("hql", mySql);
				paraMap.put("paraObjects", ((Object) (paraList.toArray())));
			} catch (ParseException e) {
				e.printStackTrace();
				paraMap.put("paraObjects", ((Object) (objs)));
				paraMap.put("hql", hql);
			}
		} else {
			paraMap.put("paraObjects", ((Object) (objs)));
			paraMap.put("hql", hql);
		}
		return paraMap;
	}

	protected long getTotalSize(String hql, Object[] objs) {
		long count = 0L;
		String midSql = null;
		try {
			midSql = getCountSql(hql);
			Query sqlQuery = getCurrentSession().createQuery(midSql);
			if (objs != null && objs.length > 0) {
				for (int i = 0; i < objs.length; i++)
					sqlQuery.setParameter(i, objs[i]);

			}
			List<T> ls = sqlQuery.list();
			if (ls != null && ls.size() > 0) {
				Object obj = ls.get(0);
				if (obj instanceof Integer)
					count = ((Integer) obj).longValue();
				else if (obj instanceof Long)
					count = ((Long) obj).longValue();
			}
		} catch (Exception he) {
			he.printStackTrace();
		}
		return count;
	}
	
	public String getCountSql(String hql) {
		String finalSql = "";
		String fromSegment = hql;
		int fromStartIndex = StringUtils.indexOf(fromSegment.toLowerCase(), "from ");
		fromSegment = StringUtils.substring(fromSegment, fromStartIndex);
		int orderStartIndex = StringUtils.lastIndexOf(fromSegment.toLowerCase(), " order ");
		int whereStartIndex = StringUtils.lastIndexOf(fromSegment.toLowerCase(), " where ");
		int rightParenthesisStartIndex = StringUtils.lastIndexOf(
				fromSegment.toLowerCase(), ")");
		if (orderStartIndex > rightParenthesisStartIndex
				&& orderStartIndex > whereStartIndex) {
			fromSegment = StringUtils.substring(fromSegment, 0, orderStartIndex);
		}
		int distinctStartIndex = StringUtils.indexOf(hql.toLowerCase(), "distinct ");
		if (distinctStartIndex > -1 && distinctStartIndex < fromStartIndex) {
			String distinctSegment = hql.substring(distinctStartIndex, fromStartIndex);
			distinctSegment = distinctSegment.trim();
			finalSql = (new StringBuilder()).append("select count(")
					.append(distinctSegment).append(") ").append(fromSegment)
					.toString();
		} else {
			finalSql = (new StringBuilder()).append("select count(*) ")
					.append(fromSegment).toString();
		}
		return finalSql;
	}

	@Override
	public List<T> findBySql(String sql, Class<T> clazz) {
		return findBySql(sql, new Object[0], clazz);
	}

	@Override
	public List<T> findBySql(String sql, Object parameter, Class<T> clazz) {
		Object[] parameters = new Object[]{ parameter };
		return findBySql(sql, parameters, clazz);
	}

	@Override
	public List<T> findBySql(String sql, Object[] parameters, Class<T> clazz) {
		Map<String, Object> paraMap = getParaMap(sql, parameters);
		sql = (String)paraMap.get("hql");
        parameters = (Object[])paraMap.get("paraObjects");
        Query query = getCurrentSession().createSQLQuery(sql).addEntity(clazz);
        this.setProperties(query, parameters);
        return query.list();
	}
	/**
	 * 设置参数
	 * @param query
	 * @param parameters
	 */
	private void setProperties(Query query, Object[] parameters) {
      if (parameters != null && parameters.length > 0) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i, parameters[i]);
			}
		}
	}

}
