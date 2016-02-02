package com.dejionline.agent.logger;

import com.dejionline.agent.logger.annotation.Log;
import com.dejionline.agent.logger.interceptor.LogInterceptor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.matcher.ElementMatchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 * Created by zcfrank1st on 2/2/16.
 */
public class LogAgent {
    private static Logger logger = LoggerFactory.getLogger(LogAgent.class);

    public static void premain(String args, Instrumentation inst) {
        try {
            new AgentBuilder.Default()
                    .with(new AgentBuilder.Listener() {
                        @Override
                        public void onTransformation(TypeDescription typeDescription, DynamicType dynamicType) {
                        }

                        @Override
                        public void onIgnored(TypeDescription typeDescription) {
                        }

                        @Override
                        public void onError(String s, Throwable throwable) {
                            System.out.println("error --- " + s);
                            System.out.println("error === " + throwable.getMessage());
                            throwable.printStackTrace();
                        }

                        @Override
                        public void onComplete(String s) {
                            System.out.println("complete --- " + s);
                        }
                    })
                    .type(declaresMethod(isAnnotatedWith(Log.class)))
                    .transform((builder, typeDescription) -> builder
                            .method(isDeclaredBy(any()))
                            .intercept(MethodDelegation.to(LogInterceptor.class).andThen(SuperMethodCall.INSTANCE)))
                    .installOn(inst);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
