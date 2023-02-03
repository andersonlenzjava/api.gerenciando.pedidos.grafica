# API Gerenciando pedidos de uma gráfica 

## 💻 Sobre o projeto

Este projeto apresenta uma solução para o desafio do desenvolvimento de um sistema 
para gerenciar as ordens de pedido de uma gráfica e cadastro dos funcionarios em formato de API.

A API permite cadadastrar e gerenciar os funcionarios da empresa. E também fazer o controle 
dos pedidos, conforme passam pelos setores da empresa: Vendas, Produção e Contabilidade. 


---

## 📃 Requisitos apresentados pelo cliente


O cliente que será atendido será uma gráfica onde são impressos banners, revistas, material publicitário, livros,</br> 
entre outros produtos. O cliente procurou você para resolver um problema muito específico dele. </br>
O cliente deseja realizar o gerenciamento das ordem dos pedidos que serão impressos. Portanto,</br>
ele gostaria que você desenvolvesse uma solução elegante onde alguns requisitos fossem satisfeitos.</br>

Lembre-se que para esse exercício de UML você terá vários requisitos levantados na entrevista, </br>
porém nem todos necessariamente são relevantes ao seu sistema. Tome as decisões de analista</br>
de sistemas e construa um diagrama de classes que atenda a necessidade de gerenciar os pedidos.</br>

O sistema deverá ter tipos diferentes de usuários: gerente, empregado e empregado terceirizado.</br>
O gerente deverá ser o administrador do sistema e cada gerente terá um setor específico da gráfica para cuidar. </br>
Por exemplo (gerente financeiro, gerente da produção, gerente de vendas).</br>
O empregado é assalariado mensal e opera qualquer tipo de equipamento dentro da gráfica</br>
O empregado terceirizado não faz parte do quadro de funcionários, porém, executam tarefas importantes na empresa. </br>
Desde limpeza até alimentação dos funcionários.</br>
Cada empregado ocupa uma função dentro da empresa, isso deve ser registrado no cadastro.</br>
Cada pedido é composto por data e hora de emissão e de finalização, bem como o preço. </br>
O pedido é feito pela equipe de vendas da gráfica e é repassado a produção.</br>
Cada pedido é vinculado a um funcionário do setor de vendas para que ele se responsabilize pelo que será entregue.</br>
A produção deverá seguir o sistema de gerenciamento de pedidos e cada funcionário da produção deverá </br>
se responsabilizar pela produção de um produto (impressão).</br>
Cada produto que será impresso pode ter um tipo diferente ( banner, livro, panfleto).</br>
O preço de cada impressão varia através de uma tabela fixa que é definida pelos gerentes.</br>

Um ponto adicional e importante é que o pode dar a possibilidade do operador (gerente)</br>
adicionar funcionários ao quadro de funcionários. O funcionário poderá incluir</br>
um novo pedido ao sistema que será enviado a produção. Por fim, </br>
o sistema deve apresentar uma lista de pedidos que estão na fila para serem confeccionados.</br>

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
* ListarPeidodosProduzindo
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

## Etapas

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

## Como utilizar

#### Carregamento do projeto

 <p>Neste momento para utilizar o sistema é necessário rodar o sistema offline dentro de alguma IDE, através do Spring Boot.</p>
   <p><strong>Etapas:</strong></p>
     - Download do projeto e descompactar </br>
     - Fazer a atualização das dependências com o Maven</br>
     - Fazer a configuração do banco de dados de sua preferência</br>
     - Criar o banco de dados </br>
     - Configurar a API a este banco de dados</br>
     - Rodar o projeto com a app.properties em spring.jpa.hibernate.ddl-auto=create</br>
     - Em seguida colocar spring.jpa.hibernate.ddl-auto=none</br>
     - Abrir a collection de endpoints com o software que gerencia requisições PostMan</br>

#### Na operacionalização do sistema obedecer a seguinte sequência:

Na operacionalização do sistema obedecer a seguinte sequência  na collection de endpoints junto ao arquivo do postman em anexo:</br>
Cadastrar todas as entidades:</br>
Cadastrar as entidades: 
#### ChefeGeral
- ChefeGeral
- GerenteVendas
- GerenteProducao
- GerenteFinanceiro
#### GerenteVendas
- Vendedor
#### GerenteProducao
- Copiador
#### GerenteFinanceiro
- Contador

Para o produto obedecer a seguinte sequência:

#### Vendedor
- AbrirPedido
- listarPedidoPorId
- colocarFilaProducao

#### Copiador
- listarPedidosFila
- tirarFilaProduzir
- fecharImpressao

#### Vendedor
- calculaTrocoFechaPedido

#### Contador
- listarPedidosPagoFinalizado
- documentarPedido
- listarPedidosRegistrado

---

## Um melhor detalhamento do uso desta API é apresentado no video deste link.

---

## Considerações:

<p> Neste sistema foi possível construir uma API REST com Spring Boot, realizando operações de CRUD, regras de negócio</br>
fazendo a persistência dos dados de acordo com a sua estrutura</p>
