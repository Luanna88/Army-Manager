import org.crescer.militar.Elite;
import org.crescer.militar.EspecialistaTerrestre;
import org.crescer.militar.Militar;
import org.crescer.militar.Piloto;
import org.crescer.militar.PilotoTanque;
import org.crescer.veiculo.Tanque;
import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TanqueTest {
    @Test
    public void testTanqueConstrutorComPilotoValido() {
        Piloto pilotoValido = new PilotoTanque();
        List<Militar> tripulacao = Arrays.asList(new Militar(), new Militar(), new Militar());
        Tanque tanque = new Tanque(pilotoValido, tripulacao, 10.0, BigDecimal.valueOf(5.0));
        Assert.assertNotNull(tanque);
    }
    @Test
    public void testTanqueConstrutorComPilotoInvalido() {
        Piloto pilotoInvalido = new Piloto();
        List<Militar> tripulacao = Arrays.asList(new Militar(), new Militar(), new Militar());
        BigDecimal preco = BigDecimal.valueOf(5.0);
        Assert.assertThrows(IllegalArgumentException.class,
                () -> new Tanque(pilotoInvalido, tripulacao, 10.0, preco));
    }

    @Test
    public void testTripulacaoValida() {
        Piloto piloto = new PilotoTanque();
        piloto.licencaValida();
        List<Militar> tripulacao = Arrays.asList(new Militar(), new Militar(), new Militar());
        Tanque tanque = new Tanque(piloto, tripulacao, 10.0, BigDecimal.valueOf(5.0));
        Assert.assertTrue(tanque.tripulacaoValida());
    }
    @Test
    public void testTripulacaoInvalidaPorLicenca() {
        PilotoTanque piloto = new PilotoTanque();
        piloto.setValidadeLicenca(LocalDate.now().minusDays(1));
        List<Militar> tripulacao = Arrays.asList(new Militar(), new Militar(), new Militar());
        Tanque tanque = new Tanque(piloto, tripulacao, 10.0, BigDecimal.valueOf(5.0));
        Assert.assertFalse(tanque.tripulacaoValida());
    }
    @Test
    public void testConstrutorComParametrosNulos() {
        Assert.assertThrows(NullPointerException.class,
                () -> new Tanque(null, null, 0.0, null));
    }
    @Test
    public void testTanqueConstrutorComPilotoEspecialistaTerrestre() {
        Piloto pilotoEspecialista = new EspecialistaTerrestre();
        List<Militar> tripulacao = Arrays.asList(new Militar(), new Militar(), new Militar());
        Tanque tanque = new Tanque(pilotoEspecialista, tripulacao, 10.0, BigDecimal.valueOf(5.0));
        Assert.assertNotNull(tanque);
    }
    @Test
    public void testTanqueConstrutorComPilotoElite() {
        Elite pilotoElite = new Elite(
                BigDecimal.valueOf(5000.0),
                LocalDate.now().plusYears(1),
                LocalDate.now().plusYears(1),
                LocalDate.now().plusYears(1),
                LocalDate.now().plusYears(1)
        );
        List<Militar> tripulacao = Arrays.asList(new Militar(), new Militar(), new Militar());
        Tanque tanque = new Tanque(pilotoElite, tripulacao, 10.0, BigDecimal.valueOf(5.0));
        Assert.assertNotNull(tanque);
    }
    @Test
    public void testTripulacaoInvalidaPorQuantidade() {
        PilotoTanque piloto = new PilotoTanque();
        piloto.setValidadeLicenca(LocalDate.now().plusDays(1));
        List<Militar> tripulacao = Arrays.asList(new Militar(), new Militar());
        Tanque tanque = new Tanque(piloto, tripulacao, 10.0, BigDecimal.valueOf(5.0));
        Assert.assertFalse(tanque.tripulacaoValida());
    }
}
