-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 27, 2020 at 01:35 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `uts`
--

-- --------------------------------------------------------

--
-- Table structure for table `paket`
--

CREATE TABLE `paket` (
  `id` int(11) NOT NULL,
  `paket` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `paket`
--

INSERT INTO `paket` (`id`, `paket`) VALUES
(1, 'Regular'),
(2, 'Special');

-- --------------------------------------------------------

--
-- Table structure for table `sekolah_asal`
--

CREATE TABLE `sekolah_asal` (
  `id` int(11) NOT NULL,
  `sekolah` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sekolah_asal`
--

INSERT INTO `sekolah_asal` (`id`, `sekolah`) VALUES
(1, 'SMP MAITREYAWIRA BATAM'),
(2, 'SMP YOS SUDARSO BATAM'),
(3, 'SMP KARTINI 2 BATAM');

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `id` int(11) NOT NULL,
  `paket_id` int(11) DEFAULT NULL,
  `no_induk` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `jenis_kelamin` enum('Laki-laki','Perempuan') DEFAULT NULL,
  `tempat_lahir` varchar(255) DEFAULT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `sekolah_asal_id` int(11) DEFAULT NULL,
  `alamat` text DEFAULT NULL,
  `nama_wali` varchar(255) DEFAULT NULL,
  `telp` varchar(255) DEFAULT NULL,
  `foto_name` varchar(255) DEFAULT NULL,
  `foto_link` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`id`, `paket_id`, `no_induk`, `nama`, `jenis_kelamin`, `tempat_lahir`, `tanggal_lahir`, `sekolah_asal_id`, `alamat`, `nama_wali`, `telp`, `foto_name`, `foto_link`) VALUES
(63, 2, 1831113, 'Hendry Wijaya', 'Perempuan', 'Batam', '1999-12-07', 1, 'Jodoh Permai', '-', '08190838', '', NULL),
(67, 1, 18311132, 'Andy Yuris', 'Perempuan', 'Isekais', '2020-10-08', 2, 'Jodoh Liar', 'Kisamaaa', '0812312312', '80', 'http://192.168.100.2:8080/uts/siswa/images/80.png'),
(68, 1, 12345678, 'Hendry bear', 'Laki-laki', '', '0000-00-00', 1, '', '', '', 'xxx', 'http://192.168.100.2:8080/uts/siswa/images/67.png'),
(69, 1, 124685, 'hebdrtijdt', 'Laki-laki', 'tjjv', '2020-10-27', 1, 'gdaykm', 'fduv', '48853226', 'xxx', 'http://192.168.100.2:8080/uts/siswa/images/68.png'),
(70, 1, 124685, 'hebdrtijdt', 'Laki-laki', '', '2020-10-27', 1, '', '', '', 'xxx', 'http://192.168.100.2:8080/uts/siswa/images/69.png'),
(71, 1, 123456789, 'ahjokgs', 'Perempuan', '', '2020-10-27', 1, '', '', '', 'xxx', 'http://192.168.100.2:8080/uts/siswa/images/70.png'),
(72, 1, 12312, 'dawdad', 'Perempuan', 'ara', '0000-00-00', 1, '', '', '', 'xxx', 'http://192.168.100.2:8080/uts/siswa/images/71.png'),
(73, 1, 13123, 'dawdw', 'Perempuan', '', '0000-00-00', 1, '', '', '', 'xxx', 'http://192.168.100.2:8080/uts/siswa/images/72.png'),
(74, 1, 213, 'faef', 'Perempuan', '', '0000-00-00', 1, '', '', '', '73', 'http://192.168.100.2:8080/uts/siswa/images/73.png'),
(75, 1, 123456789, 'thanks', 'Perempuan', '', '0000-00-00', 1, '', '', '', '74', 'http://192.168.100.2:8080/uts/siswa/images/74.png'),
(76, 1, 123456789, 'thanks', 'Laki-laki', '', '0000-00-00', 1, '', '', '', '75', 'http://192.168.100.2:8080/uts/siswa/images/75.png'),
(77, 1, 134567890, 'thanks', 'Laki-laki', '', '0000-00-00', 1, '', '', '', '76', 'http://192.168.100.2:8080/uts/siswa/images/76.png'),
(78, 1, 908, 'thanks', 'Laki-laki', '', '0000-00-00', 1, '', '', '', '77', 'http://192.168.100.2:8080/uts/siswa/images/77.png'),
(79, 1, 1245678, 'thanks', 'Laki-laki', '', '0000-00-00', 1, '', '', '', '78', 'http://192.168.100.2:8080/uts/siswa/images/78.png'),
(80, 1, 1245678, 'thanks', 'Laki-laki', '', '0000-00-00', 1, '', '', '', '80', 'http://192.168.100.2:8080/uts/siswa/images/80.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `paket`
--
ALTER TABLE `paket`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sekolah_asal`
--
ALTER TABLE `sekolah_asal`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `siswa_paket_id_foreign` (`paket_id`),
  ADD KEY `siswa_sekolah_asal_id_foreign` (`sekolah_asal_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `paket`
--
ALTER TABLE `paket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `sekolah_asal`
--
ALTER TABLE `sekolah_asal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `siswa`
--
ALTER TABLE `siswa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `siswa`
--
ALTER TABLE `siswa`
  ADD CONSTRAINT `siswa_paket_id_foreign` FOREIGN KEY (`paket_id`) REFERENCES `paket` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `siswa_sekolah_asal_id_foreign` FOREIGN KEY (`sekolah_asal_id`) REFERENCES `sekolah_asal` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
