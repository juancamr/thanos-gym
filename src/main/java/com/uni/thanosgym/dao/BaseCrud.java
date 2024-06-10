package com.uni.thanosgym.dao;

import com.uni.thanosgym.config.DbConnection;
import com.uni.thanosgym.interfaces.ConditionsExtractor;
import com.uni.thanosgym.interfaces.PrepareExtractor;
import com.uni.thanosgym.interfaces.ResultSetExtractor;
import com.uni.thanosgym.model.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseCrud<T> {
    Statement st;
    Connection connection;
    ResultSet rs;
    PreparedStatement ps;

    public BaseCrud() {
        try {
            connection = DbConnection.getConnection();
            st = connection.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Response<T> baseDeleteById(int id, String consulta) {
        try {
            ps = connection.prepareStatement(consulta);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            return new Response<T>(true, "Se elimino correctamente");
        } catch (Exception e) {
            System.out.println(e);
            return new Response<T>(false, "Something went wrong");
        }
    }

    public Response<T> baseReadByIdentity(String identity, String consulta, ResultSetExtractor<T> callback) {
        return readTemplate(identity, consulta, callback, 0, true);
    }

    public Response<T> baseReadByIdentity(int identity, String consulta, ResultSetExtractor<T> callback) {
        return readTemplate("", consulta, callback, identity, true);
    }

    private boolean areAllTrue(boolean[] array) {
        for (boolean b : array)
            if (!b)
                return false;
        return true;
    }

    public Response<T> baseCreate(T data, String query, PrepareExtractor<T> callback) {
        try {
            final T newData = callback.extractData(ps);
            return new Response<T>(true, "Se creo el plan con exito", newData);
        } catch (Exception e) {
            System.out.println(e);
            return new Response<T>(false, "Something went wrong");
        }
    }

    public Response<T> baseCreateWithConditions(T data, String query, boolean[] conditions, String errorMessage,
            PrepareExtractor<T> callback) {
        try {
            if (areAllTrue(conditions)) {
                return baseCreate(data, query, callback);
            } else {
                return new Response<T>(false, errorMessage);
            }
        } catch (Exception e) {
            return new Response<T>(false, "Something went wrong");
        }
    }

    private Response<T> readTemplate(String identiyString, String consulta, ResultSetExtractor<T> callback,
            int identityInt, boolean forString) {
        try {
            ps = connection.prepareStatement(consulta);
            if (forString) {
                ps.setString(1, identiyString);
            } else {
                ps.setInt(1, identityInt);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                T data = callback.extractData(rs);
                return new Response<T>(true, "No encontrado", data);
            } else {
                return new Response<T>(false, "No encontrado");
            }
        } catch (Exception e) {
            System.out.println(e);
            return new Response<T>(false, "Algo salio mal");
        }
    }

    public Response<T> baseReadAll(String consulta, ResultSetExtractor<T> callback) {
        try {
            rs = st.executeQuery(consulta);
            List<T> dataList = new ArrayList<T>();
            while (rs.next()) {
                T data = callback.extractData(rs);
                dataList.add(data);
            }
            rs.close();
            return new Response<T>(true, dataList);
        } catch (Exception e) {
            System.out.println("Error, no se pudo obtener la lista de datos");
            return new Response<T>(false);
        }
    }

    public Response<T> baseUpdate(String consulta, T data) {
        try {
            sendObject(ps, consulta, data);
            return new Response<T>(true, "Datos actualizados con exito");
        } catch (Exception e) {
            System.out.println(e);
            return new Response<T>(false, "Algo salio mal");
        }
    }

    public abstract T generateObject(ResultSet rs) throws SQLException;

    public abstract T sendObject(PreparedStatement ps, String consulta, T data) throws SQLException;
}
