# agendamento-nava

Versão do Java: 11.

Arquitetura: MVC, utilizado o MVC pois é uma arquitetura organizada, fléxivel e de facíl manutenção.

Persistência de dados: h2.  

IDEA: Intellij.

Para rodar o projeto:

1- Utilizar a versão 11 do java.

2- Inicializar o projeto: mvn spring-boot:run.

Projeto esta configurado para rodar na porta 8080: http://localhost:8080/

#Acesso ao h2.  

url para acesso ao h2: http://localhost:8080/h2-console/  

user: admin  

senha: admin  

##Postman

API'S feitas para o projeto e disponiveis para acesso via postman

GET: http://localhost:8080/agendamentos/buscar

GET: http://localhost:8080/agendamentos/taxa/2024-05-14/2024-05-15/10.2

GET: http://localhost:8080/agendamentos/valor

POST: http://localhost:8080/agendamentos/agendar
BODY: {
    "contaOrigem": 12355,
    "contaDestino": 123444,
    "vlTranferencia": 100,
    "dtTranferencia": "2024-05-14",
    "dtAgendamento": "2024-05-14"
}
