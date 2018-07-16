package com.baixiangfood.service;

import com.baixiangfood.mapper.SignInMapper;
import com.baixiangfood.model.SignIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SignInService {

    @Autowired
    private SignInMapper signInMapper;

    /**
     * 插入当前的数据
     * @param list
     */
    public void saveSignInMsg(List<SignIn> list){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dayString = format.format(new Date());
        saveSignInMsg(list,dayString);
    }

    /**
     * 插入指定某一天的数据，要进行判重，数据库中已经存在的，不要再次插入，判断依据为openid和时间
     * @param list
     * @param dayString
     */
    public void saveSignInMsg(List<SignIn> list,String dayString){
        List<SignIn> listOrg =  signInMapper.selectListByDay(dayString);
        list.removeAll(listOrg);//求差集，需要重写SignIn的equals方法
        for(SignIn msg : list) {
            signInMapper.insert(msg);
        }
    }


    //分页使用例子
//    public List<GoodsType> getList(int pageNum, int pageSize) throws Exception {
//        //使用分页插件,核心代码就这一行
//        PageHelper.startPage(pageNum, pageSize);
//        // 获取
//        List<GoodsType> typeList = typeDao.getList();
//        return typeList;
//    }

}
