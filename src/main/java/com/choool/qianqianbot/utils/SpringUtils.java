package com.choool.qianqianbot.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring工具类
 *
 * @author <a href="https://github.com/liuc-c">choool</a>
 * @since 2021/8/6 10:38
 */
public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        if (SpringUtils.applicationContext == null) {
            SpringUtils.applicationContext = applicationContext;
        }
    }

    /**
     * 获取applicationContext
     *
     * @return {@link ApplicationContext} ApplicationContext app上下文
     * @author <a href="https://github.com/liuc-c">choool</a>
     * @since 2021/8/6 10:38
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name 名字
     * @return Bean
     * @author <a href="https://github.com/liuc-c">choool</a>
     * @since 2021/8/6 10:40
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz 类的字节码
     * @return Bean
     * @author <a href="https://github.com/liuc-c">choool</a>
     * @since 2021/8/6 10:41
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name  名字
     * @param clazz 类的字节码
     * @return Bean
     * @author <a href="https://github.com/liuc-c">choool</a>
     * @since 2021/8/6 10:42
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
