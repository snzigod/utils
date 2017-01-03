package com.sn.utils.paginator.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * 包含“分页信息的List
 * 
 * 要得到页数请使toPaginator().getTotalPages();
 */
public class PageList<E> extends ArrayList<E> {
	private static final long serialVersionUID = 1412759446332294208L;

	private Paginator paginator;

	public PageList() {
	}

	public PageList(Collection<? extends E> c) {
		super(c);
	}

	public PageList(Collection<? extends E> c, Paginator p) {
		super(c);
		this.paginator = p;
	}

	public PageList(Paginator p) {
		this.paginator = p;
	}

	/**
	 * 得到分页器，通过Paginator可以得到总页数等
	 */
	public Paginator getPaginator() {
		return paginator;
	}

}
