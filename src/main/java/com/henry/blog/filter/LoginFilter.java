package com.henry.blog.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2015/8/7.
 */
public class LoginFilter implements Filter {
    private static final String NO_VERIFY_URL[] = {
            "/searchMember","/wechatLogin","regMember","validateIDCard","validateTel","bindWechat"
    };
    private static final int ERR_NOLOGIN = 600;
    private static final String CLIENT_U = "userId";
    private static final String CLIENT_T = "token";

    public void destroy() {
        System.out.println("LoginFilter destroy");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;
            String path = request.getRequestURI();
            System.out.println("Path: " + path);
            for (int i = 0; i < NO_VERIFY_URL.length; i++) {
                if (path.endsWith(NO_VERIFY_URL[i])) {
                    chain.doFilter(request, response);
                    return;
                }
            }

            boolean legal = false;
            legal = verifyToken(request, response);
            if (legal) {
                chain.doFilter(request, response);
            } else {
                System.out.println("Warn Deny access");
                response.sendError(ERR_NOLOGIN);
                System.out.println("out filter");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("f++++++LoginFilter init++++++");
    }

    private boolean verifyToken(ServletRequest req, ServletResponse resp){
        HttpServletRequest request = (HttpServletRequest) req;
        String userId = request.getParameter(CLIENT_U);
        String clientToken = request.getParameter(CLIENT_T);
        System.out.println("userId:" + userId + "/token :" + clientToken);

        if(userId == null || clientToken == null){

        }else {
            ServletContext context = request.getSession().getServletContext();
            String token = (String) context.getAttribute(userId);
            if(token != null && token.equals(clientToken)){
                System.out.println("token:" + token);
                return  true;
            }
        }
        return false;
    }
}
