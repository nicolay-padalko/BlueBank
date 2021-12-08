# BlueBank

![JAVA](https://img.shields.io/static/v1?label=JAVA&message=BACKEND&color=0091EA&style=flat&logo=JAVA)
![SPRING](https://img.shields.io/static/v1?label=Spring&message=FRAMEWORK&color=0091EA&style=flat&logo=Spring)

Este projeto visa a construção de uma aplicação bancaria digital, proposto pela professora da instituição _Gama Academy_, _mariannesalomao_.
A aplicação permite aos usuários realizarem operações de saques, depositos, e transferências, como também acesso ao extrado bancario.
Todas estas requisições são realizadas diretamente ao backend construido em Java utilizando Spring Framework.

## Pré Requisitos

- [**Git**](https://git-scm.com/)
- [**Java 11+**](https://jdk.java.net/15/)
- [**Mysql 8+**](https://dev.mysql.com/downloads/)

## Usando docker

Você pode rodar o banco de dados usando docker pelo comando abaixo:

```shell
docker-compose up mysql
# Ou
sudo docker-compose up mysql
```

## Equipe

- René Bastos - rsbastos
- Nicolay Padalko - nicolay-padalko
- Joanantha Matheus Vieira
- Jéssica Correa - jessicacorreaes
- Giselle Forjaz
- Chrystian Medeiros de Oliveira - oChrys
- Jéssika Sousa - jessikasousa

## End Points

### AUTENTICAÇÃO:
- POST(PARA FAZER LOGIN EMAIL E SENHA)\
 localhost:8080/auth 

### CLIENTE:
- GET (listar todos clientes) \
localhost:8080/clientes \
- GET (listar cliente por id) \
localhost:8080/clientes/{id}
- POST (salvar cliente) \
localhost:8080/clientes
- DELETE (deletar cliente por id)\
localhost:8080/clientes/{id}
- PUT (atualizar cliente SOMENTE TELEFONE EMAIL E SENHA) \
localhost:8080/clientes/{id}
- PUT (atualizar credenciais cliente SOMENTE CPF EMAIL E SENHA) \
localhost:8080/clientes/credenciais/{id}

### CONTA:
- GET (listar todas contas)\
localhost:8080/contas
- GET (filtrar conta por id)\
localhost:8080/contas/{id}
- DELETE (deletar conta por id)\
localhost:8080/contas/{id}

### FUNCIONÁRIO:
- GET (listar todos funcionarios)\
localhost:8080/funcionarios 
- GET (listar funcionario por id)\
localhost:8080/funcionarios/{id}
- POST (salvar funcionario)\
localhost:8080/funcionarios
- DELETE (deletar funcionario por id)\
localhost:8080/funcionarios/{id}
- PUT (atualizar funcionario SOMENTE TELEFONE EMAIL E SENHA)\
localhost:8080/funcionarios/{id}
- PUT (atualizar credenciais funcionario SOMENTE CPF EMAIL E SENHA)\
localhost:8080/funcionarios/credenciais/{id}
- POST (cadastrar email no topico de sns (mandar requisicao))\
localhost:8080/funcionarios/cadastrarEmail/{email}

### PERFIL
(ainda nao tem nenhuma aplicacao direta fazer esses metodos mas estão criados):
- POST (cadastrar perfil)\
localhost:8080/perfis
- DELETE (deletar perfil)\
localhost:8080/perfis

### TRANSAÇÃO:
#### seta conta origem como a conta de quem esta pedindo
- GET (listar todas transacoes)\
localhost:8080/transacoes
- POST (depositar)\
localhost:8080/depositar
- POST (sacar)\
localhost:8080/sacar
- POST (transferir)\
localhost:8080/transferir/{contaIdDestino}
#### vai ser usado somente pelos admin
- POST (depositar)\
localhost:8080/depositar/{contaId}
- POST (sacar)\
localhost:8080/sacar/{contaId}
- POST (transferir)\
localhost:8080/transferir/{contaIdOrigem}/{contaIdDestino}

