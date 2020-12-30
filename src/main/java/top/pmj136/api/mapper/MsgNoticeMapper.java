package top.pmj136.api.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.pmj136.api.entity.MsgNotice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 彭明久
 * @since 2020-11-26
 */
public interface MsgNoticeMapper extends BaseMapper<MsgNotice> {
    IPage<Map<String,Object>> getMsgList(Page<Map<String,Object>> page, Integer target_id);

    Integer getNewMsgTotal(Map<String, Object> req);
}
