package org.crescer.militar;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Piloto  extends Militar {
    protected LocalDate validadeLicenca;

    public Piloto(BigDecimal salario, LocalDate validadeLicenca) {
        super(salario);
        if (validadeLicenca == null) {
            throw new IllegalArgumentException("A validade da licença não pode ser nula");
        }
        this.validadeLicenca = validadeLicenca;
    }

    public Piloto() {
        super(BigDecimal.valueOf(2500));
        this.validadeLicenca = LocalDate.now().plusYears(1);
        this.validadeLicenca = LocalDate.now();
    }

    public boolean licencaValida() {
        return validadeLicenca != null && validadeLicenca.isAfter(LocalDate.now());
    }

    public LocalDate getValidadeLicenca() {
        return validadeLicenca;
    }

    public void setValidadeLicenca(LocalDate validadeLicenca) {
        if (validadeLicenca == null) {
            throw new IllegalArgumentException("A validade da licença não pode ser nula");
        }
        this.validadeLicenca = validadeLicenca;
    }
}