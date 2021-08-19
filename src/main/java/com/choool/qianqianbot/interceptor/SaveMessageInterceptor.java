/*
 * MIT License
 *
 * Copyright (c) 2021 liu.c
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.choool.qianqianbot.interceptor;

import com.choool.qianqianbot.entity.GroupMessage;
import com.choool.qianqianbot.mapper.GroupMessageMapper;
import lombok.extern.slf4j.Slf4j;
import love.forte.simbot.api.message.events.FriendMsg;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.api.message.events.PrivateMsg;
import love.forte.simbot.component.mirai.message.event.MiraiTempMsg;
import love.forte.simbot.constant.PriorityConstant;
import love.forte.simbot.intercept.InterceptionType;
import love.forte.simbot.listener.MsgInterceptContext;
import love.forte.simbot.listener.MsgInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 保存消息拦截类
 * 优先级：SECOND  第二
 *
 * @author <a href="https://github.com/liuc-c">choool</a>
 * @since 2021/8/8 18:26
 */
@Slf4j
@Component
public class SaveMessageInterceptor implements MsgInterceptor {
    @Resource
    private GroupMessageMapper groupMessageMapper;

    @Override
    public int getPriority() {
        return PriorityConstant.SECOND;
    }

    /**
     * 拦截器-保存群消息
     *
     * @param context 拦截器消息主题
     * @return {@link InterceptionType}
     * @author <a href="mailto:liuchao_1127@qq.com">choool</a>
     * @since 2021/8/8 19:08
     */
    @NotNull
    @Override
    public InterceptionType intercept(@NotNull MsgInterceptContext context) {
        MsgGet msgGet = context.getMsgGet();
        if (msgGet instanceof GroupMsg) {
            //处理群聊消息
            GroupMsg groupMsg = (GroupMsg) msgGet;
            long group = groupMsg.getGroupInfo().getGroupCodeNumber();
            String groupName = groupMsg.getGroupInfo().getGroupName();
            long qq = groupMsg.getAccountInfo().getAccountCodeNumber();
            String qqName = groupMsg.getAccountInfo().getAccountRemarkOrNickname();
            String msg = groupMsg.getMsg();
            log.debug(String.format("[%s(%s)]%s(%s) -> ([ %s ])",
                    groupName, group, qq, qqName, msg));
            GroupMessage groupMessage = new GroupMessage(msg, groupMsg.getId(), group, qq);
            groupMessageMapper.insertSelective(groupMessage);
        } else if (msgGet instanceof FriendMsg) {
            //处理私聊消息
            PrivateMsg privateMsg = (PrivateMsg) msgGet;
            long qq = privateMsg.getAccountInfo().getAccountCodeNumber();
            String name = privateMsg.getAccountInfo().getAccountRemarkOrNickname();
            String msg = privateMsg.getMsg();
            log.info(String.format("%s(%s) -> ([ %s ])",
                    name, qq, msg));
        } else if (msgGet instanceof MiraiTempMsg) {
            //处理临时消息
            MiraiTempMsg miraiTempMsg = (MiraiTempMsg) msgGet;
            long qq = miraiTempMsg.getAccountInfo().getAccountCodeNumber();
            long group = miraiTempMsg.getGroupInfo().getGroupCodeNumber();
            String name = miraiTempMsg.getAccountInfo().getAccountRemarkOrNickname();
            String groupName = miraiTempMsg.getGroupInfo().getGroupName();
            String msg = miraiTempMsg.getMsg();
            log.info(String.format("%s(%s)[%s(%s)] -> ([ %s ])", name, qq, groupName,
                    group, msg));
        }
        return InterceptionType.PASS;
    }
}
