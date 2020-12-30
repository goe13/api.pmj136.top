package top.pmj136.api.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.pmj136.api.entity.ArticleTag;
import top.pmj136.api.entity.ArticleType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 彭明久
 * @since 2020-11-26
 */
public interface ArticleTypeMapper extends BaseMapper<ArticleType> {
    IPage<ArticleTag> getTypeList(Page<ArticleTag> page, String keyword);
}
