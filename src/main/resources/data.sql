-- Insertar usuarios
INSERT INTO app_user (name, lastname, email, phone_number) VALUES 
('Juan', 'Pérez', 'juan.perez@example.com', '123456789'),
('María', 'González', 'maria.gonzalez@example.com', '987654321'),
('Carlos', 'López', 'carlos.lopez@example.com', '456789123'),
('Ana', 'Martínez', 'ana.martinez@example.com', '741852963'),
('Pedro', 'Sánchez', 'pedro.sanchez@example.com', '369258147'),
('Lucía', 'Ramírez', 'lucia.ramirez@example.com', '852147963');

-- Insertar roles (asignados a usuarios)
INSERT INTO role (name, user_id) VALUES 
('Admin', 1),
('User', 2),
('Editor', 3),
('Moderator', 4),
('SuperAdmin', 5),
('Guest', 6);
