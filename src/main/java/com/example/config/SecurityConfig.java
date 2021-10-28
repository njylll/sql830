package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userAuthenticateService")
    private UserDetailsService userAuthenticateService;


    //设置认证的服务类
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthenticateService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //配置拦截
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //无权403页面
        http.exceptionHandling().accessDeniedPage("/error/403.html");
        //注销页面
        http.logout().logoutUrl("/newVersion/logout").logoutSuccessUrl("/newVersion/login").permitAll();

        //认证路径匹配
        http.authorizeRequests()
                .antMatchers("/error/**","/js/**","/layui/**","/images/**","/newVersion/registerOK").permitAll()
                .antMatchers("/newVersion/student/**").hasRole("STUDENT")
                .antMatchers("/newVersion/teacher/**").hasRole("TEACHER")
                .antMatchers("/newVersion/superTeacher/**").hasRole("SUPERTEACHER")
                .antMatchers("/**").permitAll();


        //登陆页面，用ajax登陆加重定向
        http.formLogin()
                .loginPage("/newVersion/login")
                //表单action
                .loginProcessingUrl("/newVersion/gologin")
                //成功返回默认信息
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    out.write("{\"status\":\"ok\",\"msg\":\"登录成功\"}");
                    out.flush();
                    out.close();
                })
                //ajax登录错误
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    httpServletResponse.setStatus(403);
                    PrintWriter out = httpServletResponse.getWriter();
                    out.write("{\"status\":\"error\",\"msg\":\""+ e.getMessage() +"\"}");
                    out.flush();
                    out.close();
                }).
                permitAll();

        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();

    }
    //Thymeleaf方言
    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }
}
