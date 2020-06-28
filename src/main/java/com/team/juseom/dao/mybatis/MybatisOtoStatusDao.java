package com.team.juseom.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.juseom.dao.OtoStatusDao;
import com.team.juseom.dao.mybatis.mapper.OtoStatusMapper;
import com.team.juseom.dao.mybatis.mapper.RateMapper;
import com.team.juseom.domain.OtoStatus;

@Repository
public class MybatisOtoStatusDao implements OtoStatusDao {

	@Autowired
	private OtoStatusMapper statusMapper;
	
	@Override
	public void insertStatus(OtoStatus status) {
		statusMapper.insertStatus(status);
		
	}

	@Override
	public void updateSellerStatus(OtoStatus status) {
		statusMapper.updateSellerStatus(status);
	}

	@Override
	public void updateBuyerStatus(OtoStatus status) {
		statusMapper.updateBuyerStatus(status);
	}

	@Override
	public OtoStatus getStatusByChattingRoomId(String chattingRoomId) {
		return statusMapper.getStatusByChattingRoomId(chattingRoomId);
		
	}

}
