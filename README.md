## Começando

### Cadastro de novo usuário
__Endpoint:__ `localhost:8080/auth/cadastro`

__Modelo body:__
`{
"login": "teste",
"password": "teste",
"role": "ADMIN"
}`

### Login - Gerar token de acesso
__Endpoint:__ `localhost:8080/auth/login`

__Modelo body:__ `{
"login": "teste",
"password": "teste"
}`

__Modelo de resposta:__ `{
"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6InRlc3RlIiwiZXhwIjoxNzE0MTAzODA0fQ.08RlXwf1ZCdZa_MBXPW0WPcNLvDmxkZXnuU4YDnbImU"
}` 


### Usando o token de autenticação
Adicionar ao header das requisições a chave `Authorization` e no campo valor `Bearer + token`

### Exemplo:
`curl --location 'localhost:8080/conta/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6InRlc3RlIiwiZXhwIjoxNzE0MTAzODA0fQ.08RlXwf1ZCdZa_MBXPW0WPcNLvDmxkZXnuU4YDnbImU'`

## Contas

### Nova conta
__Modelo de request:__
`curl --location 'localhost:8080/conta' \
--header 'Authorization: Bearer {{token}}' \
--data '{
"data_vencimento": "2024-05-01",
"valor": 100.00,
"descricao": "Compra de coisa"
}'`

__Modelo de resposta:__
`{
"id": 1,
"data_criacao": "2024-04-25T22:57:26",
"data_vencimento": "2024-05-01",
"data_pagamento": null,
"valor": 100.00,
"descricao": "Compra de coisa",
"situacao": "PENDENTE"
}`

__Obs:__ A data de pagamento pode ser fornecida no momento do cadastro incluindo o campo `data_pagamento` no corpo da request

__Importante:__ Os valores de data seguem o seguinte formado: `yyyy-MM-dd`

### Atualizar uma conta
__Modelo de request:__ 
`curl --location --request PUT 'localhost:8080/conta/{id}' \
--header 'Authorization: Bearer {{token}}' \
--data '{
"data_vencimento": "2024-05-05",
"valor": 100.10,
"descricao": "Compra de coisa alterada"
}'`

### Pagando uma conta
__Modelo de request:__
`curl --location --request PUT 'localhost:8080/conta/pagamento/{id}' \
--header 'Authorization: Bearer {{token}}'`


### Consultando uma conta por seu ID
__Modelo de request:__
`curl --location 'localhost:8080/conta/{id}' \
--header 'Authorization: Bearer {{token}}'`

### Pesquisa de contas por data de vencimento e descrição
__Modelo de request:__
`curl --location 'localhost:8080/conta/search?dataVencimento={dataVencimento}}&descricao={descrição}}&pageNumber={página}&pageSize={registrosPorPagina}' \
--header 'Authorization: Bearer {{token}}'`

### Consultar o total pago em um periodo
__Modelo de request:__
`curl --location 'localhost:8080/conta/search/totalPago?dataInicio={dataInicio}}&dataFim={dataFim}}' \
--header 'Authorization: Bearer {{token}}'`

### Lista todas as contas
__Modelo de request:__
`curl --location 'localhost:8080/conta/list?pageNumber={página}}&pageSize={registrosPorPagina}' \
--header 'Authorization: Bearer {{token}}'`

### Importando CSV de contas
O arquivo CSV deve possuir o seguinte formado:

`data_vencimento,data_pagamento,valor,descricao`

__Obs:__ A data de pagamento é opcional

Exemplo:

`2024-05-01,2024-04-30,150.13,Compra de uma coisa`

`2024-05-01,,23.45,Outra coisa que foi comprada`

__Modelo de request:__
`curl --location 'localhost:8080/conta/import' \
--header 'Authorization: Bearer {{token}}' \
--form 'file=@"{{arquivo}}"'`