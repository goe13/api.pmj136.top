package top.pmj136.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.pmj136.api.entity.Notice;
import top.pmj136.api.mapper.NoticeMapper;
import top.pmj136.api.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.pmj136.api.util.Result;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 彭明久
 * @since 2020-11-26
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {
    @Resource
    private UserServiceImpl userService;

    @Resource
    private MsgNoticeServiceImpl msgNoticeService;

    public Result add(Notice entity) {
        entity.setTime(LocalDateTime.now());
        save(entity);
        userService.alterNotice(entity.getNoticeId(), "increase");
        userService.alterFans(entity.getTargetId(), "increase");
        msgNoticeService.sendMsg(entity);
        return Result.resolve("关注成功", true);
    }

    public Result cancel(Notice entity) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("notice_id", entity.getNoticeId())
                .eq("target_id", entity.getTargetId());
        remove(queryWrapper);
        userService.alterNotice(entity.getNoticeId(), "decrease");
        userService.alterFans(entity.getTargetId(), "decrease");
        entity.setTime(LocalDateTime.now());
        msgNoticeService.sendMsg(entity);
        return Result.resolve("取关成功", true);
    }
}
