use lb_backend;
CREATE TABLE `notes` (
`id`  int UNSIGNED NOT NULL AUTO_INCREMENT,
`body`  text NOT NULL ,
PRIMARY KEY (`id`));

INSERT INTO `notes` (`id`, `body`) VALUES (1, 'This is a test');