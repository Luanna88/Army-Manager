package org.crescer.militar;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PilotoCaminhao extends Piloto {
    public PilotoCaminhao(BigDecimal salario, LocalDate validadeLicenca) {
        super(salario, validadeLicenca);
    }

    @Override
    public LocalDate getValidadeLicenca() {
        return validadeLicenca;
    }
}