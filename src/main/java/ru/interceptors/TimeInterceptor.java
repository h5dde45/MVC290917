package ru.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@Component
public class TimeInterceptor extends HandlerInterceptorAdapter{
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(TimeInterceptor.class));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime",System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime= Long.parseLong(request.getAttribute("startTime").toString());
        Thread.sleep(3333);
        long time=System.currentTimeMillis()-startTime;
        modelAndView.addObject("time",time);
        LOGGER.info(handler+" = "+String.valueOf(time)+" - milliSec");
    }


}
