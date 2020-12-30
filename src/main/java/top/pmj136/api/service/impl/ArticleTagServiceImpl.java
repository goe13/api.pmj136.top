package top.pmj136.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import top.pmj136.api.entity.ArticleTag;
import top.pmj136.api.mapper.ArticleTagMapper;
import top.pmj136.api.service.IArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.pmj136.api.util.Result;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 彭明久
 * @since 2020-11-26
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements IArticleTagService {
    public List<Map<String, Object>> getHotTags() {
        return baseMapper.getHotTags();
    }

    /*用户前台热点tag*/
    @Cacheable(cacheNames = "article", key = "'tags'")
    public List<ArticleTag> getTags() {
        QueryWrapper<ArticleTag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", 1);
        return list(queryWrapper);
    }

    /*获取tag列表*/
    public Result getTagList(Integer page, Integer size, String keyword) {
        return Result.resolve(baseMapper.getTagList(new Page<>(page, size), keyword));
    }

    @CacheEvict(cacheNames = "article", key = "'tags'")
    public Result add(ArticleTag entity) {
        QueryWrapper<ArticleTag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", entity.getTitle());
        ArticleTag one = getOne(queryWrapper);
        if (one != null) return Result.reject("标签已存在");
        save(entity);
        return Result.resolve("添加成功");
    }

    @CacheEvict(cacheNames = "article", key = "'tags'")
    public Result update(ArticleTag entity) {
        updateById(entity);
        return Result.resolve("更新成功");
    }
}
