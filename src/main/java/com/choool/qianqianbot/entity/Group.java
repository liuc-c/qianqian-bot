package com.choool.qianqianbot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import love.forte.simbot.api.message.containers.GroupInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * 群
 *
 * @author <a href="https://github.com/liuc-c">choool</a>
 * @since $date$ $time$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 群号
     */
    private Long groupCodeNumber;

    /**
     * 群开关
     */
    private Integer groupSwitch;

    /**
     * 群头像
     */
    private String groupAvatar;

    /**
     * 群名字
     */
    private String groupName;

    /**
     * 创建时间
     */
    private Date createdTime;

    public Group(GroupInfo groupInfo) {
        this.groupCodeNumber = groupInfo.getGroupCodeNumber();
        this.groupAvatar = groupInfo.getGroupAvatar();
        this.groupName = groupInfo.getGroupName();
    }

    private static final long serialVersionUID = 1L;
}