package top.pmj136.api.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.pmj136.api.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 彭明久
 * @since 2020-11-26
 */
public interface ArticleMapper extends BaseMapper<Article> {

    IPage<Map<String,Object>> getList(Page<Map<String,Object>> page, Integer user_id,Integer type,Integer order);

    IPage<Map<String,Object>> getManageArticleList(Page<Map<String,Object>> page, String keyword);

    Map<String, Object> getDetailById(Integer article_id, Integer user_id);

    Integer getSearchTotal(Map<String, Object> req);

    List<Map<String, Object>> search(Map<String, Object> req);

    List<Map<String, Object>> searchUserByNick(Map<String, Object> req);

    Boolean restoreArticle(Integer id);

    Boolean forceDel(Integer id);

    List<Article> getRecommends(Integer id, String tag_ids);

    List<Map<String, Object>> getGrowth();



}
