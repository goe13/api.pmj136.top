package top.pmj136.api.mapper;

import top.pmj136.api.entity.RemarkRoot;
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
public interface RemarkRootMapper extends BaseMapper<RemarkRoot> {
    /**
     * 分页获取文章评论
     *
     * @param req {
     *            article_id 文章类型
     *            page 当前页数
     *            size 每页数据大小
     *            }
     * @return List
     */
    List<Map<String, Object>> getList(Map<String, Integer> req);


    /**
     * 添加评论
     */
    Integer addRoot(RemarkRoot remarkRoot);


    Boolean isFrequentPost(Integer user_id, Integer article_id, String content);

    Boolean delAllRemarkByArticleId(Integer article_id);
}
