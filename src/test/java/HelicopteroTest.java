import org.crescer.militar.Elite;
import org.crescer.militar.EspecialistaDoAr;
import org.crescer.militar.Militar;
import org.crescer.militar.Piloto;
import org.crescer.militar.PilotoHelicoptero;
import org.crescer.veiculo.Helicoptero;
import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class HelicopteroTest {
    @Test
    public void PilotoHelicopteroTest() {
        BigDecimal salario = new BigDecimal("5000.00");
        LocalDate validadeLicenca = LocalDate.of(2025, 12, 31);
        PilotoHelicoptero piloto = new PilotoHelicoptero(salario, validadeLicenca);
        Assert.assertEquals(salario, piloto.getSalario());
        Assert.assertEquals(validadeLicenca, piloto.getValidadeLicenca());
    }
    @Test
    public void testHelicopteroComPilotoValido() {
        Piloto piloto = new PilotoHelicoptero();
        List<Militar> tripulacao = Arrays.asList(new Militar(BigDecimal.valueOf(2500)), new Militar(BigDecimal.valueOf(2500)));
        Helicoptero helicoptero = new Helicoptero(piloto, tripulacao, 5.0, BigDecimal.valueOf(10));
        Assert.assertNotNull(helicoptero);
    }
    @Test
    public void testHelicopteroComPilotoInvalido() {
        Piloto piloto = new Piloto();
        List<Militar> tripulacao = Arrays.asList(
                new Militar(BigDecimal.valueOf(2500)),
                new Militar(BigDecimal.valueOf(2500))
        );
        BigDecimal preco = BigDecimal.valueOf(10);
        IllegalArgumentException exception = Assert.assertThrows(
                IllegalArgumentException.class,
                () -> new Helicoptero(piloto, tripulacao, 5.0, preco)
        );
        Assert.assertEquals(
                "O piloto deve ser uma das seguintes especializações: Elite, EspecialistaDoAr ou PilotoHelicoptero.",
                exception.getMessage()
        );
    }
    @Test
    public void testHelicopteroTripulacaoValida() {
        Piloto piloto = new PilotoHelicoptero() {
            @Override
            public boolean licencaValida() {
                return true;
            }
        };
        List<Militar> tripulacao = Arrays.asList(new Militar(BigDecimal.valueOf(2500)), new Militar(BigDecimal.valueOf(2500)));
        Helicoptero helicoptero = new Helicoptero(piloto, tripulacao, 5.0, BigDecimal.valueOf(10));
        Assert.assertTrue("A validação da tripulação falhou, mas deveria ser verdadeira.", helicoptero.tripulacaoValida());
    }
    @Test
    public void testTripulacaoValida() {
        Piloto pilotoValido = new PilotoHelicoptero();
        List<Militar> tripulacao = Arrays.asList(new Militar(), new Militar());
        BigDecimal preco = new BigDecimal("100.0");
        Helicoptero helicoptero = new Helicoptero(pilotoValido, tripulacao, 10.0, preco);
        Assert.assertTrue(helicoptero.tripulacaoValida());
        Piloto pilotoInvalido = new Piloto();
        Assert.assertThrows(
                IllegalArgumentException.class,
                () -> new Helicoptero(pilotoInvalido, tripulacao, 10.0, preco)
        );
    }
    @Test
    public void testHelicopteroComTripulacaoNula() {
        Piloto piloto = new PilotoHelicoptero();
        BigDecimal preco = BigDecimal.valueOf(10);
        IllegalArgumentException exception = Assert.assertThrows(
                IllegalArgumentException.class,
                () -> new Helicoptero(piloto, null, 5.0, preco)
        );
        Assert.assertEquals(
                "A tripulação não pode ser nula, vazia ou exceder o limite permitido.",
                exception.getMessage()
        );
    }
    @Test
    public void testHelicopteroComTripulacaoExcedendoLimite() {
        Piloto piloto = new PilotoHelicoptero();
        List<Militar> tripulacaoGrande = Arrays.asList(
                new Militar(), new Militar(), new Militar(), new Militar(), new Militar(),
                new Militar(), new Militar(), new Militar(), new Militar(), new Militar(), new Militar()
        );
        BigDecimal preco = BigDecimal.valueOf(10);
        IllegalArgumentException exception = Assert.assertThrows(
                IllegalArgumentException.class,
                () -> new Helicoptero(piloto, tripulacaoGrande, 5.0, preco)
        );
        Assert.assertEquals(
                "A tripulação não pode ser nula, vazia ou exceder o limite permitido.",
                exception.getMessage()
        );
    }
    @Test
    public void testPilotoComLicencaInvalida() {
        Piloto piloto = new PilotoHelicoptero() {
            @Override
            public boolean licencaValida() {
                return false;
            }
        };
        List<Militar> tripulacao = Arrays.asList(new Militar(BigDecimal.valueOf(2500)), new Militar(BigDecimal.valueOf(2500)));
        Helicoptero helicoptero = new Helicoptero(piloto, tripulacao, 5.0, BigDecimal.valueOf(10));
        Assert.assertFalse("A validação da tripulação falhou: deveria ser falsa devido à licença inválida do piloto.", helicoptero.tripulacaoValida());
    }
    @Test
    public void testHelicopteroComTripulacaoVazia() {
        Piloto piloto = new PilotoHelicoptero();
        List<Militar> tripulacaoVazia = Arrays.asList();
        BigDecimal preco = BigDecimal.valueOf(10);
        IllegalArgumentException exception = Assert.assertThrows(
                IllegalArgumentException.class,
                () -> new Helicoptero(piloto, tripulacaoVazia, 5.0, preco)
        );
        Assert.assertEquals(
                "A tripulação não pode ser nula, vazia ou exceder o limite permitido.",
                exception.getMessage()
        );
    }
    @Test
    public void testHelicopteroComValoresNegativos() {
        Piloto piloto = new PilotoHelicoptero();
        List<Militar> tripulacao = Arrays.asList(new Militar(), new Militar());
        BigDecimal precoPositivo = BigDecimal.valueOf(10);
        BigDecimal precoNegativo = BigDecimal.valueOf(-10);
        double quilometragemNegativa = -5.0;
        double quilometragemPositiva = 5.0;
        IllegalArgumentException exceptionQuilometragem = Assert.assertThrows(
                IllegalArgumentException.class,
                () -> new Helicoptero(piloto, tripulacao, quilometragemNegativa, precoPositivo)
        );
        Assert.assertEquals(
                "A quilometragem por litro não pode ser negativa ou zero.",
                exceptionQuilometragem.getMessage()
        );
        IllegalArgumentException exceptionPreco = Assert.assertThrows(
                IllegalArgumentException.class,
                () -> new Helicoptero(piloto, tripulacao, quilometragemPositiva, precoNegativo)
        );
        Assert.assertEquals(
                "O preço por litro não pode ser negativo ou zero.",
                exceptionPreco.getMessage()
        );
    }
    @Test
    public void testTripulacaoValidaComPilotoInvalido() {
        Piloto pilotoInvalido = new PilotoHelicoptero() {
            @Override
            public boolean licencaValida() {
                return false;
            }
        };
        List<Militar> tripulacao = Arrays.asList(new Militar(), new Militar());
        Helicoptero helicoptero = new Helicoptero(pilotoInvalido, tripulacao, 5.0, BigDecimal.valueOf(10));
        Assert.assertFalse("A validação da tripulação deveria falhar devido ao piloto inválido.", helicoptero.tripulacaoValida());
    }
    @Test
    public void testHelicopteroComPilotoElite() {
        BigDecimal salario = BigDecimal.valueOf(8000);
        LocalDate validadeAviao = LocalDate.now().plusYears(1);
        LocalDate validadeCaminhao = LocalDate.now().plusYears(1);
        LocalDate validadeHelicoptero = LocalDate.now().plusYears(1);
        LocalDate validadeTanque = LocalDate.now().plusYears(1);
        Elite piloto = new Elite(salario, validadeAviao, validadeCaminhao, validadeHelicoptero, validadeTanque);
        List<Militar> tripulacao = Arrays.asList(new Militar(BigDecimal.valueOf(2000)), new Militar(BigDecimal.valueOf(2000)));
        Helicoptero helicoptero = new Helicoptero(piloto, tripulacao, 6.0, BigDecimal.valueOf(15));
        Assert.assertNotNull("Helicóptero com piloto Elite deveria ser válido.", helicoptero);
    }
    @Test
    public void testHelicopteroComPilotoEspecialistaDoAr() {
        BigDecimal salario = BigDecimal.valueOf(7000);
        LocalDate validadeAviao = LocalDate.now().plusYears(1);
        EspecialistaDoAr piloto = new EspecialistaDoAr(salario, validadeAviao);
        List<Militar> tripulacao = Arrays.asList(new Militar(BigDecimal.valueOf(2500)), new Militar(BigDecimal.valueOf(2500)));
        Helicoptero helicoptero = new Helicoptero(piloto, tripulacao, 5.5, BigDecimal.valueOf(12));
        Assert.assertNotNull("Helicóptero com piloto EspecialistaDoAr deveria ser válido.", helicoptero);
    }
    @Test
    public void testTripulacaoValidaComPilotoInvalidoETriupulacaoInvalida() {
        Piloto pilotoInvalido = new PilotoHelicoptero() {
            @Override
            public boolean licencaValida() {
                return false;
            }
        };
        List<Militar> tripulacaoValida = Arrays.asList(new Militar(), new Militar());
        Helicoptero helicoptero = new Helicoptero(pilotoInvalido, tripulacaoValida, 5.0, BigDecimal.valueOf(10));
        helicoptero.setTripulacao(Arrays.asList());
        Assert.assertFalse("Deveria retornar false quando piloto e tripulação são inválidos", helicoptero.tripulacaoValida());
    }
    @Test
    public void testTripulacaoInvalidaComPilotoValido() {
        Piloto pilotoValido = new PilotoHelicoptero() {
            @Override
            public boolean licencaValida() {
                return true;
            }
        };
        List<Militar> tripulacaoInvalida = Arrays.asList();
        Helicoptero helicoptero = new Helicoptero(pilotoValido, Arrays.asList(new Militar()), 5.0, BigDecimal.valueOf(10));
        helicoptero.setTripulacao(tripulacaoInvalida);
        Assert.assertFalse("Deveria retornar false quando a tripulação é inválida, mesmo com piloto válido", helicoptero.tripulacaoValida());
    }
}
