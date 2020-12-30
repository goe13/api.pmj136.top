package top.pmj136.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.scheduling.annotation.Async;
import top.pmj136.api.entity.MsgNotice;
import top.pmj136.api.entity.Notice;
import top.pmj136.api.mapper.MsgNoticeMapper;
import top.pmj136.api.service.IMsgNoticeService;
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
public class MsgNoticeServiceImpl extends ServiceImpl<MsgNoticeMapper, MsgNotice> implements IMsgNoticeService {

    public Result getMsg(Integer page, Integer size, Integer target_id) {
        return Result.resolve(baseMapper.getMsgList(new Page<>(page, size), target_id));
    }

    public void delAll(Integer target_id) {
        QueryWrapper<MsgNotice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("target_id", target_id);
        remove(queryWrapper);
    }

    @Async
    public void sendMsg(Notice entity) {
        MsgNotice msgNotice = new MsgNotice();
        msgNotice.setTime(entity.getTime());
        msgNotice.setAction(entity.getAction());
        msgNotice.setUserId(entity.getNoticeId());
        msgNotice.setTargetId(entity.getTargetId());
        Integer post_id = isPost(msgNotice);
        if (post_id == null)
            save(msgNotice);
        else {
            msgNotice.setId(post_id);
            msgNotice.setIsReview(0);
            updateById(msgNotice);
        }
    }

    /*是否已发送过消息*/
    private Integer isPost(MsgNotice msgNotice) {
        QueryWrapper<MsgNotice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", msgNotice.getUserId())
                .eq("target_id", msgNotice.getTargetId())
                .eq("action", msgNotice.getAction());
        MsgNotice one = getOne(queryWrapper);
        if (one == null) return null;
        return one.getId();
    }

}
