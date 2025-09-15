package com.spring.application.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.application.dto.MenuVO;

public interface MenuService {
    List<MenuVO> getMenuList() throws SQLException;

    MenuVO getMenuByMcode(String mCode) throws SQLException;

    MenuVO getMenuByMname(String mName) throws SQLException;
}
