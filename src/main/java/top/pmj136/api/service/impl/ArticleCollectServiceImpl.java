package top.pmj136.api.service.impl;

import top.pmj136.api.entity.ArticleCollect;
import top.pmj136.api.mapper.ArticleCollectMapper;
import top.pmj136.api.service.IArticleCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.pmj136.api.util.Result;

import java.time.LocalDateTime;
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
public class ArticleCollectServiceImpl extends ServiceImpl<ArticleCollectMapper, ArticleCollect> implements IArticleCollectService {
    public Result add(ArticleCollect entity) {
        entity.setTime(LocalDateTime.now());
        save(entity);
        return Result.resolve("收藏成功");
    }

    public Result cancel(Map<String, Object> req) {
        removeByMap(req);
        return Result.resolve("已取消收藏");
    }
}
