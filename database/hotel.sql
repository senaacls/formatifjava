-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 19, 2017 at 05:53 PM
-- Server version: 5.7.20-0ubuntu0.17.10.1
-- PHP Version: 7.1.8-1ubuntu1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel`
--

-- --------------------------------------------------------

--
-- Table structure for table `jeniskamar`
--

CREATE TABLE `jeniskamar` (
  `jenisid` varchar(3) NOT NULL,
  `jeniskamar` varchar(50) NOT NULL,
  `hargasewa` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jeniskamar`
--

INSERT INTO `jeniskamar` (`jenisid`, `jeniskamar`, `hargasewa`) VALUES
('J01', 'Suite', 5000),
('J02', 'Deluxe', 10000);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `idtransaksi` varchar(10) NOT NULL,
  `tanggal` datetime NOT NULL,
  `namacustomer` varchar(40) NOT NULL,
  `nomorktp` varchar(30) NOT NULL,
  `jenisid` varchar(3) NOT NULL,
  `lamainap` int(11) NOT NULL,
  `jumlahuang` double NOT NULL,
  `pajak` double NOT NULL,
  `grandtotal` double NOT NULL,
  `bayar` double NOT NULL,
  `kembali` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`idtransaksi`, `tanggal`, `namacustomer`, `nomorktp`, `jenisid`, `lamainap`, `jumlahuang`, `pajak`, `grandtotal`, `bayar`, `kembali`) VALUES
('T01', '2017-11-19 00:00:00', 'Rina', '1051515', 'J02', 2, 20000, 4200, 60500, 100000, 75800);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `jeniskamar`
--
ALTER TABLE `jeniskamar`
  ADD PRIMARY KEY (`jenisid`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`idtransaksi`),
  ADD KEY `jenisid` (`jenisid`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`jenisid`) REFERENCES `jeniskamar` (`jenisid`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
