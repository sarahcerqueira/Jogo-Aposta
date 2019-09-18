//Cadstro de dezena de concurso
INSERT INTO dezena (concurso, hora, d1, d2, d3, d4, d5)
VALUES('2019-09-16', '{12:00}', 88, 10, 33, 7, 00)

//Cadastro de usuario(vendedor)INSERT INTO usuario(nome, username, senha, admin) 
VALUES('Cesa', 'cesa', '1234', true),
('Jhonny', 'cuca', '1234', true),
('Lucas', 'lucas', '1234', false)

//Cadastrar concurso
INSERT INTO concurso(data, hora)
VALUES('2019/09/16', '{00:00}')

//Alterar data de concurso
update concurso 
set data = '2019/09/21'
where data = '2019/09/16'

//Ver usuarios Cadastrados
SELECT * FROM usuario

//Ver dezenas
SELECT * FROM dezena

//Ver concursos
SELECT * FROM concurso

//VER apostas
SELECT * FROM aposta

//VER maquinas
SELECT * FROM maquina

//VER jogador
SELECT * FROM jogador
