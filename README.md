# Luxury Wear Service

## Descripción del Proyecto

**Luxury Wear Service** es el backend de **Luxury Wear**, una plataforma de alquiler de moda de alta gama. Este servicio, desarrollado en Java con Spring Boot, proporciona una API robusta para gestionar las funcionalidades principales de la plataforma, incluyendo la administración de productos, usuarios, alquileres y pagos. 

Diseñado para ser escalable, seguro y de fácil despliegue, el servicio utiliza Docker para la contenedorización y MySQL como base de datos, asegurando una configuración consistente y confiable tanto en desarrollo como en producción.

## Endpoints Disponibles

A continuación se detallan los endpoints disponibles en la API de **Luxury Wear Service** y sus respectivas funcionalidades.

| Método | Endpoint                     | Descripción                                        |
|--------|------------------------------|----------------------------------------------------|
| GET    | `/api/v1/products/{id}`      | Obtiene la información de un producto por `id`.    |
| GET    | `/api/v1/products`           | Obtiene una lista de todos los productos.          |
| GET    | `/api/v1/products/top-rents` | Obtiene una lista de productos en orden aleatorio. |


## Ejecutar Luxury Wear Service Localmente con Docker

Esta sección explica cómo ejecutar el **Luxury Wear Service** localmente utilizando Docker. El archivo `docker-compose.yml` provisiona tanto la base de datos MySQL como la aplicación Spring Boot en contenedores separados, permitiendo una configuración rápida y sencilla en tu entorno local.

### Requisitos

Para ejecutar esta aplicación, asegúrate de tener las siguientes dependencias instaladas:

- **Docker Engine**: versión 20.10.7 o superior.
    - Puedes instalar Docker Engine desde el [sitio web de Docker](https://docs.docker.com/engine/install/).
- **Docker Compose**: versión 1.29.2 o superior.
    - Puedes instalar Docker Compose desde el [sitio web de Docker](https://docs.docker.com/compose/install/).
- **Java 17**: Necesario para ejecutar la aplicación.
- **Maven 3.6** o superior: Usado para construir el proyecto y gestionar sus dependencias.

### Clonar el Repositorio

Clona el repositorio y accede al directorio del proyecto:

```bash
git clone https://github.com/Integrator-group-5/backend.git
cd backend
```

### Cambiar a la rama `fernando`

Cámbiate a la rama `fernando` con el siguiente comando:

```bash
git checkout fernando
```

### Pasos para Ejecutar Localmente

Antes de comenzar, asegúrate de que el `daemon` de Docker esté ejecutándose en tu máquina o abre **Docker Desktop** para iniciarlo.

1. **Construir e Iniciar los Contenedores**:
   Para iniciar la aplicación localmente con Docker, ejecuta el siguiente comando:

   ```bash
   docker-compose -f docker-compose.yml up --build -d
   ```
   
   Este comando:
   - Construirá la aplicación Spring Boot usando el `Dockerfile`.
   - Iniciará la base de datos MySQL.
   - Iniciará la aplicación Spring Boot y la conectará a la base de datos MySQL.

### Probar la API

Para probar los endpoints de la API, se incluye una **colección de Postman** con solicitudes preconfiguradas para todos los endpoints disponibles.