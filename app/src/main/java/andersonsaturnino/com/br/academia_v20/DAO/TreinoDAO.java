package andersonsaturnino.com.br.academia_v20.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import andersonsaturnino.com.br.academia_v20.Controle.ConexaoBD;
import andersonsaturnino.com.br.academia_v20.Modelo.Treinos;

/**
 * Created by AndersonLuizRamos on 29/02/2016.
 */
public class TreinoDAO {

    private SQLiteDatabase bd;
    private String retorno;

    //Construtor...
    public TreinoDAO (Context context){
        ConexaoBD con = new ConexaoBD(context);
        bd = con.getWritableDatabase();
    }

    public TreinoDAO() {

    }

    public String incluirDAO(Treinos treinos) {

        ContentValues dados = new ContentValues();
        dados.put("descricao",treinos.getDescricao());

        try{
            bd.insert("treinos", null, dados);
            retorno = "Dados incluidos com Sucesso!!";
            Log.i("INFOBD", retorno);
            return retorno;

        }catch (Exception e){
            retorno = String.format("Erro ao Incluir: %s", e.getMessage());
            Log.i("INFOBD", retorno);
            return retorno;
        }
    }

    public List<String> carregaComboTreinosDAO(){
        List<String> lista = new ArrayList<String>();
        String[] colunas = new String[]{"_codigo", "descricao"};

        Cursor cursor = bd.query("treinos", colunas, null, null, null, null, "descricao ASC");

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

    public Treinos buscaPorCodigo(long codigo) {
        String[] colunas = new String[]{"_codigo", "descricao"};
        Cursor cursor = bd.query("treinos",colunas,String.format("_codigo = %s", String.valueOf(codigo)),null, null, null, null, null);
        Treinos treinos = new Treinos();
        if(cursor != null ){
            cursor.moveToFirst();
            treinos.setCodigo(cursor.getInt(cursor.getColumnIndex("_codigo")));
            treinos.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
        }else{
            cursor.close();
        }
        return treinos;
    }
}
