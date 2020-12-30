package top.pmj136.api.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 彭明久
 * @since 2020-11-26
 */
@Data
@TableName("jiu_article")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章标题
     */
    @NotNull(message = "文章标题不能为空")
    @NotBlank(message = "文章标题不能为空")
    private String title;

    /**
     * 文章第一张图片
     */
    @JsonProperty("initial_img")
    private String initialImg;

    /**
     * 文章内容/md字符串
     */
    @NotNull(message = "文章内容不能为空")
    @NotBlank(message = "文章内容不能为空")
    private String content;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("release_time")
    private LocalDateTime releaseTime;

    /**
     * 文章类型/例如 公告
     */
    @NotNull(message = "文章类型不能为空")
    private Integer type;

    /**
     * 文章tag/例如JavaScript、Java等一个或多个
     */
    @JsonProperty("tag_ids")
    @NotNull(message = "文章标签不能为空")
    @NotBlank(message = "文章标签不能为空")
    private String tagIds;

    /**
     * 点赞数
     */
    @JsonProperty("star_count")
    private Integer starCount;

    /**
     * 评论数
     */
    @JsonProperty("comment_count")
    private Integer commentCount;

    /**
     * 阅读量
     */
    @JsonProperty("view_count")
    private Integer viewCount;

    /**
     * 发布者id
     */
    @JsonProperty("user_id")
    @NotNull(message = "发布者id不能为空")
    private Integer userId;

    /**
     * 逻辑删除
     */
    @TableLogic
    @JsonIgnore
    private Boolean isDel;


}
