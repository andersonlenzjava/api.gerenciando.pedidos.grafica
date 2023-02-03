# API Gerenciando pedidos de uma gráfica 

## 💻 Sobre o projeto

Este projeto apresenta uma solução para o desafio do desenvolvimento de um sistema 
para gerenciar as ordens de pedido de uma gráfica e cadastro dos funcionarios em formato de API.

A API permite cadadastrar e gerenciar os funcionarios da empresa. E também fazer o controle 
dos pedidos, conforme passam pelos setores da empresa: Vendas, Produção e Contabilidade. 


## Requisitos apresentados pelo cliente


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

## Etapas

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
