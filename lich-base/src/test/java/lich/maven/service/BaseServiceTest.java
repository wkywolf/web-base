package lich.maven.service;

import java.util.List;

import lich.maven.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:applicationContext-base.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseServiceTest {
	
	private User user = null;

	@Autowired
	private BaseService<User> baseService;
	
	@Test
	public void find() {
		List<User> users = baseService.find("from User");
		for(User u : users) {
			System.out.println(u.toString());
		}
	}
	@Test
	public void save() {
		user = new User();
		user.setAccount("Lich3");
		user.setRealName("李生3");
		user.setPassword("123456");
		user.setSex("男");
		baseService.saveOrUpdate(user);
	}
	@Test
	public void update() {
		try{
			user = baseService.getEntityById("lich.maven.model.User", 1);
			System.out.println(user);
			user.setSex("男");
			baseService.update(user);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void findById() {
		try{
			user = baseService.findById(1);
			System.out.println(user);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
