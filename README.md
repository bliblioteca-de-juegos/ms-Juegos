# ms-Juegos

Microservicio encargado del catalogo principal de juegos.

## Responsabilidad

- Crear juegos.
- Consultar juegos.
- Buscar por titulo.
- Filtrar por categoria y precio.
- Asociar juegos a categoria y clasificacion.
- Validar categoria y clasificacion antes de guardar.

## Datos tecnicos

| Item | Valor |
| --- | --- |
| Puerto | `8082` |
| Base de datos | `juegos_db` |
| Ruta base | `/api/v2/juegos` |
| HATEOAS | `/api/v2/hateoas/juegos` |
| Swagger | `http://localhost:8082/doc/swagger-ui.html` |
| Eureka name | `ms-juegos` |

## Endpoints principales

- `GET /api/v2/juegos`
- `GET /api/v2/juegos/{id}`
- `GET /api/v2/juegos/categoria/{cateId}`
- `GET /api/v2/juegos/buscar`
- `GET /api/v2/juegos/precio`
- `POST /api/v2/juegos`
- `PUT /api/v2/juegos/{id}`
- `DELETE /api/v2/juegos/{id}`

## Comunicacion

- Usa WebClient para validar categoria en `ms-categoria`.
- Usa WebClient para validar clasificacion en `ms-clasificacion`.
- Se registra en Eureka.

## Datos demo

Incluye `DataInitializer` y `DataLoader`. El `DataLoader` esta asociado al perfil `dev` para facilitar datos de prueba durante desarrollo.

## Ejecucion local

```bash
./mvnw spring-boot:run
```

## Ejecucion con Docker

Desde la repo `Infraestructura`:

```bash
docker compose up -d --build ms-juegos
```

