package top.pmj136.api.mapper;

import top.pmj136.api.entity.RemarkChild;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 彭明久
 * @since 2020-11-26
 */
public interface RemarkChildMapper extends BaseMapper<RemarkChild> {
    /**
     * 添加回复
     */
    Integer addChild(RemarkChild reply);

    Boolean isFrequentPost(Integer user_id,Integer comment_id,String content);
}
