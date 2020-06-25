package com.team.juseom.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Applier implements Serializable {
	int applierId;
	String userId;
	int shareId;
	
	public int getApplierId() {
		return applierId;
	}
	public void setApplierId(int applierId) {
		this.applierId = applierId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getSharedId() {
		return shareId;
	}
	public void setShareId(int shareId) {
		this.shareId = shareId;
	}
}
