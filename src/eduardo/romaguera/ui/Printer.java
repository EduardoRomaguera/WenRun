package eduardo.romaguera.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Printer {
    private PrintStream out = System.out;
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public void imprimirMensaje(String mensaje) throws IOException {
        out.println(mensaje);
    }

}
