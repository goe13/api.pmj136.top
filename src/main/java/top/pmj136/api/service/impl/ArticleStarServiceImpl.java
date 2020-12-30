package top.pmj136.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.pmj136.api.entity.ArticleStar;
import top.pmj136.api.mapper.ArticleStarMapper;
import top.pmj136.api.service.IArticleStarService;
import top.pmj136.api.util.Result;

import javax.annotation.Resource;
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
public class ArticleStarServiceImpl extends ServiceImpl<ArticleStarMapper, ArticleStar> implements IArticleStarService {
    @Resource
    private ArticleServiceImpl articleService;

    @Resource
    private MsgStarServiceImpl msgStarService;

    public Result add(ArticleStar entity) {
        entity.setTime(LocalDateTime.now());
        save(entity);
        articleService.increaseNum(entity.getArticleId(), "star", 1);
        if (!entity.getUserId().equals(entity.getTarget_id())) msgStarService.sendMsg(entity);
        return Result.resolve("点赞成功", true);
    }

    public Result cancel(Map<String, Integer> req) {
        Integer article_id = req.get("article_id");
        Integer user_id = req.get("user_id");
        QueryWrapper<ArticleStar> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user_id).eq("article_id", article_id);
        remove(queryWrapper);
        articleService.decreaseNum(article_id, "star", 1);
        return Result.resolve("取消成功", true);
    }
}

