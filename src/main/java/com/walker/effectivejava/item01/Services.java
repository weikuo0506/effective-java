package com.walker.effectivejava.item01;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by walker on 2016/8/21.
 */
public class Services {
    private Services(){}

    private static final Map<String,Provider> providers = new ConcurrentHashMap<>();

    public static final String DEFAULT_PROVIDER_NAME = "<def>";

    //provider registriation API
    public static void registerDefaultProvider(Provider p) {
        registerProvider(DEFAULT_PROVIDER_NAME,p);
    }
    public static void registerProvider(String name, Provider p) {
        providers.put(name, p);
    }

    //serivce access API
    public static Service newInstance() {
        return newInstance(DEFAULT_PROVIDER_NAME);
    }
    public static Service newInstance(String name) {
        Provider p = providers.get(name);
        if (p == null) {
            throw new IllegalArgumentException("No provider registered with name : " + name);
        }
        return p.newService();
    }
}
