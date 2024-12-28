package com.example.wia2007_zerohunger.Part3;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wia2007_zerohunger.Part3.ConnectionDatabase.Connection;
import com.example.wia2007_zerohunger.Part3.ConnectionDatabase.ConnectionAdapter;
import com.example.wia2007_zerohunger.Part3.ConnectionDatabase.ConnectionViewModel;
import com.example.wia2007_zerohunger.R;

import java.util.ArrayList;
import java.util.List;


public class ConnectionFragmentPart3 extends Fragment {

    RecyclerView recyclerViewP3F1;
    private ConnectionViewModel connectionViewModel;
    private SearchView editTextFarmerConnectionP3F1;
    private ImageView searchP3F1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_connection_part3, container, false);

        recyclerViewP3F1 = view.findViewById(R.id.recyclerViewP3F1);
        recyclerViewP3F1.setLayoutManager(new LinearLayoutManager(getContext()));

        ConnectionAdapter connectionAdapter = new ConnectionAdapter();
        recyclerViewP3F1.setAdapter(connectionAdapter);

        connectionViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())
                .create(ConnectionViewModel.class);

        connectionViewModel.getAllConnections().observe(getViewLifecycleOwner(), new Observer<List<Connection>>() {
            @Override
            public void onChanged(List<Connection> connections) {
                connectionAdapter.setConnections(connections);
            }
        });

        editTextFarmerConnectionP3F1 = view.findViewById(R.id.editTextFarmerConnectionP3F1);
        editTextFarmerConnectionP3F1.clearFocus();

        editTextFarmerConnectionP3F1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        searchP3F1 = view.findViewById(R.id.searchP3F1);

        return view;
    }

    private void filterList(String newText) {
        List<Connection> filteredList = new ArrayList<>();
        String lowerNewText = newText.toLowerCase();

        connectionViewModel.getAllConnections().observe(getViewLifecycleOwner(), new Observer<List<Connection>>() {
            @Override
            public void onChanged(List<Connection> connections) {
                for (Connection connection : connections) {
                    String connectionName = connection.getConnectionName().toLowerCase();

                    if (connectionName.equals(lowerNewText)) {
                        filteredList.add(connection);
                    }
                }

                ConnectionAdapter connectionAdapter = new ConnectionAdapter();

                if (filteredList.isEmpty()) {
                    connectionAdapter.setConnections(connections);
                    recyclerViewP3F1.setAdapter(connectionAdapter);
                } else {
                    Toast.makeText(getContext(), "Data Found", Toast.LENGTH_SHORT).show();
                    connectionAdapter.setConnections(filteredList);
                    recyclerViewP3F1.setAdapter(connectionAdapter);
                }

            }
        });
    }

}