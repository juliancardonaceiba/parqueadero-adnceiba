Feature: Registra la salida de un vehiculo del parqueadero
	Los usuarios necesitan poder registrar cuando un vehiculo sale del parqueadero
	
Scenario: Registrar salida de un vehiculo
	Given Juan va a cobrar por un vehiculo en el parqueadero
	When Registra la salida de el vehiculo
	Then Debe generar el recibo de salida	
	
	