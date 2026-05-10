-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: medicine
-- ------------------------------------------------------
-- Server version	5.7.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `medicine`
--

/*!40000 DROP DATABASE IF EXISTS `medicine`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `medicine` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `medicine`;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `adminid` bigint(20) DEFAULT NULL COMMENT '管理员id',
  `ask` longtext COLLATE utf8mb4_unicode_ci COMMENT '提问',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复',
  `isreply` int(11) DEFAULT NULL COMMENT '是否回复',
  `isread` int(11) DEFAULT '0' COMMENT '已读/未读(1:已读,0:未读)',
  `uname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `uimage` longtext COLLATE utf8mb4_unicode_ci COMMENT '用户头像',
  `type` int(11) DEFAULT '1' COMMENT '内容类型(1:文本,2:图片,3:视频,4:文件,5:表情)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='互动咨询';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (1,'2026-04-26 06:41:02',1,1,'提问1','回复1',1,1,'用户名1','upload/chat_uimage1.jpg,upload/chat_uimage2.jpg,upload/chat_uimage3.jpg',1),(2,'2026-04-26 06:41:02',2,2,'提问2','回复2',2,2,'用户名2','upload/chat_uimage2.jpg,upload/chat_uimage3.jpg,upload/chat_uimage4.jpg',2),(3,'2026-04-26 06:41:02',3,3,'提问3','回复3',3,3,'用户名3','upload/chat_uimage3.jpg,upload/chat_uimage4.jpg,upload/chat_uimage5.jpg',3),(4,'2026-04-26 06:41:02',4,4,'提问4','回复4',4,4,'用户名4','upload/chat_uimage4.jpg,upload/chat_uimage5.jpg,upload/chat_uimage6.jpg',4),(5,'2026-04-26 06:41:02',5,5,'提问5','回复5',5,5,'用户名5','upload/chat_uimage5.jpg,upload/chat_uimage6.jpg,upload/chat_uimage7.jpg',5),(6,'2026-04-26 06:41:02',6,6,'提问6','回复6',6,6,'用户名6','upload/chat_uimage6.jpg,upload/chat_uimage7.jpg,upload/chat_uimage8.jpg',6),(7,'2026-04-26 06:41:02',7,7,'提问7','回复7',7,7,'用户名7','upload/chat_uimage7.jpg,upload/chat_uimage8.jpg,upload/chat_uimage1.jpg',7),(8,'2026-04-26 06:41:02',8,8,'提问8','回复8',8,8,'用户名8','upload/chat_uimage8.jpg,upload/chat_uimage1.jpg,upload/chat_uimage2.jpg',8);
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '配置参数名称',
  `value` varchar(200) DEFAULT NULL COMMENT '配置参数值',
  `url` varchar(500) DEFAULT NULL COMMENT 'url',
  `type` int(11) DEFAULT NULL COMMENT '参数类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='配置文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES (1,'picture1','upload/picture1.jpg',NULL,1),(2,'picture2','upload/picture2.jpg',NULL,1),(3,'picture3','upload/picture3.jpg',NULL,1),(11,'baidu','{\"appId\":\"49214550\",\"apiKey\":\"7Otjpv2kn0ljQk45qXOXh5MO\",\"secretKey\":\"BMfbXRbTIVaB4C3SbRTtGqDv1wHDvyXS\"}',NULL,2),(14,'deepseek','{\"key\":\"sk-961af215e60e4f269c2808b3a55eb9e8\"}',NULL,2),(21,'bLoginBackgroundImg','',NULL,3),(22,'bRegisterBackgroundImg','',NULL,3),(23,'bIndexBackgroundImg','',NULL,3),(24,'bTopLogo','',NULL,3),(25,'bHomeLogo','',NULL,3),(26,'fLoginBackgroundImg','',NULL,3),(27,'fRegisterBackgroudImg','',NULL,3),(28,'fTopLogo','',NULL,3);
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discussjinqueziyuan`
--

DROP TABLE IF EXISTS `discussjinqueziyuan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discussjinqueziyuan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `avatarurl` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `nickname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复内容',
  `thumbsupnum` int(11) DEFAULT '0' COMMENT '赞',
  `crazilynum` int(11) DEFAULT '0' COMMENT '踩',
  `istop` int(11) DEFAULT '0' COMMENT '置顶(1:置顶,0:非置顶)',
  `tuserids` longtext COLLATE utf8mb4_unicode_ci COMMENT '赞用户ids',
  `cuserids` longtext COLLATE utf8mb4_unicode_ci COMMENT '踩用户ids',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='紧缺资源评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discussjinqueziyuan`
--

LOCK TABLES `discussjinqueziyuan` WRITE;
/*!40000 ALTER TABLE `discussjinqueziyuan` DISABLE KEYS */;
/*!40000 ALTER TABLE `discussjinqueziyuan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discussyiliaoziyuan`
--

DROP TABLE IF EXISTS `discussyiliaoziyuan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discussyiliaoziyuan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `avatarurl` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `nickname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复内容',
  `thumbsupnum` int(11) DEFAULT '0' COMMENT '赞',
  `crazilynum` int(11) DEFAULT '0' COMMENT '踩',
  `istop` int(11) DEFAULT '0' COMMENT '置顶(1:置顶,0:非置顶)',
  `tuserids` longtext COLLATE utf8mb4_unicode_ci COMMENT '赞用户ids',
  `cuserids` longtext COLLATE utf8mb4_unicode_ci COMMENT '踩用户ids',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='医疗资源评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discussyiliaoziyuan`
--

LOCK TABLES `discussyiliaoziyuan` WRITE;
/*!40000 ALTER TABLE `discussyiliaoziyuan` DISABLE KEYS */;
/*!40000 ALTER TABLE `discussyiliaoziyuan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `huanzhe`
--

DROP TABLE IF EXISTS `huanzhe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `huanzhe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yonghuzhanghao` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户账号',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `yonghuxingming` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户姓名',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `xingbie` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `nianling` int(11) DEFAULT NULL COMMENT '年龄',
  `lianxifangshi` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系方式',
  `jiatingzhuzhi` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '家庭住址',
  `bingshi` longtext COLLATE utf8mb4_unicode_ci COMMENT '病史',
  `pquestion` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密保问题',
  `panswer` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密保答案',
  PRIMARY KEY (`id`),
  UNIQUE KEY `yonghuzhanghao` (`yonghuzhanghao`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='患者';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `huanzhe`
--

LOCK TABLES `huanzhe` WRITE;
/*!40000 ALTER TABLE `huanzhe` DISABLE KEYS */;
INSERT INTO `huanzhe` VALUES (11,'2026-04-26 06:41:02','007','e10adc3949ba59abbe56e057f20f883e','赵阳','upload/huanzhe_touxiang1.jpg','男',38,'13170567890','云南省昆明市五华区翠湖街道人民中路17号','无','密保问题1','密保答案1'),(12,'2026-04-26 06:41:02','004','e10adc3949ba59abbe56e057f20f883e','刘婷','upload/huanzhe_touxiang2.jpg','女',19,'13740890123','四川省成都市锦江区春熙路街道总府路15号','高血压','密保问题2','密保答案2'),(13,'2026-04-26 06:41:02','005','e10adc3949ba59abbe56e057f20f883e','李明','upload/huanzhe_touxiang3.jpg','男',35,'13650345678','湖北省武汉市洪山区光谷大道70号光谷新世界','无','密保问题3','密保答案3'),(14,'2026-04-26 06:41:02','003','e10adc3949ba59abbe56e057f20f883e','张伟','upload/huanzhe_touxiang4.jpg','男',41,'13930456789','广东省深圳市南山区科技园南区粤兴道20号','腰椎间盘突出','密保问题4','密保答案4'),(15,'2026-04-26 06:41:02','008','e10adc3949ba59abbe56e057f20f883e','孙悦','upload/huanzhe_touxiang5.jpg','女',27,'13280234567','黑龙江省哈尔滨市南岗区果戈里大街130号','慢性支气管炎','密保问题5','密保答案5'),(16,'2026-04-26 06:41:02','001','e10adc3949ba59abbe56e057f20f883e','陈浩','upload/huanzhe_touxiang6.jpg','男',29,'13810234567','北京市朝阳区建国路88号现代城小区','高血压糖尿病','密保问题6','密保答案6'),(17,'2026-04-26 06:41:02','002','e10adc3949ba59abbe56e057f20f883e','林玥','upload/huanzhe_touxiang7.jpg','女',24,'13520678901','上海市浦东新区张江高科技园区博云路123号','冠心病','密保问题7','密保答案7'),(18,'2026-04-26 06:41:02','006','e10adc3949ba59abbe56e057f20f883e','王佳','upload/huanzhe_touxiang8.jpg','女',17,'13460987654','陕西省西安市雁塔区大雁塔街道小寨东路8号','糖尿病','密保问题8','密保答案8');
/*!40000 ALTER TABLE `huanzhe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jinqueziyuan`
--

DROP TABLE IF EXISTS `jinqueziyuan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jinqueziyuan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ziyuanbianhao` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '资源编号',
  `ziyuanmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '资源名称',
  `ziyuantupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '资源图片',
  `ziyuanfenlei` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源分类',
  `zhuyishixiang` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '注意事项',
  `ziyuanjiage` double NOT NULL COMMENT '资源价格',
  `paiduirenshu` int(11) DEFAULT NULL COMMENT '排队人数',
  `ziyuanyongtu` longtext COLLATE utf8mb4_unicode_ci COMMENT '资源用途',
  `ziyuanjianjie` longtext COLLATE utf8mb4_unicode_ci COMMENT '资源简介',
  `clicknum` int(11) DEFAULT '0' COMMENT '点击次数',
  `discussnum` int(11) DEFAULT '0' COMMENT '评论数',
  `storeupnum` int(11) DEFAULT '0' COMMENT '收藏数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ziyuanbianhao` (`ziyuanbianhao`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='紧缺资源';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jinqueziyuan`
--

LOCK TABLES `jinqueziyuan` WRITE;
/*!40000 ALTER TABLE `jinqueziyuan` DISABLE KEYS */;
INSERT INTO `jinqueziyuan` VALUES (1,'2026-04-26 06:41:02','BS2023001','青州市谭坊镇普通卫生院输液架','upload/jinqueziyuan_青州市谭坊镇普通卫生院输液架1.jpg,upload/jinqueziyuan_青州市谭坊镇普通卫生院输液架2.jpg,upload/jinqueziyuan_青州市谭坊镇普通卫生院输液架3.jpg','病房器械','检查支架稳定性防止倾倒',150,1,'患者静脉输液治疗使用','可升降移动式输液支架',1,0,1),(2,'2026-04-26 06:41:02','TS2023007','曹县人民医院CT机','upload/jinqueziyuan_曹县人民医院CT机1.jpg,upload/jinqueziyuan_曹县人民医院CT机2.jpg,upload/jinqueziyuan_曹县人民医院CT机3.jpg','医疗设备','定期维护校准避免故障',163,6,'临床疾病诊断扫描检查','多排螺旋CT扫描仪器',2,0,2),(3,'2026-04-26 06:41:02','XL2023014','寿光市圣城街道社区卫生服务中心雾化器','upload/jinqueziyuan_寿光市圣城街道社区卫生服务中心雾化器1.jpg,upload/jinqueziyuan_寿光市圣城街道社区卫生服务中心雾化器2.jpg,upload/jinqueziyuan_寿光市圣城街道社区卫生服务中心雾化器3.jpg','治疗设备','清洁雾化杯防止细菌滋生',189,4,'呼吸道疾病患者雾化治疗','压缩式医用雾化吸入仪器',3,0,3),(4,'2026-04-26 06:41:02','BK2023013','临朐县冶源街道中心卫生院救护车','upload/jinqueziyuan_临朐县冶源街道中心卫生院救护车1.jpg,upload/jinqueziyuan_临朐县冶源街道中心卫生院救护车2.jpg,upload/jinqueziyuan_临朐县冶源街道中心卫生院救护车3.jpg','急救设备','定期检修确保车况良好',111,1,'院前急救转运危重患者','搭载急救监护设备专用车辆',4,0,4),(5,'2026-04-26 06:41:02','JL2023009','莱州市平里店镇专科骨科门诊康复训练器','upload/jinqueziyuan_莱州市平里店镇专科骨科门诊康复训练器1.jpg,upload/jinqueziyuan_莱州市平里店镇专科骨科门诊康复训练器2.jpg,upload/jinqueziyuan_莱州市平里店镇专科骨科门诊康复训练器3.jpg','康复设备','根据患者情况调节强度',88,1,'骨折术后患者康复锻炼','下肢关节康复训练仪器',5,0,5),(6,'2026-04-26 06:41:02','WL2023020','肥城市新城街道民营协和诊所用诊查床','upload/jinqueziyuan_肥城市新城街道民营协和诊所用诊查床1.jpg,upload/jinqueziyuan_肥城市新城街道民营协和诊所用诊查床2.jpg,upload/jinqueziyuan_肥城市新城街道民营协和诊所用诊查床3.jpg','检查设备','铺好一次性垫单保持卫生',127,4,'患者常规体格检查使用','简易式医用诊查诊疗床',6,0,6),(7,'2026-04-26 06:41:02','QY2023017','滕州市柴胡店镇刘村卫生室听诊器','upload/jinqueziyuan_滕州市柴胡店镇刘村卫生室听诊器1.jpg,upload/jinqueziyuan_滕州市柴胡店镇刘村卫生室听诊器2.jpg,upload/jinqueziyuan_滕州市柴胡店镇刘村卫生室听诊器3.jpg','基础器械','轻拿轻放避免部件损坏',156,5,'心肺呼吸音等体征检查','双听头医用听诊仪器',7,0,7),(8,'2026-04-26 06:41:02','SV2023004','肥城市新城街道民营协和诊所血压计','upload/jinqueziyuan_肥城市新城街道民营协和诊所血压计1.jpg,upload/jinqueziyuan_肥城市新城街道民营协和诊所血压计2.jpg,upload/jinqueziyuan_肥城市新城街道民营协和诊所血压计3.jpg','基础设备','定期校准保证测量精准',142,1,'高血压患者血压监测','电子上臂式血压测量仪器',8,0,8);
/*!40000 ALTER TABLE `jinqueziyuan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '留言人id',
  `username` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `avatarurl` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '留言内容',
  `cpicture` longtext COLLATE utf8mb4_unicode_ci COMMENT '留言图片',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复内容',
  `rpicture` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='反馈建议';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'2026-04-26 06:41:02',1,'用户名1','upload/messages_avatarurl1.jpg','留言内容1','upload/messages_cpicture1.jpg','回复内容1','upload/messages_rpicture1.jpg'),(2,'2026-04-26 06:41:02',2,'用户名2','upload/messages_avatarurl2.jpg','留言内容2','upload/messages_cpicture2.jpg','回复内容2','upload/messages_rpicture2.jpg'),(3,'2026-04-26 06:41:02',3,'用户名3','upload/messages_avatarurl3.jpg','留言内容3','upload/messages_cpicture3.jpg','回复内容3','upload/messages_rpicture3.jpg'),(4,'2026-04-26 06:41:02',4,'用户名4','upload/messages_avatarurl4.jpg','留言内容4','upload/messages_cpicture4.jpg','回复内容4','upload/messages_rpicture4.jpg'),(5,'2026-04-26 06:41:02',5,'用户名5','upload/messages_avatarurl5.jpg','留言内容5','upload/messages_cpicture5.jpg','回复内容5','upload/messages_rpicture5.jpg'),(6,'2026-04-26 06:41:02',6,'用户名6','upload/messages_avatarurl6.jpg','留言内容6','upload/messages_cpicture6.jpg','回复内容6','upload/messages_rpicture6.jpg'),(7,'2026-04-26 06:41:02',7,'用户名7','upload/messages_avatarurl7.jpg','留言内容7','upload/messages_cpicture7.jpg','回复内容7','upload/messages_rpicture7.jpg'),(8,'2026-04-26 06:41:02',8,'用户名8','upload/messages_avatarurl8.jpg','留言内容8','upload/messages_cpicture8.jpg','回复内容8','upload/messages_rpicture8.jpg');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `picture` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片',
  `introduction` longtext COLLATE utf8mb4_unicode_ci COMMENT '简介',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发布人',
  `headportrait` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `storeupnum` int(11) DEFAULT '0' COMMENT '收藏数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='公告资讯';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'2026-04-26 06:41:02','传染病筛查','upload/news_picture1.jpg','乙肝五项丙肝抗体艾滋病抗体检测','为有传染病暴露风险的人群提供乙肝丙肝艾滋病筛查服务及时发现感染情况','发布人1','upload/news_headportrait1.jpg',1),(2,'2026-04-26 06:41:02','营养膳食指导','upload/news_picture2.jpg','膳食调查营养评估饮食计划制定食谱设计','由专业营养师根据糖尿病患者的病情和身体状况制定个性化的饮食方案指导患者合理安排饮食','发布人2','upload/news_headportrait2.jpg',2),(3,'2026-04-26 06:41:02','康复理疗服务','upload/news_picture3.jpg','关节活动度训练肌力训练物理因子治疗作业疗法','根据患者损伤情况制定个性化康复计划通过专业训练和治疗促进骨关节功能恢复提高生活自理能力','发布人3','upload/news_headportrait3.jpg',3),(4,'2026-04-26 06:41:02','老年人慢性病管理','upload/news_picture4.jpg','血压监测血糖监测心电图检查用药调整建议','建立个人健康档案每月线上随访记录病情变化每季度线下全面体检提供饮食运动及用药指导','发布人4','upload/news_headportrait4.jpg',4),(5,'2026-04-26 06:41:02','心理健康咨询','upload/news_picture5.jpg','情绪评估心理测评认知行为治疗放松训练','针对焦虑抑郁压力等心理问题提供专业咨询帮助来访者缓解心理困扰改善情绪状态','发布人5','upload/news_headportrait5.jpg',5),(6,'2026-04-26 06:41:02','儿科急诊服务','upload/news_picture6.jpg','急症诊断治疗输液吸氧雾化治疗','为急症儿童提供24小时紧急医疗服务快速诊断病情及时进行治疗处理','发布人6','upload/news_headportrait6.jpg',6),(7,'2026-04-26 06:41:02','妇科炎症诊疗','upload/news_picture7.jpg','妇科检查白带常规检测细菌培养药敏试验','针对各种妇科炎症进行专业诊断根据检查结果制定个性化治疗方案','发布人7','upload/news_headportrait7.jpg',7),(8,'2026-04-26 06:41:02','儿童疫苗接种','upload/news_picture8.jpg','乙肝疫苗脊灰疫苗百白破疫苗麻疹疫苗','按照国家免疫规划程序为儿童提供适龄疫苗接种服务接种前进行健康评估接种后留观30分钟','发布人8','upload/news_headportrait8.jpg',8);
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paiduijindu`
--

DROP TABLE IF EXISTS `paiduijindu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paiduijindu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `shenqingbianhao` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '申请编号',
  `ziyuanbianhao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源编号',
  `ziyuanmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源名称',
  `ziyuantupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '资源图片',
  `ziyuanfenlei` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源分类',
  `ziyuanjiage` double DEFAULT NULL COMMENT '资源价格',
  `paiduixuhao` int(11) DEFAULT NULL COMMENT '排队序号',
  `muqianpaixu` int(11) DEFAULT NULL COMMENT '目前排序',
  `yonghuzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户账号',
  `yishenggonghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '医生工号',
  `gengxinshijian` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='排队进度';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paiduijindu`
--

LOCK TABLES `paiduijindu` WRITE;
/*!40000 ALTER TABLE `paiduijindu` DISABLE KEYS */;
INSERT INTO `paiduijindu` VALUES (1,'2026-04-26 06:41:02','YS004','BS2023001','青州市谭坊镇普通卫生院输液架','upload/paiduijindu_青州市谭坊镇普通卫生院输液架1.jpg,upload/paiduijindu_青州市谭坊镇普通卫生院输液架2.jpg,upload/paiduijindu_青州市谭坊镇普通卫生院输液架3.jpg','病房器械',150,1,1,'007','22','2023-06-19 18:42:17'),(2,'2026-04-26 06:41:02','YS011','TS2023007','曹县人民医院CT机','upload/paiduijindu_曹县人民医院CT机1.jpg,upload/paiduijindu_曹县人民医院CT机2.jpg,upload/paiduijindu_曹县人民医院CT机3.jpg','医疗设备',163,6,6,'004','28','2025-08-30 18:07:51'),(3,'2026-04-26 06:41:02','YS012','XL2023014','寿光市圣城街道社区卫生服务中心雾化器','upload/paiduijindu_寿光市圣城街道社区卫生服务中心雾化器1.jpg,upload/paiduijindu_寿光市圣城街道社区卫生服务中心雾化器2.jpg,upload/paiduijindu_寿光市圣城街道社区卫生服务中心雾化器3.jpg','治疗设备',189,4,4,'005','24','2025-03-14 08:45:12'),(4,'2026-04-26 06:41:02','YS014','BK2023013','临朐县冶源街道中心卫生院救护车','upload/paiduijindu_临朐县冶源街道中心卫生院救护车1.jpg,upload/paiduijindu_临朐县冶源街道中心卫生院救护车2.jpg,upload/paiduijindu_临朐县冶源街道中心卫生院救护车3.jpg','急救设备',111,1,1,'003','27','2025-08-23 13:41:26'),(5,'2026-04-26 06:41:02','YS009','JL2023009','莱州市平里店镇专科骨科门诊康复训练器','upload/paiduijindu_莱州市平里店镇专科骨科门诊康复训练器1.jpg,upload/paiduijindu_莱州市平里店镇专科骨科门诊康复训练器2.jpg,upload/paiduijindu_莱州市平里店镇专科骨科门诊康复训练器3.jpg','康复设备',88,1,1,'008','21','2025-08-10 13:21:09'),(6,'2026-04-26 06:41:02','YS015','WL2023020','肥城市新城街道民营协和诊所用诊查床','upload/paiduijindu_肥城市新城街道民营协和诊所用诊查床1.jpg,upload/paiduijindu_肥城市新城街道民营协和诊所用诊查床2.jpg,upload/paiduijindu_肥城市新城街道民营协和诊所用诊查床3.jpg','检查设备',127,4,4,'001','26','2022-09-10 15:19:42'),(7,'2026-04-26 06:41:02','YS003','QY2023017','滕州市柴胡店镇刘村卫生室听诊器','upload/paiduijindu_滕州市柴胡店镇刘村卫生室听诊器1.jpg,upload/paiduijindu_滕州市柴胡店镇刘村卫生室听诊器2.jpg,upload/paiduijindu_滕州市柴胡店镇刘村卫生室听诊器3.jpg','基础器械',156,5,5,'002','25','2025-01-18 10:53:24'),(8,'2026-04-26 06:41:02','YS020','SV2023004','肥城市新城街道民营协和诊所血压计','upload/paiduijindu_肥城市新城街道民营协和诊所血压计1.jpg,upload/paiduijindu_肥城市新城街道民营协和诊所血压计2.jpg,upload/paiduijindu_肥城市新城街道民营协和诊所血压计3.jpg','基础设备',142,1,1,'006','23','2024-09-02 15:47:31');
/*!40000 ALTER TABLE `paiduijindu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paiduishenqing`
--

DROP TABLE IF EXISTS `paiduishenqing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paiduishenqing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `shenqingbianhao` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '申请编号',
  `ziyuanbianhao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源编号',
  `ziyuanmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源名称',
  `ziyuantupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '资源图片',
  `ziyuanfenlei` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源分类',
  `ziyuanjiage` double DEFAULT NULL COMMENT '资源价格',
  `paiduirenshu` int(11) DEFAULT NULL COMMENT '排队人数',
  `paiduixuhao` int(11) DEFAULT NULL COMMENT '排队序号',
  `shenqingyuanyin` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '申请原因',
  `yonghuzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户账号',
  `yishenggonghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '医生工号',
  `shenqingshijian` datetime DEFAULT NULL COMMENT '申请时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `shenqingbianhao` (`shenqingbianhao`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='排队申请';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paiduishenqing`
--

LOCK TABLES `paiduishenqing` WRITE;
/*!40000 ALTER TABLE `paiduishenqing` DISABLE KEYS */;
INSERT INTO `paiduishenqing` VALUES (1,'2026-04-26 06:41:02','YS004','BS2023001','青州市谭坊镇普通卫生院输液架','upload/paiduishenqing_青州市谭坊镇普通卫生院输液架1.jpg,upload/paiduishenqing_青州市谭坊镇普通卫生院输液架2.jpg,upload/paiduishenqing_青州市谭坊镇普通卫生院输液架3.jpg','病房器械',150,1,1,'我院无冠脉造影检查设备，无法明确冠脉病变程度，无法评估是否需要支架植入治疗','007','22','2023-06-19 18:42:17'),(2,'2026-04-26 06:41:02','YS011','TS2023007','曹县人民医院CT机','upload/paiduishenqing_曹县人民医院CT机1.jpg,upload/paiduishenqing_曹县人民医院CT机2.jpg,upload/paiduishenqing_曹县人民医院CT机3.jpg','医疗设备',163,6,6,'我院无血液透析中心，无法为患者提供规律透析治疗，且缺乏肾内科专科医师评估肾移植指征','004','28','2025-08-30 18:07:51'),(3,'2026-04-26 06:41:02','YS012','XL2023014','寿光市圣城街道社区卫生服务中心雾化器','upload/paiduishenqing_寿光市圣城街道社区卫生服务中心雾化器1.jpg,upload/paiduishenqing_寿光市圣城街道社区卫生服务中心雾化器2.jpg,upload/paiduishenqing_寿光市圣城街道社区卫生服务中心雾化器3.jpg','治疗设备',189,4,4,'我院无儿科重症监护病房，无法监测患儿神经系统及循环系统功能变化，缺乏重症手足口病救治经验','005','24','2025-03-14 08:45:12'),(4,'2026-04-26 06:41:02','YS014','BK2023013','临朐县冶源街道中心卫生院救护车','upload/paiduishenqing_临朐县冶源街道中心卫生院救护车1.jpg,upload/paiduishenqing_临朐县冶源街道中心卫生院救护车2.jpg,upload/paiduishenqing_临朐县冶源街道中心卫生院救护车3.jpg','急救设备',111,1,1,'我院无法开展过敏原检测及免疫功能评估，且缺乏儿科风湿免疫专科医师制定治疗方案','003','27','2025-08-23 13:41:26'),(5,'2026-04-26 06:41:02','YS009','JL2023009','莱州市平里店镇专科骨科门诊康复训练器','upload/paiduishenqing_莱州市平里店镇专科骨科门诊康复训练器1.jpg,upload/paiduishenqing_莱州市平里店镇专科骨科门诊康复训练器2.jpg,upload/paiduishenqing_莱州市平里店镇专科骨科门诊康复训练器3.jpg','康复设备',88,1,1,'我院无呼吸专科门诊及肺功能检查设备，无法进行哮喘病情分级及制定长期控制方案','008','21','2025-08-10 13:21:09'),(6,'2026-04-26 06:41:02','YS015','WL2023020','肥城市新城街道民营协和诊所用诊查床','upload/paiduishenqing_肥城市新城街道民营协和诊所用诊查床1.jpg,upload/paiduishenqing_肥城市新城街道民营协和诊所用诊查床2.jpg,upload/paiduishenqing_肥城市新城街道民营协和诊所用诊查床3.jpg','检查设备',127,4,4,'初步诊断急性阑尾炎，需进一步完善腹部CT检查并评估手术指征，我院不具备腹腔镜手术条件','001','26','2022-09-10 15:19:42'),(7,'2026-04-26 06:41:02','YS003','QY2023017','滕州市柴胡店镇刘村卫生室听诊器','upload/paiduishenqing_滕州市柴胡店镇刘村卫生室听诊器1.jpg,upload/paiduishenqing_滕州市柴胡店镇刘村卫生室听诊器2.jpg,upload/paiduishenqing_滕州市柴胡店镇刘村卫生室听诊器3.jpg','基础器械',156,5,5,'我院无神经内科专科医师及溶栓治疗资质，无法在溶栓时间窗内进行静脉溶栓治疗','002','25','2025-01-18 10:53:24'),(8,'2026-04-26 06:41:02','YS020','SV2023004','肥城市新城街道民营协和诊所血压计','upload/paiduishenqing_肥城市新城街道民营协和诊所血压计1.jpg,upload/paiduishenqing_肥城市新城街道民营协和诊所血压计2.jpg,upload/paiduishenqing_肥城市新城街道民营协和诊所血压计3.jpg','基础设备',142,1,1,'我院缺乏皮肤科专科药物（如生物制剂）及光疗设备，无法制定系统治疗方案','006','23','2024-09-02 15:47:31');
/*!40000 ALTER TABLE `paiduishenqing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storeup`
--

DROP TABLE IF EXISTS `storeup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storeup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `refid` bigint(20) DEFAULT NULL COMMENT '外键id',
  `tablename` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '表名',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `picture` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `type` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '1' COMMENT '类型',
  `inteltype` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '推荐类型',
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='收藏表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storeup`
--

LOCK TABLES `storeup` WRITE;
/*!40000 ALTER TABLE `storeup` DISABLE KEYS */;
/*!40000 ALTER TABLE `storeup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `syslog`
--

DROP TABLE IF EXISTS `syslog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `syslog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `username` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `operation` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户操作',
  `method` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '请求方法',
  `params` longtext COLLATE utf8mb4_unicode_ci COMMENT '请求参数',
  `time` bigint(20) DEFAULT NULL COMMENT '请求时长(毫秒)',
  `ip` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'IP地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `syslog`
--

LOCK TABLES `syslog` WRITE;
/*!40000 ALTER TABLE `syslog` DISABLE KEYS */;
/*!40000 ALTER TABLE `syslog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `tablename` varchar(100) DEFAULT NULL COMMENT '表名',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `token` varchar(500) NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='token表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `username` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `role` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '管理员' COMMENT '角色',
  `image` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2026-04-26 06:41:02','admin','admin','管理员','upload/image1.jpg');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yiliaoziyuan`
--

DROP TABLE IF EXISTS `yiliaoziyuan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yiliaoziyuan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ziyuanbianhao` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '资源编号',
  `ziyuanmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '资源名称',
  `ziyuantupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '资源图片',
  `ziyuanfenlei` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源分类',
  `zhuyishixiang` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '注意事项',
  `ziyuanjiage` double NOT NULL COMMENT '资源价格',
  `ziyuanshuliang` int(11) DEFAULT NULL COMMENT '资源数量',
  `ziyuanyongtu` longtext COLLATE utf8mb4_unicode_ci COMMENT '资源用途',
  `ziyuanjianjie` longtext COLLATE utf8mb4_unicode_ci COMMENT '资源简介',
  `clicktime` datetime DEFAULT NULL COMMENT '最近点击时间',
  `clicknum` int(11) DEFAULT '0' COMMENT '点击次数',
  `discussnum` int(11) DEFAULT '0' COMMENT '评论数',
  `storeupnum` int(11) DEFAULT '0' COMMENT '收藏数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ziyuanbianhao` (`ziyuanbianhao`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='医疗资源';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yiliaoziyuan`
--

LOCK TABLES `yiliaoziyuan` WRITE;
/*!40000 ALTER TABLE `yiliaoziyuan` DISABLE KEYS */;
INSERT INTO `yiliaoziyuan` VALUES (1,'2026-04-26 06:41:02','BS2023001','青州市谭坊镇普通卫生院输液架','upload/yiliaoziyuan_青州市谭坊镇普通卫生院输液架1.jpg,upload/yiliaoziyuan_青州市谭坊镇普通卫生院输液架2.jpg,upload/yiliaoziyuan_青州市谭坊镇普通卫生院输液架3.jpg','病房器械','检查支架稳定性防止倾倒',150,23,'患者静脉输液治疗使用','可升降移动式输液支架','2026-04-26 14:41:02',1,0,1),(2,'2026-04-26 06:41:02','TS2023007','曹县人民医院CT机','upload/yiliaoziyuan_曹县人民医院CT机1.jpg,upload/yiliaoziyuan_曹县人民医院CT机2.jpg,upload/yiliaoziyuan_曹县人民医院CT机3.jpg','医疗设备','定期维护校准避免故障',163,39,'临床疾病诊断扫描检查','多排螺旋CT扫描仪器','2026-04-26 14:41:02',2,0,2),(3,'2026-04-26 06:41:02','XL2023014','寿光市圣城街道社区卫生服务中心雾化器','upload/yiliaoziyuan_寿光市圣城街道社区卫生服务中心雾化器1.jpg,upload/yiliaoziyuan_寿光市圣城街道社区卫生服务中心雾化器2.jpg,upload/yiliaoziyuan_寿光市圣城街道社区卫生服务中心雾化器3.jpg','治疗设备','清洁雾化杯防止细菌滋生',189,44,'呼吸道疾病患者雾化治疗','压缩式医用雾化吸入仪器','2026-04-26 14:41:02',3,0,3),(4,'2026-04-26 06:41:02','BK2023013','临朐县冶源街道中心卫生院救护车','upload/yiliaoziyuan_临朐县冶源街道中心卫生院救护车1.jpg,upload/yiliaoziyuan_临朐县冶源街道中心卫生院救护车2.jpg,upload/yiliaoziyuan_临朐县冶源街道中心卫生院救护车3.jpg','急救设备','定期检修确保车况良好',111,38,'院前急救转运危重患者','搭载急救监护设备专用车辆','2026-04-26 14:41:02',4,0,4),(5,'2026-04-26 06:41:02','JL2023009','莱州市平里店镇专科骨科门诊康复训练器','upload/yiliaoziyuan_莱州市平里店镇专科骨科门诊康复训练器1.jpg,upload/yiliaoziyuan_莱州市平里店镇专科骨科门诊康复训练器2.jpg,upload/yiliaoziyuan_莱州市平里店镇专科骨科门诊康复训练器3.jpg','康复设备','根据患者情况调节强度',88,55,'骨折术后患者康复锻炼','下肢关节康复训练仪器','2026-04-26 14:41:02',5,0,5),(6,'2026-04-26 06:41:02','WL2023020','肥城市新城街道民营协和诊所用诊查床','upload/yiliaoziyuan_肥城市新城街道民营协和诊所用诊查床1.jpg,upload/yiliaoziyuan_肥城市新城街道民营协和诊所用诊查床2.jpg,upload/yiliaoziyuan_肥城市新城街道民营协和诊所用诊查床3.jpg','检查设备','铺好一次性垫单保持卫生',127,72,'患者常规体格检查使用','简易式医用诊查诊疗床','2026-04-26 14:41:02',6,0,6),(7,'2026-04-26 06:41:02','QY2023017','滕州市柴胡店镇刘村卫生室听诊器','upload/yiliaoziyuan_滕州市柴胡店镇刘村卫生室听诊器1.jpg,upload/yiliaoziyuan_滕州市柴胡店镇刘村卫生室听诊器2.jpg,upload/yiliaoziyuan_滕州市柴胡店镇刘村卫生室听诊器3.jpg','基础器械','轻拿轻放避免部件损坏',156,48,'心肺呼吸音等体征检查','双听头医用听诊仪器','2026-04-26 14:41:02',7,0,7),(8,'2026-04-26 06:41:02','SV2023004','肥城市新城街道民营协和诊所血压计','upload/yiliaoziyuan_肥城市新城街道民营协和诊所血压计1.jpg,upload/yiliaoziyuan_肥城市新城街道民营协和诊所血压计2.jpg,upload/yiliaoziyuan_肥城市新城街道民营协和诊所血压计3.jpg','基础设备','定期校准保证测量精准',142,58,'高血压患者血压监测','电子上臂式血压测量仪器','2026-04-26 14:41:02',8,0,8);
/*!40000 ALTER TABLE `yiliaoziyuan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yisheng`
--

DROP TABLE IF EXISTS `yisheng`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yisheng` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yishenggonghao` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '医生工号',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `yishengxingming` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '医生姓名',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `zhixingzigezheng` longtext COLLATE utf8mb4_unicode_ci COMMENT '执行资格证',
  `keshimingcheng` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '科室名称',
  `zhicheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '职称',
  `fuwushijian` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '服务时间',
  `shanzhanglingyu` longtext COLLATE utf8mb4_unicode_ci COMMENT '擅长领域',
  `zhiyelvli` longtext COLLATE utf8mb4_unicode_ci COMMENT '职业履历',
  `sfsh` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext COLLATE utf8mb4_unicode_ci COMMENT '审核回复',
  `pquestion` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密保问题',
  `panswer` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密保答案',
  PRIMARY KEY (`id`),
  UNIQUE KEY `yishenggonghao` (`yishenggonghao`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='医生';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yisheng`
--

LOCK TABLES `yisheng` WRITE;
/*!40000 ALTER TABLE `yisheng` DISABLE KEYS */;
INSERT INTO `yisheng` VALUES (21,'2026-04-26 06:41:02','22','e10adc3949ba59abbe56e057f20f883e','陈然','upload/yisheng_touxiang1.jpg','','麻醉科','主任医师','周一至周五8:00-17:00周六8:00-12:00','快速筛查结果准确','华为技术有限公司后端开发工程师2021-至今','是','','密保问题1','密保答案1'),(22,'2026-04-26 06:41:02','28','e10adc3949ba59abbe56e057f20f883e','周宇恒','upload/yisheng_touxiang2.jpg','','康复科','主治医师','周一至周五8:00-17:00','个体化饮食方案专业营养师指导','网易数据分析师2021-至今','是','','密保问题2','密保答案2'),(23,'2026-04-26 06:41:02','24','e10adc3949ba59abbe56e057f20f883e','林文','upload/yisheng_touxiang3.jpg','','外科','副主任医师','周一至周五8:00-17:00周六8:00-12:00','个性化康复方案专业康复器械辅助','阿里巴巴集团UI设计师2022-至今','是','','密保问题3','密保答案3'),(24,'2026-04-26 06:41:02','27','e10adc3949ba59abbe56e057f20f883e','高欣','upload/yisheng_touxiang4.jpg','','口腔科','主任医师','周一至周日8:00-17:30','个性化健康档案动态监测用药指导','腾讯科技有限公司产品经理2022-至今','是','','密保问题4','密保答案4'),(25,'2026-04-26 06:41:02','21','e10adc3949ba59abbe56e057f20f883e','梁行高','upload/yisheng_touxiang5.jpg','','药剂科','主治医师','周一至周日9:00-21:00','保密专业一对一心理疏导','广东省建筑设计研究院建筑设计师2020-至今','是','','密保问题5','密保答案5'),(26,'2026-04-26 06:41:02','26','e10adc3949ba59abbe56e057f20f883e','马子轩','upload/yisheng_touxiang6.jpg','','中医科','副主任医师','24小时','24小时接诊快速诊疗','字节跳动前端开发工程师2021-至今','是','','密保问题6','密保答案6'),(27,'2026-04-26 06:41:02','25','e10adc3949ba59abbe56e057f20f883e','徐行达','upload/yisheng_touxiang7.jpg','','传染科','主治医师','周一至周六8:00-17:30','精准诊断高效治疗','京东市场营销专员2022-至今','是','','密保问题7','密保答案7'),(28,'2026-04-26 06:41:02','23','e10adc3949ba59abbe56e057f20f883e','赵思雨','upload/yisheng_touxiang8.jpg','','儿科','主任医师','周一至周五8:00-11:3013:30-17:00周六8:00-11:30','专业医护团队操作安全接种流程','美团销售代表2023-至今','是','','密保问题8','密保答案8');
/*!40000 ALTER TABLE `yisheng` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yuyuequxiao`
--

DROP TABLE IF EXISTS `yuyuequxiao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yuyuequxiao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `shenqingbianhao` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单编号',
  `ziyuanbianhao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源编号',
  `ziyuanmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源名称',
  `ziyuantupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '资源图片',
  `ziyuanfenlei` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源分类',
  `ziyuanjiage` double DEFAULT NULL COMMENT '资源价格',
  `ziyuanshuliang` int(11) DEFAULT NULL COMMENT '取消数量',
  `tuidingjine` double DEFAULT NULL COMMENT '取消金额',
  `quxiaoyuanyin` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '取消原因',
  `yonghuzhanghao` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户账号',
  `yishenggonghao` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '医生工号',
  `quxiaoshijian` datetime DEFAULT NULL COMMENT '取消时间',
  `sfsh` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext COLLATE utf8mb4_unicode_ci COMMENT '审核回复',
  `ispay` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '未支付' COMMENT '是否支付',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='预约取消';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yuyuequxiao`
--

LOCK TABLES `yuyuequxiao` WRITE;
/*!40000 ALTER TABLE `yuyuequxiao` DISABLE KEYS */;
INSERT INTO `yuyuequxiao` VALUES (1,'2026-04-26 06:41:02','YS004','BS2023001','青州市谭坊镇普通卫生院输液架','upload/yuyuequxiao_青州市谭坊镇普通卫生院输液架1.jpg,upload/yuyuequxiao_青州市谭坊镇普通卫生院输液架2.jpg,upload/yuyuequxiao_青州市谭坊镇普通卫生院输液架3.jpg','病房器械',15,5,75,'预约后24小时内可取消','007','22','2023-06-19 18:42:17','是','','已支付'),(2,'2026-04-26 06:41:02','YS011','TS2023007','曹县人民医院CT机','upload/yuyuequxiao_曹县人民医院CT机1.jpg,upload/yuyuequxiao_曹县人民医院CT机2.jpg,upload/yuyuequxiao_曹县人民医院CT机3.jpg','医疗设备',12,4,48,'无取消要求','004','28','2025-08-30 18:07:51','是','','已支付'),(3,'2026-04-26 06:41:02','YS012','XL2023014','寿光市圣城街道社区卫生服务中心雾化器','upload/yuyuequxiao_寿光市圣城街道社区卫生服务中心雾化器1.jpg,upload/yuyuequxiao_寿光市圣城街道社区卫生服务中心雾化器2.jpg,upload/yuyuequxiao_寿光市圣城街道社区卫生服务中心雾化器3.jpg','治疗设备',32,12,384,'无取消要求','005','24','2025-03-14 08:45:12','是','','已支付'),(4,'2026-04-26 06:41:02','YS014','BK2023013','临朐县冶源街道中心卫生院救护车','upload/yuyuequxiao_临朐县冶源街道中心卫生院救护车1.jpg,upload/yuyuequxiao_临朐县冶源街道中心卫生院救护车2.jpg,upload/yuyuequxiao_临朐县冶源街道中心卫生院救护车3.jpg','急救设备',20,7,140,'无取消要求','003','27','2025-08-23 13:41:26','是','','已支付'),(5,'2026-04-26 06:41:02','YS009','JL2023009','莱州市平里店镇专科骨科门诊康复训练器','upload/yuyuequxiao_莱州市平里店镇专科骨科门诊康复训练器1.jpg,upload/yuyuequxiao_莱州市平里店镇专科骨科门诊康复训练器2.jpg,upload/yuyuequxiao_莱州市平里店镇专科骨科门诊康复训练器3.jpg','康复设备',10,3,30,'预约后72小时内可取消','008','21','2025-08-10 13:21:09','是','','已支付'),(6,'2026-04-26 06:41:02','YS015','WL2023020','肥城市新城街道民营协和诊所用诊查床','upload/yuyuequxiao_肥城市新城街道民营协和诊所用诊查床1.jpg,upload/yuyuequxiao_肥城市新城街道民营协和诊所用诊查床2.jpg,upload/yuyuequxiao_肥城市新城街道民营协和诊所用诊查床3.jpg','检查设备',30,11,330,'预约后48小时内可取消','001','26','2022-09-10 15:19:42','是','','已支付'),(7,'2026-04-26 06:41:02','YS003','QY2023017','滕州市柴胡店镇刘村卫生室听诊器','upload/yuyuequxiao_滕州市柴胡店镇刘村卫生室听诊器1.jpg,upload/yuyuequxiao_滕州市柴胡店镇刘村卫生室听诊器2.jpg,upload/yuyuequxiao_滕州市柴胡店镇刘村卫生室听诊器3.jpg','基础器械',22,8,176,'预约后48小时内可取消','002','25','2025-01-18 10:53:24','是','','已支付'),(8,'2026-04-26 06:41:02','YS020','SV2023004','肥城市新城街道民营协和诊所血压计','upload/yuyuequxiao_肥城市新城街道民营协和诊所血压计1.jpg,upload/yuyuequxiao_肥城市新城街道民营协和诊所血压计2.jpg,upload/yuyuequxiao_肥城市新城街道民营协和诊所血压计3.jpg','基础设备',25,9,225,'无取消要求','006','23','2024-09-02 15:47:31','是','','已支付');
/*!40000 ALTER TABLE `yuyuequxiao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zhenliaofangan`
--

DROP TABLE IF EXISTS `zhenliaofangan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zhenliaofangan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiuzhenkahao` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '就诊卡号',
  `yonghuzhanghao` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户账号',
  `yonghuxingming` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户姓名',
  `bingrenzhusu` longtext COLLATE utf8mb4_unicode_ci COMMENT '病人主诉',
  `jiwangshi` longtext COLLATE utf8mb4_unicode_ci COMMENT '既往史',
  `yaominshi` longtext COLLATE utf8mb4_unicode_ci COMMENT '药敏史',
  `zhenduanjieguo` longtext COLLATE utf8mb4_unicode_ci COMMENT '诊断结果',
  `zhiliaofangan` longtext COLLATE utf8mb4_unicode_ci COMMENT '治疗方案',
  `jiankangjianyi` longtext COLLATE utf8mb4_unicode_ci COMMENT '健康建议',
  `chufangxinxi` longtext COLLATE utf8mb4_unicode_ci COMMENT '处方信息',
  `yizhu` longtext COLLATE utf8mb4_unicode_ci COMMENT '医嘱',
  `yishenggonghao` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '医生工号',
  `yishengxingming` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '医生姓名',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  PRIMARY KEY (`id`),
  UNIQUE KEY `jiuzhenkahao` (`jiuzhenkahao`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='诊疗方案';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zhenliaofangan`
--

LOCK TABLES `zhenliaofangan` WRITE;
/*!40000 ALTER TABLE `zhenliaofangan` DISABLE KEYS */;
INSERT INTO `zhenliaofangan` VALUES (1,'2026-04-26 06:41:02','SK001','007','赵阳','关节红肿疼痛3天','否认类风湿关节炎病史否认外伤史','无','痛风性关节炎','药物治疗加护理','饮食均衡运动舒缓定期体检','遵医嘱服用抗感染药物，监测体温，若＞38.5℃及时物理降温','口服急支糖浆、乙酰半胱氨酸泡腾片，适当吸氧','22','陈然','upload/zhenliaofangan_touxiang1.jpg,upload/zhenliaofangan_touxiang2.jpg,upload/zhenliaofangan_touxiang3.jpg'),(2,'2026-04-26 06:41:02','SK008','004','刘婷','口苦口臭1个月','否认消化系统疾病史否认口腔疾病史','青霉素','牙龈炎幽门螺杆菌感染','药物治疗加饮食调理','饮食清淡运动舒缓保持心情愉悦','遵医嘱服用止咳祛痰药物，若出现发热、咳黄痰及时加用抗感染药物','口服硝酸甘油片（急救用）、阿托伐他汀钙片、阿司匹林肠溶片','28','周宇恒','upload/zhenliaofangan_touxiang2.jpg,upload/zhenliaofangan_touxiang3.jpg,upload/zhenliaofangan_touxiang4.jpg'),(3,'2026-04-26 06:41:02','SK013','005','李明','耳鸣3天','否认耳部疾病史否认高血压糖尿病病史','花粉','神经性耳鸣','药物治疗加康复训练','饮食清淡易消化运动循序渐进注意保暖','及时到牙科就诊处理患牙，疼痛明显时遵医嘱服用止痛药物','口服甲钴胺片、银杏叶提取物片，避免噪音刺激','24','林文','upload/zhenliaofangan_touxiang3.jpg,upload/zhenliaofangan_touxiang4.jpg,upload/zhenliaofangan_touxiang5.jpg'),(4,'2026-04-26 06:41:02','SK004','003','张伟','失眠1周','否认精神疾病史否认慢性病史','无','神经衰弱','药物治疗加休息','运动适度饮食合理保持情绪稳定','遵医嘱规律服用抗风湿药物，监测关节肿胀、疼痛变化','口服布洛芬缓释胶囊，静脉输注甘露醇，配合腰椎牵引治疗','27','高欣','upload/zhenliaofangan_touxiang4.jpg,upload/zhenliaofangan_touxiang5.jpg,upload/zhenliaofangan_touxiang6.jpg'),(5,'2026-04-26 06:41:02','SK002','008','孙悦','牙痛3天','否认牙科疾病史否认慢性病史','无','龋齿牙髓炎','药物治疗加心脏监护','运动结合兴趣爱好饮食清淡少油少盐','多休息，避免劳累，遵医嘱服用止血调节药物','复方氯己定含漱液含漱，洗牙清除牙结石，口服维生素C','21','梁行高','upload/zhenliaofangan_touxiang5.jpg,upload/zhenliaofangan_touxiang6.jpg,upload/zhenliaofangan_touxiang7.jpg'),(6,'2026-04-26 06:41:02','SK005','001','陈浩','皮肤瘙痒伴皮疹1天','否认皮肤病史否认其他慢性病史','无','过敏性皮炎','药物治疗加康复训练','饮食软烂易消化运动量力而行保持社交活动','遵医嘱外用活血化瘀药膏，疼痛缓解后逐步进行膝关节屈伸练习','口服塞来昔布胶囊，外用活血止痛膏，避免关节负重','26','马子轩','upload/zhenliaofangan_touxiang6.jpg,upload/zhenliaofangan_touxiang7.jpg,upload/zhenliaofangan_touxiang8.jpg'),(7,'2026-04-26 06:41:02','SK006','002','林玥','关节疼痛1周','否认类风湿关节炎等病史否认外伤史','无','膝关节软骨损伤','药物治疗加康复训练','饮食均衡营养运动选择温和项目注意防寒保暖','每日监测空腹+餐后2小时血糖，记录数值并反馈给医生','静脉输注头孢曲松钠、氯化钾，口服蒙脱石散','25','徐行达','upload/zhenliaofangan_touxiang7.jpg,upload/zhenliaofangan_touxiang8.jpg,upload/zhenliaofangan_touxiang1.jpg'),(8,'2026-04-26 06:41:02','SK009','006','王佳','咳嗽5天伴咽痛','否认既往慢性病史否认手术外伤史','芒果','急性咽喉炎','药物治疗加护理','运动前热身饮食低糖低脂保持心态平和','遵医嘱足疗程服用抗感染药物，定期复查尿常规','口服奥美拉唑肠溶胶囊、阿莫西林、克拉霉素，疗程14天','23','赵思雨','upload/zhenliaofangan_touxiang8.jpg,upload/zhenliaofangan_touxiang1.jpg,upload/zhenliaofangan_touxiang2.jpg');
/*!40000 ALTER TABLE `zhenliaofangan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ziyuanfenlei`
--

DROP TABLE IF EXISTS `ziyuanfenlei`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ziyuanfenlei` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ziyuanfenlei` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源分类',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ziyuanfenlei` (`ziyuanfenlei`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='资源分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ziyuanfenlei`
--

LOCK TABLES `ziyuanfenlei` WRITE;
/*!40000 ALTER TABLE `ziyuanfenlei` DISABLE KEYS */;
INSERT INTO `ziyuanfenlei` VALUES (1,'2026-04-26 06:41:02','病房器械'),(2,'2026-04-26 06:41:02','医疗设备'),(3,'2026-04-26 06:41:02','治疗设备'),(4,'2026-04-26 06:41:02','急救设备'),(5,'2026-04-26 06:41:02','康复设备'),(6,'2026-04-26 06:41:02','检查设备'),(7,'2026-04-26 06:41:02','基础器械'),(8,'2026-04-26 06:41:02','基础设备');
/*!40000 ALTER TABLE `ziyuanfenlei` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ziyuanshenqing`
--

DROP TABLE IF EXISTS `ziyuanshenqing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ziyuanshenqing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `shenqingbianhao` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '申请编号',
  `ziyuanbianhao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源编号',
  `ziyuanmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源名称',
  `ziyuantupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '资源图片',
  `ziyuanfenlei` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源分类',
  `ziyuanjiage` double DEFAULT NULL COMMENT '资源价格',
  `ziyuanshuliang` int(11) DEFAULT NULL COMMENT '申请数量',
  `shenqingfeiyong` double DEFAULT NULL COMMENT '申请费用',
  `shenqingyuanyin` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '申请原因',
  `yonghuzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户账号',
  `yishenggonghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '医生工号',
  `shenqingshijian` datetime DEFAULT NULL COMMENT '申请时间',
  `sfsh` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext COLLATE utf8mb4_unicode_ci COMMENT '审核回复',
  `ispay` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '未支付' COMMENT '是否支付',
  PRIMARY KEY (`id`),
  UNIQUE KEY `shenqingbianhao` (`shenqingbianhao`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='资源申请';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ziyuanshenqing`
--

LOCK TABLES `ziyuanshenqing` WRITE;
/*!40000 ALTER TABLE `ziyuanshenqing` DISABLE KEYS */;
INSERT INTO `ziyuanshenqing` VALUES (1,'2026-04-26 06:41:02','YS004','BS2023001','青州市谭坊镇普通卫生院输液架','upload/ziyuanshenqing_青州市谭坊镇普通卫生院输液架1.jpg,upload/ziyuanshenqing_青州市谭坊镇普通卫生院输液架2.jpg,upload/ziyuanshenqing_青州市谭坊镇普通卫生院输液架3.jpg','病房器械',15,5,75,'我院无冠脉造影检查设备，无法明确冠脉病变程度，无法评估是否需要支架植入治疗','007','22','2023-06-19 18:42:17','是','','已支付'),(2,'2026-04-26 06:41:02','YS011','TS2023007','曹县人民医院CT机','upload/ziyuanshenqing_曹县人民医院CT机1.jpg,upload/ziyuanshenqing_曹县人民医院CT机2.jpg,upload/ziyuanshenqing_曹县人民医院CT机3.jpg','医疗设备',12,4,48,'我院无血液透析中心，无法为患者提供规律透析治疗，且缺乏肾内科专科医师评估肾移植指征','004','28','2025-08-30 18:07:51','是','','已支付'),(3,'2026-04-26 06:41:02','YS012','XL2023014','寿光市圣城街道社区卫生服务中心雾化器','upload/ziyuanshenqing_寿光市圣城街道社区卫生服务中心雾化器1.jpg,upload/ziyuanshenqing_寿光市圣城街道社区卫生服务中心雾化器2.jpg,upload/ziyuanshenqing_寿光市圣城街道社区卫生服务中心雾化器3.jpg','治疗设备',32,12,384,'我院无儿科重症监护病房，无法监测患儿神经系统及循环系统功能变化，缺乏重症手足口病救治经验','005','24','2025-03-14 08:45:12','是','','已支付'),(4,'2026-04-26 06:41:02','YS014','BK2023013','临朐县冶源街道中心卫生院救护车','upload/ziyuanshenqing_临朐县冶源街道中心卫生院救护车1.jpg,upload/ziyuanshenqing_临朐县冶源街道中心卫生院救护车2.jpg,upload/ziyuanshenqing_临朐县冶源街道中心卫生院救护车3.jpg','急救设备',20,7,140,'我院无法开展过敏原检测及免疫功能评估，且缺乏儿科风湿免疫专科医师制定治疗方案','003','27','2025-08-23 13:41:26','是','','已支付'),(5,'2026-04-26 06:41:02','YS009','JL2023009','莱州市平里店镇专科骨科门诊康复训练器','upload/ziyuanshenqing_莱州市平里店镇专科骨科门诊康复训练器1.jpg,upload/ziyuanshenqing_莱州市平里店镇专科骨科门诊康复训练器2.jpg,upload/ziyuanshenqing_莱州市平里店镇专科骨科门诊康复训练器3.jpg','康复设备',10,3,30,'我院无呼吸专科门诊及肺功能检查设备，无法进行哮喘病情分级及制定长期控制方案','008','21','2025-08-10 13:21:09','是','','已支付'),(6,'2026-04-26 06:41:02','YS015','WL2023020','肥城市新城街道民营协和诊所用诊查床','upload/ziyuanshenqing_肥城市新城街道民营协和诊所用诊查床1.jpg,upload/ziyuanshenqing_肥城市新城街道民营协和诊所用诊查床2.jpg,upload/ziyuanshenqing_肥城市新城街道民营协和诊所用诊查床3.jpg','检查设备',30,11,330,'初步诊断急性阑尾炎，需进一步完善腹部CT检查并评估手术指征，我院不具备腹腔镜手术条件','001','26','2022-09-10 15:19:42','是','','已支付'),(7,'2026-04-26 06:41:02','YS003','QY2023017','滕州市柴胡店镇刘村卫生室听诊器','upload/ziyuanshenqing_滕州市柴胡店镇刘村卫生室听诊器1.jpg,upload/ziyuanshenqing_滕州市柴胡店镇刘村卫生室听诊器2.jpg,upload/ziyuanshenqing_滕州市柴胡店镇刘村卫生室听诊器3.jpg','基础器械',22,8,176,'我院无神经内科专科医师及溶栓治疗资质，无法在溶栓时间窗内进行静脉溶栓治疗','002','25','2025-01-18 10:53:24','是','','已支付'),(8,'2026-04-26 06:41:02','YS020','SV2023004','肥城市新城街道民营协和诊所血压计','upload/ziyuanshenqing_肥城市新城街道民营协和诊所血压计1.jpg,upload/ziyuanshenqing_肥城市新城街道民营协和诊所血压计2.jpg,upload/ziyuanshenqing_肥城市新城街道民营协和诊所血压计3.jpg','基础设备',25,9,225,'我院缺乏皮肤科专科药物（如生物制剂）及光疗设备，无法制定系统治疗方案','006','23','2024-09-02 15:47:31','是','','已支付');
/*!40000 ALTER TABLE `ziyuanshenqing` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-27 16:05:17
