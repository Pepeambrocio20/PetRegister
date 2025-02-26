package co.edu.unipiloto.petregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class PetAdapter extends ArrayAdapter<Pet> {

    public PetAdapter(Context context, ArrayList<Pet> pets) {
        super(context, 0, pets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Pet pet = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(pet.getName() + " - " + pet.getBreed()); // Muestra el nombre y la raza

        return convertView;
    }
}