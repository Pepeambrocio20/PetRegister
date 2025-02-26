package co.edu.unipiloto.petregister;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewPets;
    private Button btnAddPet;
    private ArrayList<Pet> petList;
    private PetAdapter petAdapter;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "PetRegisterPrefs";
    private static final String PET_LIST_KEY = "petList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewPets = findViewById(R.id.listViewPets);
        btnAddPet = findViewById(R.id.btnAddPet);
        petList = new ArrayList<>();
        petAdapter = new PetAdapter(this, petList);
        listViewPets.setAdapter(petAdapter);

        // Cargar las mascotas guardadas
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        loadPets();

        btnAddPet.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PetDetailActivity.class);
            startActivityForResult(intent, 1); // Usamos startActivityForResult para obtener el resultado
        });

        listViewPets.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, PetDetailActivity.class);
            intent.putExtra("pet", petList.get(position));
            intent.putExtra("position", position);
            startActivityForResult(intent, 2); // Usamos startActivityForResult para obtener el resultado
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Pet pet = (Pet) data.getSerializableExtra("pet");
            int position = data.getIntExtra("position", -1);

            if (requestCode == 1) { // Código para agregar una nueva mascota
                petList.add(pet);
            } else if (requestCode == 2) { // Código para editar una mascota existente
                if (position != -1) {
                    petList.set(position, pet);
                }
            }

            savePets(); // Guardar las mascotas actualizadas
            petAdapter.notifyDataSetChanged(); // Actualiza la lista
        }
    }

    private void savePets() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(petList);
        editor.putString(PET_LIST_KEY, json);
        editor.apply();
    }

    private void loadPets() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(PET_LIST_KEY, null);
        Type type = new TypeToken<ArrayList<Pet>>() {}.getType();
        ArrayList<Pet> loadedPets = gson.fromJson(json, type);

        if (loadedPets != null) {
            petList.clear();
            petList.addAll(loadedPets);
            petAdapter.notifyDataSetChanged();
        }
    }
}