package top.pmj136.api.service;

import top.pmj136.api.entity.ArticleBrowse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 彭明久
 * @since 2020-11-26
 */
public interface IArticleBrowseService extends IService<ArticleBrowse> {

    /**
     * 添加浏览记录
     *
     * @param req Map
     */
    void track(Map<String, Integer> req);
}
