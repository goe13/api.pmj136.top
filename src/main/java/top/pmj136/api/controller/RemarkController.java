package top.pmj136.api.controller;


import org.springframework.web.bind.annotation.*;

import top.pmj136.api.entity.RemarkChild;
import top.pmj136.api.entity.RemarkRoot;
import top.pmj136.api.service.impl.RemarkChildServiceImpl;
import top.pmj136.api.service.impl.RemarkRootServiceImpl;
import top.pmj136.api.util.Result;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author 彭明久
 * @since 2020-11-26
 */
@RestController
@RequestMapping("/remark")
public class RemarkController {
    @Resource
    private RemarkRootServiceImpl remarkRootService;

    @Resource
    private RemarkChildServiceImpl remarkChildService;

    //article_id,page,size
    @PostMapping("/root/list")
    public Result getList(@RequestBody Map<String, Integer> req) {
        return remarkRootService.getList(req);
    }

    //Comment
    /*添加评论*/
    @PostMapping("/root/add")
    public Result addRootRemark(@RequestBody @Valid RemarkRoot entity,
                                @ModelAttribute("user_id") Integer user_id) {
        entity.setUserId(user_id);
        return remarkRootService.add(entity);
    }

    /*删除评论*/
    @PostMapping("/root/del")
    public Result delRootRemark(@RequestBody Map<String, Object> req) {
        return remarkRootService.del(req);
    }


    @PostMapping("/child/add")
    public Result addChildRemark(@RequestBody @Valid RemarkChild entity,
                                 @ModelAttribute("user_id") Integer user_id) {
        entity.setUserId(user_id);
        return remarkChildService.addReply(entity);
    }

    @PostMapping("/child/del")
    public Result delChildRemark(@RequestBody Map<String, Integer> req) {
        return remarkChildService.del(req);
    }
}
