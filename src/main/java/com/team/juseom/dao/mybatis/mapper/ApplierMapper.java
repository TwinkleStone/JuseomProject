package com.team.juseom.dao.mybatis.mapper;

import java.util.List;

import com.team.juseom.domain.Applier;

public interface ApplierMapper {

	public void insertApplier(Applier applier);
	public void updatePeopleNumber(int shareId);
	public int searchApplier(int applierId, String userId);
	public void insertWinner(int shareId, String shareNumber);
	public List<String> getWinnerUserIds(int shareId);
}
