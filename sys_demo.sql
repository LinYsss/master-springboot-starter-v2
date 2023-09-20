/*
 Navicat Premium Data Transfer

 Source Server         : waya
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : rm-wz917hb1agk7tq52qzo.mysql.rds.aliyuncs.com:3306
 Source Schema         : sys_demo

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 31/07/2023 11:49:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority_api
-- ----------------------------
DROP TABLE IF EXISTS `authority_api`;
CREATE TABLE `authority_api`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `api` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'api地址',
  `method` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'api请求方式',
  `quote` tinyint(4) NOT NULL DEFAULT 0 COMMENT '引用次数',
  `explain` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority_api
-- ----------------------------
INSERT INTO `authority_api` VALUES (1, '/authority/belong', 'GET', 1, '查询所有用户来源');
INSERT INTO `authority_api` VALUES (2, '/authority/belong', 'POST', 1, '新增用户来源');
INSERT INTO `authority_api` VALUES (3, '/authority/belong', 'DELETE', 1, '删除用户来源');
INSERT INTO `authority_api` VALUES (4, '/authority/user', 'GET', 1, '分页查询用户，该查询会根据当前登陆用户查询当前登陆用户可操作的用户');
INSERT INTO `authority_api` VALUES (5, '/authority/user', 'POST', 1, '新增用户，该api会保存与绑定当前用户与当前用户所属角色至创建的用户。');
INSERT INTO `authority_api` VALUES (6, '/authority/user/enabled', 'PATCH', 1, '修改用户可用状态，1表示可用，0表示禁用，该api仅允许传入0或1。');
INSERT INTO `authority_api` VALUES (7, '/authority/user/password', 'PATCH', 1, '重置用户密码，该api适用于管理员重置用户密码，该api不需要传入之前的密码，只需要指定用于与新密码即可。');
INSERT INTO `authority_api` VALUES (8, '/authority/user/byrid', 'GET', 1, '根据当前登陆用户的角色id查询当前角色下可管理的未被绑定角色的用户（有点绕哇？），该api不需要传入参数，系统会自动取登陆用户的缓存角色id。');
INSERT INTO `authority_api` VALUES (9, '/authority/module/api', 'POST', 1, '新增api接口');
INSERT INTO `authority_api` VALUES (10, '/authority/module/api', 'GET', 1, '查询所有api接口');
INSERT INTO `authority_api` VALUES (11, '/authority/module/api', 'PUT', 1, '修改api接口');
INSERT INTO `authority_api` VALUES (12, '/authority/module/api', 'DELETE', 1, '删除api接口');
INSERT INTO `authority_api` VALUES (13, '/authority/module', 'GET', 1, '查询所有模块（包括目录、菜单、页面、操作）');
INSERT INTO `authority_api` VALUES (14, '/authority/module', 'POST', 1, '新增模块，根据type值判断新增的类型，1表示目录，2表示菜单，3表示页面，4表示操作');
INSERT INTO `authority_api` VALUES (15, '/authority/module', 'PUT', 1, '修改模块');
INSERT INTO `authority_api` VALUES (16, '/authority/module', 'DELETE', 1, '删除模块');
INSERT INTO `authority_api` VALUES (17, '/authority/module/sequence', 'PATCH', 1, '修改菜单&页面的序号');
INSERT INTO `authority_api` VALUES (18, '/authority/module/byrid', 'GET', 1, '根据角色id查询所有模块，该api接口不需要传入参数，系统会根据当前登陆用户自动获取缓存角色id');
INSERT INTO `authority_api` VALUES (19, '/authority/role', 'POST', 1, '新增角色');
INSERT INTO `authority_api` VALUES (20, '/authority/role', 'DELETE', 1, '删除角色，删除角色会删除角色的子、子子、子子子、...角色，直到没有子角色为止，该接口是一个非常危险的操作接口，删除后是不可逆的');
INSERT INTO `authority_api` VALUES (21, '/authority/role', 'GET', 1, '分页查询角色列表，接口会根据当前登陆的用户判断，查询当前角色下可管理的下一级（不会查询多级）角色');
INSERT INTO `authority_api` VALUES (22, '/authority/role', 'PUT', 1, '修改角色');
INSERT INTO `authority_api` VALUES (23, '/authority/role/user', 'GET', 1, '查询角色下绑定的用户，系统根据当前请求的用户角色ID查询该角色下已经绑定的用户');
INSERT INTO `authority_api` VALUES (24, '/authority/role/user', 'POST', 1, '保存角色下的用户，保存角色下的用户，如果不是超管会验证传入的rid是否正确，以及添加的用户列表是否是当前用户可以操作的用户');
INSERT INTO `authority_api` VALUES (25, '/authority/role/module', 'GET', 1, '查询当前角色下绑定的权限，系统根据当前请求的用户角色ID查询该角色下已经绑定的权限');
INSERT INTO `authority_api` VALUES (26, '/authority/role/module', 'POST', 1, '保存角色下的权限，当前操作会验证是否是超管，如果不是超管会验证传入的rid是否正确，以及添加的权限列表是否是当前用户可以操作的权限');
INSERT INTO `authority_api` VALUES (27, '/system/log', 'GET', 1, '分页查询日志，当前版本为了节省空间没有使用缓存，当数据非常非常多的时候查询效率可能较慢');
INSERT INTO `authority_api` VALUES (29, '/authority/module/apiId', 'GET', 1, '通过id(clientId)查询api列表。');
INSERT INTO `authority_api` VALUES (30, '/authority/module/pageId', 'GET', 1, '通过id(clientId)查询page和 menus列表。');
INSERT INTO `authority_api` VALUES (31, '/authority/role/moduleV2', 'POST', 1, '保存角色下的权限v2。');
INSERT INTO `authority_api` VALUES (32, '/authority/module/tree', 'GET', 1, '树形结构模块加载');
INSERT INTO `authority_api` VALUES (33, '/authority/module/delete', 'DELETE', 1, '删除模块所用内容');
INSERT INTO `authority_api` VALUES (34, '/authority/user', 'PUT', 1, '修改用户信息');
INSERT INTO `authority_api` VALUES (35, '/plugin/qiniuimg', 'GET', 1, '分页查询图片列表（所有需要引用图片的模块都需要勾选此权限）');
INSERT INTO `authority_api` VALUES (36, '/plugin/qiniuimg/token', 'GET', 1, '获取图片上传token（引用模块中，如需上传图片，需要勾选此权限）');
INSERT INTO `authority_api` VALUES (37, '/plugin/qiniuimg', 'POST', 1, '新增图片（引用模块中，如需上传图片，需要勾选此权限）');
INSERT INTO `authority_api` VALUES (38, '/plugin/qiniuimg/v2', 'GET', 1, '分页查询图片和文件列表（所有需要引用图片的模块都需要勾选此权限）');
INSERT INTO `authority_api` VALUES (39, '/plugin/qiniuimg/v2', 'POST', 1, '新增图片和文件（引用模块中，如需上传图片，需要勾选此权限）');
INSERT INTO `authority_api` VALUES (40, '/plugin/qiniuimg/v2', 'PUT', 1, '修改图片名称');
INSERT INTO `authority_api` VALUES (41, '/plugin/qiniuimg/v2', 'DELETE', 1, '删除文件夹及文件夹里面的图片');
INSERT INTO `authority_api` VALUES (42, '/plugin/qiniuimg/v2/folder', 'GET', 1, '查询全部的文件夹');
INSERT INTO `authority_api` VALUES (43, '/plugin/qiniuimg', 'DELETE', 2, '删除图片');
INSERT INTO `authority_api` VALUES (44, '/plugin/qiniuimg/quote', 'GET', 2, '查询图片引用详情');
INSERT INTO `authority_api` VALUES (45, '/plugin/alivod/page', 'GET', 1, '分页查询视频列表');
INSERT INTO `authority_api` VALUES (46, '/plugin/alivod', 'POST', 1, '新增视频');
INSERT INTO `authority_api` VALUES (47, '/plugin/alivod', 'PUT', 1, '刷新视频token');
INSERT INTO `authority_api` VALUES (48, '/plugin/alivod', 'DELETE', 1, '删除视频');
INSERT INTO `authority_api` VALUES (49, '/plugin/alivod/url', 'PATCH', 1, '修改视频地址');
INSERT INTO `authority_api` VALUES (50, '/plugin/alivod/quote', 'GET', 1, '查询视频引用详情');
INSERT INTO `authority_api` VALUES (52, '/admin/log/page', 'GET', 1, '简单分页查询');
INSERT INTO `authority_api` VALUES (53, '/admin/log', 'POST', 1, '插入日志');
INSERT INTO `authority_api` VALUES (54, '/admin/log/export', 'GET', 1, '导出excel 表格');
INSERT INTO `authority_api` VALUES (55, '/admin/log', 'DELETE', 1, '删除日志');

-- ----------------------------
-- Table structure for authority_log
-- ----------------------------
DROP TABLE IF EXISTS `authority_log`;
CREATE TABLE `authority_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '日志类型',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志标题',
  `service_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务ID',
  `remote_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求URI',
  `method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作方式',
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作提交的数据',
  `time` bigint(20) NULL DEFAULT NULL COMMENT '执行时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记',
  `exception` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '异常信息',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_log_create_by`(`create_by`) USING BTREE,
  INDEX `sys_log_request_uri`(`request_uri`) USING BTREE,
  INDEX `sys_log_type`(`type`) USING BTREE,
  INDEX `sys_log_create_date`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority_log
-- ----------------------------

-- ----------------------------
-- Table structure for authority_login_history
-- ----------------------------
DROP TABLE IF EXISTS `authority_login_history`;
CREATE TABLE `authority_login_history`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键(自增)',
  `userId` bigint(20) NOT NULL COMMENT '用户主键',
  `time` datetime NOT NULL COMMENT '时间',
  `ip` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'IP',
  `status` tinyint(4) NOT NULL COMMENT '状态，0-成功/1-账号密码不匹配/2-用户已被禁用/3-登陆次数过多,账号暂时锁定',
  `explain` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority_login_history
-- ----------------------------

-- ----------------------------
-- Table structure for authority_module
-- ----------------------------
DROP TABLE IF EXISTS `authority_module`;
CREATE TABLE `authority_module`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'icon',
  `type` tinyint(4) NOT NULL COMMENT '1-目录，2-菜单，3-页面，4-操作（对应api）',
  `aid` int(11) NULL DEFAULT NULL COMMENT 'api的id',
  `page` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '页面地址',
  `explain` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  `sequence` int(11) NULL DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `aid`(`aid`) USING BTREE,
  CONSTRAINT `authority_module_ibfk_1` FOREIGN KEY (`aid`) REFERENCES `authority_api` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 81 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority_module
-- ----------------------------
INSERT INTO `authority_module` VALUES (1, NULL, '权限管理', 'icon-quanxianguanli', 2, NULL, NULL, '权限管理菜单', 1);
INSERT INTO `authority_module` VALUES (2, 1, '用户来源管理', NULL, 3, NULL, '/authority/belong', '用户来源管理页面', 1);
INSERT INTO `authority_module` VALUES (3, 1, '用户管理', NULL, 3, NULL, '/authority/user', '用户管理页面', 2);
INSERT INTO `authority_module` VALUES (4, 1, '模块管理', NULL, 3, NULL, '/authority/module', '模块管理页面', 3);
INSERT INTO `authority_module` VALUES (5, 1, '角色管理', NULL, 3, NULL, '/authority/role', '角色管理页面', 4);
INSERT INTO `authority_module` VALUES (6, 2, '查询所有用户来源', NULL, 4, 1, NULL, '查询所有用户来源', NULL);
INSERT INTO `authority_module` VALUES (7, 2, '新增用户来源', NULL, 4, 2, NULL, '新增用户来源', NULL);
INSERT INTO `authority_module` VALUES (8, 2, '删除用户来源', NULL, 4, 3, NULL, '删除用户来源', NULL);
INSERT INTO `authority_module` VALUES (9, 3, '分页查询用户', NULL, 4, 4, NULL, '分页查询用户，该查询会根据当前登陆用户查询当前登陆用户可操作的用户', NULL);
INSERT INTO `authority_module` VALUES (10, 3, '新增用户', NULL, 4, 5, NULL, '新增用户，该api会保存与绑定当前用户与当前用户所属角色至创建的用户。', NULL);
INSERT INTO `authority_module` VALUES (11, 3, '修改用户可用状态', NULL, 4, 6, NULL, '修改用户可用状态，1表示可用，0表示禁用，该api仅允许传入0或1。', NULL);
INSERT INTO `authority_module` VALUES (12, 3, '重置用户密码', NULL, 4, 7, NULL, '重置用户密码，该api适用于管理员重置用户密码，该api不需要传入之前的密码，只需要指定用于与新密码即可。', NULL);
INSERT INTO `authority_module` VALUES (14, 4, '查询所有模块', NULL, 4, 13, NULL, '查询所有模块（包括目录、菜单、页面、操作）', NULL);
INSERT INTO `authority_module` VALUES (15, 4, '新增模块', NULL, 4, 14, NULL, '新增模块，根据type值判断新增的类型，1表示目录，2表示菜单，3表示页面，4表示操作', NULL);
INSERT INTO `authority_module` VALUES (16, 4, '修改模块', NULL, 4, 15, NULL, '修改模块', NULL);
INSERT INTO `authority_module` VALUES (17, 4, '删除模块', NULL, 4, 16, NULL, '删除模块', NULL);
INSERT INTO `authority_module` VALUES (18, 4, '查询所有api接口', NULL, 4, 10, NULL, '查询所有api接口', NULL);
INSERT INTO `authority_module` VALUES (19, 4, '新增api接口', NULL, 4, 9, NULL, '新增api接口', NULL);
INSERT INTO `authority_module` VALUES (20, 4, '修改api接口', NULL, 4, 11, NULL, '修改api接口', NULL);
INSERT INTO `authority_module` VALUES (21, 4, '删除api接口', NULL, 4, 12, NULL, '删除api接口', NULL);
INSERT INTO `authority_module` VALUES (22, 4, '修改模块的序号', NULL, 4, 17, NULL, '修改菜单&页面的序号', NULL);
INSERT INTO `authority_module` VALUES (23, 5, '新增角色', NULL, 4, 19, NULL, '新增角色', NULL);
INSERT INTO `authority_module` VALUES (24, 5, '修改角色', NULL, 4, 22, NULL, '修改角色', NULL);
INSERT INTO `authority_module` VALUES (25, 5, '分页查询角色', NULL, 4, 21, NULL, '分页查询角色列表，接口会根据当前登陆的用户判断，查询当前角色下可管理的下一级（不会查询多级）角色', NULL);
INSERT INTO `authority_module` VALUES (26, 5, '删除角色', NULL, 4, 20, NULL, '删除角色，删除角色会删除角色的子、子子、子子子、...角色，直到没有子角色为止，该接口是一个非常危险的操作接口，删除后是不可逆的', NULL);
INSERT INTO `authority_module` VALUES (27, 5, '查询角色下绑定的用户', NULL, 4, 23, NULL, '查询角色下绑定的用户，系统根据当前请求的用户角色ID查询该角色下已经绑定的用户', NULL);
INSERT INTO `authority_module` VALUES (28, 5, '查询角色下未绑定角色的用户', NULL, 4, 8, NULL, '根据当前登陆用户的角色id查询当前角色下可管理的未被绑定角色的用户（有点绕哇？），该api不需要传入参数，系统会自动取登陆用户的缓存角色id。', NULL);
INSERT INTO `authority_module` VALUES (29, 5, '保存角色下的用户', NULL, 4, 24, NULL, '保存角色下的用户，保存角色下的用户，如果不是超管会验证传入的rid是否正确，以及添加的用户列表是否是当前用户可以操作的用户', NULL);
INSERT INTO `authority_module` VALUES (30, 5, '查询角色下绑定的权限', NULL, 4, 25, NULL, '查询当前角色下绑定的权限，系统根据当前请求的用户角色ID查询该角色下已经绑定的权限', NULL);
INSERT INTO `authority_module` VALUES (31, 5, '查询角色下包含的模块', NULL, 4, 18, NULL, '根据角色id查询所有模块，该api接口不需要传入参数，系统会根据当前登陆用户自动获取缓存角色id', NULL);
INSERT INTO `authority_module` VALUES (32, 5, '保存角色下的权限', NULL, 4, 26, NULL, '保存角色下的权限，当前操作会验证是否是超管，如果不是超管会验证传入的rid是否正确，以及添加的权限列表是否是当前用户可以操作的权限', NULL);
INSERT INTO `authority_module` VALUES (33, NULL, '系统管理', 'icon-shezhi', 2, NULL, NULL, '系统管理', 2);
INSERT INTO `authority_module` VALUES (34, 33, '日志管理', NULL, 3, NULL, '/system/log', '日志管理页面', 1);
INSERT INTO `authority_module` VALUES (35, 34, '分页查询日志', NULL, 4, 27, NULL, '分页查询日志，当前版本为了节省空间没有使用缓存，当数据非常非常多的时候查询效率可能较慢', NULL);
INSERT INTO `authority_module` VALUES (38, 3, '查询所有用户来源', NULL, 4, 1, NULL, '查询所有用户来源', NULL);
INSERT INTO `authority_module` VALUES (39, 4, '查询api列表', NULL, 4, 29, NULL, '通过id(clientId)查询api列表', NULL);
INSERT INTO `authority_module` VALUES (40, 4, '查询page和 menus列表', NULL, 4, 30, NULL, '通过id(clientId)查询page和 menus列表', NULL);
INSERT INTO `authority_module` VALUES (41, 5, '保存角色下的权限v2', NULL, 4, 31, NULL, '保存角色下的权限v2', NULL);
INSERT INTO `authority_module` VALUES (42, 4, '树形结构模块加载', NULL, 4, 32, NULL, '树形结构模块加载', NULL);
INSERT INTO `authority_module` VALUES (43, 4, '删除模块所用内容', NULL, 4, 33, NULL, '删除模块所用内容', NULL);
INSERT INTO `authority_module` VALUES (44, 3, '修改用户信息', NULL, 4, 34, NULL, '修改用户信息', NULL);
INSERT INTO `authority_module` VALUES (45, 33, '图片管理', NULL, 3, NULL, '/plugin/qiniuimg', '七牛云图片管理', 2);
INSERT INTO `authority_module` VALUES (46, 0, '图片插件接口（供其它模块调用）', NULL, 1, NULL, NULL, '七牛云图片插件接口（供其它模块调用）', 3);
INSERT INTO `authority_module` VALUES (47, 46, '分页查询图片列表（所有需要引用图片的模块都需要勾选此权限）', NULL, 4, 35, NULL, '分页查询图片列表（所有需要引用图片的模块都需要勾选此权限）', NULL);
INSERT INTO `authority_module` VALUES (48, 46, '获取图片上传token（引用模块中，如需上传图片，需要勾选此权限）', NULL, 4, 36, NULL, '获取图片上传token（引用模块中，如需上传图片，需要勾选此权限）', NULL);
INSERT INTO `authority_module` VALUES (49, 46, '新增图片（引用模块中，如需上传图片，需要勾选此权限）', NULL, 4, 37, NULL, '新增图片（引用模块中，如需上传图片，需要勾选此权限）', NULL);
INSERT INTO `authority_module` VALUES (50, 46, '分页查询图片和文件列表（所有需要引用图片的模块都需要勾选此权限）', NULL, 4, 38, NULL, '分页查询图片和文件列表（所有需要引用图片的模块都需要勾选此权限）', NULL);
INSERT INTO `authority_module` VALUES (51, 46, '新增图片和文件（引用模块中，如需上传图片，需要勾选此权限）', NULL, 4, 39, NULL, '新增图片和文件（引用模块中，如需上传图片，需要勾选此权限）', NULL);
INSERT INTO `authority_module` VALUES (52, 46, '修改图片名称', NULL, 4, 40, NULL, '修改图片名称', NULL);
INSERT INTO `authority_module` VALUES (53, 46, '删除文件夹及文件夹里面的图片', NULL, 4, 41, NULL, '删除文件夹及文件夹里面的图片', NULL);
INSERT INTO `authority_module` VALUES (54, 46, '查询全部的文件夹', NULL, 4, 42, NULL, '查询全部的文件夹', NULL);
INSERT INTO `authority_module` VALUES (55, 45, '删除图片', NULL, 4, 43, NULL, '删除图片', NULL);
INSERT INTO `authority_module` VALUES (56, 45, '查询图片引用详情', NULL, 4, 44, NULL, '查询图片引用详情', NULL);
INSERT INTO `authority_module` VALUES (57, 33, '视频管理', NULL, 3, NULL, '/plugin/alivod', '阿里云视频管理', 3);
INSERT INTO `authority_module` VALUES (58, 57, '分页查询视频列表', NULL, 4, 45, NULL, '分页查询视频列表', NULL);
INSERT INTO `authority_module` VALUES (59, 57, '新增视频', NULL, 4, 46, NULL, '新增视频', NULL);
INSERT INTO `authority_module` VALUES (60, 57, '刷新视频token', NULL, 4, 47, NULL, '刷新视频token', NULL);
INSERT INTO `authority_module` VALUES (61, 57, '删除视频', NULL, 4, 48, NULL, '删除视频', NULL);
INSERT INTO `authority_module` VALUES (62, 57, '修改视频地址', NULL, 4, 49, NULL, '修改视频地址', NULL);
INSERT INTO `authority_module` VALUES (63, 57, '查询视频引用详情', NULL, 4, 50, NULL, '查询视频引用详情', NULL);
INSERT INTO `authority_module` VALUES (64, 33, '图库管理', NULL, 3, NULL, '/plugin/qiniuimgV2', '图库管理', 4);
INSERT INTO `authority_module` VALUES (74, 64, '删除图片', NULL, 4, 43, NULL, '删除图片', NULL);
INSERT INTO `authority_module` VALUES (75, 64, '查询图片引用详情', NULL, 4, 44, NULL, '查询图片引用详情', NULL);
INSERT INTO `authority_module` VALUES (76, 34, '删除日志', NULL, 4, 55, NULL, '删除日志', NULL);
INSERT INTO `authority_module` VALUES (77, 34, '插入日志', NULL, 4, 53, NULL, '插入日志', NULL);
INSERT INTO `authority_module` VALUES (78, 34, '简单分页查询', NULL, 4, 52, NULL, '简单分页查询', NULL);
INSERT INTO `authority_module` VALUES (79, 34, '导出excel 表格', NULL, 4, 54, NULL, '导出excel 表格', NULL);
INSERT INTO `authority_module` VALUES (80, 33, '代码生成', NULL, 3, NULL, '/gen/index', '代码生成', 5);

-- ----------------------------
-- Table structure for authority_role
-- ----------------------------
DROP TABLE IF EXISTS `authority_role`;
CREATE TABLE `authority_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `explain` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `clientId` bigint(20) NOT NULL,
  `addTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority_role
-- ----------------------------
INSERT INTO `authority_role` VALUES (1, NULL, '系统超管', '最最最顶级系统超管', 888888, '2022-06-23 14:05:48');

-- ----------------------------
-- Table structure for authority_role_module
-- ----------------------------
DROP TABLE IF EXISTS `authority_role_module`;
CREATE TABLE `authority_role_module`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL COMMENT '角色id',
  `mid` int(11) NOT NULL COMMENT '模块id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 513 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority_role_module
-- ----------------------------
INSERT INTO `authority_role_module` VALUES (445, 1, 1);
INSERT INTO `authority_role_module` VALUES (446, 1, 2);
INSERT INTO `authority_role_module` VALUES (447, 1, 8);
INSERT INTO `authority_role_module` VALUES (448, 1, 7);
INSERT INTO `authority_role_module` VALUES (449, 1, 6);
INSERT INTO `authority_role_module` VALUES (450, 1, 3);
INSERT INTO `authority_role_module` VALUES (451, 1, 44);
INSERT INTO `authority_role_module` VALUES (452, 1, 38);
INSERT INTO `authority_role_module` VALUES (453, 1, 12);
INSERT INTO `authority_role_module` VALUES (454, 1, 11);
INSERT INTO `authority_role_module` VALUES (455, 1, 10);
INSERT INTO `authority_role_module` VALUES (456, 1, 9);
INSERT INTO `authority_role_module` VALUES (457, 1, 4);
INSERT INTO `authority_role_module` VALUES (458, 1, 43);
INSERT INTO `authority_role_module` VALUES (459, 1, 42);
INSERT INTO `authority_role_module` VALUES (460, 1, 40);
INSERT INTO `authority_role_module` VALUES (461, 1, 39);
INSERT INTO `authority_role_module` VALUES (462, 1, 22);
INSERT INTO `authority_role_module` VALUES (463, 1, 21);
INSERT INTO `authority_role_module` VALUES (464, 1, 20);
INSERT INTO `authority_role_module` VALUES (465, 1, 19);
INSERT INTO `authority_role_module` VALUES (466, 1, 18);
INSERT INTO `authority_role_module` VALUES (467, 1, 17);
INSERT INTO `authority_role_module` VALUES (468, 1, 16);
INSERT INTO `authority_role_module` VALUES (469, 1, 15);
INSERT INTO `authority_role_module` VALUES (470, 1, 14);
INSERT INTO `authority_role_module` VALUES (471, 1, 5);
INSERT INTO `authority_role_module` VALUES (472, 1, 41);
INSERT INTO `authority_role_module` VALUES (473, 1, 32);
INSERT INTO `authority_role_module` VALUES (474, 1, 31);
INSERT INTO `authority_role_module` VALUES (475, 1, 30);
INSERT INTO `authority_role_module` VALUES (476, 1, 29);
INSERT INTO `authority_role_module` VALUES (477, 1, 28);
INSERT INTO `authority_role_module` VALUES (478, 1, 27);
INSERT INTO `authority_role_module` VALUES (479, 1, 26);
INSERT INTO `authority_role_module` VALUES (480, 1, 25);
INSERT INTO `authority_role_module` VALUES (481, 1, 24);
INSERT INTO `authority_role_module` VALUES (482, 1, 23);
INSERT INTO `authority_role_module` VALUES (483, 1, 33);
INSERT INTO `authority_role_module` VALUES (484, 1, 34);
INSERT INTO `authority_role_module` VALUES (485, 1, 79);
INSERT INTO `authority_role_module` VALUES (486, 1, 78);
INSERT INTO `authority_role_module` VALUES (487, 1, 77);
INSERT INTO `authority_role_module` VALUES (488, 1, 76);
INSERT INTO `authority_role_module` VALUES (489, 1, 35);
INSERT INTO `authority_role_module` VALUES (490, 1, 45);
INSERT INTO `authority_role_module` VALUES (491, 1, 56);
INSERT INTO `authority_role_module` VALUES (492, 1, 55);
INSERT INTO `authority_role_module` VALUES (493, 1, 57);
INSERT INTO `authority_role_module` VALUES (494, 1, 63);
INSERT INTO `authority_role_module` VALUES (495, 1, 62);
INSERT INTO `authority_role_module` VALUES (496, 1, 61);
INSERT INTO `authority_role_module` VALUES (497, 1, 60);
INSERT INTO `authority_role_module` VALUES (498, 1, 59);
INSERT INTO `authority_role_module` VALUES (499, 1, 58);
INSERT INTO `authority_role_module` VALUES (500, 1, 64);
INSERT INTO `authority_role_module` VALUES (501, 1, 75);
INSERT INTO `authority_role_module` VALUES (502, 1, 74);
INSERT INTO `authority_role_module` VALUES (503, 1, 80);
INSERT INTO `authority_role_module` VALUES (504, 1, 46);
INSERT INTO `authority_role_module` VALUES (505, 1, 54);
INSERT INTO `authority_role_module` VALUES (506, 1, 53);
INSERT INTO `authority_role_module` VALUES (507, 1, 52);
INSERT INTO `authority_role_module` VALUES (508, 1, 51);
INSERT INTO `authority_role_module` VALUES (509, 1, 50);
INSERT INTO `authority_role_module` VALUES (510, 1, 49);
INSERT INTO `authority_role_module` VALUES (511, 1, 48);
INSERT INTO `authority_role_module` VALUES (512, 1, 47);

-- ----------------------------
-- Table structure for authority_role_user
-- ----------------------------
DROP TABLE IF EXISTS `authority_role_user`;
CREATE TABLE `authority_role_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL COMMENT '角色id',
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority_role_user
-- ----------------------------
INSERT INTO `authority_role_user` VALUES (1, 1, 888888);

-- ----------------------------
-- Table structure for authority_user
-- ----------------------------
DROP TABLE IF EXISTS `authority_user`;
CREATE TABLE `authority_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `phone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码摘要',
  `salt` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码盐',
  `mix` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码混淆',
  `nickname` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `secret` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密钥，用于加解密/签名',
  `inviteCode` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邀请码',
  `inviteCodeFrom` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被邀请码',
  `organization` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织',
  `enabled` tinyint(4) NOT NULL COMMENT '是否启用，1-肯定，0-否定',
  `belong` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户是属于哪个域的？如后台管理用户、小程序用户、API用户等',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `createIp` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建IP',
  `loginTime` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `loginIp` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `addUid` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID',
  `addRid` int(11) NULL DEFAULT NULL COMMENT '创建人角色ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100000000 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority_user
-- ----------------------------
INSERT INTO `authority_user` VALUES (888888, 'supreme', NULL, NULL, '5577cbce0199c2ea4c945df4e62dc0573c834ab5b7e8153b262b03cae2deb13c', '1g5f9g4d4h3g2b5h4a1i2d2d0g2s5i6h5i2f4b0j4f1i9g4a1a4s7b9h6e1a3h9b', 'VXarXlVXdrXgVwIrXx', 'supreme', 'abb73eaea3f7d49d405806b36b3784ad', 'WNnU73nWp0IL', NULL, NULL, 1, 'back', '2022-06-23 14:05:47', NULL, '2023-07-31 10:52:00', '192.168.1.4', NULL, NULL);

-- ----------------------------
-- Table structure for authority_user_belong
-- ----------------------------
DROP TABLE IF EXISTS `authority_user_belong`;
CREATE TABLE `authority_user_belong`  (
  `belong` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `explain` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`belong`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority_user_belong
-- ----------------------------
INSERT INTO `authority_user_belong` VALUES ('back', '后台账号');

-- ----------------------------
-- Table structure for plugin_alivod
-- ----------------------------
DROP TABLE IF EXISTS `plugin_alivod`;
CREATE TABLE `plugin_alivod`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `videoId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '阿里云视频id',
  `cover` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频封面',
  `videoOriginalAddr` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '阿里云原视频地址',
  `videoTranscodeAddr` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '阿里云转码后视频地址',
  `size` bigint(20) NULL DEFAULT NULL COMMENT '视频大小，单位byte',
  `videoTime` int(11) NULL DEFAULT NULL COMMENT '视频时长，单位秒',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频名称',
  `startTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始上传时间，即创建时间',
  `endTime` datetime NULL DEFAULT NULL COMMENT '结束上传时间',
  `state` tinyint(4) NULL DEFAULT 1 COMMENT '上传状态，1-上传中、2-上传失败、3-转码中、4-转码失败、5-完成',
  `quote` tinyint(4) NULL DEFAULT 0 COMMENT '视频被引用的次数',
  `addUid` bigint(20) NULL DEFAULT NULL COMMENT '用户ID(该值可通过缓存bufAlivodUid指定)',
  `addRid` int(11) NULL DEFAULT NULL COMMENT '角色ID(该值可通过缓存bufAlivodRid指定)',
  `modifyTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of plugin_alivod
-- ----------------------------

-- ----------------------------
-- Table structure for plugin_alivod_quote
-- ----------------------------
DROP TABLE IF EXISTS `plugin_alivod_quote`;
CREATE TABLE `plugin_alivod_quote`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL COMMENT '视频表id',
  `alias` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '引用的别名（模块名）',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块详情名称（如果它为null，则关联查询name）',
  `table` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '引用的表名',
  `tid` int(11) NULL DEFAULT NULL COMMENT '引用表的id',
  `time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of plugin_alivod_quote
-- ----------------------------

-- ----------------------------
-- Table structure for plugin_qiniu_img
-- ----------------------------
DROP TABLE IF EXISTS `plugin_qiniu_img`;
CREATE TABLE `plugin_qiniu_img`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '七牛云key',
  `addr` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '七牛云图片地址',
  `size` int(11) NULL DEFAULT NULL COMMENT '图片大小，单位byte',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片名称',
  `time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始上传时间，即创建时间',
  `quote` tinyint(4) NULL DEFAULT 0 COMMENT '图片被引用的次数',
  `addUid` bigint(20) NULL DEFAULT NULL COMMENT '用户ID(该值可通过缓存bufAlivodUid指定)',
  `addRid` int(11) NULL DEFAULT NULL COMMENT '角色ID(该值可通过缓存bufAlivodRid指定)',
  `modifyTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `pid` int(11) NULL DEFAULT NULL COMMENT '文件夹Id',
  `type` tinyint(4) NULL DEFAULT 0 COMMENT '类型 0 ：图片， 1：文件夹',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `key`(`key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of plugin_qiniu_img
-- ----------------------------
INSERT INTO `plugin_qiniu_img` VALUES (1, NULL, NULL, NULL, '测试', '2023-07-28 16:08:52', 0, 888888, 1, '2023-07-28 16:08:52', NULL, 1);

-- ----------------------------
-- Table structure for plugin_qiniu_img_quote
-- ----------------------------
DROP TABLE IF EXISTS `plugin_qiniu_img_quote`;
CREATE TABLE `plugin_qiniu_img_quote`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL COMMENT '图片表id',
  `alias` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '引用的别名（模块名）',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块详情名称（如果它为null，则关联查询name）',
  `table` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '引用的表名',
  `tid` int(11) NULL DEFAULT NULL COMMENT '引用表的id',
  `time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of plugin_qiniu_img_quote
-- ----------------------------

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2023-07-31 11:38:46', '', NULL, '');
INSERT INTO `sys_job` VALUES (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2023-07-31 11:38:46', '', NULL, '');
INSERT INTO `sys_job` VALUES (3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2023-07-31 11:38:46', '', NULL, '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
