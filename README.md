# webapp15

# Name of application: 
'Mis resultados'
# Members of the dev team:
|        **Member**        |          **URJC email**          |        **GitHub**       |
|:---|:---|:---|
| *Álvaro Orbaneja Gómez* | a.orbaneja@alumnos.urjc.es   | [aorbaneja](https://github.com/aorbaneja) |
| *Juan Enrique Antón Marazuela*       | je.anton@alumnos.urjc.es    | [juanAntonMarazuela](https://github.com/juanAntonMarazuela) |

# Entitys:
Users and Teams.


The user entity have a field with the favourite team
# Permissions of the users:
Anonymous user: watch statics of the teams, tables and near matches.


Register user: bet on matches and visualize static of her favourite team.


Admin user: Entity team have a field of near matches (near matches 1,2 and 3). Admin user will be responsible for filling these fields when the matches are simulated for bets.


# Images:
## Página de inicio
![Página de inicio](Fase&#32;1/screenshots/inicio.PNG)
## Login
![Login](Fase&#32;1/screenshots/login.PNG)
## Registro
![Registro](Fase&#32;1/screenshots/registro.PNG)
## Apuestas
![Apuestas](screenshots/apostar.PNG)
## Clasificación
![Clasificación](Fase&#32;1/screenshots/clasificacion.PNG)
## Próximos partidos
![Próximos partidos 1](screenshots/partidos.PNG)
## Equipos
![Equipos 1](screenshots/equipos.PNG)
![Equipos 2](screenshots/equipos_2.PNG)
## Equipo
![Equipo 1](Fase&#32;1/screenshots/equipo1.PNG)
![Equipo 2](Fase&#32;1/screenshots/equipo2.PNG)
## Perfil de usuario
![Usuario](screenshots/user.PNG)


# Graphics: 
The leaderboard.
# Complementary technology:
Google Maps for locate stadiums of the teams.
# Advanced algorithm:
Calculate the match bet based on team statistics 

# Development and execution instructions:
In order to run succesfully the app, you will have to follow the next steps:

1. Download the app from repository https://github.com/CodeURJC-DAW-2019-20/webapp15
2. Run a MySQL 8.0.19 server with **user** root and **password** admin (you can change the password on the application.properties if your server password is not admin)
3. Open cmd and use the following up commands in order to create the database for the app:
	1. cd C:\Program Files\MySQL\MySQL Workbench 8.0 CE (or your path of MySql Workbench 8.0
	2. mysql -u root -p
	3. CREATE DATABASE mis_resultados_db;
	4. *OPTIONAL* SHOW DATABASES; (To check if the database was created)
4. Run the app **Mis resultados**

# ERD Diagram
![ERD Diagram](UML/BBDD_ERD.jpg)

 # Classes and templates
 ![Classes and templates](UML/clases_templates.jpg)
 
 # Participation
 
 ## Álvaro
 
 - Implementation: WebSecurity, BBDD (deploy, config and model), User (Controllers, Services, Entity), styles for htmls, embed Google Maps and implementation of its functionality, implementation of admin functionality (add matches),
 implementation of CRUD operation for user role (set favourite team), pagination with AJAX.
 
 - Top 5 commits: 
	- User+Security
	- Controllers+HTTPS+Databaseloader
	- Added Google Maps+Fav Teams (with errors)+fix bug Registro
	- Changed H2 to MYSQL
	- Teams Pageable with AJAX
	
- Top 5 files
	- Session Controller.java
	- UserRepository.java
	- User.java
	- WebSecurityConfig.java
	- UserRepositoryAuthenticationProvider.java


