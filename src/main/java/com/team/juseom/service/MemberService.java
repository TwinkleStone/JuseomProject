package com.team.juseom.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.team.juseom.controller.LoginCommand;
import com.team.juseom.controller.MemberCommand;
import com.team.juseom.domain.User;

@Service
public class MemberService {
	private Map<String, User> memberMap = new HashMap<String, User>();

	public MemberService() {
	}

	public List<User> getMembers() {
		return new ArrayList<User>(memberMap.values());
	}

	public User getUser(String memberId) {
		return memberMap.get(memberId);
	}

	public User registNewMember(MemberCommand memRegReq) throws ExistUserIdException {
		if (memberMap.containsKey(memRegReq.getUserId()))
			throw new ExistUserIdException();

		User mi = new User(memRegReq.getUserId(), memRegReq.getPassword(), memRegReq.getName(), memRegReq.getPhone(),
				memRegReq.getCommName(), memRegReq.getProfilePicUrl(), memRegReq.getAddress());
		return mi;
	}

	public void deleteMember(String memberId) {
		memberMap.remove(memberId);
	}

	public void authenticate(LoginCommand loginCommand) throws AuthenticationException {
		User mi = getUser(loginCommand.getUserId());
		if (mi == null || !mi.getPassword().equals(loginCommand.getPassword())) {
			throw new AuthenticationException();
		}
	}
}
