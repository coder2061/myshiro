package com.github.bean;

import java.io.Serializable;

/**
 * 请求信息
 * 
 * @author jiangyf
 * @date 2017年7月5日 上午11:37:47
 */
public class RequestInfo implements Serializable {
	private static final long serialVersionUID = -6891502424683399824L;

	/**
	 * 请求地址
	 */
	private String ip;

	/**
	 * 请求次数
	 */
	private Integer count;

	/**
	 * 请求时间
	 */
	private Long createTime;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public RequestInfo() {
	}

	public RequestInfo(String ip, Integer count, Long createTime) {
		this.ip = ip;
		this.count = count;
		this.createTime = createTime;
	}

	public static RequestInfo instance(String ip, Integer count, Long createTime) {
		return new RequestInfo(ip, count, createTime);
	}

}
