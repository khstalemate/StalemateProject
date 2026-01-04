package com.stale.mate.board.model.dto;

public class Pagination {
	private int currentPage;
	private int listCount;
	private int limit = 5;
	
	private int pageSize = 5;
	private int maxPage;
	private int startPage;
	
	private int endPage;
	private int prevPage;
	private int nextPage;

	public Pagination(int currentPage, int listCount) {
		super();
		this.currentPage = currentPage;
		this.listCount = listCount;
		
		calculate();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getListCount() {
		return listCount;
	}

	public int getLimit() {
		return limit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}	

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	@Override
	public String toString() {
		return "Pagination [currentPage=" + currentPage + ", listCount=" + listCount + ", limit=" + limit
				+ ", pageSize=" + pageSize + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prevPage=" + prevPage + ", nextPage=" + nextPage + "]";
	}
	
	private void calculate() {
		maxPage = (int)Math.ceil((double)listCount / limit);
		startPage = (currentPage - 1)/pageSize * pageSize + 1;
		endPage = startPage + pageSize - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		if(currentPage <= pageSize) {
			prevPage = 1;
		} else {
			prevPage = startPage - 1;
		}
		
		if(endPage == maxPage) {
			nextPage = maxPage;
		} else {
			nextPage = endPage + 1;
		}
	}
}