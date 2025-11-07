package org.crescer.veiculo;

import org.crescer.militar.Elite;
import org.crescer.militar.EspecialistaDoAr;
import org.crescer.militar.Militar;
import org.crescer.militar.Piloto;
import org.crescer.militar.PilotoHelicoptero;
import java.math.BigDecimal;
import java.util.List;

public class Helicoptero extends Veiculo {

    public Helicoptero(Piloto piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitro) {
        super(piloto, tripulacao, quilometragemPorLitro, precoPorLitro);

        if (!(piloto instanceof Elite || piloto instanceof EspecialistaDoAr || piloto instanceof PilotoHelicoptero)) {
            throw new IllegalArgumentException("O piloto deve ser uma das seguintes especializações: Elite, EspecialistaDoAr ou PilotoHelicoptero.");
        }

        if (!isTripulacaoValida(tripulacao)) {
            throw new IllegalArgumentException("A tripulação não pode ser nula, vazia ou exceder o limite permitido.");
        }
        if (quilometragemPorLitro <= 0) {
            throw new IllegalArgumentException("A quilometragem por litro não pode ser negativa ou zero.");
        }

        if (precoPorLitro.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço por litro não pode ser negativo ou zero.");
        }
    }
    @Override
    public boolean tripulacaoValida() {
        return getPiloto().licencaValida() && isTripulacaoValida(getTripulacao());
    }

    protected boolean isTripulacaoValida(List<Militar> tripulacao) {
        return tripulacao != null && !tripulacao.isEmpty() && tripulacao.size() <= 10;
    }
}
