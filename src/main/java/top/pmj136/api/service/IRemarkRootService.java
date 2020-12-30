package top.pmj136.api.service;

import top.pmj136.api.entity.RemarkRoot;
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
public interface IRemarkRootService extends IService<RemarkRoot> {
    /**
     * 获取评论列表
     *
     * @param req Map
     * @return Result
     */
    Result getList(Map<String, Integer> req);

    /**
     * 添加父评论
     *
     * @param entity RemarkRoot
     * @return Result
     */
    Result add(RemarkRoot entity);

    /**
     * 删除父评论
     *
     * @param req Map
     * @return Result
     */
    Result del(Map<String, Object> req);
}
