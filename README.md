[![Build Status](https://travis-ci.org/MISO4203-201610/HomeService-Back.svg?branch=master)](https://travis-ci.org/MISO4203-201610/HomeService-Back)

# Tabla de contenidos
-  [Introducción](#introducción)
-  [API](#api-de-la-aplicación-homeservices)
  - [Entidad Customer](#entidad-customer)
  - [Entidad ServiceRequest](#entidad-servicerequest)
  - [Entidad Category](#entidad-category)
  - [Entidad Contractor](#entidad-contractor)
  - [Entidad Skill](#entidad-skill)
  - [Entidad WorkExperience](#entidad-workexperience)
  - [Entidad Status](#entidad-status)

# API Rest
## Introducción
La comunicación entre cliente y servidor se realiza intercambiando objetos JSON. Para cada entidad se hace un mapeo a JSON, donde cada uno de sus atributos se transforma en una propiedad de un objeto JSON. Todos los servicios se generan en la URL /HomeServices.api/api/. Por defecto, todas las entidades tienen un atributo `id`, con el cual se identifica cada registro:

```javascript
{
    id: '',
    attribute_1: '',
    attribute_2: '',
    ...
    attribute_n: ''
}
```

Cuando se transmite información sobre un registro específico, se realiza enviando un objeto con la estructura mencionada en la sección anterior.
La única excepción se presenta al solicitar al servidor una lista de los registros en la base de datos, que incluye información adicional para manejar paginación de lado del servidor en el header `X-Total-Count` y los registros se envían en el cuerpo del mensaje como un arreglo.

La respuesta del servidor al solicitar una colección presenta el siguiente formato:

```javascript
[{}, {}, {}, {}, {}, {}]
```

## API de la aplicación HomeServices
### Entidad Customer
#### Estructura de objeto Customer
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    lastName: '' /*Tipo String*/,
    document: '' /*Tipo String*/,
    picture: '' /*Tipo String*/
}
```
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/customers|Lista los registros de Customer (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de Customer y el total de registros en la base de datos en el header X-Total-Count
**GET**|/customers/*:customersid*|Obtener los atributos de una instancia de Customer (READ)|**@PathParam customersid**: Identificador del registro||Atributos de la instancia de Customer
**POST**|/customers|Crear una nueva instancia de la entidad Customer (CREATE)||Atributos de la instancia de Customer a crear|Instancia de Customer creada, incluyendo su nuevo ID
**PUT**|/customers/*:customersid*|Actualiza una instancia de la entidad Customer (UPDATE)|**@PathParam customersid**: Identificador del registro|Objeto JSON de Customer|Instancia de Customer actualizada
**DELETE**|/customers/*:customersid*|Borra instancia de Customer en el servidor (DELETE)|**@PathParam customersid**: Identificador del registro||
**GET**|customers/*:customersid*/serviceRequests|Listar registros de serviceRequests (ServiceRequest) asociados a Customer|**@PathParam customersid**: Identificador de instancia de Customer||Colección de objetos JSON de serviceRequests(ServiceRequest)
**GET**|customers/*:customersid*/serviceRequests/*:serviceRequestsid*|Obtener un registro de serviceRequests (ServiceRequest) asociado a Customer|**@PathParam customersid**: Identificador de instancia de Customer<br><br>**@PathParam serviceRequestsid**: Identificador de instancia de ServiceRequest||Lista de registros de serviceRequests(ServiceRequest)
**POST**|customers/*:customersid*/serviceRequests/*:serviceRequestsid*|Asocia una instancia de ServiceRequest a una de Customer|**@PathParam customersid**: Identificador de instancia de Customer<br><br>**@PathParam serviceRequestsid**: Identificador de instancia de ServiceRequest|Registro de serviceRequests(ServiceRequest) para asociar a Customer|Instancia de serviceRequests(ServiceRequest) asociada a instancia de Customer
**PUT**|customers/*:customersid*/serviceRequests|Actualización de instancias de serviceRequests (ServiceRequest) asociadas a Customer|**@PathParam customersid**: Identificador de instancia de Customer|Colección de instancias de serviceRequests(ServiceRequest) a actualizar|Colección de instancias de serviceRequests(ServiceRequest) actualizados
**DELETE**|customers/*:customersid*/serviceRequests/*:serviceRequestsid*|Remueve asociación de instancias de serviceRequests (ServiceRequest) a Customer|**@PathParam customersid**: Identificador de instancia de Customer<br><br>**@PathParam serviceRequestsid**: Identificador de instancia de ServiceRequest|Colección de atributo `id` de serviceRequests(ServiceRequest) a eliminar|

[Volver arriba](#tabla-de-contenidos)

### Entidad ServiceRequest
#### Estructura de objeto ServiceRequest
```javascript
{
    customer: '' /*Objeto que representa instancia de Customer*/,
    creationDate: '' /*Tipo Date*/,
    status: '' /*Objeto que representa instancia de Status*/,
    recommendedTime: '' /*Tipo String*/,
    id: '' /*Tipo Long*/,
    category: '' /*Objeto que representa instancia de Category*/,
    dueDate: '' /*Tipo Date*/,
    name: '' /*Tipo String*/,
    price: '' /*Tipo Integer*/
}
```
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/serviceRequests|Lista los registros de ServiceRequest (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de ServiceRequest y el total de registros en la base de datos en el header X-Total-Count
**GET**|/serviceRequests/*:serviceRequestsid*|Obtener los atributos de una instancia de ServiceRequest (READ)|**@PathParam serviceRequestsid**: Identificador del registro||Atributos de la instancia de ServiceRequest
**POST**|/serviceRequests|Crear una nueva instancia de la entidad ServiceRequest (CREATE)||Atributos de la instancia de ServiceRequest a crear|Instancia de ServiceRequest creada, incluyendo su nuevo ID
**PUT**|/serviceRequests/*:serviceRequestsid*|Actualiza una instancia de la entidad ServiceRequest (UPDATE)|**@PathParam serviceRequestsid**: Identificador del registro|Objeto JSON de ServiceRequest|Instancia de ServiceRequest actualizada
**DELETE**|/serviceRequests/*:serviceRequestsid*|Borra instancia de ServiceRequest en el servidor (DELETE)|**@PathParam serviceRequestsid**: Identificador del registro||
**GET**|serviceRequests/*:serviceRequestsid*/expectedskills|Listar registros de expectedskills (Skill) asociados a ServiceRequest|**@PathParam serviceRequestsid**: Identificador de instancia de ServiceRequest||Colección de objetos JSON de expectedskills(Skill)
**GET**|serviceRequests/*:serviceRequestsid*/expectedskills/*:expectedskillsid*|Obtener un registro de expectedskills (Skill) asociado a ServiceRequest|**@PathParam serviceRequestsid**: Identificador de instancia de ServiceRequest<br><br>**@PathParam expectedskillsid**: Identificador de instancia de Skill||Lista de registros de expectedskills(Skill)
**POST**|serviceRequests/*:serviceRequestsid*/expectedskills/*:expectedskillsid*|Asocia una instancia de Skill a una de ServiceRequest|**@PathParam serviceRequestsid**: Identificador de instancia de ServiceRequest<br><br>**@PathParam expectedskillsid**: Identificador de instancia de Skill|Registro de expectedskills(Skill) para asociar a ServiceRequest|Instancia de expectedskills(Skill) asociada a instancia de ServiceRequest
**PUT**|serviceRequests/*:serviceRequestsid*/expectedskills|Actualización de instancias de expectedskills (Skill) asociadas a ServiceRequest|**@PathParam serviceRequestsid**: Identificador de instancia de ServiceRequest|Colección de instancias de expectedskills(Skill) a actualizar|Colección de instancias de expectedskills(Skill) actualizados
**DELETE**|serviceRequests/*:serviceRequestsid*/expectedskills/*:expectedskillsid*|Remueve asociación de instancias de expectedskills (Skill) a ServiceRequest|**@PathParam serviceRequestsid**: Identificador de instancia de ServiceRequest<br><br>**@PathParam expectedskillsid**: Identificador de instancia de Skill|Colección de atributo `id` de expectedskills(Skill) a eliminar|

[Volver arriba](#tabla-de-contenidos)

### Entidad Category
#### Estructura de objeto Category
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    description: '' /*Tipo String*/
}
```
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/categorys|Lista los registros de Category (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de Category y el total de registros en la base de datos en el header X-Total-Count
**GET**|/categorys/*:categorysid*|Obtener los atributos de una instancia de Category (READ)|**@PathParam categorysid**: Identificador del registro||Atributos de la instancia de Category
**POST**|/categorys|Crear una nueva instancia de la entidad Category (CREATE)||Atributos de la instancia de Category a crear|Instancia de Category creada, incluyendo su nuevo ID
**PUT**|/categorys/*:categorysid*|Actualiza una instancia de la entidad Category (UPDATE)|**@PathParam categorysid**: Identificador del registro|Objeto JSON de Category|Instancia de Category actualizada
**DELETE**|/categorys/*:categorysid*|Borra instancia de Category en el servidor (DELETE)|**@PathParam categorysid**: Identificador del registro||

[Volver arriba](#tabla-de-contenidos)

### Entidad Contractor
#### Estructura de objeto Contractor
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    lastName: '' /*Tipo String*/,
    document: '' /*Tipo String*/,
    picture: '' /*Tipo String*/,
    workExperiences: [] /*Colección de registros de WorkExperience*/
}
```
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/contractors|Lista los registros de Contractor (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de Contractor y el total de registros en la base de datos en el header X-Total-Count
**GET**|/contractors/*:contractorsid*|Obtener los atributos de una instancia de Contractor (READ)|**@PathParam contractorsid**: Identificador del registro||Atributos de la instancia de Contractor
**POST**|/contractors|Crear una nueva instancia de la entidad Contractor (CREATE)||Atributos de la instancia de Contractor a crear|Instancia de Contractor creada, incluyendo su nuevo ID
**PUT**|/contractors/*:contractorsid*|Actualiza una instancia de la entidad Contractor (UPDATE)|**@PathParam contractorsid**: Identificador del registro|Objeto JSON de Contractor|Instancia de Contractor actualizada
**DELETE**|/contractors/*:contractorsid*|Borra instancia de Contractor en el servidor (DELETE)|**@PathParam contractorsid**: Identificador del registro||
**GET**|contractors/*:contractorsid*/skills|Listar registros de skills (Skill) asociados a Contractor|**@PathParam contractorsid**: Identificador de instancia de Contractor||Colección de objetos JSON de skills(Skill)
**GET**|contractors/*:contractorsid*/skills/*:skillsid*|Obtener un registro de skills (Skill) asociado a Contractor|**@PathParam contractorsid**: Identificador de instancia de Contractor<br><br>**@PathParam skillsid**: Identificador de instancia de Skill||Lista de registros de skills(Skill)
**POST**|contractors/*:contractorsid*/skills/*:skillsid*|Asocia una instancia de Skill a una de Contractor|**@PathParam contractorsid**: Identificador de instancia de Contractor<br><br>**@PathParam skillsid**: Identificador de instancia de Skill|Registro de skills(Skill) para asociar a Contractor|Instancia de skills(Skill) asociada a instancia de Contractor
**PUT**|contractors/*:contractorsid*/skills|Actualización de instancias de skills (Skill) asociadas a Contractor|**@PathParam contractorsid**: Identificador de instancia de Contractor|Colección de instancias de skills(Skill) a actualizar|Colección de instancias de skills(Skill) actualizados
**DELETE**|contractors/*:contractorsid*/skills/*:skillsid*|Remueve asociación de instancias de skills (Skill) a Contractor|**@PathParam contractorsid**: Identificador de instancia de Contractor<br><br>**@PathParam skillsid**: Identificador de instancia de Skill|Colección de atributo `id` de skills(Skill) a eliminar|

[Volver arriba](#tabla-de-contenidos)

### Entidad Skill
#### Estructura de objeto Skill
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    description: '' /*Tipo String*/
}
```
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/skills|Lista los registros de Skill (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de Skill y el total de registros en la base de datos en el header X-Total-Count
**GET**|/skills/*:skillsid*|Obtener los atributos de una instancia de Skill (READ)|**@PathParam skillsid**: Identificador del registro||Atributos de la instancia de Skill
**POST**|/skills|Crear una nueva instancia de la entidad Skill (CREATE)||Atributos de la instancia de Skill a crear|Instancia de Skill creada, incluyendo su nuevo ID
**PUT**|/skills/*:skillsid*|Actualiza una instancia de la entidad Skill (UPDATE)|**@PathParam skillsid**: Identificador del registro|Objeto JSON de Skill|Instancia de Skill actualizada
**DELETE**|/skills/*:skillsid*|Borra instancia de Skill en el servidor (DELETE)|**@PathParam skillsid**: Identificador del registro||

[Volver arriba](#tabla-de-contenidos)

### Entidad WorkExperience
#### Estructura de objeto WorkExperience
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    description: '' /*Tipo String*/
}
```
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/workExperiences|Lista los registros de WorkExperience (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de WorkExperience y el total de registros en la base de datos en el header X-Total-Count
**GET**|/workExperiences/*:workExperiencesid*|Obtener los atributos de una instancia de WorkExperience (READ)|**@PathParam workExperiencesid**: Identificador del registro||Atributos de la instancia de WorkExperience
**POST**|/workExperiences|Crear una nueva instancia de la entidad WorkExperience (CREATE)||Atributos de la instancia de WorkExperience a crear|Instancia de WorkExperience creada, incluyendo su nuevo ID
**PUT**|/workExperiences/*:workExperiencesid*|Actualiza una instancia de la entidad WorkExperience (UPDATE)|**@PathParam workExperiencesid**: Identificador del registro|Objeto JSON de WorkExperience|Instancia de WorkExperience actualizada
**DELETE**|/workExperiences/*:workExperiencesid*|Borra instancia de WorkExperience en el servidor (DELETE)|**@PathParam workExperiencesid**: Identificador del registro||

[Volver arriba](#tabla-de-contenidos)

### Entidad Status
#### Estructura de objeto Status
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/
}
```
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/statuss|Lista los registros de Status (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de Status y el total de registros en la base de datos en el header X-Total-Count
**GET**|/statuss/*:statussid*|Obtener los atributos de una instancia de Status (READ)|**@PathParam statussid**: Identificador del registro||Atributos de la instancia de Status
**POST**|/statuss|Crear una nueva instancia de la entidad Status (CREATE)||Atributos de la instancia de Status a crear|Instancia de Status creada, incluyendo su nuevo ID
**PUT**|/statuss/*:statussid*|Actualiza una instancia de la entidad Status (UPDATE)|**@PathParam statussid**: Identificador del registro|Objeto JSON de Status|Instancia de Status actualizada
**DELETE**|/statuss/*:statussid*|Borra instancia de Status en el servidor (DELETE)|**@PathParam statussid**: Identificador del registro||

[Volver arriba](#tabla-de-contenidos)

