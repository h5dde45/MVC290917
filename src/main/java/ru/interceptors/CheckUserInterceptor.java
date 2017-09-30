package ru.interceptors;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ru.objectss.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckUserInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        if(request.getRequestURI().contains("checkUser")){
            User user= (User) modelAndView.getModel().get("user");
            if(user==null || !user.isAdmin()){
                response.sendRedirect(request.getContextPath()+"/failed");
            }

        }
    }
}
