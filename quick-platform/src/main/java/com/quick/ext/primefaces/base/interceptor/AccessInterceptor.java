package com.quick.ext.primefaces.base.interceptor;

import java.io.Serializable;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.apache.log4j.Logger;

/**
 *
 * @author chenquan
 */
@AccessLogInterceptor
@Interceptor
public class AccessInterceptor implements Serializable{

    Logger LOG = Logger.getLogger(AccessInterceptor.class.getSimpleName());

    @AroundInvoke
    public Object log(InvocationContext context) throws Exception {
        LOG.info("call   " + context.getTarget().getClass());
        LOG.info("method " + context.getMethod());
        for (Object o : context.getParameters()) {
            LOG.info("param  " + o);
        }
//        System.out.println("BEFORE: " + context.getParameters());
        Object result = context.proceed();
        LOG.info("AFTER: " + context.getMethod());

        return result;
    }
}
