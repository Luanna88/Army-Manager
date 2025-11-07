package org.crescer.militar;

import org.crescer.veiculo.TipoVeiculo;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Elite extends Piloto {
    private LocalDate validadeAviao;
    private LocalDate validadeCaminhao;
    private LocalDate validadeHelicoptero;
    private LocalDate validadeTanque;

    public Elite(BigDecimal salario, LocalDate validadeAviao, LocalDate validadeCaminhao, LocalDate validadeHelicoptero, LocalDate validadeTanque) {
        super(salario, LocalDate.now().plusYears(1));

        this.validadeAviao = Objects.requireNonNull(validadeAviao, "Validade do avião não pode ser nula.");
        this.validadeCaminhao = Objects.requireNonNull(validadeCaminhao, "Validade do caminhão não pode ser nula.");
        this.validadeHelicoptero = Objects.requireNonNull(validadeHelicoptero, "Validade do helicóptero não pode ser nula.");
        this.validadeTanque = Objects.requireNonNull(validadeTanque, "Validade do tanque não pode ser nula.");

        if (validadeAviao.isBefore(LocalDate.now()) ||
                validadeCaminhao.isBefore(LocalDate.now()) ||
                validadeHelicoptero.isBefore(LocalDate.now()) ||
                validadeTanque.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("As datas de validade devem ser no futuro.");
        }
    }

    public boolean licencaValidaPara(TipoVeiculo tipoVeiculo) {
        if (tipoVeiculo == null) {
            return false;
        }
        switch (tipoVeiculo) {
            case AVIAO:
                return validadeAviao.isAfter(LocalDate.now());
            case CAMINHAO:
                return validadeCaminhao.isAfter(LocalDate.now());
            case HELICOPTERO:
                return validadeHelicoptero.isAfter(LocalDate.now());
            case TANQUE:
                return validadeTanque.isAfter(LocalDate.now());
            default:
                return false;
        }
    }

    public LocalDate getValidadeAviao() {
        return validadeAviao;
    }

    public LocalDate getValidadeCaminhao() {
        return validadeCaminhao;
    }

    public LocalDate getValidadeHelicoptero() {
        return validadeHelicoptero;
    }

    public LocalDate getValidadeTanque() {
        return validadeTanque;
    }
}
