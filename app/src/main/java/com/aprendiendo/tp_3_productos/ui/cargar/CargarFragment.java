package com.aprendiendo.tp_3_productos.ui.cargar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.aprendiendo.tp_3_productos.databinding.FragmentCargarBinding;


//1- Crear el Fragment
//2- Crear el objeto binding y el viewModel
//3- Inicializar el binding y el viewModel en el onCreateView
//4- Crear el observer para el mensaje
//5- Setear el mensaje en el textView dentro del observer
//6- Crear el listener del boton cargar
//7- Crear el metodo guardarProducto() en el viewModel
//8- llamar al metodo guardarProducto() en el listener del boton cargar

public class CargarFragment extends Fragment {

    private FragmentCargarBinding binding;
    private CargarViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mv = new ViewModelProvider(this).get(CargarViewModel.class);
        binding = FragmentCargarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mv.getmError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.twMensaje.setText(s);
            }
        });

        mv.getmMExito().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });


        binding.btCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Obtengo los valores de los campos de texto y llamo al metodo guardarProducto() en el viewModel
                String codigo = binding.etCodigo.getText().toString();
                String descripcion = binding.etDescripcion.getText().toString();
                String precio = binding.etPrecio.getText().toString();
                mv.guardarProducto(codigo, descripcion, precio);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}