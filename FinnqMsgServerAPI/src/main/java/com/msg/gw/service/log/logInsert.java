package com.msg.gw.service.log;

import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.msg.gw.config.AppConfiguration;

@Component
public class logInsert {

	SqlSession sqlSession;
	public logInsert() {
		// TODO Auto-generated constructor stub
		sqlSession = (SqlSession)AppConfiguration.getContext().getBean("sqlSession");
	}

	@Transactional
	public int insertdata(Map insertData) {
		return sqlSession.insert("insertSlackMsg", insertData);
	}
}
