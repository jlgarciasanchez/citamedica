CREATE DATABASE `citamedica` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `cita` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `cliente_fk` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `numero_visitas_previstas` int(11) NOT NULL,
  `numero_visitas_realizadas` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `cliente_fk_idx` (`cliente_fk`),
  CONSTRAINT `cliente_fk` FOREIGN KEY (`cliente_fk`) REFERENCES `cliente` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=30002 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `cliente` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `razon_social` varchar(200) NOT NULL,
  `cif` varchar(9) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `municipio` varchar(20) NOT NULL,
  `provincia` varchar(20) NOT NULL,
  `inicioContrato` date NOT NULL,
  `finContrato` date NOT NULL,
  `numero_reconocimientos` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `usuario` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `user_UNIQUE` (`user`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;