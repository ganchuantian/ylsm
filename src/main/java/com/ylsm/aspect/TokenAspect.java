package com.ylsm.aspect;


import com.ylsm.annotation.TokenField;
import com.ylsm.annotation.YlsmToken;
import com.ylsm.untils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.core.env.Environment;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Objects;


@Aspect
@Component
@Slf4j
public class TokenAspect implements EnvironmentAware {

    private Environment environment;

    @Pointcut("@annotation(com.ylsm.annotation.YlsmToken)")
    public void tokenPointcut() {

    }

    @Around("tokenPointcut() && @annotation(ylsmToken)")
    public Object token(ProceedingJoinPoint joinPoint, YlsmToken ylsmToken) throws Throwable {
        Object[] objects = joinPoint.getArgs();
        if (Objects.isNull(objects) || objects.length == 0) {
            return joinPoint.proceed();
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        signature.getDeclaringType();
        Method method = signature.getMethod();
        String route = environment.resolvePlaceholders(ylsmToken.value());
        Parameter[] parameters = method.getParameters();
        if (Objects.nonNull(parameters) && parameters.length > 0) {
            for (int i = 0; i < parameters.length; i++) {
                TokenField tokenField = parameters[i].getAnnotation(TokenField.class);
                if (Objects.isNull(tokenField)) {
                    continue;
                }
                if (Objects.isNull(objects[i])) {
                    continue;
                }
                objects[i] = TokenUtils.encrypt(String.valueOf(objects[i]), route);
            }
        }
        return joinPoint.proceed(objects);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
