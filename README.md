<h1  align="center">< progDashboard BETA /></h1>


<h3>O que é o progDashboard?</h3>
<h5>É uma API para empresas terem um maior controle sobre quais atividades determinado funcionário(usuário) tem, como está a situação dessa tarefa, seu prazo de término, etc.</h5>
<br/>

<h3>Como o progDashboard funciona?</h3>
<h5>Primeiramente é necessário criar uma empresa(company), após isso, você usando o token JWT dessa empresa tem permissões de adiministrador. Com a permissão de adiministrador você pode criar usuários e tarefas para esse determinado usuário, excluir usuários e também tarefas. Tudo isso é salvo localmente usando o banco de dados MySQL</h5>

<h5>Ao criar o usuário, além de determinar suas informações como cargo e nome, você determina também informações de login daquele usuário, para que posteriormente o funcionário entre na área dele usando essas credenciais.</h5>

<h5>Já nas tarefas são inseridas informações importantissimas para o controle posteriormente, como a data de criação da tarefa, prazo de termino, status, etc. Onde o usuário pode atualizar o status em que a tarefa se encontra.</h5>
<br/>

<h1 align="center"> Rotas de empresa </h1> 


* <h3> Criação</h3>
```dart
  POST company/register
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `name` | `string` | **Obrigatório:** Nome da empresa. |
| `email` | `string` | **Obrigatório:** Email de acesso. |
| `password` | `string` | **Obrigatório:** Senha de acesso. |
<br/>

* <h3>Login</h3>
```dart
  POST company/login
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `email` | `string` | **Obrigatório:** Email de acesso. |
| `password` | `string` | **Obrigatório:** Senha de acesso. |

<br/>

* <h3> Detalhes</h3>
```dart
  GET /company/{id}
```
<br/>

* <h3> Todas tasks [JWT]</h3>
```dart
  GET /company/gettasks/{id}
```
<br/>

* <h3> Todos funcionários [JWT]</h3>
```dart
  GET /company/getusers/{id}
```
<br/>

* <h3> Criação de usuário [JWT]</h3>
```dart
  POST /company/createUser
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `name` | `string` | **Obrigatório:** Node do funcionário. |
| `username` | `string` | **Obrigatório:** Usuário de acesso. |
| `password` | `string` | **Obrigatório:** Senha de acesso. |
| `idCompany` | `Long` | **Obrigatório:** Id da empresa. |
| `office` | `string` | **Obrigatório:** Cargo na empresa. |
<br/>

* <h3> Deleção de usuário [JWT]</h3>
```dart
  DELETE company/deleteUser/{id}
```
<br/>

* <h3> Deleção de usuário e todas suas tarefas [JWT]</h3>
```dart
  DELETE /company/deleteAllUser/{id}
```
<br/>

* <h3> Criação de tarefa [JWT]</h3>
```dart
  POST /task/create
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `name` | `string` | **Obrigatório:** Nome da tarefa. |
| `description` | `string` | **Obrigatório:** Descrição da tarefa. |
| `idUser` | `Long` | **Obrigatório:** Id do usuário. |
| `idCompany` | `Long` | **Obrigatório:** Id da empresa. |
| `taskStatus` | `String` | **Obrigatório:** status da tarefa. |
| `expirationDate` | `String` | **Obrigatório:** Data de expiração da tarefa padrão ISO 8601. |
<br/>

* <h3> Deleção de tarefa [JWT]</h3>
```dart
  DELETE /task/delete/{id}
```
<br/>
