package com.example.jwtaprend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jwtaprend.api.WebServiceApi;
import com.example.jwtaprend.api.WebServiceJWT;
import com.example.jwtaprend.model.Login;

import java.util.List;
import java.util.logging.Level;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtClave;
    private Button btnToken;
    private Button btnObtenerRecurso;
    private Button btnSolicitudTokenErroneo;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();


    }
    private void setUpView() {
      txtUsuario = findViewById(R.id.txtUsuario);
      txtClave = findViewById(R.id.txtClave);
      btnToken = findViewById(R.id.btnToken);
      btnSolicitudTokenErroneo = findViewById(R.id.btnSolicitudTokenError);
      btnObtenerRecurso = findViewById(R.id.btnObtenerRecurso);

      btnToken.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             obtenerToken();
          }
      });

      btnObtenerRecurso.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             obtenerRecursoToken();
          }
      });

      btnSolicitudTokenErroneo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              obtenerRecursoTokenErroneo();
            }
      });


    }

    private void obtenerRecursoTokenErroneo() {
        token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGJlcnRvIiwidXNlcklkIjoiMiIsInJvbGUiOiJBZG1pbiJ9.T0Q44tYoFsCQ5dtJPw0mhmBN19JndrvruCHvV8gmAHM";
        String authHeader =  "Bearer" + token;
        Call<List<String>> call = WebServiceJWT.getInstance().createService(WebServiceApi.class).obtenerRecursosToken(authHeader);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.code()==200) {
                    for (int i = 0; i < response.body().size(); i++) {
                        Log.d("TAG1", "Movimiento Bancario  : " + (i + 1) + " " + response.body().get(i));
                    }
                }
                else {
                    Log.d("TAG1", "Token es incorrecto y no podemos obtener la respuesta");
                }

            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });


    }

    private void obtenerRecursoToken() {
        String  authHeader = "Bearer "+ token;
        Call<List<String>> call = WebServiceJWT.getInstance().createService(WebServiceApi.class).obtenerRecursosToken(authHeader);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.code()==200) {
                    for (int i = 0; i < response.body().size(); i++) {
                        Log.d("TAG1", "Movimiento Bancario  : " + (i + 1) + " " + response.body().get(i));
                    }
                }
                else {
                    Log.d("TAG1", "Token es incorrecto y no podemos obtener la respuesta");
                }

            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });
    }

    private void obtenerToken() {
        Login login = new Login();
        login.setUser(txtUsuario.getText().toString());
        login.setPassword((txtClave.getText().toString()));
        Call<List<String>> call = WebServiceJWT.getInstance().createService(WebServiceApi.class).obtenerToken(login);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.code()==200){
                    token = response.body().get(0);
                    Log.d("TAG1",token);
                }else if(response.code()==401){
                    Log.d("TAG1","No Autorizado");
                    token=null;
                }else{
                    Log.d("TAG1","No obtenido token");
                    token=null;
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });





    }


}
