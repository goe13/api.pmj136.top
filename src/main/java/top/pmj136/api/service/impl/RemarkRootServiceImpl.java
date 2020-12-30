package top.pmj136.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.scheduling.annotation.Async;
import top.pmj136.api.entity.RemarkRoot;
import top.pmj136.api.mapper.RemarkRootMapper;
import top.pmj136.api.service.IRemarkRootService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.pmj136.api.util.Result;
import top.pmj136.api.wordfilter.WordFilter;

import javax.annotation.Resource;
import java.util.HashMap;
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
public class RemarkRootServiceImpl extends ServiceImpl<RemarkRootMapper, RemarkRoot> implements IRemarkRootService {
    @Resource
    private ArticleServiceImpl articleService;

    @Resource
    private RemarkChildServiceImpl remarkChildService;

    @Resource
    private MsgRemarkServiceImpl msgRemarkService;

    @Resource
    private WordFilter wordFilter;

    public Result getList(Map<String, Integer> req) {
        Map<String, Object> map = new HashMap<>();
        Integer articleId = req.get("article_id");
        Integer authorId = articleService.getArticleAuthorId(articleId);
        if (authorId == null) return Result.reject();
        map.put("records", baseMapper.getList(req));
        QueryWrapper<RemarkRoot> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId);
        map.put("total", count(queryWrapper));
        map.put("author_id", authorId);
        return Result.resolve(map);
    }

    public Result add(RemarkRoot entity) {
        boolean isInclude = wordFilter.include(entity.getContent());
        if (isInclude) return Result.reject("评论失败！请勿发布违规评论");
        if (baseMapper.isFrequentPost(entity.getUserId(), entity.getArticleId(), entity.getContent()))
            return Result.reject("发送相同内容频繁，稍等一下再试吧！");
//        发送相同内容过于频繁
        baseMapper.addRoot(entity);
        articleService.increaseNum(entity.getArticleId(), "comment", 1);
        if (!entity.getUserId().equals(entity.getTargetId())) msgRemarkService.sendMsg(entity);
        Map<String, Object> resp = new HashMap<>();
        resp.put("id", entity.getId());
        resp.put("content", entity.getContent());
        return Result.resolve("评论成功", resp);
    }


    public Result del(Map<String, Object> req) {
        Integer id = (Integer) req.get("id");
        boolean b = removeById(id);
        remarkChildService.delByRoot(id);//同时删除该父评论下的子评论
        Integer size = (Integer) req.get("size");
        articleService.decreaseNum((Integer) req.get("article_id"), "comment", size);
        return Result.resolve(b ? "删除成功" : "未作删除", b);
    }
}
