# BlueBank
![JAVA](https://img.shields.io/static/v1?label=JAVA&message=BACKEND&color=0091EA&style=flat&logo=JAVA)
![SPRING](https://img.shields.io/static/v1?label=Spring&message=FRAMEWORK&color=0091EA&style=flat&logo=Spring)

Este projeto visa a construção de uma aplicação bancaria digital, que permite aos usuários do Blue Bank realizarem operações de saques, depositos, e transferências, como também acesso ao extrado bancario. 
Todas estas requisições são realizadas diretamente ao backend construido em Java utilizando Spring Framework.

Projeto de apresentação final do PAN Academy, um programa de treinamento do Banco PAN em parceria com a Gama Academy. Aulas ministradas por Marianne Salomão (https://github.com/mariannesalomao).

## Pré Requisitos
* [**Git**](https://git-scm.com/)
* [**Java 11+**](https://jdk.java.net/15/)
* [**Mysql 8+**](https://dev.mysql.com/downloads/)

<h3>Requisitos e funcionalidades</h3>

- <h4> Obrigatórios da entrega:</h4>

    - Planejamento (Metodologias Ágeis):
        - [X] Kanban com todas as tarefas organizadas e responsáveis definidos.
    - Projeto (Backend):
        - [X] Cadastro de clientes.
        - [X] Listagem de clientes.
        - [X] Atualização de clientes.
        - [X] Deletar clientes.
        - [X] Histórico de transações entre contas.
    - Banco de Dados:
        - [X] Tabelas bem estruturadas e populadas com valores para testes.
        - [X] O banco deve ser entregue em script SQL junto ao repositório.
    - Gerais:
        - [X] O código deve ser entregue em um repositório no Github.
        - [X] A API deve ser disponibilizada em ambiente AWS com EC2 e em Beanstalk.
        - [X] A aplicação deve ter um pipeline em Jenkins ou no <b>Aws Build</b>.
        - [X] A aplicação precisa ser configurada no API Gateway da AWS.
        - [X] A aplicação precisa ter no mínimo um endpoint de SNS para cadastro de emails e verificação automática.
        - [X] A aplicação precisa ter no mínimo um Lambda.
        - [X] Liste os endpoints no README.md
        - [X] O Banco deve ser entregue em script SQL junto ao repositório.


## Listagem de Endpoints por Classes:

<H3>autenticacao-controle</H3>

| MÉTODO | ENDPOINT | DESCRIÇÃO |
| --- | --- | --- |
| <a><img src="https://img.shields.io/badge/-POST-brightgreen"></a> | /auth | Autenticação por Token |

<H3>cliente-controle</h3>

| MÉTODO | ENDPOINT | DESCRIÇÃO |
| --- | --- | --- |
| <a><img src="https://img.shields.io/badge/-GET-9cf"></a> | /clientes | Lista todos os clientes |
| <a><img src="https://img.shields.io/badge/-POST-brightgreen"></a> | /clientes | Cadastra um cliente, com atribuição dinâmica de ID |
| <a><img src="https://img.shields.io/badge/-GET-9cf"></a> | /clientes/{id} | Busca e retorna um cliente, filtrando pelo ID |
| <a><img src="https://img.shields.io/badge/-PUT%20%20%20-orange"></a> | /clientes/{id} | Atualização de telefone, email e senha do cliente, filtrando pelo ID |
| <a><img src="https://img.shields.io/badge/-DELETE-red"></a> | /clientes/{id} | Busca e deleta um cliente, filtrando pelo ID |
| <a><img src="https://img.shields.io/badge/-POST-brightgreen"></a> | /clientes/cadastrarEmail/{email} | Cadastra o e-mail do cliente |


- <h4>Extras:</h4>
- 

**Para contribuir:**

```bash
git clone https://github.com/nicolay-padalko/BlueBank.git
```

## Equipe PanCoders
<a href="https://github.com/oChrys" target="_blank" rel="noopener noreferrer"><img src="https://img.shields.io/badge/GitHub-100000?style=lat-square&logo=github&logoColor=white" alt="GitHub"></a> <a href="https://www.linkedin.com/in/chrystianmoliveira/" target="_blank" rel="noopener noreferrer"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=lat-square&logo=linkedin&logoColor=white" alt="LinkedIn"></a>  Chrystian Medeiros 

<a href="https://github.com/GiselleForjaz" target="_blank" rel="noopener noreferrer"><img src="https://img.shields.io/badge/GitHub-100000?style=lat-square&logo=github&logoColor=white" alt="GitHub"></a> <a href="https://www.linkedin.com/in/giselle-forjaz-a8166b59/" target="_blank" rel="noopener noreferrer"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=lat-square&logo=linkedin&logoColor=white" alt="LinkedIn"></a>  Giselle Forjaz

<a href="https://github.com/jessicacorreaes" target="_blank" rel="noopener noreferrer"><img src="https://img.shields.io/badge/GitHub-100000?style=lat-square&logo=github&logoColor=white" alt="GitHub"></a> <a href="https://www.linkedin.com/in/jessicacorreaes/" target="_blank" rel="noopener noreferrer"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=lat-square&logo=linkedin&logoColor=white" alt="LinkedIn"></a>  Jéssica Corrêa 

<a href="https://github.com/jessikasousa" target="_blank" rel="noopener noreferrer"><img src="https://img.shields.io/badge/GitHub-100000?style=lat-square&logo=github&logoColor=white" alt="GitHub"></a> <a href="https://www.linkedin.com/in/jessikasousa" target="_blank" rel="noopener noreferrer"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=lat-square&logo=linkedin&logoColor=white" alt="LinkedIn"></a>  Jéssika Sousa 

<a href="https://github.com/jmvgcomp" target="_blank" rel="noopener noreferrer"><img src="https://img.shields.io/badge/GitHub-100000?style=lat-square&logo=github&logoColor=white" alt="GitHub"></a> <a href="https://www.linkedin.com/in/joananthamatheus/" target="_blank" rel="noopener noreferrer"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=lat-square&logo=linkedin&logoColor=white" alt="LinkedIn"></a>  Joanantha Matheus Vieira

<a href="https://github.com/nicolay-padalko" target="_blank" rel="noopener noreferrer"><img src="https://img.shields.io/badge/GitHub-100000?style=lat-square&logo=github&logoColor=white" alt="GitHub"></a> <a href="https://www.linkedin.com/in/nicolay-padalko-a9a90923/" target="_blank" rel="noopener noreferrer"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=lat-square&logo=linkedin&logoColor=white" alt="LinkedIn"></a>  Nicolay Padalko

<a href="https://github.com/rsbastos" target="_blank" rel="noopener noreferrer"><img src="https://img.shields.io/badge/GitHub-100000?style=lat-square&logo=github&logoColor=white" alt="GitHub"></a> <a href="https://www.linkedin.com/in/ren%C3%A9-dos-santos-bastos-9125661b0/" target="_blank" rel="noopener noreferrer"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=lat-square&logo=linkedin&logoColor=white" alt="LinkedIn"></a>  René Bastos

