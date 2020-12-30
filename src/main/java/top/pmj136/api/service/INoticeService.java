package top.pmj136.api.service;

import top.pmj136.api.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import top.pmj136.api.util.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 彭明久
 * @since 2020-11-26
 */
public interface INoticeService extends IService<Notice> {
    /**
     * 添加关注
     *
     * @param entity Notice
     * @return Result
     */
    Result add(Notice entity);

    /**
     * 取消关注
     *
     * @param entity Notice
     * @return Result
     */
    Result cancel(Notice entity);
}
