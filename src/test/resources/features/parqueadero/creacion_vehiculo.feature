Feature: Crea un vehiculo en el parqueadero
	Los usuarios necesitan poder crear un vehiculo para luego registrar la entrada
	
Scenario: Creacion de un vehiculo
	Given Miguel recibe un nuevo vehiculo en el parqueadero
	When Registra los datos de el vehiculo
	Then Debe guardarse en la base de datos
	
	