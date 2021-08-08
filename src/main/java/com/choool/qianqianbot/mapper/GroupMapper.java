package com.choool.qianqianbot.mapper;

import com.choool.qianqianbot.entity.Group;
import org.apache.ibatis.annotations.Param;

/**
 * TODO
 *
 * @author <a href="https://github.com/liuc-c">choool</a>
 * @since $date$ $time$
 */
public interface GroupMapper {
    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Group record);

    /**
     * 根据群号查找群实体
     *
     * @param groupCodeNumber qq群号码
     * @return {@link Group} 群实体
     * @author <a href="https://github.com/liuc-c">choool</a>
     * @since 2021/8/2 1:20
     */
    Group selectByGroupCodeNumber(@Param("groupCodeNumber") Long groupCodeNumber);
}