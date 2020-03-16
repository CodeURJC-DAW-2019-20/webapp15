# [API Documentation]
## About our API

Clone the web simulation: Get information of teams, user, matches and tables of points.
Futhermore UserLogin and UserLogout. 
All API queries have been preceded by /api
The resource API has GET, POST, PUT methods. https: // localhost: 8443 followed by the containt request URL.
Implementation in SesionRestController.java

## How to use our API
1. Download [Postman](https://www.getpostman.com/).
2. You only can send GET, POST, PUT and DELETE requests.

##API request

###getTeams
Allows a anonymous user to see info of all teams
* ##### URL:

	< /equipos/ >

* ##### Method:

	`GET`
	
* ##### Success Response:
  
  	```
		    {
        "id": 1,
        "name": "Real Madrid",
        "winners": 14,
        "lossers": 2,
        "tied": 8,
        "points": 53,
        "goalsInFavor": 46,
        "goalsAgainst": 17,
        "position": 1,
        "league": "La liga",
        "direction": "Santiago Bernabeu",
        "matches": [
            "Osasuna",
            "Barcelona",
            "Atletico de Madrid"
        ],
        "nameImgTemplate": "Plantilla_RealMadrid",
        "nameImgShield": "escudo_RealMadrid"
    },
    {
        "id": 2,
        "name": "Barcelona",
        "winners": 16,
        "lossers": 4,
        "tied": 4,
        "points": 52,
        "goalsInFavor": 62,
        "goalsAgainst": 29,
        "position": 2,
        "league": "La liga",
        "direction": "Camp Nou",
        "matches": [
            "Espanyol",
            "Real Madrid",
            "Betis"
        ],
        "nameImgTemplate": "Plantilla_Barcelona",
        "nameImgShield": "escudo_Barcelona"
    },
    {
        "id": 3,
        "name": "Atletico de Madrid",
        "winners": 10,
        "lossers": 4,
        "tied": 10,
        "points": 40,
        "goalsInFavor": 25,
        "goalsAgainst": 17,
        "position": 4,
        "league": "La liga",
        "direction": "Wanda Metropolitano",
        "matches": [
            "Valladolid",
            "Espanyol",
            "Real Madrid"
        ],
        "nameImgTemplate": "Plantilla_AtleticodeMadrid",
        "nameImgShield": "escudo_AtleticodeMadrid"
    },
    {
        "id": 4,
        "name": "Real Sociedad",
        "winners": 12,
        "lossers": 8,
        "tied": 4,
        "points": 40,
        "goalsInFavor": 42,
        "goalsAgainst": 31,
        "position": 3,
        "league": "La liga",
        "direction": "Reale Arena",
        "matches": [
            "Betis",
            "Osasuna",
            "Valladolid"
        ],
        "nameImgTemplate": "Plantilla_RealSociedad",
        "nameImgShield": "escudo_RealSociedad"
    },
    {
        "id": 5,
        "name": "Osasuna",
        "winners": 9,
        "lossers": 5,
        "tied": 10,
        "points": 37,
        "goalsInFavor": 28,
        "goalsAgainst": 18,
        "position": 5,
        "league": "La liga",
        "direction": "Estadio El Sadar",
        "matches": [
            "Real Madrid",
            "Real Sociedad",
            "Espanyol"
        ],
        "nameImgTemplate": "Plantilla_Osasuna",
        "nameImgShield": "escudo_Osasuna"
    },
    {
        "id": 6,
        "name": "Espanyol",
        "winners": 8,
        "lossers": 6,
        "tied": 10,
        "points": 34,
        "goalsInFavor": 30,
        "goalsAgainst": 10,
        "position": 6,
        "league": "La liga",
        "direction": "RCDE Stadium",
        "matches": [
            "Barcelona",
            "Atletico de Madrid",
            "Osasuna"
        ],
        "nameImgTemplate": "plantilla_Espanyol",
        "nameImgShield": "escudo_Espanyol"
    },
    {
        "id": 7,
        "name": "Valladolid",
        "winners": 11,
        "lossers": 13,
        "tied": 0,
        "points": 33,
        "goalsInFavor": 45,
        "goalsAgainst": 30,
        "position": 7,
        "league": "La liga",
        "direction": "Estadio José Zorrilla",
        "matches": [
            "Atletico de Madrid",
            "Betis",
            "Real Sociedad"
        ],
        "nameImgTemplate": "Plantilla_Valladolid",
        "nameImgShield": "escudo_Valladolid"
    },
    {
        "id": 8,
        "name": "Betis",
        "winners": 7,
        "lossers": 7,
        "tied": 10,
        "points": 31,
        "goalsInFavor": 30,
        "goalsAgainst": 20,
        "position": 8,
        "league": "La liga",
        "direction": "Alava",
        "matches": [
            "Real Sociedad",
            "Valladolid",
            "Barcelona"
        ],
        "nameImgTemplate": "plantilla_Betis",
        "nameImgShield": "escudo_Betis"
    },
    {
        "id": 9,
        "name": "Getafe",
        "winners": 7,
        "lossers": 7,
        "tied": 10,
        "points": 30,
        "goalsInFavor": 30,
        "goalsAgainst": 20,
        "position": 9,
        "league": "La liga",
        "direction": "Coliseum Alfonso Perez",
        "matches": [
            "Real Sociedad",
            "Valladolid",
            "Barcelona"
        ],
        "nameImgTemplate": "Plantilla_RealSociedad",
        "nameImgShield": "escudo_getafe"
    },
    {
        "id": 10,
        "name": "Villareal",
        "winners": 7,
        "lossers": 7,
        "tied": 10,
        "points": 29,
        "goalsInFavor": 30,
        "goalsAgainst": 20,
        "position": 10,
        "league": "La liga",
        "direction": "Coliseum Alfonso Perez",
        "matches": [
            "Real Sociedad",
            "Valladolid",
            "Barcelona"
        ],
        "nameImgTemplate": "Plantilla_RealSociedad",
        "nameImgShield": "escudo_villareal"
    },
    {
        "id": 11,
        "name": "Mallorca",
        "winners": 7,
        "lossers": 7,
        "tied": 10,
        "points": 28,
        "goalsInFavor": 30,
        "goalsAgainst": 20,
        "position": 11,
        "league": "La liga",
        "direction": "Coliseum Alfonso Perez",
        "matches": [
            "Real Sociedad",
            "Valladolid",
            "Barcelona"
        ],
        "nameImgTemplate": "Plantilla_RealSociedad",
        "nameImgShield": "escudo_mallorca"
    },
    {
        "id": 12,
        "name": "Sevilla",
        "winners": 7,
        "lossers": 7,
        "tied": 10,
        "points": 27,
        "goalsInFavor": 30,
        "goalsAgainst": 20,
        "position": 12,
        "league": "La liga",
        "direction": "Coliseum Alfonso Perez",
        "matches": [
            "Real Sociedad",
            "Valladolid",
            "Barcelona"
        ],
        "nameImgTemplate": "Plantilla_RealSociedad",
        "nameImgShield": "escudo_sevilla"
    }
	```


* ##### Error response:

	**Code**: NOT.FOUND
	
	###getTeamsById
Allows a anonymous user to see info of a team filter by id
* ##### URL:

	< /equipos/{id} >

* ##### Method:

	`GET`
	
* ##### Success Response:
  
  	```
		{
    "id": 1,
    "name": "Real Madrid",
    "winners": 14,
    "lossers": 2,
    "tied": 8,
    "points": 53,
    "goalsInFavor": 46,
    "goalsAgainst": 17,
    "position": 1,
    "league": "La liga",
    "direction": "Santiago Bernabeu",
    "matches": [
        "Osasuna",
        "Barcelona",
        "Atletico de Madrid"
    ],
    "nameImgTemplate": "Plantilla_RealMadrid",
    "nameImgShield": "escudo_RealMadrid"
	}
	```


* ##### Error response:

	**Code**: NOT.FOUND
	###getTeamsByName
Allows a anonymous user to see info of a teams filter by name
* ##### URL:

	< equipos/name/{name}>

* ##### Method:

	`GET`
	
* ##### Success Response:
  
  	```
		{
			"id": 2,
			"name": "Barcelona",
			"winners": 16,
			"lossers": 4,
			"tied": 4,
			"points": 52,
			"goalsInFavor": 62,
			"goalsAgainst": 29,
			"position": 2,
			"league": "La liga",
			"direction": "Camp Nou",
			"matches": [
				"Espanyol",
				"Real Madrid",
				"Betis"
			],
			"nameImgTemplate": "Plantilla_Barcelona",
			"nameImgShield": "escudo_Barcelona"	
		}
	```


* ##### Error response:

	**Code**: NOT.FOUND

	###/equipos/first
Allows a anonymous user to see info of a first team in the table
* ##### URL:

	< /equipos/first >

* ##### Method:

	`GET`
	
* ##### Success Response:
  
  	```
	{
		"id": 1,
		"name": "Real Madrid",
		"winners": 14,
		"lossers": 2,
		"tied": 8,
		"points": 53,
		"goalsInFavor": 46,
		"goalsAgainst": 17,
		"position": 1,
		"league": "La liga",
		"direction": "Santiago Bernabeu",
		"matches": [
			"Osasuna",
			"Barcelona",
			"Atletico de Madrid"
		],
		"nameImgTemplate": "Plantilla_RealMadrid",
		"nameImgShield": "escudo_RealMadrid"
	}
		
	```


* ##### Error response:

	**Code**: NOT.FOUND

	###getTeamMoreGoalInFavor
Allows a anonymous user to see info of the team with more goals in favor
* ##### URL:

	< /equipos/goalsF >

* ##### Method:

	`GET`
	
* ##### Success Response:
  
  	```
		{
			"id": 2,
			"name": "Barcelona",
			"winners": 16,
			"lossers": 4,
			"tied": 4,
			"points": 52,
			"goalsInFavor": 62,
			"goalsAgainst": 29,
			"position": 2,
			"league": "La liga",
			"direction": "Camp Nou",
			"matches": [
				"Espanyol",
				"Real Madrid",
				"Betis"
			],
			"nameImgTemplate": "Plantilla_Barcelona",
			"nameImgShield": "escudo_Barcelona"
		}
	```


* ##### Error response:

	**Code**: NOT.FOUND

	###getTeamLessGoalAgainst
Allows a anonymous user to see info of the team with less goals against
* ##### URL:

	</equipos/goalsA >

* ##### Method:

	`GET`
	
* ##### Success Response:
  
  	```
		{
			"id": 6,
			"name": "Espanyol",
			"winners": 8,
			"lossers": 6,
			"tied": 10,
			"points": 34,
			"goalsInFavor": 30,
			"goalsAgainst": 10,
			"position": 6,
			"league": "La liga",
			"direction": "RCDE Stadium",
			"matches": [
				"Barcelona",
				"Atletico de Madrid",
				"Osasuna"
			],
			"nameImgTemplate": "plantilla_Espanyol",
			"nameImgShield": "escudo_Espanyol"
		}
	```


* ##### Error response:

	**Code**: NOT.FOUND

	###getTeamByPosition
Allows a anonymous user to see info of specific team in a position
* ##### URL:

	< /equipos/position/{p} >

* ##### Method:

	`GET`
	
* ##### Success Response:
  
  	```
	{
		"id": 12,
		"name": "Sevilla",
		"winners": 7,
		"lossers": 7,
		"tied": 10,
		"points": 27,
		"goalsInFavor": 30,
		"goalsAgainst": 20,
		"position": 12,
		"league": "La liga",
		"direction": "Coliseum Alfonso Perez",
		"matches": [
			"Real Sociedad",
			"Valladolid",
			"Barcelona"
		],
		"nameImgTemplate": "Plantilla_RealSociedad",
		"nameImgShield": "escudo_sevilla"
	}		
	```


* ##### Error response:

	**Code**: NOT.FOUND

	###getUser
Allows see if we have user logged
* ##### URL:

	< /equipos/user/ >

* ##### Method:

	`GET`
	
* ##### Success Response:
  
  	```
		{
			"id": 2,
			"name": "Alvaro",
			"surname": "Orbaneja",
			"email": "alvaro@gmail.com",
			"passwordHash": "$2a$10$X7YGaSa3EVa9wvJbnqH.F.3wFPnZ.XESkOqn5X2gI4WUK/oRRCHE2",
			"fav_team": "Real Madrid",
			"acc_balance": 1000,
			"roles": [
				"ROLE_USER"
			]
		}
	```


* ##### Error response:

	**Code**: NOT.FOUND

	###logIn
Allows a anonymous user login in the page
* ##### URL:

	< /logIn >

* ##### Method:

	`GET`
	
* ##### Success Response:
  
  	```
	{
		"id": 2,
		"name": "Alvaro",
		"surname": "Orbaneja",
		"email": "alvaro@gmail.com",
		"passwordHash": "$2a$10$X7YGaSa3EVa9wvJbnqH.F.3wFPnZ.XESkOqn5X2gI4WUK/oRRCHE2",
		"fav_team": "Real Madrid",
		"acc_balance": 1000,
		"roles": [
			"ROLE_USER"
		]
	}		
	```


* ##### Error response:

	**Code**: UNAUTHORIZED

	###logOut
Allows a user logout of the page
* ##### URL:

	< /logOut/ >

* ##### Method:

	`GET`
	
* ##### Success Response:
  
  	```
		true
	```


* ##### Error response:

	**Code**:UNAUTHORIZED
	
	
	###showTable
Allows a anonymous user to see info table
* ##### URL:

	< /table/{league} >

* ##### Method:

	`GET`
	
* ##### Success Response:
  
  	```
		[
    {
        "id": 1,
        "name": "Real Madrid",
        "winners": 14,
        "lossers": 2,
        "tied": 8,
        "points": 53,
        "goalsInFavor": 46,
        "goalsAgainst": 17,
        "position": 1,
        "league": "La liga",
        "direction": "Santiago Bernabeu",
        "matches": [
            "Osasuna",
            "Barcelona",
            "Atletico de Madrid"
        ],
        "nameImgTemplate": "Plantilla_RealMadrid",
        "nameImgShield": "escudo_RealMadrid"
    },
    {
        "id": 2,
        "name": "Barcelona",
        "winners": 16,
        "lossers": 4,
        "tied": 4,
        "points": 52,
        "goalsInFavor": 62,
        "goalsAgainst": 29,
        "position": 2,
        "league": "La liga",
        "direction": "Camp Nou",
        "matches": [
            "Espanyol",
            "Real Madrid",
            "Betis"
        ],
        "nameImgTemplate": "Plantilla_Barcelona",
        "nameImgShield": "escudo_Barcelona"
    },
    {
        "id": 3,
        "name": "Atletico de Madrid",
        "winners": 10,
        "lossers": 4,
        "tied": 10,
        "points": 40,
        "goalsInFavor": 25,
        "goalsAgainst": 17,
        "position": 4,
        "league": "La liga",
        "direction": "Wanda Metropolitano",
        "matches": [
            "Valladolid",
            "Espanyol",
            "Real Madrid"
        ],
        "nameImgTemplate": "Plantilla_AtleticodeMadrid",
        "nameImgShield": "escudo_AtleticodeMadrid"
    },
    {
        "id": 4,
        "name": "Real Sociedad",
        "winners": 12,
        "lossers": 8,
        "tied": 4,
        "points": 40,
        "goalsInFavor": 42,
        "goalsAgainst": 31,
        "position": 3,
        "league": "La liga",
        "direction": "Reale Arena",
        "matches": [
            "Betis",
            "Osasuna",
            "Valladolid"
        ],
        "nameImgTemplate": "Plantilla_RealSociedad",
        "nameImgShield": "escudo_RealSociedad"
    },
    {
        "id": 5,
        "name": "Osasuna",
        "winners": 9,
        "lossers": 5,
        "tied": 10,
        "points": 37,
        "goalsInFavor": 28,
        "goalsAgainst": 18,
        "position": 5,
        "league": "La liga",
        "direction": "Estadio El Sadar",
        "matches": [
            "Real Madrid",
            "Real Sociedad",
            "Espanyol"
        ],
        "nameImgTemplate": "Plantilla_Osasuna",
        "nameImgShield": "escudo_Osasuna"
    },
    {
        "id": 6,
        "name": "Espanyol",
        "winners": 8,
        "lossers": 6,
        "tied": 10,
        "points": 34,
        "goalsInFavor": 30,
        "goalsAgainst": 10,
        "position": 6,
        "league": "La liga",
        "direction": "RCDE Stadium",
        "matches": [
            "Barcelona",
            "Atletico de Madrid",
            "Osasuna"
        ],
        "nameImgTemplate": "plantilla_Espanyol",
        "nameImgShield": "escudo_Espanyol"
    },
    {
        "id": 7,
        "name": "Valladolid",
        "winners": 11,
        "lossers": 13,
        "tied": 0,
        "points": 33,
        "goalsInFavor": 45,
        "goalsAgainst": 30,
        "position": 7,
        "league": "La liga",
        "direction": "Estadio José Zorrilla",
        "matches": [
            "Atletico de Madrid",
            "Betis",
            "Real Sociedad"
        ],
        "nameImgTemplate": "Plantilla_Valladolid",
        "nameImgShield": "escudo_Valladolid"
    },
    {
        "id": 8,
        "name": "Betis",
        "winners": 7,
        "lossers": 7,
        "tied": 10,
        "points": 31,
        "goalsInFavor": 30,
        "goalsAgainst": 20,
        "position": 8,
        "league": "La liga",
        "direction": "Alava",
        "matches": [
            "Real Sociedad",
            "Valladolid",
            "Barcelona"
        ],
        "nameImgTemplate": "plantilla_Betis",
        "nameImgShield": "escudo_Betis"
    },
    {
        "id": 9,
        "name": "Getafe",
        "winners": 7,
        "lossers": 7,
        "tied": 10,
        "points": 30,
        "goalsInFavor": 30,
        "goalsAgainst": 20,
        "position": 9,
        "league": "La liga",
        "direction": "Coliseum Alfonso Perez",
        "matches": [
            "Real Sociedad",
            "Valladolid",
            "Barcelona"
        ],
        "nameImgTemplate": "Plantilla_RealSociedad",
        "nameImgShield": "escudo_getafe"
    },
    {
        "id": 10,
        "name": "Villareal",
        "winners": 7,
        "lossers": 7,
        "tied": 10,
        "points": 29,
        "goalsInFavor": 30,
        "goalsAgainst": 20,
        "position": 10,
        "league": "La liga",
        "direction": "Coliseum Alfonso Perez",
        "matches": [
            "Real Sociedad",
            "Valladolid",
            "Barcelona"
        ],
        "nameImgTemplate": "Plantilla_RealSociedad",
        "nameImgShield": "escudo_villareal"
    },
    {
        "id": 11,
        "name": "Mallorca",
        "winners": 7,
        "lossers": 7,
        "tied": 10,
        "points": 28,
        "goalsInFavor": 30,
        "goalsAgainst": 20,
        "position": 11,
        "league": "La liga",
        "direction": "Coliseum Alfonso Perez",
        "matches": [
            "Real Sociedad",
            "Valladolid",
            "Barcelona"
        ],
        "nameImgTemplate": "Plantilla_RealSociedad",
        "nameImgShield": "escudo_mallorca"
    },
    {
        "id": 12,
        "name": "Sevilla",
        "winners": 7,
        "lossers": 7,
        "tied": 10,
        "points": 27,
        "goalsInFavor": 30,
        "goalsAgainst": 20,
        "position": 12,
        "league": "La liga",
        "direction": "Coliseum Alfonso Perez",
        "matches": [
            "Real Sociedad",
            "Valladolid",
            "Barcelona"
        ],
        "nameImgTemplate": "Plantilla_RealSociedad",
        "nameImgShield": "escudo_sevilla"
    }
]
	```


* ##### Error response:

	**Code**: NOT.FOUND
	
	
	###nextMatches
Allows a anonymous user to see the next matches(first match of all teams) of the teams
* ##### URL:

	< /equipos/nextmatches >

* ##### Method:

	`GET`
	
* ##### Success Response:
  
  	```
		[
			{
				"localTeam": {
					"id": 1,
					"name": "Real Madrid",
					"winners": 14,
					"lossers": 2,
					"tied": 8,
					"points": 53,
					"goalsInFavor": 46,
					"goalsAgainst": 17,
					"position": 1,
					"league": "La liga",
					"direction": "Santiago Bernabeu",
					"matches": [
						"Osasuna",
						"Barcelona",
						"Atletico de Madrid"
					],
					"nameImgTemplate": "Plantilla_RealMadrid",
					"nameImgShield": "escudo_RealMadrid"
				},
				"visitantTeam": {
					"id": 5,
					"name": "Osasuna",
					"winners": 9,
					"lossers": 5,
					"tied": 10,
					"points": 37,
					"goalsInFavor": 28,
					"goalsAgainst": 18,
					"position": 5,
					"league": "La liga",
					"direction": "Estadio El Sadar",
					"matches": [
						"Real Madrid",
						"Real Sociedad",
						"Espanyol"
					],
					"nameImgTemplate": "Plantilla_Osasuna",
					"nameImgShield": "escudo_Osasuna"
				},
				"dateMatch": "20/3/2020 21:00",
				"result": null,
				"betSelected": null,
				"betLocal": "1.18",
				"betVisit": "5.76",
				"betTied": "3.47"
			},
			{
				"localTeam": {
					"id": 2,
					"name": "Barcelona",
					"winners": 16,
					"lossers": 4,
					"tied": 4,
					"points": 52,
					"goalsInFavor": 62,
					"goalsAgainst": 29,
					"position": 2,
					"league": "La liga",
					"direction": "Camp Nou",
					"matches": [
						"Espanyol",
						"Real Madrid",
						"Betis"
					],
					"nameImgTemplate": "Plantilla_Barcelona",
					"nameImgShield": "escudo_Barcelona"
				},
				"visitantTeam": {
					"id": 6,
					"name": "Espanyol",
					"winners": 8,
					"lossers": 6,
					"tied": 10,
					"points": 34,
					"goalsInFavor": 30,
					"goalsAgainst": 10,
					"position": 6,
					"league": "La liga",
					"direction": "RCDE Stadium",
					"matches": [
						"Barcelona",
						"Atletico de Madrid",
						"Osasuna"
					],
					"nameImgTemplate": "plantilla_Espanyol",
					"nameImgShield": "escudo_Espanyol"
				},
				"dateMatch": "17/3/2020 22:00",
				"result": null,
				"betSelected": null,
				"betLocal": "1.48",
				"betVisit": "5.46",
				"betTied": "3.47"
			},
			{
				"localTeam": {
					"id": 3,
					"name": "Atletico de Madrid",
					"winners": 10,
					"lossers": 4,
					"tied": 10,
					"points": 40,
					"goalsInFavor": 25,
					"goalsAgainst": 17,
					"position": 4,
					"league": "La liga",
					"direction": "Wanda Metropolitano",
					"matches": [
						"Valladolid",
						"Espanyol",
						"Real Madrid"
					],
					"nameImgTemplate": "Plantilla_AtleticodeMadrid",
					"nameImgShield": "escudo_AtleticodeMadrid"
				},
				"visitantTeam": {
					"id": 7,
					"name": "Valladolid",
					"winners": 11,
					"lossers": 13,
					"tied": 0,
					"points": 33,
					"goalsInFavor": 45,
					"goalsAgainst": 30,
					"position": 7,
					"league": "La liga",
					"direction": "Estadio José Zorrilla",
					"matches": [
						"Atletico de Madrid",
						"Betis",
						"Real Sociedad"
					],
					"nameImgTemplate": "Plantilla_Valladolid",
					"nameImgShield": "escudo_Valladolid"
				},
				"dateMatch": "19/3/2020 22:00",
				"result": null,
				"betSelected": null,
				"betLocal": "1.96",
				"betVisit": "4.98",
				"betTied": "3.47"
			},
			{
				"localTeam": {
					"id": 4,
					"name": "Real Sociedad",
					"winners": 12,
					"lossers": 8,
					"tied": 4,
					"points": 40,
					"goalsInFavor": 42,
					"goalsAgainst": 31,
					"position": 3,
					"league": "La liga",
					"direction": "Reale Arena",
					"matches": [
						"Betis",
						"Osasuna",
						"Valladolid"
					],
					"nameImgTemplate": "Plantilla_RealSociedad",
					"nameImgShield": "escudo_RealSociedad"
				},
				"visitantTeam": {
					"id": 8,
					"name": "Betis",
					"winners": 7,
					"lossers": 7,
					"tied": 10,
					"points": 31,
					"goalsInFavor": 30,
					"goalsAgainst": 20,
					"position": 8,
					"league": "La liga",
					"direction": "Alava",
					"matches": [
						"Real Sociedad",
						"Valladolid",
						"Barcelona"
					],
					"nameImgTemplate": "plantilla_Betis",
					"nameImgShield": "escudo_Betis"
				},
				"dateMatch": "20/3/2020 22:00",
				"result": null,
				"betSelected": null,
				"betLocal": "1.83",
				"betVisit": "5.04",
				"betTied": "3.44"
			}
		]
	```


* ##### Error response:

	**Code**: NOT.FOUND

	###calculateTotal
Allows a user to calculate prize of a bet and show this prize. 
For execution of this rest is need first bet in someone match, else multiply for 0.
* ##### URL:

	< /apostar/calcularTotal/{precio} >

* ##### Method:

	`GET`
	
* ##### Success Response:
  
  	```
		7.20
	```


* ##### Error response:

	**Code**: NOT_ACCEPTABLE
	
		###getHistorico
Allows a user to see info of all own bets.
* ##### URL:

	< //historico/ >

* ##### Method:

	`GET`
	
* ##### Success Response:
  
  	```
	[
    {
        "id": 1,
        "user": {
            "id": 2,
            "name": "Alvaro",
            "surname": "Orbaneja",
            "email": "alvaro@gmail.com",
            "passwordHash": "$2a$10$X7YGaSa3EVa9wvJbnqH.F.3wFPnZ.XESkOqn5X2gI4WUK/oRRCHE2",
            "fav_team": "Real Madrid",
            "acc_balance": 1000,
            "roles": [
                "ROLE_USER"
            ]
        },
        "matches": [
            "Real Madrid vs Osasuna Ganado: 500€"
        ]
    }
	]
	```


* ##### Error response:

	**Code**: UNAUTHORIZED
		###updateTeam
Allows a admin to update info of a team specific
* ##### URL:

	< /equipos/update/{id}/ >

* ##### Method:

	`PUT`
	
* ##### Body request:
  
  	```
	{
        "id": 1,
        "name": "Real Madrid",
        "winners": 1,
        "lossers": 1,
        "tied": 1,
        "points": 4,
        "goalsInFavor": 46,
        "goalsAgainst": 17,
        "position": 12,
        "league": "La liga",
        "direction": "Santiago Bernabeu",
        "matches": [
            "Osasuna",
            "Barcelona",
            "Atletico de Madrid"
        ],
        "nameImgTemplate": "Plantilla_RealMadrid",
        "nameImgShield": "escudo_RealMadrid"
    }
	```
* ##### Success Response:
	{
		"id": 1,
		"name": "Real Madrid",
		"winners": 1,
		"lossers": 1,
		"tied": 1,
		"points": 4,
		"goalsInFavor": 46,
		"goalsAgainst": 17,
		"position": 12,
		"league": "La liga",
		"direction": "Santiago Bernabeu",
		"matches": [
			"Osasuna",
			"Barcelona",
			"Atletico de Madrid"
		],
		"nameImgTemplate": "Plantilla_RealMadrid",
		"nameImgShield": "escudo_RealMadrid"
	}

* ##### Error response:

	**Code**: CONFLICT
		###updateTeamEfecttive
Allows a admin to update info of a team specific. If someone atributte of team is empty, this not setted.
* ##### URL:

	< /equipos/updateTeam/{id}/ >

* ##### Method:

	`PUT`
* ##### Body request:
	```
	{
		"id": 1,
		"name": "",
		"winners": 13,
		"lossers": 2,
		"tied": 8,
		"points": 53,
		"goalsInFavor": 46,
		"goalsAgainst": 17,
		"position": 1,
		"league": "La liga",
		"direction": "Santiago Bernabeu",
		"matches": [
			"Osasuna",
			"Barcelona",
			"Atletico de Madrid"
		],
		"nameImgTemplate": "Plantilla_RealMadrid",
		"nameImgShield": "escudo_RealMadrid"
	}
	```
* ##### Success Response:
  
  	```
	{
			"id": 1,
			"name": "Real Madrid",
			"winners": 14,
			"lossers": 2,
			"tied": 8,
			"points": 53,
			"goalsInFavor": 46,
			"goalsAgainst": 17,
			"position": 1,
			"league": "La liga",
			"direction": "Santiago Bernabeu",
			"matches": [
				"Osasuna",
				"Barcelona",
				"Atletico de Madrid"
			],
			"nameImgTemplate": "Plantilla_RealMadrid",
			"nameImgShield": "escudo_RealMadrid"
	}
	```


* ##### Error response:

	**Code**: CONFLICT

	###updateTeamPosition
Allows a admin to update position of a team specific.
* ##### URL:

	< /equipos/update/{id}/{p} >

* ##### Method:

	`PUT`
	
* ##### Success Response:
  
  	```
		{
			"id": 1,
			"name": "Real Madrid",
			"winners": 14,
			"lossers": 2,
			"tied": 8,
			"points": 53,
			"goalsInFavor": 46,
			"goalsAgainst": 17,
			"position": 2,
			"league": "La liga",
			"direction": "Santiago Bernabeu",
			"matches": [
				"Osasuna",
				"Barcelona",
				"Atletico de Madrid"
			],
			"nameImgTemplate": "Plantilla_RealMadrid",
			"nameImgShield": "escudo_RealMadrid"
		}
	```


* ##### Error response:

	**Code**: NOT.FOUND
		###updateMatchWin
Allows a admin to update points of win.
* ##### URL:

	< /equipos/updateTeamWin/1/ >

* ##### Method:

	`PUT`

* ##### Success Response:
  
  	```
	{
		"id": 1,
		"name": "Real Madrid",
		"winners": 15,
		"lossers": 2,
		"tied": 8,
		"points": 56,
		"goalsInFavor": 46,
		"goalsAgainst": 17,
		"position": 1,
		"league": "La liga",
		"direction": "Santiago Bernabeu",
		"matches": [
			"Osasuna",
			"Barcelona",
			"Atletico de Madrid"
		],
		"nameImgTemplate": "Plantilla_RealMadrid",
		"nameImgShield": "escudo_RealMadrid"
	}
	```


* ##### Error response:

	**Code**: NOT.FOUND

		###updateMatchTied
Allows a admin to update points of tied.
* ##### URL:

	< /equipos/ >

* ##### Method:

	`PUT`
	
* ##### Success Response:
	```
	{
		"id": 2,
		"name": "Barcelona",
		"winners": 16,
		"lossers": 4,
		"tied": 5,
		"points": 53,
		"goalsInFavor": 62,
		"goalsAgainst": 29,
		"position": 2,
		"league": "La liga",
		"direction": "Camp Nou",
		"matches": [
			"Espanyol",
			"Real Madrid",
			"Betis"
		],
		"nameImgTemplate": "Plantilla_Barcelona",
		"nameImgShield": "escudo_Barcelona"
	}
	```

* ##### Error response:

	**Code**: NOT.FOUND

		###updateFav
Allows a admin to update  team favorite of user
* ##### URL:

	< /user/updateFav/{userName}/{team} >

* ##### Method:

	`PUT`
	
* ##### Success Response:
  
  	```
		{
			"id": 2,
			"name": "Alvaro",
			"surname": "Orbaneja",
			"email": "alvaro@gmail.com",
			"passwordHash": "$2a$10$l6e2shKy0gXaH2catAgYSuOZdBWkqXGpW15IQtgNSaA7puYNvVLbO",
			"fav_team": "Mallorca",
			"acc_balance": 1000,
			"roles": [
				"ROLE_USER"
			]
		}
	```


* ##### Error response:

	**Code**: NOT.FOUND


		###updateFavLoggerUser
Allows a user to update her team favorite
* ##### URL:

	< /user/updateFav/{team} >

* ##### Method:

	`PUT`
	
* ##### Success Response:
  
  	```
		{
			"id": 2,
			"name": "Alvaro",
			"surname": "Orbaneja",
			"email": "alvaro@gmail.com",
			"passwordHash": "$2a$10$l6e2shKy0gXaH2catAgYSuOZdBWkqXGpW15IQtgNSaA7puYNvVLbO",
			"fav_team": "Mallorca",
			"acc_balance": 1000,
			"roles": [
				"ROLE_USER"
			]
		}
	```


* ##### Error response:

	**Code**: NOT.FOUND


		###doBetRest
Allows a user to bet. Rest returns the simulating of the matches.
* ##### URL:

	< /apostar/doBet/{totalBet} >

* ##### Method:

	`PUT`
	
* ##### Success Response:
  
  	```
		true
	```


* ##### Error response:

	**Code**: NOT_ACCEPTABLE

		###apostar
Allows a user to fill the bet. 
{id1} TeamLocal
{id2} TeamVisit
{id3} bet
{id4} team of bet
Return the array of matchs who its bets.
* ##### URL:

	< /apostar/{id1}/{id2}/{id3}/{id4} >

* ##### Method:

	`POST`
	
* ##### Success Response:
  
  	```
[
    {
        "localTeam": {
            "id": 2,
            "name": "Barcelona",
            "winners": 16,
            "lossers": 4,
            "tied": 4,
            "points": 52,
            "goalsInFavor": 62,
            "goalsAgainst": 29,
            "position": 2,
            "league": "La liga",
            "direction": "Camp Nou",
            "matches": [
                "Espanyol",
                "Real Madrid",
                "Betis"
            ],
            "nameImgTemplate": "Plantilla_Barcelona",
            "nameImgShield": "escudo_Barcelona"
        },
        "visitantTeam": {
            "id": 6,
            "name": "Espanyol",
            "winners": 8,
            "lossers": 6,
            "tied": 10,
            "points": 34,
            "goalsInFavor": 30,
            "goalsAgainst": 10,
            "position": 6,
            "league": "La liga",
            "direction": "RCDE Stadium",
            "matches": [
                "Barcelona",
                "Atletico de Madrid",
                "Osasuna"
            ],
            "nameImgTemplate": "plantilla_Espanyol",
            "nameImgShield": "escudo_Espanyol"
        },
        "dateMatch": null,
        "result": null,
        "betSelected": "1.48",
        "betLocal": "1.48",
        "betVisit": null,
        "betTied": null
    }
]
	```


* ##### Error response:

	**Code**: NOT.FOUND

		###addUser
Register user by anonymous user
* ##### URL:

	< /user/addUser >

* ##### Method:

	`POST`
	
* ##### Body Request:
  	```
	{
		"name": "Juan",
		"surname": "Anton",
		"email": "juan@gmail.com",
		"fav_team": "Barcelona",
		"passwordHash":"1234",
		"acc_balance": 1000,
		"roles": [
			"ROLE_USER"
		]
	}
	```
* ##### Success Response:
  
  	```
	{
		"name": "Juan",
		"surname": "Anton",
		"email": "juan@gmail.com",
		"fav_team": "Barcelona",
		"passwordHash":"1234",
		"acc_balance": 1000,
		"roles": [
			"ROLE_USER"
		]
	}
	```




* ##### Error response:

	**Code**: CONFLICT


		###saveTeam
Register team by admin
* ##### URL:

	< /equipos/saveTeam/ >

* ##### Method:

	`POST`
	
* ##### Body Request:
  	```
    {
        "name": "Recreativo Madridf",
        "winners": 14,
        "lossers": 2,
        "tied": 8,
        "points": 34,
        "goalsInFavor": 45,
        "goalsAgainst": 17,
        "position": 1,
        "league": "La liga",
        "direction": "Santiago Bernabeu",
        "matches": [
        ],
        "nameImgTemplate": "Plantilla_RealMadrid",
        "nameImgShield": "escudo_RealMadrid"
    }
	```
* ##### Success Response:
  
  	```
	{
		"id": 13,
		"name": "Recreativo ",
		"winners": 14,
		"lossers": 2,
		"tied": 8,
		"points": 34,
		"goalsInFavor": 45,
		"goalsAgainst": 17,
		"position": 1,
		"league": "La liga",
		"direction": "Santiago Bernabeu",
		"matches": [],
		"nameImgTemplate": "Plantilla_RealMadrid",
		"nameImgShield": "escudo_RealMadrid"
	}
	```


* ##### Error response:

	**Code**: CONFLICT

		###addMatch
Register match by admin
* ##### URL:

	< /equipos/addMatch/{local}/{visit} >

* ##### Method:

	`POST`
	
* ##### Success Response:
  
  	```
	{
		"localTeam": {
			"id": 1,
			"name": "Real Madrid",
			"winners": 14,
			"lossers": 2,
			"tied": 8,
			"points": 53,
			"goalsInFavor": 46,
			"goalsAgainst": 17,
			"position": 1,
			"league": "La liga",
			"direction": "Santiago Bernabeu",
			"matches": [
				"Osasuna",
				"Barcelona",
				"Atletico de Madrid",
				"Barcelona"
			],
			"nameImgTemplate": "Plantilla_RealMadrid",
			"nameImgShield": "escudo_RealMadrid"
		},
		"visitantTeam": {
			"id": 2,
			"name": "Barcelona",
			"winners": 16,
			"lossers": 4,
			"tied": 4,
			"points": 52,
			"goalsInFavor": 62,
			"goalsAgainst": 29,
			"position": 2,
			"league": "La liga",
			"direction": "Camp Nou",
			"matches": [
				"Espanyol",
				"Real Madrid",
				"Betis"
			],
			"nameImgTemplate": "Plantilla_Barcelona",
			"nameImgShield": "escudo_Barcelona"
		},
		"dateMatch": null,
		"result": null,
		"betSelected": null,
		"betLocal": null,
		"betVisit": null,
		"betTied": null
	}
	```


* ##### Error response:

	**Code**: CONFLICT


		###deleteBet
Delete list of bets makes from user
* ##### URL:

	< /apostar/deleteBet >

* ##### Method:

	`DELETE`
	
* ##### Error response:

	**Code**: NOT_MODIFIED

		###deleteTeam
Delete team by admin
* ##### URL:

	< /equipos/deleteTeam/{name} >

* ##### Method:

	`DELETE`
	
* ##### Error response:

	**Code**: NOT_FOUND





