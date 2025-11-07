import org.crescer.militar.Elite;
import org.crescer.militar.EspecialistaTerrestre;
import org.crescer.militar.Militar;
import org.crescer.militar.Piloto;
import org.crescer.militar.PilotoCaminhao;
import org.crescer.veiculo.Caminhao;
import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CaminhaoTest {
    @Test
    public void testPilotoCaminhaoGetters() {
        BigDecimal salario = new BigDecimal("3000.00");
        LocalDate validadeLicenca = LocalDate.of(2025, 12, 31);
        PilotoCaminhao piloto = new PilotoCaminhao(salario, validadeLicenca);
        Assert.assertEquals(salario, piloto.getSalario());
        Assert.assertEquals(validadeLicenca, piloto.getValidadeLicenca());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testConstrutorPilotoInvalido() {
        Piloto pilotoInvalido = new Piloto(new BigDecimal("5000"), null);
        new Caminhao(pilotoInvalido, Collections.emptyList(), 5.0, new BigDecimal("4.50"));
    }
    @Test
    public void testConstrutorPilotoValido() {
        LocalDate validadeLicencaCaminhao = LocalDate.now().plusDays(10);
        LocalDate validadeLicencaTanque = LocalDate.now().plusDays(15);
        EspecialistaTerrestre pilotoValido = new EspecialistaTerrestre(new BigDecimal("5000"), validadeLicencaCaminhao, validadeLicencaTanque);
        Caminhao caminhao = new Caminhao(pilotoValido, Collections.emptyList(), 5.0, new BigDecimal("4.50"));
        Assert.assertNotNull(caminhao);
    }
    @Test
    public void testPilotoCaminhaoTripulacaoValida() {
        PilotoCaminhao pilotoCaminhao = new PilotoCaminhao(new BigDecimal("4500"), LocalDate.now().plusDays(10));
        List<Militar> tripulacao = Arrays.asList(new Militar(new BigDecimal("3000")), new Militar(new BigDecimal("3000")), new Militar(new BigDecimal("3000")), new Militar(new BigDecimal("3000")), new Militar(new BigDecimal("3000")));
        Caminhao caminhao = new Caminhao(pilotoCaminhao, tripulacao, 5.0, new BigDecimal("4.50"));
        Assert.assertTrue(caminhao.tripulacaoValida());
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
    public void testConstrutorComPilotoValido() {
        Piloto pilotoValido = new EspecialistaTerrestre();
        List<Militar> tripulacao = Arrays.asList(new Militar(), new Militar());
        double quilometragemPorLitro = 10.0;
        BigDecimal precoPorLitro = BigDecimal.valueOf(5.0);
        Caminhao caminhao = new Caminhao(pilotoValido, tripulacao, quilometragemPorLitro, precoPorLitro);
        Assert.assertNotNull(caminhao);
    }
    @Test
    public void testConstrutorComPilotoInvalido() {
        Piloto pilotoInvalido = new Piloto();
        List<Militar> tripulacao = Arrays.asList(new Militar(), new Militar());
        double quilometragemPorLitro = 10.0;
        BigDecimal precoPorLitro = BigDecimal.valueOf(5.0);
        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> {new Caminhao(pilotoInvalido, tripulacao, quilometragemPorLitro, precoPorLitro);});
        Assert.assertEquals("Piloto inválido para Caminhão.", exception.getMessage());
    }
    @Test
    public void testTripulacaoValidaComEspecialistaTerrestre() {
        EspecialistaTerrestre piloto = new EspecialistaTerrestre();
        piloto.licencaValidaPara("Caminhao");
        List<Militar> tripulacao = Arrays.asList(new Militar(), new Militar(), new Militar(), new Militar(), new Militar());
        Caminhao caminhao = new Caminhao(piloto, tripulacao, 10.0, BigDecimal.valueOf(5.0));
        Assert.assertTrue(caminhao.tripulacaoValida());
    }
    @Test
    public void testTripulacaoInvalidaComTripulacaoPequena() {
        EspecialistaTerrestre piloto = new EspecialistaTerrestre();
        piloto.licencaValidaPara("Caminhao");
        List<Militar> tripulacao = Arrays.asList(new Militar());
        Caminhao caminhao = new Caminhao(piloto, tripulacao, 10.0, BigDecimal.valueOf(5.0));
        Assert.assertFalse(caminhao.tripulacaoValida());
    }
    @Test
    public void testTripulacaoInvalidaComLicencaInvalida() {
        EspecialistaTerrestre piloto = new EspecialistaTerrestre();
        piloto.setValidadeCaminhao(LocalDate.now().minusDays(1));
        List<Militar> tripulacao = Arrays.asList(new Militar(), new Militar(), new Militar(), new Militar(), new Militar());
        Caminhao caminhao = new Caminhao(piloto, tripulacao, 10.0, BigDecimal.valueOf(5.0));
        Assert.assertFalse("A tripulação deve ser inválida devido à licença expirada do piloto.", caminhao.tripulacaoValida());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testConstrutorComPilotoNull() {
        new Caminhao(null, Collections.emptyList(), 8.0, BigDecimal.valueOf(5.0));
    }
    @Test
    public void testTripulacaoValidaComPilotoEliteRetornaFalse() {
        LocalDate futuro = LocalDate.now().plusDays(10);
        Elite pilotoElite = new Elite(new BigDecimal("7000"), futuro, futuro, futuro, futuro);
        List<Militar> tripulacao = Arrays.asList(new Militar(new BigDecimal("3000")), new Militar(new BigDecimal("3000")), new Militar(new BigDecimal("3000")), new Militar(new BigDecimal("3000")), new Militar(new BigDecimal("3000")));
        Caminhao caminhao = new Caminhao(pilotoElite, tripulacao, 8.0, new BigDecimal("5.0"));
        Assert.assertFalse("Deve retornar false para piloto do tipo Elite.", caminhao.tripulacaoValida());
    }
    @Test
    public void testTripulacaoValidaCom30Militares() {
        PilotoCaminhao piloto = new PilotoCaminhao(new BigDecimal("4500"), LocalDate.now().plusDays(10));
        List<Militar> tripulacao = Collections.nCopies(30, new Militar(new BigDecimal("3000")));
        Caminhao caminhao = new Caminhao(piloto, tripulacao, 5.0, new BigDecimal("4.50"));
        Assert.assertTrue("A tripulação deve ser válida com exatamente 30 militares.", caminhao.tripulacaoValida());
    }
    @Test
    public void testTripulacaoInvalidaComMaisDe30Militares() {
        PilotoCaminhao piloto = new PilotoCaminhao(new BigDecimal("4500"), LocalDate.now().plusDays(10));
        List<Militar> tripulacao = Collections.nCopies(31, new Militar(new BigDecimal("3000")));
        Caminhao caminhao = new Caminhao(piloto, tripulacao, 5.0, new BigDecimal("4.50"));
        Assert.assertFalse("A tripulação deve ser inválida com mais de 30 militares.", caminhao.tripulacaoValida());
    }
    @Test
    public void testTripulacaoInvalidaComMaisDe30MilitaresParaEspecialistaTerrestre() {
        LocalDate validadeCaminhao = LocalDate.now().plusDays(10);
        LocalDate validadeTanque = LocalDate.now().plusDays(15);
        EspecialistaTerrestre piloto = new EspecialistaTerrestre(new BigDecimal("5000"), validadeCaminhao, validadeTanque);
        List<Militar> tripulacao = Collections.nCopies(31, new Militar(new BigDecimal("3000")));
        Caminhao caminhao = new Caminhao(piloto, tripulacao, 8.0, new BigDecimal("5.0"));
        Assert.assertFalse("A tripulação deve ser inválida com mais de 30 militares.", caminhao.tripulacaoValida());
    }
    @Test
    public void testTripulacaoInvalidaComPilotoCaminhaoLicencaExpirada() {
        PilotoCaminhao pilotoCaminhao = new PilotoCaminhao(new BigDecimal("4500"), LocalDate.now().minusDays(1));
        List<Militar> tripulacao = Arrays.asList(new Militar(new BigDecimal("3000")), new Militar(new BigDecimal("3000")), new Militar(new BigDecimal("3000")), new Militar(new BigDecimal("3000")), new Militar(new BigDecimal("3000")));
        Caminhao caminhao = new Caminhao(pilotoCaminhao, tripulacao, 5.0, new BigDecimal("4.50"));
        Assert.assertFalse("Deve retornar false quando a licença do piloto estiver expirada.", caminhao.tripulacaoValida());
    }
    @Test
    public void testTripulacaoInvalidaComMenosDe5MilitaresPilotoCaminhao() {
        PilotoCaminhao piloto = new PilotoCaminhao(new BigDecimal("4000"), LocalDate.now().plusDays(10));
        List<Militar> tripulacao = Arrays.asList(new Militar(new BigDecimal("3000")), new Militar(new BigDecimal("3000")), new Militar(new BigDecimal("3000")), new Militar(new BigDecimal("3000")));
        Caminhao caminhao = new Caminhao(piloto, tripulacao, 5.0, new BigDecimal("4.50"));
        Assert.assertFalse("A tripulação deve ser inválida com menos de 5 militares.", caminhao.tripulacaoValida());
    }
}
