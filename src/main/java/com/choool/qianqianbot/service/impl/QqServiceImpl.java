package com.choool.qianqianbot.service.impl;

import com.choool.qianqianbot.entity.Qq;
import com.choool.qianqianbot.mapper.QqMapper;
import com.choool.qianqianbot.service.QqService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * qq服务实现类
 *
 * @author <a href="https://github.com/liuc-c">choool</a>
 * @since 2021/8/2 0:26
 */
@Service
public class QqServiceImpl implements QqService {
    @Resource
    private QqMapper qqMapper;

    /**
     * 通过QQ号查找qq实体（包含群）
     *
     * @param qq qq号
     * @return Qq实体，包含群
     * @author <a href="https://github.com/liuc-c">choool</a>
     * @since 2021/8/2 0:27
     */
    @Override
    public Qq findByQq(Long qq) {
        return qqMapper.findByQq(qq);
    }

    /**
     * 保存QQ实体
     *
     * @param qq qq实体
     * @author <a href="https://github.com/liuc-c">choool</a>
     * @since 2021/8/2 0:35
     */
    @Override
    public void sava(Qq qq) {
        qqMapper.insertSelective(qq);
    }
}
