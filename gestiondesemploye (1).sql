-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 17 juin 2020 à 18:44
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
  `date_de_fin` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhqbqod7o6xw5axoiuvp1sonn4` (`congee`),
  KEY `FK6dovv0gq9mk1mjhmrrkvaetjd` (`employe`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `congé`
--

INSERT INTO `congé` (`id`, `date_de_debut`, `periode`, `congee`, `employe`, `raison`, `date_de_fin`) VALUES
(257, '2020-06-11', 4, 3, 247, 'voyage', '2020-06-15'),
(261, '2020-06-04', 60, 2, 220, NULL, '2020-06-14'),
(265, '2020-06-11', 6, 3, 247, 'voyage', '2020-06-17'),
(267, '2020-06-04', 90, 4, 247, NULL, '2020-05-25');

-- --------------------------------------------------------

--
-- Structure de la table `demane_de_document`
--

DROP TABLE IF EXISTS `demane_de_document`;
CREATE TABLE IF NOT EXISTS `demane_de_document` (
  `id` bigint(20) NOT NULL,
  `date_demande` date DEFAULT NULL,
  `etat` varchar(255) DEFAULT NULL,
  `employe` bigint(20) DEFAULT NULL,
  `type_de_document` bigint(20) DEFAULT NULL,
  `nbr_de_document` int(11) NOT NULL,
  `copie_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcke1pv0x3ef0c7llv9vtws3sv` (`employe`),
  KEY `FKow6xx98e6vsrqi15xf1rx8dke` (`type_de_document`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `demane_de_document`
--

INSERT INTO `demane_de_document` (`id`, `date_demande`, `etat`, `employe`, `type_de_document`, `nbr_de_document`, `copie_email`) VALUES
(269, '2020-06-17', 'non traité', 247, 1, 3, 'oui'),
(271, '2020-06-17', 'traité', 247, 2, 4, 'oui');

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
(1, 'Mathémathique', 132456, 'Ayoub Bendrimo'),
(2, 'physique', 162811, 'nabila morabiti'),
(25, 'Informatique', 739140, 'rachid bouigrouane'),
(27, 'TEC', 749430, 'yossef el Aziri'),
(96, 'Biologie', 741845, 'abd el karim ait ouzo'),
(7, 'Chimie', 132456, 'Ayoub Bendrimo'),
(8, 'Géologie', 132456, 'Ayoub Bendrimo');

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
(100, 2, 97),
(106, 25, 105),
(108, 25, 107),
(212, 27, 211),
(214, 27, 213),
(240, 96, 239),
(242, 96, 241),
(244, 7, 243),
(246, 7, 245);

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
  `date_avancement_prevue` date DEFAULT NULL,
  `date_de_naissance` date DEFAULT NULL,
  `date_de_prochain_note` date DEFAULT NULL,
  `date_entree` date DEFAULT NULL,
  `date_prochain_evaluation` date DEFAULT NULL,
  `date_sortie` date DEFAULT NULL,
  `doti` varchar(22) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enfants` int(11) DEFAULT NULL,
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
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `lieu_de_resedence` varchar(255) DEFAULT NULL,
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

INSERT INTO `employe` (`id`, `adresse`, `cin`, `date_avancement_prevue`, `date_de_naissance`, `date_de_prochain_note`, `date_entree`, `date_prochain_evaluation`, `date_sortie`, `doti`, `email`, `enfants`, `gender`, `lieu_de_naissance`, `pays`, `situation_familiale`, `tel`, `dep`, `dernier_grade`, `dernier_note`, `sup`, `fonction`, `solde_restantes_conge_exceptionnel`, `first_name`, `last_name`, `lieu_de_resedence`) VALUES
(2, 'N252 sokoma askejour', 'EE1628', NULL, '2020-05-28', '2020-05-24', '2020-05-27', NULL, NULL, '162811', 'nabilaMorabiti@gmail.com\r\n', 3, 'Homme', NULL, 'Marrakech', 'Celebataire', 706019991, 1, 3, 2, NULL, 1, 10, 'nabila', 'morabiti', 'marrakech'),
(220, 'tranche1 tamansourt marrakech', 'EE1627', NULL, '1943-02-04', '2021-06-04', '2017-06-11', NULL, NULL, '132456', 'bendrimou@gmail.com', 3, 'Homme', 'Marakechr', NULL, 'Marie', 613430719, 1, 221, 230, NULL, 2, 10, 'Ayoub', 'Bendrimo', 'Marrakech'),
(234, 'j55 tranche1 tamansourt', 'EE7494', NULL, '1969-02-04', '2020-02-01', NULL, '2022-06-18', NULL, '749430', 'youssef74@gmail.com', 0, 'Homme', 'Marrakech', NULL, 'Celebataire', 634345465, 27, 235, NULL, NULL, 211, 10, 'yossef', 'el Aziri', 'marrakech'),
(247, 'j66 tranche1 tamansourt', '739140', NULL, '1970-03-05', '2021-01-06', '2016-02-02', '2022-06-18', NULL, '739140', 'abiabiya10042003@gmail.com', 3, 'Homme', 'Marrakech', NULL, 'Marie', 654324675, 25, 248, NULL, NULL, 107, 0, 'rachid', 'bouigrouane', 'marrakech'),
(252, 'J77 tranche1 tamansourt', 'EE7418', NULL, '2006-07-13', '2021-02-03', '1978-02-04', '2024-06-17', NULL, '741845', 'aitouzo99@gmail.com', 3, 'Homme', 'marrakech', NULL, 'Marie', 653453658, 96, 253, NULL, NULL, 239, 10, 'abd el karim', 'ait ouzo', 'marrakech');

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
(1, 'prof Mathématique'),
(2, 'chef departement Mathématique'),
(31, 'prof physique'),
(97, 'chef departement physiue'),
(105, 'prof informatique'),
(107, 'chef departement informatique'),
(211, 'chef deparatement tec'),
(213, 'prof tec'),
(239, 'chef departement biologie'),
(241, 'prof Biologie'),
(243, 'chef departement chimie'),
(245, 'prof chimie');

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
  `employe` bigint(20) DEFAULT NULL,
  `etablissement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcpynwso3d4a89t34sbisxjwl3` (`employe`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`id`, `annee`, `attestation`, `domaine`, `mention`, `ville`, `employe`, `etablissement`) VALUES
(275, '2020-06-03', 'developpement', 'domaine1', 'ention1', 'ville1', 220, 'etabliseement');

-- --------------------------------------------------------

--
-- Structure de la table `grade`
--

DROP TABLE IF EXISTS `grade`;
CREATE TABLE IF NOT EXISTS `grade` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `grade`
--

INSERT INTO `grade` (`id`, `libelle`) VALUES
(1, 'grade1'),
(2, 'grade2'),
(5, 'grade5'),
(3, 'grade3'),
(26, 'grade4'),
(6, 'grade6'),
(7, 'grade7'),
(8, 'grade8'),
(109, 'grade9'),
(110, 'grade10'),
(11, 'gradeExceptionnel'),
(12, 'hors echelle');

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
(3, '2020-05-19', 1, 'traité', '162811'),
(221, '2019-03-07', 110, 'traité', '132456'),
(232, NULL, 11, 'en traitement', '132456'),
(235, '2019-02-01', 110, 'traité', '749430'),
(248, '2020-01-07', 110, 'traité', '739140'),
(253, '2020-02-04', 109, 'traité', '741845');

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
(276),
(276),
(276),
(276),
(276),
(276),
(276),
(276),
(276),
(276),
(276),
(276),
(276),
(276),
(276),
(276),
(276),
(276),
(276),
(276),
(276),
(276),
(276),
(276),
(276),
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
  `mention` double DEFAULT NULL,
  `remarque` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `note`
--

INSERT INTO `note` (`id`, `mention`, `remarque`) VALUES
(1, 17.5, 'remarque1'),
(2, 20, 'remarque2'),
(3, 13, 'remarque3'),
(4, 16.4, 'remarque4'),
(5, 15, 'remarque5'),
(229, 19, 'super'),
(228, 18, 'tres bien'),
(227, 15, 'assez bien'),
(226, 12, 'bien'),
(225, 10, 'passable');

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
(1, '2020-05-21', 'mention1', 18.5, 1, 3, 4, 5, 2, 'non traite', '162811', 'nabila morabiti'),
(2, '2020-05-15', 'mention2', 17.5, 1, 3, 4, 5, 2, 'non traite', '162811', 'nabila morabiti'),
(230, '2020-06-04', 'moyen', 14.8, 225, 226, 227, 228, 229, NULL, '132456', 'Ayoub Bendrimo');

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
  `type_notification` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKquie4di0s61lb135uhh85pl53` (`employe`),
  KEY `FK6eonh5263ksu3s63aj9q8hjhs` (`notification`),
  KEY `FK8vb6myrt31ge38mt5cmh7fapf` (`type_notification`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `notification_employe`
--

INSERT INTO `notification_employe` (`id`, `date_de_notification`, `libelle`, `employe`, `notification`, `type_notification`) VALUES
(222, '2020-06-16 19:31:55', 'save salaire employe ', 220, NULL, 1),
(224, '2020-06-16 19:31:55', 'save employe', 220, NULL, 1),
(231, '2020-06-16 19:34:26', 'save note', 220, NULL, 1),
(236, '2020-06-17 14:11:53', 'save salaire employe ', 234, NULL, 1),
(238, '2020-06-17 14:11:53', 'save employe', 234, NULL, 1),
(249, '2020-06-17 14:33:58', 'save salaire employe ', 247, NULL, 1),
(251, '2020-06-17 14:33:58', 'save employe', 247, NULL, 1),
(254, '2020-06-17 14:38:29', 'save salaire employe ', 252, NULL, 1),
(256, '2020-06-17 14:38:29', 'save employe', 252, NULL, 1),
(258, '2020-06-17 14:42:00', 'update conge', 247, NULL, 1),
(260, '2020-06-17 14:42:41', 'update conge', 234, NULL, 1),
(262, '2020-06-17 14:53:19', 'update conge', 220, NULL, 1),
(264, '2020-06-17 14:54:01', 'update conge', 234, NULL, 1),
(266, '2020-06-17 15:18:10', 'update conge', 247, NULL, 1),
(268, '2020-06-17 15:29:24', 'update conge', 247, NULL, 1),
(270, '2020-06-17 16:09:53', 'save demande', 247, NULL, 1),
(272, '2020-06-17 17:29:30', 'save demande', 247, NULL, 1),
(273, '2020-06-17 17:31:08', 'imprimer attestation de travail', 247, NULL, 4),
(274, '2020-06-17 18:02:12', 'save formation', 220, NULL, 1);

-- --------------------------------------------------------

--
-- Structure de la table `permanence_administrative`
--

DROP TABLE IF EXISTS `permanence_administrative`;
CREATE TABLE IF NOT EXISTS `permanence_administrative` (
  `id` bigint(20) NOT NULL,
  `periode` int(11) DEFAULT NULL,
  `periode_de_recuperation` int(11) DEFAULT NULL,
  `employe` bigint(20) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `recuperation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj511f80de92poew8npmcj6852` (`employe`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `permanence_administrative`
--

INSERT INTO `permanence_administrative` (`id`, `periode`, `periode_de_recuperation`, `employe`, `date`, `recuperation`) VALUES
(1, 20, 30, 2, '2020-05-12', 'oui');

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
  `remarque` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKohq70aidpp77w6y6f553v9ro` (`employe`),
  KEY `FK9vp0qil39i0hyarsl1mxbw5ue` (`prix`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

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
  `remarque` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpej5xp47xku0yd4iv1j9gynug` (`employe`),
  KEY `FKrf9p9v19jckk2f91p2mqbq5q1` (`punition`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

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
(233, 'moyen', 14.8, NULL, 232, 220);

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
(233, 230);

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
(218, 9200, 8500, 3, 4, 3, 4, 215, 2, 1, 2, 1),
(223, 9200, 8500, 3, 4, 3, 4, 220, 2, 1, 2, 1),
(237, 8000, 8500, 3, 4, 3, 4, 234, 2, 1, 2, 1),
(250, 9200, 8500, 3, 4, 3, 4, 247, 2, 1, 2, 1),
(255, 8700, 8000, 3, 4, 3, 4, 252, 2, 1, 2, 1);

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
(2, 'certificat court duree 3 mois'),
(3, 'conge exceptionnel'),
(4, 'certificat court duree 6 mois');

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
-- Structure de la table `type_notification`
--

DROP TABLE IF EXISTS `type_notification`;
CREATE TABLE IF NOT EXISTS `type_notification` (
  `id` bigint(20) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `type_notification`
--

INSERT INTO `type_notification` (`id`, `type`) VALUES
(1, 'save'),
(2, 'update'),
(3, 'delete'),
(4, 'imprimer'),
(6, 'note aujourdhui'),
(7, 'avancement aujourdhui'),
(8, 'évaluation aujourdhui');

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
(1, 'shadeau99@gmail.com', '683ddff5d298e8540a4e15e12ee56b67c3822c3a80d25e8fc9c647983028cbd1', b'0', '2020-06-02 14:28:55', 3, 'e2666660bfa5e9fff544956ad7b2f8af80dc94b31ae44dbd13e0531b62e5519c');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
