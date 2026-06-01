package hello;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.io.IOException;
import javax.microedition.media.*;
import java.io.InputStream;
import javax.microedition.media.control.VolumeControl;

public class MiJuego extends GameCanvas implements Runnable {
    // Offset del jugador para ajustar el punto de colisión (hitbox)
    private static final int OFFSET_X = 4;
    private static final int OFFSET_Y = 22;
    
    int w;
    int h;
    
    int contandoCantidadDeVecesEnElCamino;
    final int X_IMAGEN = 0;
    final int X_NUMERO = 26;
    
    private int ultimoCaminoConvertido;
    
    
    private int direccionEntradaCamino;

    private static int ENTRA_IZQUIERDA;
    private static int ENTRA_DERECHA;
    private static int ENTRA_ARRIBA;
    private static int ENTRA_ABAJO;
    
    
    private int tipoDeTilesPngUtilizado;
    
    
    
   
    int anchoDelNivel;
    int altoDelNivel;
    
    private boolean gameOver;
    private boolean bobbyEncimaDeTrampa;
    
    int trampaX;
    int trampaY;
    
    private int nivelSiguiente=3;
    private static final int totalNiveles = 4;
    
    private String textoObjetoPorRecoger;
    private String textoObjetoPorDejar;
    private String textoReiniciarNivel;
    private String textoReiniciarNivel1;
    private String textoReiniciarNivel2;
    private String textoReiniciarNivel3;
    private int colorTexto;
    private Player musicaFondo;
    private Player sonidoMuerte;

    private Player sonidoZanahoria;
    
    private Player sonidoLlave;
    private Player sonidoCandadoCerrado;
    private Player sonidoCandadoAbierto;

    private Player sonidoMeta;
    
    
    private boolean inicializandoVariables;
    
    private String nombreNivel;
    
    private boolean desaparecerMensajeNivel;
    private int repetirVariasVecesDesaparicion;
    private int contadordeRepeticionesDeDesaparicion;
    
    private long tiempoMensaje;
    private int duracionMensaje;
    
    
    private boolean moviendo;
    
    private boolean tieneLlaveBronce;
    private boolean tieneLlavePlata;
    private boolean tieneLlaveOro;
    
    private boolean llaveBronceUsada;
    private boolean llavePlataUsada;
    private boolean llaveOroUsada;
    
    private boolean modoAparecer;
    
    private boolean nivelTerminado;
    
    private int totalZanahoriasRecogerEnNivel;
    private boolean todasLasZanahoriasRecogidas;
    
    private int totalHuevosDejarEnNivel;
    private boolean todosLosHuevosDejados;
    
    private boolean mostrarMensajeCantidadZanahoriasPorRecoger;
    private boolean mostrarMensajeCantidadHuevosPorDejar;

    private Graphics g;
    private boolean run;

    // GAME DESIGN
    private GameDesign gd;

    // MAPA
    private TiledLayer nivel;

    // SPRITES DIRECCIONALES
    private Sprite down;
    private Sprite up;
    private Sprite left;
    private Sprite right;
    private Sprite aparecer;
    
    private Sprite desaparecer;
    
    private Sprite muriendo;
    
    private Image llaveBronceImagen;
    private Image llavePlataImagen;
    private Image llaveOroImagen;
    
    
    
    //posiblemente se utilizará en algún otro momento cuando usemos otro tiles donde tengamos más objetos por dejar
    //en el mapa
    private Image imagencitaObjetoPorDejar;
    
    //necesarios
    private Image imagencitaObjetoPorRecoger;
    private Image imagencitaZanahoria;
    private Image imagencitaHuevo;
    private Image imagencitaEstrella;

    // SPRITE ACTUAL
    private Sprite player;
    
    //SPRITE META
    private Sprite meta;

    // POSICION
    private int x;
    private int y;

    // CAMARA
    private int camX;
    private int camY;

    // TAMAÑO MAPA
    private int MAP_W;
    private int MAP_H;

    // VELOCIDAD
    private int SPEED;
    
    
    
    // Tamaño de cada tile del mapa (en píxeles)
    private static final int TILE_SIZE = 16;
    
    // TILES SOLIDOS
    private static final int TILE_PARED = 1;
    private static final int TILE_CERCA = 14;
    private static final int TILE_CAMINO = 19;
    
    //ZANAHORIAS
    private static final int TILE_ZANAHORIA = 20;
    private static final int TILE_ZANAHORIA_NO = 21;
    
    //INICIO TITLE
    private static final int TILE_SPAWN = 22;
    
        
    private static final int TILE_BOTON_PARA_PULSAR = 23;
        
    private static final int TILE_BOTON_PARA_NO_PULSAR = 24;
        
    private static final int TILE_LLAVE_PLATEADO = 33;
    private static final int TILE_CANDADO_PLATEADO = 34;
        
    private static final int TILE_LLAVE_ORO = 35;
    private static final int TILE_CANDADO_ORO = 36;
    
    private static final int TILE_LLAVE_BRONCE = 37;
    private static final int TILE_CANDADO_BRONCE = 38;
    
    private static final int TILE_TRAMPA_SI_ACTIVA =32;
    private static final int TILE_TRAMPA_NO_ACTIVA =31;
    
       
    //META TITLE
    private static final int TILE_META = 45;
    
    
    
    //VARIABLES PARA LOS CAMINITOS METÁLICOS
    private boolean bobbyEncimaDeCamino;

    private int caminoX;
    private int caminoY;

    

    
    private static final int TILE_CAMINO_HORIZONTAL = 29;

    private static final int TILE_CAMINO_VERTICAL = 30;
    
    private static final int TILE_CAMINO_ESQUINA_1 = 25;

    private static final int TILE_CAMINO_ESQUINA_2 = 26;

    private static final int TILE_CAMINO_ESQUINA_3 = 27;

    private static final int TILE_CAMINO_ESQUINA_4 = 28;
    
    
    
    //En caso de que carguemos el tileset_normal
    private static int TILE_SIN_HUEVO = 46;
    private static int TILE_CON_HUEVO = 47;
    
    //En caso de que carguemos el tileset_invierno
    private static int TILE_ESPEJO_1 = 46;
    private static int TILE_ESPEJO_2 = 47;
    

    public MiJuego() {

        super(true);
        
        inicializarVariables();

    }

    public void start() {

        run = true;

        new Thread(this).start();
    }

    public void run() {

        while (run) {
            if(inicializandoVariables) return;
            actualizar();

            render();

            flushGraphics();

            try {
                Thread.sleep(40);
            } catch (Exception e) {
            }
        }
    }
    
    public void cargarSonido(){
        try {
            InputStream musica = getClass().getResourceAsStream("/ingame.mid");

            musicaFondo = Manager.createPlayer(musica, "audio/midi");

            musicaFondo.realize();
            
            VolumeControl vc = (VolumeControl) musicaFondo.getControl("VolumeControl");

            if(vc != null){
                vc.setLevel(16); // 0 a 100
            }

            
            musicaFondo.setLoopCount(-1);
            musicaFondo.prefetch();

            
            InputStream muertito = getClass().getResourceAsStream("/death.mid");

            sonidoMuerte = Manager.createPlayer(muertito, "audio/midi");

            sonidoMuerte.realize();
            sonidoMuerte.prefetch();


            InputStream zanahoria = getClass().getResourceAsStream("/carrot.wav");

            sonidoZanahoria = Manager.createPlayer(zanahoria, "audio/X-wav");

            sonidoZanahoria.realize();
            sonidoZanahoria.prefetch();
            
            
            
            InputStream llave = getClass().getResourceAsStream("/llave.wav");

            sonidoLlave = Manager.createPlayer(llave, "audio/X-wav");

            sonidoLlave.realize();
            sonidoLlave.prefetch();
            
            
            InputStream candadoCerrado = getClass().getResourceAsStream("/candado_cerrado.wav");

            sonidoCandadoCerrado = Manager.createPlayer(candadoCerrado, "audio/X-wav");

            sonidoCandadoCerrado.realize();
            sonidoCandadoCerrado.prefetch();
            
            
            
            
            InputStream candadoAbierto = getClass().getResourceAsStream("/candado_abierto.wav");

            sonidoCandadoAbierto = Manager.createPlayer(candadoAbierto, "audio/X-wav");

            sonidoCandadoAbierto.realize();
            sonidoCandadoAbierto.prefetch();




            InputStream meta = getClass().getResourceAsStream("/cleared.mid");

            sonidoMeta = Manager.createPlayer(meta, "audio/midi");

            sonidoMeta.realize();
            sonidoMeta.prefetch();



        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
    public void inicializarVariables(){
        
        try{
            nivelSiguiente++;
            if(nivelSiguiente>totalNiveles) nivelSiguiente = 1;
            inicializandoVariables = true;
            w = 8;
            h = 5;
            
            trampaX = -1;
            trampaY = -1;
            
            gameOver = false;
            bobbyEncimaDeTrampa = false;
            
            
            
            //VARIABLES PARA LOS CAMINITOS METÁLICOS
            bobbyEncimaDeCamino = false;

            caminoX = -1;
            caminoY = -1;

            mostrarMensajeCantidadZanahoriasPorRecoger = false;
            mostrarMensajeCantidadHuevosPorDejar = false;
            
            contandoCantidadDeVecesEnElCamino = 0;
            
            
            direccionEntradaCamino = 0;

            ENTRA_IZQUIERDA = 1;
            ENTRA_DERECHA   = 2;
            ENTRA_ARRIBA    = 3;
            ENTRA_ABAJO     = 4;
            
            
            ultimoCaminoConvertido = -1;
            
    
            repetirVariasVecesDesaparicion = 3;
            contadordeRepeticionesDeDesaparicion = 0;
            llaveBronceUsada = false;
            llavePlataUsada = false;
            llaveOroUsada = false;
            nombreNivel = "Nivel #"+ nivelSiguiente;
            this.textoObjetoPorDejar = "Huevos: ";
            this.textoReiniciarNivel = "Nivel #"+ nivelSiguiente+ " completo :D";
            this.textoReiniciarNivel1 = "Presione boton del centro";
            this.textoReiniciarNivel2 = "Has muerto :(";
            this.textoReiniciarNivel3 = "Reiniciar..";
            
            desaparecerMensajeNivel = false;
            moviendo = false;
            duracionMensaje = 500;
            g = getGraphics();

            gd = new GameDesign();
            
            liberarSonido();
            cargarSonido();
        
        // MAPA
            switch(nivelSiguiente){
                case 1:
                    nivel = gd.getNivel_1();
                break;
                case 2:
                    nivel = gd.getNivel_2();
                break;
                case 3:
                    nivel = gd.getNivel_3();
                break;
                case 4:
                    nivel = gd.getNivel_4();
                break;
            }
            
            
            anchoDelNivel = nivel.getColumns();
            altoDelNivel = nivel.getRows();
            
            //MUY IMPORTANTE (PARA SABER SI ESTAMOS UTILIZANDO EL TILESET NORMAL O EL INVERNAL)
            //PUESTO QUE CAMBIAN ALGUNOS TILES
            tipoDeTilesPngUtilizado = gd.getTipoTilesActual();
            System.out.println("tipo de tiles cargado png es: "+tipoDeTilesPngUtilizado);
            
            MAP_W = anchoDelNivel * 16;
            MAP_H = altoDelNivel * 16;
            
            System.out.println("ancho de nivel es: "+anchoDelNivel);
            System.out.println("alto de nivel es: "+altoDelNivel);
            
            // POSICION
            x = 40;
            y = 40;

            // CAMARA
            camX = 0;
            camY = 0;
            
            SPEED = 2;
            todasLasZanahoriasRecogidas = false;
            nivelTerminado = false;
            
            //Total de zanahorias es igual a 0
            totalZanahoriasRecogerEnNivel = 0;
            
            
            tieneLlaveBronce = false;
            tieneLlavePlata = false;
            tieneLlaveOro = false;

            modoAparecer = true;

            totalZanahoriasRecogerEnNivel = 0;
            todasLasZanahoriasRecogidas = false;
            
            totalHuevosDejarEnNivel = 0;
            todosLosHuevosDejados = false;
            
            imagencitaZanahoria = Image.createImage("/zanahoria.png");
            imagencitaHuevo = Image.createImage("/huevo.png");
            imagencitaEstrella = Image.createImage("/estrella.png");
            
            //contar cantidad de zanahorias que es necesario recoger en el nivel
            contarTotalDeZanahoriasPorRecoger();
            
            //si el tiles png es el normal, entendemos que si es necesario contar la cantidad de huevos a dejar
            //en caso de que ese tiles haya sido colocando en el nivel verdad
            if(this.tipoDeTilesPngUtilizado == 1){
                contarTotalDeHuevosPorDejar();
                this.textoObjetoPorRecoger = "Zanahorias: ";
                this.colorTexto = 0xFFFFFF;
                imagencitaObjetoPorRecoger = this.imagencitaZanahoria;
                
            }
            if(this.tipoDeTilesPngUtilizado == 2){
                this.textoObjetoPorRecoger = "Estrellas: ";
                this.colorTexto = 0x000000;
                imagencitaObjetoPorRecoger = this.imagencitaEstrella;
            }
            
            //Saber dónde debe aparecer el personaje al inicio del juego
            buscarSpawn();

            // SPRITES
            down = gd.getBobby_abajo();

            left = gd.getBobby_izquierda();

            // EJEMPLOS
            // CAMBIA ESTOS POR LOS TUYOS
            right = gd.getBobby_derecha();

            up = gd.getBobby_arriba();
            
            aparecer = gd.getBobby_aparecer();
            desaparecer = gd.getBobby_desaparec();
            muriendo = gd.getBobby_muerto();
            
            //obtener SPRITE DE LA META
            meta =gd.getBobby_meta();
            
            //obtener imagen de llave bronce
            llaveBronceImagen = Image.createImage("/llave_bronce.png");
            
            //obtener imagen de llave plateada
            llavePlataImagen = Image.createImage("/llave_plata.png");
            
            //obtener imagen de llave oro
            llaveOroImagen = Image.createImage("/llave_oro.png");
            

            // DIRECCION INICIAL
            player = aparecer;
            modoAparecer = true;
            

            // POSICION INICIAL
            player.setPosition(x, y);
            
            nivelTerminado = false;
            
            inicializandoVariables = false;
            
            iniciarMusicaFondo();
            
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void iniciarMusicaFondo(){
        try{
          musicaFondo.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void detenerMusicaFondo(){
        try{
          musicaFondo.stop();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void iniciarSonidoMuerte(){
        try{
          sonidoMuerte.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void detenerSonidoMuerte(){
        try{
          sonidoMuerte.stop();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void iniciarSonidoZanahoria(){
        try{
          sonidoZanahoria.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void detenerSonidoZanahoria(){
        try{
          sonidoZanahoria.stop();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    private void iniciarSonidoLlave(){
        try{
          sonidoLlave.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void detenerSonidoLlave(){
        try{
          sonidoLlave.stop();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    
    
    private void iniciarSonidoCandadoCerrado(){
        try{
          sonidoCandadoCerrado.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void detenerSonidoCandadoCerrado(){
        try{
          sonidoCandadoCerrado.stop();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    private void iniciarSonidoCandadoAbierto(){
        try{
          sonidoCandadoAbierto.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void detenerSonidoCandadoAbierto(){
        try{
          sonidoCandadoAbierto.stop();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void iniciarSonidoMeta(){
        try{
          sonidoMeta.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void detenerSonidoMeta(){
        try{
          sonidoMeta.stop();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void liberarSonido(){
        try{
            if(musicaFondo != null){
                musicaFondo.close();
                musicaFondo = null;
            }
            
            if(sonidoMuerte != null){
                sonidoMuerte.close();
                sonidoMuerte = null;
            }

            if(sonidoZanahoria != null){

                sonidoZanahoria.close();
                sonidoZanahoria = null;
            }
            
            

            if(sonidoLlave != null){

                sonidoLlave.close();
                sonidoLlave = null;
            }            
            
            
            if(sonidoCandadoCerrado != null){

                sonidoCandadoCerrado.close();
                sonidoCandadoCerrado = null;
            }    
            
      
            
            if(sonidoCandadoAbierto != null){

                sonidoCandadoAbierto.close();
                sonidoCandadoAbierto = null;
            }             
            
            
            
            

            if(sonidoMeta != null){

                sonidoMeta.close();
                sonidoMeta = null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // =========================================
    // ACTUALIZAR JUEGO
    // =========================================

    private void actualizar() {

        int key = getKeyStates();

        moviendo = false;
        
        if(todasLasZanahoriasRecogidas || todosLosHuevosDejados || modoAparecer || gameOver){
            if ((key & FIRE_PRESSED) != 0) {
                //Usado para reiniciar nivel o cambiar nivel
                inicializarVariables();
            }
        }else{
                // =====================================
            // IZQUIERDA
            // =====================================


            if ((key & LEFT_PRESSED) != 0) {

                player = left;

                if (!colision(x - SPEED, y)) {
                    x -= SPEED;
                }

                moviendo = true;
            }

            // =====================================
            // DERECHA
            // =====================================

            else if ((key & RIGHT_PRESSED) != 0) {

                player = right;

                if (!colision(x + SPEED, y)) {
                    x += SPEED;
                }

                moviendo = true;
            }
            // =====================================
            // ARRIBA
            // =====================================

            else if ((key & UP_PRESSED) != 0) {

                player = up;

                if (!colision(x, y - SPEED)) {
                    y -= SPEED;
                }

                moviendo = true;
            }

            // =====================================
            // ABAJO
            // =====================================

            else if ((key & DOWN_PRESSED) != 0) {

                player = down;

                if (!colision(x, y + SPEED)) {
                    y += SPEED;
                }

                moviendo = true;
            }
        }
        

        // =====================================
        // ANIMACION
        // =====================================

        if ((!gameOver ||!todasLasZanahoriasRecogidas ||!todosLosHuevosDejados) && moviendo) {
            player.nextFrame();

        } else {
            if(gameOver){
                System.out.println("game oveeeeeeeeeeeeeeeeeeeeeeeer");
                moviendo = false;
                if(player.getFrame() < player.getFrameSequenceLength() - 1){

                       player.nextFrame();

                }else{
                     contadordeRepeticionesDeDesaparicion++;
                     if(contadordeRepeticionesDeDesaparicion == repetirVariasVecesDesaparicion){
                          nivelTerminado = true;
                     }
                    
                        //player = down;
                        //modoAparecer = false;
                }
                 
            }else if(todasLasZanahoriasRecogidas && todosLosHuevosDejados){
                moviendo = false;
                 if(player.getFrame() < player.getFrameSequenceLength() - 1){

                       player.nextFrame();

                 }else{
                     contadordeRepeticionesDeDesaparicion++;
                     if(contadordeRepeticionesDeDesaparicion == repetirVariasVecesDesaparicion){
                          nivelTerminado = true;
                     }
                    
                        //player = down;
                        //modoAparecer = false;
                 }
            }else if(modoAparecer){
                    if(player.getFrame() < player.getFrameSequenceLength() - 1){

                        player.nextFrame();

                    }else{
                        player = down;
                        tiempoMensaje = System.currentTimeMillis();
                        modoAparecer = false;
                    }
            }
            else{

            // FRAME QUIETO
            player.setFrame(0);                
            }

        }

        // =====================================
        // ACTUALIZAR POSICION
        // =====================================
        
        if(!todasLasZanahoriasRecogidas && !todosLosHuevosDejados){
            down.setPosition(x, y);
            up.setPosition(x, y);
            left.setPosition(x, y);
            right.setPosition(x, y);
            aparecer.setPosition(x,y);
            desaparecer.setPosition(x,y);  
            muriendo.setPosition(x,y);
        }

        // =====================================
        // CAMARA
        // =====================================

        camX = x - getWidth() / 2;
        camY = y - getHeight() / 2;

        // LIMITES CAMARA

        int maxX = MAP_W - getWidth();
        int maxY = MAP_H - getHeight();

        if (camX < 0) camX = 0;
        if (camY < 0) camY = 0;

        if (camX > maxX) camX = maxX;
        if (camY > maxY) camY = maxY;
        
    }

    // =========================================
    // COLISIONES
    // =========================================
    
    //Detectar dónde debe aparecer el personaje
    private void buscarSpawn() {

        for (int y = 0; y < altoDelNivel; y++) {
            for (int x = 0; x < anchoDelNivel; x++) {

                int tile = nivel.getCell(x, y);

                if (tile == TILE_SPAWN) { // TILE DE INICIO

                    this.x = x * 16;
                    this.y = y * 16;

                    return;
                }
            }
        }
    }
    

 
    //Detectar cuántaz zanahorias es necesario recoger
    private void contarTotalDeZanahoriasPorRecoger() {

        for (int y = 0; y < altoDelNivel; y++) {
            for (int x = 0; x < anchoDelNivel; x++) {

                int tile = nivel.getCell(x, y);

                if (tile == TILE_ZANAHORIA) { // TILE DE INICIO
                    totalZanahoriasRecogerEnNivel++;
                    
                    if(!mostrarMensajeCantidadZanahoriasPorRecoger){
                      mostrarMensajeCantidadZanahoriasPorRecoger = true;  
                    }                   
                    
                }
            }
        }
    }  
    
    
    //Detectar cuántos huevos es necesario dejar
    private void contarTotalDeHuevosPorDejar() {

        for (int y = 0; y < altoDelNivel; y++) {
            for (int x = 0; x < anchoDelNivel; x++) {

                int tile = nivel.getCell(x, y);

                if (tile == TILE_SIN_HUEVO) {
                    totalHuevosDejarEnNivel++;
                    if(!mostrarMensajeCantidadHuevosPorDejar){
                      mostrarMensajeCantidadHuevosPorDejar = true;  
                    }
                }
            }
        }
    }
    
    
    
    private void verificarSiNosotrosTomamosUnaLlave(int px, int py){
        //revizamos esto
        int tileX = (px + OFFSET_X) / TILE_SIZE;
        int tileY = (py + OFFSET_Y) / TILE_SIZE;

        int tile = nivel.getCell(tileX, tileY);
        
        if (tile == TILE_LLAVE_BRONCE && !tieneLlaveBronce) {
            this.iniciarSonidoLlave();
            tieneLlaveBronce = true;
            nivel.setCell(tileX, tileY, TILE_CAMINO); // desaparece la llave
        }
        
        if (tile == TILE_LLAVE_PLATEADO && !tieneLlavePlata) {
            this.iniciarSonidoLlave();
            tieneLlavePlata = true;
            nivel.setCell(tileX, tileY, TILE_CAMINO); // desaparece la llave
        }
        
        if (tile == TILE_LLAVE_ORO && !tieneLlaveOro) {
            this.iniciarSonidoLlave();
            tieneLlaveOro = true;
            nivel.setCell(tileX, tileY, TILE_CAMINO); // desaparece la llave
        }
    }
    
    
    
    
    
    private void verificarSiActivarMeta(){

        for(int _y = 0; _y < altoDelNivel; _y++){

            for(int _x = 0; _x < anchoDelNivel; _x++){

                int tile = nivel.getCell(_x, _y);

                if(tile == TILE_META){

                    if(this.totalZanahoriasRecogerEnNivel == 0 && this.totalHuevosDejarEnNivel == 0){

                        meta.nextFrame();
                        
                            meta.setPosition(
                                _x * TILE_SIZE,
                                _y * TILE_SIZE
                            );

                        meta.paint(g);
                    }

                    return;
                }
            }
        }
    }
    
    
    private void verificarSiTocamosLaMeta(int px, int py){
        int tileX = (px + OFFSET_X) / TILE_SIZE;
        int tileY = (py + OFFSET_Y) / TILE_SIZE;

        int tile = nivel.getCell(tileX, tileY);
        
        //Si nosotros tocamos la meta,
        if (tile == TILE_META && totalZanahoriasRecogerEnNivel == 0 && !todasLasZanahoriasRecogidas && totalHuevosDejarEnNivel == 0 && !todosLosHuevosDejados) {
            this.detenerMusicaFondo();
            this.iniciarSonidoMeta();
            moviendo = false;
            todasLasZanahoriasRecogidas = true;
            todosLosHuevosDejados = true;
            mostrarMensajeCantidadZanahoriasPorRecoger = false;
            mostrarMensajeCantidadHuevosPorDejar = false;
            player = desaparecer;
            player.setFrame(0);
            // FRAME QUIETO
        }
    }
    
    
    
    private void verificarSiTocamosUnaTrampaNoActiva(int px, int py){
        int tileX = (px + OFFSET_X) / TILE_SIZE;
        int tileY = (py + OFFSET_Y) / TILE_SIZE;

        int tile = nivel.getCell(tileX, tileY);
        
        //Si nosotros tocamos UNA TRAMPA NO ACTIVA, SE CAMBIA POR UN TILE DE TRAMPA ACTIVA
        if (tile == TILE_TRAMPA_NO_ACTIVA) {

            if (bobbyEncimaDeTrampa &&
                (tileX != trampaX || tileY != trampaY)) {

                nivel.setCell(
                    trampaX,
                    trampaY,
                    TILE_TRAMPA_SI_ACTIVA
                );
            }

            bobbyEncimaDeTrampa = true;

            trampaX = tileX;
            trampaY = tileY;
        }
        
        
        
        if (bobbyEncimaDeTrampa) {

            int tileActual = nivel.getCell(tileX, tileY);

            if (tileActual != TILE_TRAMPA_NO_ACTIVA) {

                nivel.setCell(
                    trampaX,
                    trampaY,
                    TILE_TRAMPA_SI_ACTIVA
                );

                bobbyEncimaDeTrampa = false;

                trampaX = -1;
                trampaY = -1;
            }
        }
        
        
        
        
        
        
    }
    
    private void verificarSiTocamosUnaTrampaSiActiva(int px, int py){
        int tileX = (px + OFFSET_X) / TILE_SIZE;
        int tileY = (py + OFFSET_Y) / TILE_SIZE;

        int tile = nivel.getCell(tileX, tileY);
        
        //Si nosotros tocamos la meta,
        if (tile == TILE_TRAMPA_SI_ACTIVA &&  !gameOver) {
            nivelSiguiente--;
            mostrarMensajeCantidadZanahoriasPorRecoger = false;
            mostrarMensajeCantidadHuevosPorDejar = false;
            this.detenerMusicaFondo();
            this.iniciarSonidoMuerte();
            moviendo = false;
            gameOver = true;
            player = muriendo;
            player.setFrame(0);
            // FRAME QUIETO
        }
    }
    
    
    
    
    
    
    private boolean verificarCandados(int px, int py){
        int tileX = (px + OFFSET_X) / TILE_SIZE;
        int tileY = (py + OFFSET_Y) / TILE_SIZE;

        int tile = nivel.getCell(tileX, tileY);
        
        //Si nosotros tenemos la llave lista
        if (tile == TILE_CANDADO_BRONCE) {
            if(tieneLlaveBronce && !llaveBronceUsada){
                this.iniciarSonidoCandadoAbierto();
                nivel.setCell(tileX, tileY, TILE_CAMINO); // se abre 
                llaveBronceUsada = true;
                tieneLlaveBronce = false;
                return true;   
            }
            this.iniciarSonidoCandadoCerrado();
        }
        if (tile == TILE_CANDADO_PLATEADO) {
            if(tieneLlavePlata && !llavePlataUsada){
                this.iniciarSonidoCandadoAbierto();
                nivel.setCell(tileX, tileY, TILE_CAMINO); // se abre 
                llavePlataUsada = true;
                tieneLlavePlata = false;
                return true;    
            }
            this.iniciarSonidoCandadoCerrado();
            
        }
        if (tile == TILE_CANDADO_ORO) {
            if(tieneLlaveOro && !llaveOroUsada){
                this.iniciarSonidoCandadoAbierto();
                nivel.setCell(tileX, tileY, TILE_CAMINO); // se abre 
                llaveOroUsada = true;
                tieneLlaveOro = false;
                return true;    
            }
            this.iniciarSonidoCandadoCerrado();
            
        }
        return false;
    }
    
    
    
    private void verificarZanahorias(int px, int py){
        int tileX = (px + OFFSET_X) / TILE_SIZE;
        int tileY = (py + OFFSET_Y) / TILE_SIZE;

        int tile = nivel.getCell(tileX, tileY);
        
        //Si nosotros hemos pasado por una zanahoria, colocar un contador
        if (tile == TILE_ZANAHORIA) {
            iniciarSonidoZanahoria();
            nivel.setCell(tileX, tileY, TILE_ZANAHORIA_NO); // se coloca icono de zanahoria vacia 
            totalZanahoriasRecogerEnNivel--;
        }

    }
    
    
    private void verificarHuevos(int px, int py){
        int tileX = (px + OFFSET_X) / TILE_SIZE;
        int tileY = (py + OFFSET_Y) / TILE_SIZE;

        int tile = nivel.getCell(tileX, tileY);
        
        //Si nosotros hemos pasado por una zona donde se debe dejar un juego, reducir el contador
        if (tile == TILE_SIN_HUEVO) {
            iniciarSonidoZanahoria();
            nivel.setCell(tileX, tileY, TILE_CON_HUEVO); // se coloca icono de zanahoria vacia 
            this.totalHuevosDejarEnNivel--;
        }

    }
    
    private void verificarSiPulsamosBoton(int px, int py){
        int tileX = (px + OFFSET_X) / TILE_SIZE;
        int tileY = (py + OFFSET_Y) / TILE_SIZE;

        int tile = nivel.getCell(tileX, tileY);
        
        //Si nosotros hemos pasado por una zanahoria, colocar un contador
        if (tile == TILE_BOTON_PARA_PULSAR) {
            cambiarTodosLosCaminos();
            nivel.setCell(tileX, tileY, TILE_BOTON_PARA_NO_PULSAR); // se coloca icono de botón no pulsar 
        }

    }
    

    private boolean colision(int px, int py) {

        int tileX = (px+OFFSET_X) / TILE_SIZE;
        //int tileY = (py+OFFSET_Y) / TILE_SIZE;
        int tileY = (py + OFFSET_Y) / TILE_SIZE;
        
        
        
        
        
        
        
        

        int tile = nivel.getCell(tileX, tileY);
        
        verificarSiPulsamosBoton(px,py);
        
        verificarSiTocamosUnaTrampaNoActiva(px,py);
        verificarSiTocamosUnaTrampaSiActiva(px,py);
        
        //VERIFICAMOS ZANAHORIAS
        verificarZanahorias(px,py);
        //verificar si hemos tomando una llave
        verificarSiNosotrosTomamosUnaLlave(px,py);
        
        verificarSiTocamosLaMeta(px,py);
        
        //utilizado para manejar la lógica de los TILES, con respecto al archivo PNG utilizado
        /*
         *tipoDeTilesPngUtilizado == 1, entonces está cargando tileset_normal.png
         * tipoDeTilesPngUtilizado == 2, entonces está cargando tileset_invierno.png
         *
         * Esto mencionado es importante, porque hay TILES únicos que solo están disponibles en su 
         * respectivo archivo PNG 
         */
        switch(tipoDeTilesPngUtilizado){
            case 1:
             verificarHuevos(px,py);
            break;
            case 2:
            break;
            default:
                verificarHuevos(px,py);
            break;
        }
        
        
        
        
        
        if(verificarCandados(px,py)) return true;
        
        //verificar diferentes colisiones para ver si debe haber paso o no
        if((tile >=0 && tile <=18)||(tile==34 || tile ==36 || tile==38)) return true;

        if (tile == TILE_PARED) return true;

        if (tile == TILE_CERCA) return true;

        if (tile == TILE_CANDADO_PLATEADO) return true;
        
        
        boolean estoyDentroDelCamino = bobbyEncimaDeCamino && tileX == caminoX && tileY == caminoY;
        
        boolean entraPorIzquierda = px > x;
        boolean entraPorDerecha  = px < x;
        boolean entraPorArriba   = py > y;
        boolean entraPorAbajo    = py < y;
                
        
        int localX = (px + OFFSET_X) % TILE_SIZE;
        int localY = (py + OFFSET_Y) % TILE_SIZE;
        
        System.out.println("local X: "+localX+", localY: "+localY);
        
        if(tile == TILE_CAMINO_HORIZONTAL){

            if(entraPorArriba) return true;
            if(entraPorAbajo) return true;
        }
        
        if(tile == TILE_CAMINO_VERTICAL){

           
            if(entraPorIzquierda) return true;
            if(entraPorDerecha) return true;
        }
        
        
        if(tile == TILE_CAMINO_ESQUINA_1){
            if(estoyDentroDelCamino){
                if(entraPorDerecha && localX<=5) return true;
                if(entraPorAbajo && localY<=7) return true;
            }else{
                if(entraPorIzquierda) return true;
                if(entraPorArriba) return true;  
            }


        }
        
        
        if(tile == TILE_CAMINO_ESQUINA_2){
            if(estoyDentroDelCamino){
                if(entraPorIzquierda && localX>=5) return true;
                if(entraPorAbajo && localY<=7) return true;
            }else{
                if(entraPorDerecha) return true;
                if(entraPorArriba) return true;  
            }
            
        }
        
        if(tile == TILE_CAMINO_ESQUINA_3){
            if(estoyDentroDelCamino){
                if(entraPorIzquierda && localX>=5) return true;
                if(entraPorArriba && localY>=7) return true;
            }else{
                if(entraPorDerecha) return true;
                if(entraPorAbajo) return true;  
            }          
            
        }
        
        if(tile == TILE_CAMINO_ESQUINA_4){
            if(estoyDentroDelCamino){
                if(entraPorDerecha && localX<=5) return true;
                if(entraPorArriba && localY>=7) return true;
            }else{
                if(entraPorIzquierda) return true;
                if(entraPorAbajo) return true; 
            }  
            
        }
        
        
        verificarSiTocamosUnCamino(px, py);

        return false;
    }
    
    
    
    
    private boolean colision_talvez_mas_adelante(int px, int py) {

        int left = px + OFFSET_X;
        int right = left + w;
        int top = py + OFFSET_Y;
        int bottom = top + h;

        int tile;

        // esquina superior izquierda
        tile = nivel.getCell(left / TILE_SIZE, top / TILE_SIZE);
        if ((tile >= 0 && tile <= 18) || tile == 34 || tile == 36 || tile == 38
                || tile == TILE_PARED || tile == TILE_CERCA || tile == TILE_CANDADO_PLATEADO)
            return true;

        // esquina superior derecha
        tile = nivel.getCell(right / TILE_SIZE, top / TILE_SIZE);
        if ((tile >= 0 && tile <= 18) || tile == 34 || tile == 36 || tile == 38
                || tile == TILE_PARED || tile == TILE_CERCA || tile == TILE_CANDADO_PLATEADO)
            return true;

        // esquina inferior izquierda
        tile = nivel.getCell(left / TILE_SIZE, bottom / TILE_SIZE);
        if ((tile >= 0 && tile <= 18) || tile == 34 || tile == 36 || tile == 38
                || tile == TILE_PARED || tile == TILE_CERCA || tile == TILE_CANDADO_PLATEADO)
            return true;

        // esquina inferior derecha
        tile = nivel.getCell(right / TILE_SIZE, bottom / TILE_SIZE);
        if ((tile >= 0 && tile <= 18) || tile == 34 || tile == 36 || tile == 38
                || tile == TILE_PARED || tile == TILE_CERCA || tile == TILE_CANDADO_PLATEADO)
            return true;

        verificarZanahorias(px, py);
        verificarSiNosotrosTomamosUnaLlave(x, y - SPEED);
        verificarSiTocamosLaMeta(x, y - SPEED);

        if (verificarCandados(px, py)) return true;

        return false;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void verificarSiTocamosUnCamino(int px, int py){

        int tileX = (px + OFFSET_X) / TILE_SIZE;
        int tileY = (py + OFFSET_Y) / TILE_SIZE;

        int tile = nivel.getCell(tileX, tileY);
        
        int localX = (px + OFFSET_X) % TILE_SIZE;
        int localY = (py + OFFSET_Y) % TILE_SIZE;
        
        boolean entraPorIzquierda = px > x;
        boolean entraPorDerecha  = px < x;
        boolean entraPorArriba   = py > y;
        boolean entraPorAbajo    = py < y;

        // Bobby acaba de entrar a un caminito
        if(esCamino(tile) && !bobbyEncimaDeCamino){
            
            if(entraPorIzquierda || entraPorDerecha){
                System.out.println("localX=" + localX);
            }
            if(entraPorArriba || entraPorAbajo){
                System.out.println(" localY=" + localY);     
            }
            
            if(entraPorIzquierda){
                if(localX<=0){
                    direccionEntradaCamino = ENTRA_IZQUIERDA;
                    System.out.println("Por izquierda");
                    detectarQueBobbyEstaSobreElCamino(tileX,tileY);
                }
            }
            
            if(entraPorDerecha){
                if(localX<=10){
                    direccionEntradaCamino = ENTRA_DERECHA;
                    System.out.println("Por derecha");
                    detectarQueBobbyEstaSobreElCamino(tileX,tileY);
                }
            }
            
            if(entraPorAbajo){
                if(localY<=14){
                    direccionEntradaCamino = ENTRA_ABAJO;
                    System.out.println("Por abajo");
                    detectarQueBobbyEstaSobreElCamino(tileX,tileY);
                }
            }
            
            if(entraPorArriba){
                if(localY>=0){
                    direccionEntradaCamino = ENTRA_ARRIBA;
                    System.out.println("Por arriba");
                    detectarQueBobbyEstaSobreElCamino(tileX,tileY);
                }
            }
           
            

                
                         
                
        }

        // Bobby estaba sobre un caminito y ya salió
        if(bobbyEncimaDeCamino){

            if(tileX != caminoX || tileY != caminoY){

                int tileOriginal = nivel.getCell(caminoX, caminoY);
                
                ultimoCaminoConvertido = obtenerSiguienteCamino(tileOriginal);
                
                nivel.setCell(
                    caminoX,
                    caminoY,
                    obtenerSiguienteCamino(tileOriginal)
                );
                System.out.println("YA NO ESTOY EN EL CAMINO");
                bobbyEncimaDeCamino = false;

                caminoX = -1;
                caminoY = -1;
            }
        }
    }
    
    private void detectarQueBobbyEstaSobreElCamino(int tileX, int tileY){
         System.out.println("Bobby encima del camino..");

                bobbyEncimaDeCamino = true;

                caminoX = tileX;
                caminoY = tileY; 
    }
    
    private boolean esCamino(int tile){

        return tile == TILE_CAMINO_HORIZONTAL
            || tile == TILE_CAMINO_VERTICAL
            || tile == TILE_CAMINO_ESQUINA_1
            || tile == TILE_CAMINO_ESQUINA_2
            || tile == TILE_CAMINO_ESQUINA_3
            || tile == TILE_CAMINO_ESQUINA_4;
    }
       
    
    
    
    
    
    private int obtenerSiguienteCamino(int tile){

        switch(tile){

            case TILE_CAMINO_HORIZONTAL:
                return TILE_CAMINO_VERTICAL;

            case TILE_CAMINO_VERTICAL:
                return TILE_CAMINO_HORIZONTAL;

            case TILE_CAMINO_ESQUINA_1:
                return TILE_CAMINO_ESQUINA_2;

            case TILE_CAMINO_ESQUINA_2:
                return TILE_CAMINO_ESQUINA_3;

            case TILE_CAMINO_ESQUINA_3:
                return TILE_CAMINO_ESQUINA_4;

            case TILE_CAMINO_ESQUINA_4:
                return TILE_CAMINO_ESQUINA_1;
        }

        return tile;
    }
    
    
    
    
    
    
    
    
    
    
    
    private void cambiarTodosLosCaminos(){

        for(int y = 0; y < altoDelNivel; y++){

            for(int x = 0; x < anchoDelNivel; x++){

                int tile = nivel.getCell(x, y);

                if(esCamino(tile)){

                    nivel.setCell(
                        x,
                        y,
                        obtenerSiguienteCamino(tile)
                    );
                }

                if(tile == TILE_BOTON_PARA_NO_PULSAR){

                    nivel.setCell(
                        x,
                        y,
                        TILE_BOTON_PARA_PULSAR
                    );
                }
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    // =========================================
    // DIBUJAR
    // =========================================
    
    private void dibujarColisionDebug(int px, int py) {
        //System.out.println("x es: "+ x+"\nand y es: "+y);
        int tileX = (px+OFFSET_X) / TILE_SIZE;
        //int tileY = (py+OFFSET_Y) / TILE_SIZE;
        int tileY = (py+OFFSET_Y) / TILE_SIZE;

        int cx = tileX * TILE_SIZE;
        int cy = tileY * TILE_SIZE;

        g.setColor(0xFF0000);

        g.drawRect(cx, cy, TILE_SIZE, TILE_SIZE);
    }
    
    private void dibujarColisionDebug_talvez_mas_adelante(int px, int py) {

        System.out.println("x es: " + x + "\nand y es: " + y);

        int cx = px + OFFSET_X;
        int cy = py + OFFSET_Y;

        g.setColor(0xFF0000);
        g.drawRect(cx, cy, w, h);
    }
    
    private void verificarSiTenemosCiertaLlave(){

        int x = getWidth() - 24;
        int y = 8;
        int margen = 6;

        if(tieneLlaveBronce && !llaveBronceUsada){

            g.drawImage(
                llaveBronceImagen,
                x,
                y,
                Graphics.TOP | Graphics.LEFT
            );

            y += llaveBronceImagen.getHeight() + margen;
        }

        if(tieneLlavePlata && !llavePlataUsada){

            g.drawImage(
                llavePlataImagen,
                x,
                y,
                Graphics.TOP | Graphics.LEFT
            );

            y += llavePlataImagen.getHeight() + margen;
        }

        if(tieneLlaveOro && !llaveOroUsada){

            g.drawImage(
                llaveOroImagen,
                x,
                y,
                Graphics.TOP | Graphics.LEFT
            );
        }
    }
    
    private void verificarSiTenemosCiertaLlaveddd(){
        if(tieneLlaveBronce && !llaveBronceUsada){

            g.drawImage(
                llaveBronceImagen,
                getWidth() - 20,
                5,
                Graphics.TOP | Graphics.LEFT
            );
        }
        
        if(tieneLlavePlata && !llavePlataUsada){

            g.drawImage(
                llavePlataImagen,
                getWidth() - 20,
                5,
                Graphics.TOP | Graphics.LEFT
            );
        }
        
        if(tieneLlaveOro && !llaveOroUsada){

            g.drawImage(
                llaveOroImagen,
                getWidth() - 20,
                5,
                Graphics.TOP | Graphics.LEFT
            );
        }
    }

    private void render() {
        
        // LIMPIAR PANTALLA
        g.setColor(0);
        g.fillRect(0, 0, getWidth(), getHeight());
        

        // MOVER CAMARA
        g.translate(-camX, -camY);

        // DIBUJAR MAPA
        nivel.paint(g);
        
        
        if(nivelTerminado){
            g.translate(camX, camY);

            g.setColor(colorTexto);

            if(this.todasLasZanahoriasRecogidas && this.todosLosHuevosDejados){
                g.drawString(
                    textoReiniciarNivel,
                    getWidth() / 2,
                    getHeight() / 2 - 10,
                    Graphics.HCENTER | Graphics.TOP
                );

                g.drawString(
                    textoReiniciarNivel1,
                    getWidth() / 2,
                    getHeight() / 2 + 10,
                    Graphics.HCENTER | Graphics.TOP
                );
            }
            
            if(this.gameOver){
                g.drawString(
                    textoReiniciarNivel2,
                    getWidth() / 2,
                    getHeight() / 2 - 10,
                    Graphics.HCENTER | Graphics.TOP
                );

                g.drawString(
                    textoReiniciarNivel3,
                    getWidth() / 2,
                    getHeight() / 2 + 10,
                    Graphics.HCENTER | Graphics.TOP
                );
            }

            return;
        }
        
        //dibujar la meta normal o su sprite de animación
        verificarSiActivarMeta();
        
        // DIBUJAR PLAYER
        player.paint(g);
        
        // DEBUG COLISION
        //dibujarColisionDebug(x, y - SPEED);
        

        // RESTAURAR
        g.translate(camX, camY);
        
        
        int yTexto = 0;

        if (mostrarMensajeCantidadZanahoriasPorRecoger) {
            g.setColor(colorTexto);

            g.drawImage(
                imagencitaObjetoPorRecoger,
                X_IMAGEN,
                yTexto,
                Graphics.TOP | Graphics.LEFT
            );

            g.drawString(
                "" + totalZanahoriasRecogerEnNivel,
                X_NUMERO,
                yTexto,
                Graphics.TOP | Graphics.LEFT
            );

            yTexto += Math.max(
                imagencitaObjetoPorRecoger.getHeight(),
                g.getFont().getHeight()
            );
        }
        
        //Si el tipo de tiles PNG es el (tileset_normal), indica que si es posible que exista huevos por recoger
        if (this.tipoDeTilesPngUtilizado == 1) {
            if (mostrarMensajeCantidadHuevosPorDejar) {
                g.setColor(colorTexto);

                g.drawImage(
                    imagencitaHuevo,
                    X_IMAGEN,
                    yTexto,
                    Graphics.TOP | Graphics.LEFT
                );

                g.drawString(
                    "" + totalHuevosDejarEnNivel,
                    X_NUMERO,
                    yTexto,
                    Graphics.TOP | Graphics.LEFT
                );

                yTexto += Math.max(
                    imagencitaHuevo.getHeight(),
                    g.getFont().getHeight()
                );
            }
        }
        
        
        
        verificarSiTenemosCiertaLlave();
        
        
        
         if(modoAparecer || !desaparecerMensajeNivel){
             
            Font fuente = Font.getFont(
                Font.FACE_SYSTEM,
                Font.STYLE_BOLD,
                Font.SIZE_LARGE
            );

            g.setFont(fuente);
            g.setColor(colorTexto);

            g.drawString(
                nombreNivel,
                (getWidth() / 2),
                getHeight() / 2,
                Graphics.HCENTER | Graphics.TOP
            );

            // cuando termina modoAparecer
            if(!modoAparecer){

                if(System.currentTimeMillis() - tiempoMensaje >= duracionMensaje){

                    desaparecerMensajeNivel = true;
                }
            }
        }
    }
}