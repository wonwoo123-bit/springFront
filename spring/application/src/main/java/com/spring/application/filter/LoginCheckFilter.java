package com.spring.application.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/*"})
public class LoginCheckFilter implements Filter {

    private static final String[] eUrls = {"localhost"};

    @Override
    public void destroy() {
        System.out.println("Filter 소멸");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;     // Request 꺼내기
        HttpServletResponse res = (HttpServletResponse) response;  // Response 꺼내기

		String requestURI = req.getRequestURI(); // 수행되는 URI
		
        if (isFilterUrl(requestURI)) 
            System.out.println("Request 방향 Filter 수행" + requestURI);
            chain.doFilter(request, response);                         // Filter 실행 기준이 되어주는 체이닝(chanining)                            
        }
    private boolean isFilterUrl(String requestURI){
        return !PatternMatchUtils.simpleMatch(eUrls, requestURI);
    }
        

    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter 등록");
    }
    
    
}
