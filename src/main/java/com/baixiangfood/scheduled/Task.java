package com.baixiangfood.scheduled;

import java.time.DayOfWeek;
import java.util.List;

import com.baixiangfood.model.SignIn;
import com.baixiangfood.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.baixiangfood.util.SignInObtainUtil;
 
@Component
public class Task {
	
	@Autowired
	private SignInObtainUtil signInObtainUtil;

	@Autowired
	private SignInService signInService;

	/* cron的数字含义依次是
	1秒（0~59）
	2分钟（0~59）
	3 小时（0~23）
	4 天（0~31）
	5 月（0~11）
	6 星期（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）
	年份（1970－2099）
	*/
	//注意: 目前云之家对外提供的所有获取签到记录接口有 高峰访问限制， 时间是每天的8:00-10:00和17:00-19:00，在这个时间段获取不到数据
	@Scheduled(cron = "0 43 12 * * ?")
	public void signInObtainTask() {
		//获取今天打开记录详情
		List<SignIn> listSignIn = signInObtainUtil.getSignInDetailByDay();
		signInService.saveSignInMsg(listSignIn);
	}


	public static void main(String[] args) {
		Task task = new Task();
		task.signInObtainTask();
	}
}
