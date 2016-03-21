package andersonsaturnino.com.br.academia_v20.Visao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import andersonsaturnino.com.br.academia_v20.R;
import andersonsaturnino.com.br.academia_v20.Visao.adapter.CardAdapter;
import andersonsaturnino.com.br.academia_v20.Visao.maps.Mapa;

public class Principal extends AppCompatActivity {

    Intent intent;
    RecyclerView recyclerItens;
    private RecyclerView.LayoutManager mLayoutMananger;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerItens = (RecyclerView)findViewById(R.id.rv_semanas);
        recyclerItens.setHasFixedSize(true);

        mLayoutMananger = new LinearLayoutManager(this);
        recyclerItens.setLayoutManager(mLayoutMananger);

        mAdapter = new CardAdapter();
        recyclerItens.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mnLogar) {
            Intent login = new Intent(Principal.this, LoginActivity.class);
            startActivity(login);
        }
        if (id == R.id.mnMapa){
            Intent map = new Intent(Principal.this, Mapa.class);
            startActivity(map);
        }

        return super.onOptionsItemSelected(item);
    }

    public void cadTreino(View view) {
        intent = new Intent(this, Cadastro_Exercicios.class);
        startActivity(intent);
    }

    public void cadAgenda(View view) {
        intent = new Intent(this, Cadastro_Agenda.class);
        startActivity(intent);
    }

    public void treinar(View view) {
        intent = new Intent(this, Treinar.class);
        startActivity(intent);
    }

}
