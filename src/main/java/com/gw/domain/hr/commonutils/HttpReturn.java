/**
 * 
 */
package com.gw.domain.hr.commonutils;

/**
 * @author kuangsheng@renrenche.com 2016年5月17日
 * 
 */
public class HttpReturn {
	private int status;
	private String text;

	public HttpReturn() {
		super();
	}

	public HttpReturn(int status, String text) {
		super();
		this.status = status;
		this.text = text;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
