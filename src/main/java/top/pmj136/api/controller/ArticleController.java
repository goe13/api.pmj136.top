package top.pmj136.api.controller;


import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import top.pmj136.api.entity.*;
import top.pmj136.api.service.impl.*;
import top.pmj136.api.util.Result;
import top.pmj136.api.util.UploadUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author 彭明久
 * @since 2020-11-26
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleServiceImpl articleService;

    @Resource
    private ArticleCollectServiceImpl articleCollectService;

    @Resource
    private ArticleBrowseServiceImpl articleBrowseService;

    @Resource
    private ArticleStarServiceImpl articleStarService;

    @Resource
    private ArticleTypeServiceImpl articleTypeService;

    @Resource
    private ArticleTagServiceImpl articleTagService;

    @Resource
    private UploadUtil uploadUtil;


    @PostMapping("/recommend")
    public Result recommend(@RequestBody Article article) {
        return articleService.recommend(article.getId());
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid Article article) {
        return articleService.add(article);
    }

    @PostMapping("/conceal")
    public Result conceal(@RequestBody Article article) {
        return articleService.del(article.getId());
    }

    @PostMapping("/del")
    public Result del(@RequestBody Article article) {
        return articleService.forceDel(article.getId());
    }

    @PostMapping("/restore")
    public Result restore(@RequestBody Article article) {
        return articleService.restore(article.getId());
    }

    @PostMapping("/update")
    public Result update(@RequestBody Article article) {
        return articleService.updateData(article);
    }

    @GetMapping("/get")
    public Result get(@RequestParam("id") Integer id) {
        return articleService.get(id);
    }

    //article_id,user_id
    @PostMapping("/detail")
    public Result getDetailById(@RequestBody Map<String, Integer> req,
                                @ModelAttribute("user_id") Integer user_id) {
        req.put("user_id", user_id);
        articleBrowseService.track(req);
        return articleService.getDetailById(req);
    }

    //type,order,page,size
    @PostMapping("/list")
    public Result getList(@RequestBody Map<String, Object> req,
                          @ModelAttribute("user_id") Integer user_id) {
        Integer page = (Integer) req.get("page");
        Integer size = (Integer) req.get("size");
        Integer type = (Integer) req.get("type");
        Integer order = (Integer) req.get("order");
        return articleService.getList(page, size, user_id, type, order);
    }

    //type,page,size,keyword,user_id
    @PostMapping("/search")
    public Result search(@RequestBody Map<String, Object> req,
                         @ModelAttribute("user_id") Integer user_id) {
        req.put("user_id", user_id);
        return articleService.search(req);
    }

    @PostMapping("/img/upload")
    public Result saveImg(@RequestParam("img") MultipartFile file) {
        Map<String, Object> objectMap = uploadUtil.push("article", file);
        if ((boolean) objectMap.get("flag")) return Result.resolve(objectMap.get("url"));
        return Result.reject((String) objectMap.get("msg"));
    }

    @PostMapping("/img/del")
    public Result delImg(@RequestBody Map<String, String> req) {
        return Result.resolve(uploadUtil.del(req.get("name")));
    }

    /*添加收藏*/
    @PostMapping("/collect/add")
    public Result add(@RequestBody ArticleCollect articleCollect) {
        return articleCollectService.add(articleCollect);
    }

    /*取消收藏*/
    @PostMapping("/collect/cancel")
    public Result cancel(@RequestBody Map<String, Object> req) {
        return articleCollectService.cancel(req);
    }


    /*添加点赞 */
    @PostMapping("/star/add")
    public Result add(@RequestBody ArticleStar req,
                      @ModelAttribute("user_id") Integer user_id) {
        req.setUserId(user_id);
        return articleStarService.add(req);
    }

    /*添加收藏*/
    @PostMapping("/star/cancel")
    public Result cancel(@RequestBody Map<String, Integer> req,
                         @ModelAttribute("user_id") Integer user_id) {
        req.put("user_id", user_id);
        return articleStarService.cancel(req);
    }


    /*================================admin==========================*/
    @PostMapping("/manage/list")
    public Result getArticleList(@RequestBody Map<String, Object> req) {
        Integer page = (Integer) req.get("page");
        Integer size = (Integer) req.get("size");
        String keyword = (String) req.get("keyword");
        return articleService.getManageArticleList(page, size, keyword);
    }

    @PostMapping("/tag/list")
    public Result tagList(@RequestBody Map<String, Object> req) {
        Integer page = (Integer) req.get("page");
        Integer size = (Integer) req.get("size");
        String keyword = (String) req.get("keyword");
        return articleTagService.getTagList(page, size, keyword);
    }


    @PostMapping("/tag/add")
    public Result tagAdd(@RequestBody @Valid ArticleTag articleTag) {
        return articleTagService.add(articleTag);
    }

    @PostMapping("/tag/update")
    public Result tagUpdate(@RequestBody ArticleTag articleTag) {
        return articleTagService.update(articleTag);
    }

    @PostMapping("/type/list")
    public Result typeList(@RequestBody Map<String, Object> req) {
        Integer page = (Integer) req.get("page");
        Integer size = (Integer) req.get("size");
        String keyword = (String) req.get("keyword");
        return articleTypeService.getTypeList(page, size, keyword);
    }

    /*新增文章类型*/
    @PostMapping("/type/add")
    public Result typeAdd(@RequestBody @Valid ArticleType articleType) {
        return articleTypeService.add(articleType);
    }

    /*更新文章类型*/
    @PostMapping("/type/update")
    public Result update(@RequestBody ArticleType articleType) {
        return articleTypeService.update(articleType);
    }

}
