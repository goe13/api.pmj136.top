package top.pmj136.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 彭明久
 * @since 2020-11-26
 */
@Data
@TableName("jiu_article_type")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 意义
     */
    @NotBlank(message = "类别名不能为空")
    private String text;

    /**
     * 是否使用中
     */
    private Integer state;

    @TableField(exist = false)
    private Integer type;

}
