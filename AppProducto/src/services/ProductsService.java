package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Producto;
import models.Usuario;

public class ProductsService {
   private final String tabla = "Producto";
   
   public void save(Connection conexion, Producto product,Usuario user) throws SQLException{
      try{
         PreparedStatement consulta;
         if(product.getId_Producto() == null){
            consulta = conexion.prepareStatement("INSERT INTO " + this.tabla + "(nombre, precio) VALUES(?, ?)");
            consulta.setString(1, product.getNombre());
            consulta.setString(2, product.getImagen());
            consulta.setString(3, product.getDescripcion());
            consulta.setFloat(4, product.getPrecio());
            consulta.setInt(5, product.getCant_Stock());
            consulta.setString(6, user.getId_Usuario());
         }else{
            consulta = conexion.prepareStatement("UPDATE " + this.tabla + " SET nombre = ?, precio = ? WHERE id = ?");
            consulta.setString(1, product.getNombre());
            consulta.setString(2, product.getImagen());
            consulta.setString(3, product.getDescripcion());
            consulta.setFloat(4, product.getPrecio());
            consulta.setInt(5, product.getCant_Stock());
            consulta.setString(6, user.getId_Usuario());
         }
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
   
   public Producto getProduct(Connection conexion, String Id_Producto) throws SQLException {
      Producto product = null;
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT nombre,imagen,descripcion,precio,cant_stock,id_usuario"
                 + " FROM " + this.tabla + " WHERE id_Producto = ?" );
         consulta.setString(1, Id_Producto);
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            product = new Producto(Id_Producto, resultado.getString("Nombre"), resultado.getString("Imagen"), resultado.getString("Descripcion"),
                    resultado.getFloat("Precio"),resultado.getInt("Cant_Stock"),resultado.getString("Id_Usuario"));
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
         consulta.setString(1, product.getId_Producto());
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
   
   public List<Producto> getAllProducts(Connection conexion) throws SQLException{
      List<Producto> ListaProductos = new ArrayList<>();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT Id_Producto,Nombre,Imagen,Descripcion,Precio,Cant_Stock,Id_Usuario "
                 + " FROM " + this.tabla);
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            ListaProductos.add(new Producto(resultado.getString("Id_Producto"), resultado.getString("Nombre"), resultado.getString("Imagen"), resultado.getString("Descripcion"),
                    resultado.getFloat("Precio"),resultado.getInt("Cant_Stock"),resultado.getString("Id_Usuario")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return ListaProductos;
   }
}