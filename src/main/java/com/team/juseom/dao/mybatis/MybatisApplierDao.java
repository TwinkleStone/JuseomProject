package com.team.juseom.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.juseom.dao.ApplierDao;
import com.team.juseom.dao.mybatis.mapper.ApplierMapper;
import com.team.juseom.domain.Applier;
import com.team.juseom.domain.Share;

@Repository
public class MybatisApplierDao implements ApplierDao {
	
	@Autowired
	private ApplierMapper applierMapper;

	@Override
	public void insertApplier(Applier applier) {
		applierMapper.insertApplier(applier);
		
	}

	@Override
	public void updatePeopleNumber(Applier applier) {
		int shareId = applier.getSharedId();
		applierMapper.updatePeopleNumber(shareId);
	}

	@Override
	public int searchApplier(Applier applier) {
		int shareId = applier.getSharedId();
		String userId = applier.getUserId();
		return applierMapper.searchApplier(shareId, userId);
	}

	@Override
	public List<Applier> getWinner(Share share) {
		int shareId = share.getShareId();
		String shareNumber = share.getShareNumber();
		return applierMapper.getWinner(shareId, shareNumber);
	}

}
