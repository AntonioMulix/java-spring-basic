
# 📄 Documento de Despliegue  
## Aplicación Spring Boot – JAR ejecutable (sin Tomcat externo)

---

## 1. Objetivo

Definir el procedimiento estándar para desplegar una **aplicación Spring Boot (API REST)** empaquetada como **JAR ejecutable**, utilizando **variables de entorno** para la configuración por ambiente (**local, des, pre, prod**), sin recompilar la aplicación ni utilizar servidores de aplicaciones como **WildFly** o **Tomcat externo**.

---

## 2. Arquitectura de Despliegue

- Tipo de aplicación: **Spring Boot – API REST**
- Empaquetado: **JAR ejecutable**
- Servidor HTTP: **Tomcat embebido (incluido en Spring Boot)**
- Configuración por ambiente: **Variables de entorno del sistema operativo**
- Base de datos: **PostgreSQL**
- ORM: **Spring Data JPA / Hibernate**

---

## 3. Principios del Modelo

- ✅ Un solo artefacto (JAR) para todos los ambientes
- ✅ Cero configuración dentro del servidor
- ✅ No se recompila por ambiente
- ✅ Las credenciales no viajan en el artefacto
- ✅ Separación clara entre desarrollo y despliegue
- ✅ Compatible con CI/CD y auditorías

---

## 4. Requisitos del Servidor

### 4.1 Software requerido
- Sistema operativo Linux
- Java JDK 17 o superior
- Acceso para ejecutar procesos Java
- Puerto disponible (por defecto **8080**)

> ❌ No se requiere Tomcat  

---

## 5. Preparación del Proyecto (Equipo de Desarrollo)

### 5.1 Empaquetado

En el archivo `pom.xml`:

```xml
<packaging>jar</packaging>
```

Spring Boot incluye Tomcat embebido por defecto, no es necesario agregar dependencias adicionales.

---

### 5.2 Clase principal

```java
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

---

### 5.3 Compilación

```bash
mvn clean package
```

Artefacto generado:

```text
target/demo.jar
```

---

## 6. Configuración de la Aplicación

### 6.1 Archivo `application.properties` (base)

```properties
spring.application.name=demo

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

server.port=8080
```

> ⚠️ No se definen valores específicos por ambiente dentro del JAR.

---

## 7. Configuración por Ambiente (Equipo de Despliegue)

Cada servidor define sus variables de entorno sin modificar la aplicación.

### 7.1 Ejemplo – Producción

```bash
export SPRING_PROFILES_ACTIVE=prod

export DB_URL=jdbc:postgresql://db-prod:5432/gestion_clientes
export DB_USER=demo_user
export DB_PASSWORD=********
```

### 7.2 Ejemplo – Pre-producción

```bash
export SPRING_PROFILES_ACTIVE=pre

export DB_URL=jdbc:postgresql://db-pre:5432/gestion_clientes
export DB_USER=demo_user_pre
export DB_PASSWORD=********
```

---

## 8. Arranque de la Aplicación

### 8.1 Ejecución manual

```bash
java -jar demo.jar
```

Spring Boot:
- Detecta las variables de entorno
- Aplica el perfil activo
- Inicia el servidor HTTP embebido

---

### 8.2 Ejecución en segundo plano

```bash
nohup java -jar demo.jar > app.log 2>&1 &
```

---

## 9. Ejecución como Servicio (Recomendado)

### 9.1 Servicio systemd

Archivo:

```text
/etc/systemd/system/demo.service
```

Contenido:

```ini
[Unit]
Description=Demo API - Spring Boot
After=network.target

[Service]
User=demo
WorkingDirectory=/opt/demo
ExecStart=/usr/bin/java -jar demo.jar
SuccessExitStatus=143
Restart=always

Environment=SPRING_PROFILES_ACTIVE=prod
Environment=DB_URL=jdbc:postgresql://db-prod:5432/gestion_clientes
Environment=DB_USER=demo_user
Environment=DB_PASSWORD=********

[Install]
WantedBy=multi-user.target
```

---

### 9.2 Comandos de administración

```bash
systemctl daemon-reload
systemctl enable demo
systemctl start demo
systemctl status demo
```

---

## 10. Logs

Por defecto, los logs se envían a `stdout` y son gestionados por `systemd`.

```bash
journalctl -u demo -f
```

(Opcionalmente se puede configurar Logback para salida a archivo.)

---

## 11. Acceso a la Aplicación

```text
http://<host>:8080/
```

---

## 12. Manejo de Ambientes

| Ambiente | Configuración |
|--------|---------------|
| local  | Variables en terminal o IDE |
| des    | Variables del sistema |
| pre    | Variables del sistema |
| prod   | Variables del sistema |

➡️ **El JAR es exactamente el mismo en todos los ambientes**

---

## 13. Comparación con WildFly

| WildFly | Spring Boot JAR |
|-------|----------------|
| Servidor de aplicaciones pesado | Proceso Java ligero |
| Configuración JNDI | Variables de entorno |
| XML complejo | Properties simples |
| Reempaquetar por ambiente | Un solo artefacto |
| Administración compleja | systemd / Docker |

---

## 14. Ventajas Clave

- 🔥 Arranque rápido
- 🔥 Menor complejidad operativa
- 🔥 Fácil rollback
- 🔥 Ideal para APIs REST
- 🔥 Base para Docker y Kubernetes

---
