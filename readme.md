# Recomendaciones #



1. Revisar validaciones de :

  log.info("-----------No Tiene habilitado el Tipo De watchList -------------------- ");
  log.info("-----------No Tiene Elementos el paylaod para cargar instrumentos -------------------- ");
  log.info("-----------El payload Tiene Ordernamientos Duplicados-------------------- ");
  log.info("-----------El payload Tiene SecurityIds Duplicados-------------------- ");
  log.info("-----------El payload Tiene SecurityIds Inexistentes-------------------- ");
  log.info("-----------El payload Tiene Personas Invalidas o Inexistentes-------------------- ");


2. Por ahora las recomnedaciones pueden ser acumulativas por el uuid, lo que presento al front con MD es otra historia.
Puedo mantener siempre 1 sola por el nombre o acumular y luego purgar.


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
