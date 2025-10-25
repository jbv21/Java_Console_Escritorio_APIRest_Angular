package pe.com.galaxy.training.console.app;

import pe.com.galaxy.training.console.bean.ubicacion.Departamento;
import pe.com.galaxy.training.console.bean.ubicacion.Distrito;
import pe.com.galaxy.training.console.bean.ubicacion.Provincia;
import pe.com.galaxy.training.console.persistence.ubicacion.*;
import pe.com.galaxy.training.console.persistence.util.PersistenciaException;

import java.util.List;
import java.util.Scanner;

public class AppUbigeo {
    private static DepartamentoBD departamentoBD = new DepartamentoBDImpl();
    private static ProvinciaBD provinciaBD = new ProvinciaBDImpl();
    private static DistritoBD distritoBD = new DistritoBDImpl();

    public static void mostrarMenuUbigeo(Scanner sc){
        int opcion;

        do {
            System.out.println("\n=== MENÚ UBIGEO ===");
            System.out.println("1. Listar Departamentos");
            System.out.println("2. Listar Provincias por Departamento");
            System.out.println("3. Listar Distritos por Provincia");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            switch (opcion) {
                case 1 -> listarDepartamentos(sc);
                case 2 -> listarProvincias(sc);
                case 3 -> listarDistritos(sc);
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void listarDepartamentos(Scanner sc) {
        try {
            List<Departamento> departamentos = departamentoBD.listar(new Departamento());
            System.out.println("\n--- Departamentos ---");
            for (Departamento d : departamentos) {
                System.out.println(d.toString());
            }
        } catch (PersistenciaException e) {
            throw new RuntimeException(e);
        }

    }

    private static void listarProvincias(Scanner sc) {
        try {
            System.out.print("Ingrese ID de Departamento: ");
            String idDep = sc.next();

            Departamento dep = new Departamento();
            dep.setId(idDep);

            Provincia provFiltro = new Provincia();
            provFiltro.setDepartamento(dep);

            List<Provincia> provincias = provinciaBD.listar(provFiltro);
            System.out.println("\n--- Provincias ---");
            for (Provincia p : provincias) {
                System.out.println(p.toString());
            }
        }  catch (PersistenciaException e) {
            throw new RuntimeException(e);
        }
    }

    private static void listarDistritos(Scanner sc) {
        try {
            System.out.print("Ingrese ID de Departamento: ");
            String idDep = sc.next();

            Departamento dep = new Departamento();
            dep.setId(idDep);

            System.out.print("Ingrese ID de Provincia: ");
            String idProv = sc.next();

            Provincia prov = new Provincia();
            prov.setId(idProv);
            prov.setDepartamento(dep);

            Distrito distFiltro = new Distrito();
            distFiltro.setProvincia(prov);

            List<Distrito> distritos = distritoBD.listar(distFiltro);
            System.out.println("\n--- Distritos ---");
            if(distritos == null || distritos.isEmpty()) {
                System.out.println("\n--- No Hay resultados ---");
            } else {
                for (Distrito d : distritos) {
                    System.out.println(d.toString());
                }
            }

        } catch (PersistenciaException e) {
            throw new RuntimeException(e);
        }
    }
}
