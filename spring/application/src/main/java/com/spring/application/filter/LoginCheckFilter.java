package com.spring.application.filter;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import com.spring.application.dto.MemberVO;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/*", initParams = {@WebInitParam(name = "exclude", value = "/bootstrap,/js,/common")})
public class LoginCheckFilter implements Filter {

    private List<String> exURLs  = new ArrayList<String>();

    @Override
    public void destroy() {
        System.out.println("Filter 소멸");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 강제로 형 변환하여 웹구동하며 사용 가능하도록 타입을 HttpServlet으로 바꿔줌.
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;

        //제외할 URL 확인하기
        String reqUrl = httpReq.getRequestURI().replace(httpReq.getContextPath(),"");

        if (excludeCheck(reqUrl)){
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpReq.getSession();
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

        if (loginUser != null) {
            chain.doFilter(request, response);
            return;
        }

        //로그인 필요한 것을 눌렀을 때 로그인 이후 그 url 주소로 돌아갈수 있도록 해주는 로직
        String contextPath = httpReq.getContextPath();
        String retUrl = httpReq.getRequestURI().replace(contextPath, "");

        String queryString = httpReq.getQueryString();
        if (queryString != null) {
            retUrl += "?" + queryString;
        }

        String url = httpReq.getContextPath() + "/common/loginForm?retUrl=" +retUrl;
        httpResp.sendRedirect(url);
    }
    
        

    
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        String paramExURLs = fConfig.getInitParameter("exclude");
        StringTokenizer token = new StringTokenizer(paramExURLs,",");

        while (token.hasMoreTokens()) {
            exURLs.add(token.nextToken().trim());
        }
    }

    private boolean excludeCheck(String url){
        boolean result = false;
        result = result || url.length() <= 1; //root 경로 요청시
        for(String exURL : exURLs){
            result = result || (url.indexOf(exURL) == 0);
        }
        return result;
    }
    
    
}
