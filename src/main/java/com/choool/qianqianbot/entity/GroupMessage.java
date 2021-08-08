package com.choool.qianqianbot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 群消息表
 *
 * @author <a href="https://github.com/liuc-c">choool</a>
 * @since 2021/8/2 0:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupMessage implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息ID
     */
    private String messageId;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 群号
     */
    private Long groupId;

    /**
     * qq号
     */
    private Long qq;

    private static final long serialVersionUID = 1L;
}