package pe.com.galaxy.training.console.persistence.ubicacion;

import pe.com.galaxy.training.console.bean.ubicacion.Departamento;
import pe.com.galaxy.training.console.persistence.util.PersistenciaException;
import pe.com.galaxy.training.console.util.BdConexion;
import pe.com.galaxy.training.console.util.BdParametros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DepartamentoBDImpl implements DepartamentoBD {

    @Override
    public List<Departamento> listar(Departamento obj) throws PersistenciaException {
        try {
            String sql = "SELECT ID_DEP, NOMBRE_DEP " +
                        " FROM UBIGEO " +
                        " GROUP BY " +
                        "   ID_DEP, NOMBRE_DEP ";
            Connection cn = BdConexion.getConexion(BdParametros.BD_URL,BdParametros.BD_USUARIO, BdParametros.BD_CLAVE);
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Departamento> listObj = this.getMapping(rs);
            rs.close();ps.close();cn.close();
            return listObj.isEmpty() ? Collections.emptyList() : listObj;
        } catch (Exception e) {
            System.err.println("==> ERROR: listar <====");
            System.err.println(e.getMessage());
            return null;
        }
    }

    private List<Departamento> getMapping(ResultSet rs) throws SQLException {
        List<Departamento> listObj = new ArrayList<>();
        while (rs.next()) {
            Departamento obj = new Departamento();
            obj.setId(rs.getString("ID_DEP"));
            obj.setNombre(rs.getString("NOMBRE_DEP"));
            listObj.add(obj);
        }
        return listObj;
    }
}
