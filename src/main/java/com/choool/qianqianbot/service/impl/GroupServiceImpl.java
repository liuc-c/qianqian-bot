package com.choool.qianqianbot.service.impl;

import com.choool.qianqianbot.entity.Group;
import com.choool.qianqianbot.mapper.GroupMapper;
import com.choool.qianqianbot.service.GroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * qq群服务实现类
 *
 * @author <a href="https://github.com/liuc-c">choool</a>
 * @since 2021/8/2 1:26
 */
@Service
public class GroupServiceImpl implements GroupService {
    @Resource
    private GroupMapper groupMapper;

    /**
     * 通过群号查找群实体
     *
     * @param groupCode qq号
     * @return {@link Group} 群实体
     * @author <a href="https://github.com/liuc-c">choool</a>
     * @since 2021/8/2 1:30
     */
    @Override
    public Group findByGroupCode(Long groupCode) {
        return groupMapper.selectByGroupCodeNumber(groupCode);
    }

    /**
     * 保存群实体
     *
     * @param group 群实体
     * @author <a href="https://github.com/liuc-c">choool</a>
     * @since 2021/8/2 1:31
     */
    @Override
    public void sava(Group group) {
        groupMapper.insertSelective(group);
    }
}
