package top.pmj136.api.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDate;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 彭明久
 * @since 2020-11-26
 */
@Data
@TableName("jiu_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色
     */
    private Integer role;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * 昵称
     */
    private String nick;

    /**
     * 用户积分
     */
    private BigDecimal integral;

    /**
     * 头像url
     */
    @JsonProperty("avatar_url")
    private String avatarUrl;

    /**
     * 性别；1/男  2/女 0/未知
     */
    private Integer gender;

    /**
     * 个人介绍
     */
    private String intro;

    /**
     * 地址
     */
    private String address;

    /**
     * 加入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("create_at")
    private LocalDate createAt;

    /**
     * gitub用户唯一标识
     */
    @JsonProperty("github_id")
    private Integer githubId;

    /**
     * github昵称
     */
    @JsonProperty("github_nick")
    private String githubNick;

    /**
     * gitee用户唯一标识
     */
    @JsonProperty("gitee_id")
    private Integer giteeId;

    /**
     * gitee昵称
     */
    @JsonProperty("gitee_nick")
    private String giteeNick;

    /**
     * 钉钉用户唯一标识别
     */
    @JsonProperty("dingtalk_id")
    private String dingtalkId;

    /**
     * 钉钉昵称
     */
    @JsonProperty("dingtalk_nick")
    private String dingtalkNick;

    /**
     * 关注量
     */
    @JsonProperty("notice_count")
    private Integer noticeCount;

    /**
     * 粉丝量
     */
    @JsonProperty("fans_count")
    private Integer fansCount;

    /**
     * 最后一次登录时间
     */
    @JsonProperty("last_sign_date")
    private LocalDate lastSignDate;

    /**
     * 登录天数
     */
    @JsonProperty("signed_days")
    private Integer signedDays;

    /**
     * 用户登录凭证
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String token;


    /**
     * 账号状态 0封停  / 1正常使用
     */
    private Integer state;


}
