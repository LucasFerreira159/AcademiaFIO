package andersonsaturnino.com.br.academia_v20.Visao.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import andersonsaturnino.com.br.academia_v20.DAO.AgendamentoDAO;
import andersonsaturnino.com.br.academia_v20.Modelo.Agendamentos;
import andersonsaturnino.com.br.academia_v20.R;

public class Lista_Treinos extends AppCompatActivity {

    AgendamentoDAO agendamentoDAO;

    ListView mainListView;
    ArrayAdapter<String> listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_treinos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainListView = (ListView) findViewById(R.id.lvTreinos);
        registerForContextMenu(mainListView);
        agendamentoDAO = new AgendamentoDAO(getApplicationContext());
        ArrayList<String> dadosListView = new ArrayList<>();
        List<Agendamentos> registros = new ArrayList<>();
        registros = agendamentoDAO.listaAgendamentosPorSemana(getApplicationContext());

        for (int i = 0; i < registros.size(); i++) {
            dadosListView.add("Treino: " + registros.get(i).getTreino().getDescricao() + "\nSéries: " + registros.get(i).getSerie() + " - Repetições: " + registros.get(i).getRepeticao() + " - Qtde Peso: " + registros.get(i).getPeso());
        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.linha_lista_treino, R.id.lbLinhaListView, dadosListView);
        mainListView.setAdapter(listAdapter);

        mainListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                //long chave = adapter.getItemAtPosition(position);
                //Toast.makeText(Lista_Treinos.this, "Agendamento Selecionado: " + chave, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.menu_contexto_linha_lista_treino, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuEditar:
                Toast.makeText(this, "Edição", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuExcluir:
                Toast.makeText(this, "Exclusão", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }
}
