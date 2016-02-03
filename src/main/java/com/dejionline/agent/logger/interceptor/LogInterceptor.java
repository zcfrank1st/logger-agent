package com.dejionline.agent.logger.interceptor;

import net.bytebuddy.implementation.bind.annotation.Origin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by zcfrank1st on 2/2/16.
 */
public class LogInterceptor {
    public static Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    public static void interceptor(@Origin Method method, @Origin Class clazz) {
        logger.info(
                        "class:" + clazz.getCanonicalName() + "\t" +
                        "method:" + method.getName() + "\t" +
                        "args:" + Arrays.toString(method.getParameters())
        );
    }
}
