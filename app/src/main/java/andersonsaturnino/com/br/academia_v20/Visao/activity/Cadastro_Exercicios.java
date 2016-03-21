package andersonsaturnino.com.br.academia_v20.Visao.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import andersonsaturnino.com.br.academia_v20.DAO.AgendamentoDAO;
import andersonsaturnino.com.br.academia_v20.DAO.SemanaDAO;
import andersonsaturnino.com.br.academia_v20.DAO.TreinoDAO;
import andersonsaturnino.com.br.academia_v20.Modelo.Semanas;
import andersonsaturnino.com.br.academia_v20.Modelo.Treinos;
import andersonsaturnino.com.br.academia_v20.R;


public class Cadastro_Exercicios extends AppCompatActivity {

    TreinoDAO treinoDAO;
    private Treinos treinos = new Treinos();
    private EditText edtDescricao;
    private Button btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_exercicios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Novo Exercicio");
        setSupportActionBar(toolbar);

        edtDescricao = (EditText)findViewById(R.id.edtNomeTreino);
    }

    public void btnConfirmarClick(View view) {
        treinos.setDescricao(edtDescricao.getText().toString());

        treinoDAO = new TreinoDAO(this);
        String msg = treinoDAO.incluirDAO(treinos);
        limpaCampos();

        hideKeyboard(this);
        Snackbar.make(view, "Incluso com Sucesso", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        finish();
    }

    public void btnExcluirClick(View view) {
        hideKeyboard(this);
        Snackbar.make(view, "Comando Excluir Não Implementado", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
    }

    public void btnEditarClick(View view) {
        hideKeyboard(this);
        Snackbar.make(view, "Comando Editar Não Implementado", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void limpaCampos(){
        edtDescricao.setText("");
    }

}
