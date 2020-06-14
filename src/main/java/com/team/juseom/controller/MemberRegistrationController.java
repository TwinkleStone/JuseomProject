package com.team.juseom.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.team.juseom.domain.Address;
import com.team.juseom.domain.User;
import com.team.juseom.service.ExistUserIdException;
import com.team.juseom.service.MemberService;

@Controller
@RequestMapping("/member/register")
@SessionAttributes("regReq")
public class MemberRegistrationController {
	private static final String STEP1_FORM_VIEW = "member/regForm1";
	private static final String STEP2_FORM_VIEW = "member/regForm2";
	private static final String STEP3_VIEW = "member/regConfirm";
	private static final String RESULT_VIEW = "member/registered";
	
	@Autowired
	private MemberService memberService;

/*	@ModelAttribute("performerTypes")
	public List<String> referencePerformerTypes() {
		List<String> types = new ArrayList<String>();
		types.add("Instrumentalist");
		types.add("Singer");
		types.add("PoeticJuggler");
		types.add("OneManBand");
		return types;
	}     --> 이런 방법도 가능하지만 아래와 같이 enum을 사용하는 방법도 있음
*/

	@ModelAttribute("regReq")
	public MemberCommand formBacking() {
		MemberCommand memRegReq = new MemberCommand();
		Address address = new Address();
		address.setCity("서울");
		memRegReq.setAddress(address);
		return memRegReq;
	}   

/*
 * 초기화가 간단할 경우 아래와 같이 form 요청 처리 method에서 해도 됨 
 * 
	@RequestMapping(method = RequestMethod.GET)
	public String form(@ModelAttribute("memberRegistInfo") MemberRegistRequest memRegReq) {
		Address address = new Address();	// Address 객체 생성 필요!!
		address.setCity("서울");
		memRegReq.setAddress(address);
		memRegReq.setLength("20");
		return MEMBER_REGISTRATION_FORM;
	}
*/
	
	@GetMapping("/step1")		// step1 요청
	public String step1() {
		return STEP1_FORM_VIEW;	// step1 form view로 이동
	}
	
	@GetMapping("/step2")		// step3 -> step2 이동	
	public String step2FromStep3() {
		return STEP2_FORM_VIEW;	// step2 form view로 이동
	}
	
	@PostMapping("/step2")		// step1 -> step2 이동	
	public String step2(
			@Valid @ModelAttribute("regReq") MemberCommand memRegReq,
			BindingResult result) {
		System.out.println("command 객체: " + memRegReq);
		
		// session에 저장된 regReq 객체에 저장된 입력 값 검증
		//  - 위에서 @Valid를 통해 Hibernate Validator를 사용함
		//  - MemberRegistValidator를 직접 구현하여 사용할 경우 아래 코드 실행
		//  - new MemberRegistValidator().validate(memRegReq, bindingResult);
		
		if (result.hasFieldErrors("id") ||
			result.hasFieldErrors("name") ||
			result.hasFieldErrors("password") ||
			result.hasFieldErrors("confirmPassword") ||
			result.hasFieldErrors("phone") ||
			result.hasFieldErrors("address.street") ||
			result.hasFieldErrors("address.city") ||
			result.hasFieldErrors("address.zipcode")) {		
			return STEP1_FORM_VIEW;	// 검증 오류 발생 시 step1 form view로 이동
		}
		return STEP2_FORM_VIEW;		// 오류 없으면 step2 form view로 이동
	}

	@PostMapping("/step3")		// step2 -> step3 이동
	public String step3(
			@Valid @ModelAttribute("regReq") MemberCommand memRegReq,
			BindingResult result, Model model) {		
		System.out.println("command 객체: " + memRegReq);
		
		// session에 저장된 regReq 객체에 저장된 입력 값 검증
		// 위에서 @Valid를 통해 Hibernate Validator를 사용함
		// MemberRegistValidator를 직접 구현하여 사용할 경우 아래 코드 실행
		// new MemberRegistValidator().validate(memRegReq, bindingResult);	
		
		if (result.hasFieldErrors("type") ||
			result.hasFieldErrors("title") ||
			result.hasFieldErrors("length") ||
			result.hasFieldErrors("newPerformer")) {		
			return STEP2_FORM_VIEW;		// 검증 오류 발생 시 step2 form view로 이동
		}
		return STEP3_VIEW;		// 오류 없으면 step3 form view로 이동
	}
	
	@PostMapping("/done")		// step3 -> done 이동
	public String regist(
			@ModelAttribute("regReq") MemberCommand memRegReq, BindingResult result,
			Model model, SessionStatus sessionStatus) {	
		System.out.println("command 객체: " + memRegReq);
			
		User mi;
		try {
			mi = memberService.registNewMember(memRegReq);
		} catch (ExistUserIdException e) {
			result.rejectValue("id", "existUserId", "이미 존재하는 아이디입니다.");
			return STEP1_FORM_VIEW;		// ID 존재 시 step1 form view로 이동
		}
		
		model.addAttribute("newMember", mi);
		
		List<User> members = memberService.getMembers();
		model.addAttribute("members", members);
		
		sessionStatus.setComplete();	// session 종료		
		return RESULT_VIEW;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

}
