--
-- Table structure for table `log`
--
CREATE TABLE `log` (
  `id` int(11) NOT NULL,
  `date_time` timestamp NOT NULL,
  `ip` varchar(50) NOT NULL,
  `protocol` varchar(20) NOT NULL,
  `status` int(3) NOT NULL,
  `detail` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for table `log`
--
ALTER TABLE `log`
 ADD PRIMARY KEY (`id`);
 
--
-- AUTO_INCREMENT for table `log`
--
ALTER TABLE `log`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;