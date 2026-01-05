package com.stale.mate.email.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmailMapper {

	int updateAuthKey(Map<String, String> map);

	int insertAuthKey(Map<String, String> map);

	int checkAuthKey(Map<String, String> map);
	
	int userCheck(@Param("memberId") String memberId,
			      @Param("memberPhone") String memberPhone);

	int resetPassword(@Param("memberId") String memberId, 
					  @Param("encPw") String encPw);

}
