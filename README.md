# Prova da netprecision para desenvolvedor Java

Esta é uma API desenvolvida para responder à prova da [netprecision](https://netprecision.com.br/).

## Demonstração

### API
Para demonstração, esta API foi publicada na [Heroku](https://dashboard.heroku.com/)
- Link para acesso da demo: [https://prova-netprecision-bruno-rmks-372131324b1e.herokuapp.com/](https://prova-netprecision-bruno-rmks-372131324b1e.herokuapp.com/)

### Pagina web
A interface web foi publicada na [Vercel]()
- Link para acesso da pagina: 
- Link para o repositório do GitHub: [https://github.com/bruno-remeikis/prova-netprecision-web](https://github.com/bruno-remeikis/prova-netprecision-web)

## Tecnologias
Para o desenvolvimento desta API, foram utilizadas as seguintes tecnologias:
- Java 21: Linguagem de programação
  - Spring Boot: framework para criação de APIs REST
  - JPA: Mapear as entidades do banco de dados
  - Hibernate: Acessar e manipular banco de dados
  - Lombok: Tornar criação de classes model menos verbosa
  - JUnit: Biblioteca para testes unitários
  - Mockito: Biblioteca para mock de dados
- MySQL: Banco de dados
## Rotas (endpoints) da API

### $\color{cyan}{GET}$ `/health` &emsp; Verificar se API está rodando &emsp; [link](https://prova-netprecision-bruno-rmks-372131324b1e.herokuapp.com/health)
```json
Response example
API is running...
```

### $\color{cyan}{GET}$ `/produto` &emsp; Exibir todos os produtos &emsp; [link](https://prova-netprecision-bruno-rmks-372131324b1e.herokuapp.com/produto)
```json
Response example
[
  {
    "codigo": 1147,
    "nome": "Cachorro Quente",
    "preco": 3.0
  },
  ...
]
```

### $\color{green}{POST}$ `/pedido` &emsp; Criar pedido
```json
Response example
{
  "id": 1,
  "itensPedidos": null,
  "fechado": false
}
```

### $\color{cyan}{GET}$ `/pedido/{id}` &emsp; Exibir pedido &emsp; [link](https://prova-netprecision-bruno-rmks-372131324b1e.herokuapp.com/pedido/1)
```json
Response example
{
  "id": 1,
  "itensPedidos": [
    {
      "id": 1,
      "produto": {
        "codigo": 1155,
        "nome": "X-Burguer",
        "preco": 6.0
      },
      "quantidade": 2
    }
  ],
  "fechado": true
}
```

### $\color{cyan}{GET}$ `/pedido/{id}/total` &emsp; Exibir valor total do pedido &emsp; [link](https://prova-netprecision-bruno-rmks-372131324b1e.herokuapp.com/pedido/1/total)
```json
Response example
12.0
```

### $\color{green}{POST}$ `/pedido/{id}/adicionar` &emsp; Adicionar produto ao pedido
```json
Body example
{
  "codigoProduto": 1155,
  "quantidade": 2
}
```

```json
Response example
{
  "id": 1,
  "itensPedidos": [
    {
      "id": 1,
      "produto": {
        "codigo": 1155,
        "nome": "X-Burguer",
        "preco": 6.0
      },
      "quantidade": 2
    }
  ],
  "fechado": false
}
```

### $\color{green}{POST}$ `/pedido/{id}` &emsp; Adicionar produtos ao pedido e calcular seu total
```json
Body example
[
  {
    "codigoProduto": 1147,
    "quantidade": 1
  }, {
    "codigoProduto": 1164,
    "quantidade": 2
  }
]
```

```json
Response example
30.0
```

### $\color{orange}{PUT}$ `/pedido/{id}/fechar` &emsp; Fechar pedido
```json
Response example
{
  "id": 1,
  "itensPedidos": [ ... ],
  "fechado": true
}
```

### $\color{red}{DELETE}$ `/pedido/{id}/remover` &emsp; Remover produto do pedido
```json
Body example
{
  "codigoProduto": 1155,
  "quantidade": 10
}
```

```json
Response example
{
  "id": 1,
  "itensPedidos": [
    {
      "id": 1,
      "produto": {
        "codigo": 1155,
        "nome": "X-Burguer",
        "preco": 6.0
      },
      "quantidade": 1
    }
  ],
  "fechado": false
}
```
