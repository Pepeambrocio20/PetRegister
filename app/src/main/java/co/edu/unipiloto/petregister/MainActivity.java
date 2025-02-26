package co.edu.unipiloto.petregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewPets;
    private Button btnAddPet;
    private ArrayList<Pet> petList;
    private PetAdapter petAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewPets = findViewById(R.id.listViewPets);
        btnAddPet = findViewById(R.id.btnAddPet);
        petList = new ArrayList<>();
        petAdapter = new PetAdapter(this, petList);
        listViewPets.setAdapter(petAdapter);

        btnAddPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PetDetailActivity.class);
                startActivityForResult(intent, 1); // Usamos startActivityForResult para obtener el resultado
            }
        });

        listViewPets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, PetDetailActivity.class);
                intent.putExtra("pet", petList.get(position));
                intent.putExtra("position", position);
                startActivityForResult(intent, 2); // Usamos startActivityForResult para obtener el resultado
            }
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

            petAdapter.notifyDataSetChanged(); // Actualiza la lista
        }
    }
}