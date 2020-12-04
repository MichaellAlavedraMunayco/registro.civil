CREATE TABLE `person` (
  `id` int(11) PRIMARY KEY AUTO_INCREMENT COMMENT 'Identificador único de la persona',
  `dni` varchar(8) NOT NULL DEFAULT "00000000" COMMENT 'Número de Documento de Identidad',
  `fullname` varchar(255) NOT NULL DEFAULT "unknown" COMMENT 'Nombre completo',
  `email` varchar(50) NOT NULL DEFAULT "unknown@unknown" COMMENT 'Correo Electrónico',
  `birthdate` varchar(10) NOT NULL DEFAULT "00/00/0000" COMMENT 'Fecha de nacimiento',
  `age` varchar(2) NOT NULL DEFAULT "00" COMMENT 'Edad',
  `gender` varchar(10) NOT NULL DEFAULT "unsigned" COMMENT 'Masculino, Femenino'
)

CREATE TABLE `contact` (
  `id` int(11) PRIMARY KEY AUTO_INCREMENT COMMENT 'Identificador único del contacto',
  `address` varchar(255) NOT NULL DEFAULT "unknown" COMMENT 'Detalle del domicilio',
  `homephone` varchar(10) NOT NULL DEFAULT "000 000" COMMENT 'Número de teléfono fijo',
  `phone` varchar(15) NOT NULL DEFAULT "00 000 000 000" COMMENT 'Número de teléfono móvil',
  `personId` int(11) NOT NULL
)

ALTER TABLE `contact` ADD FOREIGN KEY (`personId`) REFERENCES `person` (`id`);

CREATE UNIQUE INDEX `person_index_0` ON `person` (`id`);
