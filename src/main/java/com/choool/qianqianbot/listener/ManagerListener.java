package com.choool.qianqianbot.listener;

import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.MsgSender;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author <a href="https://github.com/liuc-c">choool</a>
 * @since 2021/8/2 2:40
 */
@Component
public class ManagerListener {

    @OnGroup
    @Filter
    public void test(MsgSender msgSender, GroupMsg groupMsg) {
        msgSender.SENDER.sendGroupMsg(groupMsg, "aaaaaaaa");
    }
}
