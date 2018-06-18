package co.com.ceiba.parqueadero.domain.model.builder;

import java.math.BigDecimal;

import co.com.ceiba.parqueadero.domain.model.Recargo;

public class RecargoTestDataBuilder {

	public static final Integer CILINDRAJE_MINIMO_DEFAULT = 500;
	public static final BigDecimal VALOR_RECARGADO_DEFAULT = BigDecimal.valueOf(2000);

	private Integer cilindrajeMinimo;

	private BigDecimal valorRecargado;

	public RecargoTestDataBuilder() {
		this.cilindrajeMinimo = CILINDRAJE_MINIMO_DEFAULT;
		this.valorRecargado = VALOR_RECARGADO_DEFAULT;
	}

	public RecargoTestDataBuilder withCilindrajeMinimo(Integer cilindrajeMinimo) {
		this.cilindrajeMinimo = cilindrajeMinimo;
		return this;
	}

	public RecargoTestDataBuilder withValorRecargado(BigDecimal valorRecargado) {
		this.valorRecargado = valorRecargado;
		return this;
	}

	public Recargo build() {
		return new Recargo(this.valorRecargado, this.cilindrajeMinimo);
	}

}
