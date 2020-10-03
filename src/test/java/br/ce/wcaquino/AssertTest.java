package br.ce.wcaquino;

import br.ce.wcaquino.entidades.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class AssertTest {
    @Test
    public void test(){
        Assert.assertTrue(true);
        Assert.assertFalse(false);

        //Assert.assertEquals("Erro na compara��o",1,2);
        Assert.assertEquals(1,1);
        Assert.assertEquals(0.51,0.59265656,0.09);
        Assert.assertEquals(Math.PI, 3.14, 0.01);

        int i=5;
        Integer i2=5;
        Assert.assertEquals(Integer.valueOf(i),i2);
        Assert.assertEquals(i, i2.intValue());

        Assert.assertEquals("bola", "bola");
        Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
        Assert.assertTrue("bola".startsWith("bo"));

        Usuario u1 = new Usuario("usu�rio 1");
        Usuario u2 = new Usuario("usu�rio 1");
        Usuario u3 = null;

        Assert.assertEquals(u1,u2);

        Assert.assertSame(u2, u2);
        Assert.assertNotSame(u1,u3);;

        Assert.assertNull(u3);
        Assert.assertNotNull(u1);
    }
}