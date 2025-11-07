package org.crescer.militar;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EspecialistaTerrestre extends Piloto {
    private LocalDate validadeCaminhao;
    private LocalDate validadeTanque;

    public EspecialistaTerrestre(BigDecimal salario, LocalDate validadeCaminhao, LocalDate validadeTanque) {
        super(salario, escolherValidade(validadeCaminhao, validadeTanque));
        this.validadeCaminhao = validadeCaminhao != null ? validadeCaminhao : LocalDate.now().plusDays(30);
        this.validadeTanque = validadeTanque != null ? validadeTanque : LocalDate.now().plusDays(30);
    }

    public EspecialistaTerrestre() {
        super(BigDecimal.ZERO, LocalDate.now().plusDays(30));
        this.validadeCaminhao = LocalDate.now().plusDays(30);
        this.validadeTanque = LocalDate.now().plusDays(30);
    }

    private static LocalDate escolherValidade(LocalDate validadeCaminhao, LocalDate validadeTanque) {
        if (validadeCaminhao != null) return validadeCaminhao;
        if (validadeTanque != null) return validadeTanque;
        return LocalDate.now().plusDays(30);
    }

    public LocalDate getValidadeCaminhao() {
        return validadeCaminhao;
    }

    public void setValidadeCaminhao(LocalDate validadeCaminhao) {
        this.validadeCaminhao = validadeCaminhao;
    }

    public LocalDate getValidadeTanque() {
        return validadeTanque;
    }

    public void setValidadeTanque(LocalDate validadeTanque) {
        this.validadeTanque = validadeTanque;
    }

    public boolean licencaValidaPara(String tipoVeiculo) {
        if (tipoVeiculo == null) return false;

        LocalDate hoje = LocalDate.now();
        switch (tipoVeiculo) {
            case "Caminhao":
                return validadeCaminhao != null &&
                        (validadeCaminhao.isEqual(hoje) || validadeCaminhao.isAfter(hoje));
            case "Tanque":
                return validadeTanque != null &&
                        (validadeTanque.isEqual(hoje) || validadeTanque.isAfter(hoje));
            default:
                return false;
        }
    }
}
