package servicios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Film;

public class ServicioJDBCFilms extends ServicioJDBC {

    public ServicioJDBCFilms() {
        super();
    }
    
    public boolean agregarNuevoFilm(int idFilm,String nombreFilm,String directorFilm){
        boolean result = false;
        try {
            String sql = String.format("INSERT INTO APP.FILMS VALUES(%d,'%s','%s')", 
                                       idFilm,nombreFilm,directorFilm);
            Statement pstAgregaFilm =  super.getConn().createStatement();
            pstAgregaFilm.execute(sql);
            result = true;
            pstAgregaFilm.close();
            return result; 
        } catch (SQLException ex) {
            Logger.getLogger(ServicioJDBCFilms.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }
    }
    
    private Film getFirstFilm(){
         Film oFilm=new Film(); 
         try {
            String sqlSeleccionaFilm = 
            String.format("SELECT IDFILM,NOMBREFILM,DIRECTORFILM FROM APP.FILMS ");
            Statement stSeleccionaFilm = super.getConn().createStatement();
            ResultSet rsFilm = stSeleccionaFilm.executeQuery(sqlSeleccionaFilm);
            if(rsFilm.next()){   
                oFilm.setId(rsFilm.getInt("IDFILM"));
                oFilm.setName(rsFilm.getString("NOMBREFILM"));
                oFilm.setAutor(rsFilm.getString("DIRECTORFILM"));
            }
            rsFilm.close();
            stSeleccionaFilm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServicioJDBCFilms.class.getName()).log(Level.SEVERE, null, ex);
        }
         return oFilm;
    }
    
    public Film getFilmByID(int idFilm){
        Film oFilm=new Film();
        try {
            String sqlSeleccionaFilmPorID = 
                   String.format("SELECT IDFILM,NOMBREFILM,DIRECTORFILM FROM APP.FILMS "
                               + " WHERE IDFILM = %d ", 
                                       idFilm);
            Statement stSeleccionaFilmPorID = super.getConn().createStatement();
            ResultSet rsFilm = stSeleccionaFilmPorID.executeQuery(sqlSeleccionaFilmPorID);
            if(rsFilm.next()){   
                oFilm.setId(rsFilm.getInt("IDFILM"));
                oFilm.setName(rsFilm.getString("NOMBREFILM"));
                oFilm.setAutor(rsFilm.getString("DIRECTORFILM"));
            }
            rsFilm.close();
            stSeleccionaFilmPorID.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServicioJDBCFilms.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oFilm;
    }
    
    public boolean deleteFilmByID(int idFilm){
        boolean result = false;
        try {    
            String sqlBorrarFilmPorID = 
                       String.format("DELETE FROM APP.FILMS "
                                   + " WHERE IDFILM = %d ", 
                                           idFilm);
            Statement pstDeleteFilm =  super.getConn().createStatement();
                pstDeleteFilm.execute(sqlBorrarFilmPorID);
                result = true;
                pstDeleteFilm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServicioJDBCFilms.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public List<Film> getAllFilms(){
        List<Film> films = new ArrayList<Film>();
        try { 
            String sqlSeleccionaFilms = 
                       String.format("SELECT IDFILM,NOMBREFILM,DIRECTORFILM FROM APP.FILMS ");
                Statement stSeleccionaFilms = super.getConn().createStatement();
                ResultSet rsFilms = stSeleccionaFilms.executeQuery(sqlSeleccionaFilms);
                while(rsFilms.next()){  
                    Film oFilm = new Film();
                    oFilm.setId(rsFilms.getInt("IDFILM"));
                    oFilm.setName(rsFilms.getString("NOMBREFILM"));
                    oFilm.setAutor(rsFilms.getString("DIRECTORFILM"));
                    films.add(oFilm);
                    oFilm=null;
                }
                rsFilms.close();
                stSeleccionaFilms.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServicioJDBCFilms.class.getName()).log(Level.SEVERE, null, ex);
        }
        return films;
    }

    public boolean agregarNuevoFilm(Film oF) {
        return this.agregarNuevoFilm(oF.getId(),oF.getName(),oF.getAutor());
    }

    public Film getFilmByID(String idFilm) {
        try{
            return getFilmByID(Integer.parseInt(idFilm));
        }catch(RuntimeException e){
            return null;
        }
    }
      
}
