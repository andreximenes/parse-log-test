--
-- Table structure for table `ip_blocked`
--
CREATE TABLE `ip_blocked` (
  `id` int(11) NOT NULL,
  `ip` varchar(50) NOT NULL,
  `date_time_request` timestamp NOT NULL,
  `info` varchar(200) NOT NULL,
  `block_date` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for table `ip_blocked`
--
ALTER TABLE `ip_blocked`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `ip_date_time_request` (`ip`,`date_time_request`);

--
-- AUTO_INCREMENT for table `ip_blocked`
--
ALTER TABLE `ip_blocked`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;