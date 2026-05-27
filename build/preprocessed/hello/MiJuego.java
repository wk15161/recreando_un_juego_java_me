package hello;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.io.IOException;
import javax.microedition.media.*;
import java.io.InputStream;

public class MiJuego extends GameCanvas implements Runnable {
    // Tamaño de cada tile del mapa (en píxeles)
    private static final int TILE_SIZE = 16;

    // Offset del jugador para ajustar el punto de colisión (hitbox)
    private static final int OFFSET_X = 9;
    private static final int OFFSET_Y = 24;
    
    private int nivelSiguiente=0;
    private static final int totalNiveles = 2;
    
    private String textoObjetoPorRecoger;
    private String textoReiniciarNivel;
    private String textoReiniciarNivel1;
    private int colorTexto;
    private Player musicaFondo;

    private Player sonidoZanahoria;

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
    
    private Image llavePlataImagen;

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
    private final int MAP_W = 20 * 16;
    private final int MAP_H = 20 * 16;

    // VELOCIDAD
    private int SPEED;
    
    //INICIO TITLE
    private static final int TILE_SPAWN = 22;
    
    //META TITLE
    private static final int TILE_META = 45;

    //ZANAHORIAS
    private static final int TILE_ZANAHORIA = 20;
    private static final int TILE_ZANAHORIA_NO = 21;
    
    // TILES SOLIDOS
    private static final int TILE_PARED = 1;
    private static final int TILE_CERCA = 14;
    private static final int TILE_CAMINO = 19;
    private static final int TILE_LLAVE_PLATEADO = 33;
    private static final int TILE_CANDADO_PLATEADO = 34;

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
            musicaFondo.setLoopCount(-1);
            musicaFondo.prefetch();



            InputStream zanahoria = getClass().getResourceAsStream("/carrot.wav");

            sonidoZanahoria = Manager.createPlayer(zanahoria, "audio/X-wav");

            sonidoZanahoria.realize();
            sonidoZanahoria.prefetch();



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
            repetirVariasVecesDesaparicion = 3;
            contadordeRepeticionesDeDesaparicion = 0;
            llavePlataUsada = false;
            nombreNivel = "Nivel #"+ nivelSiguiente;
            this.textoObjetoPorRecoger = "Zanahorias: ";
            this.textoObjetoPorRecoger = "Estrellas: ";
            this.textoReiniciarNivel = "Nivel #"+ nivelSiguiente+ " completo :D";
            this.textoReiniciarNivel1 = "Presione boton del centro";
            this.colorTexto = 0xFFFFFF;
            this.colorTexto = 0x000000;
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
            }

            
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
            
            //contar cantidad de zanahorias que es necesario recoger en el nivel
            contarTotalDeZanahoriasPorRecoger();
            
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
            
            //obtener SPRITE DE LA META
            meta =gd.getBobby_meta();
            
            //obtener imagen de llave plateada
            llavePlataImagen = Image.createImage("/llave_plata.png");

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

            if(sonidoZanahoria != null){

                sonidoZanahoria.close();
                sonidoZanahoria = null;
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

        if(!todasLasZanahoriasRecogidas && !modoAparecer){
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

            if ((key & RIGHT_PRESSED) != 0) {

                player = right;

                if (!colision(x + SPEED, y)) {
                    x += SPEED;
                }

                moviendo = true;
            }
            // =====================================
            // ARRIBA
            // =====================================

            if ((key & UP_PRESSED) != 0) {

                player = up;

                if (!colision(x, y - SPEED)) {
                    y -= SPEED;
                }

                moviendo = true;
            }

            // =====================================
            // ABAJO
            // =====================================

            if ((key & DOWN_PRESSED) != 0) {

                player = down;

                if (!colision(x, y + SPEED)) {
                    y += SPEED;
                }

                moviendo = true;
            }
        }else{
            if ((key & FIRE_PRESSED) != 0) {
                //Usado para reiniciar nivel o cambiar nivel
                inicializarVariables();
            }
        }
        

        // =====================================
        // ANIMACION
        // =====================================

        if (moviendo) {

            player.nextFrame();

        } else {
            if(todasLasZanahoriasRecogidas){
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
        
        if(!todasLasZanahoriasRecogidas){
            down.setPosition(x, y);
            up.setPosition(x, y);
            left.setPosition(x, y);
            right.setPosition(x, y);
            aparecer.setPosition(x,y);
            desaparecer.setPosition(x,y);   
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

        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {

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

        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {

                int tile = nivel.getCell(x, y);

                if (tile == TILE_ZANAHORIA) { // TILE DE INICIO
                    totalZanahoriasRecogerEnNivel++;
                }
            }
        }
    }    
    
    
    
    private void verificarSiNosotrosTomamosUnaLlave(int px, int py){
        //revizamos esto
        int tileX = (px + OFFSET_X) / TILE_SIZE;
        int tileY = (py + OFFSET_Y) / TILE_SIZE;

        int tile = nivel.getCell(tileX, tileY);
        
        if (tile == TILE_LLAVE_PLATEADO && !tieneLlavePlata) {
            tieneLlavePlata = true;
            nivel.setCell(tileX, tileY, TILE_CAMINO); // desaparece la llave
        }
    }
    
    
    private void verificarSiActivarMeta(){

        for(int _y = 0; _y < 20; _y++){

            for(int _x = 0; _x < 20; _x++){

                int tile = nivel.getCell(_x, _y);

                if(tile == TILE_META){

                    if(this.totalZanahoriasRecogerEnNivel == 0){

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
        if (tile == TILE_META && totalZanahoriasRecogerEnNivel == 0 && !todasLasZanahoriasRecogidas) {
            this.detenerMusicaFondo();
            this.iniciarSonidoMeta();
            moviendo = false;
            todasLasZanahoriasRecogidas = true;
            player = desaparecer;
            player.setFrame(0);
            // FRAME QUIETO
        }
    }
    
    private boolean verificarCandados(int px, int py){
        int tileX = (px + OFFSET_X) / TILE_SIZE;
        int tileY = (py + OFFSET_Y) / TILE_SIZE;

        int tile = nivel.getCell(tileX, tileY);
        
        //Si nosotros tenemos la llave lista
        if (tile == TILE_CANDADO_PLATEADO && tieneLlavePlata && !llavePlataUsada) {
            nivel.setCell(tileX, tileY, TILE_CAMINO); // se abre 
            llavePlataUsada = true;
            tieneLlavePlata = false;
            return true;
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

    private boolean colision(int px, int py) {

        int tileX = (px+OFFSET_X) / TILE_SIZE;
        int tileY = (py+OFFSET_Y) / TILE_SIZE;

        int tile = nivel.getCell(tileX, tileY);
        
        //VERIFICAMOS ZANAHORIAS
        verificarZanahorias(px,py);
        //verificar si hemos tomando una llave
        verificarSiNosotrosTomamosUnaLlave(x, y - SPEED);
        
        verificarSiTocamosLaMeta(x, y - SPEED);
        
        if(verificarCandados(px,py)) return true;
        
        //verificar diferentes colisiones para ver si debe haber paso o no
        if((tile >=0 && tile <=18)||(tile==34 || tile ==36 || tile==38)) return true;

        if (tile == TILE_PARED) return true;

        if (tile == TILE_CERCA) return true;

        if (tile == TILE_CANDADO_PLATEADO) return true;

        return false;
    }
    
    

    // =========================================
    // DIBUJAR
    // =========================================
    
    private void dibujarColisionDebug(int px, int py) {
        System.out.println("x es: "+ x+"\nand y es: "+y);
        int tileX = (px+OFFSET_X) / TILE_SIZE;
        int tileY = (py+OFFSET_Y) / TILE_SIZE;

        int cx = tileX * TILE_SIZE;
        int cy = tileY * TILE_SIZE;

        g.setColor(0xFF0000);

        g.drawRect(cx, cy, TILE_SIZE, TILE_SIZE);
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
        
        g.setColor(colorTexto);

        g.drawString(
            textoObjetoPorRecoger+totalZanahoriasRecogerEnNivel,
            0,
            0,
            Graphics.TOP | Graphics.LEFT
        );
        
        if(tieneLlavePlata && !llavePlataUsada){

            g.drawImage(
                llavePlataImagen,
                getWidth() - 20,
                5,
                Graphics.TOP | Graphics.LEFT
            );
        }

         if(modoAparecer || !desaparecerMensajeNivel){
             
            Font fuente = Font.getFont(
                Font.FACE_SYSTEM,
                Font.STYLE_PLAIN,
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