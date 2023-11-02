package com.ylsm;

import com.ylsm.annotation.YlsmToken;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class YlsmRequestArgumentHandle implements AnnotatedParameterProcessor {

    private static final Class<YlsmToken> ANNOTATION = YlsmToken.class;
    @Override
    public Class<? extends Annotation> getAnnotationType() {
        return ANNOTATION;
    }

    @Override
    public boolean processArgument(AnnotatedParameterContext context, Annotation annotation, Method method) {
        return false;
    }
}
