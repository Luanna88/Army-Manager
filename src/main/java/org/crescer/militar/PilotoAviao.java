package org.crescer.militar;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PilotoAviao extends Piloto{
    public PilotoAviao(BigDecimal salario, LocalDate validadeLicenca) {
        super(salario, validadeLicenca);
    }

    public PilotoAviao() {
        super(BigDecimal.ZERO, LocalDate.now().plusYears(1));
    }

    @Override
    public LocalDate getValidadeLicenca() {
        return validadeLicenca;
    }
}
