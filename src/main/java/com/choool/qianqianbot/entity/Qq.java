package com.choool.qianqianbot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import love.forte.simbot.api.message.containers.GroupAccountInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * qq
 *
 * @author <a href="https://github.com/liuc-c">choool</a>
 * @since 2021/8/2 1:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Qq implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * qq号
     */
    private Long qq;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称（备注）
     */
    private String nickname;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 群实体
     */
    private Set<Group> groups = new HashSet<>();

    public Qq(GroupAccountInfo accountInfo) {
        this.avatar = accountInfo.getAccountAvatar();
        this.nickname = accountInfo.getAccountNicknameAndRemark();
        this.qq = accountInfo.getAccountCodeNumber();
    }

    private static final long serialVersionUID = 1L;
}