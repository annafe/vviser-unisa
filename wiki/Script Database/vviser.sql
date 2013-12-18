create database vviser;
use vviser;
-- phpMyAdmin SQL Dump
-- version 4.0.6deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generato il: Dic 16, 2013 alle 21:46
-- Versione del server: 5.5.34-0ubuntu0.13.10.1
-- Versione PHP: 5.5.3-1ubuntu2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `vviser`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `collaborazioni`
--

CREATE TABLE IF NOT EXISTS `collaborazioni` (
  `collaboratore` varchar(50) NOT NULL,
  `prodotto_isbn` char(13) NOT NULL,
  `proprietario` varchar(50) NOT NULL,
  `convalidato` tinyint(1) NOT NULL,
  PRIMARY KEY (`collaboratore`,`prodotto_isbn`),
  KEY `prodotto_isbn` (`prodotto_isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `dipartimento`
--

CREATE TABLE IF NOT EXISTS `dipartimento` (
  `nome` varchar(20) NOT NULL,
  `facolta` varchar(20) NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Struttura della tabella `eventovalutazione`
--

CREATE TABLE IF NOT EXISTS `eventovalutazione` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(20) NOT NULL,
  `numeroDiPubblicazioni` int(11) NOT NULL,
  `daData` date NOT NULL,
  `aData` date NOT NULL,
  `scadenza` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `listavalutazione`
--

CREATE TABLE IF NOT EXISTS `listavalutazione` (
  `utente_email` varchar(50) NOT NULL,
  `eventoValutazione_id` int(11) NOT NULL,
  `suggerimento` text NOT NULL,
  `bloccato` enum('si','no') NOT NULL,
  PRIMARY KEY (`utente_email`,`eventoValutazione_id`),
  KEY `utente_email` (`utente_email`),
  KEY `eventoValutazione_id` (`eventoValutazione_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `notifica`
--

CREATE TABLE IF NOT EXISTS `notifica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` enum('conflitto','mancataSottomissione','messaggio','') NOT NULL,
  `stato` enum('letto','nonletto') NOT NULL,
  `messaggio` text NOT NULL,
  `mittente` varchar(50) NOT NULL,
  `destinatario` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `notifica_ibfk_1` (`mittente`),
  KEY `notifica_ibfk_2` (`destinatario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `partecipazioneavalutazione`
--

CREATE TABLE IF NOT EXISTS `partecipazioneavalutazione` (
  `utente_email` varchar(50) NOT NULL,
  `eventoValutazione_id` int(11) NOT NULL,
  PRIMARY KEY (`utente_email`,`eventoValutazione_id`),
  KEY `eventoValutazione_id` (`eventoValutazione_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `prodotto`
--

CREATE TABLE IF NOT EXISTS `prodotto` (
  `isbn` char(13) NOT NULL,
  `titolo` varchar(50) NOT NULL,
  `annoPubblicazione` date NOT NULL,
  `formatoPubblicazione` varchar(10) DEFAULT NULL,
  `codiceDoi` varchar(50) DEFAULT NULL,
  `diffusione` varchar(20) DEFAULT NULL,
`listacollaboratori` varchar(100) default null,
`descrizionecontenuti` text,
`indirizzoweb` varchar(50),
`parolechiavi` varchar(100),
`editore` varchar(50),
`numvolume` int(11),
`totalepagine` int(11),
`dapagina` int(11),
`apagina` int(11),
`nazionalita_autore` varchar(50),	
  `note` text,
  `stato` enum('NonValidato','ValidatoDipartimento','ValidatoComitatoArea','Valutato') NOT NULL,
  `bozza` tinyint(1) NOT NULL,
  `tipologia` varchar(20) NOT NULL,
  `email_proprietario` varchar(50) NOT NULL,
  PRIMARY KEY (`isbn`),
  KEY `tipologia` (`tipologia`),
  KEY `email` (`email_proprietario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Struttura della tabella `prodottoinconflitto`
--

CREATE TABLE IF NOT EXISTS `prodottoinconflitto` (
  `Prodotto_isbn` char(13) NOT NULL,
  `utente_email` varchar(50) NOT NULL,
  `eventoValutazione_id` int(11) NOT NULL,
  PRIMARY KEY (`Prodotto_isbn`,`utente_email`,`eventoValutazione_id`),
  KEY `utente_email` (`utente_email`),
  KEY `eventoValutazione_id` (`eventoValutazione_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `prodottolista`
--

CREATE TABLE IF NOT EXISTS `prodottolista` (
  `prodotto_isbn` char(13) NOT NULL,
  `utente_email` varchar(50) NOT NULL,
  `eventoValutazione_id` int(11) NOT NULL,
  `priorita` int(11) NOT NULL,
  PRIMARY KEY (`prodotto_isbn`,`utente_email`,`eventoValutazione_id`),
  KEY `utente_email` (`utente_email`),
  KEY `eventoValutazione_id` (`eventoValutazione_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `pubblicazionesurivista`
--

CREATE TABLE IF NOT EXISTS `pubblicazionesurivista` (
  `rivista_issn` char(8) NOT NULL,
  `prodotto_isbn` char(13) NOT NULL,
  `volume` varchar(5) DEFAULT NULL,
  `daPagina` int(11) DEFAULT NULL,
  `aPagina` int(11) DEFAULT NULL,
  `totalePagine` int(11) DEFAULT NULL,
  PRIMARY KEY (`rivista_issn`,`prodotto_isbn`),
  KEY `prodotto_isbn` (`prodotto_isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `rivista`
--

CREATE TABLE IF NOT EXISTS `rivista` (
  `issn` char(8) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `nomeAlternativo` varchar(20) DEFAULT NULL,
  `editore` varchar(30) NOT NULL,
  `daAnno` date DEFAULT NULL,
  `adAnno` date DEFAULT NULL,
  `validita` tinyint(1) NOT NULL,
  PRIMARY KEY (`issn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `sottomettiMIUR`
--

CREATE TABLE IF NOT EXISTS `sottomettiMIUR` (
  `utente_email` varchar(50) NOT NULL,
  `prodotto_isbn` char(13) NOT NULL,
  PRIMARY KEY (`utente_email`,`prodotto_isbn`),
  KEY `prodotto_isbn` (`prodotto_isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `tipologia`
--

CREATE TABLE IF NOT EXISTS `tipologia` (
  `nome` varchar(20) NOT NULL,
  `descrizione` text,
  `validita` tinyint(1) NOT NULL,
  `da` date NOT NULL,
  `a` date DEFAULT NULL,
  PRIMARY KEY (`nome`,`da`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Struttura della tabella `utente`
--

CREATE TABLE IF NOT EXISTS `utente` (
  `email` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL,
  `codiceFiscale` char(16) NOT NULL,
  `provinciaDiNascita` char(2) NOT NULL,
  `comuneDiNascita` varchar(20) NOT NULL,
  `cognome` varchar(20) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `dataDiNascita` date NOT NULL,
  `tipologia` set('amministratore','ricercatore','membroDelComitatoDiAteneo','direttoreDiDipartimento','membroDelComitatoDiAreaDidattica','','','','','','','') NOT NULL,
  `dipartimento_nome` varchar(20) NOT NULL,
  PRIMARY KEY (`email`),
  KEY `dipartimento_nome` (`dipartimento_nome`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `collaborazioni`
--
ALTER TABLE `collaborazioni`
  ADD CONSTRAINT `collaborazioni_ibfk_1` FOREIGN KEY (`collaboratore`) REFERENCES `utente` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `collaborazioni_ibfk_2` FOREIGN KEY (`prodotto_isbn`) REFERENCES `prodotto` (`isbn`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `listavalutazione`
--
ALTER TABLE `listavalutazione`
  ADD CONSTRAINT `listavalutazione_ibfk_1` FOREIGN KEY (`utente_email`) REFERENCES `utente` (`email`),
  ADD CONSTRAINT `listavalutazione_ibfk_2` FOREIGN KEY (`eventoValutazione_id`) REFERENCES `eventovalutazione` (`id`);

--
-- Limiti per la tabella `notifica`
--
ALTER TABLE `notifica`
  ADD CONSTRAINT `notifica_ibfk_1` FOREIGN KEY (`mittente`) REFERENCES `utente` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `notifica_ibfk_2` FOREIGN KEY (`destinatario`) REFERENCES `utente` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `partecipazioneavalutazione`
--
ALTER TABLE `partecipazioneavalutazione`
  ADD CONSTRAINT `partecipazioneavalutazione_ibfk_1` FOREIGN KEY (`utente_email`) REFERENCES `utente` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `partecipazioneavalutazione_ibfk_2` FOREIGN KEY (`eventoValutazione_id`) REFERENCES `eventovalutazione` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `prodotto`
--
ALTER TABLE `prodotto`
  ADD CONSTRAINT `prodotto_ibfk_1` FOREIGN KEY (`tipologia`) REFERENCES `tipologia` (`nome`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `prodotto_ibfk_2` FOREIGN KEY (`email_proprietario`) REFERENCES `utente` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `prodottoinconflitto`
--
ALTER TABLE `prodottoinconflitto`
  ADD CONSTRAINT `prodottoinconflitto_ibfk_1` FOREIGN KEY (`utente_email`) REFERENCES `listavalutazione` (`utente_email`),
  ADD CONSTRAINT `prodottoinconflitto_ibfk_2` FOREIGN KEY (`eventoValutazione_id`) REFERENCES `listavalutazione` (`eventoValutazione_id`),
  ADD CONSTRAINT `prodottoinconflitto_ibfk_3` FOREIGN KEY (`Prodotto_isbn`) REFERENCES `prodotto` (`isbn`);

--
-- Limiti per la tabella `prodottolista`
--
ALTER TABLE `prodottolista`
  ADD CONSTRAINT `prodottolista_ibfk_1` FOREIGN KEY (`prodotto_isbn`) REFERENCES `prodotto` (`isbn`),
  ADD CONSTRAINT `prodottolista_ibfk_2` FOREIGN KEY (`utente_email`) REFERENCES `listavalutazione` (`utente_email`),
  ADD CONSTRAINT `prodottolista_ibfk_3` FOREIGN KEY (`eventoValutazione_id`) REFERENCES `listavalutazione` (`eventoValutazione_id`);

--
-- Limiti per la tabella `pubblicazionesurivista`
--
ALTER TABLE `pubblicazionesurivista`
  ADD CONSTRAINT `pubblicazionesurivista_ibfk_1` FOREIGN KEY (`rivista_issn`) REFERENCES `rivista` (`issn`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `pubblicazionesurivista_ibfk_2` FOREIGN KEY (`prodotto_isbn`) REFERENCES `prodotto` (`isbn`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `sottomettiMIUR`
--
ALTER TABLE `sottomettiMIUR`
  ADD CONSTRAINT `sottometti_ibfk_1` FOREIGN KEY (`utente_email`) REFERENCES `utente` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `sottometti_ibfk_2` FOREIGN KEY (`prodotto_isbn`) REFERENCES `prodotto` (`isbn`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `utente`
--
ALTER TABLE `utente`
  ADD CONSTRAINT `utente_ibfk_1` FOREIGN KEY (`dipartimento_nome`) REFERENCES `dipartimento` (`nome`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
