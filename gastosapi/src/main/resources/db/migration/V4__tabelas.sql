alter table usuario add perfil varchar(255) NOT NULL;

#cria usuario admin
insert into usuario (id, nome, senha, email, perfil) values (NULL, 'administrador', '$2a$10$dApj24vOmIWo79bLcXTZY.ZvJH9z.41Snjwl2y2eNZZQWnkKBNtLm', 'admin@gastos.com.br', 'ROLE_ADMIN');