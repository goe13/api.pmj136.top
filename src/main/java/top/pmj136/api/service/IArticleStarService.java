package top.pmj136.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.pmj136.api.entity.ArticleStar;
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
public interface IArticleStarService extends IService<ArticleStar> {

    /**
     * 文章点赞
     *
     * @param entity ArticleStar
     * @return Result
     */
    Result add(ArticleStar entity);

    /**
     * 取消点赞
     *
     * @param req Map
     * @return Result
     */
    Result cancel(Map<String, Integer> req);
}
