package com.choool.qianqianbot.service;

import com.choool.qianqianbot.entity.Qq;

/**
 * qq服务接口
 *
 * @author <a href="https://github.com/liuc-c">choool</a>
 * @since 2021/8/2 0:25
 */
public interface QqService {
    /**
     * 通过QQ号查找qq实体（包含群）
     *
     * @param qq qq号
     * @return Qq实体，包含群
     * @author <a href="https://github.com/liuc-c">choool</a>
     * @since 2021/8/2 0:27
     */
    Qq findByQq(Long qq);

    /**
     * 保存QQ实体
     *
     * @param qq qq实体
     * @author <a href="https://github.com/liuc-c">choool</a>
     * @since 2021/8/2 0:35
     */
    void sava(Qq qq);
}
