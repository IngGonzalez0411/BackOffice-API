# BackOffice API

Este proyecto es un **reto técnico** para implementar un **API RESTful con Spring Boot** que gestione usuarios, categorías y productos.  
El sistema implementa autenticación con JWT, control de permisos por roles (ADMIN / USER), logging de accesos en archivo plano y persistencia en **SQL Server 2019**.

---

## 📋 Pre-requisitos

Antes de instalar y ejecutar la aplicación, asegúrate de tener lo siguiente:

1. **Java JDK u OpenJDK 17** instalado y configurado en tu sistema.  
2. **Maven** en su versión más reciente instalado.  
3. **MS SQL Server 2019** instalado y configurado.  

---

## 📂 Contenido del repositorio

- **`script_BackOfficeDB.sql`**  
  Script SQL que crea la base de datos `BackOfficeDB`, las tablas requeridas y carga datos de prueba (usuarios, categorías y productos).

- **`BackOffice API.json`**  
  Colección de Postman lista para importar y probar los endpoints (`Auth`, `Users`, `Categories`, `Products`).

- **Código fuente del API**  
  Implementado en Java con Spring Boot y JPA.

- **Documentación (Swagger UI)**  
  El aplicativo cuenta con documentación interactiva Swagger UI para explorar y probar los endpoints. Una vez que la aplicación esté corriendo, puedes acceder a ella en la siguiente URL: http://<host>:<puerto>/swagger-ui.html

---

## 🚀 Instrucciones de instalación

### 1. Clonar el repositorio
```bash
git clone https://github.com/IngGonzalez0411/BackOffice-API.git
cd BackOffice-API
```

### 2. Crear la base de datos
Ejecutar el script `script_BackOfficeDB.sql` en tu SQL Server 2019:

```sql
:run script_BackOfficeDB.sql
```

Esto creará la base de datos, tablas y datos de prueba.

### 3. Configuración de variables de entorno
Antes de ejecutar la aplicación, debes configurar las siguientes variables en tu sistema:

```bash
# URL de conexión a la base de datos SQL Server
export DB_URL="jdbc:sqlserver://localhost\SQLEXPRESS;databaseName=BackOfficeDB;encrypt=false;trustServerCertificate=true"

# Usuario y contraseña de la base de datos
export DB_USERNAME="sa"
export DB_PASSWORD="PasswordFuerte"

# Ruta del archivo de logs (ejemplo: /var/log/backoffice/requests.log)
export ENV_VAR_LOGPATH="/ruta/donde/guardar/requests.log"
```

> En Windows (PowerShell):
```powershell
setx DB_URL "jdbc:sqlserver://localhost\SQLEXPRESS;databaseName=BackOfficeDB;encrypt=false;trustServerCertificate=true"
setx DB_USERNAME "sa"
setx DB_PASSWORD "PasswordFuerte"
setx ENV_VAR_LOGPATH "C:\logs\requests.log"
```

---

## ▶️ Ejecución de la aplicación

Tienes dos formas de correr el proyecto:

### Opción 1: Desde un IDE
Abrir el proyecto en un IDE compatible con Spring Boot (ej. **Spring Tool Suite** o **IntelliJ IDEA**) y ejecutar la clase principal:

```
com.mposglobal.backoffice.BackofficeApplication
```

### Opción 2: Desde la línea de comandos
Ejecutar dentro del directorio del proyecto:

```bash
mvn spring-boot:run
```

---

## 🧪 Pruebas con Postman

1. Importar la colección **`BackOffice API.json`** en Postman.  
2. Ejecutar el request **Auth - Login** con las credenciales de prueba (ejemplo: `admin/admin123`).  
3. Copiar el token JWT de la respuesta en la variable `{{token}}` de la colección.  
4. Probar los endpoints de **Users**, **Categories** y **Products**.

---

## 📌 Notas importantes

- El API no elimina registros físicamente. Se implementa **soft delete** (estado `DESACTIVADO`).  
- Las contraseñas están cifradas con **BCrypt**.  
- Los logs de accesos se almacenan en el archivo indicado por la variable `ENV_VAR_LOGPATH`.  
- Tiempo de sesión configurado: **10 minutos**.  

---
