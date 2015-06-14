package com.zl.pojo;

import java.io.Serializable;
import java.util.Date;

public class ChatQuery implements Serializable {

	/**  */
	private static final long serialVersionUID = -8199178486449651952L;

	private Long userId;

	private Page page;

	private Date since;

	private Date max;

	private Integer status;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getSince() {
		return since;
	}

	public void setSince(Date since) {
		this.since = since;
	}

	public Date getMax() {
		return max;
	}

	public void setMax(Date max) {
		this.max = max;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
