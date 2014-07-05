package lich.maven.util;

import java.util.ArrayList;
import java.util.List;

import lich.maven.model.Pagination;

import org.junit.Test;

public class PaginationTest {

	@Test
	public void pageTest() {
		Integer totalCount = 10;
		Integer currentPage = 1;
		Integer pageSize = 5;
		int len = totalCount;
		if(totalCount > pageSize){
			len = pageSize;
		}
		Pagination<String> pagination = new Pagination<String>(currentPage, pageSize);
		pagination.setTotalCount(totalCount);
		List<String> strs = new ArrayList<String>();
		for(int i=0; i<len; i++) {
			strs.add("record" + (i+1));
		}
		pagination.setResultList(strs);
		
		System.out.println("总记录数：" + pagination.getTotalCount());
		System.out.println("总页数：" + pagination.getTotalPage());
		System.out.println("当前页码：" + pagination.getCurrentPage());
		System.out.println("每页显示： " + pagination.getPageSize() + " 条记录");
		System.out.println("开始索引：" + pagination.getStartIndex());
		System.out.println("查询记录结果集：");
		int i = 0;
		for(String str : pagination.getResultList()) {
			i++;
			System.out.println("查询记录" + i + ": " + str);
		}
	}

}
