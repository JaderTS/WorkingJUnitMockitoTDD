package br.ce.wcaquino;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.*;

public class LocacaoServiceTest {
    @Test
    public void Test() {
        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Fime 1", 2, 5.0);

        //acao
        Locacao locacao = service.alugarFilme(usuario,filme);

        //verificacao
        Assert.assertTrue(locacao.getValor() == 5.0);
        Assert.assertEquals(5.0, locacao.getValor(), 0.01);
        Assert.assertTrue(isMesmaData(locacao.getDataLocacao(), new Date()));
        Assert.assertTrue(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)));

        Assert.assertThat(locacao.getValor(), is(equalTo(5.0))) ;
        Assert.assertThat(locacao.getValor(), is(not(6.0))) ;
        Assert.assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        Assert.assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
    }
}
