package com.dee.studyadmin.filter;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.dee.studyadmin.util.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class Interceptor implements HandlerInterceptor {
    @Autowired
    RedisSession redisSession;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            if ("OPTIONS".equals(request.getMethod())) {
                response.setStatus(HttpServletResponse.SC_OK);
                return true;
            }

            String token =  request.getHeader("Authorization");//一般都将token放在请求头中
            JWTUtils.verifyToken(token);


            if (redisSession.exist(token) == false) {
                throw new NullPointerException("Token is not in redis !");
            }

            redisSession.refresh(token);
            log.info("token pass....");
            return true;
        } catch (SignatureVerificationException e) {
            throw e;
        } catch (TokenExpiredException e){
            throw e;
        } catch (AlgorithmMismatchException e) {
            throw e;
        } catch (InvalidClaimException e) {
            throw e;
        } catch (NullPointerException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
//        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}