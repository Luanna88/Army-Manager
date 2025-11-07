import org.crescer.militar.Militar;
import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;

public class MilitarTest {
    @Test
    public void testConstrutorComSalarioValido() {
        Militar militar = new Militar(new BigDecimal("5000.00"));
        Assert.assertEquals(new BigDecimal("5000.00"), militar.getSalario());
    }
    @Test
    public void testConstrutorComSalarioNulo() {
        Militar militar = new Militar(null);
        Assert.assertEquals(BigDecimal.ZERO, militar.getSalario());
    }
    @Test
    public void testConstrutorPadrao() {
        Militar militar = new Militar();
        Assert.assertEquals(BigDecimal.ZERO, militar.getSalario());
    }
    @Test
    public void testSetSalarioComValorValido() {
        Militar militar = new Militar();
        militar.setSalario(new BigDecimal("3000.00"));
        Assert.assertEquals(new BigDecimal("3000.00"), militar.getSalario());
    }
    @Test
    public void testSetSalarioComValorNuloOuNegativo() {
        Militar militar = new Militar();
        Exception exceptionNulo = Assert.assertThrows(
                IllegalArgumentException.class,
                () -> militar.setSalario(null)
        );
        Assert.assertEquals(
                "O salário deve ser um valor não nulo e não negativo.",
                exceptionNulo.getMessage()
        );
        BigDecimal salarioNegativo = new BigDecimal("-100.00");
        Exception exceptionNegativo = Assert.assertThrows(
                IllegalArgumentException.class,
                () -> militar.setSalario(salarioNegativo)
        );
        Assert.assertEquals(
                "O salário deve ser um valor não nulo e não negativo.",
                exceptionNegativo.getMessage()
        );
    }
    @Test
    public void testSetSalarioComValorZero() {
        Militar militar = new Militar();
        militar.setSalario(BigDecimal.ZERO);
        Assert.assertEquals(BigDecimal.ZERO, militar.getSalario());
    }
}
