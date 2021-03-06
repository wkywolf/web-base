package com.lich.base.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lich.base.dao.CommonDao;
import com.lich.base.model.User;
import com.lich.base.service.BaseService;
import com.lich.common.model.Pagination;

@ContextConfiguration(locations = { "classpath:applicationContext-base.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseServiceTest {
	
	private User user = null;
	
	private List<User> users = null;
	
	private Pagination<User> pagination = null;
	
	@Autowired
	@Qualifier("BaseServiceImpl")
	private BaseService<User> baseService;
	@Autowired
	private CommonDao<User> commonDao;
	
	@Test
	public void findByHql() {
		users = (List<User>) baseService.findByHsql("from User");
		for(User u : users) {
			System.out.println(u.toString());
		}
	}
//	@Test
//	public void getById() {
////		user = baseService.findById(User.class, 1);
//		user = baseService.findById("com.lich.common.model.User", 2);
//		System.out.println(user);
//	}
//	@Test
//	public void save() {
//		user = new User();
//		user.setAccount("Lich4");
//		user.setRealName("李生4");
//		user.setPassword("123456");
//		user.setSex("男");
//		baseService.saveOrUpdate(user);
//	}
//	@Test
//	public void update() {
//		try{
//			user = baseService.findById("com.lich.common.model.User", 1);
//			System.out.println(user);
//			user.setSex("女");
//			baseService.update(user);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void findById() {
//		try{
//			user = baseService.findById(User.class, 1);
//			System.out.println(user);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void remove() {
//		user = baseService.findById(User.class, 7);
//		baseService.delete(user);
//	}
//	@Test
//	public void removeById() {
//		baseService.deleteById(User.class, 4);
//	}
//	@Test
//	public void findHqlPagination() {
//		Integer currentPage = 1;
//		Integer pageSize = 2;
//		pagination = new Pagination<User>(currentPage, pageSize);
//		String hql = "from User";
//		try {
//			users = baseService.find(hql, pagination);
//			for(User u : pagination.getResultList()) {
//				System.out.println(u);
//			}
//			System.out.println(pagination.getStartIndex() + "__" + pagination.getTotalPage() + "__" + pagination.getTotalCount());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void findHqlAndParamsPagination() {
//		Integer currentPage = 1;
//		Integer pageSize = 2;
//		pagination = new Pagination<User>(currentPage, pageSize);
//		String hql = "from User where account=? and sex=?";
//		try {
//			users = commonDao.find(hql, new Object[]{ "Lich", "女" }, pagination);
//			for(User u : pagination.getResultList()) {
//				System.out.println(u);
//			}
//			System.out.println(pagination.getStartIndex() + "__" + pagination.getTotalPage() + "__" + pagination.getTotalCount());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void findHqlByParams() {
//		String hql = "from User where account=? and sex=?";
//		try{
//			users = commonDao.findByHsql(hql, new Object[]{ "Lich", "女"});
//			for(User u : users) {
//				System.out.println(u);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void findAll() {
//		users = commonDao.findAll(User.class);
//		for(User u : users) {
//			System.out.println(u);
//		}
//	}
//	@Test
//	public void findBySql() {
//		try{
////			String sql = "select * from user";
////			users = commonDao.findBySql(sql, User.class);
////			String sql = "select * from user where account=?";
////			users = commonDao.findBySql(sql, "Lich", User.class);
//			String sql = "select * from user where account=? and sex=?";
//			users = commonDao.findBySql(sql, new Object[]{ "Lich", "女"}, User.class);
//			for(User u : users) {
//				System.out.println(u);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}

}
