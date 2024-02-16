-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: cafe
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `p_num` int NOT NULL AUTO_INCREMENT,
  `p_title` varchar(50) NOT NULL,
  `p_date` datetime NOT NULL,
  `p_content` longtext,
  `p_b_num` int NOT NULL,
  `p_u_id` varchar(12) NOT NULL,
  PRIMARY KEY (`p_num`),
  KEY `FK_board_TO_post_1` (`p_b_num`),
  KEY `FK_user_TO_post_1` (`p_u_id`),
  CONSTRAINT `FK_board_TO_post_1` FOREIGN KEY (`p_b_num`) REFERENCES `board` (`b_num`),
  CONSTRAINT `FK_user_TO_post_1` FOREIGN KEY (`p_u_id`) REFERENCES `user` (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'안녕하세요.','2024-02-15 19:52:01','안녕하세요 홍길동입니다.',2,'qwer1234'),(2,'백엔드 어렵네요','2024-02-15 20:00:46','저만 어렵나요...?',4,'qwer1234'),(3,'하이요','2024-02-15 20:01:31','여기가 자유게시판인가요?',3,'asdf1234'),(5,'여기 무슨 카페인가요','2024-02-15 20:03:02','KH가 뭔가요',3,'asdf1234'),(7,'가입인사드립니다!','2024-02-16 14:22:24','저는 임꺽정입니다. 앞으로 잘 부탁드립니다.',2,'asdf1234'),(8,'안녕하세요! 가입했습니다.','2024-02-16 14:26:03','가입인사 올려요~ 맘모스입니다^^',2,'zxcv9876'),(9,'세상에서 가장 긴 책의 이름이 무엇인지 아시나요? 클릭하시면 제가 알려드릴게요.','2024-02-16 14:30:43','바로 [The Life and Strange Surprizing Adventures of Robinson Crusoe, of York, Mariner: Who Lived Eight and Twenty Years, All Alone in an Un-inhabited Island on the Coast of America, Near the Mouth of the Great River of Oroonoque; Having Been Cast on Shore by Shipwreck, Wherein All the Men Perished but Himself. With an Account how he was at last as Strangely Deliver’d by Pyrates. Written by Himself.] 라는 책이에요. 정말 길죠? 해석하면 [조난을 당해 모든 선원이 사망하고 자신은 아메리카 대륙 오리노코 강 가까운 무인도 해변에서 28년 동안 홀로 살다가 마침내 기적적으로 해적선에 구출된 요크 출신 뱃사람 로빈슨 크루소가 그려낸 자신의 생애와 기이하고도 놀라운 모험 이야기] 라고 한답니다. 신기하죠?',3,'zxcv9876'),(10,'벌써 금요일이네요*^^*','2024-02-16 14:32:23','한 주가 이렇게 빠르게 지나갔습니다. 너무 신기하네요~ 다들 한 주 고생하셨습니다.',3,'zxcv9876'),(11,'날씨가 추워졌네요','2024-02-16 14:35:29','따듯했다가 다시 추워지다니...슬프네요',3,'qwer1234'),(12,'다음주에 비가 내린다고 합니다~','2024-02-16 14:35:58','다들 우산을 꼭 챙기세요!',3,'asdf1234'),(13,'2024년 2월 16일 별자리별 운세와 MBTI별 운세 그리고 혈액형별 운세','2024-02-16 15:57:00','뻥이지용~',7,'zxcv9876');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-16 16:04:09
