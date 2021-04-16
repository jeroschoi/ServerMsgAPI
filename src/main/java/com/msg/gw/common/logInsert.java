package com.msg.gw.common;

import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.msg.gw.config.AppConfig;

@Component
public class logInsert {

//    @Autowired
//	SqlSession sqlSession;
//	public logInsert() {
//		// TODO Auto-generated constructor stub
//		// sqlSession = (SqlSession) AppConfig.getContext().getBean("sqlSession");
//	}
//
//	@Transactional
//	public int insertdata(Map insertData) {
//		return sqlSession.insert("insertSlackMsg", insertData);
//	}
}
