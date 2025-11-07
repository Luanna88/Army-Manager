package org.crescer.veiculo;

import org.crescer.militar.Elite;
import org.crescer.militar.EspecialistaTerrestre;
import org.crescer.militar.Militar;
import org.crescer.militar.Piloto;
import org.crescer.militar.PilotoCaminhao;
import java.math.BigDecimal;
import java.util.List;

public class Caminhao extends Veiculo {
    public Caminhao(Piloto piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitro) {
        super(piloto, tripulacao, quilometragemPorLitro, precoPorLitro);
        if (piloto == null ||
                !(piloto instanceof Elite || piloto instanceof EspecialistaTerrestre || piloto instanceof PilotoCaminhao)) {
            throw new IllegalArgumentException("Piloto inválido para Caminhão.");
        }
    }

    @Override
    public boolean tripulacaoValida() {
        if (getPiloto() instanceof EspecialistaTerrestre) {
            return ((EspecialistaTerrestre) getPiloto()).licencaValidaPara("Caminhao") &&
                    getTripulacao().size() >= 5 &&
                    getTripulacao().size() <= 30;
        } else if (getPiloto() instanceof PilotoCaminhao) {
            return getPiloto().licencaValida() &&
                    getTripulacao().size() >= 5 &&
                    getTripulacao().size() <= 30;
        }
        return false;
    }
}
