# Sistema de Consultorio Médico

Este proyecto es un sistema de gestión de citas médicas para un consultorio. Permite a los usuarios agendar citas con doctores, ver las citas programadas, agregar nuevos doctores y consultorios, entre otras funcionalidades.

## Funcionalidades

- **Agenda de citas:** Los pacientes pueden agendar citas con los doctores disponibles en un consultorio específico.
- **Gestión de doctores:** Los administradores pueden agregar, listar y editar los doctores.
- **Gestión de consultorios:** Los administradores pueden agregar y gestionar los consultorios disponibles.
- **Citas programadas:** Los usuarios pueden ver las citas programadas con los doctores y consultorios.

## Requisitos

- Java 17 o superior
- Spring Boot
- MySQL o cualquier base de datos compatible

## Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/consultorio-medico.git
   
2. Ingresa al directorio del proyecto:

   ```bash
Copiar código
cd consultorio-medico

3. Importa el proyecto en tu IDE preferido (Eclipse, IntelliJ, etc.).

4. Asegúrate de tener una base de datos MySQL configurada. Crea una base de datos llamada consultorio_db o edita las configuraciones de conexión en el archivo application.properties o application.yml.

   ```properties
Copiar código
spring.datasource.url=jdbc:mysql://localhost:3306/consultorio_db
spring.datasource.username=usuario
spring.datasource.password=contraseña
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
5. Ejecuta el proyecto como una aplicación Spring Boot. El servidor arrancará en http://localhost:8080.

## Uso
1. Agendar cita: Los pacientes pueden agendar una cita eligiendo un doctor, un consultorio y el horario de consulta.
2. Ver citas: Los administradores pueden ver todas las citas programadas con sus respectivos detalles.
3. Agregar doctores: Los administradores pueden agregar nuevos doctores al sistema desde la interfaz de administración.
4. Agregar consultorios: Los administradores pueden agregar nuevos consultorios a la base de datos.
## Estructura del Proyecto
src/main/java/com/hospital/consultorio: Contiene el código Java de la aplicación.

Modelos: Doctor, Consultorio, Cita.
Controladores: Gestión de citas, doctores y consultorios.
Repositorios: Interfaces para interactuar con la base de datos (JPA).
src/main/resources/templates: Plantillas Thymeleaf para las vistas web.

src/main/resources/static: Archivos estáticos como CSS y JavaScript.
