INSERT INTO USUARIO(name, username, email, password) VALUES('Desenvolvedor Account', 'desenv gestor', 'desenv@email.com', '$2a$10$pT82fBlCD2ydMShY/jqjpOFBlqMZlHmDRXJkYMVBI7HZmyGwTd1yu');
INSERT INTO USUARIO(name, username, email, password) VALUES('Desenvolvedor Account', 'desenv tecnico', 'desenv@email.com', '$2a$10$pT82fBlCD2ydMShY/jqjpOFBlqMZlHmDRXJkYMVBI7HZmyGwTd1yu');

INSERT INTO ROLE(name) VALUES('ROLE_GESTOR');
INSERT INTO ROLE(name) VALUES('ROLE_TECNICO');

INSERT INTO USUARIO_ROLES(usuario_id, roles_id) VALUES(1,1);
INSERT INTO USUARIO_ROLES(usuario_id, roles_id) VALUES(2,2);

INSERT INTO STATUS(name) VALUES('Andamento');
INSERT INTO STATUS(name) VALUES('Finalizado');
INSERT INTO STATUS(name) VALUES('Aberto');

INSERT INTO PRIORITY(name) VALUES('Alta');
INSERT INTO PRIORITY(name) VALUES('Baixa');
INSERT INTO PRIORITY(name) VALUES('Urgente');

INSERT INTO CLIENT(cnpj, contract, name) VALUES('11212996950/0001', 'false', 'Laércio Bubiak');

INSERT INTO TYPE_SERVICES(services) VALUES('Pegou Fogo');
INSERT INTO TYPE_SERVICES(services) VALUES('Arrumação');
INSERT INTO TYPE_SERVICES(services) VALUES('Troca de Peças');

INSERT INTO EQUIPMENT(model, name) VALUES('KM7895-9', 'Impressora Epson');
INSERT INTO EQUIPMENT(model, name) VALUES('E180', 'Galaxy Tablet');

INSERT INTO INVENTORY(name, quantity) VALUES('fonte', 10);
INSERT INTO INVENTORY(name, quantity) VALUES('fonte notebook', 25);
INSERT INTO INVENTORY(name, quantity) VALUES('fonte computador', 56);
