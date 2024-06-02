package DAO;

import utils.Messages;
import model.*;
import java.util.ArrayList;
import thanosgym.Main;

public class ActualizarListas extends BaseCrud {

    public ActualizarListas() {}
    
    public void actualizarListaPlanes() {
        Main.listaPlanes = new ArrayList<>();
        try {
            rs = st.executeQuery("select * from plan");
            while (rs.next()) {
                Plan pla = new Plan();
                pla.setPlanId(rs.getInt(1));
                pla.setName(rs.getString(2));
                pla.setPrice(rs.getInt(3));
                pla.setDurationDays(rs.getInt(4));
                Main.listaPlanes.add(pla);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error, no se pudo actualizar la lista planes " + e);
        }
    }
}
