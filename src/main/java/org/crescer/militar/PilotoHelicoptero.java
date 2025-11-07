package org.crescer.militar;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PilotoHelicoptero extends Piloto {

    public PilotoHelicoptero(BigDecimal salario, LocalDate validadeLicenca) {
        super(salario, validadeLicenca);
    }

    public PilotoHelicoptero() {
        super(BigDecimal.ZERO, LocalDate.now().plusYears(1));
    }
}
