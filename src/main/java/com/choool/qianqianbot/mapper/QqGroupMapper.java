package com.choool.qianqianbot.mapper;

import com.choool.qianqianbot.entity.QqGroup;

/**
 * qq和群的关系表
 *
 * @author <a href="https://github.com/liuc-c">choool</a>
 * @since 2021/8/2 0:12
 */
public interface QqGroupMapper {
    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(QqGroup record);
}