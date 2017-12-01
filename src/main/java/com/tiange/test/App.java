package com.tiange.test;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.tiange.model.User;

public class App {
    private static SqlSessionFactory sqlSession;
    private static Reader reader;
    
    static {
    	try {
			reader = Resources.getResourceAsReader("configuration.xml");
			sqlSession = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static SqlSessionFactory getSession() {
    	return sqlSession;
    }
    
    public static void main(String[] args) {
		SqlSession session = sqlSession.openSession();
		try {
			User user = (User) session.selectOne("com.tiange.dao.UserMapper.getUserById", 1);
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
