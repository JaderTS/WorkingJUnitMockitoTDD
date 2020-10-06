package br.ce.wcaquino;

import br.ce.wcaquino.Exception.FilmeSemEstoqueExcetion;
import br.ce.wcaquino.Exception.LocadoraExcetion;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Date;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LocacaoServiceTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();
    private Object nul;

    @Test
    public void testLocacao() throws Exception {
        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Fime 1", 2, 5.0);

        //acao
        Locacao locacao = service.alugarFilme(usuario,filme);

            //verificacao
            assertTrue(locacao.getValor() == 5.0);
            Assert.assertEquals(5.0, locacao.getValor(), 0.01);
            assertTrue(isMesmaData(locacao.getDataLocacao(), new Date()));
            assertTrue(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)));

            assertThat(locacao.getValor(), is(equalTo(5.0))) ;
            assertThat(locacao.getValor(), is(not(6.0))) ;
            assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
            assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));

            error.checkThat(locacao.getValor(), is(equalTo(5.0)));
            error.checkThat(locacao.getValor(), is(not(6.0))) ;
            error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
            error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
    }

    //Forma elegante
    @Test(expected = FilmeSemEstoqueExcetion.class)
    public void testLocacao_filmeSemEstoque() throws Exception {
        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Fime 1", 0, 5.0);

        //acao
        service.alugarFilme(usuario,filme);
    }

    //Robusta
    @Test
    public void testLocacao_usuarioVazio() throws FilmeSemEstoqueExcetion{
       //cenario
       LocacaoService service = new LocacaoService();
       Filme filme = new Filme("Filme 2", 0, 4.0);

       //acao
        try {
            service.alugarFilme(null, filme);
        } catch (LocadoraExcetion e) {
            e.printStackTrace();
        }


    }
}
