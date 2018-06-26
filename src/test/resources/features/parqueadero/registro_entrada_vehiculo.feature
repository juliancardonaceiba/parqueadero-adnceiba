Feature: Registra la entrada de un vehiculo en el parqueadero
	Los usuarios necesitan poder registrar cuando un vehiculo entra al parqueadero
	
Scenario: Registrar entrada de un vehiculo
	Given Juan recibe un vehiculo en el parqueadero
	When Registra la entrada de el vehiculo
	Then Debe generar el recibo de entrada
	