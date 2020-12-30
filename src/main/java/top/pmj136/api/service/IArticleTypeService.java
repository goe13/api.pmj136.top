package top.pmj136.api.service;

import top.pmj136.api.entity.ArticleType;
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
public interface IArticleTypeService extends IService<ArticleType> {
    /**
     * 获取所有类别
     *
     * @return List
     */
    List<ArticleType> getTypes();

    /**
     * 管理员分页获取类别列表
     *
     * @param page    pageIndex
     * @param size    size
     * @param keyword keyword
     * @return Result
     */
    Result getTypeList(Integer page, Integer size, String keyword);

    /**
     * 管理员添加类别
     *
     * @param entity ArticleType
     * @return Result
     */
    Result add(ArticleType entity);

    /**
     * 管理员更新类别信息
     *
     * @param entity ArticleType
     * @return Result
     */
    Result update(ArticleType entity);
}
