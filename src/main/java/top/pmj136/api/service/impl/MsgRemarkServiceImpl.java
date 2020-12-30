package top.pmj136.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.scheduling.annotation.Async;
import top.pmj136.api.entity.MsgRemark;
import top.pmj136.api.entity.RemarkChild;
import top.pmj136.api.entity.RemarkRoot;
import top.pmj136.api.mapper.MsgRemarkMapper;
import top.pmj136.api.service.IMsgRemarkService;
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
public class MsgRemarkServiceImpl extends ServiceImpl<MsgRemarkMapper, MsgRemark> implements IMsgRemarkService {

    public Result getMsg(Integer page, Integer size, Integer target_id) {
        return Result.resolve(baseMapper.getMsgList(new Page<>(page, size), target_id));
    }

    public void delAll(Integer target_id) {
        QueryWrapper<MsgRemark> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("target_id", target_id);
        remove(queryWrapper);
    }

    @Async
    public void sendMsg(RemarkRoot entity) {
        setData(entity.getAction(), entity.getUserId(), entity.getTargetId(), entity.getArticleId(), entity.getId(), entity.getTime());
    }

    @Async
    public void sendMsg(RemarkChild entity) {
        setData(entity.getAction(), entity.getUserId(), entity.getTargetId(), entity.getArticleId(), entity.getId(), entity.getTime());
    }


    @Async
    public void read(Integer msg_id) {
        MsgRemark msgRemark = new MsgRemark();
        msgRemark.setId(msg_id);
        msgRemark.setIsReview(1);
        updateById(msgRemark);
    }


    private void setData(Integer level, Integer userId, Integer targetId, Integer articleId, Integer id, LocalDateTime time) {
        MsgRemark msgRemark = new MsgRemark();
        msgRemark.setAction(level);
        msgRemark.setUserId(userId);
        msgRemark.setTargetId(targetId);
        msgRemark.setArticleId(articleId);
        msgRemark.setRemarkId(id);
        msgRemark.setTime(time);
        save(msgRemark);
    }
}
