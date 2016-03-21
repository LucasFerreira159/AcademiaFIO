package andersonsaturnino.com.br.academia_v20.Visao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import andersonsaturnino.com.br.academia_v20.R;

/**
 * Classe responsavel por realizar login
 * tendo opçao de conta local ou integraçao com facebook
 *
 * @author Lucas Ferreira
 * Criada em 16/03/2016
 */
public class LoginActivity extends AppCompatActivity {

    private Button logarFace;
    private Button logarLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this, Cadastro_Usuario.class);
                startActivity(it);
            }
        });
    }
}
