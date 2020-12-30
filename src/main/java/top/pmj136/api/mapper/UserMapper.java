package top.pmj136.api.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.pmj136.api.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 彭明久
 * @since 2020-11-26
 */
public interface UserMapper extends BaseMapper<User> {
    Integer addUser(User user);

    Map<String, Object> getById(Integer find_id, Integer user_id);

    IPage<Map<String, Object>> getDynamicsList(Page<Map<String, Object>> page,
                                               Integer user_id,
                                               Integer find_id,
                                               Integer type);

    IPage<Map<String, Object>> getUserList(Page<Map<String, Object>> page,
                                           String keyword);


    /*获取签到信息*/
    Map<String, Object> getSignInfo(Integer user_id);

    Map<String, Object> isContinuousSign(Integer user_id);

    Boolean unbind(Map<String, Object> req);

    Integer countGender(Integer gender);

    List<Map<String, Object>> getGrowth();


}
