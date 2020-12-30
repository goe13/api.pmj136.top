package top.pmj136.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 彭明久
 * @since 2020-11-26
 */
@Data
@TableName("jiu_remark_root")
public class RemarkRoot implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章Id
     */
    @JsonProperty("article_id")
    @NotNull(message = "article_id cannot be empty")
    private Integer articleId;

    /**
     * 评论用户id
     */
    @JsonProperty("user_id")
    private Integer userId;

    /**
     * 内容
     */
    @NotNull(message = "评论内容不能为空")
    @NotBlank(message = "评论内容不能为空")
    private String content;

    /**
     * 评论时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    @TableField(exist = false)
    private List<RemarkChild> children;

    @TableField(exist = false)
    private String nick;

    @TableField(exist = false)
    private Integer action;

    @TableField(exist = false)
    @JsonProperty("target_id")
    private Integer targetId;

    @TableField(exist = false)
    @JsonProperty("remark_id")
    private Integer remarkId;

    @TableField(exist = false)
    @JsonProperty("avatar_url")
    private String avatarUrl;

}
