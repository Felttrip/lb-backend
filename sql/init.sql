use lb_backend;
CREATE TABLE `notes` (
`id`  int UNSIGNED NOT NULL AUTO_INCREMENT,
`body`  text NOT NULL ,
PRIMARY KEY (`id`));

INSERT INTO `notes` (`id`, `body`) VALUES (1, "Ask Larry about the TPS reports.");
INSERT INTO `notes` (`id`, `body`) VALUES (2, "Pick up milk!");