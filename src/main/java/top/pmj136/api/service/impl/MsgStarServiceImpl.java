package top.pmj136.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.scheduling.annotation.Async;
import top.pmj136.api.entity.ArticleStar;
import top.pmj136.api.entity.MsgStar;
import top.pmj136.api.mapper.MsgStarMapper;
import top.pmj136.api.service.IMsgStarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.pmj136.api.util.Result;

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
public class MsgStarServiceImpl extends ServiceImpl<MsgStarMapper, MsgStar> implements IMsgStarService {


    public Result getMsg(Integer page, Integer size, Integer target_id) {
        return Result.resolve(baseMapper.getMsgList(new Page<>(page, size), target_id));
    }


    public void delAll(Integer target_id) {
        QueryWrapper<MsgStar> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("target_id", target_id);
        remove(queryWrapper);
    }

    @Async
    public void sendMsg(ArticleStar entity) {
        MsgStar msgStar = new MsgStar();
        msgStar.setTime(entity.getTime());
        msgStar.setUserId(entity.getUserId());
        msgStar.setArticleId(entity.getArticleId());
        msgStar.setTargetId(entity.getTarget_id());
        Integer post_id = isPost(msgStar);
        if (post_id == null)
            save(msgStar);
        else {
            msgStar.setId(post_id);
            msgStar.setIsReview(0);
            updateById(msgStar);
        }
    }

    @Async
    public void read(Integer msg_id) {
        MsgStar msgStar = new MsgStar();
        msgStar.setId(msg_id);
        msgStar.setIsReview(1);
        updateById(msgStar);
    }

    /*是否已发送过消息*/
    private Integer isPost(MsgStar msgStar) {
        QueryWrapper<MsgStar> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", msgStar.getUserId())
                .eq("article_id", msgStar.getArticleId())
                .eq("target_id", msgStar.getTargetId());
        MsgStar one = getOne(queryWrapper);
        if (one == null) return null;
        return one.getId();
    }

}
