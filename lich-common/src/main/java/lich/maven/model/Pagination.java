package lich.maven.model;

import java.util.List;
/**
 * 分页工具类
 * @author Lich
 * 2014年7月4日 下午4:26:22
 * @param <T>
 */
public class Pagination<T> {
	
	private static final Integer PAGE_SIZE = 10; // 默认每页十条记录
	
	private List<T> resultList; // 分页结果集
	
	private Integer totalCount = 0; // 总记录数

	private Integer pageSize = 0; // 每页记录数
	
	private Integer currentPage = 0; // 当前页数
	
	private Integer startIndex = 0; // 起始记录索引
	
	private Integer totalPage = 0; // 总页数
	
	public Pagination(Integer currentPage, Integer pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageSize() {
		if(pageSize<0 || pageSize==0){
			pageSize = PAGE_SIZE;
		}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		if(currentPage < 0){
			currentPage = 1;
		}
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getStartIndex() {
		if(currentPage==0 || currentPage<0){
			currentPage = 1;
		}
		startIndex = (currentPage - 1) * pageSize;
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getTotalPage() {
		if(totalCount < 0){
			throw new IllegalArgumentException("每页的记录数量应当 > 0");
		}else if(totalCount == 0){
			totalPage = 1;
		}else if(totalCount%pageSize == 0){
			totalPage = totalCount / pageSize;
		}else{
			totalPage = totalCount/pageSize + 1;
		}
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

}
