package com.team.juseom.dao;

import com.team.juseom.domain.OtoStatus;

public interface OtoStatusDao {
	void insertStatus(OtoStatus status);
	void updateSellerStatus(OtoStatus status);
	void updateBuyerStatus(OtoStatus status);
	OtoStatus getStatusByChattingRoomId(String chattingRoomId);
}
