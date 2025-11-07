import org.crescer.militar.Piloto;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;

public class PilotoTest {
    @Test
    public void testLicencaValidaQuandoExpirada() {
        Piloto piloto = new Piloto();
        piloto.setValidadeLicenca(LocalDate.now().minusDays(1));
        Assert.assertFalse(piloto.licencaValida());
    }
    @Test
    public void testLicencaValidaQuandoFutura() {
        Piloto piloto = new Piloto();
        piloto.setValidadeLicenca(LocalDate.now().plusDays(1));
        Assert.assertTrue(piloto.licencaValida());
    }

    @Test
    public void testSetValidadeLicencaNulaLancaExcecao() {
        Piloto piloto = new Piloto();
        Exception exception = Assert.assertThrows(
                IllegalArgumentException.class,
                () -> piloto.setValidadeLicenca(null)
        );
        Assert.assertEquals("A validade da licença não pode ser nula", exception.getMessage());
    }
    @Test
    public void testLicencaValidaQuandoCampoInternoNulo() {
        Piloto piloto = new Piloto();
        Piloto pilotoNull = new Piloto() {
            {
                this.validadeLicenca = null;
            }
        };
        Assert.assertFalse(pilotoNull.licencaValida());
    }
}
