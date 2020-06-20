package com.team.juseom.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.juseom.dao.ApplierDao;
import com.team.juseom.dao.mybatis.mapper.ApplierMapper;
import com.team.juseom.domain.Applier;

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

}
