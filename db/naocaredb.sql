/*
MySQL - 5.7.14 : Database - naocaredb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`naocaredb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `naocaredb`;

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `korisnikId` int(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `prezime` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `korisnickoIme` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `korisnickaSifra` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`korisnikId`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `korisnik` */

insert  into `korisnik`(`korisnikId`,`ime`,`prezime`,`korisnickoIme`,`korisnickaSifra`) values 
(30,'a','a','a','a'),
(56,'milica','suknovic','milicasukn','milica@'),
(57,'janko','jankovic','janko@gmail.com','janko@'),
(58,'marko','markovic','marko@gmail','marko@'),
(59,'milena','suknovic','suknovicm','milena@'),
(62,'milica','milica','milicas','milica\"@');

/*Table structure for table `proizvod` */

DROP TABLE IF EXISTS `proizvod`;

CREATE TABLE `proizvod` (
  `proizvodId` int(20) NOT NULL AUTO_INCREMENT,
  `nazivProizvoda` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `cena` double DEFAULT NULL,
  `boja` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `tip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `proizvodjacId` int(20) DEFAULT NULL,
  PRIMARY KEY (`proizvodId`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `proizvod` */

insert  into `proizvod`(`proizvodId`,`nazivProizvoda`,`cena`,`boja`,`tip`,`proizvodjacId`) values 
(65,'Aviator Large Metal',14590,'roza','Naocare za sunce',1),
(67,'Aviator 2',14590,'braon','Naocare za sunce',1),
(68,'Aviator 3',14690,'plava','Naocare za sunce',1),
(72,'Aviator1',12390,'crna','Naocare za sunce',1),
(73,'Diorama1',12990,'roza','Naocare za sunce',1),
(74,'Diorama 2 Dior',13590,'roza','Naocare za sunce',1),
(75,'Aviator women ',14590,'roza','Naocare za sunce',1),
(76,'Aviator 5',16990,'braon','Naocare za sunce',1),
(77,'Aviator 6',12990,'braon','Naocare za sunce',1),
(78,'Aviator 7',15990,'roza','Naocare za vid',1),
(79,'FIESOLE RC 1055  ',14690,'roza','Naocare za vid',3),
(80,'Gold shaded',25990,'ljubicasta','Naocare za sunce',3),
(81,'JC775',10800,'crna','Naocare za sunce',3),
(82,'JC7375',13990,'roza','Naocare za sunce',1),
(83,'Avi Rb',15990,'siva','Naocare za sunce',1),
(84,'Di Dior 12',18900,'ljubicasta','Naocare za sunce',1),
(85,'Di Dior',21390,'ljubicasta','Naocare za sunce',2),
(86,'Flowers Dior',13590,'roza','Naocare za sunce',1),
(87,'Ray',15690,'ljubicasta','Naocare za sunce',1),
(88,'Dior',23690,'ljubicasta','Naocare za sunce',2),
(89,'Avi',26590,'ljubicasta','Naocare za sunce',1),
(91,'D Dior',14590,'ljubicasta','Naocare za vid',2),
(92,'Avanglion',16900,'roza','Naocare za vid',2);

/*Table structure for table `proizvodjac` */

DROP TABLE IF EXISTS `proizvodjac`;

CREATE TABLE `proizvodjac` (
  `proizvodjacId` int(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `zemlja` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`proizvodjacId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `proizvodjac` */

insert  into `proizvodjac`(`proizvodjacId`,`naziv`,`zemlja`) values 
(1,'RayBan','Italija'),
(2,'Dior','Francuska'),
(3,'Roberto Cavalli','Italija');

/*Table structure for table `racun` */

DROP TABLE IF EXISTS `racun`;

CREATE TABLE `racun` (
  `brojRacuna` int(20) NOT NULL AUTO_INCREMENT,
  `datumKupovine` datetime DEFAULT NULL,
  `ukupanIznos` double DEFAULT NULL,
  `korisnikId` int(20) DEFAULT NULL,
  PRIMARY KEY (`brojRacuna`),
  KEY `korisnikId_fk` (`korisnikId`),
  CONSTRAINT `korisnikId_fk` FOREIGN KEY (`korisnikId`) REFERENCES `korisnik` (`korisnikId`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `racun` */

insert  into `racun`(`brojRacuna`,`datumKupovine`,`ukupanIznos`,`korisnikId`) values 
(50,'2002-02-20 12:00:00',112640,30),
(51,'2001-01-20 12:00:00',69560,30),
(52,'2005-05-20 12:00:00',27680,30),
(53,'2006-06-20 12:00:00',14590,30),
(54,'2002-02-20 12:00:00',42980,30),
(55,'2002-02-20 12:00:00',39380,30),
(56,'2006-06-20 13:00:00',30580,30),
(57,'2008-08-20 15:00:00',63260,30),
(58,'2002-02-20 15:00:00',16900,30),
(59,'2002-02-20 12:00:00',212930,30),
(60,'2001-01-20 11:00:00',46970,30),
(61,'2001-01-20 11:00:00',16900,30),
(62,'2002-02-20 12:00:00',29180,30),
(63,'2002-02-20 12:00:00',48390,30),
(64,'2002-02-20 12:00:00',111940,30),
(65,'2002-02-20 12:00:00',31980,30),
(66,'2002-02-20 12:00:00',59960,30),
(67,'2002-02-20 12:00:00',27180,30),
(68,'2002-02-20 12:00:00',33980,30),
(70,'2001-01-20 12:00:00',61160,30),
(71,'2010-10-20 10:00:00',62980,30),
(72,'2002-02-20 12:00:00',62980,30),
(73,'2002-02-20 12:00:00',62980,30),
(74,'2002-02-20 12:00:00',53160,30),
(75,'2002-02-20 12:00:00',141300,30),
(76,'2001-01-20 11:00:00',33980,30),
(77,'2005-05-20 12:00:00',12390,30),
(78,'2009-09-20 12:00:00',62980,30);

/*Table structure for table `stavkaracuna` */

DROP TABLE IF EXISTS `stavkaracuna`;

CREATE TABLE `stavkaracuna` (
  `redniBrojStavke` int(11) NOT NULL AUTO_INCREMENT,
  `brojRacuna` int(11) NOT NULL,
  `iznosStavke` double DEFAULT NULL,
  `proizvodId` int(11) DEFAULT NULL,
  `kolicina` int(11) DEFAULT NULL,
  PRIMARY KEY (`redniBrojStavke`,`brojRacuna`),
  KEY `redniBrojStavke` (`redniBrojStavke`),
  KEY `broj_rac_fk` (`brojRacuna`),
  KEY `proizvod_fk` (`proizvodId`),
  CONSTRAINT `broj_rac_fk` FOREIGN KEY (`brojRacuna`) REFERENCES `racun` (`brojRacuna`),
  CONSTRAINT `proizvod_fk` FOREIGN KEY (`proizvodId`) REFERENCES `proizvod` (`proizvodId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `stavkaracuna` */

insert  into `stavkaracuna`(`redniBrojStavke`,`brojRacuna`,`iznosStavke`,`proizvodId`,`kolicina`) values 
(1,71,29180,91,2),
(1,72,33800,92,2),
(1,74,25980,73,2),
(1,76,33980,76,2),
(1,77,12390,72,1),
(1,78,33800,92,2),
(2,74,27180,74,2),
(4,75,25980,77,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;