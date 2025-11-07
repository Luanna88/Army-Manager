package org.crescer.militar;

import java.math.BigDecimal;

public class Militar {
    protected BigDecimal salario;

    public Militar(BigDecimal salario) {
        this.salario = (salario != null) ? salario : BigDecimal.ZERO;
    }

    public Militar() {
        this.salario = BigDecimal.ZERO;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        if (salario != null && salario.compareTo(BigDecimal.ZERO) >= 0) {
            this.salario = salario;
        } else {
            throw new IllegalArgumentException("O salário deve ser um valor não nulo e não negativo.");
        }
    }
}
