import org.crescer.militar.Elite;
import org.crescer.militar.Militar;
import org.crescer.militar.Piloto;
import org.crescer.militar.PilotoAviao;
import org.crescer.veiculo.Aviao;
import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AviaoTest {
    @Test
    public void PilotoAviaoTest() {
        BigDecimal salario = new BigDecimal("4500.00");
        LocalDate validadeLicenca = LocalDate.of(2025, 12, 31);
        PilotoAviao piloto = new PilotoAviao(salario, validadeLicenca);
        Assert.assertEquals(salario, piloto.getSalario());
        Assert.assertEquals(validadeLicenca, piloto.getValidadeLicenca());
    }
    @Test
    public void testConstrutorPilotoInvalido() {
        Piloto pilotoInvalido = new Piloto();
        List<Militar> tripulacao = Arrays.asList(new Militar());
        BigDecimal preco = new BigDecimal("5.0");
        IllegalArgumentException thrown = Assert.assertThrows(
                IllegalArgumentException.class,
                () -> new Aviao(pilotoInvalido, tripulacao, 10.0, preco)
        );
        Assert.assertEquals("Piloto inválido para Avião.", thrown.getMessage());
    }
    @Test
   public void testConstrutorComPilotoValido() {
       Piloto pilotoValido = new PilotoAviao();
       List<Militar> tripulacao = Arrays.asList(new Militar());
       Aviao aviao = new Aviao(pilotoValido, tripulacao, 10.0, new BigDecimal("5.0"));
       Assert.assertNotNull(aviao);
   }
    @Test
    public void testConstrutorComTripulacaoVazia() {
        Piloto pilotoValido = new PilotoAviao();
        List<Militar> tripulacaoVazia = Arrays.asList();
        BigDecimal preco = new BigDecimal("5.0");
        IllegalArgumentException thrown = Assert.assertThrows(
                IllegalArgumentException.class,
                () -> new Aviao(pilotoValido, tripulacaoVazia, 10.0, preco)
        );
        Assert.assertEquals("A tripulação não pode ser vazia.", thrown.getMessage());
    }
    @Test
    public void testTripulacaoValida() {
        PilotoAviao piloto = new PilotoAviao(new BigDecimal("5000"), LocalDate.of(2025, 12, 31));
        List<Militar> tripulacao = Arrays.asList(new Militar());
        Aviao aviao = new Aviao(piloto, tripulacao, 10.0, new BigDecimal("5.0"));
        Assert.assertTrue(aviao.tripulacaoValida());
    }
    @Test
    public void testTripulacaoInvalida() {
        PilotoAviao pilotoComLicencaExpirada = new PilotoAviao(new BigDecimal("5000"), LocalDate.of(2020, 12, 31));
        List<Militar> tripulacao = Arrays.asList(new Militar(), new Militar(), new Militar());
        Aviao aviao = new Aviao(pilotoComLicencaExpirada, tripulacao, 10.0, new BigDecimal("5.0"));
        Assert.assertFalse(aviao.tripulacaoValida());
    }
    @Test
    public void testConstrutorComPilotoElite() {
        BigDecimal salario = new BigDecimal("1000.0");
        LocalDate validadeAviao = LocalDate.now().plusMonths(6);
        LocalDate validadeCaminhao = LocalDate.now().plusMonths(6);
        LocalDate validadeHelicoptero = LocalDate.now().plusMonths(6);
        LocalDate validadeTanque = LocalDate.now().plusMonths(6);
        Piloto pilotoElite = new Elite(salario, validadeAviao, validadeCaminhao, validadeHelicoptero, validadeTanque);
        List<Militar> tripulacao = Arrays.asList(new Militar());
        Aviao aviao = new Aviao(pilotoElite, tripulacao, 10.0, new BigDecimal("5.0"));
        Assert.assertNotNull(aviao);
    }
    @Test
    public void testConstrutorComTripulacaoNula() {
        Piloto pilotoValido = new PilotoAviao();
        List<Militar> tripulacaoNula = null;
        BigDecimal preco = new BigDecimal("5.0");
        IllegalArgumentException thrown = Assert.assertThrows(
                IllegalArgumentException.class,
                () -> new Aviao(pilotoValido, tripulacaoNula, 10.0, preco)
        );
        Assert.assertEquals("A tripulação não pode ser vazia.", thrown.getMessage());
    }
    @Test
    public void testTripulacaoInvalidaPorTamanho() {
        PilotoAviao pilotoValido = new PilotoAviao(new BigDecimal("5000"), LocalDate.of(2025, 12, 31));
        List<Militar> tripulacaoGrande = Arrays.asList(new Militar(), new Militar(), new Militar());
        Aviao aviao = new Aviao(pilotoValido, tripulacaoGrande, 10.0, new BigDecimal("5.0"));
        Assert.assertFalse(aviao.tripulacaoValida());
    }
}
