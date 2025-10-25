package pe.com.galaxy.training.escritorio;

import pe.com.galaxy.training.escritorio.app.FrmEscuelaConduccion;


public class AppMain {
    public static void main(String args[]) {
  
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEscuelaConduccion().setVisible(true);
            }
        });
    }
}