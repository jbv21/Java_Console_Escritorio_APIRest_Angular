package pe.com.galaxy.training.console.app;

import pe.com.galaxy.training.console.bean.escuela.EscuelaConduccion;
import pe.com.galaxy.training.console.persistence.escuela.EscuelaConduccionBD;
import pe.com.galaxy.training.console.persistence.escuela.EscuelaConduccionBDImpl;
import pe.com.galaxy.training.console.persistence.ubicacion.*;
import pe.com.galaxy.training.console.persistence.util.PersistenciaException;

import java.util.List;
import java.util.Scanner;

public class AppEscuelaConduccion {
    private static final EscuelaConduccionBD psBD = new EscuelaConduccionBDImpl();
    private static final DepartamentoBD psBDDep = new DepartamentoBDImpl();
    private static final ProvinciaBD psBDPro = new ProvinciaBDImpl();
    private static final DistritoBD psBDDis = new DistritoBDImpl();


    private static final Scanner sc = new Scanner(System.in);

    public static void mostrarMenuEscuela(Scanner sc){

        int opcion;

        do {
            System.out.println("===== MENÚ ESCUELA DE CONDUCCIÓN =====");
            System.out.println("1. Listar  Escuela");
            System.out.println("2. Buscar Escuelas");
            System.out.println("3. Registrar Escuelas");
            System.out.println("4. Actualizar Escuela");
            System.out.println("5. Eliminar Escuela");
            System.out.println("0. Volver al menú principal");
            System.out.println("Seleccione una opción: ");

            opcion = sc.nextInt();
            switch (opcion) {
                case 1 -> listar(sc);
                case 2 -> buscar(sc);
                case 3 -> insertar(sc);
                case 4 -> actualizar(sc);
                case 5 -> eliminar(sc);
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void listar(Scanner sc) {
        try {

            EscuelaConduccion objFiltro = new EscuelaConduccion();

            System.out.println("Ingrese ID Departamento (o ENTER para todos): ");
            System.out.flush();
            String dep = sc.nextLine().trim();
            objFiltro.setIdDepartamento(dep.isEmpty() ? null : dep);
            sc.nextLine();

            System.out.println("Ingrese ID Provincia (o ENTER para todos): ");
            System.out.flush();
            String prov = sc.nextLine().trim();
            objFiltro.setIdProvincia(prov.isEmpty() ? null : prov);
            //sc.nextLine();

            System.out.println("Ingrese ID Distrito (o ENTER para todos): ");
            System.out.flush();
            String dist = sc.nextLine().trim();
            objFiltro.setIdDistrito(dist.isEmpty() ? null : dist);
            //sc.nextLine();

            List<EscuelaConduccion> objRs = psBD.listar(objFiltro);
            if (objRs == null || objRs.isEmpty()) {
                System.out.println("No se encontraron resultados.");
            } else {
                objRs.forEach(e -> System.out.println( e.toString() ));
            }
        } catch (PersistenciaException e) {
            throw new RuntimeException(e);
        }
    }

    private static void buscar(Scanner sc) {
        try {
            System.out.println("Ingrese ID de la escuela: ");
            int id = sc.nextInt();
            sc.nextLine();

            EscuelaConduccion objFiltro = new EscuelaConduccion(); objFiltro.setId(id);
            EscuelaConduccion objRs = psBD.seleccionar(objFiltro);
            if (objRs == null) {
                System.out.println("No existe escuela con ese ID.");
            } else {

                System.out.println("Encontrado: " + objRs.toString());
            }
        } catch (PersistenciaException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertar(Scanner sc) {
        try {
            EscuelaConduccion objNuevo = new EscuelaConduccion();

            System.out.println("Ingrese RUC: ");
            objNuevo.setRuc(sc.next());
            sc.nextLine();

            // Validar duplicado por RUC
            EscuelaConduccion filtro = new EscuelaConduccion();
            filtro.setRuc(objNuevo.getRuc());
            System.out.println(filtro);
            List<EscuelaConduccion> existentes = psBD.listar(filtro);
            System.out.println(existentes);
            if (existentes != null && !existentes.isEmpty()) {
                System.out.println("Ya existe una escuela con ese RUC.");
                return;
            }

            System.out.println("Ingrese Nombre: ");
            objNuevo.setNombre(sc.next());
            System.out.println("Ingrese Dirección: ");
            objNuevo.setDireccion(sc.next());
            System.out.println("Ingrese Estado: ");
            objNuevo.setEstado(sc.next());
            System.out.println("Ingrese Teléfono: ");
            objNuevo.setTelefono(sc.next());
            System.out.println("Ingrese Correo: ");
            objNuevo.setCorreo(sc.next());
            System.out.println("Ingrese ID Departamento: ");
            objNuevo.setIdDepartamento(sc.next());
            System.out.println("Ingrese ID Provincia: ");
            objNuevo.setIdProvincia(sc.next());
            System.out.println("Ingrese ID Distrito: ");
            objNuevo.setIdDistrito(sc.next());

            if (psBD.insertar(objNuevo)) {
                System.out.println("Registro insertado con ID: " + objNuevo.getId());
            } else {
                System.out.println("Error al insertar.");
            }
        }  catch (PersistenciaException e) {
            throw new RuntimeException(e);
        }
    }

    private static void actualizar(Scanner sc) {
        try {
            System.out.print("Ingrese ID de la escuela a actualizar: ");
            int id = sc.nextInt();
            sc.nextLine();

            EscuelaConduccion objFiltro = new EscuelaConduccion();
            objFiltro.setId(id);
            EscuelaConduccion ojbExistente = psBD.seleccionar(objFiltro);

            if (ojbExistente == null) {
                System.out.println("No existe registro con ese ID.");
                return;
            }

            System.out.println("Nuevo Nombre (" + ojbExistente.getNombre() + "): ");
            String nombre = sc.nextLine();
            if (!nombre.isEmpty()) ojbExistente.setNombre(nombre);

            System.out.println("Nueva Dirección (" + ojbExistente.getDireccion() + "): ");
            String direccion = sc.nextLine();
            if (!direccion.isEmpty()) ojbExistente.setDireccion(direccion);

            if (psBD.actualizar(ojbExistente)) {
                System.out.println("Registro actualizado.");
            } else {
                System.out.println("Error al actualizar.");
            }
        } catch (PersistenciaException e) {
            throw new RuntimeException(e);
        }
    }

    private static void eliminar(Scanner sc) {
        try {
            System.out.println("Ingrese ID de la escuela a eliminar: ");
            int id = sc.nextInt();
            sc.nextLine();

            EscuelaConduccion objFiltro = new EscuelaConduccion();
            objFiltro.setId(id);

            EscuelaConduccion objExistente = psBD.seleccionar(objFiltro);
            if (objExistente == null) {
                System.out.println("No existe registro con ese ID.");
                return;
            }

            if (psBD.eliminar(objFiltro)) {
                System.out.println("Registro eliminado (marcado como inactivo).");
            } else {
                System.out.println("Error al eliminar.");
            }
        } catch (PersistenciaException e) {
            throw new RuntimeException(e);
        }
    }
}
