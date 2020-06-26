package com.team.juseom.dao;

import java.util.List;

import com.team.juseom.domain.Applier;
import com.team.juseom.domain.Share;

public interface ApplierDao {

	void insertApplier(Applier applier);
	void updatePeopleNumber(Applier applier);
	int searchApplier(Applier applier);
	void insertWinner(Share share);
	List<String> getUserIds(int shareId);
}
