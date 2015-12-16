-- MySQL dump 10.13  Distrib 5.5.13, for Win64 (x86)
--
-- Host: localhost    Database: saa
-- ------------------------------------------------------
-- Server version	5.5.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assento`
--

DROP TABLE IF EXISTS `assento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assento` (
  `cod_assento` int(11) NOT NULL AUTO_INCREMENT,
  `linha_assento` int(11) DEFAULT NULL,
  `numero_assento` int(11) DEFAULT NULL,
  `estado_assento` tinyint(4) DEFAULT NULL,
  `voo_cod` int(11) DEFAULT NULL,
  `classe_cod` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod_assento`),
  KEY `classe_cod_idx` (`classe_cod`),
  CONSTRAINT `classe_cod` FOREIGN KEY (`classe_cod`) REFERENCES `classe` (`cod_classe`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cod_voo` FOREIGN KEY (`cod_assento`) REFERENCES `voo` (`idvoo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assento`
--

LOCK TABLES `assento` WRITE;
/*!40000 ALTER TABLE `assento` DISABLE KEYS */;
INSERT INTO `assento` VALUES (1,1,1,0,1,1),(2,4,4,0,1,2),(3,4,5,0,3,1);
/*!40000 ALTER TABLE `assento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assvoo`
--

DROP TABLE IF EXISTS `assvoo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assvoo` (
  `assento_cod` int(11) NOT NULL,
  `voo_cod` int(11) NOT NULL,
  PRIMARY KEY (`assento_cod`,`voo_cod`),
  KEY `cod_voo_idx` (`voo_cod`),
  CONSTRAINT `assento_cod` FOREIGN KEY (`assento_cod`) REFERENCES `assento` (`cod_assento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `voo_cod` FOREIGN KEY (`voo_cod`) REFERENCES `voo` (`idvoo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assvoo`
--

LOCK TABLES `assvoo` WRITE;
/*!40000 ALTER TABLE `assvoo` DISABLE KEYS */;
INSERT INTO `assvoo` VALUES (1,1),(1,2),(1,3);
/*!40000 ALTER TABLE `assvoo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aviao`
--

DROP TABLE IF EXISTS `aviao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aviao` (
  `cod_aviao` int(11) NOT NULL AUTO_INCREMENT,
  `modelo_aviao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod_aviao`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aviao`
--

LOCK TABLES `aviao` WRITE;
/*!40000 ALTER TABLE `aviao` DISABLE KEYS */;
INSERT INTO `aviao` VALUES (1,'Air Caraíbes - A330-300'),(2,'Super Constelation L-1049 - TAP'),(3,'Swiss Air Ambulance - Challanger 604'),(4,'SATA AIR AÇORES - Bombardier DASH 200'),(5,'Sata Air Açores: Bombardier DHC 8 Q400'),(6,'ArkeFly - Boeing 767-300'),(7,'Boeing 777-200 da Aeroméxico.');
/*!40000 ALTER TABLE `aviao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bilhete`
--

DROP TABLE IF EXISTS `bilhete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bilhete` (
  `cliente_cod` int(11) NOT NULL,
  `assento_cod` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `voo_cod` int(11) DEFAULT NULL,
  PRIMARY KEY (`cliente_cod`,`assento_cod`,`data`),
  KEY `cod_assento_idx` (`assento_cod`),
  KEY `cod_voo_idx` (`voo_cod`),
  CONSTRAINT `cod_assento` FOREIGN KEY (`assento_cod`) REFERENCES `assento` (`cod_assento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cod_cliente` FOREIGN KEY (`cliente_cod`) REFERENCES `cliente` (`cod_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `voo_id` FOREIGN KEY (`voo_cod`) REFERENCES `voo` (`idvoo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bilhete`
--

LOCK TABLES `bilhete` WRITE;
/*!40000 ALTER TABLE `bilhete` DISABLE KEYS */;
/*!40000 ALTER TABLE `bilhete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classe`
--

DROP TABLE IF EXISTS `classe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classe` (
  `cod_classe` int(11) NOT NULL,
  `nome_classe` varchar(45) DEFAULT NULL,
  `preco_classe` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`cod_classe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classe`
--

LOCK TABLES `classe` WRITE;
/*!40000 ALTER TABLE `classe` DISABLE KEYS */;
INSERT INTO `classe` VALUES (1,'Economica',1000),(2,'Alta',4000);
/*!40000 ALTER TABLE `classe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `cod_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome_cliente` varchar(45) NOT NULL,
  `apelido_cliente` varchar(45) NOT NULL,
  `morada_cliente` varchar(45) DEFAULT NULL,
  `data_nasc_cliente` date DEFAULT NULL,
  `email_cliente` varchar(45) DEFAULT NULL,
  `senha_cliente` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Hasler','Choo','Alto-Mae','1915-01-01','hasler.choo@isutc.transcom.co.mz','choo'),(2,'Diogo','Amaral','Teste','2015-11-09','diogo.amaral@hotmail.com','teste'),(4,'Teste','teste','teste','2015-11-10','teste@testre','1234'),(5,'Teste','teste','teste','2015-11-10','teste@testrew','1234'),(8,'Teste','Teste','Teste','2015-11-03','Teste@teste.co.mz','12345'),(9,'Narciso','Matos','teste','2015-11-09','narciso.matos@isutc.transcom.co.mz','teste'),(21,'Teste','Teste','Teste','1915-01-29','teste@teste.com','teste'),(25,'Tewste','Testesd','TEste','2015-11-03','yas@yas.com','1234');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localizacao`
--

DROP TABLE IF EXISTS `localizacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localizacao` (
  `cod_local` int(11) NOT NULL AUTO_INCREMENT,
  `longitude` decimal(10,0) DEFAULT NULL,
  `latitude` decimal(10,0) DEFAULT NULL,
  `nome_local` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod_local`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localizacao`
--

LOCK TABLES `localizacao` WRITE;
/*!40000 ALTER TABLE `localizacao` DISABLE KEYS */;
INSERT INTO `localizacao` VALUES (1,33,-26,'Maputo'),(2,34,-16,'Tete '),(3,-8,39,'Portugal'),(4,-52,-14,'Brasil'),(5,104,36,'China'),(6,138,36,'Japao');
/*!40000 ALTER TABLE `localizacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voo`
--

DROP TABLE IF EXISTS `voo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `voo` (
  `idvoo` int(11) NOT NULL,
  `local_fim` int(11) DEFAULT NULL,
  `local_inicio` int(11) DEFAULT NULL,
  `data_voo` datetime DEFAULT NULL,
  `aviao_cod` int(11) DEFAULT NULL,
  `hora_voo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idvoo`),
  KEY `aviao_cod_idx` (`aviao_cod`),
  KEY `local_fim_idx` (`local_fim`),
  KEY `local_inicio_idx` (`local_inicio`),
  CONSTRAINT `aviao_cod` FOREIGN KEY (`aviao_cod`) REFERENCES `aviao` (`cod_aviao`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `local_fim` FOREIGN KEY (`local_fim`) REFERENCES `localizacao` (`cod_local`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `local_inicio` FOREIGN KEY (`local_inicio`) REFERENCES `localizacao` (`cod_local`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voo`
--

LOCK TABLES `voo` WRITE;
/*!40000 ALTER TABLE `voo` DISABLE KEYS */;
INSERT INTO `voo` VALUES (1,1,2,'2015-12-01 00:00:00',1,'12:00'),(2,4,2,'2015-10-08 00:00:00',2,'11:00'),(3,5,6,'2015-11-10 00:00:00',2,'10:00');
/*!40000 ALTER TABLE `voo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-16 13:39:33
