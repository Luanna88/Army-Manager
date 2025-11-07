package org.crescer.veiculo;

import org.crescer.militar.Elite;
import org.crescer.militar.EspecialistaDoAr;
import org.crescer.militar.Militar;
import org.crescer.militar.Piloto;
import org.crescer.militar.PilotoAviao;
import java.math.BigDecimal;
import java.util.List;

public class Aviao extends Veiculo {

    public Aviao(Piloto piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitro) {
        super(piloto, tripulacao, quilometragemPorLitro, precoPorLitro);

        if (!(piloto instanceof Elite || piloto instanceof EspecialistaDoAr || piloto instanceof PilotoAviao)) {
            throw new IllegalArgumentException("Piloto inválido para Avião.");
        }

        if (tripulacao == null || tripulacao.isEmpty()) {
            throw new IllegalArgumentException("A tripulação não pode ser vazia.");
        }
    }

    @Override
    public boolean tripulacaoValida() {
        return getPiloto().licencaValida() && getTripulacao().size() <= 1;
    }
}
