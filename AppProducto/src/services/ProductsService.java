package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Producto;

public class ProductsService {
   private final String tabla = "Producto";
   
   public void save(Connection conexion, Producto product) throws SQLException{
      try{
         PreparedStatement consulta;
         if(product.getId() == null){
            consulta = conexion.prepareStatement("INSERT INTO " + this.tabla + "(nombre, precio) VALUES(?, ?)");
            consulta.setString(1, product.getNombre());
            consulta.setFloat(2, product.getPrecio());
         }else{
            consulta = conexion.prepareStatement("UPDATE " + this.tabla + " SET nombre = ?, precio = ? WHERE id = ?");
            consulta.setString(1, product.getNombre());
            consulta.setFloat(2, product.getPrecio());
            consulta.setString(3, product.getId());
         }
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
   
   public Producto getProduct(Connection conexion, String idProducto) throws SQLException {
      Producto product = null;
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT nombre,imagen,descripcion,precio,cantstock "
                 + " FROM " + this.tabla + " WHERE idProducto = ?" );
         consulta.setString(1, idProducto);
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            product = new Producto(idProducto, resultado.getString("Nombre"), resultado.getString("Imagen"), resultado.getString("Descripcion"),
                    resultado.getFloat("Precio"),resultado.getInt("CantStock"));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return product;
   }
   
   public void remove(Connection conexion, Producto product) throws SQLException{
      try{
         PreparedStatement consulta = conexion.prepareStatement("DELETE FROM " 
      + this.tabla + " WHERE idProducto = ?");
         consulta.setString(1, product.getId());
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
   
   public List<Producto> getAllProducts(Connection conexion) throws SQLException{
      List<Producto> ListaProductos = new ArrayList<>();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT idProducto,Nombre,Imagen,Descripcion,Precio,Cant_Stock "
                 + " FROM " + this.tabla);
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            ListaProductos.add(new Producto(resultado.getString("idProducto"), resultado.getString("Nombre"), resultado.getString("Imagen"), resultado.getString("Descripcion"),
                    resultado.getFloat("Precio"),resultado.getInt("Cant_Stock")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return ListaProductos;
   }
}