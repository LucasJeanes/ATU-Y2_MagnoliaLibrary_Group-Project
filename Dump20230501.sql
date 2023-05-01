CREATE DATABASE  IF NOT EXISTS `magnolia_rebornlib` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `magnolia_rebornlib`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: magnolia_rebornlib
-- ------------------------------------------------------
-- Server version	5.5.16

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `publication` int(11) NOT NULL,
  `rented` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'The EarthSea Quartet','Ursula LeGuin',1993,0),(2,'Wiseguy','Nicholas Pileggi',1985,0),(3,'The Hobbit','J. R. R. Tolkien',1937,1),(4,'The Magicians Nephew','C.S. Lewis',1955,0),(5,'Cosmos','Carl Sagan',1980,1),(6,'A Brief History of Time','Stephen Hawking',1988,0),(7,'Ego Is The Enemy','Ryan Holiday',2016,0),(8,'Christian Bible','Some Dude 2000 Years Ago',100,1),(9,'Eragon','Christopher Paolini',2002,0);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `computer`
--

DROP TABLE IF EXISTS `computer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `computer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `brand` varchar(45) NOT NULL,
  `details` varchar(45) NOT NULL,
  `memory` varchar(45) NOT NULL,
  `price` varchar(45) NOT NULL,
  `rented` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computer`
--

LOCK TABLES `computer` WRITE;
/*!40000 ALTER TABLE `computer` DISABLE KEYS */;
INSERT INTO `computer` VALUES (1,'LAPTOP-2TU2S35I','HP','Intel Core-(i7)','8Gig / 512Gig','1000',0),(2,'ASUS Zenbook Flip S13 UX371EA 13.3\"','ASUS','Intel Core-(i9)','16Gig / 1TB','1599',1),(3,'MacBook Air 13.3\" (2020)','APPLE','M1','8Gig / 256Gig','1599',0),(4,'MacBook Pro 13.3\" (2022)','APPLE','M1','16Gig / 1TB','1450',1),(5,'MacBook Air 13.3\" (2021)','APPLE','M2','8Gig / 256Gig','1699',1),(7,'ENVY x360 15.6\" 2 in 1 Laptop','HP','AMD Ryzen™ 7-(i7)','8Gig / 512Gig','990',0);
/*!40000 ALTER TABLE `computer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `music`
--

DROP TABLE IF EXISTS `music`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `music` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `track` varchar(45) NOT NULL,
  `genre` varchar(45) NOT NULL,
  `artist` varchar(45) NOT NULL,
  `publication` varchar(45) NOT NULL,
  `rented` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music`
--

LOCK TABLES `music` WRITE;
/*!40000 ALTER TABLE `music` DISABLE KEYS */;
INSERT INTO `music` VALUES (1,'Yellow','ALternative Rock','Coldplay','2000',0),(2,'Oliver Twist','UK Drill','ArrDee','2022',0),(3,'Make Your Own Kind Of Music','Pop','Cass Elliot','1969',0),(4,'Dancing in the Moonlight','Pop','Toploader','1999',1),(5,'Back on the Chain Gang','Rock','The Pretenders','1982',0),(6,'Strutter','Rock','Kiss','1974',0),(7,'Moonlight Sonata','Classical','Beethoven','1801',1),(8,'Talking To Myself','Indie/Alternative','Watsky','2016',0),(9,'The Woods','Indie/Folk','Hollow Coves','2020',0),(10,'Two Up','UK Drill','Central Cee','2021',0);
/*!40000 ALTER TABLE `music` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stationary`
--

DROP TABLE IF EXISTS `stationary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stationary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `user_discount` decimal(10,2) NOT NULL,
  `stock` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stationary`
--

LOCK TABLES `stationary` WRITE;
/*!40000 ALTER TABLE `stationary` DISABLE KEYS */;
INSERT INTO `stationary` VALUES (1,'Notebook','Spiral-bound notebook',4.99,0.00,59),(2,'Stapler','Standard stapler',9.99,0.05,25),(3,'Highlighter','Yellow highlighter',0.99,0.20,66),(4,'Binder','3-ring binder',12.99,0.99,25),(5,'Pencil','HB/2B/4B/6B',1.99,0.20,110),(7,'Pen','Blue ballpoint pen',1.99,0.10,100);
/*!40000 ALTER TABLE `stationary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  `age(year)` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Psul Lenon','123 S Main St.','815-858-1234','27'),(2,'Doug Smith','1000 Bartel Blvd.','815-256-1435','42'),(3,'David Newell','57 Samsonville Rd','815-626-3086','21'),(4,'Ross Armitage','Roscam Galway','0830229012','20'),(5,'Lucas Jeanes','Roscam Galway','0860773062','69');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-01 15:15:09
