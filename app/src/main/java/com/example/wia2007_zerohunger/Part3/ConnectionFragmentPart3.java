package com.example.wia2007_zerohunger.Part3;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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

        if (getArguments() != null) {
            double distance = getArguments().getDouble("distance");
            String product = getArguments().getString("product");
            double price = getArguments().getDouble("price");

            Log.d("ConnectionFragmentPart3", "Distance: " + distance);
            Log.d("ConnectionFragmentPart3", "Product: " + product);
            Log.d("ConnectionFragmentPart3", "Price: " + price);

            filterListBasedOnCondition(distance, product, price);
        }

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

        //Show Dialog Fragment
        searchP3F1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                ConnectionFragmentFilterPart3 connectionFragmentFilterPart3 = new ConnectionFragmentFilterPart3();
                fragmentTransaction.replace(R.id.viewPageMainPart3, connectionFragmentFilterPart3);
                fragmentTransaction.commit();

            }
        });

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

    private void filterListBasedOnCondition(double distance, String product, double price) {
        List<Connection> filteredList = new ArrayList<>();
        String lowerCaseProduct = product.toLowerCase();

        connectionViewModel.getAllConnections().observe(getViewLifecycleOwner(), new Observer<List<Connection>>() {
            @Override
            public void onChanged(List<Connection> connections) {

                for(Connection connection : connections) {
                    double connectionDistance = connection.getConnectionDistance();
                    String connectionProduct = connection.getConnectionProduct().toLowerCase();

                    double connectionPrice1 = connection.getProduct1Price();
                    double connectionPrice2 = connection.getProduct2Price();

                    Log.d("distance", String.valueOf(distance));
                    Log.d("product", product);
                    Log.d("price", String.valueOf(price));

                    Log.d("connectionDistance", String.valueOf(connectionDistance));
                    Log.d("connectionProduct", connectionProduct);
                    Log.d("connectionPrice1", String.valueOf(connectionPrice1));
                    Log.d("connectionPrice2", String.valueOf(connectionPrice2));

                    if (distance <= connectionDistance && connectionProduct.toLowerCase().equals(lowerCaseProduct)
                            && ((price >= connectionPrice1 && price <= connectionPrice2) ||
                                    price >= connectionPrice2 && price >= connectionPrice1)) {

                        filteredList.add(connection);
                    }

                }

                Log.d("Filter List Size", String.valueOf(filteredList.size()));

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