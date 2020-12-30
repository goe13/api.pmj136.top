package top.pmj136.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 彭明久
 * @since 2020-11-26
 */
@Data
@TableName("jiu_friendship")
public class Friendship implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 链接地址
     */
    @NotBlank(message = "友链网址不能为空")
    private String link;

    /**
     * 链接标题
     */
    @NotBlank(message = "友链标题不能为空")
    private String title;


}
