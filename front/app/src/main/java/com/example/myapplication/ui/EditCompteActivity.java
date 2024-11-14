package com.example.myapplication.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.api.ApiClient;
import com.example.myapplication.api.CompteApi;
import com.example.myapplication.model.Compte;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditCompteActivity extends AppCompatActivity {
    private EditText editSolde, editType;
    private Button saveButton;
    private Long compteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_compte);

        editSolde = findViewById(R.id.editSolde);
        editType = findViewById(R.id.editType);
        saveButton = findViewById(R.id.saveButton);

        compteId = getIntent().getLongExtra("compte_id", -1);

        if (compteId != -1) {
            fetchCompteDetails();
        }

        saveButton.setOnClickListener(v -> saveCompte());
    }

    private void fetchCompteDetails() {
        CompteApi api = ApiClient.getClient("application/json").create(CompteApi.class);
        Call<Compte> call = api.getCompte(compteId);

        call.enqueue(new Callback<Compte>() {
            @Override
            public void onResponse(Call<Compte> call, Response<Compte> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Compte compte = response.body();
                    editSolde.setText(String.valueOf(compte.getSolde()));
                    editType.setText(compte.getType());
                }
            }

            @Override
            public void onFailure(Call<Compte> call, Throwable t) {
                Toast.makeText(EditCompteActivity.this, "Erreur lors de la récupération", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveCompte() {
        double solde = Double.parseDouble(editSolde.getText().toString());
        String type = editType.getText().toString();

        Compte updatedCompte = new Compte(compteId, solde, type);

        CompteApi api = ApiClient.getClient("application/json").create(CompteApi.class);
        Call<Compte> call = api.updateCompte(compteId, updatedCompte);

        call.enqueue(new Callback<Compte>() {
            @Override
            public void onResponse(Call<Compte> call, Response<Compte> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EditCompteActivity.this, "Compte modifié avec succès", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK); // Notify MainActivity
                    finish();
                } else {
                    Toast.makeText(EditCompteActivity.this, "Erreur lors de la modification", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Compte> call, Throwable t) {
                Toast.makeText(EditCompteActivity.this, "Erreur réseau : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
