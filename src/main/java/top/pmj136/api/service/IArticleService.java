package top.pmj136.api.service;

import top.pmj136.api.entity.Article;
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
public interface IArticleService extends IService<Article> {
    /**
     * 获取文章增量信息
     *
     * @return List
     */
    List<Map<String, Object>> getGrowth();

    /**
     * 获取文章推荐列表
     *
     * @param article_id 关键文章id
     * @return Result
     */
    Result recommend(Integer article_id);

    /**
     * 添加文章
     *
     * @param entity Article
     * @return Result
     */
    Result add(Article entity);

    /**
     * 文章添加回收站
     *
     * @param id 文章id
     * @return Result
     */
    Result del(Integer id);

    /**
     * 删除文章
     *
     * @param id 文章id
     * @return Result
     */
    Result forceDel(Integer id);

    /**
     * 恢复文章
     *
     * @param id 文章id
     * @return Result
     */
    Result restore(Integer id);

    /**
     * 更新文章
     *
     * @param entity Article
     * @return Result
     */
    Result updateData(Article entity);

    /**
     * 获取文章信息
     *
     * @param id 文章id
     * @return Result
     */
    Result get(Integer id);

    /**
     * 获取文章详情(包括用户信息等)
     *
     * @param req Map
     * @return Result
     */
    Result getDetailById(Map<String, Integer> req);

    /**
     * 分页获取文章列表
     *
     * @param page    pageIndex
     * @param size    size
     * @param user_id
     * @param type    类型
     * @param order   过滤方式
     * @return Result
     */
    Result getList(Integer page, Integer size, Integer user_id, Integer type, Integer order);

    /**
     * 搜索文章
     *
     * @param req Map
     * @return Result
     */
    Result search(Map<String, Object> req);

    /**
     * 获取文章作者id
     *
     * @param articleId 文章id
     * @return Integer
     */
    Integer getArticleAuthorId(Integer articleId);

    /**
     * 管理员分页获取文章列表
     *
     * @param page    pageIndex
     * @param size    size
     * @param keyword keyword
     * @return Result
     */
    Result getManageArticleList(Integer page, Integer size, String keyword);

    void increaseNum(Integer article_id, String pro, Integer x);

    void decreaseNum(Integer article_id, String pro, Integer x);
}
