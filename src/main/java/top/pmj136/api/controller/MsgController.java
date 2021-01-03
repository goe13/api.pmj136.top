package top.pmj136.api.controller;


import org.springframework.web.bind.annotation.*;

import top.pmj136.api.annotation.LoginId;
import top.pmj136.api.service.impl.MsgNoticeServiceImpl;
import top.pmj136.api.service.impl.MsgRemarkServiceImpl;
import top.pmj136.api.service.impl.MsgStarServiceImpl;
import top.pmj136.api.service.impl.UserServiceImpl;
import top.pmj136.api.util.Result;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 彭明久
 * @since 2020-11-26
 */
@RestController
@RequestMapping("/msg")
public class MsgController {
    @Resource
    private MsgStarServiceImpl msgStarService;

    @Resource
    private MsgRemarkServiceImpl msgRemarkService;

    @Resource
    private MsgNoticeServiceImpl msgNoticeService;

    @Resource
    private UserServiceImpl userService;

    @PostMapping("/query")
    public Result queryMsg(@LoginId Integer user_id,
                           @RequestBody Map<String, Object> req) {
        Integer page= (Integer) req.get("page");
        Integer size= (Integer) req.get("size");
        String type = (String) req.get("type");
        if ("remark".equals(type)) return msgRemarkService.getMsg(page,size,user_id);
        if ("star".equals(type)) return msgStarService.getMsg(page,size,user_id);
        return msgNoticeService.getMsg(page,size,user_id);
    }

    @GetMapping("/count")
    public Result articleIndex(@LoginId(required = false) Integer user_id) {
        return userService.countMsg(user_id);
    }

    @PostMapping("/read")
    public Result read(@RequestBody Map<String, Object> req) {
        String type = (String) req.get("type");
        Integer msgId = (Integer) req.get("msg_id");
        if ("remark".equals(type)) msgRemarkService.read(msgId);
        if ("star".equals(type)) msgStarService.read(msgId);
        return Result.resolve();
    }

    @PostMapping("/del")
    public Result del(@RequestBody Map<String, Object> req) {
        String type = (String) req.get("type");
        Integer msgId = (Integer) req.get("msg_id");
        if ("remark".equals(type)) msgRemarkService.removeById(msgId);
        if ("star".equals(type)) msgStarService.removeById(msgId);
        return Result.resolve("删除成功");
    }

    @PostMapping("/delAll")
    public Result delAll(@LoginId Integer target_id,
                         @RequestBody Map<String, Object> req) {
        String type = (String) req.get("type");
        if ("remark".equals(type)) msgRemarkService.delAll(target_id);
        if ("star".equals(type)) msgStarService.delAll(target_id);
        if ("notice".equals(type)) msgNoticeService.delAll(target_id);
        return Result.resolve("删除成功");
    }
}
