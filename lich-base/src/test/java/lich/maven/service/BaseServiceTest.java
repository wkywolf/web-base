package lich.maven.service;

import java.io.Serializable;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseServiceTest {

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-dao.xml");
		BaseService<Serializable> baseService = (BaseService<Serializable>) ctx.getBean("baseService");
		baseService.findById(1);
	}

}
