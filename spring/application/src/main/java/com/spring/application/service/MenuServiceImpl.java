package com.spring.application.service;

import java.sql.SQLException;
import java.util.List;


import org.springframework.stereotype.Service;

import com.spring.application.dao.MenuDAO;
import com.spring.application.dto.MenuVO;

@Service
public record MenuServiceImpl(MenuDAO menuDAO) implements MenuService{

    @Override
    public List<MenuVO> getMenuList() throws SQLException {
        List<MenuVO> menuList = menuDAO.selectMainMenu();

        for(MenuVO main : menuList){
            List<MenuVO> subMenuList = menuDAO.selectSubMenu(main.getMcode());

            main.setSubMenuList(subMenuList);
        }
        return menuList;
    }

    @Override
    public MenuVO getMenuByMcode(String mCode) throws SQLException {
        return menuDAO.selectMenuByMcode(mCode);
    }

    @Override
    public MenuVO getMenuByMname(String mName) throws SQLException {
        return menuDAO.selectMenuByMname(mName);
    }


}
