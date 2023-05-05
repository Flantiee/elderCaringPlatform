package com.yiyang.manager.filter;

import com.alibaba.fastjson.JSON;
import com.yiyang.manager.common.R;
import com.yiyang.manager.utils.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//别加，加了就G
//@WebFilter(filterName = "idEncryptionFilter", urlPatterns = {"/user/*","/dashboard/*","/table/*","/affairs/*"})
public class IdEncryptionFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String message = null;
        String url =  ((HttpServletRequest)servletRequest).getRequestURI();
        if(url != null){
            if("/user/login".equals(url)){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }else{
                //其他请求验证token
                String token = ((HttpServletRequest)servletRequest).getHeader("token");
                if(StringUtils.isNotBlank(token)){
                    //token验证结果
                    Long verify  = JWTUtil.verify(token);

                    System.out.println(request.getSession().getAttribute("user"));

                    if(verify == 0 || request.getSession().getAttribute("user") == null){
                        //验证失败
                        message = "账号已失效，请重新登录";
                    }else{
                        //验证成功，放行
                        filterChain.doFilter(servletRequest,servletResponse);
                        return;
                    }
                }else{
                    //token为空的返回
                    message = "未携带token信息";
                }
            }
            servletResponse.setContentType("application/json");
            //设置响应的编码
            servletResponse.setCharacterEncoding("utf-8");
            //响应
            PrintWriter pw=servletResponse.getWriter();
            pw.write(JSON.toJSONString(R.error(message)));
            pw.flush();
            pw.close();
        }
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
