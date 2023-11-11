package com.example.parking.interceptors;

import com.example.parking.utils.HttpUtils;
import com.example.parking.utils.LoggerUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoggerInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURI();
        String clientIP = HttpUtils.getRequestIP(request);
        String method = request.getMethod();

        int status = response.getStatus();


        LoggerUtil.petitionLog(url, method, String.valueOf(status), clientIP);
    }
}
