package com.choool.qianqianbot.service;

import com.choool.qianqianbot.entity.Group;

/**
 * 群服务接口
 *
 * @author <a href="https://github.com/liuc-c">choool</a>
 * @since 2021/8/2 1:25
 */
public interface GroupService {
    /**
     * 通过群号查找群实体
     *
     * @param groupCode qq号
     * @return {@link Group} 群实体
     * @author <a href="https://github.com/liuc-c">choool</a>
     * @since 2021/8/2 1:30
     */
    Group findByGroupCode(Long groupCode);

    /**
     * 保存群实体
     *
     * @param group 群实体
     * @author <a href="https://github.com/liuc-c">choool</a>
     * @since 2021/8/2 1:31
     */
    void sava(Group group);
}
