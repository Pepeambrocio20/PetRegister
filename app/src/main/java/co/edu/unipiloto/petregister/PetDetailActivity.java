package co.edu.unipiloto.petregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class PetDetailActivity extends AppCompatActivity {

    private EditText etName, etAge, etBreed, etSex, etDescription;
    private Button btnSave;
    private Pet pet;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etBreed = findViewById(R.id.etBreed);
        etSex = findViewById(R.id.etSex);
        etDescription = findViewById(R.id.etDescription);
        btnSave = findViewById(R.id.btnSave);

        Intent intent = getIntent();
        if (intent.hasExtra("pet")) {
            pet = (Pet) intent.getSerializableExtra("pet");
            position = intent.getIntExtra("position", -1);
            etName.setText(pet.getName());
            etAge.setText(String.valueOf(pet.getAge()));
            etBreed.setText(pet.getBreed());
            etSex.setText(pet.getSex());
            etDescription.setText(pet.getDescription());
        } else {
            pet = new Pet("", 0, "", "", "");
        }

        btnSave.setOnClickListener(v -> {
            pet.setName(etName.getText().toString());
            pet.setAge(Integer.parseInt(etAge.getText().toString()));
            pet.setBreed(etBreed.getText().toString());
            pet.setSex(etSex.getText().toString());
            pet.setDescription(etDescription.getText().toString());

            Intent resultIntent = new Intent();
            resultIntent.putExtra("pet", pet);
            resultIntent.putExtra("position", position);
            setResult(RESULT_OK, resultIntent);
            finish(); // Cierra la actividad y devuelve el resultado
        });
    }
}