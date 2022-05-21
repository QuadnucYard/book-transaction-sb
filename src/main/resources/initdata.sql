USE bookoob;

SET FOREIGN_KEY_CHECKS=0;


-- ----------------------------
-- Table structure for admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `path` varchar(64) DEFAULT NULL,
                              `name` varchar(64) DEFAULT NULL,
                              `name_zh` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                              `icon_cls` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                              `component` varchar(64) DEFAULT NULL,
                              `parent_id` int(11) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `admin_menu` VALUES ('1', '/admin', 'AdminIndex', '首页', 'el-icon-s-home', 'AdminIndex', '0');
INSERT INTO `admin_menu` VALUES ('2', '/admin/dashboard', 'DashboardAdmin', '运行情况', null, 'dashboard/admin/index', '1');
INSERT INTO `admin_menu` VALUES ('3', '/admin', 'User', '用户管理', 'el-icon-user', 'AdminIndex', '0');
INSERT INTO `admin_menu` VALUES ('4', '/admin', 'Content', '内容管理', 'el-icon-tickets', 'AdminIndex', '0');
INSERT INTO `admin_menu` VALUES ('5', '/admin', 'System', '系统配置', 'el-icon-s-tools', 'AdminIndex', '0');
INSERT INTO `admin_menu` VALUES ('6', '/admin/user/profile', 'Profile', '用户信息', null, 'user/UserProfile', '3');
INSERT INTO `admin_menu` VALUES ('7', '/admin/user/role', 'Role', '角色配置', null, 'user/Role', '3');
INSERT INTO `admin_menu` VALUES ('8', '/admin/content/book', 'BookManagement', '图书管理', null, 'content/BookManagement', '4');
INSERT INTO `admin_menu` VALUES ('9', '/admin/content/banner', 'BannerManagement', '广告管理', null, 'content/BannerManagement', '4');
INSERT INTO `admin_menu` VALUES ('10', '/admin/content/article', 'ArticleManagement', '文章管理', null, 'content/ArticleManagement', '4');

-- ----------------------------
-- Table structure for admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_permission`;
CREATE TABLE `admin_permission` (
                                    `id` int(11) NOT NULL AUTO_INCREMENT,
                                    `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                    `desc_` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                    `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_permission
-- ----------------------------
INSERT INTO `admin_permission` VALUES ('1', 'users_management', '用户管理', '/api/admin/user');
INSERT INTO `admin_permission` VALUES ('2', 'roles_management', '角色管理', '/api/admin/role');
INSERT INTO `admin_permission` VALUES ('3', 'content_management', '内容管理', '/api/admin/content');

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                              `name_zh` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                              `enabled` tinyint(1) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('1', 'sysAdmin', '系统管理员', '1');
INSERT INTO `admin_role` VALUES ('2', 'contentManager', '内容管理员', '1');
INSERT INTO `admin_role` VALUES ('3', 'visitor', '访客', '1');
INSERT INTO `admin_role` VALUES ('9', 'test', '测试角色', '1');

-- ----------------------------
-- Table structure for admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_menu`;
CREATE TABLE `admin_role_menu` (
                                   `id` int(11) NOT NULL AUTO_INCREMENT,
                                   `rid` int(11) DEFAULT NULL,
                                   `mid` int(11) DEFAULT NULL,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of admin_role_menu
-- ----------------------------
INSERT INTO `admin_role_menu` VALUES ('19', '4', '1');
INSERT INTO `admin_role_menu` VALUES ('20', '4', '2');
INSERT INTO `admin_role_menu` VALUES ('21', '3', '1');
INSERT INTO `admin_role_menu` VALUES ('22', '3', '2');
INSERT INTO `admin_role_menu` VALUES ('23', '9', '1');
INSERT INTO `admin_role_menu` VALUES ('24', '9', '2');
INSERT INTO `admin_role_menu` VALUES ('121', '1', '1');
INSERT INTO `admin_role_menu` VALUES ('122', '1', '2');
INSERT INTO `admin_role_menu` VALUES ('123', '1', '3');
INSERT INTO `admin_role_menu` VALUES ('124', '1', '6');
INSERT INTO `admin_role_menu` VALUES ('125', '1', '7');
INSERT INTO `admin_role_menu` VALUES ('126', '1', '4');
INSERT INTO `admin_role_menu` VALUES ('127', '1', '8');
INSERT INTO `admin_role_menu` VALUES ('128', '1', '9');
INSERT INTO `admin_role_menu` VALUES ('129', '1', '10');
INSERT INTO `admin_role_menu` VALUES ('130', '1', '5');
INSERT INTO `admin_role_menu` VALUES ('188', '2', '1');
INSERT INTO `admin_role_menu` VALUES ('189', '2', '2');
INSERT INTO `admin_role_menu` VALUES ('190', '2', '4');
INSERT INTO `admin_role_menu` VALUES ('191', '2', '8');
INSERT INTO `admin_role_menu` VALUES ('192', '2', '9');
INSERT INTO `admin_role_menu` VALUES ('193', '2', '10');

-- ----------------------------
-- Table structure for admin_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_permission`;
CREATE TABLE `admin_role_permission` (
                                         `id` int(20) NOT NULL AUTO_INCREMENT,
                                         `rid` int(20) DEFAULT NULL,
                                         `pid` int(20) DEFAULT NULL,
                                         PRIMARY KEY (`id`),
                                         KEY `fk_role_permission_role_1` (`rid`),
                                         KEY `fk_role_permission_permission_1` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role_permission
-- ----------------------------
INSERT INTO `admin_role_permission` VALUES ('83', '5', '3');
INSERT INTO `admin_role_permission` VALUES ('108', '1', '1');
INSERT INTO `admin_role_permission` VALUES ('109', '1', '2');
INSERT INTO `admin_role_permission` VALUES ('110', '1', '3');
INSERT INTO `admin_role_permission` VALUES ('139', '2', '3');

-- ----------------------------
-- Table structure for admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role` (
                                   `id` int(11) NOT NULL AUTO_INCREMENT,
                                   `uid` int(11) DEFAULT NULL,
                                   `rid` int(11) DEFAULT NULL,
                                   PRIMARY KEY (`id`),
                                   KEY `fk_operator_role_operator_1` (`uid`),
                                   KEY `fk_operator_role_role_1` (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user_role
-- ----------------------------
INSERT INTO `admin_user_role` VALUES ('40', '24', '2');
INSERT INTO `admin_user_role` VALUES ('63', '3', '2');
INSERT INTO `admin_user_role` VALUES ('64', '1', '1');
INSERT INTO `admin_user_role` VALUES ('67', '2', '3');
