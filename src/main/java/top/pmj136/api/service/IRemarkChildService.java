package top.pmj136.api.service;

import top.pmj136.api.entity.RemarkChild;
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
public interface IRemarkChildService extends IService<RemarkChild> {
    /**
     * 回复评论
     *
     * @param entity RemarkChild
     * @return Result
     */
    Result addReply(RemarkChild entity);

    /**
     * 删除回复
     *
     * @param req Map
     * @return Result
     */
    Result del(Map<String, Integer> req);
}
