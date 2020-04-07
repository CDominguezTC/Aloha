-- MySQL dump 10.16  Distrib 10.1.31-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: alohatiempos
-- ------------------------------------------------------
-- Server version	10.1.31-MariaDB

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
-- Table structure for table `areas`
--

DROP TABLE IF EXISTS `areas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `areas` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `codigo` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areas`
--

LOCK TABLES `areas` WRITE;
/*!40000 ALTER TABLE `areas` DISABLE KEYS */;
INSERT INTO `areas` VALUES (1,'001','Area por defecto');
/*!40000 ALTER TABLE `areas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asocgrupohorario`
--

DROP TABLE IF EXISTS `asocgrupohorario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asocgrupohorario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grupoconsumoid` int(11) NOT NULL,
  `horarioconsumoid` int(11) NOT NULL,
  `costo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `grupoconsumoid` (`grupoconsumoid`),
  KEY `horarioconsumoid` (`horarioconsumoid`),
  CONSTRAINT `asocgrupohorario_fk` FOREIGN KEY (`grupoconsumoid`) REFERENCES `grupoconsumo` (`id`),
  CONSTRAINT `asocgrupohorario_fk1` FOREIGN KEY (`horarioconsumoid`) REFERENCES `horarioconsumo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asocgrupohorario`
--

LOCK TABLES `asocgrupohorario` WRITE;
/*!40000 ALTER TABLE `asocgrupohorario` DISABLE KEYS */;
INSERT INTO `asocgrupohorario` VALUES (5,8,3,10500);
/*!40000 ALTER TABLE `asocgrupohorario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autorizaconsumo`
--

DROP TABLE IF EXISTS `autorizaconsumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `autorizaconsumo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `tipoPersona` varchar(50) DEFAULT NULL,
  `idpersonaqueautoriza` int(10) unsigned DEFAULT NULL,
  `idpersonaautorizada` int(10) unsigned DEFAULT NULL,
  `idcentroCosto` int(10) unsigned DEFAULT NULL,
  `fechaautorizada` datetime DEFAULT NULL,
  `idhorarioConsumo` int(10) unsigned DEFAULT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `cantidadautorizada` int(11) DEFAULT NULL,
  `cantidadconsumida` int(10) unsigned DEFAULT NULL,
  `fecharegistro` datetime DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autorizaconsumo`
--

LOCK TABLES `autorizaconsumo` WRITE;
/*!40000 ALTER TABLE `autorizaconsumo` DISABLE KEYS */;
/*!40000 ALTER TABLE `autorizaconsumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargos`
--

DROP TABLE IF EXISTS `cargos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargos` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(200) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargos`
--

LOCK TABLES `cargos` WRITE;
/*!40000 ALTER TABLE `cargos` DISABLE KEYS */;
INSERT INTO `cargos` VALUES (1,'001','Cargo por defecto');
/*!40000 ALTER TABLE `cargos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `centrocosto`
--

DROP TABLE IF EXISTS `centrocosto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `centrocosto` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `nombre` varchar(255) DEFAULT NULL,
  `codigoInterno` varchar(255) DEFAULT NULL,
  `definicionId` int(18) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `centrocosto`
--

LOCK TABLES `centrocosto` WRITE;
/*!40000 ALTER TABLE `centrocosto` DISABLE KEYS */;
INSERT INTO `centrocosto` VALUES (11,'Sistemas','001',NULL);
/*!40000 ALTER TABLE `centrocosto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ciudad` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `codigo` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` VALUES (2,'001','CALI'),(4,'002','BOGOTA');
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumo`
--

DROP TABLE IF EXISTS `consumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consumo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personaId` int(10) unsigned DEFAULT NULL,
  `personaNombre` varchar(800) DEFAULT NULL,
  `personaCedula` int(100) DEFAULT NULL,
  `centrodecostoId` int(10) unsigned DEFAULT NULL,
  `centrodecostoNombre` varchar(200) DEFAULT NULL,
  `grupoconsumoId` int(11) DEFAULT NULL,
  `grupoconsumoNombre` varchar(200) DEFAULT NULL,
  `horarioconsumoId` int(11) DEFAULT NULL,
  `horarioconsumoNombre` varchar(20) DEFAULT NULL,
  `Fechaconsumo` datetime DEFAULT NULL,
  `diaconsumo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `personaId` (`personaId`),
  KEY `centrodecostoId` (`centrodecostoId`),
  KEY `grupoconsumoId` (`grupoconsumoId`),
  KEY `horarioconsumoId` (`horarioconsumoId`),
  CONSTRAINT `consumo_fk` FOREIGN KEY (`personaId`) REFERENCES `persona` (`id`),
  CONSTRAINT `consumo_fk1` FOREIGN KEY (`centrodecostoId`) REFERENCES `centrocosto` (`id`),
  CONSTRAINT `consumo_fk2` FOREIGN KEY (`grupoconsumoId`) REFERENCES `grupoconsumo` (`id`),
  CONSTRAINT `consumo_fk3` FOREIGN KEY (`horarioconsumoId`) REFERENCES `horarioconsumo` (`id`)
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
-- Table structure for table `dependencia`
--

DROP TABLE IF EXISTS `dependencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dependencia` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `codigo` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dependencia`
--

LOCK TABLES `dependencia` WRITE;
/*!40000 ALTER TABLE `dependencia` DISABLE KEYS */;
INSERT INTO `dependencia` VALUES (1,'001','Dependencia por defecto');
/*!40000 ALTER TABLE `dependencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dispositivo`
--

DROP TABLE IF EXISTS `dispositivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dispositivo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `numeroDispositivo` int(10) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `direccionIP` varchar(50) DEFAULT NULL,
  `puerto` int(10) DEFAULT NULL,
  `modo` varchar(50) DEFAULT NULL,
  `ipControladora` varchar(50) DEFAULT NULL,
  `puertoControladora` int(10) DEFAULT NULL,
  `tipoLector` varchar(50) DEFAULT NULL,
  `Activo` varchar(2) DEFAULT NULL,
  `Serie` varchar(50) DEFAULT NULL,
  `Licencia` varchar(100) DEFAULT NULL,
  `Impresora` varchar(100) DEFAULT NULL,
  `EncabezadoImpresion` varchar(50) DEFAULT NULL,
  `UtilizaMenu` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dispositivo`
--

LOCK TABLES `dispositivo` WRITE;
/*!40000 ALTER TABLE `dispositivo` DISABLE KEYS */;
INSERT INTO `dispositivo` VALUES (5,1,'Casino','192.168.2.155',4370,'2','192.168.2.1',4848,'1','1','123456789','987654321','EpsonPuntoventa','Tecno Control SAS','1');
/*!40000 ALTER TABLE `dispositivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresa` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `nombre` varchar(255) NOT NULL,
  `nit` varchar(50) DEFAULT NULL,
  `direccion` varchar(2000) DEFAULT NULL,
  `contacto` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `ext` varchar(255) DEFAULT NULL,
  `ciudad` varchar(200) DEFAULT NULL,
  `observacion` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (3,'Tecno Control SAS','800211327','Carrera 32 No  10 - 29','Carlos A Dominguez D','carlos@tecnocontrol.com.co','4458714','105',NULL,'Sistemas');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `festivos`
--

DROP TABLE IF EXISTS `festivos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `festivos` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha` date DEFAULT NULL,
  `Nota` longtext,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `festivos`
--

LOCK TABLES `festivos` WRITE;
/*!40000 ALTER TABLE `festivos` DISABLE KEYS */;
INSERT INTO `festivos` VALUES (10,'2020-03-23','Festivo');
/*!40000 ALTER TABLE `festivos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funciones`
--

DROP TABLE IF EXISTS `funciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funciones` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) DEFAULT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  `estado` varchar(200) DEFAULT NULL,
  `codReloj` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funciones`
--

LOCK TABLES `funciones` WRITE;
/*!40000 ALTER TABLE `funciones` DISABLE KEYS */;
INSERT INTO `funciones` VALUES (1,'Entrada','Entrada Labor','Activo','0'),(2,'Entrada Intermedia','Entrada Intermedia','Activo','3'),(3,'Salida Intermedia','Salida Intermedia','Activo','2'),(4,'Salida','Salida Labor','Activo','1');
/*!40000 ALTER TABLE `funciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupoconsumo`
--

DROP TABLE IF EXISTS `grupoconsumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupoconsumo` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(200) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `id` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupoconsumo`
--

LOCK TABLES `grupoconsumo` WRITE;
/*!40000 ALTER TABLE `grupoconsumo` DISABLE KEYS */;
INSERT INTO `grupoconsumo` VALUES (7,'001','Planta'),(8,'002','Administracion');
/*!40000 ALTER TABLE `grupoconsumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupohorario`
--

DROP TABLE IF EXISTS `grupohorario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupohorario` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `codigo` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupohorario`
--

LOCK TABLES `grupohorario` WRITE;
/*!40000 ALTER TABLE `grupohorario` DISABLE KEYS */;
INSERT INTO `grupohorario` VALUES (1,'001','Grupo de turno por defecto');
/*!40000 ALTER TABLE `grupohorario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupohorario_horario`
--

DROP TABLE IF EXISTS `grupohorario_horario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupohorario_horario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `IdGrupoHorario` int(11) NOT NULL,
  `IdHorario` int(11) NOT NULL,
  `diaSeman` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupohorario_horario`
--

LOCK TABLES `grupohorario_horario` WRITE;
/*!40000 ALTER TABLE `grupohorario_horario` DISABLE KEYS */;
INSERT INTO `grupohorario_horario` VALUES (117,1,1,'Lunes'),(118,1,1,'Martes'),(119,1,1,'Miercoles'),(120,1,1,'Jueves'),(121,1,1,'Viernes'),(122,1,1,'Sabado'),(123,1,2,'Lunes'),(124,1,2,'Martes'),(125,1,2,'Miercoles'),(126,1,2,'Jueves'),(127,1,2,'Viernes'),(128,1,2,'Sabado'),(129,1,3,'Lunes'),(130,1,3,'Martes'),(131,1,3,'Miercoles'),(132,1,3,'Jueves'),(133,1,3,'Viernes'),(134,1,3,'Sabado'),(135,1,4,'Lunes'),(136,1,4,'Martes'),(137,1,4,'Miercoles'),(138,1,4,'Jueves'),(139,1,4,'Viernes'),(140,1,4,'Sabado');
/*!40000 ALTER TABLE `grupohorario_horario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horarioconsumo`
--

DROP TABLE IF EXISTS `horarioconsumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `horarioconsumo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(20) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `horainicio` varchar(20) NOT NULL,
  `horafin` varchar(20) NOT NULL,
  `cantidadconsumos` int(11) NOT NULL,
  `lunes` varchar(20) DEFAULT NULL,
  `martes` varchar(20) DEFAULT NULL,
  `miercoles` varchar(20) DEFAULT NULL,
  `jueves` varchar(20) DEFAULT NULL,
  `viernes` varchar(20) DEFAULT NULL,
  `sabado` varchar(20) DEFAULT NULL,
  `domingo` varchar(20) DEFAULT NULL,
  `festivo` varchar(20) DEFAULT NULL,
  `tipoConsumo` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `tipoConsumo` (`tipoConsumo`),
  CONSTRAINT `horarioconsumo_fk` FOREIGN KEY (`tipoConsumo`) REFERENCES `tipoconsumo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horarioconsumo`
--

LOCK TABLES `horarioconsumo` WRITE;
/*!40000 ALTER TABLE `horarioconsumo` DISABLE KEYS */;
INSERT INTO `horarioconsumo` VALUES (3,'001','Desayuno','06:00','09:00',1,'S','S','S','S','S','S','S','S',2);
/*!40000 ALTER TABLE `horarioconsumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `identificador`
--

DROP TABLE IF EXISTS `identificador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `identificador` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `codidentificador` varchar(20) DEFAULT NULL,
  `idpersona` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idpersona` (`idpersona`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `identificador`
--

LOCK TABLES `identificador` WRITE;
/*!40000 ALTER TABLE `identificador` DISABLE KEYS */;
INSERT INTO `identificador` VALUES (1,'16928919',1);
/*!40000 ALTER TABLE `identificador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marcacion`
--

DROP TABLE IF EXISTS `marcacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marcacion` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_empleado` int(10) DEFAULT NULL,
  `fecha_marcacion` datetime DEFAULT NULL,
  `estado_marcacion` varchar(255) DEFAULT NULL,
  `nombre_dispositivo` varchar(255) DEFAULT NULL,
  `observacion` varchar(500) DEFAULT NULL,
  `observacionPersonal` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marcacion`
--

LOCK TABLES `marcacion` WRITE;
/*!40000 ALTER TABLE `marcacion` DISABLE KEYS */;
INSERT INTO `marcacion` VALUES (1,1,'2020-02-13 05:00:00','Entrada',NULL,'MODIFICADO',''),(2,1,'2020-02-13 22:00:00','Salida',NULL,'MODIFICADO',''),(3,1,'2020-02-14 07:30:00','Entrada',NULL,'MANUAL',''),(4,1,'2020-02-14 18:00:00','Salida',NULL,'MODIFICADO',''),(5,1,'2020-02-15 13:30:00','Entrada',NULL,'MODIFICADO',''),(6,1,'2020-02-15 23:00:00','Salida',NULL,'MODIFICADO',''),(7,1,'2020-02-04 07:25:00','Entrada',NULL,NULL,NULL),(8,1,'2020-02-04 19:10:00','Salida',NULL,NULL,NULL),(9,1,'2020-02-05 07:35:00','Entrada',NULL,NULL,NULL),(10,1,'2020-02-05 17:55:00','Salida',NULL,NULL,NULL),(11,1,'2020-02-06 07:29:00','Entrada',NULL,NULL,NULL),(12,1,'2020-02-06 18:40:00','Salida',NULL,NULL,NULL),(13,1,'2020-02-07 07:15:00','Entrada',NULL,NULL,NULL),(14,1,'2020-02-07 18:50:00','Salida',NULL,NULL,NULL),(15,1,'2020-02-17 07:45:00','Entrada',NULL,'MANUAL',''),(16,1,'2020-02-17 17:55:00','Salida',NULL,'MODIFICADO',''),(17,1,'2020-02-16 02:00:00','Entrada',NULL,'MODIFICADO',''),(19,1,'2020-02-16 23:15:00','Salida',NULL,'MODIFICADO',''),(20,1,'2020-02-09 06:00:00','Entrada',NULL,'MANUAL',''),(21,1,'2020-02-09 14:00:00','Salida',NULL,'MANUAL','');
/*!40000 ALTER TABLE `marcacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodo`
--

DROP TABLE IF EXISTS `periodo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `periodo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `codigo` int(10) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `fechaInicio` date DEFAULT NULL,
  `fechaFin` date DEFAULT NULL,
  `observacion` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodo`
--

LOCK TABLES `periodo` WRITE;
/*!40000 ALTER TABLE `periodo` DISABLE KEYS */;
/*!40000 ALTER TABLE `periodo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permisos`
--

DROP TABLE IF EXISTS `permisos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permisos` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permisos`
--

LOCK TABLES `permisos` WRITE;
/*!40000 ALTER TABLE `permisos` DISABLE KEYS */;
INSERT INTO `permisos` VALUES (1,'Empresa.Abrir'),(2,'Empresa.Borrar'),(3,'Empresa.Editar'),(4,'Empresa.Guardar'),(5,'CentroCosto.Abrir'),(6,'CentroCosto.Borrar'),(7,'CentroCosto.Editar'),(8,'CentroCosto.Guardar'),(9,'Dispositivo.Abrir'),(10,'Dispositivo.Borrar'),(11,'Dispositivo.Editar'),(12,'Dispositivo.Guardar'),(13,'Festivos.Abrir'),(14,'Festivos.Borrar'),(15,'Festivos.Editar'),(16,'Festivos.Guardar'),(17,'TipoConsumo.Abrir'),(18,'TipoConsumo.Borrar'),(19,'TipoConsumo.Editar'),(20,'TipoConsumo.Guardar'),(21,'HorarioConsumo.Abrir'),(22,'HorarioConsumo.Borrar'),(23,'HorarioConsumo.Editar'),(24,'HorarioConsumo.Guardar'),(25,'GrupoConsumo.Abrir'),(26,'GrupoConsumo.Borrar'),(27,'GrupoConsumo.Editar'),(28,'GrupoConsumo.Guardar'),(29,'AsocGrupoConsumo.Abrir'),(30,'AsocGrupoConsumo.Borrar'),(31,'AsocGrupoConsumo.Editar'),(32,'AsocGrupoConsumo.Guardar'),(33,'PersonasCasino.Abrir'),(34,'PersonasCasino.Borrar'),(35,'PersonasCasino.Editar'),(36,'PersonasCasino.Guardar'),(37,'LiquidacionCasino.Abrir'),(38,'LiquidacionCasino.Borrar'),(39,'LiquidacionCasino.Editar'),(40,'LiquidacionCasino.Guardar');
/*!40000 ALTER TABLE `permisos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permisosxusuarios`
--

DROP TABLE IF EXISTS `permisosxusuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permisosxusuarios` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_permiso` int(10) NOT NULL,
  `id_usuario` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permisosxusuarios`
--

LOCK TABLES `permisosxusuarios` WRITE;
/*!40000 ALTER TABLE `permisosxusuarios` DISABLE KEYS */;
INSERT INTO `permisosxusuarios` VALUES (1,1,3),(2,9,3),(3,13,3),(4,5,3);
/*!40000 ALTER TABLE `permisosxusuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `tipoIdentificacion` varchar(50) DEFAULT NULL,
  `identificacion` varchar(50) NOT NULL,
  `nombres` varchar(255) DEFAULT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `direccion` varchar(2000) DEFAULT NULL,
  `ciudad` varchar(255) DEFAULT NULL,
  `tipoPersona` varchar(50) DEFAULT NULL,
  `empresaId` int(10) unsigned DEFAULT NULL,
  `tipoVisitante` varchar(50) DEFAULT NULL,
  `centroCostoId` int(10) unsigned DEFAULT NULL,
  `usuarioId` int(10) unsigned DEFAULT NULL,
  `codigoInterno` varchar(50) DEFAULT NULL,
  `extensionTelefonica` varchar(50) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `nombreEPS` varchar(255) DEFAULT NULL,
  `vencimientoEPS` datetime DEFAULT NULL,
  `nombreARP` varchar(255) DEFAULT NULL,
  `pasadoJudicialVencto` datetime DEFAULT NULL,
  `huella` varchar(255) DEFAULT NULL,
  `recibeVisitas` varchar(2) DEFAULT NULL,
  `plantillaHuella` blob,
  `longitudPlantilla` int(11) DEFAULT NULL,
  `vencimientoARP` datetime DEFAULT NULL,
  `enrollNumber` int(10) DEFAULT NULL,
  `definicionId` int(18) unsigned DEFAULT NULL,
  `itinerarioId` int(11) DEFAULT NULL,
  `accesoRestringido` varchar(2) DEFAULT NULL,
  `vencimientoPension` datetime DEFAULT NULL,
  `vencimientoSeguridadIndustrial` datetime DEFAULT NULL,
  `vencimientoAudiovisualSeguridadIndustrial` datetime DEFAULT NULL,
  `vencimientoTrabajoAlturas` datetime DEFAULT NULL,
  `vencimientoTrabajoConfinados` datetime DEFAULT NULL,
  `vencimientoTrabajoCaliente` datetime DEFAULT NULL,
  `vencimientoTrabajoExcavaciones` datetime DEFAULT NULL,
  `vencimientoTrabajoEnergiaElectrica` datetime DEFAULT NULL,
  `vencimientoOtros` datetime DEFAULT NULL,
  `rH` varchar(4) DEFAULT NULL,
  `seguridadIndustrialSiNo` varchar(2) DEFAULT NULL,
  `audiovisualSiNo` varchar(2) DEFAULT NULL,
  `alturasSiNo` varchar(2) DEFAULT NULL,
  `confinadosSiNo` varchar(2) DEFAULT NULL,
  `calienteSiNo` varchar(2) DEFAULT NULL,
  `excavacionesSiNo` varchar(2) DEFAULT NULL,
  `energiaElectricaSiNo` varchar(2) DEFAULT NULL,
  `otrosSiNo` varchar(2) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `zonaId` int(10) unsigned DEFAULT NULL,
  `tipoAcceso` varchar(50) DEFAULT NULL,
  `fechaInicioAcceso` datetime DEFAULT NULL,
  `fechaFinAcceso` datetime DEFAULT NULL,
  `observaciones` text,
  `consumocasino` varchar(2) DEFAULT NULL,
  `grupoConsumo` int(11) DEFAULT NULL,
  `tipoTrabajoId` int(10) DEFAULT NULL,
  `tarjetaAcceso` varchar(20) DEFAULT NULL,
  `cod_nomina` varchar(255) DEFAULT NULL,
  `id_Dependencias` int(10) unsigned DEFAULT NULL,
  `id_Empresa` int(10) unsigned DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `id_Grupo_Horario` int(10) unsigned DEFAULT NULL,
  `id_Turnos` int(10) unsigned DEFAULT NULL,
  `id_Departamento` int(10) unsigned DEFAULT NULL,
  `id_Areas` int(10) unsigned DEFAULT NULL,
  `id_Ciudad` int(10) unsigned DEFAULT NULL,
  `id_Centro_Costos` int(10) unsigned DEFAULT NULL,
  `id_Cargo` int(10) unsigned DEFAULT NULL,
  `Id_EmpresaTrabaja` int(10) unsigned DEFAULT NULL,
  `Id_Grupo_Consumo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Id_EmpresaTrabaja` (`Id_EmpresaTrabaja`),
  KEY `id_Centro_Costos` (`id_Centro_Costos`),
  KEY `Id_Grupo_Consumo` (`Id_Grupo_Consumo`),
  CONSTRAINT `persona_fk` FOREIGN KEY (`Id_EmpresaTrabaja`) REFERENCES `empresa` (`id`),
  CONSTRAINT `persona_fk1` FOREIGN KEY (`id_Centro_Costos`) REFERENCES `centrocosto` (`id`),
  CONSTRAINT `persona_fk2` FOREIGN KEY (`Id_Grupo_Consumo`) REFERENCES `grupoconsumo` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (6,'1','16928919','Carlos Arturo','Dominguez Diaz',NULL,NULL,NULL,NULL,NULL,NULL,11,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Sistemas','2',7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,NULL),(7,'1','12345','545','454',NULL,NULL,NULL,NULL,NULL,NULL,11,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'45','2',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,NULL),(8,'1','4','4','44',NULL,NULL,NULL,NULL,NULL,NULL,11,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'4','1',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,NULL);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoconsumo`
--

DROP TABLE IF EXISTS `tipoconsumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipoconsumo` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(20) NOT NULL,
  `Cantidad` int(10) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoconsumo`
--

LOCK TABLES `tipoconsumo` WRITE;
/*!40000 ALTER TABLE `tipoconsumo` DISABLE KEYS */;
INSERT INTO `tipoconsumo` VALUES (2,'Sopa',1520);
/*!40000 ALTER TABLE `tipoconsumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turnotiempos`
--

DROP TABLE IF EXISTS `turnotiempos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `turnotiempos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria',
  `codigo` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `tipo_turno` varchar(255) DEFAULT NULL,
  `hora_inicio` varchar(255) DEFAULT NULL,
  `hora_fin` varchar(255) DEFAULT NULL,
  `teorico` varchar(255) DEFAULT NULL,
  `tolerancia_despues_entrada` varchar(255) DEFAULT NULL,
  `tolerancia_antes_salir` varchar(255) DEFAULT NULL,
  `tiempo_breack` varchar(255) DEFAULT NULL,
  `limite_turno` varchar(255) DEFAULT NULL,
  `gener_extras_entrada` varchar(255) DEFAULT NULL,
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turnotiempos`
--

LOCK TABLES `turnotiempos` WRITE;
/*!40000 ALTER TABLE `turnotiempos` DISABLE KEYS */;
INSERT INTO `turnotiempos` VALUES (1,'001','Turno por defecto',NULL,'07:30','18:00','08:30','60','60',NULL,NULL,NULL,'5','60',NULL,'0','60',NULL,NULL,NULL,NULL,'02:00',NULL,NULL,NULL,'06:00','21:00',NULL,'12:00','14:00','S','N'),(2,'002','14:00 a 22:00',NULL,'14:00','22:00','08:00','60','15',NULL,NULL,NULL,'0','60',NULL,'0','50',NULL,NULL,NULL,NULL,'02:00',NULL,NULL,NULL,'06:00','21:00',NULL,'21:00','23:00','N','N'),(3,'003','06:00 a 14:00',NULL,'06:00','14:00','08:00','61','15',NULL,NULL,NULL,'0','60',NULL,'0','60',NULL,NULL,NULL,NULL,'02:00',NULL,NULL,NULL,'06:00','21:00',NULL,'21:00','23:00','N','N'),(4,'004','22:00 a 06:00',NULL,'22:00','06:00','08:00','60','15',NULL,NULL,NULL,'0','60',NULL,'0','60',NULL,NULL,NULL,NULL,'02:00',NULL,NULL,NULL,'06:00','21:00',NULL,'21:00','23:00','N','N'),(5,'005','Extra',NULL,'06:00','21:00','08:00','60','15',NULL,NULL,NULL,'0','60',NULL,'0','60',NULL,NULL,NULL,NULL,'02:00',NULL,NULL,NULL,'06:00','21:00',NULL,'12:00','14:00','S','S');
/*!40000 ALTER TABLE `turnotiempos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) COLLATE latin1_spanish_ci NOT NULL,
  `login` varchar(200) COLLATE latin1_spanish_ci NOT NULL,
  `password` varchar(200) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (3,'Tecno Controls','Tecno','gvla0fzqpws=');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-07 14:36:01
