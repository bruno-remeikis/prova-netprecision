# Prova da netprecision para desenvolvedor Java

## Rotas (endpoints) da API

### <code style="color: lightgreen">POST</code > `/pedido` Criar pedido
<div class="row">
<div class="col-md-6">
<pre class="language-json">
-- Body --
{
  "chave1": "valor1",
  "chave2": "valor2"
}
</pre>
</div>
<div class="col-md-6">
<pre class="language-json">
-- Response --
{
  "chave3": "valor3",
  "chave4": 4
}
</pre>
</div>
</div>

<table>
    <tr>
        <th>Response</th>
        <th>Response</th>
    </tr>
    <tr>
        <td><code lang="json">{ "aaaaa": aaa }</code></td>
        <td>bbb</td>
    </tr>
</table>

- Response:
```json
{
  "id": number,
  "itensPedidos": null,
  "fechado": false
}
```

### <code style="color: cyan">GET</code > `/pedido/{id}` Exibir pedido
- Response:
```json
{
  
}
```

### <code style="color: cyan">GET</code > `/pedido/{id}/total` Exibir valor total do pedido

### <code style="color: lightgreen">POST</code > `/pedido/{id}/adicionar` Adicionar produto ao pedido

```json
--- BODY ---
{
  "aaa": 1
}

```

