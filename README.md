# API Gerenciando pedidos de uma gráfica 

## 💻 Sobre o projeto

Este projeto apresenta uma solução para o desafio do desenvolvimento de um sistema 
para gerenciar as ordens de pedido de uma gráfica e cadastro dos funcionários em formato de API.

A API permite cadastrar e gerenciar os funcionarios da empresa. E também fazer o controle 
dos pedidos, conforme passam pelos setores da empresa: Vendas, Produção e Contabilidade. 


---

## Índice

<a href="#api-gerenciando-pedidos-de-uma-gráfica">API Gerenciando pedidos de uma gráfica</a></br>
<a href="#-sobre-o-projeto">💻 Sobre o projeto</a></br>
<a href="#-requisitos-apresentados-pelo-cliente">📃 Requisitos apresentados pelo cliente</a></br>
<a href="#%EF%B8%8F-funcionalidades-estabelecidas">⚙️ Funcionalidades Estabelecidas</a></br>
<a href="#%EF%B8%8Fetapas">Etapas</a></br>
<a href="#-tecnologias">Tecnologias</a></br>
<a href="#como-utilizar">Como utilizar</a></br>
<a href="#um-melhor-detalhamento-do-uso-desta-api-é-apresentado-no-video-deste-link">Video Projeto</a></br>

---

## 📃 Requisitos apresentados pelo cliente


O cliente que será atendido será uma gráfica onde são impressos banners, revistas, material publicitário, livros,</br> 
entre outros produtos. O cliente procurou você para resolver um problema muito específico dele. 
O cliente deseja realizar o gerenciamento das ordem dos pedidos que serão impressos. Portanto,
ele gostaria que você desenvolvesse uma solução elegante onde alguns requisitos fossem satisfeitos.</br>

Lembre-se que para esse exercício de UML você terá vários requisitos levantados na entrevista, 
porém nem todos necessariamente são relevantes ao seu sistema. Tome as decisões de analista
de sistemas e construa um diagrama de classes que atenda a necessidade de gerenciar os pedidos.</br>

O sistema deverá ter tipos diferentes de usuários: gerente, empregado e empregado terceirizado.
O gerente deverá ser o administrador do sistema e cada gerente terá um setor específico da gráfica para cuidar. 
Por exemplo (gerente financeiro, gerente da produção, gerente de vendas).
O empregado é assalariado mensal e opera qualquer tipo de equipamento dentro da gráfica
O empregado terceirizado não faz parte do quadro de funcionários, porém, executam tarefas importantes na empresa. 
Desde limpeza até alimentação dos funcionários.
Cada empregado ocupa uma função dentro da empresa, isso deve ser registrado no cadastro.
Cada pedido é composto por data e hora de emissão e de finalização, bem como o preço. 
O pedido é feito pela equipe de vendas da gráfica e é repassado a produção.
Cada pedido é vinculado a um funcionário do setor de vendas para que ele se responsabilize pelo que será entregue.
A produção deverá seguir o sistema de gerenciamento de pedidos e cada funcionário da produção deverá 
se responsabilizar pela produção de um produto (impressão).
Cada produto que será impresso pode ter um tipo diferente ( banner, livro, panfleto).
O preço de cada impressão varia através de uma tabela fixa que é definida pelos gerentes.</br>

Um ponto adicional e importante é que o pode dar a possibilidade do operador (gerente)
adicionar funcionários ao quadro de funcionários. O funcionário poderá incluir
um novo pedido ao sistema que será enviado a produção. Por fim, 
o sistema deve apresentar uma lista de pedidos que estão na fila para serem confeccionados.</br>

Os requisitos do cliente são apresentados no desafio de programação orientada a objetos encontrado 
neste link: https://www.computersciencemaster.com.br/exercicio-sistema-de-gerenciamento-de-pedidos-grafica/.

---

## ⚙️ Funcionalidades Estabelecidas

### ChefeGeral:
* CRUD de gerentes
 ### Gerentes:
* CRUD funcionário do seu setor
*  CRUD produto em conjunto com os três tipos de gerentes
*  Cancelar um pedido conforme o statusPedido
* ListarPedidos na Fila
###  Vendedor:
* ListarPedidos
* ListarPedidoPorId
* AbrirPedido
* AtualizarDadosPedido
* ColocarFila
* CalculaTrocoFecharPedido
###  Copiador:
* ListarPedidosFila
*  TirarFilaProduzir
* ListarPedidosProduzindo
*  FecharImpressao
###  Contador:
* ListarPedidoPagoFinalizado
* ListarPedidoRegistrado
*  RegistrarPedido
*  ListarPedidosPorNome
* PedidosPorProduto
* PedidosMaioresQue

##### Mais funcionalidades podem ser estabelecidas ao longo do projeto conforme necessário para o bom funcionamento do sistema

---

## ✔️Etapas

<img src="https://github.com/andersonlenzjava/api.gerenciando.pedidos.grafica/blob/main/Diagrama_entidades_inicial.png">

- [x] Modelagem do relacionamento das entidades.
- [x] Estabelecimento das funcionalidades.
  - [x] CRUD.
  - [x] Regras de negócio.
  - [x] Modelagem do sistema. 
    - [x] Domain.
      - [x] Entity.
      - [x] Repository.
      - [x] EntityRegister.
      - [x] EntityResponse.
    - [x] Controller.
    - [x] Service.
    - [x] Migrations. 

---

## 🛠 Tecnologias

As seguintes tecnologias foram utilizadas no desenvolvimento da API Rest do projeto:

- **[Java 17](https://www.oracle.com/java)**
- **[Spring Boot 3](https://spring.io/projects/spring-boot)**
- **[Maven](https://maven.apache.org)**
- **[PostgreSQL](https://www.postgresql.org/)**
- **[Hibernate](https://hibernate.org)**
- **[Flyway](https://flywaydb.org)**
- **[Lombok](https://projectlombok.org)**
- **[Postman](https://www.postman.com/)**
- **[Docker](https://www.docker.com/)**

---

## Como utilizar

#### Carregamento do projeto

 <p>Para utilizar o sistema é necessário rodar o sistema offline dentro de alguma IDE, através do Spring Boot.</p>
   <p><strong>Etapas:</strong></p>
     - Download do projeto e descompactar </br>
     - Fazer a atualização das dependências com o Maven</br>
     - Abrir o docker </br>
     - Rodar com a pasta database-docker selecionada no terminal: 
        docker-compose up -d
     - Ativar a classe main da aplicação </br>
     - Abrir a collection de endpoints com o software que gerencia requisições como PostMan</br>

#### Na operacionalização do sistema obedecer a seguinte sequência:

Na operacionalização do sistema obedecer a seguinte sequência  na collection de endpoints junto ao arquivo do postman em anexo:</br>
Cadastrar todas as entidades:</br>
###### ChefeGeral
- ChefeGeral
- GerenteVendas
- GerenteProducao
- GerenteFinanceiro
###### GerenteVendas
- Vendedor
###### GerenteProducao
- Copiador
###### GerenteFinanceiro
- Contador

Para o produto obedecer a seguinte sequência:

###### Vendedor
- AbrirPedido
- listarPedidoPorId
- colocarFilaProducao

###### Copiador
- listarPedidosFila
- tirarFilaProduzir 
- fecharImpressao

###### Vendedor
- calculaTrocoFechaPedido

###### Contador
- listarPedidosPagoFinalizado
- documentarPedido
- listarPedidosRegistrado

---

## Um melhor detalhamento do uso desta API é apresentado nos videos com os links abaixo.
Video 1 - Apresentação do desafio e estabelecimento das funcionalidades, apresentação do diagrama de entidades.</br>
https://youtu.be/dOMVVkOmFRY </br>
Video 2 - Apresentação das técnicas e tecnologias utilizadas e o CRUD das entidades funcionário.</br>
https://youtu.be/xidGlSMrdRA </br>
Video 3 - Apresentação do CRUD do produto.</br>
https://youtu.be/iRwtkG2a_LU </br>
Video 4 - Parte 1 e 2 - Apresentação da integração das classes junto ao objeto pedido. </br>
Parte 1 - https://youtu.be/OtC4g4cDzYU </br>
Parte 2 - https://youtu.be/VzZBttbjSKg </br>

---

## Considerações:

<p> Neste sistema foi possível construir uma API REST com Spring Boot, realizando operações de CRUD, regras de negócio</br>
fazendo a persistência dos dados de acordo com a sua estrutura</p>
