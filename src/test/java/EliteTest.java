import org.crescer.militar.Elite;
import org.crescer.veiculo.TipoVeiculo;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class EliteTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testEliteConstructor() {
        LocalDate validadeAviao = LocalDate.now().plusMonths(6);
        LocalDate validadeCaminhao = LocalDate.now().plusMonths(12);
        LocalDate validadeHelicoptero = LocalDate.now().plusMonths(18);
        LocalDate validadeTanque = LocalDate.now().plusMonths(24);
        BigDecimal salario = new BigDecimal("5000.00");
        Elite elite = new Elite(salario, validadeAviao, validadeCaminhao, validadeHelicoptero, validadeTanque);
        Assert.assertEquals(salario, elite.getSalario());
        Assert.assertEquals(validadeAviao, elite.getValidadeAviao());
        Assert.assertEquals(validadeCaminhao, elite.getValidadeCaminhao());
        Assert.assertEquals(validadeHelicoptero, elite.getValidadeHelicoptero());
        Assert.assertEquals(validadeTanque, elite.getValidadeTanque());
    }
    @Test
    public void testLicencaValidaPara() {
        LocalDate validadeAviao = LocalDate.now().plusMonths(6);
        LocalDate validadeCaminhao = LocalDate.now().plusMonths(1);
        LocalDate validadeHelicoptero = LocalDate.now().plusMonths(18);
        LocalDate validadeTanque = LocalDate.now().plusMonths(12);
        BigDecimal salario = new BigDecimal("6000.00");
        Elite elite = new Elite(salario, validadeAviao, validadeCaminhao, validadeHelicoptero, validadeTanque);
        Assert.assertTrue(elite.licencaValidaPara(TipoVeiculo.AVIAO));
        Assert.assertTrue(elite.licencaValidaPara(TipoVeiculo.CAMINHAO));
        Assert.assertTrue(elite.licencaValidaPara(TipoVeiculo.HELICOPTERO));
        Assert.assertTrue(elite.licencaValidaPara(TipoVeiculo.TANQUE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEliteConstructorComValidadeAviaoInvalida() {
        LocalDate validadeAviao = LocalDate.now().minusDays(1);
        LocalDate validadeCaminhao = LocalDate.now().plusMonths(12);
        LocalDate validadeHelicoptero = LocalDate.now().plusMonths(18);
        LocalDate validadeTanque = LocalDate.now().plusMonths(24);
        BigDecimal salario = new BigDecimal("7000.00");
        new Elite(salario, validadeAviao, validadeCaminhao, validadeHelicoptero, validadeTanque);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testConstrutorDeveLancarExcecaoQuandoValidadeCaminhaoForPassada() {
        LocalDate validadeAviao = LocalDate.now().plusDays(10);
        LocalDate validadeCaminhao = LocalDate.now().minusDays(1);
        LocalDate validadeHelicoptero = LocalDate.now().plusDays(10);
        LocalDate validadeTanque = LocalDate.now().plusDays(10);
        BigDecimal salario = new BigDecimal("5000.00");
        new Elite(salario, validadeAviao, validadeCaminhao, validadeHelicoptero, validadeTanque);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorDeveLancarExcecaoQuandoValidadeHelicopteroExpirada() {
        LocalDate validadeAviao = LocalDate.now().plusDays(10);
        LocalDate validadeCaminhao = LocalDate.now().plusDays(20);
        LocalDate validadeHelicoptero = LocalDate.now().minusDays(1);
        LocalDate validadeTanque = LocalDate.now().plusDays(30);
        BigDecimal salario = new BigDecimal("7000.00");
        new Elite(salario, validadeAviao, validadeCaminhao, validadeHelicoptero, validadeTanque);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorDeveLancarExcecaoQuandoValidadeTanqueExpirada() {
        LocalDate validadeAviao = LocalDate.now().plusDays(10);
        LocalDate validadeCaminhao = LocalDate.now().plusDays(20);
        LocalDate validadeHelicoptero = LocalDate.now().plusDays(30);
        LocalDate validadeTanque = LocalDate.now().minusDays(1);
        BigDecimal salario = new BigDecimal("8000.00");
        new Elite(salario, validadeAviao, validadeCaminhao, validadeHelicoptero, validadeTanque);
    }
    @Test
    public void deveLancarExcecaoQuandoAlgumaValidadeForAnteriorADataAtual() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("As datas de validade devem ser no futuro.");
        LocalDate validadeAviao = LocalDate.now().minusDays(1);
        LocalDate validadeCaminhao = LocalDate.now().plusDays(10);
        LocalDate validadeHelicoptero = LocalDate.now().plusDays(20);
        LocalDate validadeTanque = LocalDate.now().plusDays(30);
        BigDecimal salario = new BigDecimal("9000.00");
        new Elite(salario, validadeAviao, validadeCaminhao, validadeHelicoptero, validadeTanque);
    }
    @Test
    public void deveRetornarFalseQuandoTipoVeiculoForNulo() {
        LocalDate validadeAviao = LocalDate.now().plusDays(10);
        LocalDate validadeCaminhao = LocalDate.now().plusDays(20);
        LocalDate validadeHelicoptero = LocalDate.now().plusDays(30);
        LocalDate validadeTanque = LocalDate.now().plusDays(40);
        BigDecimal salario = new BigDecimal("5000.00");
        Elite elite = new Elite(salario, validadeAviao, validadeCaminhao, validadeHelicoptero, validadeTanque);
        boolean resultado = elite.licencaValidaPara(null);
        Assert.assertFalse("Deve retornar false quando o tipo de veículo for nulo", resultado);
    }
    @Test
    public void deveRetornarFalseQuandoTipoVeiculoNaoForPrevistoNoSwitch() {
        LocalDate validadeAviao = LocalDate.now().plusDays(10);
        LocalDate validadeCaminhao = LocalDate.now().plusDays(20);
        LocalDate validadeHelicoptero = LocalDate.now().plusDays(30);
        LocalDate validadeTanque = LocalDate.now().plusDays(40);
        BigDecimal salario = new BigDecimal("5000.00");
        Elite elite = new Elite(salario, validadeAviao, validadeCaminhao, validadeHelicoptero, validadeTanque);
        boolean resultado = elite.licencaValidaPara(TipoVeiculo.SUBMARINO);
        Assert.assertFalse("Deve retornar false quando o tipo de veículo não for tratado no switch", resultado);
    }
}
