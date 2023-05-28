package com.project.Booktion;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        if (userId != null) {
            request.setAttribute("userId", userId);
            return true;
        } else {
            response.sendRedirect("/login"); // 로그인 페이지로 이동하거나 다른 처리를 수행할 수 있습니다.
            return false;
        }
    }
}
