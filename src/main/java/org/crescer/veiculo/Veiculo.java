package org.crescer.veiculo;

import org.crescer.militar.Militar;
import org.crescer.militar.Piloto;
import java.math.BigDecimal;
import java.util.List;

public abstract class Veiculo {
    private Piloto piloto;
    private List<Militar> tripulacao;
    protected double quilometragemPorLitro;
    protected BigDecimal precoPorLitro;

    protected Veiculo(Piloto piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitro) {
        this.piloto = piloto;
        this.tripulacao = tripulacao;
        this.quilometragemPorLitro = quilometragemPorLitro;
        this.precoPorLitro = precoPorLitro;
    }
    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public List<Militar> getTripulacao() {
        return tripulacao;
    }

    public void setTripulacao(List<Militar> tripulacao) {
        this.tripulacao = tripulacao;
    }

    public abstract boolean tripulacaoValida();

    public BigDecimal calcularCustoCombustivel(double distancia) {
        double combustivelNecessario = distancia / quilometragemPorLitro;
        return precoPorLitro.multiply(BigDecimal.valueOf(combustivelNecessario));
    }
}
