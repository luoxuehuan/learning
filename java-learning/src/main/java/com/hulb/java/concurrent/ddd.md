/*
 Navicat Premium Data Transfer

 Source Server         : aliyun-megrez
 Source Server Type    : MySQL
 Source Server Version : 50620
 Source Host           : 118.31.238.2:3306
 Source Schema         : pro_megrez

 Target Server Type    : MySQL
 Target Server Version : 50620
 File Encoding         : 65001

 Date: 10/07/2018 10:39:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bas_apply
-- ----------------------------
DROP TABLE IF EXISTS `bas_apply`;
CREATE TABLE `bas_apply` (
  `apply_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '申请ID',
  `apply_name` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '申请单名称',
  `schema_id` int(11) NOT NULL,
  `apply_type` int(11) NOT NULL DEFAULT '0' COMMENT '申请单类型 0：主动申请 1：系统申请 2 管理员赋予 ',
  `apply_reason` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '申请理由',
  `user_id` int(11) NOT NULL COMMENT '申请用户ID',
  `tenant_id` int(11) NOT NULL COMMENT '申请租户ID',
  `apply_user_id` int(11) NOT NULL,
  `expire_time` datetime DEFAULT NULL COMMENT '????',
  `apply_duration` int(11) NOT NULL COMMENT '申请时长（天）',
  `review_user_id` int(11) DEFAULT NULL COMMENT '审批人',
  `review_status` tinyint(4) NOT NULL COMMENT '审批状态:1审批通过、2拒绝、0待审批、3撤销',
  `review_comment` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '审批意见',
  `review_time` datetime DEFAULT NULL,
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `invalid` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'N' COMMENT '无效的？ N:有效 Y：无效',
  PRIMARY KEY (`apply_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='权限申请表';

-- ----------------------------
-- Table structure for bas_authorized_resource
-- ----------------------------
DROP TABLE IF EXISTS `bas_authorized_resource`;
CREATE TABLE `bas_authorized_resource` (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源ID',
  `expire_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  `apply_detail_id` int(11) DEFAULT NULL COMMENT '申请详情ID',
  `tenant_id` int(11) NOT NULL COMMENT '被授权租户ID',
  `user_id` int(11) NOT NULL COMMENT '被授权用户ID',
  `resource_type` tinyint(4) DEFAULT '0' COMMENT '资源类型精确级别？0：库 1：表 2：字段 3：函数',
  `review_status` tinyint(4) NOT NULL,
  `schema_id` int(11) NOT NULL COMMENT '库ID',
  `entity_id` int(11) DEFAULT NULL COMMENT '实体ID',
  `function_id` int(11) DEFAULT NULL COMMENT '函数ID',
  `function_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `function_duty_user_id` int(11) DEFAULT NULL,
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `invalid` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'N' COMMENT '无效的？ N:有效 Y：无效',
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='授权资源及操作表';

-- ----------------------------
-- Table structure for bas_cata
-- ----------------------------
DROP TABLE IF EXISTS `bas_cata`;
CREATE TABLE `bas_cata` (
  `cata_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增类目id',
  `tenant_id` int(11) NOT NULL DEFAULT '0' COMMENT '租户ID',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `parent_cata_id` bigint(19) unsigned NOT NULL DEFAULT '0' COMMENT '父目录ID，根目录时取0值',
  `cata_name` varchar(100) NOT NULL DEFAULT '' COMMENT '类目名称',
  `cata_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '类目类型 1: 数据类目 2: 标签类目 3:术语项类目',
  `cata_level` tinyint(4) NOT NULL DEFAULT '0' COMMENT '类目等级: 1、2、3',
  `create_user_id` int(10) NOT NULL DEFAULT '0' COMMENT '创建人ID',
  `modify_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '操作用户',
  `invalid` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0:有效1:无效',
  PRIMARY KEY (`cata_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='类目信息表';

-- ----------------------------
-- Table structure for bas_dict
-- ----------------------------
DROP TABLE IF EXISTS `bas_dict`;
CREATE TABLE `bas_dict` (
  `dict_id` int(10) NOT NULL AUTO_INCREMENT,
  `ctime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `dict_type` tinyint(1) NOT NULL COMMENT '1: 生命周期配置 2: 其他(暂定)',
  `item_name` varchar(32) NOT NULL COMMENT '对应字典类型的值',
  `item_value` smallint(5) NOT NULL COMMENT '对应字典的描述值',
  `item_order` smallint(5) DEFAULT '0' COMMENT '排序id用于前端展示',
  `invalid` char(1) DEFAULT 'N' COMMENT '是否有效 Y:无效 N:有效',
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='字典值,用于前端显示';

-- ----------------------------
-- Table structure for bas_entity
-- ----------------------------
DROP TABLE IF EXISTS `bas_entity`;
CREATE TABLE `bas_entity` (
  `entity_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '实体ID, 自增',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `entity_name` varchar(512) NOT NULL COMMENT '实体名',
  `schema_id` int(10) NOT NULL COMMENT '库ID',
  `tenant_id` int(10) NOT NULL DEFAULT '0' COMMENT '租户id',
  `outer_entity_id` int(10) NOT NULL COMMENT '对应hive-meta库中TBLS表中的TBL_ID',
  `c_user_id` int(10) DEFAULT NULL COMMENT '创建人',
  `m_user_id` int(10) DEFAULT NULL COMMENT '修改人',
  `invalid` char(1) NOT NULL DEFAULT 'N' COMMENT 'Y: 删除 N: 未删除',
  `duty_user_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`entity_id`),
  KEY `fk_bas_entity_schema_id` (`schema_id`),
  CONSTRAINT `fk_bas_entity_schema_id` FOREIGN KEY (`schema_id`) REFERENCES `bas_schema` (`schema_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='实体基本信息';

-- ----------------------------
-- Table structure for bas_entity_field
-- ----------------------------
DROP TABLE IF EXISTS `bas_entity_field`;
CREATE TABLE `bas_entity_field` (
  `field_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字段ID',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `entity_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '实体ID',
  `outer_field_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '对应hive-meta库中PARTITIONS表中的PART_ID',
  `field_name` varchar(256) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '字段名称',
  `field_type` varchar(256) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '字段类型',
  `field_comment` varchar(256) COLLATE utf8_bin DEFAULT '' COMMENT '字段备注',
  `is_partition_field` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否是分区字段, 0: 普通字段 1:分区字段',
  `invalid` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'N' COMMENT '是否有效Y:无效N:有效',
  PRIMARY KEY (`field_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='字段信息';

-- ----------------------------
-- Table structure for bas_entity_lineage
-- ----------------------------
DROP TABLE IF EXISTS `bas_entity_lineage`;
CREATE TABLE `bas_entity_lineage` (
  `lineage_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '血缘ID',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `entity_id` int(10) NOT NULL COMMENT '实体ID',
  `parent_entity_id` int(10) NOT NULL COMMENT '父实体ID',
  `task_id` int(10) NOT NULL DEFAULT '0' COMMENT '任务id',
  `task_instance` varchar(64) NOT NULL DEFAULT '' COMMENT '任务实例',
  `invalid` char(1) NOT NULL DEFAULT 'N' COMMENT '是否有效 Y:无效 N:有效',
  PRIMARY KEY (`lineage_id`),
  KEY `fk_rel_entity_lineage_entity_id` (`entity_id`),
  CONSTRAINT `fk_rel_entity_lineage_entity_id` FOREIGN KEY (`entity_id`) REFERENCES `bas_entity` (`entity_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='血缘';

-- ----------------------------
-- Table structure for bas_entity_param
-- ----------------------------
DROP TABLE IF EXISTS `bas_entity_param`;
CREATE TABLE `bas_entity_param` (
  `param_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `entity_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '实体ID',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `param_key` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '参数key 例如 col.ds.max 代表ds字段最大值',
  `param_value` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '某个key对应value 例如 2017',
  `record_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '记录时间',
  `invalid` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'N' COMMENT 'Y:无效N:有效',
  PRIMARY KEY (`param_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='entity对应信息k-v存储';

-- ----------------------------
-- Table structure for bas_entity_partition
-- ----------------------------
DROP TABLE IF EXISTS `bas_entity_partition`;
CREATE TABLE `bas_entity_partition` (
  `partition_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '分区ID',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `entity_id` int(10) NOT NULL COMMENT '实体ID',
  `outer_partition_id` int(10) NOT NULL COMMENT '对应hive-meta库中PARTITIONS表中的PART_ID',
  `partition_name` varchar(256) NOT NULL COMMENT '分区名',
  `partition_ctime` datetime DEFAULT NULL COMMENT '分区创建时间',
  `partition_mtime` datetime DEFAULT NULL COMMENT '分区修改时间',
  `partition_size` bigint(19) DEFAULT NULL COMMENT '分区存储大小',
  `num_rows` bigint(19) DEFAULT NULL COMMENT '分区记录数',
  `start_time` datetime DEFAULT NULL COMMENT '分区开始计算时间',
  `end_time` datetime DEFAULT NULL COMMENT '分区计算结束时间',
  `invalid` char(1) NOT NULL DEFAULT 'N' COMMENT '是否有效 Y:无效 N:有效',
  PRIMARY KEY (`partition_id`),
  KEY `fk_rel_entity_partition_entity_id` (`entity_id`),
  CONSTRAINT `fk_rel_entity_partition_entity_id` FOREIGN KEY (`entity_id`) REFERENCES `bas_entity` (`entity_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='分区信息';

-- ----------------------------
-- Table structure for bas_monitor_alert
-- ----------------------------
DROP TABLE IF EXISTS `bas_monitor_alert`;
CREATE TABLE `bas_monitor_alert` (
  `alert_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '监控规则告警表',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `rule_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '监控规则id',
  `alert_content` varchar(200) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '告警内容',
  `c_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `m_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `invalid` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'N' COMMENT 'Y:无效N:有效',
  PRIMARY KEY (`alert_id`),
  KEY `rule_id` (`rule_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='告警配置表';

-- ----------------------------
-- Table structure for bas_opt_rule
-- ----------------------------
DROP TABLE IF EXISTS `bas_opt_rule`;
CREATE TABLE `bas_opt_rule` (
  `opt_rule_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '操作规则ID',
  `opt_rule_value` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'UPDATE INSERT SELECT',
  `opt_rule_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `opt_rule_type` tinyint(4) NOT NULL COMMENT '0:库操作、1.表操作 、2 字段操作 、3 函数操作',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `invalid` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'N' COMMENT '无效的？ N:有效 Y：无效',
  PRIMARY KEY (`opt_rule_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='操作类型维度表';

-- ----------------------------
-- Table structure for bas_schema
-- ----------------------------
DROP TABLE IF EXISTS `bas_schema`;
CREATE TABLE `bas_schema` (
  `schema_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '库ID',
  `workspace_id` int(10) DEFAULT NULL,
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `schema_name` varchar(32) NOT NULL COMMENT '库名',
  `storage_id` int(10) NOT NULL COMMENT '存储ID',
  `outer_schema_id` int(10) NOT NULL COMMENT '外部库ID, 外部库ID,对应hive-meta库中DBS表中的DB_ID\n\n外部库ID,对应hive-meta库中DBS表中的DB_ID\n\n外部库ID,对应hive-meta库中DBS表中的DB_ID\n\n外部库ID,对应hive-meta库中DBS表中的DB_ID\n\n外部库ID,对应hive-meta库中DBS表中的DB_ID',
  `c_user_id` int(10) DEFAULT NULL COMMENT '创建人',
  `m_user_id` int(10) DEFAULT NULL COMMENT '修改人',
  `tenant_id` int(10) NOT NULL DEFAULT '0' COMMENT '租户id',
  `invalid` char(1) NOT NULL DEFAULT 'N' COMMENT '是否有效 Y:无效 N:有效',
  PRIMARY KEY (`schema_id`),
  KEY `fk_bas_schema_store_id` (`storage_id`),
  CONSTRAINT `fk_bas_schema_store_id` FOREIGN KEY (`storage_id`) REFERENCES `bas_storage` (`storage_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Schema基础信息';

-- ----------------------------
-- Table structure for bas_storage
-- ----------------------------
DROP TABLE IF EXISTS `bas_storage`;
CREATE TABLE `bas_storage` (
  `storage_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '存储ID',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `storage_name` varchar(64) DEFAULT NULL COMMENT '存储名',
  `storage_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '存储类型, 0: hive 1: mysql',
  `parameter` text NOT NULL COMMENT '连接参数, JSON格式',
  `tenant_id` int(10) NOT NULL,
  `descr` varchar(255) DEFAULT NULL COMMENT '描述信息',
  `invalid` char(1) NOT NULL DEFAULT 'N' COMMENT '是否有效 Y:无效 N:有效',
  PRIMARY KEY (`storage_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='存储信息';

-- ----------------------------
-- Table structure for entity_monitor_rule
-- ----------------------------
DROP TABLE IF EXISTS `entity_monitor_rule`;
CREATE TABLE `entity_monitor_rule` (
  `rule_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '表监控规则id',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `tenant_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '租户id',
  `monitor_type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '1:表监控2:字段监控',
  `entity_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '监控实体ID',
  `field_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '表字段id',
  `rule_name` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '规则名称',
  `rule_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '规则类型1:记录波动2:每日新增存储量3:总存储量4:字段规范性5:字段值',
  `rule_type_name` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '规则类型名称',
  `field_monitor_type` tinyint(3) unsigned DEFAULT NULL COMMENT '字段监控类型1:是否唯一2:是否为空3:是否符合格式4:avg5:max6:min7:sum8:方差',
  `field_monitor_name` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '字段监控类型',
  `compare_object` tinyint(3) unsigned DEFAULT NULL COMMENT '1:固定值2:前一天3:上一工作日4:上周同期5:最近七日平均6:最近30天平均',
  `compare_object_name` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '对比对象名称',
  `monitor_strategy_type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '监控策略类型: 1. 告警 2: 告警并阻塞',
  `trend_type` tinyint(3) unsigned DEFAULT NULL COMMENT '监控策略id 1:绝对值2:上升3:下降',
  `trend_type_name` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '监控策略名称',
  `compare_type` tinyint(3) unsigned DEFAULT NULL COMMENT '对比方式1:大于2:小于3:介于4:等于5:不等于',
  `compare_type_name` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '对比方式名称',
  `first_value` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '阈值',
  `second_value` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '阈值',
  `alert_receiver` varchar(50) COLLATE utf8_bin DEFAULT '' COMMENT '告警接收人',
  `c_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `m_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  `invalid` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'N' COMMENT '有效:N无效:Y',
  PRIMARY KEY (`rule_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='表监控规则id';

-- ----------------------------
-- Table structure for monitor_compare_result
-- ----------------------------
DROP TABLE IF EXISTS `monitor_compare_result`;
CREATE TABLE `monitor_compare_result` (
  `result_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ctime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `tenant_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '租户id',
  `entity_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '实例id',
  `rule_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '监控规则id',
  `result_key` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '指标',
  `standard_value` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '标准值',
  `result_value` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '计算结果值',
  `offset_value` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '偏差值（波动值）',
  `fluctuate_value` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '偏差百分比',
  `record_time` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '记录时间 2017-01-01',
  `result_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0:暂无数据1:正常2:需告警',
  `alert_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0:未告警1:已告警 ',
  `alert_receiver` varchar(50) COLLATE utf8_bin DEFAULT '' COMMENT '告警接收人',
  `invalid` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'N' COMMENT 'Y:无效N:有效',
  PRIMARY KEY (`result_id`),
  KEY `rule_id_idx` (`rule_id`) USING BTREE,
  KEY `record_time_idx` (`record_time`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='对比记录保存';

-- ----------------------------
-- Table structure for monitor_notice
-- ----------------------------
DROP TABLE IF EXISTS `monitor_notice`;
CREATE TABLE `monitor_notice` (
  `notice_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '告警记录',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `alert_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '监控规则id',
  `rule_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '监控规则id',
  `schema_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '数据库连接id',
  `entity_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '表实例id',
  `monitor_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '1:表监控2:字段监控',
  `rule_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '规则类型1:记录波动2:每日新增存储量3:总存储量4:字段规范性5:字段值',
  `schema_name` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '库名',
  `entity_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '实例名称',
  `rule_name` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '告警规则名称',
  `alert_receiver` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '告警接收人,分号分割',
  `alert_type` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '告警方式id,以,分割',
  `alert_type_name` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '告警方式名称,以,分割',
  `send_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0:未发送1:已发送',
  `handle_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0:未处理1:已处理',
  `c_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `m_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `invalid` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'N' COMMENT 'Y:无效N:有效',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='告警记录表';

-- ----------------------------
-- Table structure for monitor_notice_detail
-- ----------------------------
DROP TABLE IF EXISTS `monitor_notice_detail`;
CREATE TABLE `monitor_notice_detail` (
  `message_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '消息发送id',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `notice_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '告警noticeId',
  `alert_receiver` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '告警接收人',
  `alert_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '告警方式',
  `alert_type_name` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '告警方式名称1:短信2:电话3:邮件',
  `receiver_address` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '接收地址',
  `alert_content` varchar(500) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '告警内容',
  `send_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0:未发送1:已发送',
  `alert_count` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '告警次数',
  `invalid` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'N' COMMENT 'N:有效Y:无效',
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='消息发送记录详情';

-- ----------------------------
-- Table structure for rel_alert_receiver
-- ----------------------------
DROP TABLE IF EXISTS `rel_alert_receiver`;
CREATE TABLE `rel_alert_receiver` (
  `rid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `tenant_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '租户id',
  `entity_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '实体ID',
  `rule_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '告警规则id',
  `alert_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'bas_monitor_alert告警规则id',
  `alert_receiver` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '告警接收人',
  `c_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `m_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `invalid` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'N' COMMENT '无效:Y有效:N',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='告警接收人';

-- ----------------------------
-- Table structure for rel_alert_type
-- ----------------------------
DROP TABLE IF EXISTS `rel_alert_type`;
CREATE TABLE `rel_alert_type` (
  `rid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `tenant_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '租户id',
  `entity_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '实体ID',
  `rule_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '告警规则id',
  `alert_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'bas_monitor_alert告警规则id',
  `alert_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '告警方式1:短信2:电话3:邮件',
  `alert_type_name` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '告警方式名称',
  `c_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `m_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `invalid` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'N' COMMENT '无效:Y有效:N',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='告警方式';

-- ----------------------------
-- Table structure for rel_apply_detail
-- ----------------------------
DROP TABLE IF EXISTS `rel_apply_detail`;
CREATE TABLE `rel_apply_detail` (
  `apply_detail_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '申请详情ID',
  `apply_id` int(11) unsigned NOT NULL COMMENT '申请ID',
  `review_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '审批状态:1审批通过、2拒绝、0待审批、3撤销',
  `review_comment` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '子申请单审核意见',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `invalid` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'N' COMMENT '无效的？ N:有效 Y：无效',
  PRIMARY KEY (`apply_detail_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='权限申请 详情表';

-- ----------------------------
-- Table structure for rel_authorized_resource_fields
-- ----------------------------
DROP TABLE IF EXISTS `rel_authorized_resource_fields`;
CREATE TABLE `rel_authorized_resource_fields` (
  `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT '???????ID',
  `resource_id` int(11) NOT NULL COMMENT '一个资源ID 对应多个value（字段）',
  `field_id` int(11) NOT NULL COMMENT '映射到字段的ID',
  `apply_detail_id` int(11) NOT NULL COMMENT '申请详情ID',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `invalid` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'N' COMMENT '无效的？ N:有效 Y：无效',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='授权资源关联字段表';

-- ----------------------------
-- Table structure for rel_authorized_resource_opts
-- ----------------------------
DROP TABLE IF EXISTS `rel_authorized_resource_opts`;
CREATE TABLE `rel_authorized_resource_opts` (
  `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT '操作值ID',
  `resource_id` int(11) NOT NULL COMMENT '关联资源ID',
  `opt_rule_id` int(11) NOT NULL COMMENT '操作类型（一个资源ID 会有多个操作类型）',
  `apply_detail_id` int(11) DEFAULT NULL COMMENT '申请详情ID',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `invalid` char(11) COLLATE utf8_bin NOT NULL DEFAULT 'N' COMMENT '无效的？ N:有效 Y：无效',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='授权资源关联操作表';

-- ----------------------------
-- Table structure for rel_entity_cata
-- ----------------------------
DROP TABLE IF EXISTS `rel_entity_cata`;
CREATE TABLE `rel_entity_cata` (
  `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `cata_id` int(11) NOT NULL DEFAULT '0' COMMENT '类目ID',
  `entity_id` int(11) NOT NULL DEFAULT '0' COMMENT '实体表ID',
  `cata_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '类目类型 1: 数据类目 2: 标签类目',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建人ID',
  `modify_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '更新人ID',
  `invalid` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否有效， 0：有效。1: 无效',
  PRIMARY KEY (`rid`),
  KEY `idx_entity_id` (`entity_id`) USING BTREE,
  KEY `idx_cata_id` (`cata_id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='实体类目关联表';

-- ----------------------------
-- Table structure for rel_entity_config
-- ----------------------------
DROP TABLE IF EXISTS `rel_entity_config`;
CREATE TABLE `rel_entity_config` (
  `rid` int(10) NOT NULL AUTO_INCREMENT,
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `entity_id` int(10) NOT NULL COMMENT '实体ID',
  `entity_chinese_name` varchar(64) DEFAULT NULL COMMENT '中文名',
  `entity_ctime` datetime DEFAULT NULL COMMENT '实体创建时间',
  `entity_ddl_mtime` datetime DEFAULT NULL COMMENT 'DDL更新时间',
  `entity_data_mtime` datetime DEFAULT NULL COMMENT '数据更新时间',
  `num_rows` bigint(19) DEFAULT NULL COMMENT '行数',
  `total_size` bigint(19) DEFAULT NULL COMMENT '存储大小',
  `is_partition` char(1) DEFAULT 'N' COMMENT '是否分区表 Y:无效 N:有效',
  `descr` varchar(255) DEFAULT NULL COMMENT '描述信息',
  `invalid` char(1) NOT NULL DEFAULT 'N' COMMENT '是否有效 Y:无效 N:有效',
  `entity_type` tinyint(1) DEFAULT '0' COMMENT '表类型 0 公开表 1 私有表',
  PRIMARY KEY (`rid`),
  KEY `fk_rel_entity_config_entity_id` (`entity_id`),
  CONSTRAINT `fk_rel_entity_config_entity_id` FOREIGN KEY (`entity_id`) REFERENCES `bas_entity` (`entity_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='实体配置信息';

-- ----------------------------
-- Table structure for rel_entity_data
-- ----------------------------
DROP TABLE IF EXISTS `rel_entity_data`;
CREATE TABLE `rel_entity_data` (
  `rid` int(10) NOT NULL AUTO_INCREMENT,
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `entity_id` int(10) NOT NULL COMMENT '实体ID',
  `partition_id` int(10) NOT NULL COMMENT '分区ID',
  `count` int(1) NOT NULL DEFAULT '10' COMMENT '预览数据条数, 默认值10',
  `entity_data` longtext COMMENT '预览数据',
  `invalid` char(1) NOT NULL DEFAULT 'N' COMMENT '是否有效 Y:无效 N:有效',
  PRIMARY KEY (`rid`),
  KEY `fk_rel_entity_data_entity_id` (`entity_id`),
  CONSTRAINT `fk_rel_entity_data_entity_id` FOREIGN KEY (`entity_id`) REFERENCES `bas_entity` (`entity_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='存储预览数据, 可指定分区。 如果是分区表，预览最新分区的数据。';

-- ----------------------------
-- Table structure for rel_entity_history
-- ----------------------------
DROP TABLE IF EXISTS `rel_entity_history`;
CREATE TABLE `rel_entity_history` (
  `rid` int(10) NOT NULL AUTO_INCREMENT,
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `entity_id` int(10) NOT NULL COMMENT '实体ID',
  `num_rows` bigint(19) NOT NULL COMMENT '历史记录条数',
  `total_size` bigint(19) NOT NULL,
  `record_time` varchar(10) NOT NULL,
  `invalid` char(1) NOT NULL DEFAULT 'N' COMMENT '是否有效 Y:无效 N:有效',
  PRIMARY KEY (`rid`),
  KEY `fk_rel_entity_data_entity_id` (`entity_id`),
  CONSTRAINT `fk_rel_entity_history_entity_id` FOREIGN KEY (`entity_id`) REFERENCES `bas_entity` (`entity_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='历史记录条数';

-- ----------------------------
-- Table structure for rel_entity_isomery_lineage
-- ----------------------------
DROP TABLE IF EXISTS `rel_entity_isomery_lineage`;
CREATE TABLE `rel_entity_isomery_lineage` (
  `rid` int(10) NOT NULL AUTO_INCREMENT COMMENT 'rid',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `lineage_entity_id` int(10) NOT NULL COMMENT '上游或下游实体ID',
  `is_parent` tinyint(4) NOT NULL DEFAULT '0' COMMENT '异构实体是否是父节点： 0=父节点  1=子节点',
  `entity_name` varchar(45) NOT NULL DEFAULT '' COMMENT '异构表名',
  `schema_name` varchar(32) NOT NULL DEFAULT '' COMMENT '库名',
  `schema_type` varchar(32) NOT NULL DEFAULT '' COMMENT '数据库类型',
  `entity_chinese_name` varchar(64) DEFAULT NULL COMMENT '中文名',
  `entity_ctime` datetime DEFAULT NULL COMMENT '实体创建时间',
  `entity_ddl_mtime` datetime DEFAULT NULL COMMENT 'DDL更新时间',
  `entity_data_mtime` datetime DEFAULT NULL COMMENT '数据更新时间',
  `descr` varchar(255) DEFAULT NULL COMMENT '描述信息',
  `dxp_task_id` varchar(32) NOT NULL DEFAULT '' COMMENT '数据同步任务ID',
  `invalid` char(1) NOT NULL DEFAULT 'N' COMMENT '是否有效 Y:无效 N:有效',
  PRIMARY KEY (`rid`),
  KEY `fk_lineage_entity_id` (`lineage_entity_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='异构血缘实体信息';

-- ----------------------------
-- Table structure for rel_entity_life_cycle
-- ----------------------------
DROP TABLE IF EXISTS `rel_entity_life_cycle`;
CREATE TABLE `rel_entity_life_cycle` (
  `rid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `entity_id` int(10) NOT NULL COMMENT '实体ID',
  `life_cycle` int(10) NOT NULL DEFAULT '0' COMMENT '生命周期，3:3天,7: 7天 32: 32天 367: 367天 0: 永久',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `invalid` char(1) DEFAULT 'N' COMMENT '是否有效 Y:无效 N:有效',
  PRIMARY KEY (`rid`),
  KEY `fk_rel_entity_life_cycle_entity_id` (`entity_id`),
  CONSTRAINT `fk_rel_entity_life_cycle_entity_id` FOREIGN KEY (`entity_id`) REFERENCES `bas_entity` (`entity_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='实体的生产周期';

-- ----------------------------
-- Table structure for rel_field_cata
-- ----------------------------
DROP TABLE IF EXISTS `rel_field_cata`;
CREATE TABLE `rel_field_cata` (
  `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `cata_id` int(11) NOT NULL DEFAULT '0' COMMENT '类目ID',
  `field_id` int(11) NOT NULL DEFAULT '0' COMMENT '实体表ID',
  `cata_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '类目类型 1: 数据类目 2: 标签类目, 3:术语项类目',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建人ID',
  `modify_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '更新人ID',
  `invalid` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否有效， 0：有效。1: 无效',
  PRIMARY KEY (`rid`),
  KEY `idx_field_id` (`field_id`) USING BTREE,
  KEY `idx_cata_id` (`cata_id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='字段类目关联表';

-- ----------------------------
-- Table structure for rel_field_lineage
-- ----------------------------
DROP TABLE IF EXISTS `rel_field_lineage`;
CREATE TABLE `rel_field_lineage` (
  `rid` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '字段血缘ID',
  `input_schema_id` int(11) NOT NULL COMMENT '输入血缘库ID',
  `input_entity_id` int(11) NOT NULL COMMENT '输入血缘实体ID',
  `input_field_id` int(11) NOT NULL COMMENT '输入字段ID',
  `output_schema_id` int(11) NOT NULL COMMENT '输入血缘库ID',
  `output_entity_id` int(11) NOT NULL COMMENT '输入血缘实体ID',
  `output_field_id` int(11) NOT NULL COMMENT '输入字段ID',
  `tenant_id` int(11) NOT NULL COMMENT '创建租户ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `invalid` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'N' COMMENT '无效的？ N:有效 Y：无效',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='字段血缘关系表';

-- ----------------------------
-- Table structure for rel_namespace_storage
-- ----------------------------
DROP TABLE IF EXISTS `rel_namespace_storage`;
CREATE TABLE `rel_namespace_storage` (
  `rid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `tenant_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '租户id',
  `namespace_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '项目空间',
  `namespace_name` varchar(30) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '项目空间名称',
  `workspace_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '工作空间id',
  `schema_name` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '数据库名',
  `storage_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '数据库连接id',
  `invalid` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'N' COMMENT 'N:有效Y:无效',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='项目空间与storage对应关系';

-- ----------------------------
-- Table structure for rel_report_subscriber
-- ----------------------------
DROP TABLE IF EXISTS `rel_report_subscriber`;
CREATE TABLE `rel_report_subscriber` (
  `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `entity_id` int(11) NOT NULL DEFAULT '0' COMMENT '订阅的实体ID',
  `subscriber_ids` varchar(200) NOT NULL DEFAULT '0' COMMENT '订阅人ID',
  `subscriber_email` varchar(1000) NOT NULL DEFAULT '' COMMENT '订阅人邮件',
  `subscribe_cc_email` varchar(1000) NOT NULL DEFAULT '' COMMENT '订阅抄送人',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
  `modify_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '更新人ID',
  `invalid` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否有效，0: 有效 1:无效',
  PRIMARY KEY (`rid`),
  KEY `idx_entity_id` (`entity_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='质量报告订阅关联表';

-- ----------------------------
-- Table structure for statistics_task_instance
-- ----------------------------
DROP TABLE IF EXISTS `statistics_task_instance`;
CREATE TABLE `statistics_task_instance` (
  `rid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `task_id` int(10) NOT NULL COMMENT '任务ID',
  `task_instance` varchar(64) NOT NULL COMMENT '实例ID',
  `task_type` int(4) NOT NULL DEFAULT '0' COMMENT '1: tableCount 表数据量统计, 2: partionCount 分区数据量统计, 3:tableShow  数据预览',
  `tenant_id` int(10) NOT NULL DEFAULT '0' COMMENT '租户id',
  `invalid` char(1) NOT NULL DEFAULT 'N' COMMENT '是否有效 Y:无效 N:有效',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='数据统计的临时任务信息';

SET FOREIGN_KEY_CHECKS = 1;
