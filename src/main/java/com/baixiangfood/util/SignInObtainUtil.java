package com.baixiangfood.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baixiangfood.model.SignIn;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Component
public class SignInObtainUtil {
	  
	
	//从配置文件读取eid
	@Value("${com.baixiangfood.eid}")
	public String EID; //团队EID
	
	//从配置文件读取签到数据密匙
	@Value("${com.baixiangfood.signDataKey}")
	public String SIGN_DATA_KEY;  //签到数据密匙
	
	//Auth2.0接口
	private static String AUTH2_0_ACCESSTOKEN_URL = "https://www.yunzhijia.com/gateway/oauth2/token/getAccessToken";
	private static String ACCESSTOKEN_SCOPE = "resGroupSecret";
	
	//签到信息接口 获取移动签到明细
	private static String SIGN_IN_DETAIL_URL = "https://www.yunzhijia.com/gateway/attendance-data/v1/clockIn/listReplaceClockIn?accessToken=";
	
	//签到信息接口 按具体日期查询签到明细
	private static String SIGN_IN_DETAIL_DAY_URL = "https://www.yunzhijia.com/gateway/attendance-data/v1/clockIn/day/list?accessToken=";
	
	
	
	/**
	 * 获取token字符串,为后面调用签到接口做准备
	 * @return
	 */
	public String getAccessToken () {
		//组织参数信息
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("eid", EID);
		jsonParam.put("secret", SIGN_DATA_KEY);
		jsonParam.put("timestamp", new Date().getTime());
		jsonParam.put("scope", ACCESSTOKEN_SCOPE); 
		
		//获取数据
		JSONObject ret = getAccessTokenJson(jsonParam);
		if(ret.getBooleanValue("success")) {
			return ret.getJSONObject("data").getString("accessToken");
		}
		return null;
	}
	/**
	 * 获取token相关信息，返回json对象
	 * @return
	 */
	private JSONObject getAccessTokenJson(JSONObject jsonParam) {
		return HttpsUtils.doPost(AUTH2_0_ACCESSTOKEN_URL, jsonParam);
	}
	
	
	/**
	 * 获取当天签到记录
	 * @return 返回List
	 */
	public List<SignIn> getSignInDetailByDay() {
		return jsonResult2List(getSignInDetailJsonByDay());
	}

	/**
	 * 获取指定某一天的签到记录
	 * @param dayString  指定的天，格式为 yyyy-MM-dd
	 * @return 返回List
	 */
	public List<SignIn> getSignInDetailByDay(String dayString) {
		return jsonResult2List(getSignInDetailJsonByDay(dayString));
	}
	
	private List<SignIn> jsonResult2List(JSONObject ret) {
		if(ret == null) {
			return null;
		}
		List<SignIn> list = new ArrayList<>();
		if(ret.getBoolean("success")) {
			JSONArray jsonArray = ret.getJSONArray("data");
			for(int i=0;i<jsonArray.size();i++) {
				JSONObject json = (JSONObject)jsonArray.get(i);
				SignIn signIn = new SignIn();
				signIn.setPosition(json.getString("position"));
				signIn.setBssid(json.getString("bssid"));
				signIn.setSsid(json.getString("ssid"));
				signIn.setDay(json.getString("day"));
				signIn.setTime( new Date(json.getLong("time")));
				signIn.setOpenid(json.getString("openId"));
				signIn.setPositionresult(json.getString("positionResult"));
				signIn.setDepartment(json.getString("department"));
				signIn.setUsername(json.getString("userName"));
				signIn.setPhotoid(json.getString("photoId"));
				signIn.setRemark(json.getString("remark"));
				
				JSONObject obj = json.getJSONObject("approveResult");
				if(obj != null) {
					signIn.setApproveid(obj.getString("approveId"));
					signIn.setApproveuseropenid(obj.getString("approveUserOpenId"));
					Long approveTime = obj.getLongValue("approveTime");
					if(approveTime == 0 ) {
						signIn.setApprovetime(null);
					} else {
						signIn.setApprovetime(new Date(approveTime));
					}
					signIn.setApprovestatus(obj.getString("approveStatus"));
					signIn.setApprovetype(obj.getString("approveType"));
				} else {
					signIn.setApproveid("");
					signIn.setApproveuseropenid("");
					signIn.setApprovetime(null);
					signIn.setApprovestatus("");
					signIn.setApprovetype("");
				}
				list.add(signIn);
			}
			return list;
		}
		return null;
	}
	/**
	 * 获取当天的签到记录
	 * @return 返回json对象
	 */
	public JSONObject getSignInDetailJsonByDay() { 
		return getSignInDetailJsonByDay(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	}
	
	/**
	 * 获取某天的签到记录 
	 * @param dayString  日期的字符串,格式 yyyy-MM-dd
	 * @return 返回json对象
	 */
	public JSONObject getSignInDetailJsonByDay(String dayString) {
		String accessToken = getAccessToken();
		if(accessToken != null) {
			JSONObject jsonParam = new JSONObject();
			jsonParam.put("eid", EID);
			jsonParam.put("day", dayString);
			return getSignInDetailOneDayJson(accessToken,jsonParam);
		}
		return null;
	}
	
	/**
	 * 根据参数，获取某一天的签到数据
	 * @param accessToken
	 * @param jsonParam
	 * @return
	 */
	private JSONObject getSignInDetailOneDayJson(String accessToken,JSONObject jsonParam) {
		return HttpsUtils.doPost(SIGN_IN_DETAIL_DAY_URL+accessToken, jsonParam);
	}
	
	
	
	
//	这个接口掉获取不到数据
//	public   JSONObject getSignInDetailToday() {
//		String accessToken = getAccessToken();
//		if(accessToken != null) {
//			JSONObject jsonParam = new JSONObject();
//			jsonParam.put("workDateFrom", "2018-07-12 01:01:01");
//			jsonParam.put("workDateTo", "2018-07-13 01:01:01");
//			jsonParam.put("eid", EID);
//			return getSignInDetailJson(accessToken,jsonParam);
//		}
//		return null;
//	}
//	/**
//	 * 
//	 * @param jsonParam
//	 * @return
//	 */
//	private   JSONObject getSignInDetailJson(String accessToken,JSONObject jsonParam) {
//		return HttpsUtils.doPost(SIGN_IN_DETAIL_URL+accessToken, jsonParam);
//	}
 
}
