package top.pmj136.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 彭明久
 * @since 2020-11-26
 */
@Data
@TableName("jiu_article_collect")
public class ArticleCollect implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 收藏人id
     */
    @JsonProperty("user_id")
    private Integer userId;

    /**
     * 文章id
     */
    @JsonProperty("article_id")
    private Integer articleId;

    /**
     * 收藏时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;


}
