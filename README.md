Requisitos
* JAVA 8+
* MAVEN 3+

Iniciar a aplicação
Passo 1 - Navegar ou realizar comando (cd) para chegar até a raiz do projeto
Passo 2 - executar o comando: mvn spring-boot:run

Caso não deseja baixar o projeto pode acesso pelo Heroku
Basta subistituir o link http://localhost:8080/ por https://safe-ocean-30926.herokuapp.com


FUNCIONALIDADES DO APP
* Criar boleto
** Listar boletos
* Ver detalhes
* Pagar um boleto
* Cancelar um boleto

CRIAR BOLETO
POST http://localhost:8080/rest/bankslips

JSON(application/json)

{
 "due_date":"2018-01-01",
 "total_in_cents":"100000",
 "customer":"Trillian Company",
 "status":"PENDING"
}

Mensagens de resposta
201 : Bankslip created
* 400 : Bankslip not provided in the request body
* 422 : Invalid bankslip provided.The possible reasons are:
  * A field of the provided bankslip was null or with invalid values


LISTAR BOLETOS
GET http://localhost:8080/rest/bankslips/

Mensagens de resposta
* 200 : Ok 
EX: [  
   {  
      "id":"b8ef0f43-e873-4da0-8465-2d7af03a0b3c",
      "due_date":"2018-02-03",
      "total_in_cents":"50",
      "customer":"TESTE",
      "status":"PAID",
      "fine":null
   },
   {  
      "id":"cd90034d-e0f4-4ea7-95f2-0da8ba157d55",
      "due_date":"2018-01-01",
      "total_in_cents":"100000",
      "customer":"Trillian Company",
      "status":"CANCELED",
      "fine":null
   }
]


VER DETALHES
GET http://localhost:8080/rest/bankslips/{id}
Ex: http://localhost:8080/rest/bankslips/cd90034d-e0f4-4ea7-95f2-0da8ba157d55
Obs: 
* Até 10 dias: Multa de 0,5% (Juros Simples)
* Acima de 10 dias: Multa de 1% (Juros Simples)

Mensagens de resposta
* 200 : Ok
* 400 : Invalid id provided - it must be a valid UUID
* 404 : Bankslip not found with the specified id


PAGAR BOLETO
PUT http://localhost:8080/rest/bankslips/{id}/pay
Ex: http://localhost:8080/rest/bankslips/cd90034d-e0f4-4ea7-95f2-0da8ba157d55/pay

Mensagens de resposta
* 204 : Bankslip paid
* 404 : Bankslip not found with the specified id


CANCELAR BOLETO
DELETE http://localhost:8080/rest/bankslips/{id}/cancel
Ex: http://localhost:8080/rest/bankslips/cd90034d-e0f4-4ea7-95f2-0da8ba157d55/cancel

Mensagens de resposta
* 204 : Bankslip canceled
* 404 : Bankslip not found with the specified id
