package top.pmj136.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.scheduling.annotation.Async;
import top.pmj136.api.entity.Article;
import top.pmj136.api.mapper.ArticleMapper;
import top.pmj136.api.mapper.RemarkRootMapper;
import top.pmj136.api.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.pmj136.api.util.Result;
import top.pmj136.api.wordfilter.WordFilter;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    @Resource
    private UserServiceImpl userService;

    @Resource
    private RemarkRootMapper remarkRootMapper;

    @Resource
    private WordFilter wordFilter;

    public List<Map<String, Object>> getGrowth() {
        return baseMapper.getGrowth();
    }

    public Result recommend(Integer article_id) {
        QueryWrapper<Article> query1 = new QueryWrapper<>();
        query1.select("tag_ids").eq("id", article_id);
        Article a1 = getOne(query1);
        if (a1 == null) return Result.reject("文章不存在");
        String tag_ids = a1.getTagIds();
        return Result.resolve(baseMapper.getRecommends(article_id, tag_ids));
    }

    public Result add(Article entity) {
        boolean isInclude = wordFilter.include(entity.getTitle());
        if (isInclude) return Result.reject("系统检测到您的标题违规，请重新输入");

        entity.setContent(wordFilter.replace(entity.getContent()));
        entity.setReleaseTime(LocalDateTime.now());
        if (entity.getId() == null) userService.increaseIntegral(entity.getUserId(), 5);
        save(entity);
        return Result.resolve("发布成功");
    }

    public Result del(Integer id) {
        removeById(id);
        return Result.resolve("已添加至回收站");
    }

    public Result forceDel(Integer id) {
        baseMapper.forceDel(id);
        remarkRootMapper.delAllRemarkByArticleId(id);
        return Result.resolve("删除成功");
    }

    public Result restore(Integer id) {
        baseMapper.restoreArticle(id);
        return Result.resolve("已恢复");
    }

    public Result updateData(Article entity) {
        boolean isInclude = wordFilter.include(entity.getTitle());
        if (isInclude) return Result.reject("系统检测到您的标题违规，请重新输入");

        entity.setContent(wordFilter.replace(entity.getContent()));
        entity.setReleaseTime(LocalDateTime.now());
        updateById(entity);
        return Result.resolve("更新成功");
    }

    public Result get(Integer id) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,title,content,type,tag_ids,user_id").eq("id", id);
        return Result.resolve(getOne(queryWrapper));
    }

    public Result getDetailById(Map<String, Integer> req) {
        Integer article_id = req.get("article_id");
        Integer user_id = req.get("user_id");
        return Result.resolve(baseMapper.getDetailById(article_id, user_id));
    }

    public Result getList(Integer page, Integer size, Integer user_id, Integer type, Integer order) {
        return Result.resolve(baseMapper.getList(new Page<>(page, size), user_id, type, order));
    }

    public Result search(Map<String, Object> req) {
        Map<String, Object> resp = new HashMap<>();
        if (req.get("type").equals(-1)) {
            List<Map<String, Object>> record = baseMapper.searchUserByNick(req);
            long total = record.size();

            req.put("type", 1);
            long total2 = (long) baseMapper.getSearchTotal(req);
            List<Map<String, Object>> item = baseMapper.search(req);

            record.addAll(item);
            total = total + total2;
            resp.put("total", total);
            resp.put("record", record);
        } else {
            resp.put("total", baseMapper.getSearchTotal(req));
            resp.put("record", baseMapper.search(req));
        }
        return Result.resolve(resp);
    }


    /*获取文章作者id*/
    public Integer getArticleAuthorId(Integer articleId) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_id").eq("id", articleId);
        Article article = getOne(queryWrapper);
        return article == null ? null : article.getUserId();
    }

    public Result getManageArticleList(Integer page, Integer size, String keyword) {
        return Result.resolve(baseMapper.getManageArticleList(new Page<>(page, size), keyword));
    }

    @Async
    public void increaseNum(Integer article_id, String pro, Integer x) {
        String sql = "star_count = star_count+" + x;
        if (pro.equals("comment")) sql = "comment_count=comment_count+" + x;
        if (pro.equals("view")) sql = "view_count=view_count+" + x;
        UpdateWrapper<Article> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql(sql).eq("id", article_id);
        update(updateWrapper);
    }

    @Async
    public void decreaseNum(Integer article_id, String pro, Integer x) {
        String sql = "star_count = star_count-" + x;
        if (pro.equals("comment")) sql = "comment_count=comment_count-" + x;
        if (pro.equals("view")) sql = "view_count=view_count-" + x;
        UpdateWrapper<Article> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql(sql).eq("id", article_id);
        update(updateWrapper);
    }
}
