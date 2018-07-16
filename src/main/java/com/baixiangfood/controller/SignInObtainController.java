package com.baixiangfood.controller;

import com.baixiangfood.model.SignIn;
import com.baixiangfood.service.SignInService;
import com.baixiangfood.util.SignInObtainUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SignInObtainController {

	@Autowired
	private SignInObtainUtil signInObtainUtil;

	@Autowired
	private SignInService signInService;
	//如果因服务器故障等问题，导致某一时间段打卡记录没有被获取过来，可以手动触发来获取这一时间段的数据

	@RequestMapping("ttt")
	public String signInObtainManual() {
		String dayString = "2018-07-12";
		List<SignIn> listSignIn = signInObtainUtil.getSignInDetailByDay(dayString);
		signInService.saveSignInMsg(listSignIn,dayString);
		return "ok";
	}
	
	
}
