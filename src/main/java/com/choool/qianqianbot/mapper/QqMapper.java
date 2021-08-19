package com.choool.qianqianbot.mapper;

import com.choool.qianqianbot.entity.Qq;

/**
 * QqMapper
 *
 * @author <a href="https://github.com/liuc-c">choool</a>
 * @since 2021/8/2 1:46
 */
public interface QqMapper {
    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Qq record);

    /**
     * 通过QQ号查找qq实体（包含群）
     *
     * @param qq qq号
     * @return Qq实体，包含群
     * @author <a href="https://github.com/liuc-c">choool</a>
     * @since 2021/8/2 0:27
     */
    Qq findByQq(Long qq);
}