package com.dejionline.agent.logger.interceptor;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by zcfrank1st on 2/2/16.
 */
public class LogInterceptor {
    public static Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    public static void interceptor(@Origin Method method, @AllArguments String[] args ) {
        // TODO 打印方法参数等相关信息
        logger.info(method.getName());
    }
}
