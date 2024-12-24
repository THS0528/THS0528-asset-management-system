-- MySQL dump 10.13  Distrib 8.1.0, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: asset_management
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `asset`
--

DROP TABLE IF EXISTS `asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asset` (
  `ASSET_CODE` varchar(16) NOT NULL COMMENT '资产编码',
  `ASSET_ID` int NOT NULL AUTO_INCREMENT,
  `ASSET_NAME` varchar(30) NOT NULL COMMENT '资产名称',
  `CLASS_ID` varchar(10) NOT NULL COMMENT '逻辑外键资产类别id',
  `SUPPLIER_ID` int NOT NULL COMMENT '逻辑外键供应商id',
  `BRAND_ID` varchar(10) NOT NULL COMMENT '逻辑外键品牌id',
  `LOCATION_ID` int NOT NULL COMMENT '逻辑外键存放地点id',
  `DATE` date NOT NULL COMMENT '资产入库日期',
  `STATUS` varchar(6) NOT NULL COMMENT '资产状态',
  PRIMARY KEY (`ASSET_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资产表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset`
--

LOCK TABLES `asset` WRITE;
/*!40000 ALTER TABLE `asset` DISABLE KEYS */;
INSERT INTO `asset` VALUES ('zc20240423093008',7,'资产','3',2,'1',1,'2024-04-23','在库'),('zc20240506080029',8,'资产入库','1',3,'1',1,'2024-05-06','在库'),('zc20241224173821',9,'资产入库','1',4,'1',2,'2024-12-24','在库');
/*!40000 ALTER TABLE `asset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset_class`
--

DROP TABLE IF EXISTS `asset_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asset_class` (
  `CLASS_ID` int NOT NULL AUTO_INCREMENT COMMENT '资产类别编码',
  `CLASS_CODE` varchar(10) NOT NULL,
  `CLASS_NAME` varchar(30) NOT NULL COMMENT '资产名称',
  `STATUS` int NOT NULL COMMENT '资产状态',
  `USER_ID` int DEFAULT NULL COMMENT '逻辑外键用户id',
  PRIMARY KEY (`CLASS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资产类别表(1-正常，0-停用)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_class`
--

LOCK TABLES `asset_class` WRITE;
/*!40000 ALTER TABLE `asset_class` DISABLE KEYS */;
INSERT INTO `asset_class` VALUES (1,'zc001','资产1',1,1),(2,'zclb001','资产类别',1,1),(3,'zclb001','资产类别1',1,1);
/*!40000 ALTER TABLE `asset_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset_repayment`
--

DROP TABLE IF EXISTS `asset_repayment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asset_repayment` (
  `BORROWED_CODE` varchar(16) NOT NULL COMMENT '借用单号（主键）',
  `ASSET_ID` varchar(16) NOT NULL COMMENT '资产编号',
  `LOAN_DATE` date NOT NULL COMMENT '借用日期',
  `SECTION_ID` int NOT NULL COMMENT '逻辑外键部门id（借用部门）',
  `CAUSE` text NOT NULL COMMENT '借用原因',
  `BACK_DATE` date DEFAULT NULL COMMENT '归还日期',
  `BACK_REMARK` text COMMENT '归还备注',
  `status` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资产借还表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_repayment`
--

LOCK TABLES `asset_repayment` WRITE;
/*!40000 ALTER TABLE `asset_repayment` DISABLE KEYS */;
INSERT INTO `asset_repayment` VALUES ('jy20240423111335','7','2024-04-23',2,'jy','2024-04-25',NULL,1),('jy20240425100710','7','2024-04-25',2,'借用','2024-04-25',NULL,1),('jy20240425161333','7','2024-04-25',2,'123','2024-04-25','归还',1),('jy20240506080221','8','2024-05-06',2,'原因','2024-05-06','备注',1);
/*!40000 ALTER TABLE `asset_repayment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset_requisition`
--

DROP TABLE IF EXISTS `asset_requisition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asset_requisition` (
  `ASSET_NAME` varchar(60) NOT NULL COMMENT '资产名称',
  `CLASS_ID` int NOT NULL COMMENT '逻辑外键资产类型id',
  `SUPPLIER_ID` int NOT NULL COMMENT '逻辑外键供应商id',
  `BRAND_ID` int NOT NULL COMMENT '逻辑外键品牌id',
  `ARGUMENT` text NOT NULL COMMENT '申购理由',
  `BUDGET` decimal(12,2) NOT NULL COMMENT '预算两位小数',
  `RESULT` varchar(9) DEFAULT NULL COMMENT '审批结果',
  `id` int NOT NULL AUTO_INCREMENT,
  `APPLICANT` varchar(10) DEFAULT NULL COMMENT '申请人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资产申购表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_requisition`
--

LOCK TABLES `asset_requisition` WRITE;
/*!40000 ALTER TABLE `asset_requisition` DISABLE KEYS */;
INSERT INTO `asset_requisition` VALUES ('2',1,2,1,'申购理由',1.00,'审批通过',2,'user001'),('资产3',1,2,1,'申请理由',1.00,'待审批',3,'user001'),('资产一',1,3,1,'理由',1.00,'审批通过',4,'user001');
/*!40000 ALTER TABLE `asset_requisition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset_scrap`
--

DROP TABLE IF EXISTS `asset_scrap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asset_scrap` (
  `ID` int NOT NULL AUTO_INCREMENT COMMENT '资产报废id',
  `ASSET_ID` int NOT NULL COMMENT '逻辑外键资产编码（资产id）',
  `SCRAP_ID` int DEFAULT NULL COMMENT '逻辑外键报废方式id',
  `DATE` date NOT NULL COMMENT '报废日期',
  `CAUSE` text NOT NULL COMMENT '报废原因',
  `RESULT` varchar(9) DEFAULT NULL COMMENT '报废审批结果',
  `APPLICANT` varchar(10) DEFAULT NULL COMMENT '申请人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资产报废表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_scrap`
--

LOCK TABLES `asset_scrap` WRITE;
/*!40000 ALTER TABLE `asset_scrap` DISABLE KEYS */;
INSERT INTO `asset_scrap` VALUES (4,7,2,'2024-04-26','报废','审批不通过','user001'),(5,7,2,'2024-05-06','原因','审批不通过','user001');
/*!40000 ALTER TABLE `asset_scrap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `BRAND_CODE` varchar(10) NOT NULL COMMENT '资产编码',
  `BRAND_ID` int NOT NULL AUTO_INCREMENT,
  `BRAND_NAME` varchar(30) DEFAULT NULL COMMENT '资产名称',
  `STATUS` int DEFAULT NULL COMMENT '品牌状态',
  `USER_ID` int DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`BRAND_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='品牌表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES ('pinpai',1,'品牌',1,1);
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `LOCATION_ID` int NOT NULL AUTO_INCREMENT COMMENT '存放地点编码',
  `LOCATION_NAME` varchar(60) NOT NULL COMMENT '存放地点名称',
  `LOCATION_TYPE` varchar(30) NOT NULL COMMENT '存放地点类型',
  `LOCATION_DESCRIPTION` text COMMENT '存放地点说明',
  `STATUS` int NOT NULL COMMENT '存放地点状态',
  PRIMARY KEY (`LOCATION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='存放地点表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'cfdd','12','12',1),(2,'存放地点','类别','',1);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `PERMISSION_ID` int NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `PERMISSION_NAME` varchar(18) NOT NULL COMMENT '权限名称',
  `PERMISSION_PATH` varchar(30) NOT NULL COMMENT '权限路径',
  PRIMARY KEY (`PERMISSION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'资产类别管理','assetClass_list'),(2,'品牌管理','brand_list'),(3,'报废方式管理','scrapMode_list'),(4,'部门管理','section_list'),(5,'供应商管理','supplier_list'),(6,'存放地点管理','storagePlace_list'),(7,'资产入库','entry_list'),(8,'资产借还','assetRepayment_list'),(9,'资产申购','assetRequisition_list'),(10,'资产申购','assetRequisition_ld'),(11,'资产报废','assetScrap_list'),(12,'资产报废','assetScrap_ld');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `ROLE_ID` int NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `ROLE_NAME` varchar(18) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'库管员'),(2,'资产审批领导'),(3,'系统管理员');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permission` (
  `ID` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ROLE_ID` int NOT NULL COMMENT '逻辑外键角色id',
  `PERMISSION_ID` int NOT NULL COMMENT '逻辑外键权限id',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,11),(11,2,10),(12,2,12);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scrap_mode`
--

DROP TABLE IF EXISTS `scrap_mode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scrap_mode` (
  `SCRAP_CODE` varchar(10) NOT NULL COMMENT '报废方式编码',
  `SCRAP_ID` int NOT NULL AUTO_INCREMENT,
  `SCRAP_NAME` varchar(30) NOT NULL COMMENT '保费方式名称',
  `STATUS` int NOT NULL COMMENT '报废方式状态',
  PRIMARY KEY (`SCRAP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='报废方式表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scrap_mode`
--

LOCK TABLES `scrap_mode` WRITE;
/*!40000 ALTER TABLE `scrap_mode` DISABLE KEYS */;
INSERT INTO `scrap_mode` VALUES ('cs1',1,'cs',1),('sadas',2,'dasdas',1),('asdfas',3,'gfhdfgh',1);
/*!40000 ALTER TABLE `scrap_mode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `section` (
  `SECTION_CODE` varchar(10) NOT NULL COMMENT '部门编码',
  `SECTION_ID` int NOT NULL AUTO_INCREMENT,
  `SECTION_NAME` varchar(30) NOT NULL COMMENT '部门名称',
  `STATUS` int NOT NULL COMMENT '部门状态',
  PRIMARY KEY (`SECTION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` VALUES ('adsdas1',1,'adsdas',1),('dasdas',2,'dasdas',1),('asdas',3,'asdas',1);
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `SUPPLIER_ID` int NOT NULL AUTO_INCREMENT COMMENT '供应商id',
  `SUPPLIER_NAME` varchar(150) NOT NULL COMMENT '供应商名称',
  `SUPPLIER_TYPE` varchar(30) NOT NULL COMMENT '供应商类型',
  `LINKMAN` varchar(30) NOT NULL COMMENT '联系人',
  `MP_NUMBER` varchar(11) NOT NULL COMMENT '联系人手机号',
  `LOCATION` text NOT NULL COMMENT '地址',
  `STATUS` int NOT NULL COMMENT '供应商状态',
  PRIMARY KEY (`SUPPLIER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='供应商表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (4,'供应商','供应商类别','李总','13031333567','中国',1);
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `USER_ID` int NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(18) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(48) NOT NULL COMMENT '密码',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USER_pk2` (`USER_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'user001','123456'),(2,'ld001','123456');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `ID` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_ID` int NOT NULL COMMENT '逻辑外键用户id',
  `ROLE_ID` int NOT NULL COMMENT '逻辑外键角色id',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户——角色关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,1),(2,2,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-24 23:11:43
