import driver.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

@SuppressWarnings("CPD-START")
public class AcceptanceTests {
    @Test
    public void shouldLostIfDownloadStairs() throws GameNotBuiltException, GameBuilderNotLoadedException {
        GameDriver driver = new ConcreteGameDriver();
        driver.loadBuilder("EntregaBuilder.jar");
        driver.buildGame();
        driver.sendCommand(1,"goto BibliotecaAcceso");
        driver.sendCommand(1, "goto Pasillo");
        driver.sendCommand(1, "goto Salon3");
        driver.sendCommand(1, "pick Llave");
        driver.sendCommand(1, "goto Pasillo");
        driver.sendCommand(1, "goto Salon1");
        driver.sendCommand(1, "move CuadroBarco");
        driver.sendCommand(1, "open CajaFuerte using Llave");
        driver.sendCommand(1, "pick Credencial");
        driver.sendCommand(1, "put Foto in Credencial");
        driver.sendCommand(1, "goto Pasillo");
        driver.sendCommand(1, "goto BibliotecaAcceso");
        driver.sendCommand(1, "show Credencial in Bibliotecario");
        driver.sendCommand(1, "goto Biblioteca");
        driver.sendCommand(1, "move LibroViejo");
        driver.sendCommand(1, "goto Sotano");
        driver.sendCommand(1, "use Escalera");
        assertEquals(PlayerState.Lost, driver.getCurrentStateOfPlayer(1));
    }

    @Test
    public void shouldLostIfGoToBasementWithoutAHammer() throws GameNotBuiltException, GameBuilderNotLoadedException {
        GameDriver driver = new ConcreteGameDriver();
        driver.loadBuilder("EntregaBuilder.jar");
        driver.buildGame();
        driver.sendCommand(1, "goto BibliotecaAcceso");
        driver.sendCommand(1, "goto Pasillo");
        driver.sendCommand(1, "goto Salon3");
        driver.sendCommand(1, "pick Llave");
        driver.sendCommand(1, "goto Pasillo");
        driver.sendCommand(1, "goto Salon1");
        driver.sendCommand(1, "move CuadroBarco");
        driver.sendCommand(1, "open CajaFuerte using Llave");
        driver.sendCommand(1, "pick Credencial");
        driver.sendCommand(1, "put Foto in Credencial");
        driver.sendCommand(1, "goto Pasillo");
        driver.sendCommand(1, "goto BibliotecaAcceso");
        driver.sendCommand(1, "show Credencial in Bibliotecario");
        driver.sendCommand(1, "goto Biblioteca");
        driver.sendCommand(1, "move LibroViejo");
        driver.sendCommand(1, "goto Sotano");
        driver.sendCommand(1, "use Baranda");
        assertEquals(PlayerState.Lost, driver.getCurrentStateOfPlayer(1));
    }

    @Test
    public void shouldWinIfGoToBasementWithAHammer() throws GameNotBuiltException, GameBuilderNotLoadedException {
        GameDriver driver = new ConcreteGameDriver();
        driver.loadBuilder("EntregaBuilder.jar");
        driver.buildGame();
        driver.sendCommand(1, "goto BibliotecaAcceso");
        driver.sendCommand(1, "goto Pasillo");
        driver.sendCommand(1, "goto Salon3");
        driver.sendCommand(1, "pick Llave");
        driver.sendCommand(1, "goto Pasillo");
        driver.sendCommand(1, "goto Salon2");
        driver.sendCommand(1, "pick Martillo");
        driver.sendCommand(1, "goto Pasillo");
        driver.sendCommand(1, "goto Salon1");
        driver.sendCommand(1, "move CuadroBarco");
        driver.sendCommand(1, "open CajaFuerte using Llave");
        driver.sendCommand(1, "pick Credencial");
        driver.sendCommand(1, "put Foto in Credencial");
        driver.sendCommand(1, "goto Pasillo");
        driver.sendCommand(1, "goto BibliotecaAcceso");
        driver.sendCommand(1, "show Credencial in Bibliotecario");
        driver.sendCommand(1, "goto Biblioteca");
        driver.sendCommand(1, "move LibroViejo");
        driver.sendCommand(1, "goto Sotano");
        driver.sendCommand(1, "use Baranda");
        driver.sendCommand(1, "break Ventana using Martillo");
        driver.sendCommand(1, "goto Afuera");
        assertEquals(PlayerState.Won, driver.getCurrentStateOfPlayer(1));
    }

    @Test
    public void shouldLostIfLibrarianMeetsUs() throws GameNotBuiltException, GameBuilderNotLoadedException {
        GameDriver driver = new ConcreteGameDriver();
        driver.loadBuilder("EntregaBuilder.jar");
        driver.setGameRandom(new MockGameRandom(new ArrayList<>(Arrays.asList(0))));
        driver.buildGame();
        driver.sendCommand(1, "goto Salon1");
        driver.sendCommand(1, "pick VasoAzul");
        driver.sendCommand(1, "pick VasoRojo");
        driver.sendCommand(1, "pick Botella");
        driver.sendCommand(1, "goto Pasillo");
        driver.sendCommand(1, "goto BibliotecaAcceso");
        driver.sendCommand(1, "make drunk Bibliotecario");
        driver.shootTimeEvent(0);
        driver.shootTimeEvent(1);
        driver.sendCommand(1, "goto Biblioteca");
        assertEquals(PlayerState.Lost, driver.getCurrentStateOfPlayer(1));
    }

    @Test
    public void playerOneShouldLoseIfMeetsLibrarian() throws GameNotBuiltException, GameBuilderNotLoadedException {
        GameDriver driver = new ConcreteGameDriver();
        driver.loadBuilder("EntregaBuilder.jar");
        driver.setGameRandom(new MockGameRandom(new ArrayList<>(Arrays.asList(0))));
        driver.buildGame();
        driver.sendCommand(1, "goto Salon1");
        driver.sendCommand(1, "pick VasoAzul");
        driver.sendCommand(1, "pick VasoRojo");
        driver.sendCommand(1, "pick Botella");
        driver.sendCommand(1, "goto Pasillo");
        driver.sendCommand(1, "goto BibliotecaAcceso");
        driver.sendCommand(1, "make drunk Bibliotecario");

        driver.sendCommand(2, "goto BibliotecaAcceso");
        driver.sendCommand(2, "goto Biblioteca");
        driver.sendCommand(1, "goto Biblioteca");

        driver.sendCommand(2, "goto BibliotecaAcceso");
        driver.sendCommand(2, "goto Pasillo");
        driver.shootTimeEvent(0);
        driver.shootTimeEvent(1);

        assertEquals(PlayerState.Playing, driver.getCurrentStateOfPlayer(2));
        assertEquals(PlayerState.Lost, driver.getCurrentStateOfPlayer(1));
    }
}