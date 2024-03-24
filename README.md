# Prova da netprecision para desenvolvedor Java

## Rotas (endpoints) da API

### $\color{green}{POST}$ `/pedido` Criar pedido
```json
// Response example //
{
  "id": 1,
  "itensPedidos": null,
  "fechado": false
}
```

### $\color{cyan}{GET}$ `/pedido/{id}` Exibir pedido
```json
// Response example //
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

### $\color{cyan}{GET}$ `/pedido/{id}/total` Exibir valor total do pedido
```json
// Response example //
12.0
```

### $\color{green}{POST}$ `/pedido/{id}/adicionar` Adicionar produto ao pedido
```json
// Body example //
{
  "codigoProduto": 1155,
  "quantidade": 2
}
```

```json
//
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



