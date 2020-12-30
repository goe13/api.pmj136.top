package top.pmj136.api.service;

import top.pmj136.api.entity.ArticleTag;
import com.baomidou.mybatisplus.extension.service.IService;
import top.pmj136.api.util.Result;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 彭明久
 * @since 2020-11-26
 */
public interface IArticleTagService extends IService<ArticleTag> {
    /**
     * 获取所有标签
     *
     * @return List
     */
    List<ArticleTag> getTags();

    /**
     * 管理员分页获取类别列表
     *
     * @param page    pageIndex
     * @param size    size
     * @param keyword 关键字
     * @return Result
     */
    Result getTagList(Integer page, Integer size, String keyword);

    /**
     * 管理员添加标签
     *
     * @param entity ArticleTag
     * @return Result
     */
    Result add(ArticleTag entity);

    /**
     * 管理员更新标签
     *
     * @param entity ArticleTag
     * @return Result
     */
    Result update(ArticleTag entity);
}
