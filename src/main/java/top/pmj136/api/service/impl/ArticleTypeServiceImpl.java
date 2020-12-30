package top.pmj136.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import top.pmj136.api.entity.ArticleType;
import top.pmj136.api.mapper.ArticleTypeMapper;
import top.pmj136.api.service.IArticleTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.pmj136.api.util.Result;

import java.util.HashMap;
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
public class ArticleTypeServiceImpl extends ServiceImpl<ArticleTypeMapper, ArticleType> implements IArticleTypeService {
    @Cacheable(cacheNames = "article", key = "'types'")
    public List<ArticleType> getTypes() {
        QueryWrapper<ArticleType> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id type", "text").eq("state", 1);
        return list(queryWrapper);
    }


    public Result getTypeList(Integer page, Integer size, String keyword) {
        return Result.resolve(baseMapper.getTypeList(new Page<>(page, size), keyword));
    }


    @CacheEvict(cacheNames = "article", key = "'types'")
    public Result add(ArticleType articleType) {
        QueryWrapper<ArticleType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("text", articleType.getText());
        ArticleType one = getOne(queryWrapper);
        if (one != null) return Result.reject("类别已存在");
        save(articleType);
        return Result.resolve("添加成功");
    }

    @CacheEvict(cacheNames = "article", key = "'types'")
    public Result update(ArticleType articleType) {
        updateById(articleType);
        return Result.resolve("更新成功");
    }
}
