package lich.maven.model;

import java.util.List;
/**
 * 分页工具类
 * @author Lich
 * 2014年7月4日 下午4:26:22
 * @param <T>
 */
public class Pagination<T> {
	
	private static final long PAGE_SIZE = 10; // 默认每页十条记录
	
	private List<T> resultList; // 分页结果集
	
	private long totalCount = 0L; // 总记录数

	private long pageSize = 1; // 每页记录数
	
	private long currentPage = 1; // 当前页数
	
	private long startIndex = 0; // 起始记录索引
	
	private long totalPage = 0; // 总页数
	
	public Pagination(long currentPage, long pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getPageSize() {
		if(pageSize<0 || pageSize==0){
			pageSize = PAGE_SIZE;
		}
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getCurrentPage() {
		if(currentPage < 0){
			currentPage = 1;
		}
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

	public long getStartIndex() {
		if(currentPage==0 || currentPage<0){
			currentPage = 1;
		}
		startIndex = (currentPage - 1) * pageSize;
		return startIndex;
	}

	public void setStartIndex(long startIndex) {
		this.startIndex = startIndex;
	}

	public long getTotalPage() {
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

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

}
