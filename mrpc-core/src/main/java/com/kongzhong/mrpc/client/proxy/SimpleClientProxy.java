package com.kongzhong.mrpc.client.proxy;

import com.google.common.reflect.AbstractInvocationHandler;
import com.kongzhong.mrpc.client.cluster.ha.HaStrategy;
import com.kongzhong.mrpc.client.cluster.loadblance.LoadBalance;
import com.kongzhong.mrpc.client.cluster.loadblance.SimpleLoadBalance;
import com.kongzhong.mrpc.config.ClientConfig;
import com.kongzhong.mrpc.interceptor.InterceptorChain;
import com.kongzhong.mrpc.interceptor.Invocation;
import com.kongzhong.mrpc.interceptor.RpcInteceptor;
import com.kongzhong.mrpc.model.RpcRequest;
import com.kongzhong.mrpc.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import java.lang.reflect.Method;
import java.util.List;

import static com.kongzhong.mrpc.model.Const.INTERCEPTOR_NAME_PREFIX;

/**
 * 默认的客户端代理
 *
 * @author biezhi
 *         2017/4/28
 */
@Slf4j
public class SimpleClientProxy<T> extends AbstractInvocationHandler {

    // 负载均衡器
    protected LoadBalance loadBalance = new SimpleLoadBalance();

    // HA策略
    protected HaStrategy haStrategy = ClientConfig.me().getHaStrategy();

    protected boolean hasInterceptors;

    protected List<RpcInteceptor> interceptors;

    protected InterceptorChain interceptorChain = new InterceptorChain();

    public SimpleClientProxy(List<RpcInteceptor> interceptors) {
        this.interceptors = interceptors;
        if (null != interceptors && !interceptors.isEmpty()) {
            hasInterceptors = true;
            int pos = interceptors.size();
            log.info("Add interceptors {}", interceptors.toString());
            for (RpcInteceptor rpcInteceptor : interceptors) {
                interceptorChain.addLast(INTERCEPTOR_NAME_PREFIX + (pos--), rpcInteceptor);
            }
        }
    }

    @Override
    protected Object handleInvocation(Object proxy, Method method, Object[] args) throws Exception {

        RpcRequest request = RpcRequest.builder()
                .appId(ClientConfig.me().getAppId())
                .requestId(StringUtils.getUUID())
                .methodName(method.getName())
                .className(method.getDeclaringClass().getName())
                .parameterTypes(method.getParameterTypes())
                .parameters(args)
                .returnType(method.getReturnType())
                .build();

        if (!hasInterceptors) {
            return haStrategy.call(request, loadBalance);
        }

        FastClass serviceFastClass = FastClass.create(proxy.getClass());
        FastMethod serviceFastMethod = serviceFastClass.getMethod(method);
        Invocation invocation = new Invocation(serviceFastMethod, proxy, args, request, interceptors);
        Object result = invocation.next();
        return result;
    }

}
