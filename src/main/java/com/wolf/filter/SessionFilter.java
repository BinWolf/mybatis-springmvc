package com.wolf.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wolf on 16/2/20.
 */
public class SessionFilter implements Filter {

    private static String loginPageUrl = "login";

    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String jSessionId = getCookieValue(request,response,"JSESSIONID")	;
        System.out.println("8080=   "+jSessionId);
        String user_id = null;
        if (null != jSessionId && !jSessionId.equals("")) {
            user_id = com.wolf.util.RedisUtil.hget(jSessionId, "user_id");
        }
        if(request.getRequestURI().contains("logout")){
            if(jSessionId!=null){
                clearCookie(request, response, "/",jSessionId);
                com.wolf.util.RedisUtil.del(jSessionId);
            }
            response.sendRedirect(com.wolf.common.ConstValue.home+loginPageUrl);
            return;
        }

        if(jSessionId==null || jSessionId.equals("") || user_id==null || user_id.equals("")){

            String uri = request.getRequestURI();

            // 各种常见静态文件不做处理 放行
            if( uri.endsWith(".js")){
                response.setContentType("application/x-javascript");
            }else if( uri.endsWith(".css")){
                response.setContentType("text/css");
            }else if(uri.endsWith(".jpg") || uri.endsWith(".jpeg")){
                response.setContentType("image/jpeg");
            }else if(uri.endsWith(".png")){
                response.setContentType("image/png");
            }else if(uri.endsWith(".gif")){
                response.setContentType("image/gif");
            }else if(uri.endsWith(".bmp")){
                response.setContentType("image/bitmap");
            }else if(uri.endsWith(".svg")){
                response.setContentType("text/xml");
            }else if(uri.endsWith(".eot")||uri.endsWith(".woff")||uri.endsWith(".ttf")){
                response.setContentType("application/x-www-form-urlencoded");
            }else if(uri.endsWith(".xml") || uri.endsWith(".jsp") || uri.endsWith("login") || uri.endsWith("doLogin")){
            }else{
                //跳到登陆页
                response.sendRedirect(com.wolf.common.ConstValue.home+loginPageUrl);
//                request.getRequestDispatcher(loginPageUrl).forward(request,response);
                return;
            }
        }else{
            request.setAttribute("jSessionId", jSessionId);
            saveCookie(response,"JSESSIONID",jSessionId, com.wolf.common.ConstValue.sessionTimeLong);
            com.wolf.util.RedisUtil.setExpireTime(jSessionId, com.wolf.common.ConstValue.sessionTimeLong);//会话时长延续ConstValue.sessionTimeLong秒

        }
        chain.doFilter(req, resp);
    }

    public void destroy() {

    }


    protected String getCookieValue(HttpServletRequest request,HttpServletResponse response,String name)
    {
        //判断是否其它系统跳转过来的
        String jSessionId = request.getParameter("jSessionId");
        Cookie cookies[] = request.getCookies();
        if(jSessionId!=null) {
            //重写request的cookie中的JSESSIONID
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(name))
                        cookie.setValue(jSessionId);
                }

            }
            saveCookie(response,name,jSessionId, com.wolf.common.ConstValue.sessionTimeLong);
            return jSessionId;
        }
        //如果是本系统请求就在cookie中获取jSessionId
        if (cookies != null)
        {
            for (Cookie cookie : cookies)
            {
                if (!cookie.getName().equals(name))
                    continue;
                return cookie.getValue();
            }

        }
        return null;
    }
    protected void saveCookie(HttpServletResponse response,String name, String value, int maxAge)
    {	//maxAge单位秒
        Cookie cookie = new Cookie(name,value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 清空cookie  clearCookie(request, response, "/",jsessionid);
     */
    public static void clearCookie(HttpServletRequest request,HttpServletResponse response, String path,String key) {
        Cookie[] cookies = request.getCookies();
        try
        {
            for(int i=0;i<cookies.length;i++)
            {
                if(cookies[i].getName().equals(key)){
                    Cookie cookie = new Cookie(cookies[i].getName(), null);
                    cookie.setMaxAge(0);
                    cookie.setPath(path);//根据你创建cookie的路径进行填写
                    response.addCookie(cookie);
                    break;
                }
            }
        }catch(Exception ex)
        {
        }

    }

}