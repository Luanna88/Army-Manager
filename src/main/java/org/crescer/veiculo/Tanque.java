package org.crescer.veiculo;

import org.crescer.militar.Elite;
import org.crescer.militar.EspecialistaTerrestre;
import org.crescer.militar.Militar;
import org.crescer.militar.Piloto;
import org.crescer.militar.PilotoTanque;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Tanque extends Veiculo {
    public Tanque(Piloto piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitro) {
        super(piloto, tripulacao, quilometragemPorLitro, precoPorLitro);

        Objects.requireNonNull(piloto, "Piloto não pode ser nulo");
        Objects.requireNonNull(tripulacao, "Tripulação não pode ser nula");
        Objects.requireNonNull(precoPorLitro, "Preço por litro não pode ser nulo");

        if (!(piloto instanceof Elite || piloto instanceof EspecialistaTerrestre || piloto instanceof PilotoTanque)) {
            throw new IllegalArgumentException("Piloto inválido para Tanque.");
        }
    }

    @Override
    public boolean tripulacaoValida() {
        return getPiloto().licencaValida() && getTripulacao().size() == 3;
    }
}
