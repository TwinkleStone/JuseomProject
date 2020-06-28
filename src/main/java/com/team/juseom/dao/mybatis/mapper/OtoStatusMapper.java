package com.team.juseom.dao.mybatis.mapper;

import com.team.juseom.domain.OtoStatus;

public interface OtoStatusMapper {
	void insertStatus(OtoStatus status);
	void updateSellerStatus(OtoStatus status);
	void updateBuyerStatus(OtoStatus status);
	OtoStatus getStatusByChattingRoomId(String chattingRoomId);

}
