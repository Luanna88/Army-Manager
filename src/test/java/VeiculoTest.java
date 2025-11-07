import org.crescer.militar.Piloto;
import org.crescer.veiculo.Veiculo;
import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.Collections;

public class VeiculoTest {
    static class VeiculoConcreto extends Veiculo {
        public VeiculoConcreto(Piloto piloto) {
            super(piloto, Collections.emptyList(), 10.0, BigDecimal.TEN);
        }
        @Override
        public boolean tripulacaoValida() {
            return true;
        }
    }
    @Test
    public void testSetPiloto() {
        Piloto piloto1 = new Piloto();
        Piloto piloto2 = new Piloto();
        Veiculo veiculo = new VeiculoConcreto(piloto1);
        veiculo.setPiloto(piloto2);
        Assert.assertEquals(piloto2, veiculo.getPiloto());
    }
}
