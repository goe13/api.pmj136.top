package top.pmj136.api.service;

import top.pmj136.api.entity.ArticleStar;
import top.pmj136.api.entity.MsgStar;
import com.baomidou.mybatisplus.extension.service.IService;
import top.pmj136.api.util.Result;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 彭明久
 * @since 2020-11-26
 */
public interface IMsgStarService extends IService<MsgStar> {

    /**
     * 获取所有消息
     *
     * @param page      页码
     * @param size      页码大小
     * @param target_id 用户id
     * @return Result
     */
    Result getMsg(Integer page, Integer size, Integer target_id);

    /**
     * 清空所有消息
     *
     * @param target_id 所属用户id
     */
    void delAll(Integer target_id);

    /**
     * 发送消息
     *
     * @param entity ArticleStar
     */
    void sendMsg(ArticleStar entity);

    /**
     * 已读消息
     *
     * @param msg_id 消息id
     */
    void read(Integer msg_id);
}
