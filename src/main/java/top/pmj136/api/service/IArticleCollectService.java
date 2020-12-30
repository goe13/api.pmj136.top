package top.pmj136.api.service;

import top.pmj136.api.entity.ArticleCollect;
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
public interface IArticleCollectService extends IService<ArticleCollect> {
    /**
     * 文章收藏
     *
     * @param entity ArticleCollect
     * @return Result
     */
    Result add(ArticleCollect entity);

    /**
     * 取消收藏
     *
     * @param req Map
     * @return Result
     */
    Result cancel(Map<String, Object> req);
}
