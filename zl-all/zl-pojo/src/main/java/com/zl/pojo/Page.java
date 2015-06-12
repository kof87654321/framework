package com.zl.pojo;

import java.io.Serializable;

/**
 * 分页对象.
 * 
 */
public final class Page implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 分页查询开始记录位置.
	 */
	private int begin;

	/**
	 * 每页显示记录数.
	 */
	private int length = 20;

	public Page() {
	}

	public Page(int begin, int length) {
		this.begin = begin;
		this.length = length;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Page setPageByPageNoAndPageSize(int pageNo, int pageSize) {
		if (pageNo < 1) {
			pageNo = 1;
		}
		if (pageSize < 1) {
			pageSize = 10;
		}
		this.begin = (pageNo - 1) * pageSize;
		this.length = pageSize;
		return this;
	}

}