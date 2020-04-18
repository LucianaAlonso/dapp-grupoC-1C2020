package dominio;

import builders.ComercioBuilder;
import model.Comercio;
import model.excepciones.ProductoInexistenteEnComercioException;
import model.excepciones.ProductoRepetidoEnComercioException;
import org.junit.Test;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class MercaderiaEnComercioTest {

    @Test
    public void unComercioInicialmenteNoTieneNingunProducto() {
        Comercio comercioNuevo = ComercioBuilder.unComercio().build();
        assertFalse(comercioNuevo.tieneProductos());
    }

   @Test
    public void cuandoUnComercioAgregaUnProductoEstePasaAFormarParteDeSuListaDeMercaderiasConElStockYPrecioIndicados() {
        Comercio comercio = ComercioBuilder.unComercio().build();
        comercio.agregarMercaderia("Fideos", "Marolio", 34.45, 23);
        assertTrue(comercio.vendeProducto("Fideos", "Marolio"));
        assertEquals(comercio.stockPara("Fideos", "Marolio"), 23);
        assertEquals(comercio.precioPara("Fideos", "Marolio"), 34.45);
    }

    @Test
    public void unComercioNoConoceElPrecioDeUnProductoQueNoTiene() {
        Comercio comercio = ComercioBuilder.unComercio().build();
        assertThrows(ProductoInexistenteEnComercioException.class, () -> comercio.precioPara("Un producto", "Que no existe"));
    }

    @Test
    public void unComercioNoConoceElStockDeUnProductoQueNoTiene() {
        Comercio comercio = ComercioBuilder.unComercio().build();
        assertThrows(ProductoInexistenteEnComercioException.class, () -> comercio.stockPara("Un producto", "Que no existe"));
    }

    @Test
    public void unComercioNoPuedeAgregarElMismoProductoMasDeUnaVez() {
        //por mismo producto se entiende mismo nombre y marca
        Comercio comercio = ComercioBuilder.unComercio().build();
        comercio.agregarMercaderia("Fideos", "Marolio", 34.45, 23);
        assertThrows(ProductoRepetidoEnComercioException.class, () -> comercio.agregarMercaderia("Fideos", "Marolio", 34.45, 23));
    }

    @Test
    public void unComercioPuedeActualizarElPrecioDeUnProductoExistente() {
        Comercio comercio = ComercioBuilder.unComercio().build();
        comercio.agregarMercaderia("Fideos", "Marolio", 34.45, 23);
        comercio.actualizarPrecio("Fideos", "Marolio", 36.45);
        assertEquals(comercio.precioPara("Fideos", "Marolio"), 36.45);
    }

    @Test
    public void unComercioNoPuedeActualizarElPrecioDeUnProductoInexistente() {
        Comercio comercio = ComercioBuilder.unComercio().build();
        assertThrows(ProductoInexistenteEnComercioException.class, () -> comercio.actualizarPrecio("Fideos", "Marolio", 36.45));
    }

    @Test
    public void unComercioPuedeAgregarStockParaUnProductoExistente() {
        Comercio comercio = ComercioBuilder.unComercio().build();
        comercio.agregarMercaderia("Fideos", "Marolio", 34.45, 23);
        comercio.agregarStock("Fideos", "Marolio", 20);
        assertEquals(comercio.stockPara("Fideos", "Marolio"), 43);
    }


    @Test
    public void unComercioNoPuedeAgregarStockParaUnProductoInexistente() {
        Comercio comercio = ComercioBuilder.unComercio().build();
        assertThrows(ProductoInexistenteEnComercioException.class, () -> comercio.agregarStock("Fideos", "Marolio", 10));
    }

    @Test
    public void unComercioPuedeDecrementarStockParaUnProductoExistente() {
        Comercio comercio = ComercioBuilder.unComercio().build();
        comercio.agregarMercaderia("Fideos", "Marolio", 34.45, 23);
        comercio.decrementarStock("Fideos", "Marolio", 20);
        assertEquals(comercio.stockPara("Fideos", "Marolio"), 3);
    }

    @Test
    public void unComercioNoPuedeDecrementarStockParaUnProductoInexistente() {
        Comercio comercio = ComercioBuilder.unComercio().build();
        assertThrows(ProductoInexistenteEnComercioException.class, () -> comercio.decrementarStock("Fideos", "Marolio", 10));
    }

    @Test
    public void testPruebaCi() {
        assertTrue(true);
    }
}
