<h1  align="center">< progDashboard BETA /></h1>


<h3>O que é o progDashboard?</h3>
<h5>É uma API para empresas terem um maior controle sobre quais atividades determinado funcionário(usuário) tem, como está a situação dessa tarefa, seu prazo de término, etc.</h5>
<br/>

<h3>Como o progDashboard funciona?</h3>
<h5>Primeiramente é necessário criar uma empresa(company), após isso, você usando o token JWT dessa empresa tem permissões de administrador. Com a permissão de administrador você pode criar usuários e tarefas para esse determinado usuário, excluir usuários e também tarefas. Tudo isso é salvo localmente usando o banco de dados MySQL</h5>

<h5>Ao criar o usuário, além de determinar suas informações como cargo e nome, você determina também informações de login daquele usuário, para que posteriormente o funcionário entre na área dele usando essas credenciais.</h5>

<h5>Já nas tarefas são inseridas informações importantíssimas para o controle posteriormente, como a data de criação da tarefa, prazo de termino, status, etc. Onde o usuário pode atualizar o status em que a tarefa se encontra.</h5>
<br/>

<h1 align="center"> Rotas de empresa </h1> 


* <h3> Criação</h3>
```dart
  POST /company/register
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `name` | `string` | **Obrigatório:** Nome da empresa. |
| `email` | `string` | **Obrigatório:** Email de acesso. |
| `password` | `string` | **Obrigatório:** Senha de acesso. |
<br/>

* <h3>Login</h3>
```dart
  POST /company/login
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

* <h3> Todas tarefas [JWT]</h3>
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
  DELETE /company/deleteUser/{id}
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


<h1 align="center"> Rotas de usuário </h1> 


* <h3>Login</h3>
```dart
  POST /user/login
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `username` | `string` | **Obrigatório:** Username de acesso. |
| `password` | `string` | **Obrigatório:** Senha de acesso. |

<br/>

* <h3> Detalhes [JWT]</h3>
```dart
  GET /user/{id}
```
<br/>

* <h3>Alterar status da tarefa [JWT]</h3>
```dart
  POST /user/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `Long` | **Obrigatório:** Id da tarefa. |
| `taskStatus` | `string` | **Obrigatório:** Novo status da tarefa. |
| `updateDate` | `string` | **Obrigatório:** Data de alteração da tarefa padrão ISO 8601. |

<br/>


<h1 align="center"> Outras rotas </h1> 

* <h3>Autênticação automática com token [JWT]</h3>
```dart
  POST /auth
```

<br/>

<h1 align="center"> Como usar localmente: </h1> 

1- Instale e configure todo o ambiente Java(JDK +17);<br/>
2- Abra no arquivo "pom.xml" e atualize, dessa forma irá baixar todas as dependências;<br/>
3- Adicionar a conexão do seu MySQL no arquivo "application-progDashboard.properties"<br/>
4- Inicie o projeto spring.<br/>

OBS: Caso você use o Insomnia, importe o arquivo "progDashboard Insomnia Collection" nele e uma coleção com todas rotas acima será criada.
