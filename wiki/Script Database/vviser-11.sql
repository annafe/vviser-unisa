create database vviser;
use vviser;
-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generato il: 20 gen, 2014 at 10:14 AM
-- Versione MySQL: 5.1.44
-- Versione PHP: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


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

--
-- Dump dei dati per la tabella `collaborazioni`
--


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
-- Dump dei dati per la tabella `dipartimento`
--

INSERT INTO `dipartimento` (`nome`, `facolta`) VALUES
('informatica', 'informatica'),
('studi e ricerche azi', 'economia');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dump dei dati per la tabella `eventovalutazione`
--

INSERT INTO `eventovalutazione` (`id`, `nome`, `numeroDiPubblicazioni`, `daData`, `aData`, `scadenza`) VALUES
(1, 'evento1', 2, '2012-12-01', '2013-01-31', '2013-01-31'),
(2, 'evento2', 3, '2013-12-01', '2014-01-31', '2014-01-31');

-- --------------------------------------------------------

--
-- Struttura della tabella `listavalutazione`
--

CREATE TABLE IF NOT EXISTS `listavalutazione` (
  `utente_email` varchar(50) NOT NULL,
  `eventoValutazione_id` int(11) NOT NULL,
  `suggerimento` text,
  `bloccato` enum('si','no') NOT NULL,
  PRIMARY KEY (`utente_email`,`eventoValutazione_id`),
  KEY `utente_email` (`utente_email`),
  KEY `eventoValutazione_id` (`eventoValutazione_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `listavalutazione`
--


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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=894144766 ;

--
-- Dump dei dati per la tabella `notifica`
--

INSERT INTO `notifica` (`id`, `tipo`, `stato`, `messaggio`, `mittente`, `destinatario`) VALUES
(145698456, 'conflitto', 'letto', 'La vostra pubblicazione "Innovative approaches for security of small artefa" è in conflitto con un altro utente.', 'nicolafrasca11@gmail.com', 'deufemia@unisa.it'),
(145698545, 'messaggio', 'letto', 'La vostra pubblicazione "Finite Element Dynamic Analysis of Anisotropic ela" presenta contenuti illegali.', 'mnappi@unisa.it', 'robdep@unisa.it'),
(154786332, 'messaggio', 'nonletto', 'La vostra pubblicazione "Biomechanics of traumatic brain injury" presenta errori nel titolo', 'rtagliaferri@unisa.it', 'deufemia@unisa.it'),
(365415854, 'conflitto', 'letto', 'La vostra pubblicazione "Cheating Immune Threshold Visual Secret Sharing" è in conflitto con un altro utente.', 'nicolafrasca11@gmail.com', 'alberto@unisa.it'),
(487565564, 'conflitto', 'letto', 'La vostra pubblicazione "Construction of the EF-based Runge-Kutta methods r" è in conflitto con un altro utente.', 'desantis11@gmail.com', 'robdep@unisa.it'),
(894144765, 'messaggio', 'nonletto', 'La vostra pubblicazione "The Labeled Maximum Matching Problem" presenta errori di uniformità con gli standard.', 'loia@unisa.it', 'alberto@unisa.it');

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

--
-- Dump dei dati per la tabella `partecipazioneavalutazione`
--


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
  `listacollaboratori` varchar(100) DEFAULT NULL,
  `descrizionecontenuti` text,
  `indirizzoweb` varchar(50) DEFAULT NULL,
  `parolechiavi` varchar(100) DEFAULT NULL,
  `editore` varchar(50) DEFAULT NULL,
  `numvolume` int(11) DEFAULT NULL,
  `totalepagine` int(11) DEFAULT NULL,
  `dapagina` int(11) DEFAULT NULL,
  `apagina` int(11) DEFAULT NULL,
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
-- Dump dei dati per la tabella `prodotto`
--

INSERT INTO `prodotto` (`isbn`, `titolo`, `annoPubblicazione`, `formatoPubblicazione`, `codiceDoi`, `diffusione`, `listacollaboratori`, `descrizionecontenuti`, `indirizzoweb`, `parolechiavi`, `editore`, `numvolume`, `totalepagine`, `dapagina`, `apagina`, `note`, `stato`, `bozza`, `tipologia`, `email_proprietario`) VALUES
('1284673284239', 'An asynchronous covert channel using spam', '2013-11-18', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NonValidato', 1, 'tesiDiDottorando', 'deufemia@unisa.it'),
('1657482300835', 'Finite Element Dynamic Analysis of Anisotropic ela', '2013-12-10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ValidatoDipartimento', 0, 'articoloSuRivista', 'robdep@unisa.it'),
('1726365549221', 'Innovative approaches for security of small artefa', '2013-03-03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NonValidato', 1, 'articoloSuLibro', 'deufemia@unisa.it'),
('1784003895123', 'Biomechanics of traumatic brain injury', '2013-08-19', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NonValidato', 1, 'articoloSuRivista', 'deufemia@unisa.it'),
('1844234301235', 'Degree-Optimal Routing for P2P Systems', '2009-11-04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Valutato', 0, 'articoloSuRivista', 'alberto@unisa.it'),
('2736456738291', 'Integrated design and control of plantwide systems', '2012-06-05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ValidatoComitatoArea', 0, 'articoloSuLibro', 'alberto@unisa.it'),
('2736475893748', 'Inverse analysis of the laser forming process by c', '2013-04-16', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Valutato', 0, 'prodottoArchitettoni', 'robdep@unisa.it'),
('2736478892913', 'Efficient preflow push algorithms', '2013-06-04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ValidatoComitatoArea', 0, 'prodottoArchitettoni', 'alberto@unisa.it'),
('2843763632467', 'The Labeled Maximum Matching Problem', '2012-04-02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NonValidato', 0, 'monografie', 'alberto@unisa.it'),
('6672819385476', 'Development and Evaluation of a Virtual Campus on ', '2013-06-17', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Valutato', 0, 'articoloSuLibro', 'robdep@unisa.it'),
('7432642372849', 'Cheating Immune Threshold Visual Secret Sharing', '2013-10-14', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NonValidato', 0, 'proceedings', 'alberto@unisa.it'),
('7643537883211', 'Limit cycles in nonlinear excitation of clusters o', '2013-08-13', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ValidatoDipartimento', 0, 'articoloSuLibro', 'deufemia@unisa.it'),
('8217312381929', 'An aggregation heuristic for large scale p-median ', '2014-01-08', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Valutato', 0, 'proceedings', 'robdep@unisa.it'),
('8432843274237', 'Construction of the EF-based Runge-Kutta methods r', '2013-12-25', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NonValidato', 0, 'monografie', 'robdep@unisa.it');

-- --------------------------------------------------------

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

--
-- Dump dei dati per la tabella `prodottoinconflitto`
--


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

--
-- Dump dei dati per la tabella `prodottolista`
--


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

--
-- Dump dei dati per la tabella `pubblicazionesurivista`
--


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

--
-- Dump dei dati per la tabella `rivista`
--

INSERT INTO `rivista` (`issn`, `nome`, `nomeAlternativo`, `editore`, `daAnno`, `adAnno`, `validita`) VALUES
('00457949', 'COMPUTERS & STRUCTUR', NULL, 'Elsevier', NULL, NULL, 1),
('03050548', 'COMPUTERS & OPERATIO', NULL, 'Elsevier', NULL, NULL, 1),
('09205489', 'COMPUTER STANDARDS &', 'Computer standards &', 'Elsevier', NULL, NULL, 1),
('14324350', ' THEORY OF COMPUTING', NULL, 'Springer', NULL, NULL, 1);

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

--
-- Dump dei dati per la tabella `sottomettiMIUR`
--


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
-- Dump dei dati per la tabella `tipologia`
--

INSERT INTO `tipologia` (`nome`, `descrizione`, `validita`, `da`, `a`) VALUES
('altroMinesteriale', NULL, 1, '1990-02-02', NULL),
('articoloSuLibro', NULL, 1, '1992-01-10', NULL),
('articoloSuRivista', NULL, 1, '1997-04-05', NULL),
('brevetto', NULL, 1, '2001-09-02', NULL),
('curatela', NULL, 1, '2002-04-04', NULL),
('monografie', NULL, 1, '2003-08-09', NULL),
('proceedings', NULL, 1, '2005-04-08', NULL),
('prodottoArchitettoni', NULL, 1, '2005-05-07', NULL),
('tesiDiDottorando', NULL, 1, '2006-02-09', NULL);

-- --------------------------------------------------------

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

--
-- Dump dei dati per la tabella `utente`
--

INSERT INTO `utente` (`email`, `password`, `codiceFiscale`, `provinciaDiNascita`, `comuneDiNascita`, `cognome`, `nome`, `dataDiNascita`, `tipologia`, `dipartimento_nome`) VALUES
('adinolfi@unisa.it', 'padinolfi', 'DNLPLA53L22F629Z', 'AV', 'avellino', 'adinolfi', 'paola', '1953-10-22', 'direttoreDiDipartimento', 'studi e ricerche azi'),
('afasano@unisa.it', 'afasano', 'FSNNTN57H22F629Z', 'AV', 'avellino', 'fasano', 'antonia', '1957-08-22', 'ricercatore', 'informatica'),
('alberto@unisa.it', 'negro1', 'NGRLBR56E21A309F', 'SA', 'salerno', 'negro', 'alberto', '1956-05-21', 'ricercatore', 'informatica'),
('desantis11@gmail.com', 'ndesantis', 'DSNNCL72F08A309F', 'SA', 'salerno', 'deSantis', 'nicola', '1972-06-08', 'amministratore', 'informatica'),
('deufemia@unisa.it', 'vdeufemia', 'DFMVNC73D23A309F', 'SA', 'salerno', 'deufemia', 'vincenzo', '1973-04-23', 'ricercatore', 'informatica'),
('loia@unisa.it', 'vloia', 'LOIVNC57B26B211L', 'NA', 'napoli', 'loia', 'vincenzo', '1957-02-26', 'direttoreDiDipartimento', 'informatica'),
('lspada@unisa.it', 'lspada', 'SPDLUC72H29A309F', 'SA', 'salerno', 'spada', 'luca', '1972-08-29', 'membroDelComitatoDiAteneo', 'informatica'),
('mnappi@unisa.it', 'mnappi', 'NPPMCH65B08A309F', 'SA', 'salerno', 'nappi', 'michele', '1965-02-08', 'membroDelComitatoDiAreaDidattica', 'informatica'),
('nicolafrasca11@gmail.com', 'nfrasca', 'FRSNCL60A10C409L', 'CE', 'caserta', 'frasca', 'nicola', '1960-01-10', 'amministratore', 'informatica'),
('robdep@unisa.it', 'rdeprisco', 'DPRRBR68F11B410O', 'SA', 'salerno', 'dePrisco', 'roberto', '1968-06-11', 'ricercatore', 'informatica'),
('rtagliaferri@unisa.it', 'rtagliaferri', 'TGLRBR62H17C409L', 'CE', 'caserta', 'tagliaferri', 'roberto', '1962-08-17', 'membroDelComitatoDiAreaDidattica', 'informatica'),
('tortora@unisa.it', 'gtortora', 'TRTGNV67A11B211L', 'NA', 'napoli', 'tortora', 'genoveffa', '1967-01-11', 'membroDelComitatoDiAteneo', 'informatica');

--
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
