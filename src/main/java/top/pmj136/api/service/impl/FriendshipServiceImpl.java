package top.pmj136.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import top.pmj136.api.entity.Friendship;
import top.pmj136.api.mapper.FriendshipMapper;
import top.pmj136.api.service.IFriendshipService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.pmj136.api.util.Result;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 彭明久
 * @since 2020-11-26
 */
@Service
public class FriendshipServiceImpl extends ServiceImpl<FriendshipMapper, Friendship> implements IFriendshipService {
    @Cacheable(cacheNames = "friend", key = "'links'")
    public List<Friendship> getLinks() {
        return list();
    }

    public Result getLinkList(Integer page, Integer size, String keyword) {
        return Result.resolve(baseMapper.getLinkList(new Page<>(page, size), keyword));
    }

    @CacheEvict(cacheNames = "friend", key = "'links'")
    public Result add(Friendship entity) {
        QueryWrapper<Friendship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", entity.getTitle());
        Friendship one = getOne(queryWrapper);
        if (one != null) return Result.reject("友链名已存在");
        save(entity);
        return Result.resolve("添加成功");
    }

}
