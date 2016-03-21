package andersonsaturnino.com.br.academia_v20.Visao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import andersonsaturnino.com.br.academia_v20.DAO.AgendamentoDAO;
import andersonsaturnino.com.br.academia_v20.DAO.SemanaDAO;
import andersonsaturnino.com.br.academia_v20.DAO.TreinoDAO;
import andersonsaturnino.com.br.academia_v20.Modelo.Agendamentos;
import andersonsaturnino.com.br.academia_v20.Modelo.Semanas;
import andersonsaturnino.com.br.academia_v20.Modelo.Treinos;
import andersonsaturnino.com.br.academia_v20.R;
import fr.ganfra.materialspinner.MaterialSpinner;

public class Cadastro_Agenda extends AppCompatActivity {

    SemanaDAO semanaDAO;
    TreinoDAO treinoDAO;
    AgendamentoDAO agendamentoDAO;
    Agendamentos agendamentos = new Agendamentos();
    private MaterialSpinner cbSemana;
    private MaterialSpinner cbTreinos;
    private EditText edtSerie;
    private EditText edtRepeticoes;
    private EditText edtPeso;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_agenda);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Popular Spinner Treinos*/
        cbTreinos = (MaterialSpinner) findViewById(R.id.cbTreino);
        treinoDAO = new TreinoDAO(getApplicationContext());
        List<String> listaTreinos = treinoDAO.carregaComboTreinosDAO();
        ArrayAdapter<String> adapterTreinos = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaTreinos);
        adapterTreinos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cbTreinos.setAdapter(adapterTreinos);

        /*Popular Spinner Semana*/
        cbSemana = (MaterialSpinner) findViewById(R.id.cbSemana);
        semanaDAO = new SemanaDAO(getApplicationContext());
        List<String> listaSemana = semanaDAO.carregaComboSemanasDAO();
        ArrayAdapter<String> adapterSemana = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaSemana);
        adapterSemana.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cbSemana.setAdapter(adapterSemana);

        edtSerie = (EditText)findViewById(R.id.edtSerie);
        edtRepeticoes = (EditText)findViewById(R.id.edtRepeticoes);
        edtPeso = (EditText)findViewById(R.id.edtPeso);
    }

    public void btnSalvarClick(View view) {

        // Verifica se a Semana foi selecionado;
        if (cbSemana.getSelectedItemId() == 0) {
            Toast.makeText(this, "Selecione Um Dia Da Semana Antes de Salvar!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verifica se o Treino foi selecionado;
        if (cbTreinos.getSelectedItemId() == 0) {
            Toast.makeText(this, "Selecione Um Treino Antes de Salvar!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verifica se a Série foi digitada;
        if (edtSerie.getText().length() == 0) {
            Toast.makeText(this, "Informe a Série Antes de Salvar!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verifica se a Repetição foi digitada;
        if (edtRepeticoes.getText().length() == 0) {
            Toast.makeText(this, "Informe a Repetição Antes de Salvar!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verifica se o Peso foi digitada;
        if (edtPeso.getText().length() == 0) {
            Toast.makeText(this, "Informe o Peso Antes de Salvar!", Toast.LENGTH_SHORT).show();
            return;
        }

        try{
            long codSemana = cbSemana.getSelectedItemId();
            semanaDAO = new SemanaDAO(this);
            Semanas semana =  semanaDAO.buscaPorCodigo(codSemana);

            long codTreino= cbTreinos.getSelectedItemId();
            treinoDAO = new TreinoDAO(this);
            Treinos treinos = treinoDAO.buscaPorCodigo(codTreino);

            agendamentoDAO = new AgendamentoDAO(this);
            agendamentos.setSemana(semana);
            agendamentos.setTreino(treinos);
            agendamentos.setPeso(Integer.parseInt(edtPeso.getText().toString()));
            agendamentos.setRepeticao(Integer.parseInt(edtRepeticoes.getText().toString()));
            agendamentos.setSerie(Integer.parseInt(edtSerie.getText().toString()));

            String msg = agendamentoDAO.incluirAgendamento(agendamentos);

            limpaCampos();

            Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction("Action", null).show();


        }catch (Exception e) {
            Log.i("ERRO", e.getMessage().toString());
        }
    }

    public void btnListaTreinosClick(View view) {
        intent = new Intent(this, Lista_Treinos.class);
        startActivity(intent);
    }

    public void btnExcluirTreinoClick(View view) {
        Snackbar.make(view, "Evento Excluir Não Implementado", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    private void limpaCampos() {
        cbSemana.setSelection(0);
        cbTreinos.setSelection(0);
        edtSerie.setText(null);
        edtRepeticoes.setText(null);
        edtPeso.setText(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mn_cadastro_agenda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mnSalvarAgenda) {
            // Verifica se a Semana foi selecionado;
            if (cbSemana.getSelectedItemId() == 0) {
                Toast.makeText(this, "Selecione Um Dia Da Semana Antes de Salvar!", Toast.LENGTH_SHORT).show();
            }

            // Verifica se o Treino foi selecionado;
            if (cbTreinos.getSelectedItemId() == 0) {
                Toast.makeText(this, "Selecione Um Treino Antes de Salvar!", Toast.LENGTH_SHORT).show();
            }

            // Verifica se a Série foi digitada;
            if (edtSerie.getText().length() == 0) {
                Toast.makeText(this, "Informe a Série Antes de Salvar!", Toast.LENGTH_SHORT).show();
            }

            // Verifica se a Repetição foi digitada;
            if (edtRepeticoes.getText().length() == 0) {
                Toast.makeText(this, "Informe a Repetição Antes de Salvar!", Toast.LENGTH_SHORT).show();
            }

            // Verifica se o Peso foi digitada;
            if (edtPeso.getText().length() == 0) {
                Toast.makeText(this, "Informe o Peso Antes de Salvar!", Toast.LENGTH_SHORT).show();
            }

            try {
                long codSemana = cbSemana.getSelectedItemId();
                semanaDAO = new SemanaDAO(this);
                Semanas semana = semanaDAO.buscaPorCodigo(codSemana);

                long codTreino = cbTreinos.getSelectedItemId();
                treinoDAO = new TreinoDAO(this);
                Treinos treinos = treinoDAO.buscaPorCodigo(codTreino);

                agendamentoDAO = new AgendamentoDAO(this);
                agendamentos.setSemana(semana);
                agendamentos.setTreino(treinos);
                agendamentos.setPeso(Integer.parseInt(edtPeso.getText().toString()));
                agendamentos.setRepeticao(Integer.parseInt(edtRepeticoes.getText().toString()));
                agendamentos.setSerie(Integer.parseInt(edtSerie.getText().toString()));

                String msg = agendamentoDAO.incluirAgendamento(agendamentos);

                limpaCampos();

                Toast.makeText(Cadastro_Agenda.this, "Exercicio Agendado", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.i("ERRO", e.getMessage().toString());
                Toast.makeText(Cadastro_Agenda.this, "Erro: " + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
