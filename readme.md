# Hacer un get de todas las recomendaciones #


## Recomendaciones actuales: ##
- 40    Instrumentos Destacados
41  Instrumentos Top Down
42  Instrumentos Top Up
43  Instrumentos Recomendados por Operacion
44  Instrumentos Recomendados por Otros Usuarios
48  Top up Cedears
49  Top UP Bonos
50  Top UP Acciones
- 53    Acciones Recomendaciones
- 54    Cedears Recomendaciones
- 55    Bonos Recomendaciones

## Lo que hablaron con Manu ##
"general",
"cedears",
"acciones",
"bonos"


## Como cargan las nuevas recomendaciones: ##
PUT /bz-marketdata/v1/recommendations/name

## PAYLOAD ##
```json
{
    "id": "d993723f-5811-4795-aacc-bf11f5879aa4", 
    "name": "bonos", 
    "instrumentIds": [
        {"securityId": "AL29-0003-C-CT-ARS", "ordenamiento": 1}, 
        {"securityId": "GD35-0003-C-CT-ARS", "ordenamiento": 2},
        {"securityId": "AF20-0003-C-CT-ARS", "ordenamiento": 3}
    ]
}
```

```json
{
    "id": "d993723f-5811-4795-aacc-t1", 
    "name": "t1", 
    "instrumentIds": [
        {"securityId": "AL29-0003-C-CT-ARS", "ordenamiento": 1}, 
        {"securityId": "GD35-0003-C-CT-ARS", "ordenamiento": 2}
    ],
    "personsIds": [1231,1252]
}
```
