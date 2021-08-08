package com.choool.qianqianbot.mapper;

import com.choool.qianqianbot.entity.GroupMessage;

/**
 * 群消息mapper
 *
 * @author <a href="https://github.com/liuc-c">choool</a>
 * @since 2021/8/2 0:09
 */
public interface GroupMessageMapper {
    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(GroupMessage record);
}