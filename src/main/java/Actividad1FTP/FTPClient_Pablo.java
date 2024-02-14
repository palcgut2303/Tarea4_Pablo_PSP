/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividad1FTP;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author manana
 */
public class FTPClient_Pablo {

    static FTPClient cliente = new FTPClient();//cliente FTP

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        String servidor = "";
        String usuario = "";
        String contrasena = "";
        boolean login;

        
            System.out.println("BIENVENIDO AL SERVIDOR FPT.\nIntroduce el servidor al que conectarte:");
            servidor = sc.nextLine();

            cliente.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
            cliente.connect(servidor);
            cliente.enterLocalPassiveMode();

            System.out.println("CONECTADO AL SERVIDOR!, INTRODUCE TUS CREDENCIALES.\nUSUARIO: ");
            usuario = sc.nextLine();

            System.out.println("CONTRASEÑA: ");
            contrasena = sc.nextLine();

            login = cliente.login(usuario, contrasena);

            if (login) {
                System.out.println("CREDENCIALES CORRECTAS!");
                FTPFile[] files = cliente.listFiles();

                if (files != null) {

                    
                    java.util.Date registroFecha = new java.util.Date(System.currentTimeMillis());

                    String mensaje = "\nHora de conexion: " + registroFecha.toString();
                    InputStream inputStream = new ByteArrayInputStream(mensaje.getBytes(StandardCharsets.UTF_8));

                    cliente.changeWorkingDirectory("LOG");
                    cliente.appendFile("LOG.txt", inputStream);

                } else {
                    System.out.println("No tienes ningun fichero en la carpeta del usuario");
                }

            } else {
                System.out.println("CREDENCIALES INCORRECTAS.");
            }

            
        

    }

}
