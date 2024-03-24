# Prova da netprecision para desenvolvedor Java

## Rotas (endpoints) da API

### $\color{green}{POST}$ `/pedido` &emsp; Criar pedido
```json
Response example
{
  "id": 1,
  "itensPedidos": null,
  "fechado": false
}
```

### $\color{cyan}{GET}$ `/pedido/{id}` &emsp; Exibir pedido
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

### $\color{cyan}{GET}$ `/pedido/{id}/total` &emsp; Exibir valor total do pedido
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
