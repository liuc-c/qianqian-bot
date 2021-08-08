package com.choool.qianqianbot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * qq和群的关系表
 *
 * @author <a href="https://github.com/liuc-c">choool</a>
 * @since 2021/8/2 0:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QqGroup implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 群id
     */
    private Integer groupId;

    /**
     * QQ的ID
     */
    private Integer qqId;

    public QqGroup(Integer groupId, Integer qqId) {
        this.groupId = groupId;
        this.qqId = qqId;
    }

    private static final long serialVersionUID = 1L;
}