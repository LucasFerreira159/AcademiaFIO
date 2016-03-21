package andersonsaturnino.com.br.academia_v20.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import andersonsaturnino.com.br.academia_v20.Controle.ConexaoBD;
import andersonsaturnino.com.br.academia_v20.Modelo.Agendamentos;
import andersonsaturnino.com.br.academia_v20.Modelo.Semanas;
import andersonsaturnino.com.br.academia_v20.Modelo.Treinos;

/**
 * Created by AndersonLuizRamos on 06/03/2016.
 */
public class AgendamentoDAO {

    private SQLiteDatabase bd;
    private String retorno;

    public AgendamentoDAO( Context context){
        ConexaoBD con = new ConexaoBD(context);
        bd = con.getWritableDatabase();
    }

    public String incluirAgendamento(Agendamentos agendamentos) {

        ContentValues dados = new ContentValues();
        dados.put("semana", agendamentos.getSemana().getCodigo());
        dados.put("treino", agendamentos.getTreino().getCodigo());
        dados.put("serie", agendamentos.getSerie());
        dados.put("repeticao",agendamentos.getRepeticao());
        dados.put("peso",agendamentos.getPeso());
        try{
            bd.insert("agendamentos", null, dados);
            retorno = "Dados incluidos com Sucesso!!";
            Log.i("INFOBD", retorno);
            return retorno;

        }catch (Exception e){
            retorno = String.format("Erro ao Incluir: %s", e.getMessage());
            Log.i("INFOBD", retorno);
            return retorno;
        }
    }

    public List<Agendamentos> listaAgendamentosPorSemana(Context context) {
        List<Agendamentos> lista = new ArrayList<Agendamentos>();

        try{
            Cursor cursor = bd.rawQuery("select " +
                    "ag._codigo, " +
                    "ag.treino, " +
                    "ag.semana, " +
                    "ag.serie, " +
                    "ag.repeticao, " +
                    "ag.peso " +
                    "from agendamentos ag " +
                    " order by ag.semana, ag.treino ", null);

            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                do{
                    SemanaDAO semanaDAO = new SemanaDAO(context);
                    Semanas semanas = semanaDAO.buscaPorCodigo(cursor.getLong(cursor.getColumnIndex("semana")));

                    TreinoDAO treinoDAO = new TreinoDAO(context);
                    Treinos treinos = treinoDAO.buscaPorCodigo(cursor.getLong(cursor.getColumnIndex("treino")));

                    Agendamentos agendamentos = new Agendamentos();
                    agendamentos.setCodigo(cursor.getInt(cursor.getColumnIndex("_codigo")));
                    agendamentos.setTreino(treinos);
                    agendamentos.setSemana(semanas);
                    agendamentos.setSerie(cursor.getInt(cursor.getColumnIndex("serie")));
                    agendamentos.setRepeticao(cursor.getInt(cursor.getColumnIndex("repeticao")));
                    agendamentos.setPeso(cursor.getInt(cursor.getColumnIndex("peso")));

                    lista.add(agendamentos);
                }while(cursor.moveToNext());
            }
            return(lista);
        }catch (Exception e){
            Log.i("INFOBD","Erro ao Listar Agendamentos: " + e.getMessage());
            return null;
        }

    }

    public Agendamentos listaAgendamentosPorSemana(Context context, long semana){
        String[] colunas = {"_codigo", "treino", "semana", "serie", "repeticao", "peso"};
        Cursor cursor = bd.query("agendamentos",colunas,String.format("_semana = %s",String.valueOf(semana)),null,null,null,null);
        Agendamentos agendamentos = new Agendamentos();
        if (cursor != null){
            cursor.moveToFirst();
            agendamentos.setCodigo(cursor.getInt(cursor.getColumnIndex("_codigo")));
            //agendamentos.setTreino(cursor.getString(cursor.getColumnIndex("descricao")));
           // agendamentos.setSeman(cursor.getString(cursor.getColumnIndex("descricao")));
           // agendamentos.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
          //  agendamentos.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
        }else{
            cursor.close();
        }
        return agendamentos;
    }
}
