import org.crescer.militar.EspecialistaTerrestre;
import org.crescer.militar.Militar;
import org.crescer.veiculo.Caminhao;
import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

public class EspecialistaTerrestreTest {
    @Test
    public void testLicencaValidaParaCaminhao() {
        LocalDate validadeCaminhao = LocalDate.now().plusDays(10);
        LocalDate validadeTanque = LocalDate.now().minusDays(1);
        EspecialistaTerrestre especialista = new EspecialistaTerrestre(new BigDecimal("5000"), validadeCaminhao, validadeTanque);
        Assert.assertTrue(especialista.licencaValidaPara("Caminhao"));
        Assert.assertFalse(especialista.licencaValidaPara("Tanque"));
        Assert.assertFalse(especialista.licencaValidaPara("Outro"));
    }
    @Test
    public void testLicencaInvalidaParaCaminhao() {
        LocalDate validadeCaminhao = LocalDate.now().minusDays(10);
        LocalDate validadeTanque = LocalDate.now().minusDays(1);
        EspecialistaTerrestre especialista = new EspecialistaTerrestre(new BigDecimal("5000"), validadeCaminhao, validadeTanque);
        Assert.assertFalse(especialista.licencaValidaPara("Caminhao"));
        Assert.assertFalse(especialista.licencaValidaPara("Tanque"));
        Assert.assertFalse(especialista.licencaValidaPara("Outro"));
    }
    @Test
    public void testTripulacaoInvalida() {
        EspecialistaTerrestre pilotoInvalido = new EspecialistaTerrestre(new BigDecimal("5000"), LocalDate.now().minusDays(10), LocalDate.now().minusDays(10));
        Militar tripulante = new Militar(new BigDecimal("3000"));
        Caminhao caminhao = new Caminhao(pilotoInvalido, Collections.singletonList(tripulante), 5.0, new BigDecimal("4.50"));
        Assert.assertFalse(caminhao.tripulacaoValida());
    }
    @Test
    public void testConstrutorPilotoValido() {
        EspecialistaTerrestre pilotoValido = new EspecialistaTerrestre(new BigDecimal("5000"), LocalDate.now().plusDays(10), LocalDate.now().plusDays(10));
        Caminhao caminhao = new Caminhao(pilotoValido, Collections.emptyList(), 5.0, new BigDecimal("4.50"));
        Assert.assertNotNull(caminhao);
    }
    @Test
    public void testConstrutorComValoresNulos() {
        EspecialistaTerrestre especialista = new EspecialistaTerrestre(BigDecimal.valueOf(2000), null, null);
        Assert.assertEquals(LocalDate.now().plusDays(30), especialista.getValidadeCaminhao());
        Assert.assertEquals(LocalDate.now().plusDays(30), especialista.getValidadeTanque());
    }
    @Test
    public void testLicencaValidaParaCaminhaoComDataValida() {
        LocalDate validade = LocalDate.now().plusDays(10);
        EspecialistaTerrestre especialista = new EspecialistaTerrestre(BigDecimal.valueOf(2000), validade, null);
        Assert.assertTrue(especialista.licencaValidaPara("Caminhao"));
    }
    @Test
    public void testLicencaValidaParaTanqueComDataValida() {
        LocalDate validade = LocalDate.now().plusDays(10);
        EspecialistaTerrestre especialista = new EspecialistaTerrestre(BigDecimal.valueOf(2000), null, validade);
        Assert.assertTrue(especialista.licencaValidaPara("Tanque"));
    }
    @Test
    public void testLicencaValidaParaCaminhaoComDataExpirada() {
        LocalDate validade = LocalDate.now().minusDays(1);
        EspecialistaTerrestre especialista = new EspecialistaTerrestre(BigDecimal.valueOf(2000), validade, null);
        Assert.assertFalse(especialista.licencaValidaPara("Caminhao"));
    }
    @Test
    public void testLicencaValidaParaTanqueComDataExpirada() {
        LocalDate validade = LocalDate.now().minusDays(1);
        EspecialistaTerrestre especialista = new EspecialistaTerrestre(BigDecimal.valueOf(2000), null, validade);
        Assert.assertFalse(especialista.licencaValidaPara("Tanque"));
    }
    @Test
    public void testLicencaValidaParaTipoInvalido() {
        EspecialistaTerrestre especialista = new EspecialistaTerrestre(BigDecimal.valueOf(2000), null, null);
        Assert.assertFalse(especialista.licencaValidaPara("Outro"));
    }
    @Test
    public void testSetValidadeCaminhao() {
        EspecialistaTerrestre especialista = new EspecialistaTerrestre(BigDecimal.valueOf(2000), null, null);
        LocalDate novaValidade = LocalDate.now().plusDays(15);
        especialista.setValidadeCaminhao(novaValidade);
        Assert.assertEquals(novaValidade, especialista.getValidadeCaminhao());
    }
    @Test
    public void testLicencaValidaParaTipoNulo() {
        EspecialistaTerrestre especialista = new EspecialistaTerrestre(BigDecimal.valueOf(2000), null, null);
        Assert.assertFalse(especialista.licencaValidaPara(null));
    }
    @Test
    public void testLicencaValidaParaDataLimite() {
        LocalDate validadeAmanha = LocalDate.now().plusDays(1);
        EspecialistaTerrestre especialista = new EspecialistaTerrestre(BigDecimal.valueOf(2000), validadeAmanha, validadeAmanha);
        Assert.assertTrue(especialista.licencaValidaPara("Caminhao"));
        Assert.assertTrue(especialista.licencaValidaPara("Tanque"));
    }
    @Test
    public void testConstrutorComValoresParciais() {
        LocalDate validadeCaminhao = LocalDate.now().plusDays(15);
        EspecialistaTerrestre especialista = new EspecialistaTerrestre(BigDecimal.valueOf(2000), validadeCaminhao, null);
        Assert.assertEquals(validadeCaminhao, especialista.getValidadeCaminhao());
        Assert.assertEquals(LocalDate.now().plusDays(30), especialista.getValidadeTanque());
    }
    @Test
    public void testLicencaValidaParaDataAtual() {
        LocalDate validadeHoje = LocalDate.now();
        EspecialistaTerrestre especialista = new EspecialistaTerrestre(BigDecimal.valueOf(2000), validadeHoje, validadeHoje);
        Assert.assertTrue(especialista.licencaValidaPara("Caminhao"));
        Assert.assertTrue(especialista.licencaValidaPara("Tanque"));
    }
    @Test
    public void testValoresPadraoSaoConsistentes() {
        EspecialistaTerrestre especialista = new EspecialistaTerrestre(BigDecimal.valueOf(2000), null, null);
        LocalDate esperado = LocalDate.now().plusDays(30);
        Assert.assertEquals(esperado, especialista.getValidadeCaminhao());
        Assert.assertEquals(esperado, especialista.getValidadeTanque());
    }
    @Test
    public void testConstrutorComDatasExtremas() {
        LocalDate validadeFutura = LocalDate.now().plusYears(100);
        LocalDate validadePassada = LocalDate.now().minusYears(100);
        EspecialistaTerrestre especialista = new EspecialistaTerrestre(BigDecimal.valueOf(2000), validadeFutura, validadePassada);
        Assert.assertEquals(validadeFutura, especialista.getValidadeCaminhao());
        Assert.assertEquals(validadePassada, especialista.getValidadeTanque());
    }
    @Test
    public void testConstrutorComSalarioNegativo() {
        EspecialistaTerrestre especialista = new EspecialistaTerrestre(BigDecimal.valueOf(-1000), null, null);
        Assert.assertEquals(BigDecimal.valueOf(-1000), especialista.getSalario());
    }
    @Test
    public void testLicencaCaminhaoComValidadeNula() {
        EspecialistaTerrestre especialista = new EspecialistaTerrestre();
        especialista.setValidadeCaminhao(null); // define explicitamente null
        especialista.setValidadeTanque(LocalDate.now().plusDays(10));
        Assert.assertFalse(especialista.licencaValidaPara("Caminhao"));
        Assert.assertTrue(especialista.licencaValidaPara("Tanque"));
    }
    @Test
    public void testLicencaTanqueComValidadeNula() {
        EspecialistaTerrestre especialista = new EspecialistaTerrestre();
        especialista.setValidadeTanque(null);
        especialista.setValidadeCaminhao(LocalDate.now().plusDays(10));
        Assert.assertFalse(especialista.licencaValidaPara("Tanque"));
        Assert.assertTrue(especialista.licencaValidaPara("Caminhao"));
    }
}
