@SuppressWarnings("CPD-START")
public class EntregaConstants {

    //Times
    public static final String librerianWakeUp = "wakeup";
    public static final String librerianRandom = "changeRoom";
    public static final String sleeping = "sleeping";
    public static final String noEsta = "";

    //Elements
    public static final int numberOfPlayers = 1;
    public static final String roomPasillo = "pasillo";
    public static final String roomSalon1 = "salon1";
    public static final String roomSalon2 = "salon2";
    public static final String roomSalon3 = "salon3";
    public static final String roomBiblioteca = "biblioteca";
    public static final String roomAccesoBiblioteca = "accesoBiblioteca";
    public static final String roomSotano = "sotano";
    public static final String roomSubSotano = "subsotano";
    public static final String roomPatio = "patio";

    public static final String doorPasilloToSalon1 = "Salon1";
    public static final String doorPasilloToSalon2 = "Salon2";
    public static final String doorPasilloToSalon3 = "Salon3";
    public static final String doorAccesoBiblioteca = "BibliotecaAcceso";
    public static final String doorPasillo = "Pasillo";
    public static final String doorBiblioteca = "Biblioteca";
    public static final String doorBibliotecaToSotano = "Sotano";
    public static final String doorSubSotanoToPatio = "Afuera";

    //Items
    public static final String pen = "Lapicera";
    public static final String photo = "Foto";
    public static final String tableSalon1 = "Mesa";
    public static final String bottleSalon1 = "Botella";
    public static final String glass1Salon1 = "VasoRojo";
    public static final String glass2Salon1 = "VasoAzul";
    public static final String chair1Salon1 = "SillaRoja";
    public static final String chair2Salon1 = "SillaAzul";
    public static final String cuadroBarcoSalon1 = "CuadroBarco";
    public static final String cajaFuerteSalon1 = "CajaFuerte";
    public static final String credencialSalon1 = "Credencial";
    public static final String cuadroTrenSalon1 = "CuadroTren";
    public static final String martilloSalon2 = "Martillo";
    public static final String destornillador1Salon2 = "DestornilladorRojo";
    public static final String destornillador2Salon2 = "DestornilladorAzul";
    public static final String keySalon3 = "Llave";
    public static final String bibliotecario = "Bibliotecario";
    public static final String estante = "Estante";
    public static final String libroViejo = "LibroViejo";
    public static final String libro1 = "LibroAzul";
    public static final String libro2 = "LibroRojo";
    public static final String libro3 = "LibroAmarillo";
    public static final String libro4 = "LibroVioleta";
    public static final String libro5 = "LibroVerde";
    public static final String libro6 = "LibroCeleste";
    public static final String libro7 = "LibroRosa";
    public static final String libro8 = "LibroNaranja";
    public static final String libro9 = "LibroBlanco";
    public static final String baranda = "Baranda";
    public static final String escaleraOxidada = "Escalera";
    public static final String ventana = "Ventana";

    //States
    public static final String abierta = "abierta";
    public static final String valido = "valida";
    public static final String invalido = "invalida";
    public static final String feliz = "feliz";
    public static final String borracho = "borracho";
    public static final String muerto = "muerto";
    public static final String roto = "roto";

    //Rules
    public static final String noEstaEnLaRoom = "No se puede realizar esa accion en esta habitacion";
    public static final String necesitaTenerLlaveSalon3 = "Necesitas una llave para eso!";
    public static final String estasMuerto = "Moriste";
    public static final String necesitaSerValida = "Parece que eso no es valido...";
    public static final String fotoNoPegada = "No eres tu el de la credencial";
    public static final String noTieneElMartillo = "No tienes el martillo, no puedes romper la ventana";
    public static final String necesitaLaBotella = "No agarraste la botella";
    public static final String necesitaElVaso = "No agarraste el vaso";
    public static final String noTieneVasos = "Necesitas algo donde servir una bebida";
    public static final String noSePuedeEmborrachar = "No se pudo emborrachar al bibliotecario";
    public static final String noEstaFeliz = "No esta feliz de verte otra vez";
    public static final String noEstaBorracho = "No esta borracho como para dejarte pasar";
    public static final String noSePuedePasarALaBiblioteca = "No tienes permitido ingresar a la biblioteca";
    public static final String necesitaTenerMartillo = "Necesitas el martillo";
    public static final String necesitaEstarRotaLaVentana = "No puedes pasar por la ventana!";

    //Moves
    public static final String cambiadoAPasillo = "Es un pasillo";
    public static final String cambiadoASalon1 = "Es un salon";
    public static final String cambiadoASalon2 = "Es un salon";
    public static final String cambiadoASalon3 = "Es un salon";
    public static final String cambiadoAAccesoBiblioteca = "Es el acceso a una biblioteca";
    public static final String cambiadoABiblioteca = "Es una biblioteca";
    public static final String cambiadoASotano = "Es un sotano frio y humedo";
    public static final String cambiadoASubSotano = "Es la parte mas baja del sotano";
    public static final String cambiadoAPatio = "Estas afuera!!";

    public static final String cambiadoFotoDeCredencial = "Ahora la credencial parece tuya!";
    public static final String bibliotecarioBorracho = "El bibliotecario esta boracho, hip!";
    public static final String bibliotecarioFeliz = "Parece que el bibliotecario te deja pasar...";
    public static final String escaleraEnMalasCondiciones = "Hay un escalon roto.  Moriste al caer";

    public static final String movedCuadroBarco = "Hay algo atras del cuadro...";
    public static final String movedLibroViejo = "Parece que la biblioteca se esta moviendo!";
    public static final String movedLibro = "Es solo un libro";

    public static final String abiertaCajaFuerte = "Parece que hay algo en la caja fuerte!";

    public static final String tomadoCredencial = "Ahora tienes una credencial.. de donde puede ser?";
    public static final String tomadaBotella = "Ahora tienes una botella.. Parece alcoholica";
    public static final String tomadaLlave = "Ahora tienes una llave";
    public static final String tomadoMartillo = "Ahora tienes un martillo";
    public static final String tomadoDestornillador = "Ahora tienes un destornillador";
    public static final String tomadoVaso = "Ahora tienes un vaso";

    public static final String seRompioVentana = "Se rompio la ventana, puedo ver afuera!";

    public static final String movePick = "pick";
    public static final String moveIrA = "goto";
    public static final String moveUse = "use";
    public static final String movePutFoto = "put Foto in";
    public static final String moveMover = "move";
    public static final String moveAbrirCajaFuerte = "open CajaFuerte using";
    public static final String moveEmborrachar = "make drunk";
    public static final String moveMostrarCredencial = "show Credencial in";
    public static final String moveRomperVentana = "break Ventana using";

}
