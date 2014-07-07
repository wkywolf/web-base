package lich.maven.dao.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lich.maven.dao.CommonDao;
import lich.maven.model.Pagination;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.Type;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class CommonDaoHibernate<T extends Serializable> implements CommonDao {
	
	private Class<T> entityClass;
	
	private SessionFactory sessionFactory;
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Object getObject(Serializable id) {
        Object o = getCurrentSession().get(entityClass, id);
        if(o == null)
            throw new ObjectRetrievalFailureException(entityClass, id);
        return o;
	}

	@Override
	public Object loadObject(Serializable id) {
        Object o = getCurrentSession().load(entityClass, id);
        if(o == null)
            throw new ObjectRetrievalFailureException(entityClass, id);
        return o;
	}

	@Override
	public void saveObject(Object obj) {
		getCurrentSession().save(obj);
//		getCurrentSession().flush();
	}

	@Override
	public void refreshObject(Object obj) {
		getCurrentSession().refresh(obj);
	}

	@Override
	public void updateObject(Object obj) {
		getCurrentSession().saveOrUpdate(obj);
//		getCurrentSession().flush();
	}

	@Override
	public void removeObject(Serializable id) {
		getCurrentSession().delete(getObject(id));

	}

	@Override
	public void removeObject(Object obj) {
		getCurrentSession().delete(obj);
	}

	@Override
	public List find(String hql, Pagination pagination) throws Exception {
		try {
			if (pagination == null)
				return find(hql);
		} catch (Exception he) {
			throw he;
		}
		try {
			List rt = find(hql, new Object[0], new Type[0], pagination);
			return rt;
		} catch (Exception he) {
			throw he;
		}
	}

	@Override
	public List find(String hql, Object obj, Pagination pagination) throws Exception {
        if(pagination == null)
            return findbyhsql(hql, obj);
        List rt = find(hql, new Object[] { obj }, pagination);
        return rt;
	}

	@Override
	public List find(String hql, Object[] objs, Pagination pagination) throws Exception {
        Map paraMap = getParaMap(hql, objs);
        hql = (String)paraMap.get("hql");
        objs = (Object[])(Object[])paraMap.get("paraObjects");
        if(pagination == null)
            return getCurrentSession().createQuery(hql).setProperties(paraMap).list();
        pagination.setTotalCount(loadTotalSize(hql, objs));
        Query sqlQuery = null;
        List ls = null;
		try {
			sqlQuery = getCurrentSession().createQuery(hql);
			if (objs != null && objs.length > 0) {
				for (int i = 0; i < objs.length; i++) {
					sqlQuery.setParameter(i, objs[i]);
				}
			}
			sqlQuery.setFirstResult((int) pagination.getStartIndex() - 1).setMaxResults((int)pagination.getPageSize());
			ls = sqlQuery.list();
			if (ls == null)
				ls = new ArrayList(0);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public List find(String hql) {
        return findbyhsql(hql, new Object[0]);
	}

	@Override
	public List findbyhsql(String hql, Object parameter) {
        Object parameters[] = new Object[1];
        parameters[0] = parameter;
        Map paraMap = getParaMap(hql, parameters);
        hql = (String)paraMap.get("hql");
        List rt = getCurrentSession().createQuery(hql).setProperties(paraMap).list();
        return rt;
	}

	@Override
	public List findbyhsql(String hql, Object[] parameters) {
        Map paraMap = getParaMap(hql, parameters);
        hql = (String)paraMap.get("hql");
        List rt = getCurrentSession().createQuery(hql).setProperties(paraMap).list();
        return rt;
	}

	@Override
	public Object loadById(String entityName, Long id) {
        return getCurrentSession().get(entityName, id);
	}

	@Override
	public List findAll() {
        List rt = getCurrentSession().createQuery("from " + this.entityClass.getName()).list();
        return rt;
	}

	public static Map getParaMap(String hql, Object objs[]) {
		Map paraMap = new HashMap();
		if (hql.toLowerCase().indexOf("to_date") >= 0) {
			try {
				String leftSql = hql.toLowerCase();
				String mySql = hql;
				int loc = 0;
				int old_index = 0;
				List paraList = new ArrayList();
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

	protected long loadTotalSize(String hql, Object objs[]) throws Exception {
		long count = 0L;
		String midSql = null;
		try {
			midSql = getCountSql(hql);
			Query sqlQuery = getCurrentSession().createQuery(midSql);
			if (objs != null && objs.length > 0) {
				for (int i = 0; i < objs.length; i++)
					sqlQuery.setParameter(i, objs[i]);

			}
			List ls = sqlQuery.list();
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
	public List find(String hql, Object[] objs, Type[] atype, Pagination pagination) throws Exception {
		 return find(hql, objs, pagination);
	}

	@Override
	public List findBySql(String sql) {
		return this.getCurrentSession().createSQLQuery(sql).list();
	}

	@Override
	public List findBySql(String sql, Map paramsMap) {
		if(paramsMap!=null && paramsMap.size()>0){
			return this.getCurrentSession().createSQLQuery(sql).setProperties(paramsMap).list();
		}else{
			return this.findBySql(sql);
		}
	}
	
	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
