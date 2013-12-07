-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generato il: Dic 07, 2013 alle 16:27
-- Versione del server: 5.5.32
-- Versione PHP: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `vviser`
--
CREATE DATABASE IF NOT EXISTS `vviser` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `vviser`;

-- --------------------------------------------------------

--
-- Struttura della tabella `categoria`
--

CREATE TABLE IF NOT EXISTS `categoria` (
  `nome` varchar(20) NOT NULL,
  `descrizione` text,
  `validita` tinyint(1) NOT NULL,
  PRIMARY KEY (`nome`)
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

-- --------------------------------------------------------

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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `utente.id` int(11) NOT NULL,
  `eventoValutazione.id` int(11) NOT NULL,
  `suggerimento` text NOT NULL,
  PRIMARY KEY (`id`,`utente.id`,`eventoValutazione.id`),
  KEY `utente.id` (`utente.id`),
  KEY `eventoValutazione.id` (`eventoValutazione.id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `notifica`
--

CREATE TABLE IF NOT EXISTS `notifica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` enum('conflitto','mancataSottomissione','messaggio','') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `partecipazioneavalutazione`
--

CREATE TABLE IF NOT EXISTS `partecipazioneavalutazione` (
  `utente_id` int(11) NOT NULL,
  `eventoValutazione_id` int(11) NOT NULL,
  PRIMARY KEY (`utente_id`,`eventoValutazione_id`),
  KEY `eventoValutazione_id` (`eventoValutazione_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `prodottiutente`
--

CREATE TABLE IF NOT EXISTS `prodottiutente` (
  `utente_id` int(11) NOT NULL,
  `prodotto_isbn` char(13) NOT NULL,
  PRIMARY KEY (`utente_id`,`prodotto_isbn`),
  KEY `prodotto_isbn` (`prodotto_isbn`)
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
  `note` text,
  `stato` enum('NonValidato','ValidatoDipartimento','ValidatoComitatoArea','Valutato') NOT NULL,
  `bozza` tinyint(1) NOT NULL,
  `categoria_nome` varchar(20) NOT NULL,
  PRIMARY KEY (`isbn`),
  KEY `categoria_nome` (`categoria_nome`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `prodottoinconflitto`
--

CREATE TABLE IF NOT EXISTS `prodottoinconflitto` (
  `Prodotto.isbn` char(13) NOT NULL,
  `Lista.id` int(11) NOT NULL,
  PRIMARY KEY (`Prodotto.isbn`,`Lista.id`),
  KEY `prodottoinconflitto_ibfk_2` (`Lista.id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `prodottolista`
--

CREATE TABLE IF NOT EXISTS `prodottolista` (
  `prodotto.isbn` char(13) NOT NULL,
  `lista.id` int(11) NOT NULL,
  `priorita` int(11) NOT NULL,
  PRIMARY KEY (`prodotto.isbn`,`lista.id`),
  KEY `lista.id` (`lista.id`)
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
-- Struttura della tabella `ricezionenotifica`
--

CREATE TABLE IF NOT EXISTS `ricezionenotifica` (
  `utente_id` int(11) NOT NULL,
  `notifica_id` int(11) NOT NULL,
  PRIMARY KEY (`utente_id`,`notifica_id`),
  KEY `notifica_id` (`notifica_id`)
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
-- Struttura della tabella `utente`
--

CREATE TABLE IF NOT EXISTS `utente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL,
  `codiceFiscale` char(16) NOT NULL,
  `username` varchar(20) NOT NULL,
  `provinciaDiNascita` char(2) NOT NULL,
  `comuneDiNascita` varchar(20) NOT NULL,
  `cognome` varchar(20) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `dataDiNascita` date NOT NULL,
  `tipologia` set('amministratore','ricercatore','membroDelComitatoDiAteneo','direttoreDiDipartimento','membroDelComitatoDiAreaDidattica','','','','','','','') NOT NULL,
  `dipartimento_nome` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dipartimento_nome` (`dipartimento_nome`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `listavalutazione`
--
ALTER TABLE `listavalutazione`
  ADD CONSTRAINT `listavalutazione_ibfk_1` FOREIGN KEY (`utente.id`) REFERENCES `utente` (`id`),
  ADD CONSTRAINT `listavalutazione_ibfk_2` FOREIGN KEY (`eventoValutazione.id`) REFERENCES `eventovalutazione` (`id`);

--
-- Limiti per la tabella `partecipazioneavalutazione`
--
ALTER TABLE `partecipazioneavalutazione`
  ADD CONSTRAINT `partecipazioneavalutazione_ibfk_1` FOREIGN KEY (`utente_id`) REFERENCES `utente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `partecipazioneavalutazione_ibfk_2` FOREIGN KEY (`eventoValutazione_id`) REFERENCES `eventovalutazione` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `prodottiutente`
--
ALTER TABLE `prodottiutente`
  ADD CONSTRAINT `prodottiutente_ibfk_1` FOREIGN KEY (`utente_id`) REFERENCES `utente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `prodottiutente_ibfk_2` FOREIGN KEY (`prodotto_isbn`) REFERENCES `prodotto` (`isbn`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `prodotto`
--
ALTER TABLE `prodotto`
  ADD CONSTRAINT `prodotto_ibfk_1` FOREIGN KEY (`categoria_nome`) REFERENCES `categoria` (`nome`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `prodottoinconflitto`
--
ALTER TABLE `prodottoinconflitto`
  ADD CONSTRAINT `prodottoinconflitto_ibfk_1` FOREIGN KEY (`Prodotto.isbn`) REFERENCES `prodotto` (`isbn`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `prodottoinconflitto_ibfk_2` FOREIGN KEY (`Lista.id`) REFERENCES `listavalutazione` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `prodottolista`
--
ALTER TABLE `prodottolista`
  ADD CONSTRAINT `prodottolista_ibfk_2` FOREIGN KEY (`lista.id`) REFERENCES `listavalutazione` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `prodottolista_ibfk_1` FOREIGN KEY (`prodotto.isbn`) REFERENCES `prodotto` (`isbn`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `pubblicazionesurivista`
--
ALTER TABLE `pubblicazionesurivista`
  ADD CONSTRAINT `pubblicazionesurivista_ibfk_1` FOREIGN KEY (`rivista_issn`) REFERENCES `rivista` (`issn`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `pubblicazionesurivista_ibfk_2` FOREIGN KEY (`prodotto_isbn`) REFERENCES `prodotto` (`isbn`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `ricezionenotifica`
--
ALTER TABLE `ricezionenotifica`
  ADD CONSTRAINT `ricezionenotifica_ibfk_1` FOREIGN KEY (`utente_id`) REFERENCES `utente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `ricezionenotifica_ibfk_2` FOREIGN KEY (`notifica_id`) REFERENCES `notifica` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `utente`
--
ALTER TABLE `utente`
  ADD CONSTRAINT `utente_ibfk_1` FOREIGN KEY (`dipartimento_nome`) REFERENCES `dipartimento` (`nome`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
