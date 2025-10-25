package pe.com.galaxy.training.escritorio.persistence.ubicacion;

import pe.com.galaxy.training.escritorio.bean.ubicacion.Provincia;
import pe.com.galaxy.training.escritorio.persistence.util.PersistenciaException;
import pe.com.galaxy.training.escritorio.util.BdConexion;
import pe.com.galaxy.training.escritorio.util.BdParametros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProvinciaBDImpl implements ProvinciaBD {
    @Override
    public List<Provincia> listar(Provincia obj) throws PersistenciaException {
        try {
            String sql = "SELECT ID_PRO, NOMBRE_PRO " +
                    " FROM UBIGEO " +
                    " WHERE ID_DEP = ? " +
                    " GROUP BY " +
                    "   ID_PRO, NOMBRE_PRO ";
            Connection cn = BdConexion.getConexion(BdParametros.BD_URL,BdParametros.BD_USUARIO, BdParametros.BD_CLAVE);
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, obj.getDepartamento().getId());
            ResultSet rs = ps.executeQuery();
            List<Provincia> listObj = this.getMapping(rs);
            rs.close();ps.close();cn.close();
            return listObj.isEmpty() ? Collections.emptyList() : listObj;
        } catch (SQLException e) {
            System.err.println("==> ERROR: listar <====");
            System.err.println(e.getMessage());
            return Collections.emptyList();
        }
    }
    
    @Override
    public Provincia seleccionar(Provincia obj) throws PersistenciaException {
        try {
            String sql = "SELECT ID_PRO, NOMBRE_PRO " +
                    " FROM UBIGEO " +
                    " WHERE ID_DEP = ? " +
                    "   AND ID_PRO = ? " +
                    " GROUP BY " +
                    "   ID_PRO, NOMBRE_PRO ";
            Connection cn = BdConexion.getConexion(BdParametros.BD_URL,BdParametros.BD_USUARIO, BdParametros.BD_CLAVE);
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, obj.getDepartamento().getId());
            ps.setString(2, obj.getId());
            ResultSet rs = ps.executeQuery();
            List<Provincia> listObj = this.getMapping(rs);
            rs.close();ps.close();cn.close();
            return listObj.isEmpty() ? null : listObj.getFirst();
        } catch (SQLException e) {
            System.err.println("==> ERROR: seleccionar <====");
            System.err.println(e.getMessage());
            return null;
        }
    }

    private List<Provincia> getMapping(ResultSet rs) throws SQLException {
        List<Provincia> listObj = new ArrayList<>();
        while (rs.next()){
            Provincia obj = new Provincia();
            obj.setId(rs.getString("ID_PRO"));
            obj.setNombre(rs.getString("NOMBRE_PRO"));
            listObj.add(obj);
        }
        return listObj;
    }
    
}
