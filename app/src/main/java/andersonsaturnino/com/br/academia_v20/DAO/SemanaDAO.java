package andersonsaturnino.com.br.academia_v20.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import andersonsaturnino.com.br.academia_v20.Controle.ConexaoBD;
import andersonsaturnino.com.br.academia_v20.Modelo.Semanas;

/**
 * Created by AndersonLuizRamos on 06/03/2016.
 */
public class SemanaDAO {

    private SQLiteDatabase bd;

    public SemanaDAO(Context context) {
        ConexaoBD con = new ConexaoBD(context);
        bd = con.getWritableDatabase();
    }

    public SemanaDAO() {
        ConexaoBD con = new ConexaoBD(null);
        bd = con.getWritableDatabase();
    }

    public List<String> carregaComboSemanasDAO(){
        List<String> lista = new ArrayList<String>();
        String[] colunas = new String[]{"_codigo", "descricao"};

        Cursor cursor = bd.query("semanas", colunas, null, null, null, null, "_codigo ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                String codigo = String.valueOf(cursor.getInt(0));
                String descricao = (cursor.getString(1));

                lista.add(codigo + " - " + descricao );
            }while(cursor.moveToNext());
        }

        return(lista);
    }

    public Semanas buscaPorCodigo(long codigo) {
        String[] colunas = {"_codigo","descricao"};
         Cursor cursor = bd.query("semanas",colunas,String.format("_codigo = %s",String.valueOf(codigo)),null,null,null,null);
        Semanas semanas = new Semanas();
        if (cursor != null){
            cursor.moveToFirst();
            semanas.setCodigo(cursor.getInt(cursor.getColumnIndex("_codigo")));
            semanas.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
        }else{
            cursor.close();
        }
        return semanas;
    }
}
