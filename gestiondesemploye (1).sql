-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 01 juin 2020 à 09:58
-- Version du serveur :  5.7.26
-- Version de PHP :  7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gestiondesemploye`
--

-- --------------------------------------------------------

--
-- Structure de la table `congé`
--

DROP TABLE IF EXISTS `congé`;
CREATE TABLE IF NOT EXISTS `congé` (
  `id` bigint(20) NOT NULL,
  `date_de_debut` date DEFAULT NULL,
  `periode` int(11) DEFAULT NULL,
  `congee` bigint(20) DEFAULT NULL,
  `employe` bigint(20) DEFAULT NULL,
  `raison` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhqbqod7o6xw5axoiuvp1sonn4` (`congee`),
  KEY `FK6dovv0gq9mk1mjhmrrkvaetjd` (`employe`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `congé`
--

INSERT INTO `congé` (`id`, `date_de_debut`, `periode`, `congee`, `employe`, `raison`) VALUES
(1, '2017-05-19', 20, 1, 2, NULL),
(2, '2020-05-12', 15, 2, 2, NULL),
(59, '2020-05-23', 9, 3, 2, NULL),
(60, '2020-05-23', 9, 4, 2, NULL),
(119, '2019-05-20', 11, 2, 2, NULL),
(123, '2020-05-21', 9, 1, 120, NULL),
(124, '2020-05-20', 7, 3, 120, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `demane_de_document`
--

DROP TABLE IF EXISTS `demane_de_document`;
CREATE TABLE IF NOT EXISTS `demane_de_document` (
  `id` bigint(20) NOT NULL,
  `date_demande` date DEFAULT NULL,
  `etat` varchar(255) DEFAULT NULL,
  `maniere_de_retrait` varchar(255) DEFAULT NULL,
  `employe` bigint(20) DEFAULT NULL,
  `type_de_document` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcke1pv0x3ef0c7llv9vtws3sv` (`employe`),
  KEY `FKow6xx98e6vsrqi15xf1rx8dke` (`type_de_document`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `demane_de_document`
--

INSERT INTO `demane_de_document` (`id`, `date_demande`, `etat`, `maniere_de_retrait`, `employe`, `type_de_document`) VALUES
(1, '2020-05-13', 'non traité', 'par email', 2, 1),
(3, '2020-05-20', 'non traité', 'par guichet', 2, 2),
(67, '2020-05-22', 'non traité', 'papier', 2, 1),
(125, '2020-05-28', 'non traité', 'gmail', 120, 1),
(126, '2020-05-28', 'non traité', 'gmail', 120, 2),
(141, '2020-05-29', 'traité', 'gmail', 116, 1);

-- --------------------------------------------------------

--
-- Structure de la table `departement`
--

DROP TABLE IF EXISTS `departement`;
CREATE TABLE IF NOT EXISTS `departement` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `chefdoti` int(11) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `departement`
--

INSERT INTO `departement` (`id`, `nom`, `chefdoti`, `fullname`) VALUES
(1, 'mathematique', 1628, 'nabila morabiti'),
(2, 'physique', 1628, 'nabila morabiti'),
(25, 'informatique', 1628, 'nabila morabiti'),
(27, 'tec', 1628, 'nabila morabiti'),
(96, 'biologie', 1628, 'nabila morabiti');

-- --------------------------------------------------------

--
-- Structure de la table `dep_fonction`
--

DROP TABLE IF EXISTS `dep_fonction`;
CREATE TABLE IF NOT EXISTS `dep_fonction` (
  `id` bigint(20) NOT NULL,
  `departemant` bigint(20) DEFAULT NULL,
  `fonction` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt6q8ox9pi8clcf9utakoq8reb` (`departemant`),
  KEY `FKajkwd1o3s0ly22lg7htc2fcly` (`fonction`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `dep_fonction`
--

INSERT INTO `dep_fonction` (`id`, `departemant`, `fonction`) VALUES
(1, 1, 1),
(2, 2, 31),
(30, 1, 2),
(99, 1, 97),
(100, 2, 97),
(103, 2, 101),
(104, 25, 97),
(106, 25, 105),
(108, 25, 107),
(112, 25, 111);

-- --------------------------------------------------------

--
-- Structure de la table `emoluments`
--

DROP TABLE IF EXISTS `emoluments`;
CREATE TABLE IF NOT EXISTS `emoluments` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `montant` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `emoluments`
--

INSERT INTO `emoluments` (`id`, `libelle`, `montant`) VALUES
(1, 'idemFamialieleMarocaine', 400),
(2, 'idemDeLaResidence', 300),
(3, 'allocationDeEncadrement', 120),
(4, 'allocationDeEnseignement', 180);

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

DROP TABLE IF EXISTS `employe`;
CREATE TABLE IF NOT EXISTS `employe` (
  `id` bigint(20) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `cin` varchar(22) DEFAULT NULL,
  `compte_bancaire_rib` int(11) DEFAULT NULL,
  `date_avancement_prevue` date DEFAULT NULL,
  `date_de_naissance` date DEFAULT NULL,
  `date_de_prochain_note` date DEFAULT NULL,
  `date_entree` date DEFAULT NULL,
  `date_prochain_evaluation` date DEFAULT NULL,
  `date_sortie` date DEFAULT NULL,
  `doti` varchar(22) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enfants` int(11) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `lieu_de_naissance` varchar(255) DEFAULT NULL,
  `pays` varchar(255) DEFAULT NULL,
  `situation_familiale` varchar(255) DEFAULT NULL,
  `tel` int(11) DEFAULT NULL,
  `dep` bigint(20) DEFAULT NULL,
  `dernier_grade` bigint(20) DEFAULT NULL,
  `dernier_note` bigint(20) DEFAULT NULL,
  `sup` bigint(20) DEFAULT NULL,
  `fonction` bigint(20) DEFAULT NULL,
  `solde_restantes_conge_exceptionnel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmcsf3mebmnyuacokv52ul95dk` (`dep`),
  KEY `FKr8eud0mtpur1eng2bo2fc1mh1` (`dernier_grade`),
  KEY `FKfeolcnb0ikwlx0kejmf5ele79` (`dernier_note`),
  KEY `FK2l27r1rhetll8m7miorp66e0u` (`fonction`),
  KEY `FKqk3yqri81i2s97g2283akxeua` (`sup`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `employe`
--

INSERT INTO `employe` (`id`, `adresse`, `cin`, `compte_bancaire_rib`, `date_avancement_prevue`, `date_de_naissance`, `date_de_prochain_note`, `date_entree`, `date_prochain_evaluation`, `date_sortie`, `doti`, `email`, `enfants`, `full_name`, `gender`, `lieu_de_naissance`, `pays`, `situation_familiale`, `tel`, `dep`, `dernier_grade`, `dernier_note`, `sup`, `fonction`, `solde_restantes_conge_exceptionnel`) VALUES
(2, 'N252 sokoma askejour', '1628', NULL, '2020-05-24', '2020-05-28', '2020-05-24', '2020-05-27', '2020-05-24', NULL, '1628', 'abiabiya10042003@gmail.com\r\n', 3, 'nabila morabiti', 'Homme', NULL, 'Marrakech', 'Celebataire', 706019991, 1, 3, 2, NULL, 1, 0),
(19, 'N252 sokoma askejour', '987', NULL, '2020-05-24', '2020-05-28', '2020-05-24', '2020-05-14', '2020-05-24', NULL, '1627', 'hamza@gmail.com', 2, 'hamza shadeau', 'Femme', NULL, 'Marrakech', 'Celebataire', 706019991, 1, 23, 1, 2, 1, 5),
(53, 'N252 sokoma askejour', '1200', 56788, NULL, '2020-05-08', '2021-05-24', '2020-05-16', '2021-05-19', NULL, '1299', 'hamza@gmail.com', NULL, 'hamza', 'Homme', 'marrakech', 'Marrakech', 'Celebataire', 5678, 1, 54, NULL, 2, 1, 4),
(56, 'N252 sokoma askejour', '1234', 123456, NULL, '2020-05-08', '2021-05-24', '2020-05-08', '2020-05-24', NULL, '1333', 'ayoub@gmail.com', NULL, 'ayoub', 'Homme', 'marrakech', 'Marrakech', 'Celebataire', 123456, 1, 5, NULL, 2, 1, 10),
(116, 'N252 sokoma askejour', 'EE4527', 12345, NULL, '1977-05-10', '2021-05-19', '2020-05-18', '2023-05-28', NULL, '99EE4527', 'abiabiya10042003@gmail.com', 3, 'rachid bouigrouane', 'Homme', 'marrakech', 'Marrakech', 'Marie', 7868564, 1, 117, NULL, 2, 1, 10),
(120, 'N252 sokoma askejour', '12435', 12349875, NULL, '1996-06-12', '2021-05-28', '2020-05-05', '2022-05-29', NULL, 'fstg12435', 'bendrimou@gmail.com', 11, 'ayoub bendrimoU', 'Homme', 'marrakech', 'Marrakech', 'Marie', 67676433, 25, 139, 138, 2, 111, 3);

-- --------------------------------------------------------

--
-- Structure de la table `fonction`
--

DROP TABLE IF EXISTS `fonction`;
CREATE TABLE IF NOT EXISTS `fonction` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `fonction`
--

INSERT INTO `fonction` (`id`, `libelle`) VALUES
(1, 'prof analyse'),
(2, 'prof algebre'),
(31, 'prof mecanique'),
(97, 'chef departement'),
(101, 'prof optique'),
(105, 'prof java'),
(107, 'prof language c'),
(111, 'paython');

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

DROP TABLE IF EXISTS `formation`;
CREATE TABLE IF NOT EXISTS `formation` (
  `id` bigint(20) NOT NULL,
  `annee` date DEFAULT NULL,
  `attestation` varchar(255) DEFAULT NULL,
  `domaine` varchar(255) DEFAULT NULL,
  `mention` varchar(255) DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL,
  `établissement` varchar(255) DEFAULT NULL,
  `employe` bigint(20) DEFAULT NULL,
  `etablissement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcpynwso3d4a89t34sbisxjwl3` (`employe`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`id`, `annee`, `attestation`, `domaine`, `mention`, `ville`, `établissement`, `employe`, `etablissement`) VALUES
(1, '2020-05-13', 'attestation1', 'domaine1', 'mention1', 'marrakech', '', 2, 'etablissement1'),
(2, '2020-05-14', 'attestation2', 'domaine2', 'mention2', 'marrakech', '', 2, 'etablissment2'),
(68, '2020-05-20', 'developpement', 'info', 'très bien', 'marrakech', NULL, 2, 'termidi'),
(5, '2020-05-20', 'attestation5', 'domaine5', 'mention5', 'ville5', NULL, 19, 'etablissement5'),
(7, '2020-05-20', 'attestaion6', 'domaine6', 'mention6', 'ville6', '', 19, 'etablissement6'),
(127, '2020-05-19', 'coaching', 'informatique', 'très bien', 'marrakech', NULL, 120, 'fstg'),
(128, '2020-05-20', 'developpement', 'info2', 'bien', 'tanger', NULL, 120, 'fstg2');

-- --------------------------------------------------------

--
-- Structure de la table `grade`
--

DROP TABLE IF EXISTS `grade`;
CREATE TABLE IF NOT EXISTS `grade` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `nombre_de_poste_non_occupe` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `grade`
--

INSERT INTO `grade` (`id`, `libelle`, `nombre_de_poste_non_occupe`) VALUES
(1, 'grade1', 10),
(2, 'grade2', 10),
(5, 'grade5', 10),
(3, 'grade3', 10),
(26, 'grade4', 20),
(6, 'grade6', 10),
(7, 'grade7', 10),
(8, 'grade8', 10),
(109, 'grade9', 10),
(110, 'grade10', 20);

-- --------------------------------------------------------

--
-- Structure de la table `grade_employe`
--

DROP TABLE IF EXISTS `grade_employe`;
CREATE TABLE IF NOT EXISTS `grade_employe` (
  `id` bigint(20) NOT NULL,
  `date_de_affectation` date DEFAULT NULL,
  `grade` bigint(20) DEFAULT NULL,
  `etat` varchar(255) DEFAULT NULL,
  `doti` varchar(22) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7tnmht62b156ybpetl0sxadv0` (`grade`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `grade_employe`
--

INSERT INTO `grade_employe` (`id`, `date_de_affectation`, `grade`, `etat`, `doti`) VALUES
(18, '2020-05-27', 1, 'en traitement', '1628'),
(20, '2020-05-14', 1, 'en traitement', '1627'),
(3, '2020-05-19', 1, 'traité', '1628'),
(22, '2020-05-27', 1, 'en traitement', '1628'),
(23, '2020-05-14', 1, 'traité', '1627'),
(54, '2020-05-08', 2, NULL, '1299'),
(5, '2020-05-12', 3, NULL, '1333'),
(94, NULL, 26, 'en traitement', '1333'),
(117, '2020-05-19', 109, NULL, '99EE4527'),
(121, '2020-05-13', 26, NULL, 'fstg12435'),
(139, '2020-05-28', 5, 'traité', 'fstg12435');

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(142),
(142),
(142),
(142),
(142),
(142),
(142),
(142),
(142),
(142),
(142),
(142),
(142),
(142),
(142),
(142),
(142),
(142),
(142),
(142),
(142),
(142),
(142),
(142),
(142),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1);

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

DROP TABLE IF EXISTS `note`;
CREATE TABLE IF NOT EXISTS `note` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `mention` double DEFAULT NULL,
  `remarque` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `note`
--

INSERT INTO `note` (`id`, `libelle`, `mention`, `remarque`) VALUES
(1, 'noteDeAffectationDesTachesLiéeAuTravail', 17.5, 'remarque1'),
(2, 'noteDeRentabilite', 20, 'remarque2'),
(3, 'noteDeCapacitéDeOrganisation', 13, 'remarque3'),
(4, 'noteDeCompotement', 16.4, 'remarque4'),
(5, 'noteDeRechercheEtDeInnovation', 15, 'remarque5'),
(78, NULL, 17.5, 'assez bien'),
(79, NULL, 16, 'bien'),
(80, NULL, 12, 'assez bien'),
(81, NULL, 10, 'passable'),
(82, NULL, 19, 'tres bien'),
(133, NULL, 18, 'remarque6'),
(134, NULL, 16, 'bien'),
(135, NULL, 15, 'assez bien'),
(136, NULL, 19, 'remaque2'),
(137, NULL, 20, 'remarque1');

-- --------------------------------------------------------

--
-- Structure de la table `note_general_de_annee`
--

DROP TABLE IF EXISTS `note_general_de_annee`;
CREATE TABLE IF NOT EXISTS `note_general_de_annee` (
  `id` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `mention` varchar(255) DEFAULT NULL,
  `moyen_general` double DEFAULT NULL,
  `note_de_affectation_des_taches_liee_au_travail` bigint(20) DEFAULT NULL,
  `note_de_capacite_de_organisation` bigint(20) DEFAULT NULL,
  `note_de_compotement` bigint(20) DEFAULT NULL,
  `note_de_recherche_et_de_innovation` bigint(20) DEFAULT NULL,
  `note_de_rentabilite` bigint(20) DEFAULT NULL,
  `etat` varchar(255) DEFAULT NULL,
  `employe_doti` varchar(22) DEFAULT NULL,
  `fuul_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn8s5lg3q1g6mh0f309ri8nql1` (`note_de_affectation_des_taches_liee_au_travail`),
  KEY `FKch6qnewx6vus3ysexdfd7vror` (`note_de_capacite_de_organisation`),
  KEY `FKi4160wh2lnb74flkih8ghu8tr` (`note_de_compotement`),
  KEY `FKsb0cinu2512lrht82t9mtitrj` (`note_de_recherche_et_de_innovation`),
  KEY `FKm3vw1oinxbo1iq8wqvur1te93` (`note_de_rentabilite`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `note_general_de_annee`
--

INSERT INTO `note_general_de_annee` (`id`, `date`, `mention`, `moyen_general`, `note_de_affectation_des_taches_liee_au_travail`, `note_de_capacite_de_organisation`, `note_de_compotement`, `note_de_recherche_et_de_innovation`, `note_de_rentabilite`, `etat`, `employe_doti`, `fuul_name`) VALUES
(1, '2020-05-21', 'mention1', 18.5, 1, 3, 4, 5, 2, 'non traite', '1628', 'hamza bouigrouane'),
(2, '2020-05-15', 'mention2', 17.5, 1, 3, 4, 5, 2, 'non traite', '1628', 'hamza bouigrouane'),
(5, '2020-05-16', 'mention5', 16.380000000000003, 1, 3, 4, 5, 2, NULL, '1627', 'hamza shadeau'),
(6, '2020-05-21', 'mention7', 16.380000000000003, 1, 3, 4, 5, 2, NULL, '1627', 'hamza shadeau'),
(83, '2020-05-20', NULL, 14.9, 78, 79, 80, 81, 82, 'non traite', '1627', 'hamza shadeau'),
(138, '2020-05-26', NULL, 17.6, 133, 134, 135, 136, 137, NULL, 'fstg12435', 'ayoub bendrimoU');

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

DROP TABLE IF EXISTS `notification`;
CREATE TABLE IF NOT EXISTS `notification` (
  `id` bigint(20) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `notification`
--

INSERT INTO `notification` (`id`, `type`) VALUES
(1, 'save'),
(2, 'update'),
(3, 'delete'),
(4, 'imprimer');

-- --------------------------------------------------------

--
-- Structure de la table `notification_employe`
--

DROP TABLE IF EXISTS `notification_employe`;
CREATE TABLE IF NOT EXISTS `notification_employe` (
  `id` bigint(20) NOT NULL,
  `date_de_notification` datetime DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `employe` bigint(20) DEFAULT NULL,
  `notification` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKquie4di0s61lb135uhh85pl53` (`employe`),
  KEY `FK6eonh5263ksu3s63aj9q8hjhs` (`notification`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `notification_employe`
--

INSERT INTO `notification_employe` (`id`, `date_de_notification`, `libelle`, `employe`, `notification`) VALUES
(1, '2020-05-14 02:03:06', 'save employe', 2, 1),
(2, '2020-05-21 03:13:13', 'save note', 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `permanence_administrative`
--

DROP TABLE IF EXISTS `permanence_administrative`;
CREATE TABLE IF NOT EXISTS `permanence_administrative` (
  `id` bigint(20) NOT NULL,
  `periode` int(11) DEFAULT NULL,
  `periode_de_recuperation` int(11) DEFAULT NULL,
  `recuperation` bit(1) DEFAULT NULL,
  `employe` bigint(20) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj511f80de92poew8npmcj6852` (`employe`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `permanence_administrative`
--

INSERT INTO `permanence_administrative` (`id`, `periode`, `periode_de_recuperation`, `recuperation`, `employe`, `date`) VALUES
(1, 20, 30, b'0', 2, '2020-05-12'),
(2, 15, 10, b'0', 19, '2020-05-08');

-- --------------------------------------------------------

--
-- Structure de la table `prix`
--

DROP TABLE IF EXISTS `prix`;
CREATE TABLE IF NOT EXISTS `prix` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `prix`
--

INSERT INTO `prix` (`id`, `libelle`) VALUES
(1, 'prix1'),
(2, 'prix2');

-- --------------------------------------------------------

--
-- Structure de la table `prix_employe`
--

DROP TABLE IF EXISTS `prix_employe`;
CREATE TABLE IF NOT EXISTS `prix_employe` (
  `id` bigint(20) NOT NULL,
  `date_de_obtenation` date DEFAULT NULL,
  `employe` bigint(20) DEFAULT NULL,
  `prix` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKohq70aidpp77w6y6f553v9ro` (`employe`),
  KEY `FK9vp0qil39i0hyarsl1mxbw5ue` (`prix`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `prix_employe`
--

INSERT INTO `prix_employe` (`id`, `date_de_obtenation`, `employe`, `prix`) VALUES
(1, '2020-05-13', 2, 1),
(2, '2020-05-07', 2, 2),
(69, '2020-05-06', 2, 1),
(5, '2020-05-15', 19, 1),
(6, '2020-05-17', 19, 2),
(77, '2020-05-16', 19, 1),
(129, '2020-05-27', 120, 1),
(130, '2020-05-26', 120, 2);

-- --------------------------------------------------------

--
-- Structure de la table `punition`
--

DROP TABLE IF EXISTS `punition`;
CREATE TABLE IF NOT EXISTS `punition` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `punition`
--

INSERT INTO `punition` (`id`, `libelle`, `type`) VALUES
(1, 'libelle1', 'type1'),
(2, 'libelle2', 'type2');

-- --------------------------------------------------------

--
-- Structure de la table `punition_employe`
--

DROP TABLE IF EXISTS `punition_employe`;
CREATE TABLE IF NOT EXISTS `punition_employe` (
  `id` bigint(20) NOT NULL,
  `date_obtenation` date DEFAULT NULL,
  `employe` bigint(20) DEFAULT NULL,
  `punition` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpej5xp47xku0yd4iv1j9gynug` (`employe`),
  KEY `FKrf9p9v19jckk2f91p2mqbq5q1` (`punition`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `punition_employe`
--

INSERT INTO `punition_employe` (`id`, `date_obtenation`, `employe`, `punition`) VALUES
(1, '2020-05-14', 2, 1),
(2, '2020-05-08', 2, 2),
(5, '2020-05-18', 19, 1),
(6, '2020-05-19', 19, 2),
(131, '2020-05-19', 120, 1),
(132, '2020-05-25', 120, 2);

-- --------------------------------------------------------

--
-- Structure de la table `rapport_de_evaluation`
--

DROP TABLE IF EXISTS `rapport_de_evaluation`;
CREATE TABLE IF NOT EXISTS `rapport_de_evaluation` (
  `id` bigint(20) NOT NULL,
  `mention` varchar(255) DEFAULT NULL,
  `moyen` double DEFAULT NULL,
  `remarques` varchar(255) DEFAULT NULL,
  `nouveau_grade` bigint(20) DEFAULT NULL,
  `employe` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhkid0v6wfu2w4c2f949vvj5vb` (`employe`),
  KEY `FK6f9rgenvywutwbrw4bdkpy4yc` (`nouveau_grade`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `rapport_de_evaluation`
--

INSERT INTO `rapport_de_evaluation` (`id`, `mention`, `moyen`, `remarques`, `nouveau_grade`, `employe`) VALUES
(1, 'mention1', 18.5, 'remarque1', 3, 2),
(75, 'rapide', 18.5, NULL, 74, 19),
(95, NULL, NULL, NULL, 94, 56),
(140, 'rapide', 17.6, NULL, 139, 120);

-- --------------------------------------------------------

--
-- Structure de la table `rapport_de_evaluation_formation`
--

DROP TABLE IF EXISTS `rapport_de_evaluation_formation`;
CREATE TABLE IF NOT EXISTS `rapport_de_evaluation_formation` (
  `rapport_de_evaluation_id` bigint(20) NOT NULL,
  `formation` bigint(20) NOT NULL,
  UNIQUE KEY `UK_1ycsc7lim6qiqv7jyv9hx6oqy` (`formation`),
  KEY `FKrkc6txg4vxyupijk4hn9ryarf` (`rapport_de_evaluation_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `rapport_de_evaluation_formation`
--

INSERT INTO `rapport_de_evaluation_formation` (`rapport_de_evaluation_id`, `formation`) VALUES
(1, 1),
(1, 2),
(75, 5),
(75, 7),
(140, 127),
(140, 128);

-- --------------------------------------------------------

--
-- Structure de la table `rapport_de_evaluation_note_generale`
--

DROP TABLE IF EXISTS `rapport_de_evaluation_note_generale`;
CREATE TABLE IF NOT EXISTS `rapport_de_evaluation_note_generale` (
  `rapport_de_evaluation_id` bigint(20) NOT NULL,
  `note_generale` bigint(20) NOT NULL,
  UNIQUE KEY `UK_bwsi9176fir2s7pa96v49yiy3` (`note_generale`),
  KEY `FKofkrx6m9maa4vhqe1yfi7c0mx` (`rapport_de_evaluation_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `rapport_de_evaluation_note_generale`
--

INSERT INTO `rapport_de_evaluation_note_generale` (`rapport_de_evaluation_id`, `note_generale`) VALUES
(1, 1),
(1, 2),
(75, 5),
(75, 6),
(140, 138);

-- --------------------------------------------------------

--
-- Structure de la table `rapport_de_evaluation_prix`
--

DROP TABLE IF EXISTS `rapport_de_evaluation_prix`;
CREATE TABLE IF NOT EXISTS `rapport_de_evaluation_prix` (
  `rapport_de_evaluation_id` bigint(20) NOT NULL,
  `prix` bigint(20) NOT NULL,
  UNIQUE KEY `UK_bgc83krwgeb7r2g6yye21gucm` (`prix`),
  KEY `FKcv8scksd2e6pforpge73fcm6p` (`rapport_de_evaluation_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `rapport_de_evaluation_prix`
--

INSERT INTO `rapport_de_evaluation_prix` (`rapport_de_evaluation_id`, `prix`) VALUES
(1, 1),
(1, 2),
(75, 6),
(75, 77),
(140, 129),
(140, 130);

-- --------------------------------------------------------

--
-- Structure de la table `rapport_de_evaluation_punition`
--

DROP TABLE IF EXISTS `rapport_de_evaluation_punition`;
CREATE TABLE IF NOT EXISTS `rapport_de_evaluation_punition` (
  `rapport_de_evaluation_id` bigint(20) NOT NULL,
  `punition` bigint(20) NOT NULL,
  UNIQUE KEY `UK_r7j0ek2wpagmswoa1yxe9lw49` (`punition`),
  KEY `FK2yb24apv7slp55jqgce2aopwo` (`rapport_de_evaluation_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `rapport_de_evaluation_punition`
--

INSERT INTO `rapport_de_evaluation_punition` (`rapport_de_evaluation_id`, `punition`) VALUES
(1, 1),
(1, 2),
(75, 6),
(75, 5),
(140, 131),
(140, 132);

-- --------------------------------------------------------

--
-- Structure de la table `revenu`
--

DROP TABLE IF EXISTS `revenu`;
CREATE TABLE IF NOT EXISTS `revenu` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `montant` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `revenu`
--

INSERT INTO `revenu` (`id`, `libelle`, `montant`) VALUES
(1, 'mutuelleCaisseRetraitEtDeces', 200),
(2, 'impotSurLeRevenu', 300),
(3, 'assuranceMaladieObligatoire', 400),
(4, 'caisseMarocaineDeretrait', 200);

-- --------------------------------------------------------

--
-- Structure de la table `salaire_employe`
--

DROP TABLE IF EXISTS `salaire_employe`;
CREATE TABLE IF NOT EXISTS `salaire_employe` (
  `id` bigint(20) NOT NULL,
  `monatnt_modifie` double DEFAULT NULL,
  `salaire_net` double DEFAULT NULL,
  `allocation_de_encadrement` bigint(20) DEFAULT NULL,
  `allocation_de_enseignement` bigint(20) DEFAULT NULL,
  `assurance_maladie_obligatoire` bigint(20) DEFAULT NULL,
  `caisse_marocaine_deretrait` bigint(20) DEFAULT NULL,
  `employe` bigint(20) DEFAULT NULL,
  `idem_de_la_residence` bigint(20) DEFAULT NULL,
  `idem_famialiele_marocaine` bigint(20) DEFAULT NULL,
  `impot_sur_le_revenu` bigint(20) DEFAULT NULL,
  `mutuelle_caisse_retrait_et_deces` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKli6cb608v39r8gudcs4ebya08` (`allocation_de_encadrement`),
  KEY `FK3amx12fuyckis28qo0os3fli5` (`allocation_de_enseignement`),
  KEY `FKa2l68nxlj93bnqx623wapbjy3` (`assurance_maladie_obligatoire`),
  KEY `FK8inu71iu3ou45tet4swan6p0b` (`caisse_marocaine_deretrait`),
  KEY `FKpic28nri2mcuuxwt53mun1cqi` (`employe`),
  KEY `FKe7s1uttje8ro8h28q6dthhuv1` (`idem_de_la_residence`),
  KEY `FK2cccg1h6l3l5q7xtb5w0m6qny` (`idem_famialiele_marocaine`),
  KEY `FKmvn9rkrw5mofs6v1p2rfm4hx9` (`impot_sur_le_revenu`),
  KEY `FKhtrxffhpm9l7npw4irys2dple` (`mutuelle_caisse_retrait_et_deces`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `salaire_employe`
--

INSERT INTO `salaire_employe` (`id`, `monatnt_modifie`, `salaire_net`, `allocation_de_encadrement`, `allocation_de_enseignement`, `assurance_maladie_obligatoire`, `caisse_marocaine_deretrait`, `employe`, `idem_de_la_residence`, `idem_famialiele_marocaine`, `impot_sur_le_revenu`, `mutuelle_caisse_retrait_et_deces`) VALUES
(1, 2400, 1300, 3, 4, 3, 4, 2, 2, 1, 2, 1),
(55, 4500, 4500, 3, 4, 3, 4, 53, 2, 1, 2, 1),
(58, 5000, 5000, 3, 4, 3, 4, 56, 2, 1, 2, 1),
(118, 8000, 8000, 3, 4, 3, 4, 116, 2, 1, 2, 1),
(122, 5500, 5500, 3, 4, 3, 4, 120, 2, 1, 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `type_congee`
--

DROP TABLE IF EXISTS `type_congee`;
CREATE TABLE IF NOT EXISTS `type_congee` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `type_congee`
--

INSERT INTO `type_congee` (`id`, `libelle`) VALUES
(1, 'certificat long duree'),
(2, 'certificat court duree'),
(3, 'conge exceptionnel'),
(4, 'sans solde');

-- --------------------------------------------------------

--
-- Structure de la table `type_document`
--

DROP TABLE IF EXISTS `type_document`;
CREATE TABLE IF NOT EXISTS `type_document` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `type_document`
--

INSERT INTO `type_document` (`id`, `libelle`) VALUES
(1, 'attestation de salaire'),
(2, 'attestation de travail');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `bloqued` bit(1) NOT NULL,
  `date_bloquage` datetime DEFAULT NULL,
  `nbr_tentatif_restant` int(11) NOT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `login`, `password`, `bloqued`, `date_bloquage`, `nbr_tentatif_restant`, `pwd`) VALUES
(1, 'bendrimou@gmail.com', '683ddff5d298e8540a4e15e12ee56b67c3822c3a80d25e8fc9c647983028cbd1', b'0', '2020-05-07 16:09:32', 1, '385dc06b15cfcb8250bc2968d70ba6f6ae5889859199fa8b927dfa972d0d97dc');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
