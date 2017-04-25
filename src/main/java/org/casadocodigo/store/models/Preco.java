package org.casadocodigo.store.models;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Preco {

	private BigDecimal value;
	private TipoPreco precos;

	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public TipoPreco getPrecos() {
		return precos;
	}
	public void setPrecos(TipoPreco precos) {
		this.precos = precos;
	}
}
