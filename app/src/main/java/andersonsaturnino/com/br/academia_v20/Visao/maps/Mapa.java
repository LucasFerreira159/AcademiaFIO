package andersonsaturnino.com.br.academia_v20.Visao.maps;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.FileOutputStream;

import andersonsaturnino.com.br.academia_v20.R;

public class Mapa extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng ourinhos = new LatLng(-22.97, -49.87);
        mMap.addMarker(new MarkerOptions().position(ourinhos).title("Marcado em Ourinhos"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ourinhos));
    }

    public void capturarTela() {
        GoogleMap.SnapshotReadyCallback callback = new GoogleMap.SnapshotReadyCallback() {
            Bitmap bitmap;

            @Override
            public void onSnapshotReady(Bitmap snapshot) {
                bitmap = snapshot;
                File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                try {
                    FileOutputStream out = new FileOutputStream("/mnt/sdcard/" + "MapaTela" + System.currentTimeMillis() + ".png");
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                    out.close();

                } catch (Exception e) {
                    Toast.makeText(Mapa.this, "Erro ao Salvar a Tela" + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        };
        mMap.snapshot(callback);
        Toast.makeText(Mapa.this,"Capturado Com Sucesso!", Toast.LENGTH_SHORT).show();
    }

    public void btnCapturaTela(View view) {
        capturarTela();
    }
}
