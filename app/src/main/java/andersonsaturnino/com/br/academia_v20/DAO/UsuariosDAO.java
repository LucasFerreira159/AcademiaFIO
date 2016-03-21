package andersonsaturnino.com.br.academia_v20.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import andersonsaturnino.com.br.academia_v20.Controle.ConexaoBD;
import andersonsaturnino.com.br.academia_v20.Modelo.Usuarios;

/**
 * Created by AndersonLuizRamos on 07/03/2016.
 */
public class UsuariosDAO {
    private SQLiteDatabase bd;
    private String retorno;


    public UsuariosDAO(Context context) {
        ConexaoBD con = new ConexaoBD(context);
        bd = con.getWritableDatabase();
    }

    public String IncluirUsuario(Usuarios usuarios){
        ContentValues dados = new ContentValues();
        dados.put("nome", usuarios.getNome());

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
}
