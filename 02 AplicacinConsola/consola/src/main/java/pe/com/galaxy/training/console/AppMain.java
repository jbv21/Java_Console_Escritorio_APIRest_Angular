package pe.com.galaxy.training.console;

import pe.com.galaxy.training.console.app.AppEscuelaConduccion;
import pe.com.galaxy.training.console.app.AppUbigeo;

import java.util.Scanner;

public class AppMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("===== MENÚ PRINCIPAL =====");
            System.out.println("1. Gestión de Ubigeo");
            System.out.println("2. Gestión de Escuelas de Conducción");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    AppUbigeo.mostrarMenuUbigeo(sc);
                    break;
                case 2:
                    AppEscuelaConduccion.mostrarMenuEscuela(sc);
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }

        } while (opcion != 3);

        sc.close();
    }
}
