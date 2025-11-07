import org.crescer.militar.Elite;
import org.crescer.militar.EspecialistaDoAr;
import org.crescer.militar.Militar;
import org.crescer.militar.Piloto;
import org.crescer.militar.PilotoTanque;
import org.crescer.simulacaofinanceira.SimulacaoFinanceira;
import org.crescer.veiculo.Aviao;
import org.crescer.veiculo.Tanque;
import org.crescer.veiculo.Veiculo;
import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimulacaoFinanceiraTest {
    @Test
    public void deveCalcularOCustoTotalDaMissaoCorretamente() {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(criarAviao());
        veiculos.add(criarTanque());
        veiculos.add(criarTanque());
        veiculos.add(criarTanque());
        veiculos.add(criarTanque());
        veiculos.add(criarTanque());
        SimulacaoFinanceira simulacao = new SimulacaoFinanceira(1137, veiculos, 1);
        BigDecimal custoTotal = simulacao.getCustoTotalMissao();
        BigDecimal valorEsperado = BigDecimal.valueOf(213623.83);
        BigDecimal margemErro = BigDecimal.valueOf(0.01);
        Assert.assertTrue("Custo total fora da margem de erro: esperado " + valorEsperado + ", obtido " + custoTotal, custoTotal.subtract(valorEsperado).abs().compareTo(margemErro) <= 0);
        Assert.assertTrue("Nem todas as tripulações são válidas", simulacao.todosTripulacoesValidas());
    }
    @Test
    public void deveCalcularOCustoTotalComCombustivelCorretamente() {
        Tanque tanque = criarTanque();
        SimulacaoFinanceira simulacao = new SimulacaoFinanceira(1137, new ArrayList<>(Arrays.asList(tanque)), 1);
        BigDecimal custoCombustivel = tanque.calcularCustoCombustivel(1137);
        BigDecimal expected = BigDecimal.valueOf(17903.46).setScale(2, RoundingMode.HALF_UP);
        BigDecimal delta = BigDecimal.valueOf(50.00);
        Assert.assertEquals(expected.setScale(2, RoundingMode.HALF_UP).doubleValue(), custoCombustivel.setScale(2, RoundingMode.HALF_UP).doubleValue(), delta.doubleValue());
    }
    @Test
    public void deveCalcularOCustoTotalComSalariosCorretamente() {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(criarAviao());
        SimulacaoFinanceira simulacao = new SimulacaoFinanceira(100, veiculos, 2);
        BigDecimal custoSalarios = BigDecimal.valueOf(7000 + 2500).multiply(BigDecimal.valueOf(2));
        BigDecimal custoCombustivel = veiculos.get(0).calcularCustoCombustivel(100);
        BigDecimal custoEsperado = custoSalarios.add(custoCombustivel).setScale(2, RoundingMode.HALF_UP);
        BigDecimal custoCalculado = simulacao.getCustoTotalMissao().setScale(2, RoundingMode.HALF_UP);
        System.out.println("Custo Esperado: " + custoEsperado);
        System.out.println("Custo Calculado: " + custoCalculado);
        Assert.assertEquals("Os custos não são iguais", custoEsperado, custoCalculado);
    }
    @Test
    public void deveLancarExcecaoParaVeiculosInvalidos() {
        List<Veiculo> veiculos = new ArrayList<>();
        IllegalArgumentException exception = Assert.assertThrows(
                IllegalArgumentException.class,
                () -> new SimulacaoFinanceira(100, veiculos, 1)
        );
        Assert.assertEquals("A lista de veículos não pode ser nula ou vazia.", exception.getMessage()
        );
    }
    private Tanque criarTanque() {
        Elite piloto = new Elite(BigDecimal.valueOf(3000), LocalDate.now().plusDays(20), LocalDate.now().plusDays(20), LocalDate.now().plusDays(20), LocalDate.now().plusDays(20));
        ArrayList<Militar> tripulacao = new ArrayList<>();
        tripulacao.add(new PilotoTanque(BigDecimal.valueOf(2500), LocalDate.now().plusYears(1)));
        tripulacao.add(new Militar(BigDecimal.valueOf(600)));
        tripulacao.add(new Militar(BigDecimal.valueOf(600)));
        return new Tanque(piloto, tripulacao, 0.22, BigDecimal.valueOf(3.46));
    }
    private Aviao criarAviao() {
        EspecialistaDoAr piloto = new EspecialistaDoAr(BigDecimal.valueOf(7000), LocalDate.now().plusYears(1));
        ArrayList<Militar> tripulacao = new ArrayList<>();
        tripulacao.add(new Militar(BigDecimal.valueOf(2500)));
        return new Aviao(piloto, tripulacao, 0.14, BigDecimal.valueOf(10));
    }
    @Test
    public void deveRetornarFalseQuandoUmaTripulacaoForInvalida() {
        Veiculo veiculoValido = criarTanque();
        Veiculo veiculoInvalido = new Tanque(new PilotoTanque(BigDecimal.valueOf(1000), LocalDate.now().plusYears(1)), new ArrayList<>(), 0.22, BigDecimal.valueOf(3.46)) {
            @Override
            public boolean tripulacaoValida() {
                return false;
            }
            @Override
            public String toString() {
                return "TanqueInvalido";
            }};
        SimulacaoFinanceira simulacao = new SimulacaoFinanceira(100, new ArrayList<>(Arrays.asList(veiculoValido, veiculoInvalido)), 1);
        Assert.assertFalse("Simulação deve retornar false quando houver tripulação inválida", simulacao.todosTripulacoesValidas());
    }
    @Test
    public void deveLancarExcecaoQuandoListaDeVeiculosForNula() {
        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> new SimulacaoFinanceira(100, null, 1));
        Assert.assertEquals("A lista de veículos não pode ser nula ou vazia.", exception.getMessage());
    }
    @Test
    public void deveLancarExcecaoQuandoDuracaoMissaoForNaoPositiva() {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(criarTanque());
        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> new SimulacaoFinanceira(100, veiculos, 0));
        Assert.assertEquals("Distância e duração da missão devem ser maiores que zero.", exception.getMessage());
    }
    @Test
    public void deveLancarExcecaoQuandoDistanciaForNaoPositiva() {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(criarTanque());
        Exception exceptionZero = Assert.assertThrows(IllegalArgumentException.class, () -> new SimulacaoFinanceira(0, veiculos, 1));
        Assert.assertEquals("Distância e duração da missão devem ser maiores que zero.", exceptionZero.getMessage());
        Exception exceptionNegativa = Assert.assertThrows(IllegalArgumentException.class, () -> new SimulacaoFinanceira(-10, veiculos, 1));
        Assert.assertEquals("Distância e duração da missão devem ser maiores que zero.", exceptionNegativa.getMessage());
    }
    @Test
    public void deveIgnorarTripulanteNuloNoCalculoDeCusto() {
        Elite piloto = new Elite(BigDecimal.valueOf(3000), LocalDate.now().plusDays(20),
                LocalDate.now().plusDays(20), LocalDate.now().plusDays(20), LocalDate.now().plusDays(20));
        ArrayList<Militar> tripulacao = new ArrayList<>();
        tripulacao.add(new Militar(BigDecimal.valueOf(500)));
        tripulacao.add(null);
        Tanque tanque = new Tanque(piloto, tripulacao, 0.22, BigDecimal.valueOf(3.46));
        SimulacaoFinanceira simulacao = new SimulacaoFinanceira(100, new ArrayList<>(Arrays.asList(tanque)), 1);
        BigDecimal custoEsperado = piloto.getSalario().add(tripulacao.get(0).getSalario());
        BigDecimal custoCalculado = simulacao.getCustoTotalMissao().subtract(tanque.calcularCustoCombustivel(100)).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals(custoEsperado.setScale(2, RoundingMode.HALF_UP), custoCalculado);
    }
    @Test
    public void deveIgnorarPilotoNuloEmVeiculoDeTeste() {
        class VeiculoTeste extends Veiculo {
            public VeiculoTeste(Piloto piloto, List<Militar> tripulacao) {
                super(piloto, tripulacao, 1.0, BigDecimal.valueOf(1.0));
            }
            @Override
            public boolean tripulacaoValida() {
                return true;
            }
        }
        List<Militar> tripulacao = new ArrayList<>();
        tripulacao.add(new Militar(BigDecimal.valueOf(500)));
        tripulacao.add(new Militar(BigDecimal.valueOf(600)));
        VeiculoTeste veiculo = new VeiculoTeste(null, tripulacao);
        SimulacaoFinanceira simulacao = new SimulacaoFinanceira(100, new ArrayList<>(Arrays.asList(veiculo)), 1);
        BigDecimal custoEsperado = tripulacao.get(0).getSalario().add(tripulacao.get(1).getSalario());
        BigDecimal custoCalculado = simulacao.getCustoTotalMissao().subtract(veiculo.calcularCustoCombustivel(100)).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals(custoEsperado.setScale(2, RoundingMode.HALF_UP), custoCalculado);
    }
}
