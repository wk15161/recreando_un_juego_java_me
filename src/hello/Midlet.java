package hello;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Midlet extends MIDlet {

    private Display display;
    private MiJuego juego;

    public Midlet() {
        display = Display.getDisplay(this);
        juego = new MiJuego();
    }

    public void startApp() {
        display.setCurrent(juego);
        juego.start();
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}