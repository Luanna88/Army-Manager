package org.crescer.simulacaofinanceira;

import org.crescer.militar.Militar;
import org.crescer.veiculo.Veiculo;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;
import java.text.MessageFormat;
import java.math.RoundingMode;

public class SimulacaoFinanceira {
    private static final Logger LOGGER = Logger.getLogger(SimulacaoFinanceira.class.getName());
    private double distancia;
    private List<Veiculo> veiculos;
    private int duracaoMissao;

    public SimulacaoFinanceira(double distancia, List<Veiculo> veiculos, int duracaoMissao) {
        if (veiculos == null || veiculos.isEmpty()) {
            throw new IllegalArgumentException("A lista de veículos não pode ser nula ou vazia.");
        }
        if (distancia <= 0 || duracaoMissao <= 0) {
            throw new IllegalArgumentException("Distância e duração da missão devem ser maiores que zero.");
        }

        this.distancia = distancia;
        this.veiculos = veiculos;
        this.duracaoMissao = duracaoMissao;
    }

    public BigDecimal getCustoTotalMissao() {
        BigDecimal custoSalariosPorDia = BigDecimal.ZERO;
        BigDecimal custoCombustivel = BigDecimal.ZERO;

        for (Veiculo veiculo : veiculos) {
            custoSalariosPorDia = custoSalariosPorDia.add(calcularCustoTripulantes(veiculo));
            custoCombustivel = custoCombustivel.add(calcularCustoCombustivel(veiculo, distancia));
        }

        BigDecimal custoTotalSalarios = custoSalariosPorDia.multiply(BigDecimal.valueOf(duracaoMissao));
        BigDecimal custoTotal = custoTotalSalarios.add(custoCombustivel);


        return arredondar(custoTotal);
    }

    public boolean todosTripulacoesValidas() {
        for (Veiculo veiculo : veiculos) {
            if (!veiculo.tripulacaoValida()) {
                LOGGER.warning(() -> MessageFormat.format("Tripulação inválida para o veículo: {0}", veiculo));
                return false;
            }
        }
        return true;
    }

    private BigDecimal calcularCustoTripulantes(Veiculo veiculo) {
        BigDecimal custo = BigDecimal.ZERO;
        if (veiculo.getPiloto() != null) {
            custo = custo.add(veiculo.getPiloto().getSalario());
        }
        for (Militar tripulante : veiculo.getTripulacao()) {
            if (tripulante != null) {
                custo = custo.add(tripulante.getSalario());
            }
        }
        return custo;
    }

    private BigDecimal calcularCustoCombustivel(Veiculo veiculo, double distancia) {
        return veiculo.calcularCustoCombustivel(distancia);
    }

    private BigDecimal arredondar(BigDecimal valor) {
        return valor.setScale(2, RoundingMode.HALF_UP);
    }
}
