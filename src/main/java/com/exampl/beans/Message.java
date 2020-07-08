package com.exampl.beans;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author HuangJian
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_message")
public class Message extends Model<Message> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String content;

    private String title;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
