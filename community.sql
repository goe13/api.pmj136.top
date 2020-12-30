/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : community

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 22/12/2020 17:23:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for jiu_advert
-- ----------------------------
DROP TABLE IF EXISTS `jiu_advert`;
CREATE TABLE `jiu_advert`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jiu_advert
-- ----------------------------

-- ----------------------------
-- Table structure for jiu_article
-- ----------------------------
DROP TABLE IF EXISTS `jiu_article`;
CREATE TABLE `jiu_article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '文章标题',
  `initial_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '文章第一张图片',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '文章内容/md字符串',
  `release_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `type` tinyint(2) NULL DEFAULT NULL COMMENT '文章类型/例如 公告',
  `tag_ids` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章tag/例如JavaScript、Java等一个或多个',
  `star_count` int(11) NULL DEFAULT 0 COMMENT '点赞数 ',
  `comment_count` int(11) NULL DEFAULT 0 COMMENT '评论数',
  `view_count` int(11) NULL DEFAULT 0 COMMENT '阅读量',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '发布者id',
  `is_del` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of jiu_article
-- ----------------------------
INSERT INTO `jiu_article` VALUES (1, '测试1', '', '测试1', '2020-12-01 16:57:19', 5, '1', 1, 20, 6, 1, 0);
INSERT INTO `jiu_article` VALUES (2, '1', NULL, '1', '2020-12-02 14:35:27', 2, '5', 0, 0, 1, 1, 0);
INSERT INTO `jiu_article` VALUES (3, '2', NULL, '2', '2020-12-02 14:35:46', 4, '5,6,20', 0, 0, 0, 1, 0);
INSERT INTO `jiu_article` VALUES (4, '使用create-react-app 创建的工程，并且配置支持less的方式', NULL, '22', '2020-12-03 16:22:07', 5, '53', 2, 24, 17, 1, 0);
INSERT INTO `jiu_article` VALUES (5, '432432', NULL, '4324', '2020-12-02 14:36:22', 5, '28', 0, 0, 1, 1, 0);
INSERT INTO `jiu_article` VALUES (6, '4234324324', NULL, '43243242', '2020-12-02 14:36:41', 5, '54', 1, 0, 2, 1, 0);
INSERT INTO `jiu_article` VALUES (7, '423432432432432', NULL, 'JFK是JFK上了飞机书房里看书 ', '2020-12-02 14:37:04', 5, '6', 1, 4, 4, 1, 0);
INSERT INTO `jiu_article` VALUES (8, '京东卡了解到；', NULL, '开发建设了房间', '2020-12-02 14:37:22', 4, '51', 1, 3, 2, 1, 0);
INSERT INTO `jiu_article` VALUES (9, '认可威力惊人了我看nklkJlkkgjdkgjkdlgjkfdg加快了更加深刻了解顾客的结果打开两个开发大家赶快来发动进攻发动机两个', 'https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1607271280&di=493c3d4c77e4d87ccce3853ed3861d37&src=http://a4.att.hudong.com/27/67/01300000921826141299672233506.jpg', '狂热我困扰了我看人建立抗日额外', '2020-12-02 14:37:42', 4, '6,48', 2, 1, 13, 1, 0);
INSERT INTO `jiu_article` VALUES (10, 'Vue创建响应式全局数据--Vue.observable() 实现类似vuex的状态管理功能', '', '困扰了我', '2020-12-03 16:21:26', 3, '49', 1, 10, 6, 1, 0);
INSERT INTO `jiu_article` VALUES (11, '测试', NULL, '哈哈哈', '2020-12-07 11:00:02', 5, '14', 1, 3, 3, 2, 0);
INSERT INTO `jiu_article` VALUES (12, '四级必过技巧', NULL, 'test', '2020-12-07 21:21:42', 5, '15', 1, 1, 3, 1, 0);
INSERT INTO `jiu_article` VALUES (13, '今天又没抓到羊', NULL, '1234', '2020-12-07 23:18:29', 5, '3,2,6', 1, 0, 4, 4, 0);
INSERT INTO `jiu_article` VALUES (14, '1', NULL, '1', '2020-12-10 19:40:37', 1, '1', 1, 0, 0, 1, 0);
INSERT INTO `jiu_article` VALUES (15, '1', NULL, '1', '2020-12-10 19:41:12', 3, '3', 0, 3, 1, 1, 0);
INSERT INTO `jiu_article` VALUES (16, '2', NULL, '2', '2020-12-10 19:41:47', 2, '2', 1, 0, 1, 1, 0);
INSERT INTO `jiu_article` VALUES (17, '1fdsfsdfdsf', '', '1', '2020-12-13 11:01:42', 2, '2', 0, 2, 6, 1, 0);
INSERT INTO `jiu_article` VALUES (20, '1111111', '', '1', '2020-12-16 17:05:58', 3, '60', 0, 0, 3, 1, 0);
INSERT INTO `jiu_article` VALUES (21, 'test', '', 'test', '2020-12-17 14:51:39', 1, '69,63,67', 2, 0, 1, 4, 0);
INSERT INTO `jiu_article` VALUES (22, '1', '', '1', '2020-12-20 20:40:07', 3, '5', 1, 0, 0, 4, 0);
INSERT INTO `jiu_article` VALUES (23, '2', '', '2', '2020-12-20 20:40:39', 2, '11', 1, 0, 1, 4, 0);
INSERT INTO `jiu_article` VALUES (24, '3', '', '3', '2020-12-20 20:41:02', 5, '29', 1, 13, 7, 4, 0);
INSERT INTO `jiu_article` VALUES (25, 'hahah', '', '双方的', '2020-12-22 17:22:44', 2, '2', 0, 0, 1, 4, 0);

-- ----------------------------
-- Table structure for jiu_article_browse
-- ----------------------------
DROP TABLE IF EXISTS `jiu_article_browse`;
CREATE TABLE `jiu_article_browse`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '浏览者_id',
  `article_id` int(11) NULL DEFAULT NULL COMMENT '文章_id',
  `time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jiu_article_browse
-- ----------------------------
INSERT INTO `jiu_article_browse` VALUES (1, 1, 1, '2020-12-01 19:58:01');
INSERT INTO `jiu_article_browse` VALUES (2, 2, 1, '2020-12-01 22:10:58');
INSERT INTO `jiu_article_browse` VALUES (3, 1, 10, '2020-12-02 14:53:12');
INSERT INTO `jiu_article_browse` VALUES (4, 1, 9, '2020-12-02 14:53:20');
INSERT INTO `jiu_article_browse` VALUES (5, 1, 8, '2020-12-02 14:53:24');
INSERT INTO `jiu_article_browse` VALUES (6, 2, 10, '2020-12-02 15:11:50');
INSERT INTO `jiu_article_browse` VALUES (7, 1, 7, '2020-12-02 18:23:21');
INSERT INTO `jiu_article_browse` VALUES (8, 2, 9, '2020-12-03 10:26:05');
INSERT INTO `jiu_article_browse` VALUES (9, 2, 7, '2020-12-03 12:01:31');
INSERT INTO `jiu_article_browse` VALUES (10, 2, 8, '2020-12-03 12:15:02');
INSERT INTO `jiu_article_browse` VALUES (11, 2, 4, '2020-12-03 12:25:46');
INSERT INTO `jiu_article_browse` VALUES (12, 1, 4, '2020-12-03 12:54:47');
INSERT INTO `jiu_article_browse` VALUES (13, 1, NULL, '2020-12-03 19:45:49');
INSERT INTO `jiu_article_browse` VALUES (14, 1, NULL, '2020-12-03 19:45:52');
INSERT INTO `jiu_article_browse` VALUES (15, 1, NULL, '2020-12-03 19:45:55');
INSERT INTO `jiu_article_browse` VALUES (16, 2, 11, '2020-12-07 11:03:18');
INSERT INTO `jiu_article_browse` VALUES (17, 1, 11, '2020-12-07 11:09:21');
INSERT INTO `jiu_article_browse` VALUES (18, 2, 12, '2020-12-07 21:34:00');
INSERT INTO `jiu_article_browse` VALUES (19, 1, 12, '2020-12-07 21:38:21');
INSERT INTO `jiu_article_browse` VALUES (20, 1, 13, '2020-12-09 12:10:23');
INSERT INTO `jiu_article_browse` VALUES (21, 1, 6, '2020-12-09 12:28:42');
INSERT INTO `jiu_article_browse` VALUES (22, 3, 13, '2020-12-09 12:29:23');
INSERT INTO `jiu_article_browse` VALUES (23, 3, 11, '2020-12-09 12:30:13');
INSERT INTO `jiu_article_browse` VALUES (24, 3, 9, '2020-12-09 12:30:35');
INSERT INTO `jiu_article_browse` VALUES (25, 1, 2, '2020-12-09 12:34:50');
INSERT INTO `jiu_article_browse` VALUES (26, 1, 17, '2020-12-10 19:45:22');
INSERT INTO `jiu_article_browse` VALUES (27, 3, 17, '2020-12-10 20:34:26');
INSERT INTO `jiu_article_browse` VALUES (28, 1, 16, '2020-12-13 14:57:34');
INSERT INTO `jiu_article_browse` VALUES (29, 4, 17, '2020-12-14 10:20:00');
INSERT INTO `jiu_article_browse` VALUES (30, 4, 20, '2020-12-17 14:48:01');
INSERT INTO `jiu_article_browse` VALUES (31, 4, 21, '2020-12-20 20:11:13');
INSERT INTO `jiu_article_browse` VALUES (32, 4, 9, '2020-12-20 20:16:02');
INSERT INTO `jiu_article_browse` VALUES (33, 4, 4, '2020-12-20 20:33:17');
INSERT INTO `jiu_article_browse` VALUES (34, 4, 13, '2020-12-20 20:33:25');
INSERT INTO `jiu_article_browse` VALUES (35, 4, 24, '2020-12-20 20:52:41');
INSERT INTO `jiu_article_browse` VALUES (36, 4, 15, '2020-12-22 15:53:05');
INSERT INTO `jiu_article_browse` VALUES (37, 4, 1, '2020-12-22 15:55:09');
INSERT INTO `jiu_article_browse` VALUES (38, 4, 25, '2020-12-22 17:17:28');

-- ----------------------------
-- Table structure for jiu_article_collect
-- ----------------------------
DROP TABLE IF EXISTS `jiu_article_collect`;
CREATE TABLE `jiu_article_collect`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '收藏人id',
  `article_id` int(11) NULL DEFAULT NULL COMMENT '文章id',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jiu_article_collect
-- ----------------------------
INSERT INTO `jiu_article_collect` VALUES (1, 1, 4, '2020-12-03 16:30:10');

-- ----------------------------
-- Table structure for jiu_article_star
-- ----------------------------
DROP TABLE IF EXISTS `jiu_article_star`;
CREATE TABLE `jiu_article_star`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '点赞者id',
  `article_id` int(11) NULL DEFAULT NULL COMMENT '文章id',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '点赞时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jiu_article_star
-- ----------------------------
INSERT INTO `jiu_article_star` VALUES (23, 3, 4, '2020-12-03 17:36:54');
INSERT INTO `jiu_article_star` VALUES (24, 3, 10, '2020-12-03 17:50:09');
INSERT INTO `jiu_article_star` VALUES (25, 3, 9, '2020-12-03 17:50:11');
INSERT INTO `jiu_article_star` VALUES (26, 3, 8, '2020-12-03 17:50:12');
INSERT INTO `jiu_article_star` VALUES (28, 3, 6, '2020-12-03 17:50:14');
INSERT INTO `jiu_article_star` VALUES (30, 3, 1, '2020-12-03 17:50:17');
INSERT INTO `jiu_article_star` VALUES (31, 3, 7, '2020-12-03 20:03:58');
INSERT INTO `jiu_article_star` VALUES (36, 1, 11, '2020-12-07 20:50:15');
INSERT INTO `jiu_article_star` VALUES (37, 2, 4, '2020-12-07 20:50:40');
INSERT INTO `jiu_article_star` VALUES (38, 3, 12, '2020-12-09 12:21:13');
INSERT INTO `jiu_article_star` VALUES (55, 1, 14, '2020-12-16 15:58:13');
INSERT INTO `jiu_article_star` VALUES (56, 1, 16, '2020-12-16 15:58:16');
INSERT INTO `jiu_article_star` VALUES (58, 4, 21, '2020-12-20 20:11:18');
INSERT INTO `jiu_article_star` VALUES (59, 3, 24, '2020-12-22 00:07:34');
INSERT INTO `jiu_article_star` VALUES (60, 3, 23, '2020-12-22 00:07:35');
INSERT INTO `jiu_article_star` VALUES (61, 3, 22, '2020-12-22 00:07:36');
INSERT INTO `jiu_article_star` VALUES (62, 3, 21, '2020-12-22 00:07:37');
INSERT INTO `jiu_article_star` VALUES (63, 3, 13, '2020-12-22 00:07:38');

-- ----------------------------
-- Table structure for jiu_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `jiu_article_tag`;
CREATE TABLE `jiu_article_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `state` tinyint(2) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of jiu_article_tag
-- ----------------------------
INSERT INTO `jiu_article_tag` VALUES (1, '原创', 1);
INSERT INTO `jiu_article_tag` VALUES (2, '前端', 1);
INSERT INTO `jiu_article_tag` VALUES (3, '后端', 1);
INSERT INTO `jiu_article_tag` VALUES (4, 'Java', 1);
INSERT INTO `jiu_article_tag` VALUES (5, 'JavaScript', 1);
INSERT INTO `jiu_article_tag` VALUES (6, 'C', 1);
INSERT INTO `jiu_article_tag` VALUES (7, 'C#', 1);
INSERT INTO `jiu_article_tag` VALUES (8, 'C++', 1);
INSERT INTO `jiu_article_tag` VALUES (9, 'Python', 1);
INSERT INTO `jiu_article_tag` VALUES (10, 'PHP', 1);
INSERT INTO `jiu_article_tag` VALUES (11, 'Vue.js', 1);
INSERT INTO `jiu_article_tag` VALUES (12, 'SpringBoot', 1);
INSERT INTO `jiu_article_tag` VALUES (13, 'Phalapi', 1);
INSERT INTO `jiu_article_tag` VALUES (14, 'React.js', 1);
INSERT INTO `jiu_article_tag` VALUES (15, 'Node.js', 1);
INSERT INTO `jiu_article_tag` VALUES (16, 'Angular.js', 1);
INSERT INTO `jiu_article_tag` VALUES (17, 'Android', 1);
INSERT INTO `jiu_article_tag` VALUES (18, 'Django', 1);
INSERT INTO `jiu_article_tag` VALUES (19, 'API', 1);
INSERT INTO `jiu_article_tag` VALUES (20, 'ECMAScript6', 1);
INSERT INTO `jiu_article_tag` VALUES (21, 'Facebook', 1);
INSERT INTO `jiu_article_tag` VALUES (22, 'Ruby', 1);
INSERT INTO `jiu_article_tag` VALUES (23, 'MongoDB', 1);
INSERT INTO `jiu_article_tag` VALUES (24, 'GitHub', 1);
INSERT INTO `jiu_article_tag` VALUES (25, 'Sublime Text', 1);
INSERT INTO `jiu_article_tag` VALUES (26, 'Webkit', 1);
INSERT INTO `jiu_article_tag` VALUES (27, 'Ubuntu', 1);
INSERT INTO `jiu_article_tag` VALUES (28, 'Objective-C', 1);
INSERT INTO `jiu_article_tag` VALUES (29, 'Debug', 1);
INSERT INTO `jiu_article_tag` VALUES (30, 'Chorme', 1);
INSERT INTO `jiu_article_tag` VALUES (31, 'Microsoft', 1);
INSERT INTO `jiu_article_tag` VALUES (32, 'CSS', 1);
INSERT INTO `jiu_article_tag` VALUES (33, 'Docker', 1);
INSERT INTO `jiu_article_tag` VALUES (34, 'DOM', 1);
INSERT INTO `jiu_article_tag` VALUES (35, 'Redis', 1);
INSERT INTO `jiu_article_tag` VALUES (36, 'Firefox', 1);
INSERT INTO `jiu_article_tag` VALUES (37, 'Golang', 1);
INSERT INTO `jiu_article_tag` VALUES (38, 'Git', 1);
INSERT INTO `jiu_article_tag` VALUES (39, 'HTML', 1);
INSERT INTO `jiu_article_tag` VALUES (40, 'HTTP', 1);
INSERT INTO `jiu_article_tag` VALUES (41, 'Linux', 1);
INSERT INTO `jiu_article_tag` VALUES (42, 'JQuery', 1);
INSERT INTO `jiu_article_tag` VALUES (43, 'Webpack', 1);
INSERT INTO `jiu_article_tag` VALUES (44, 'Markdown', 1);
INSERT INTO `jiu_article_tag` VALUES (45, 'MySQL', 1);
INSERT INTO `jiu_article_tag` VALUES (46, 'WebGL', 1);
INSERT INTO `jiu_article_tag` VALUES (47, 'MVVM', 1);
INSERT INTO `jiu_article_tag` VALUES (48, 'MVC', 1);
INSERT INTO `jiu_article_tag` VALUES (49, 'npm', 1);
INSERT INTO `jiu_article_tag` VALUES (50, 'Maven', 1);
INSERT INTO `jiu_article_tag` VALUES (51, 'Promise', 1);
INSERT INTO `jiu_article_tag` VALUES (52, 'SQL', 1);
INSERT INTO `jiu_article_tag` VALUES (53, 'NoSQL', 1);
INSERT INTO `jiu_article_tag` VALUES (54, 'Nginx', 1);
INSERT INTO `jiu_article_tag` VALUES (55, 'Express', 0);
INSERT INTO `jiu_article_tag` VALUES (56, 'SSM', 1);
INSERT INTO `jiu_article_tag` VALUES (57, 'IDEA', 1);
INSERT INTO `jiu_article_tag` VALUES (58, 'WebStorm', 1);
INSERT INTO `jiu_article_tag` VALUES (59, 'PhpStorm', 0);
INSERT INTO `jiu_article_tag` VALUES (60, 'nodejs', 0);
INSERT INTO `jiu_article_tag` VALUES (61, 'Spring', 1);
INSERT INTO `jiu_article_tag` VALUES (62, 'Spring Boot', 1);
INSERT INTO `jiu_article_tag` VALUES (63, 'Windows', 1);
INSERT INTO `jiu_article_tag` VALUES (64, 'ios', 1);
INSERT INTO `jiu_article_tag` VALUES (65, 'DevTools', 1);
INSERT INTO `jiu_article_tag` VALUES (66, 'sdk', 0);
INSERT INTO `jiu_article_tag` VALUES (67, 'moz', 1);
INSERT INTO `jiu_article_tag` VALUES (68, 'SSH', 1);
INSERT INTO `jiu_article_tag` VALUES (69, 'io', 1);

-- ----------------------------
-- Table structure for jiu_article_type
-- ----------------------------
DROP TABLE IF EXISTS `jiu_article_type`;
CREATE TABLE `jiu_article_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '意义',
  `state` tinyint(2) NULL DEFAULT 1 COMMENT '是否使用中',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jiu_article_type
-- ----------------------------
INSERT INTO `jiu_article_type` VALUES (1, '提问', 1);
INSERT INTO `jiu_article_type` VALUES (2, '分享', 1);
INSERT INTO `jiu_article_type` VALUES (3, '笔记', 1);
INSERT INTO `jiu_article_type` VALUES (4, '讨论', 1);
INSERT INTO `jiu_article_type` VALUES (5, '测试', 1);
INSERT INTO `jiu_article_type` VALUES (6, '公告', 0);
INSERT INTO `jiu_article_type` VALUES (7, '建议', 0);
INSERT INTO `jiu_article_type` VALUES (8, '新闻', 0);

-- ----------------------------
-- Table structure for jiu_friendship
-- ----------------------------
DROP TABLE IF EXISTS `jiu_friendship`;
CREATE TABLE `jiu_friendship`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `title` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '链接标题',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jiu_friendship
-- ----------------------------
INSERT INTO `jiu_friendship` VALUES (1, 'https://www.jju.edu.cn/', '九江学院');
INSERT INTO `jiu_friendship` VALUES (2, 'https://xxxy.jju.edu.cn/', '计算机与大数据科学学院');
INSERT INTO `jiu_friendship` VALUES (3, 'https://github.com', 'GitHub');
INSERT INTO `jiu_friendship` VALUES (4, 'https://www.bilibili.com/', '哔哩哔哩');
INSERT INTO `jiu_friendship` VALUES (6, 'https://spring.io/projects/spring-boot', 'Spring boot官网');

-- ----------------------------
-- Table structure for jiu_msg_notice
-- ----------------------------
DROP TABLE IF EXISTS `jiu_msg_notice`;
CREATE TABLE `jiu_msg_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action` tinyint(2) NULL DEFAULT NULL COMMENT '1:关注；2:取关',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `target_id` int(11) NULL DEFAULT NULL COMMENT '接收者id',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '消息时间',
  `is_review` tinyint(2) NULL DEFAULT 0 COMMENT '是否查看',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jiu_msg_notice
-- ----------------------------
INSERT INTO `jiu_msg_notice` VALUES (11, 1, 1, 2, '2020-12-22 00:04:07', 0);
INSERT INTO `jiu_msg_notice` VALUES (12, 2, 1, 2, '2020-12-22 00:04:43', 0);
INSERT INTO `jiu_msg_notice` VALUES (15, 1, 4, 1, '2020-12-22 11:27:43', 0);
INSERT INTO `jiu_msg_notice` VALUES (16, 1, 1, 4, '2020-12-22 14:48:56', 0);

-- ----------------------------
-- Table structure for jiu_msg_remark
-- ----------------------------
DROP TABLE IF EXISTS `jiu_msg_remark`;
CREATE TABLE `jiu_msg_remark`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action` tinyint(2) NULL DEFAULT NULL COMMENT '消息类型，1:评论 2:回复',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '评论用户id',
  `target_id` int(11) NULL DEFAULT NULL COMMENT '接收用户id',
  `article_id` int(11) NULL DEFAULT NULL COMMENT '文章id',
  `remark_id` int(11) NULL DEFAULT NULL COMMENT '评论id',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `is_review` tinyint(2) NULL DEFAULT 0 COMMENT '是否查看',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jiu_msg_remark
-- ----------------------------
INSERT INTO `jiu_msg_remark` VALUES (69, 1, 3, 1, 17, 55, '2020-12-10 20:34:27', 0);
INSERT INTO `jiu_msg_remark` VALUES (70, 1, 3, 1, 17, 56, '2020-12-10 20:39:41', 0);
INSERT INTO `jiu_msg_remark` VALUES (71, 1, 3, 1, 17, 57, '2020-12-10 20:42:24', 1);
INSERT INTO `jiu_msg_remark` VALUES (72, 1, 4, 1, 4, 58, '2020-12-22 00:31:03', 0);
INSERT INTO `jiu_msg_remark` VALUES (73, 1, 4, 1, 4, 59, '2020-12-22 00:31:50', 0);
INSERT INTO `jiu_msg_remark` VALUES (74, 1, 4, 1, 4, 60, '2020-12-22 00:34:03', 0);
INSERT INTO `jiu_msg_remark` VALUES (75, 1, 4, 1, 15, 61, '2020-12-22 15:53:08', 0);

-- ----------------------------
-- Table structure for jiu_msg_star
-- ----------------------------
DROP TABLE IF EXISTS `jiu_msg_star`;
CREATE TABLE `jiu_msg_star`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `target_id` int(11) NULL DEFAULT NULL COMMENT '接收者id',
  `article_id` int(11) NULL DEFAULT NULL COMMENT '文章id',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '消息时间',
  `is_review` tinyint(2) NULL DEFAULT 0 COMMENT '是否查看',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jiu_msg_star
-- ----------------------------
INSERT INTO `jiu_msg_star` VALUES (22, 1, 2, 11, '2020-12-07 20:50:15', 1);
INSERT INTO `jiu_msg_star` VALUES (23, 2, 1, 4, '2020-12-07 20:50:40', 1);
INSERT INTO `jiu_msg_star` VALUES (24, 3, 1, 12, '2020-12-09 12:21:13', 1);
INSERT INTO `jiu_msg_star` VALUES (25, 4, 1, 20, '2020-12-17 09:47:52', 0);
INSERT INTO `jiu_msg_star` VALUES (26, 3, 4, 24, '2020-12-22 00:07:34', 0);
INSERT INTO `jiu_msg_star` VALUES (27, 3, 4, 23, '2020-12-22 00:07:35', 0);
INSERT INTO `jiu_msg_star` VALUES (28, 3, 4, 22, '2020-12-22 00:07:36', 0);
INSERT INTO `jiu_msg_star` VALUES (29, 3, 4, 21, '2020-12-22 00:07:37', 0);
INSERT INTO `jiu_msg_star` VALUES (30, 3, 4, 13, '2020-12-22 00:07:38', 1);

-- ----------------------------
-- Table structure for jiu_msg_sys
-- ----------------------------
DROP TABLE IF EXISTS `jiu_msg_sys`;
CREATE TABLE `jiu_msg_sys`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_review` tinyint(2) NULL DEFAULT 0 COMMENT '是否查看',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jiu_msg_sys
-- ----------------------------

-- ----------------------------
-- Table structure for jiu_notice
-- ----------------------------
DROP TABLE IF EXISTS `jiu_notice`;
CREATE TABLE `jiu_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_id` int(11) NULL DEFAULT NULL COMMENT '关注人id',
  `target_id` int(11) NULL DEFAULT NULL COMMENT '被关注人id',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '关注时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of jiu_notice
-- ----------------------------
INSERT INTO `jiu_notice` VALUES (6, 2, 1, '2020-12-07 00:39:20');
INSERT INTO `jiu_notice` VALUES (9, 3, 4, '2020-12-22 00:06:58');
INSERT INTO `jiu_notice` VALUES (10, 4, 1, '2020-12-22 11:27:43');
INSERT INTO `jiu_notice` VALUES (11, 1, 4, '2020-12-22 14:48:56');

-- ----------------------------
-- Table structure for jiu_remark_child
-- ----------------------------
DROP TABLE IF EXISTS `jiu_remark_child`;
CREATE TABLE `jiu_remark_child`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父评论id',
  `comment_id` int(11) NULL DEFAULT NULL COMMENT 'root评论id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '评论者id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '回复内容',
  `target_id` int(11) NULL DEFAULT NULL COMMENT '回复用户id',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of jiu_remark_child
-- ----------------------------
INSERT INTO `jiu_remark_child` VALUES (3, NULL, 1, 1, 'hi', 2, '2020-12-02 15:29:07');
INSERT INTO `jiu_remark_child` VALUES (14, 3, 1, 2, '11', 1, '2020-12-02 17:15:07');
INSERT INTO `jiu_remark_child` VALUES (16, NULL, 3, 2, '111', 1, '2020-12-03 16:19:00');
INSERT INTO `jiu_remark_child` VALUES (17, NULL, 3, 2, '22', 1, '2020-12-03 16:25:17');
INSERT INTO `jiu_remark_child` VALUES (18, NULL, 52, 1, '3213', 2, '2020-12-07 00:37:27');
INSERT INTO `jiu_remark_child` VALUES (19, 18, 52, 2, '33313123', 1, '2020-12-07 00:37:42');
INSERT INTO `jiu_remark_child` VALUES (20, 19, 52, 1, '666', 2, '2020-12-07 00:38:03');
INSERT INTO `jiu_remark_child` VALUES (21, NULL, 53, 2, '1', 1, '2020-12-07 11:18:04');
INSERT INTO `jiu_remark_child` VALUES (22, 21, 53, 1, '2', 2, '2020-12-07 11:18:39');
INSERT INTO `jiu_remark_child` VALUES (23, NULL, 61, 4, '123', 4, '2020-12-22 15:54:02');
INSERT INTO `jiu_remark_child` VALUES (24, 23, 61, 4, '123456', 4, '2020-12-22 15:54:12');
INSERT INTO `jiu_remark_child` VALUES (25, NULL, 73, 4, '***', 4, '2020-12-22 17:10:40');

-- ----------------------------
-- Table structure for jiu_remark_root
-- ----------------------------
DROP TABLE IF EXISTS `jiu_remark_root`;
CREATE TABLE `jiu_remark_root`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NULL DEFAULT NULL COMMENT '文章Id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '评论用户id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of jiu_remark_root
-- ----------------------------
INSERT INTO `jiu_remark_root` VALUES (1, 10, 2, '喔', '2020-12-02 15:11:55');
INSERT INTO `jiu_remark_root` VALUES (2, 10, 1, '1', '2020-12-02 16:25:49');
INSERT INTO `jiu_remark_root` VALUES (3, 10, 1, '11', '2020-12-02 16:36:51');
INSERT INTO `jiu_remark_root` VALUES (4, 10, 2, '1', '2020-12-02 17:07:39');
INSERT INTO `jiu_remark_root` VALUES (5, 10, 2, '111', '2020-12-02 17:14:17');
INSERT INTO `jiu_remark_root` VALUES (6, 9, 2, '666', '2020-12-03 10:26:08');
INSERT INTO `jiu_remark_root` VALUES (7, 7, 2, '11', '2020-12-03 12:01:32');
INSERT INTO `jiu_remark_root` VALUES (8, 7, 2, '11', '2020-12-03 12:02:12');
INSERT INTO `jiu_remark_root` VALUES (9, 7, 2, '111', '2020-12-03 12:06:15');
INSERT INTO `jiu_remark_root` VALUES (10, 7, 2, '222222', '2020-12-03 12:06:30');
INSERT INTO `jiu_remark_root` VALUES (11, 8, 2, '1', '2020-12-03 12:15:03');
INSERT INTO `jiu_remark_root` VALUES (12, 8, 2, '2', '2020-12-03 12:15:05');
INSERT INTO `jiu_remark_root` VALUES (13, 8, 2, '3', '2020-12-03 12:15:06');
INSERT INTO `jiu_remark_root` VALUES (14, 1, 2, '1', '2020-12-03 12:15:11');
INSERT INTO `jiu_remark_root` VALUES (15, 1, 2, '1', '2020-12-03 12:15:12');
INSERT INTO `jiu_remark_root` VALUES (16, 1, 2, '1', '2020-12-03 12:15:15');
INSERT INTO `jiu_remark_root` VALUES (17, 1, 2, '1', '2020-12-03 12:15:15');
INSERT INTO `jiu_remark_root` VALUES (18, 1, 2, '1', '2020-12-03 12:15:16');
INSERT INTO `jiu_remark_root` VALUES (19, 1, 2, '1\n', '2020-12-03 12:15:18');
INSERT INTO `jiu_remark_root` VALUES (20, 1, 2, '1', '2020-12-03 12:15:18');
INSERT INTO `jiu_remark_root` VALUES (21, 1, 2, '1', '2020-12-03 12:15:19');
INSERT INTO `jiu_remark_root` VALUES (22, 1, 2, '1', '2020-12-03 12:15:20');
INSERT INTO `jiu_remark_root` VALUES (23, 1, 2, '1', '2020-12-03 12:15:20');
INSERT INTO `jiu_remark_root` VALUES (24, 1, 2, '1', '2020-12-03 12:15:21');
INSERT INTO `jiu_remark_root` VALUES (25, 1, 2, '1', '2020-12-03 12:15:22');
INSERT INTO `jiu_remark_root` VALUES (26, 1, 2, '1', '2020-12-03 12:15:22');
INSERT INTO `jiu_remark_root` VALUES (27, 1, 2, '1\n1', '2020-12-03 12:15:24');
INSERT INTO `jiu_remark_root` VALUES (28, 1, 2, '1', '2020-12-03 12:15:25');
INSERT INTO `jiu_remark_root` VALUES (29, 1, 2, '1', '2020-12-03 12:15:25');
INSERT INTO `jiu_remark_root` VALUES (30, 1, 2, '1', '2020-12-03 12:15:26');
INSERT INTO `jiu_remark_root` VALUES (31, 1, 2, '1', '2020-12-03 12:15:27');
INSERT INTO `jiu_remark_root` VALUES (32, 1, 2, '1', '2020-12-03 12:15:27');
INSERT INTO `jiu_remark_root` VALUES (33, 1, 2, '1', '2020-12-03 12:15:28');
INSERT INTO `jiu_remark_root` VALUES (34, 4, 2, '12', '2020-12-03 12:25:48');
INSERT INTO `jiu_remark_root` VALUES (35, 4, 2, '32', '2020-12-03 12:25:49');
INSERT INTO `jiu_remark_root` VALUES (36, 4, 2, '3213', '2020-12-03 12:25:50');
INSERT INTO `jiu_remark_root` VALUES (37, 4, 2, '321312', '2020-12-03 12:25:51');
INSERT INTO `jiu_remark_root` VALUES (38, 4, 2, '1321', '2020-12-03 12:26:56');
INSERT INTO `jiu_remark_root` VALUES (39, 4, 2, '3213', '2020-12-03 12:26:57');
INSERT INTO `jiu_remark_root` VALUES (40, 4, 2, '3123', '2020-12-03 12:26:58');
INSERT INTO `jiu_remark_root` VALUES (41, 4, 2, '321312', '2020-12-03 12:26:58');
INSERT INTO `jiu_remark_root` VALUES (42, 4, 2, '312312', '2020-12-03 12:26:59');
INSERT INTO `jiu_remark_root` VALUES (43, 4, 2, '123', '2020-12-03 12:34:45');
INSERT INTO `jiu_remark_root` VALUES (44, 4, 2, '31231', '2020-12-03 12:34:46');
INSERT INTO `jiu_remark_root` VALUES (45, 4, 2, '312312', '2020-12-03 12:34:47');
INSERT INTO `jiu_remark_root` VALUES (46, 4, 2, '312321', '2020-12-03 12:34:48');
INSERT INTO `jiu_remark_root` VALUES (47, 4, 2, '312312', '2020-12-03 12:34:49');
INSERT INTO `jiu_remark_root` VALUES (48, 4, 2, '321312', '2020-12-03 12:34:50');
INSERT INTO `jiu_remark_root` VALUES (49, 10, 2, '1', '2020-12-03 16:25:14');
INSERT INTO `jiu_remark_root` VALUES (50, 4, 2, '1', '2020-12-03 16:25:58');
INSERT INTO `jiu_remark_root` VALUES (51, 4, 2, '1', '2020-12-03 16:29:04');
INSERT INTO `jiu_remark_root` VALUES (52, 4, 2, '1', '2020-12-07 00:37:11');
INSERT INTO `jiu_remark_root` VALUES (53, 11, 1, '哈哈哈黑', '2020-12-07 11:09:26');
INSERT INTO `jiu_remark_root` VALUES (54, 12, 1, '1', '2020-12-10 19:00:57');
INSERT INTO `jiu_remark_root` VALUES (56, 17, 3, '222', '2020-12-10 20:39:41');
INSERT INTO `jiu_remark_root` VALUES (57, 17, 3, '2', '2020-12-10 20:42:24');
INSERT INTO `jiu_remark_root` VALUES (58, 4, 4, '12345678', '2020-12-22 00:31:03');
INSERT INTO `jiu_remark_root` VALUES (59, 4, 4, '1234567898765432', '2020-12-22 00:31:50');
INSERT INTO `jiu_remark_root` VALUES (60, 4, 4, '0000', '2020-12-22 00:34:03');
INSERT INTO `jiu_remark_root` VALUES (61, 15, 4, '123', '2020-12-22 15:53:08');
INSERT INTO `jiu_remark_root` VALUES (62, 24, 4, '1', '2020-12-22 16:59:13');
INSERT INTO `jiu_remark_root` VALUES (63, 24, 4, '***', '2020-12-22 16:59:24');
INSERT INTO `jiu_remark_root` VALUES (64, 24, 4, '***', '2020-12-22 17:00:20');
INSERT INTO `jiu_remark_root` VALUES (65, 24, 4, '***', '2020-12-22 17:01:06');
INSERT INTO `jiu_remark_root` VALUES (66, 24, 4, '测试', '2020-12-22 17:05:51');
INSERT INTO `jiu_remark_root` VALUES (67, 24, 4, '***', '2020-12-22 17:05:58');
INSERT INTO `jiu_remark_root` VALUES (68, 24, 4, 'oo', '2020-12-22 17:06:13');
INSERT INTO `jiu_remark_root` VALUES (69, 24, 4, '哈哈哈', '2020-12-22 17:09:28');
INSERT INTO `jiu_remark_root` VALUES (70, 24, 4, '草泥马', '2020-12-22 17:09:45');
INSERT INTO `jiu_remark_root` VALUES (71, 24, 4, '***', '2020-12-22 17:09:58');
INSERT INTO `jiu_remark_root` VALUES (72, 24, 4, '***', '2020-12-22 17:10:06');
INSERT INTO `jiu_remark_root` VALUES (73, 24, 4, '梁乙辉', '2020-12-22 17:10:22');

-- ----------------------------
-- Table structure for jiu_user
-- ----------------------------
DROP TABLE IF EXISTS `jiu_user`;
CREATE TABLE `jiu_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` tinyint(2) NULL DEFAULT 1 COMMENT '1用户 2管理员 3超级管理员',
  `email` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `nick` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '昵称',
  `integral` decimal(6, 0) NULL DEFAULT 0 COMMENT '用户积分',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像url',
  `gender` tinyint(2) NULL DEFAULT 0 COMMENT '性别；1/男  2/女 0/未知',
  `intro` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '个人介绍',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `create_at` date NULL DEFAULT NULL COMMENT '加入时间',
  `github_id` int(11) NULL DEFAULT NULL COMMENT 'gitub用户唯一标识',
  `github_nick` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'github昵称',
  `gitee_id` int(11) NULL DEFAULT NULL COMMENT 'gitee用户唯一标识',
  `gitee_nick` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'gitee昵称',
  `dingtalk_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '钉钉用户唯一标识别',
  `dingtalk_nick` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '钉钉昵称',
  `notice_count` int(11) NULL DEFAULT 0 COMMENT '关注量',
  `fans_count` int(11) NULL DEFAULT 0 COMMENT '粉丝量',
  `last_sign_date` date NULL DEFAULT NULL COMMENT '最后一次登录时间',
  `signed_days` int(11) NULL DEFAULT 0 COMMENT '登录天数',
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户登录凭证',
  `state` tinyint(2) NULL DEFAULT 1 COMMENT '账号状态 0封停  / 1正常使用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of jiu_user
-- ----------------------------
INSERT INTO `jiu_user` VALUES (1, 2, '111@qq.com', '202cb962ac59075b964b07152d234b70', '爱的荷尔蒙', 32, 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1194131577,2954769920&fm=26&gp=0.jpg', 2, '天行健君子以自强不息', '江西九江', '2019-12-01', NULL, NULL, NULL, NULL, NULL, NULL, 1, 2, '2020-12-22', 1, '1babebb497fb64953b2e245bc02a6beb', 1);
INSERT INTO `jiu_user` VALUES (2, 1, '222@qq.com', 'bcbe3365e6ac95ea2c0343a2395834dd', '落叶的位置', 2, 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1049415056,2671862138&fm=26&gp=0.jpg', 1, NULL, NULL, '2020-12-01', NULL, NULL, NULL, NULL, NULL, NULL, 1, 0, '2020-12-07', 1, NULL, 1);
INSERT INTO `jiu_user` VALUES (3, 1, '333@qq.com', '202cb962ac59075b964b07152d234b70', '黑子', 2, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606998218474&di=a157fb5066a5e3bbe11e9f2168d50ea8&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201603%2F06%2F20160306204517_i4Se8.jpeg', 0, NULL, NULL, '2020-12-13', NULL, NULL, NULL, NULL, NULL, NULL, 1, 0, '2020-12-22', 1, 'f6b774204844aa5e6551b12a72a26dfc', 1);
INSERT INTO `jiu_user` VALUES (4, 3, '1367559786@qq.com', '202cb962ac59075b964b07152d234b70', '灰太狼', 37, 'https://img.pmj136.top/avatar/default-avatar.jpg', 0, '我对岸再拜那，风雨漂泊的残陋再聚首', NULL, '2020-12-15', NULL, NULL, NULL, NULL, NULL, NULL, 1, 2, '2020-12-22', 3, '505ec6b469fa89fb0fb60f6e1f5729ac', 1);
INSERT INTO `jiu_user` VALUES (5, 1, '444@qq.com', '202cb962ac59075b964b07152d234b70', '嘿嘿', 0, '', 0, NULL, '', '2020-12-15', 0, '', 0, '', '', '', 0, 0, NULL, 0, NULL, 1);

SET FOREIGN_KEY_CHECKS = 1;
