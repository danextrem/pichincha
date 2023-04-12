### Demo Tipo de Cambio
## Version de Java 11

## Generador de Tocken
`<Post>` : <http://localhost:8080/v1/login>

#### usuario admin
```json
{
    "email" : "dan@gmail.com",
    "password" : "1234"
}
```

#### usuario user
```json
{
    "email" : "david@gmail.com",
    "password" : "1234"
}
```

## Tipo de Cambio
En esta ocacion se mostrara el tipo de cambio moneda Sol y Dolar

`<Get>` : <http://localhost:8080/v1/getByCointExchange?one=1&two=2>


## Actualiza el tipo de Cambio

`<Put>` : <[http://localhost:8080/v1/getByCointExchange?one=1&two=2](http://localhost:8080/v1/updateByCointExchange)>

```json
{
    "coint_one" : 1,
    "coint_two" : 2,
    "buy" : 3.951,
    "sale" : 3.589
}
```


