package top.pmj136.api.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;
import top.pmj136.api.entity.Friendship;
import top.pmj136.api.service.impl.*;
import top.pmj136.api.util.Result;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 彭明久
 * @since 2020-11-26
 */
@RestController
@RequestMapping("/app")
public class AppController {
    @Resource
    private ArticleTypeServiceImpl articleTypeService;

    @Resource
    private ArticleTagServiceImpl articleTagService;

    @Resource
    private FriendshipServiceImpl friendshipService;

    @Resource
    private UserServiceImpl userService;

    @Resource
    private ArticleServiceImpl articleService;


    @GetMapping("/index")
    public Result articleIndex(@ModelAttribute("user_id") Integer user_id) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("article_types", articleTypeService.getTypes());
        resp.put("hot_tags", articleTagService.getHotTags());
        resp.put("friend_links", friendshipService.getLinks());
        resp.put("sign_info", userService.getSignInfo(user_id));
        return Result.resolve(resp);
    }


    @GetMapping("/md")
    public Result md() {
        Map<String, Object> resp = new HashMap<>();
        resp.put("types", articleTypeService.getTypes());
        resp.put("tags", articleTagService.getTags());
        return Result.resolve(resp);
    }


    /*================================admin==========================*/
    @GetMapping("/manage/home")
    public Result home() {
        Map<String, Object> resp = new HashMap<>();
        resp.put("user_total", userService.count());
        resp.put("article_total", articleService.count());
        resp.put("gender_info", userService.getGenderInfo());
        Map<String, Object> growth = new HashMap<>();
        growth.put("user", userService.getGrowth());
        growth.put("article", articleService.getGrowth());
        resp.put("growth", growth);
        return Result.resolve(resp);
    }

    @PostMapping("/link/list")
    public Result linkList(@RequestBody Map<String, Object> req) {
        Integer page = (Integer) req.get("page");
        Integer size = (Integer) req.get("size");
        String keyword = (String) req.get("keyword");
        return friendshipService.getLinkList(page, size, keyword);
    }

    @PostMapping("/link/add")
    public Result linkAdd(@RequestBody @Valid Friendship friendship) {
        return friendshipService.add(friendship);
    }

    @PostMapping("/link/del")
    @CacheEvict(cacheNames = "friend", key = "'links'")
    public Result linkDel(@RequestBody Friendship friendship) {
        return Result.resolve("删除成功", friendshipService.removeById(friendship.getId()));
    }
}
