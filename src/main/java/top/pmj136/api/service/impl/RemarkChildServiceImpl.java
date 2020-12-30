package top.pmj136.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.scheduling.annotation.Async;
import top.pmj136.api.entity.RemarkChild;
import top.pmj136.api.entity.RemarkRoot;
import top.pmj136.api.mapper.RemarkChildMapper;
import top.pmj136.api.service.IRemarkChildService;
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
public class RemarkChildServiceImpl extends ServiceImpl<RemarkChildMapper, RemarkChild> implements IRemarkChildService {
    @Resource
    private ArticleServiceImpl articleService;

    @Resource
    private MsgRemarkServiceImpl msgRemarkService;

    @Resource
    private WordFilter wordFilter;

    public Result addReply(RemarkChild entity) {
        boolean isInclude = wordFilter.include(entity.getContent());
        if (isInclude) return Result.reject("回复失败！请勿发布违规评论");
        if (baseMapper.isFrequentPost(entity.getUserId(), entity.getCommentId(), entity.getContent()))
            return Result.reject("发送相同内容频繁，稍等一下再试吧！");

        baseMapper.addChild(entity);
        articleService.increaseNum(entity.getArticleId(), "comment", 1);
        if (!entity.getUserId().equals(entity.getTargetId())) msgRemarkService.sendMsg(entity);
        Map<String, Object> resp = new HashMap<>();
        resp.put("id", entity.getId());
        resp.put("content", entity.getContent());
        return Result.resolve("回复成功", resp);
    }

    public Result del(Map<String, Integer> req) {
        Integer id = req.get("id");
        boolean b = removeById(id);
        articleService.decreaseNum(req.get("article_id"), "comment", 1);
        return Result.resolve(b ? "删除成功" : "未作删除", b);
    }


    @Async
    public void delByRoot(Integer comment_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("comment_id", comment_id);
        removeByMap(map);
    }

}
