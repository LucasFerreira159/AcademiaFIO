package andersonsaturnino.com.br.academia_v20.Controle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by AndersonLuizRamos on 29/02/2016
 */
public class ConexaoBD extends SQLiteOpenHelper {

    private static final String NOME_BD = "Academia";
    private static final int VERSAO_BD = 10;

    public ConexaoBD(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            //Criação da tabela Treinos;
            db.execSQL("create table treinos(_codigo integer primary key autoincrement, descricao varchar(30) not null)");
            Log.i("INFOBD", "Tabela Treinos Criada com Sucesso!");

            //Criação da tabela Usuarios;
            db.execSQL("create table usuarios(_codigo integer primary key autoincrement, nome varchar(30) not null, datanasc date, sexo varchar(3))");
            Log.i("INFOBD", "Tabela Usuarios Criada com Sucesso!");

            //Criação da tabela Semanas;
            db.execSQL("create table semanas(_codigo integer primary key autoincrement, descricao varchar(30) not null)");
            db.execSQL("insert into semanas(descricao) values ('Segunda-Feira')");
            db.execSQL("insert into semanas(descricao) values ('Terça-Feira')");
            db.execSQL("insert into semanas(descricao) values ('Quarta-Feira')");
            db.execSQL("insert into semanas(descricao) values ('Quinta-Feira')");
            db.execSQL("insert into semanas(descricao) values ('Sexta-Feira')");
            db.execSQL("insert into semanas(descricao) values ('Sábado')");
            db.execSQL("insert into semanas(descricao) values ('Domingo')");
            Log.i("INFOBD", "Tabela Semanas Criada com Sucesso!");

            //Criação da tabela Agendamentos;
            db.execSQL("create table agendamentos(_codigo integer primary key autoincrement, semana integer not null, treino integer not null, serie integer, repeticao integer, peso integer, FOREIGN KEY(semana) REFERENCES semanas(_codigo), FOREIGN KEY(treino) REFERENCES treinos(_codigo))");
            Log.i("INFOBD", "Tabela Agendamentos Criada com Sucesso!");

        }catch (Exception e){
            Log.i("INFOBD","Erro ao Criar o Banco: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            if(newVersion>oldVersion) {
                db.execSQL("drop table treinos");
                Log.i("INFOBD", "Tabela Treinos 'Dropada'.");

                db.execSQL("drop table semanas");
                Log.i("INFOBD", "Tabela Semanas 'Dropada'.");

                db.execSQL("drop table usuarios");
                Log.i("INFOBD", "Tabela Usuarios 'Dropada'.");

                db.execSQL("drop table agendamentos");
                Log.i("INFOBD", "Tabela Agendamentos 'Dropada'.");

                onCreate(db);
            }
        }catch (Exception e){
            Log.i("INFOBD","Erro ao Excluir Tabelas: " + e.getMessage());
        }
    }
}
