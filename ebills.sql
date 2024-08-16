-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 15, 2024 at 09:50 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ebills`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `Changeadminpassword` (IN `tid` INT, IN `pass` VARCHAR(20))   BEGIN
UPDATE admin SET password = pass where id = tid;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Changecustomerpassword` (IN `tid` INT, IN `pass` VARCHAR(20))   BEGIN
UPDATE customer SET password = pass where customerid = tid;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Changeemployeepassword` (IN `tid` INT, IN `pass` VARCHAR(20))   BEGIN
UPDATE employee SET password = pass WHERE empid = tid;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Deleteadmin` (IN `tid` INT)   BEGIN
DELETE FROM admin where id = tid;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Deletecustomer` (IN `tid` INT)   BEGIN
DELETE FROM customer WHERE customerid = tid;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Deleteemployee` (IN `tid` INT)   BEGIN
DELETE FROM employee WHERE empid = tid;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Displayadmin` ()   BEGIN
SELECT * FROM admin;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Displaycustomer` ()   BEGIN
SELECT * FROM customer;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Displayemployee` ()   BEGIN
SELECT * FROM employee;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Insertadmin` (IN `nm` VARCHAR(20), IN `ph` VARCHAR(15), IN `eml` VARCHAR(50), IN `pass` VARCHAR(20))   BEGIN
INSERT INTO admin (name, phoneno, emailid, password) VALUES (nm, ph, eml, pass);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Insertcustomer` (IN `nm` VARCHAR(20), IN `addr` VARCHAR(100), IN `ph` VARCHAR(15), IN `eml` VARCHAR(50), IN `pass` VARCHAR(20), IN `cdate` TEXT, IN `ctype` VARCHAR(20))   BEGIN
INSERT INTO customer (name, address, phoneno, emailid, password, connectiondate, connectiontype) VALUES (nm, addr, ph, eml, pass, cdate, ctype);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Insertemployee` (IN `nm` VARCHAR(20), IN `addr` VARCHAR(100), IN `ph` VARCHAR(15), IN `eml` VARCHAR(50), IN `pass` VARCHAR(20), IN `adhr` VARCHAR(20))   BEGIN
INSERT INTO employee (name, address, phoneno, emailid, password, aadharno) VALUES (nm, addr, ph, eml, pass, adhr);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Oneadmin` (IN `tid` INT)   BEGIN
SELECT * FROM admin where id = tid;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Onecustomer` (IN `tid` INT)   BEGIN
SELECT * FROM customer where id = tid;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Oneemployee` (IN `tid` INT)   BEGIN
SELECT * FROM employee WHERE empid = tid;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Updateadmin` (IN `tid` INT, IN `nm` VARCHAR(20), IN `ph` VARCHAR(15), IN `eml` VARCHAR(50), IN `pass` VARCHAR(20))   BEGIN
UPDATE admin SET name = nm, phoneno = ph, emailid = eml, password = pass WHERE id = tid;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Updatecustomer` (IN `tid` INT, IN `nm` VARCHAR(20), IN `addr` VARCHAR(100), IN `ph` VARCHAR(15), IN `eml` VARCHAR(50), IN `pass` VARCHAR(20), IN `cdate` TEXT, IN `ctype` VARCHAR(20))   BEGIN
UPDATE customer SET name = nm, address = addr, phoneno = ph, emailid = eml, password = pass, connectiondate = cdate, connectiontype = ctype WHERE customerid = tid;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Updatecustomer2` (IN `tid` INT, IN `nm` VARCHAR(20), IN `ph` VARCHAR(15), IN `eml` VARCHAR(50), IN `pass` VARCHAR(20))   BEGIN
UPDATE customer SET name = nm, phoneno = ph, emailid = eml, password = pass WHERE customerid = tid;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Updateemployee` (IN `tid` INT, IN `nm` VARCHAR(20), IN `addr` VARCHAR(100), IN `ph` VARCHAR(15), IN `eml` VARCHAR(50), IN `pass` VARCHAR(20), IN `adhr` VARCHAR(20))   BEGIN
UPDATE employee SET name = nm, address = addr, phoneno = ph, emailid = eml, password = pass, aadharno = adhr WHERE empid = tid;
END$$

--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `Customerlogin` (`eml` VARCHAR(50), `pass` VARCHAR(20)) RETURNS INT(11)  BEGIN
DECLARE res INT;
SET res = (SELECT COUNT(*) FROM customer WHERE emailid = eml AND password = pass);
RETURN res;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `Employeelogin` (`eml` VARCHAR(50), `pass` VARCHAR(20)) RETURNS INT(10)  BEGIN
DECLARE res INT;
SET res = (SELECT COUNT(*) FROM employee WHERE emailid = eml AND password = pass);
RETURN res;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `phoneno` varchar(15) NOT NULL,
  `emailid` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `name`, `phoneno`, `emailid`, `password`) VALUES
(1, 'Vishal', '1234565412', 'vks@gmail.com', 'Vishal'),
(2, 'bantu', '9845763125', 'bantuka4@gmail.com', 'bantu');

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `billid` int(11) NOT NULL,
  `customerid` int(11) NOT NULL,
  `date` text NOT NULL,
  `unit` int(10) NOT NULL,
  `amount` int(10) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customerid` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phoneno` varchar(15) NOT NULL,
  `emailid` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `connectiondate` text NOT NULL,
  `connectiontype` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customerid`, `name`, `address`, `phoneno`, `emailid`, `password`, `connectiondate`, `connectiontype`) VALUES
(1, 'Raja Koley', 'Singur, Hooghly, West Bengal', '9845632251', 'raja@gmail.com', 'Raja', '27/08/2023', 'Domestic'),
(2, 'kaka', 'kakatua', '461469163', 'kaka@kaka', 'kaka', '21/07/20223', 'Domestic');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `empid` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phoneno` varchar(15) NOT NULL,
  `emailid` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `aadharno` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`empid`, `name`, `address`, `phoneno`, `emailid`, `password`, `aadharno`) VALUES
(1, 'Vishal', 'Sheoraphuli', '135454534', 'vishal@gmail.com', 'Vishal', '452123451674');

-- --------------------------------------------------------

--
-- Table structure for table `meter`
--

CREATE TABLE `meter` (
  `id` int(11) NOT NULL,
  `customerid` int(11) NOT NULL,
  `reading` int(11) NOT NULL,
  `date` text NOT NULL,
  `empid` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `meter`
--

INSERT INTO `meter` (`id`, `customerid`, `reading`, `date`, `empid`, `status`) VALUES
(1, 1, 10, '26/07/2023', 1, 0),
(2, 1, 45, '27/08/2023', 1, 0),
(3, 2, 2, '25/07/20223', 1, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `emailid` (`emailid`,`password`);

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`billid`),
  ADD KEY `customerid` (`customerid`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customerid`),
  ADD UNIQUE KEY `phoneno` (`phoneno`,`emailid`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`empid`),
  ADD UNIQUE KEY `phoneno` (`phoneno`),
  ADD UNIQUE KEY `emailid` (`emailid`);

--
-- Indexes for table `meter`
--
ALTER TABLE `meter`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customerid` (`customerid`),
  ADD KEY `empid` (`empid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `billid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customerid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `empid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `meter`
--
ALTER TABLE `meter`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerid`);

--
-- Constraints for table `meter`
--
ALTER TABLE `meter`
  ADD CONSTRAINT `meter_ibfk_1` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerid`),
  ADD CONSTRAINT `meter_ibfk_2` FOREIGN KEY (`empid`) REFERENCES `employee` (`empid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
