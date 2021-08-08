package com.choool.qianqianbot.interceptor;

import com.choool.qianqianbot.entity.Group;
import com.choool.qianqianbot.entity.Qq;
import com.choool.qianqianbot.entity.QqGroup;
import com.choool.qianqianbot.mapper.QqGroupMapper;
import com.choool.qianqianbot.service.GroupService;
import com.choool.qianqianbot.service.QqService;
import love.forte.simbot.api.message.containers.GroupAccountInfo;
import love.forte.simbot.api.message.containers.GroupInfo;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.constant.PriorityConstant;
import love.forte.simbot.intercept.InterceptionType;
import love.forte.simbot.listener.ListenerContext;
import love.forte.simbot.listener.MsgInterceptContext;
import love.forte.simbot.listener.MsgInterceptor;
import love.forte.simbot.listener.ScopeContext;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 保存用户信息及群消息的拦截器
 * 优先级：FIRST  最高
 *
 * @author <a href="https://github.com/liuc-c">choool</a>
 * @since 2021/8/1 22:14
 */
@Component
public class SaveInfoInterceptor implements MsgInterceptor {

    @Resource
    private QqService qqService;
    @Resource
    private GroupService groupService;
    @Resource
    private QqGroupMapper qqGroupMapper;

    @Override
    public int getPriority() {
        return PriorityConstant.FIRST;
    }

    @NotNull
    @Override
    @Transactional(rollbackFor = Exception.class)
    public InterceptionType intercept(@NotNull MsgInterceptContext context) {
        //获取每次监听事件触发的监听上下文
        final ScopeContext scopeContext = context.getListenerContext().getContext(ListenerContext.Scope.EVENT_INSTANT);
        //获取所有监听类型的父接口
        MsgGet msgGet = context.getMsgGet();
        //如果是群消息
        if (msgGet instanceof GroupMsg) {
            //获取群消息接口
            GroupMsg groupMsg = (GroupMsg) msgGet;
            //获取发送消息的qq账号信息
            final GroupAccountInfo accountInfo = groupMsg.getAccountInfo();
            //获取群消息信息
            final GroupInfo groupInfo = groupMsg.getGroupInfo();
            //获取QQ群号码
            final long groupCodeNumber = groupInfo.getGroupCodeNumber();
            try {
                Qq qqEntity = qqService.findByQq(accountInfo.getAccountCodeNumber());
                //qq信息不存在，添加qq信息
                if (qqEntity == null) {
                    qqEntity = new Qq(accountInfo);
                    qqService.sava(qqEntity);
                } else {
                    //获取qq所拥有的群
                    Set<Group> groups = qqEntity.getGroups();
                    if (groups.size() > 0) {
                        //查找当前群
                        Set<Group> res = groups.parallelStream()
                                .filter(i -> i.getGroupCodeNumber() == groupCodeNumber)
                                .collect(Collectors.toSet());
                        //如果res>0，说明存在群实体，已经添加过，直接放行
                        if (res.size() > 0) {
                            //设置qqEntity为监听上下文局部变量
                            scopeContext.set("qqEntity", qqEntity);
                            return InterceptionType.PASS;
                        }
                    }
                }
                //从数据库查询群
                Group groupEntity = groupService.findByGroupCode(groupCodeNumber);
                //不存在群，添加群信息
                if (groupEntity == null) {
                    groupEntity = new Group(groupInfo);
                    groupService.sava(groupEntity);
                }
                final Set<Group> qqGroups = qqEntity.getGroups();
                qqGroups.add(groupEntity);
                //设置qqEntity为监听上下文局部变量
                scopeContext.set("qqEntity", qqEntity);
                //保存qq和群的映射
                qqGroupMapper.insertSelective(new QqGroup(groupEntity.getId(), qqEntity.getId()));
            } catch (Exception e) {

                return InterceptionType.PASS;
            }
        }
        return InterceptionType.PASS;
    }
}
