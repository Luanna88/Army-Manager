package org.crescer.militar;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PilotoTanque extends Piloto {
    public PilotoTanque(BigDecimal salario, LocalDate validadeLicenca) {
        super(salario, validadeLicenca);
    }

    public PilotoTanque() {
        super(BigDecimal.ZERO, LocalDate.now().plusYears(1));
    }

}
