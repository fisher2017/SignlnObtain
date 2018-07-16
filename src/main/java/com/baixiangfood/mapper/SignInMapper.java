package com.baixiangfood.mapper;

import com.baixiangfood.model.SignIn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SignInMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SignIn record);

    int insertSelective(SignIn record);

    SignIn selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SignIn record);

    int updateByPrimaryKey(SignIn record);

    List<SignIn> selectListByDay(@Param("dayString") String dayString);
}