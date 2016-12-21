package rml.intercepter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import rml.service.serviceImpl.ActionLogServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {
    String NO_INTERCEPTOR_PATH = ".*/((login)|(reg)|(logout)|(code)|(app)|(weixin)|(static)|(main)|(websocket)).*";    //不对匹配该值的访问路径拦截（正则）
    @Autowired
    ActionLogServiceImpl service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TODO Auto-generated method stub
        String path = request.getServletPath();
        if (!path.matches(".*/((static)|(login)|(reg)|(uploadHeadPic)).*")) service.add(request); //不包含静态资源和登陆注册的请求
        if (path.matches(NO_INTERCEPTOR_PATH)) {    //匹配正则表达式的不拦截
            return true;
        } else {    //不匹配的进行处理
            try {
                if (request.getSession().getAttribute("userInfo") == null) { //session中是否存在用户信息，不存在则是未登录状态
                    response.sendRedirect(request.getContextPath() + "/mvc/login");
                    return false;
                }
            } catch (IOException e) {
                response.sendRedirect(request.getContextPath() + "/mvc/login");
                e.printStackTrace();
                return false;
            }
        }
        return true;    //默认是不拦截···当然具体的还看一些需求设计啊  之类的
    }

}
