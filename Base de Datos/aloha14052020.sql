-- MySQL dump 10.13  Distrib 5.5.11, for Win32 (x86)
--
-- Host: localhost    Database: aloha
-- ------------------------------------------------------
-- Server version	5.5.11

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
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `codigo` varchar(200) DEFAULT NULL,
  `nombre` varchar(200) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asociacion_grupo_consumo_horario_consumo`
--

DROP TABLE IF EXISTS `asociacion_grupo_consumo_horario_consumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asociacion_grupo_consumo_horario_consumo` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `id_grupo_consumo` int(10) NOT NULL,
  `id_horario_consumo` int(10) NOT NULL,
  `costo_consumo` int(10) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_grupo_consumo` (`id_grupo_consumo`),
  KEY `id_horario_consumo` (`id_horario_consumo`),
  CONSTRAINT `asociacion_grupo_consumo_horario_consumo_fk` FOREIGN KEY (`id_grupo_consumo`) REFERENCES `grupo_consumo` (`id`),
  CONSTRAINT `asociacion_grupo_consumo_horario_consumo_fk1` FOREIGN KEY (`id_horario_consumo`) REFERENCES `horario_consumo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asociacion_grupo_consumo_horario_consumo`
--

LOCK TABLES `asociacion_grupo_consumo_horario_consumo` WRITE;
/*!40000 ALTER TABLE `asociacion_grupo_consumo_horario_consumo` DISABLE KEYS */;
/*!40000 ALTER TABLE `asociacion_grupo_consumo_horario_consumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auditoria`
--

DROP TABLE IF EXISTS `auditoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auditoria` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `operacion` varchar(50) NOT NULL,
  `tabla` varchar(50) NOT NULL,
  `fecha` datetime NOT NULL,
  `id_usuario` int(10) NOT NULL,
  `registro_modificado` int(10) NOT NULL,
  `observacion` longtext,
  PRIMARY KEY (`id`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `auditoria_fk` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditoria`
--

LOCK TABLES `auditoria` WRITE;
/*!40000 ALTER TABLE `auditoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `auditoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autoriza_consumo`
--

DROP TABLE IF EXISTS `autoriza_consumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `autoriza_consumo` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `tipo_persona` varchar(50) DEFAULT NULL,
  `id_persona_que_autoriza` int(10) DEFAULT NULL,
  `id_persona_autorizada` int(10) DEFAULT NULL,
  `id_centro_costo` int(10) DEFAULT NULL,
  `fecha_autorizada` datetime DEFAULT NULL,
  `id_horario_consumo` int(10) DEFAULT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `cantidad_autorizada` int(10) DEFAULT NULL,
  `cantidad_consumida` int(10) DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `id_usuario` int(10) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_persona_que_autoriza` (`id_persona_que_autoriza`),
  KEY `id_persona_autorizada` (`id_persona_autorizada`),
  KEY `id_centro_costo` (`id_centro_costo`),
  KEY `id_horario_consumo` (`id_horario_consumo`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `autoriza_consumo_fk` FOREIGN KEY (`id_persona_que_autoriza`) REFERENCES `persona` (`id`),
  CONSTRAINT `autoriza_consumo_fk1` FOREIGN KEY (`id_persona_autorizada`) REFERENCES `persona` (`id`),
  CONSTRAINT `autoriza_consumo_fk2` FOREIGN KEY (`id_centro_costo`) REFERENCES `centro_costo` (`id`),
  CONSTRAINT `autoriza_consumo_fk3` FOREIGN KEY (`id_horario_consumo`) REFERENCES `horario_consumo` (`id`),
  CONSTRAINT `autoriza_consumo_fk4` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autoriza_consumo`
--

LOCK TABLES `autoriza_consumo` WRITE;
/*!40000 ALTER TABLE `autoriza_consumo` DISABLE KEYS */;
/*!40000 ALTER TABLE `autoriza_consumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargo`
--

DROP TABLE IF EXISTS `cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargo` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `codigo` varchar(200) DEFAULT NULL,
  `nombre` varchar(200) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargo_hoteleria`
--

DROP TABLE IF EXISTS `cargo_hoteleria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargo_hoteleria` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `tipo_cargo` varchar(200) DEFAULT NULL,
  `valor_cargo` int(10) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo_hoteleria`
--

LOCK TABLES `cargo_hoteleria` WRITE;
/*!40000 ALTER TABLE `cargo_hoteleria` DISABLE KEYS */;
/*!40000 ALTER TABLE `cargo_hoteleria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `centro_costo`
--

DROP TABLE IF EXISTS `centro_costo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `centro_costo` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `codigo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `centro_costo`
--

LOCK TABLES `centro_costo` WRITE;
/*!40000 ALTER TABLE `centro_costo` DISABLE KEYS */;
/*!40000 ALTER TABLE `centro_costo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ciudad` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `codigo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `id_departamento` int(10) NOT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_departamento` (`id_departamento`),
  CONSTRAINT `ciudad_fk` FOREIGN KEY (`id_departamento`) REFERENCES `departamento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumo`
--

DROP TABLE IF EXISTS `consumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consumo` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `id_persona` int(10) DEFAULT NULL,
  `id_centro_costo` int(10) DEFAULT NULL,
  `id_grupo_consumo` int(10) DEFAULT NULL,
  `id_horario_consumo` int(10) DEFAULT NULL,
  `fecha_consumo` datetime DEFAULT NULL,
  `dia_consumo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `id_persona` (`id_persona`),
  KEY `id_centro_costo` (`id_centro_costo`),
  KEY `id_grupo_consumo` (`id_grupo_consumo`),
  KEY `id_horario_consumo` (`id_horario_consumo`),
  CONSTRAINT `consumo_fk` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`),
  CONSTRAINT `consumo_fk1` FOREIGN KEY (`id_centro_costo`) REFERENCES `centro_costo` (`id`),
  CONSTRAINT `consumo_fk2` FOREIGN KEY (`id_grupo_consumo`) REFERENCES `grupo_consumo` (`id`),
  CONSTRAINT `consumo_fk3` FOREIGN KEY (`id_horario_consumo`) REFERENCES `horario_consumo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumo`
--

LOCK TABLES `consumo` WRITE;
/*!40000 ALTER TABLE `consumo` DISABLE KEYS */;
/*!40000 ALTER TABLE `consumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumo_hoteleria`
--

DROP TABLE IF EXISTS `consumo_hoteleria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consumo_hoteleria` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `id_persona` int(10) DEFAULT NULL,
  `cargo_persona` varchar(20) DEFAULT NULL,
  `id_centro_costo` int(10) DEFAULT NULL,
  `id_grupo_consumo` int(10) DEFAULT NULL,
  `id_horario_consumo` int(10) DEFAULT NULL,
  `id_cargo_hoteleria` int(10) DEFAULT NULL,
  `cargo_hoteleria_valor` varchar(200) DEFAULT NULL,
  `fecha_consumo` datetime DEFAULT NULL,
  `dia_consumo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_persona` (`id_persona`),
  KEY `id_centro_costo` (`id_centro_costo`),
  KEY `id_grupo_consumo` (`id_grupo_consumo`),
  KEY `id_horario_consumo` (`id_horario_consumo`),
  KEY `id_cargo_hoteleria` (`id_cargo_hoteleria`),
  CONSTRAINT `consumo_hoteleria_fk` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`),
  CONSTRAINT `consumo_hoteleria_fk1` FOREIGN KEY (`id_centro_costo`) REFERENCES `centro_costo` (`id`),
  CONSTRAINT `consumo_hoteleria_fk2` FOREIGN KEY (`id_grupo_consumo`) REFERENCES `grupo_consumo` (`id`),
  CONSTRAINT `consumo_hoteleria_fk3` FOREIGN KEY (`id_horario_consumo`) REFERENCES `horario_consumo` (`id`),
  CONSTRAINT `consumo_hoteleria_fk4` FOREIGN KEY (`id_cargo_hoteleria`) REFERENCES `cargo_hoteleria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumo_hoteleria`
--

LOCK TABLES `consumo_hoteleria` WRITE;
/*!40000 ALTER TABLE `consumo_hoteleria` DISABLE KEYS */;
/*!40000 ALTER TABLE `consumo_hoteleria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamento`
--

DROP TABLE IF EXISTS `departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departamento` (
  `id` int(10) NOT NULL COMMENT 'Llave primaria',
  `codigo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamento`
--

LOCK TABLES `departamento` WRITE;
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dependencia`
--

DROP TABLE IF EXISTS `dependencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dependencia` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `codigo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dependencia`
--

LOCK TABLES `dependencia` WRITE;
/*!40000 ALTER TABLE `dependencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `dependencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dispositivo`
--

DROP TABLE IF EXISTS `dispositivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dispositivo` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `numero` int(10) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `direccion_ip` varchar(50) DEFAULT NULL,
  `puerto` int(10) DEFAULT NULL,
  `modo` varchar(50) DEFAULT NULL,
  `ip_controladora` varchar(50) DEFAULT NULL,
  `puerto_controladora` int(10) DEFAULT NULL,
  `tipo_lector` varchar(50) DEFAULT NULL,
  `activo` varchar(2) DEFAULT NULL,
  `serie` varchar(50) DEFAULT NULL,
  `licencia` varchar(100) DEFAULT NULL,
  `impresora` varchar(100) DEFAULT NULL,
  `encabezado_impresion` varchar(50) DEFAULT NULL,
  `utiliza_menu` varchar(2) DEFAULT NULL,
  `evento` varchar(2) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dispositivo`
--

LOCK TABLES `dispositivo` WRITE;
/*!40000 ALTER TABLE `dispositivo` DISABLE KEYS */;
/*!40000 ALTER TABLE `dispositivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresa` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `nombre` varchar(255) NOT NULL,
  `nit` varchar(50) DEFAULT NULL,
  `direccion` varchar(2000) DEFAULT NULL,
  `contacto` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `ext` varchar(255) DEFAULT NULL,
  `ciudad` varchar(200) DEFAULT NULL,
  `observacion` longtext,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `festivo`
--

DROP TABLE IF EXISTS `festivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `festivo` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `fecha` date DEFAULT NULL,
  `nota` longtext,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `festivo`
--

LOCK TABLES `festivo` WRITE;
/*!40000 ALTER TABLE `festivo` DISABLE KEYS */;
/*!40000 ALTER TABLE `festivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcion`
--

DROP TABLE IF EXISTS `funcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcion` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `nombre` varchar(200) DEFAULT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  `codigo_reloj` varchar(20) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcion`
--

LOCK TABLES `funcion` WRITE;
/*!40000 ALTER TABLE `funcion` DISABLE KEYS */;
/*!40000 ALTER TABLE `funcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_consumo`
--

DROP TABLE IF EXISTS `grupo_consumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo_consumo` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `codigo` varchar(200) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_consumo`
--

LOCK TABLES `grupo_consumo` WRITE;
/*!40000 ALTER TABLE `grupo_consumo` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo_consumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_horario`
--

DROP TABLE IF EXISTS `grupo_horario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo_horario` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `codigo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_horario`
--

LOCK TABLES `grupo_horario` WRITE;
/*!40000 ALTER TABLE `grupo_horario` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo_horario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_horario_horario`
--

DROP TABLE IF EXISTS `grupo_horario_horario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo_horario_horario` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `id_grupo_horario` int(10) NOT NULL,
  `id_horario` int(10) NOT NULL,
  `dia_semana` varchar(20) NOT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_grupo_horario` (`id_grupo_horario`),
  KEY `id_horario` (`id_horario`),
  CONSTRAINT `grupo_horario_horario_fk` FOREIGN KEY (`id_grupo_horario`) REFERENCES `grupo_horario` (`id`),
  CONSTRAINT `grupo_horario_horario_fk1` FOREIGN KEY (`id_horario`) REFERENCES `horario_consumo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_horario_horario`
--

LOCK TABLES `grupo_horario_horario` WRITE;
/*!40000 ALTER TABLE `grupo_horario_horario` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo_horario_horario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horario_consumo`
--

DROP TABLE IF EXISTS `horario_consumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `horario_consumo` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `codigo` varchar(20) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `hora_inicio` varchar(20) NOT NULL,
  `hora_fin` varchar(20) NOT NULL,
  `cantidad_consumo` int(10) NOT NULL,
  `lunes` varchar(20) DEFAULT NULL,
  `martes` varchar(20) DEFAULT NULL,
  `miercoles` varchar(20) DEFAULT NULL,
  `jueves` varchar(20) DEFAULT NULL,
  `viernes` varchar(20) DEFAULT NULL,
  `sabado` varchar(20) DEFAULT NULL,
  `domingo` varchar(20) DEFAULT NULL,
  `festivo` varchar(20) DEFAULT NULL,
  `id_tipo_consumo` int(10) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_tipo_consumo` (`id_tipo_consumo`),
  CONSTRAINT `horario_consumo_fk` FOREIGN KEY (`id_tipo_consumo`) REFERENCES `tipo_consumo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horario_consumo`
--

LOCK TABLES `horario_consumo` WRITE;
/*!40000 ALTER TABLE `horario_consumo` DISABLE KEYS */;
/*!40000 ALTER TABLE `horario_consumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `identificador`
--

DROP TABLE IF EXISTS `identificador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `identificador` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `codigo_dentificador` varchar(20) DEFAULT NULL,
  `id_persona` int(10) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_persona` (`id_persona`),
  CONSTRAINT `identificador_fk` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `identificador`
--

LOCK TABLES `identificador` WRITE;
/*!40000 ALTER TABLE `identificador` DISABLE KEYS */;
/*!40000 ALTER TABLE `identificador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marcacion`
--

DROP TABLE IF EXISTS `marcacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marcacion` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `id_persona` int(10) DEFAULT NULL,
  `fecha_marcacion` datetime DEFAULT NULL,
  `estado_marcacion` varchar(255) DEFAULT NULL,
  `nombre_dispositivo` varchar(255) DEFAULT NULL,
  `observacion` longtext,
  `observacion_personal` longtext,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_persona` (`id_persona`),
  CONSTRAINT `marcacion_fk` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marcacion`
--

LOCK TABLES `marcacion` WRITE;
/*!40000 ALTER TABLE `marcacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `marcacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodo`
--

DROP TABLE IF EXISTS `periodo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `periodo` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `codigo` int(10) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `observacion` longtext,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodo`
--

LOCK TABLES `periodo` WRITE;
/*!40000 ALTER TABLE `periodo` DISABLE KEYS */;
/*!40000 ALTER TABLE `periodo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permiso`
--

DROP TABLE IF EXISTS `permiso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permiso` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `nombre` varchar(150) NOT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permiso`
--

LOCK TABLES `permiso` WRITE;
/*!40000 ALTER TABLE `permiso` DISABLE KEYS */;
INSERT INTO `permiso` VALUES (1,'Empresa.Abrir',NULL),(2,'Empresa.Borrar',NULL),(3,'Empresa.Editar',NULL),(4,'Empresa.Guardar',NULL),(5,'CentroCostos.Abrir',NULL),(6,'CentroCostos.Borrar',NULL),(7,'CentroCostos.Editar',NULL),(8,'CentroCostos.Guardar',NULL),(9,'Dispositivo.Abrir',NULL),(10,'Dispositivo.Borrar',NULL),(11,'Dispositivo.Editar',NULL),(12,'Dispositivo.Guardar',NULL),(13,'Festivos.Abrir',NULL),(14,'Festivos.Borrar',NULL),(15,'Festivos.Editar',NULL),(16,'Festivos.Guardar',NULL),(17,'TipoConsumo.Abrir',NULL),(18,'TipoConsumo.Borrar',NULL),(19,'TipoConsumo.Editar',NULL),(20,'TipoConsumo.Guardar',NULL),(21,'HorarioConsumo.Abrir',NULL),(22,'HorarioConsumo.Borrar',NULL),(23,'HorarioConsumo.Editar',NULL),(24,'HorarioConsumo.Guardar',NULL),(25,'GrupoConsumo.Abrir',NULL),(26,'GrupoConsumo.Borrar',NULL),(27,'GrupoConsumo.Editar',NULL),(28,'GrupoConsumo.Guardar',NULL),(29,'AsocGrupoConsumo.Abrir',NULL),(30,'AsocGrupoConsumo.Borrar',NULL),(31,'AsocGrupoConsumo.Editar',NULL),(32,'AsocGrupoConsumo.Guardar',NULL),(33,'PersonasCasino.Abrir',NULL),(34,'PersonasCasino.Borrar',NULL),(35,'PersonasCasino.Editar',NULL),(36,'PersonasCasino.Guardar',NULL),(37,'LiquidacionCasino.Abrir',NULL),(38,'LiquidacionCasino.Borrar',NULL),(39,'LiquidacionCasino.Editar',NULL),(40,'LiquidacionCasino.Guardar',NULL),(41,'Permisos.Abrir',NULL),(42,'Auditoria.Abrir',NULL),(43,'Ciudad.Abrir',NULL),(44,'Ciudad.Borrar',NULL),(45,'Ciudad.Editar',NULL),(46,'Ciudad.Guardar',NULL),(47,'Dependencias.Abrir',NULL),(48,'Dependencias.Borrar',NULL),(49,'Dependencias.Editar',NULL),(50,'Dependencias.Guardar',NULL),(51,'Areas.Abrir',NULL),(52,'Areas.Borrar',NULL),(53,'Areas.Editar',NULL),(54,'Areas.Guardar',NULL),(55,'Usuarios.Abrir',NULL),(56,'Usuarios.Borrar',NULL),(57,'Usuarios.Editar',NULL),(58,'Usuarios.Guardar',NULL),(59,'CargosHoteleria.Abrir',NULL),(60,'CargosHoteleria.Borrar',NULL),(61,'CargosHoteleria.Editar',NULL),(62,'CargosHoteleria.Guardar',NULL),(63,'LiquidacionHoteleria.Abrir',NULL),(64,'LiquidacionHoteleria.Borrar',NULL),(65,'LiquidacionHoteleria.Editar',NULL),(66,'LiquidacionHoteleria.Guardar',NULL),(67,'RegistroCargos.Abrir',NULL),(68,'RegistroCargos.Borrar',NULL),(69,'RegistroCargos.Editar',NULL),(70,'RegistroCargos.Guardar',NULL),(71,'Periodos.Abrir',NULL),(72,'Periodos.Borrar',NULL),(73,'Periodos.Editar',NULL),(74,'Periodos.Guardar',NULL),(75,'GrupoTurnos.Abrir',NULL),(76,'GrupoTurnos.Borrar',NULL),(77,'GrupoTurnos.Editar',NULL),(78,'GrupoTurnos.Guardar',NULL),(79,'RelacionHorarioTurno.Abrir',NULL),(80,'RelacionHorarioTurno.Borrar',NULL),(81,'RelacionHorarioTurno.Editar',NULL),(82,'RelacionHorarioTurno.Guardar',NULL),(83,'Turnos.Abrir',NULL),(84,'Turnos.Borrar',NULL),(85,'Turnos.Editar',NULL),(86,'Turnos.Guardar',NULL),(87,'Funciones.Abrir',NULL),(88,'Funciones.Borrar',NULL),(89,'Funciones.Editar',NULL),(90,'Funciones.Guardar',NULL),(91,'PersonasTiempos.Abrir',NULL),(92,'PersonasTiempos.Borrar',NULL),(93,'PersonasTiempos.Editar',NULL),(94,'PersonasTiempos.Guardar',NULL),(95,'LiquidacionNomina.Abrir',NULL),(96,'LiquidacionNomina.Borrar',NULL),(97,'LiquidacionNomina.Editar',NULL),(98,'LiquidacionNomina.Guardar',NULL),(99,'Cargos.Abrir',NULL),(100,'Cargos.Borrar',NULL),(101,'Cargos.Editar',NULL),(102,'Cargos.Guardar',NULL);
/*!40000 ALTER TABLE `permiso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permiso_x_usuario`
--

DROP TABLE IF EXISTS `permiso_x_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permiso_x_usuario` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `id_permiso` int(10) NOT NULL,
  `id_usuario` int(10) NOT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_permiso` (`id_permiso`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `permiso_x_usuarios_fk` FOREIGN KEY (`id_permiso`) REFERENCES `permiso` (`id`),
  CONSTRAINT `permiso_x_usuarios_fk1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permiso_x_usuario`
--

LOCK TABLES `permiso_x_usuario` WRITE;
/*!40000 ALTER TABLE `permiso_x_usuario` DISABLE KEYS */;
INSERT INTO `permiso_x_usuario` VALUES (4,1,1,NULL),(5,2,1,NULL),(6,3,1,NULL),(7,4,1,NULL),(8,5,1,NULL),(9,6,1,NULL),(10,7,1,NULL),(11,8,1,NULL),(12,9,1,NULL),(13,10,1,NULL),(14,11,1,NULL),(15,12,1,NULL),(16,13,1,NULL),(17,14,1,NULL),(18,15,1,NULL),(19,16,1,NULL),(20,17,1,NULL),(21,18,1,NULL),(22,19,1,NULL),(23,20,1,NULL),(24,21,1,NULL),(25,22,1,NULL),(26,23,1,NULL),(27,24,1,NULL),(28,25,1,NULL),(29,26,1,NULL),(30,27,1,NULL),(31,28,1,NULL),(32,29,1,NULL),(33,30,1,NULL),(34,31,1,NULL),(35,32,1,NULL),(36,33,1,NULL),(37,34,1,NULL),(38,35,1,NULL),(39,36,1,NULL),(40,37,1,NULL),(41,38,1,NULL),(42,39,1,NULL),(43,40,1,NULL),(44,41,1,NULL),(45,42,1,NULL),(46,43,1,NULL),(47,44,1,NULL),(48,45,1,NULL),(49,46,1,NULL),(50,47,1,NULL),(51,48,1,NULL),(52,49,1,NULL),(53,50,1,NULL),(54,51,1,NULL),(55,52,1,NULL),(56,53,1,NULL),(57,54,1,NULL),(58,55,1,NULL),(59,56,1,NULL),(60,57,1,NULL),(61,58,1,NULL),(62,59,1,NULL),(63,60,1,NULL),(64,61,1,NULL),(65,62,1,NULL),(66,63,1,NULL),(67,64,1,NULL),(68,65,1,NULL),(69,66,1,NULL),(70,67,1,NULL),(71,68,1,NULL),(72,69,1,NULL),(73,70,1,NULL),(74,71,1,NULL),(75,72,1,NULL),(76,73,1,NULL),(77,74,1,NULL),(78,75,1,NULL),(79,76,1,NULL),(80,77,1,NULL),(81,78,1,NULL),(82,79,1,NULL),(83,80,1,NULL),(84,81,1,NULL),(85,82,1,NULL),(86,83,1,NULL),(87,84,1,NULL),(88,85,1,NULL),(89,86,1,NULL),(90,87,1,NULL),(91,88,1,NULL),(92,89,1,NULL),(93,90,1,NULL),(94,91,1,NULL),(95,92,1,NULL),(96,93,1,NULL),(97,94,1,NULL),(98,95,1,NULL),(99,96,1,NULL),(100,97,1,NULL),(101,98,1,NULL),(102,99,1,NULL),(103,100,1,NULL),(104,101,1,NULL),(105,102,1,NULL);
/*!40000 ALTER TABLE `permiso_x_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `tipo_identificacion` varchar(50) DEFAULT NULL,
  `identificacion` varchar(50) DEFAULT NULL,
  `nombres` varchar(255) DEFAULT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(100) DEFAULT NULL,
  `rh` varchar(5) DEFAULT NULL,
  `tipo_persona` varchar(50) DEFAULT NULL,
  `recibe_visitas` varchar(5) DEFAULT NULL,
  `nombre_eps` varchar(100) DEFAULT NULL,
  `nombre_arl` varchar(100) DEFAULT NULL,
  `acceso_restringido` varchar(5) DEFAULT NULL,
  `observacion` longtext,
  `consumo_casino` varchar(5) DEFAULT NULL,
  `tarjeta_acceso` varchar(20) DEFAULT NULL,
  `codigo_nomina` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `id_dependencia` int(10) DEFAULT NULL,
  `id_empresa_seguridad_social` int(10) DEFAULT NULL,
  `id_grupo_horario` int(10) DEFAULT NULL,
  `id_turno` int(10) DEFAULT NULL,
  `id_departamento` int(10) DEFAULT NULL,
  `id_area` int(10) DEFAULT NULL,
  `id_ciudad` int(10) DEFAULT NULL,
  `id_centro_costo` int(10) DEFAULT NULL,
  `id_cargo` int(10) DEFAULT NULL,
  `id_empresa_trabaja` int(10) DEFAULT NULL,
  `id_grupo_consumo` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_dependencia` (`id_dependencia`),
  KEY `id_empresa_seguridad_social` (`id_empresa_seguridad_social`),
  KEY `id_grupo_horario` (`id_grupo_horario`),
  KEY `id_turno` (`id_turno`),
  KEY `id_departamento` (`id_departamento`),
  KEY `id_area` (`id_area`),
  KEY `id_ciudad` (`id_ciudad`),
  KEY `id_centro_costo` (`id_centro_costo`),
  KEY `id_cargo` (`id_cargo`),
  KEY `id_empresa_trabaja` (`id_empresa_trabaja`),
  KEY `id_grupo_consumo` (`id_grupo_consumo`),
  CONSTRAINT `persona_fk` FOREIGN KEY (`id_dependencia`) REFERENCES `dependencia` (`id`),
  CONSTRAINT `persona_fk1` FOREIGN KEY (`id_empresa_seguridad_social`) REFERENCES `empresa` (`id`),
  CONSTRAINT `persona_fk10` FOREIGN KEY (`id_grupo_consumo`) REFERENCES `grupo_consumo` (`id`),
  CONSTRAINT `persona_fk2` FOREIGN KEY (`id_grupo_horario`) REFERENCES `grupo_horario` (`id`),
  CONSTRAINT `persona_fk3` FOREIGN KEY (`id_turno`) REFERENCES `turno_tiempo` (`id`),
  CONSTRAINT `persona_fk4` FOREIGN KEY (`id_departamento`) REFERENCES `departamento` (`id`),
  CONSTRAINT `persona_fk5` FOREIGN KEY (`id_area`) REFERENCES `area` (`id`),
  CONSTRAINT `persona_fk6` FOREIGN KEY (`id_ciudad`) REFERENCES `ciudad` (`id`),
  CONSTRAINT `persona_fk7` FOREIGN KEY (`id_centro_costo`) REFERENCES `centro_costo` (`id`),
  CONSTRAINT `persona_fk8` FOREIGN KEY (`id_cargo`) REFERENCES `cargo` (`id`),
  CONSTRAINT `persona_fk9` FOREIGN KEY (`id_empresa_trabaja`) REFERENCES `empresa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro_tiempo`
--

DROP TABLE IF EXISTS `registro_tiempo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registro_tiempo` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `codigo` varchar(20) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `campo1` varchar(20) DEFAULT NULL,
  `campo2` varchar(20) DEFAULT NULL,
  `campo3` varchar(20) DEFAULT NULL,
  `campo4` varchar(20) DEFAULT NULL,
  `dispositivo` varchar(20) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro_tiempo`
--

LOCK TABLES `registro_tiempo` WRITE;
/*!40000 ALTER TABLE `registro_tiempo` DISABLE KEYS */;
/*!40000 ALTER TABLE `registro_tiempo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_consumo`
--

DROP TABLE IF EXISTS `tipo_consumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_consumo` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `nombre` varchar(200) NOT NULL,
  `cantidad` int(10) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_consumo`
--

LOCK TABLES `tipo_consumo` WRITE;
/*!40000 ALTER TABLE `tipo_consumo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_consumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turno_tiempo`
--

DROP TABLE IF EXISTS `turno_tiempo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `turno_tiempo` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `codigo` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `tipo_turno` varchar(255) DEFAULT NULL,
  `hora_inicio` varchar(255) DEFAULT NULL,
  `hora_fin` varchar(255) DEFAULT NULL,
  `teorico` varchar(255) DEFAULT NULL,
  `tolerancia_despues_entrada` varchar(255) DEFAULT NULL,
  `tolerancia_antes_salir` varchar(255) DEFAULT NULL,
  `tiempo_break` varchar(255) DEFAULT NULL,
  `limite_turno` varchar(255) DEFAULT NULL,
  `genera_extras_entrada` varchar(255) DEFAULT NULL,
  `tiempo_minimo_entrada` varchar(255) DEFAULT NULL,
  `tiempo_maximo_entrada` varchar(255) DEFAULT NULL,
  `genera_extras_salida` varchar(255) DEFAULT NULL,
  `tiempo_minimo_salida` varchar(255) DEFAULT NULL,
  `tiempo_maximo_salida` varchar(255) DEFAULT NULL,
  `redondeo_entrada` varchar(255) DEFAULT NULL,
  `sentido_entrada` varchar(255) DEFAULT NULL,
  `redondeo_salida` varchar(255) DEFAULT NULL,
  `sentido_salida` varchar(255) DEFAULT NULL,
  `descanso` varchar(255) DEFAULT NULL,
  `sentido_descanso` varchar(255) DEFAULT NULL,
  `conceptos` varchar(255) DEFAULT NULL,
  `sentido_concepto` varchar(255) DEFAULT NULL,
  `hora_inicio_diurno` varchar(255) DEFAULT NULL,
  `hora_inicio_nocturno` varchar(255) DEFAULT NULL,
  `turno_noche` varchar(10) DEFAULT NULL,
  `hora_inicio_break` varchar(255) DEFAULT NULL,
  `hora_fin_break` varchar(255) DEFAULT NULL,
  `descuenta_break` varchar(255) DEFAULT NULL,
  `turno_extra` varchar(20) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turno_tiempo`
--

LOCK TABLES `turno_tiempo` WRITE;
/*!40000 ALTER TABLE `turno_tiempo` DISABLE KEYS */;
/*!40000 ALTER TABLE `turno_tiempo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) NOT NULL,
  `login` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Tecno Control','Tecno','gvla0fzqpws=','');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehiculo` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `placa_vehiculo` varchar(50) DEFAULT NULL,
  `color_vehiculo` varchar(50) DEFAULT NULL,
  `marca_vehiculo` varchar(50) DEFAULT NULL,
  `tipo_vehiculo` varchar(50) DEFAULT NULL,
  `id_persona_responsable` int(10) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_persona_responsable` (`id_persona_responsable`),
  CONSTRAINT `vehiculo_fk` FOREIGN KEY (`id_persona_responsable`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo`
--

LOCK TABLES `vehiculo` WRITE;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visita`
--

DROP TABLE IF EXISTS `visita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visita` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `id_persona_visitante` int(10) DEFAULT NULL,
  `id_empresa_visitante` int(10) DEFAULT NULL,
  `id_persona_visitada` int(10) DEFAULT NULL,
  `id_area_visitada` int(10) DEFAULT NULL,
  `tipo_visita` varchar(50) DEFAULT NULL,
  `numero_tarjeta` varchar(50) DEFAULT NULL,
  `fecha_hora_entrada` datetime DEFAULT NULL,
  `fecha_hora_salida` datetime DEFAULT NULL,
  `estado_visita` varchar(50) DEFAULT NULL,
  `id_vehiculo` int(10) DEFAULT NULL,
  `observacion` longtext,
  `id_usuario_ingreso` int(10) DEFAULT NULL,
  `id_usuario_salida` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_persona_visitante` (`id_persona_visitante`),
  KEY `id_empresa_visitante` (`id_empresa_visitante`),
  KEY `id_persona_visitada` (`id_persona_visitada`),
  KEY `id_area_visitada` (`id_area_visitada`),
  KEY `id_vehiculo` (`id_vehiculo`),
  KEY `id_usuario_ingreso` (`id_usuario_ingreso`),
  KEY `id_usuario_salida` (`id_usuario_salida`),
  CONSTRAINT `visita_fk` FOREIGN KEY (`id_persona_visitante`) REFERENCES `persona` (`id`),
  CONSTRAINT `visita_fk1` FOREIGN KEY (`id_empresa_visitante`) REFERENCES `empresa` (`id`),
  CONSTRAINT `visita_fk2` FOREIGN KEY (`id_persona_visitada`) REFERENCES `persona` (`id`),
  CONSTRAINT `visita_fk3` FOREIGN KEY (`id_area_visitada`) REFERENCES `area` (`id`),
  CONSTRAINT `visita_fk4` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculo` (`id`),
  CONSTRAINT `visita_fk5` FOREIGN KEY (`id_usuario_ingreso`) REFERENCES `usuario` (`id`),
  CONSTRAINT `visita_fk6` FOREIGN KEY (`id_usuario_salida`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visita`
--

LOCK TABLES `visita` WRITE;
/*!40000 ALTER TABLE `visita` DISABLE KEYS */;
/*!40000 ALTER TABLE `visita` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-14 15:29:10
