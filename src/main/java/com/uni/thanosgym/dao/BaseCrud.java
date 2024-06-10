package com.uni.thanosgym.dao;

import com.uni.thanosgym.config.DbConnection;
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

    /**
     * Crea un nuevo registro en la base de datos.
     *
     * @param data     El objeto que contiene los datos a insertar.
     * @param query    La consulta SQL para insertar el nuevo registro.
     * @param callback El callback que extrae los datos necesarios del objeto
     *                 preparado.
     * @return Una respuesta que indica si la operación fue exitosa o no.
     *         Si es exitosa, devuelve una respuesta con un mensaje indicando que se
     *         creó correctamente y el nuevo registro.
     *         Si ocurre una excepción, devuelve una respuesta con el error.
     */
    public Response<T> baseCreate(T data, String query, PrepareExtractor<T> callback) {
        try {
            final T newData = callback.extractData(ps);
            return new Response<T>(true, "Se creo el plan con exito", newData);
        } catch (Exception e) {
            return somethingWentWrong(e);
        }
    }

    /**
     * Elimina un registro de la base de datos basado en su ID.
     *
     * @param id       El ID del registro a eliminar.
     * @param consulta La consulta SQL para eliminar el registro.
     * @return Una respuesta que indica si la operación fue exitosa o no.
     *         Si es exitosa, devuelve una respuesta con un mensaje indicando que se
     *         eliminó correctamente.
     *         Si ocurre una excepción, devuelve una respuesta con el error.
     */
    public Response<T> baseDeleteById(int id, String consulta) {
        try {
            ps = connection.prepareStatement(consulta);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            return new Response<T>(true, "Se elimino correctamente");
        } catch (Exception e) {
            return somethingWentWrong(e);
        }
    }

    /**
     * Crea un nuevo registro en la base de datos si se cumplen todas las
     * condiciones especificadas.
     *
     * @param data         El objeto que contiene los datos a insertar.
     * @param query        La consulta SQL para insertar el nuevo registro.
     * @param conditions   Un arreglo de booleanos que representan las condiciones
     *                     que deben cumplirse para proceder con la creación.
     * @param errorMessage El mensaje de error que se devuelve si alguna de las
     *                     condiciones no se cumple.
     * @param callback     El callback que extrae los datos necesarios del objeto
     *                     preparado.
     * @return Una respuesta que indica si la operación fue exitosa o no.
     *         Si todas las condiciones son verdaderas y la creación es exitosa,
     *         devuelve una respuesta con un mensaje indicando que se creó
     *         correctamente y el nuevo registro.
     *         Si alguna condición es falsa, devuelve una respuesta con el mensaje
     *         de error especificado.
     *         Si ocurre una excepción, devuelve una respuesta con el error.
     */
    public Response<T> baseCreateWithConditions(T data, String query, boolean[] conditions, String errorMessage,
            PrepareExtractor<T> callback) {
        try {
            if (areAllTrue(conditions)) {
                return baseCreate(data, query, callback);
            } else {
                return new Response<T>(false, errorMessage);
            }
        } catch (Exception e) {
            return somethingWentWrong(e);
        }
    }

    /**
     * Lee un registro de la base de datos utilizando un valor de identidad en forma
     * de cadena y una consulta SQL dada.
     *
     * @param identity El valor de identidad en forma de cadena que se utilizará
     *                 para buscar el registro.
     * @param consulta La consulta SQL para buscar el registro.
     * @param callback El callback que extrae los datos del objeto.
     * @return Una respuesta que indica si la operación fue exitosa o no.
     *         Si el registro es encontrado, devuelve una respuesta con un mensaje
     *         indicando que se encontró y los datos del registro.
     *         Si no se encuentra el registro, devuelve una respuesta con un mensaje
     *         indicando que no se encontró.
     *         Si ocurre una excepción, devuelve una respuesta con el error.
     */
    public Response<T> baseReadByIdentity(String identity, String consulta, ResultSetExtractor<T> callback) {
        try {
            ps = connection.prepareStatement(consulta);
            ps.setString(1, identity);
            rs = ps.executeQuery();
            if (rs.next()) {
                T data = callback.extractData(rs);
                return new Response<T>(true, "No encontrado", data);
            } else {
                return new Response<T>(false, "No encontrado");
            }
        } catch (Exception e) {
            return somethingWentWrong(e);
        }
    }

    /**
     * Lee un registro de la base de datos utilizando un valor de identidad en forma
     * de entero y una consulta SQL dada.
     *
     * @param identity El valor de identidad en forma de entero que se utilizará
     *                 para buscar el registro.
     * @param consulta La consulta SQL para buscar el registro.
     * @param callback Funcion que extrae los datos del objeto.
     * @return Una respuesta que indica si la operación fue exitosa o no.
     *         Si el registro es encontrado, devuelve una respuesta con un mensaje
     *         indicando que se encontró y los datos del registro.
     *         Si no se encuentra el registro, devuelve una respuesta con un mensaje
     *         indicando que no se encontró.
     *         Si ocurre una excepción, devuelve una respuesta con el error.
     */
    public Response<T> baseReadByIdentity(int identity, String consulta, ResultSetExtractor<T> callback) {
        try {
            ps = connection.prepareStatement(consulta);
            ps.setInt(1, identity);
            rs = ps.executeQuery();
            if (rs.next()) {
                T data = callback.extractData(rs);
                return new Response<T>(true, "No encontrado", data);
            } else {
                return new Response<T>(false, "No encontrado");
            }
        } catch (Exception e) {
            return somethingWentWrong(e);
        }
    }

    /**
     * Lee todos los registros de la base de datos utilizando una consulta SQL dada.
     *
     * @param consulta La consulta SQL para obtener todos los registros.
     * @param callback El callback que extrae los datos necesarios de cada
     *                 objeto.
     * @return Una respuesta que indica si la operación fue exitosa o no.
     *         Si la operación es exitosa, devuelve una respuesta con una lista de
     *         todos los registros obtenidos.
     *         Si ocurre una excepción, devuelve una respuesta con el error.
     */
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
            return somethingWentWrong(e);
        }
    }

    /**
     * Actualiza un registro en la base de datos utilizando una consulta SQL dada y
     * los datos proporcionados.
     *
     * @param consulta La consulta SQL para actualizar el registro.
     * @param data     El objeto que contiene los nuevos datos para actualizar.
     * @return Una respuesta que indica si la operación fue exitosa o no.
     *         Si la operación es exitosa, devuelve una respuesta con un mensaje
     *         indicando que los datos se actualizaron con éxito.
     *         Si ocurre una excepción, devuelve una respuesta con el error.
     */
    public Response<T> baseUpdate(String consulta, T data) {
        try {
            sendObject(ps, consulta, data);
            return new Response<T>(true, "Datos actualizados con exito");
        } catch (Exception e) {
            return somethingWentWrong(e);
        }
    }

    /**
     * Genera un objeto de tipo T utilizando los datos proporcionados en el
     * ResultSet.
     *
     * @param rs El ResultSet que contiene los datos para generar el objeto.
     * @return Un objeto de tipo T generado a partir de los datos del ResultSet.
     * @throws SQLException Si ocurre algún error al acceder a los datos del
     *                      ResultSet.
     */
    public abstract T generateObject(ResultSet rs) throws SQLException;

    /**
     * Envía un objeto de tipo T a la base de datos utilizando una consulta SQL y un
     * PreparedStatement dados.
     *
     * @param ps       El PreparedStatement utilizado para ejecutar la consulta SQL.
     * @param consulta La consulta SQL que se ejecutará para enviar el objeto a la
     *                 base de datos.
     * @param data     El objeto de tipo T que se enviará a la base de datos.
     * @return El objeto de tipo T que se envió a la base de datos.
     * @throws SQLException Si ocurre algún error al enviar el objeto a la base de
     *                      datos.
     */
    public abstract T sendObject(PreparedStatement ps, String consulta, T data) throws SQLException;

    public Response<T> somethingWentWrong(Exception e) {
        System.out.println(e);
        return new Response<T>(false, "Algo salio mal");
    }

    private boolean areAllTrue(boolean[] array) {
        for (boolean b : array)
            if (!b)
                return false;
        return true;
    }
}
