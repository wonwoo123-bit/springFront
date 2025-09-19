package com.spring.application.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.application.dto.MenuVO;

@Repository
public class MenuDAOImpl implements MenuDAO{

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<MenuVO> selectMainMenu() throws SQLException {
        List<MenuVO> menuList = sqlSession.selectList("Menu-Mapper.selectMainMenu");
        return menuList;
    }
    @Override
    public List<MenuVO> selectSubMenu(String mCode) throws SQLException {
    return sqlSession.selectList("Menu-Mapper.selectSubMenu", mCode); // ★ 이게 핵심
}
    
    @Override
    public MenuVO selectMenuByMcode(String mCode) throws SQLException {
        MenuVO menu = sqlSession.selectOne("Menu-Mapper.selectMenuByMcode",mCode);
        return menu;
    }

    @Override
    public MenuVO selectMenuByMname(String mName) throws SQLException {
        MenuVO menu = sqlSession.selectOne("Menu-Mapper.selectMenuByMname",mName);
        return menu;
    }

    

}
