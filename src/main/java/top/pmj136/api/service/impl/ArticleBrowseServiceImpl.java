package top.pmj136.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.scheduling.annotation.Async;
import top.pmj136.api.entity.ArticleBrowse;
import top.pmj136.api.mapper.ArticleBrowseMapper;
import top.pmj136.api.service.IArticleBrowseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
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
public class ArticleBrowseServiceImpl extends ServiceImpl<ArticleBrowseMapper, ArticleBrowse> implements IArticleBrowseService {
    @Resource
    private ArticleServiceImpl articleService;


    @Async
    public void track(Map<String, Integer> req) {
        Integer article_id = req.get("article_id");
        Integer user_id = req.get("user_id");
        if (user_id != null) {
            ArticleBrowse browse = findRecord(user_id, article_id);
            if (browse == null) {
                addRecord(user_id, article_id);
                articleService.increaseNum(article_id, "view", 1);
            }
        } else
            articleService.increaseNum(article_id, "view", 1);
    }

    private ArticleBrowse findRecord(Integer user_id, Integer article_id) {
        QueryWrapper<ArticleBrowse> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id").eq("user_id", user_id).eq("article_id", article_id);
        return getOne(queryWrapper);
    }

    private void addRecord(Integer user_id, Integer article_id) {
        ArticleBrowse browse = new ArticleBrowse();
        browse.setArticleId(article_id);
        browse.setUserId(user_id);
        browse.setTime(LocalDateTime.now());
        save(browse);
    }

}
