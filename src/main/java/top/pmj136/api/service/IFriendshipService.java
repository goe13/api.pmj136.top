package top.pmj136.api.service;

import top.pmj136.api.entity.Friendship;
import com.baomidou.mybatisplus.extension.service.IService;
import top.pmj136.api.util.Result;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 彭明久
 * @since 2020-11-26
 */
public interface IFriendshipService extends IService<Friendship> {
    /**
     * 获取所有友链
     *
     * @return List
     */
    List<Friendship> getLinks();

    /**
     * 管理员分页获取友链列表
     *
     * @param page    pageIndex
     * @param size    size
     * @param keyword keyword
     * @return Result
     */
    Result getLinkList(Integer page, Integer size, String keyword);

    /**
     * 管理员添加友链
     *
     * @param entity Friendship
     * @return Result
     */
    Result add(Friendship entity);
}
