package br.ce.wcaquino.servicos;

import java.util.Date;
import br.ce.wcaquino.Exception.FilmeSemEstoqueExcetion;
import br.ce.wcaquino.Exception.LocadoraExcetion;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.ws.Service;

import static br.ce.wcaquino.utils.DataUtils.*;

public class LocacaoService {

    public Locacao alugarFilme(Usuario usuario, Filme filme) throws FilmeSemEstoqueExcetion, LocadoraExcetion{

        if (usuario == null) {
            throw new LocadoraExcetion("Usuario vazio");
        }

        if (filme == null) {
            throw new LocadoraExcetion("Filme vazio");
        }

        if (filme.getEstoque() == 0) {
            throw new FilmeSemEstoqueExcetion();
        }
        Locacao locacao = new Locacao();
        locacao.setFilme(filme);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(new Date());
        locacao.setValor(filme.getPrecoLocacao());

        //Entrega no dia seguinte
        Date dataEntrega = new Date();
        dataEntrega = adicionarDias(dataEntrega, 1);
        locacao.setDataRetorno(dataEntrega);

        //Salvando a locacao...

        return locacao;
    }
}