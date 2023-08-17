-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: lms
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `book_details`
--

DROP TABLE IF EXISTS `book_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_details` (
  `Book_ID` varchar(6) NOT NULL,
  `Book_Name` varchar(45) NOT NULL,
  `Author` varchar(45) NOT NULL,
  `Category` varchar(45) NOT NULL,
  `Price` varchar(45) DEFAULT NULL,
  `NoOfPages` varchar(45) DEFAULT NULL,
  `Location` varchar(45) NOT NULL,
  PRIMARY KEY (`Book_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_details`
--

LOCK TABLES `book_details` WRITE;
/*!40000 ALTER TABLE `book_details` DISABLE KEYS */;
INSERT INTO `book_details` VALUES ('A0002','SQL Programming','Thinuja','Programming','2000.00','200','P05'),('A028','Madol duwa','Martin Wikramasinhe','nowel','510.00','470','N09'),('Adv5','book5','kk','Adventure','500','10','A50'),('b4','sdvsfv','sdfsdf','fgbfgb','140','50','f5'),('c456','kasun','asdfa','asfaf','100','10','c5'),('s4','hffhf','fhfh','vbvbv','150','10','g4');
/*!40000 ALTER TABLE `book_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `user_ID` int NOT NULL AUTO_INCREMENT,
  `FName` varchar(45) NOT NULL,
  `LName` varchar(45) NOT NULL,
  `BOD` date DEFAULT NULL,
  `UserName` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Role` varchar(45) NOT NULL,
  PRIMARY KEY (`user_ID`),
  UNIQUE KEY `User_ID_UNIQUE` (`user_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'Thinuja','Hettiarachchi','2001-09-27','Thinuj','123','Admin'),(2,'Tiran','Hettiarachchi','1998-03-11','Tiran','456','Admin'),(3,'Kasun','Priyadharashana','2000-05-15','Kasun','789','Admin'),(4,'Ravindu','Dilshan','2000-08-14','Ravi','1234','Admin'),(5,'Manula','DDD','2000-02-11','Manu','852','Admin'),(6,'Maheesha','Nethmina','2000-03-16','Mahee','147','Admin'),(7,'pasidu','jayasinha','2000-08-10','pasidu','1234','User'),(8,'chamika','ascasc','2000-08-15','chamika','258','User');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sypassword`
--

DROP TABLE IF EXISTS `sypassword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sypassword` (
  `password` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sypassword`
--

LOCK TABLES `sypassword` WRITE;
/*!40000 ALTER TABLE `sypassword` DISABLE KEYS */;
INSERT INTO `sypassword` VALUES ('Project123');
/*!40000 ALTER TABLE `sypassword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_books`
--

DROP TABLE IF EXISTS `user_books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_books` (
  `UserId` int NOT NULL,
  `BookID` varchar(6) NOT NULL,
  `date_of_issued` date DEFAULT NULL,
  `date_of_resubmition` date DEFAULT NULL,
  `date_of_recived` date DEFAULT NULL,
  `amount_of_fine` decimal(5,1) DEFAULT NULL,
  `status` varchar(10) DEFAULT 'pending',
  PRIMARY KEY (`UserId`,`BookID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_books`
--

LOCK TABLES `user_books` WRITE;
/*!40000 ALTER TABLE `user_books` DISABLE KEYS */;
INSERT INTO `user_books` VALUES (3,'b4','2023-08-04',NULL,'2023-08-11',0.0,'pending'),(3,'c456','2023-08-04',NULL,NULL,0.0,'pending'),(7,'A028','2023-08-13','2023-08-20','2023-08-16',0.0,'ok'),(7,'b4','2023-08-16','2023-08-20','2023-08-16',0.0,'ok');
/*!40000 ALTER TABLE `user_books` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-17 18:08:30
