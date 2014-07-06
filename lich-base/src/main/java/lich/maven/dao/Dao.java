package lich.maven.dao;

import java.io.Serializable;

/**
 * 基础CRUD Dao接口
 * @author Lich
 * 2014年7月6日 下午5:55:38
 */
public interface Dao<T extends Serializable> {
	
    public abstract Object getObject(Serializable id);

    public abstract Object loadObject(Serializable id);

    public abstract void saveObject(Object obj);

    public abstract void refreshObject(Object obj);

    public abstract void updateObject(Object obj);

    public abstract void removeObject(Serializable id);

    public abstract void removeObject(Object obj);

}
