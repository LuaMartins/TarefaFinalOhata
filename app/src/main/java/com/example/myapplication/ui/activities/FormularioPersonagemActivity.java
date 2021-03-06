package com.example.myapplication.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.PersonagemDAO;
import com.example.myapplication.model.Personagem;

import java.io.Serializable;

public class FormularioPersonagemActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem Personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        setTitle("Formulário de Personagem"); //settar o titulo

        //PersonagemDAO dao = new PersonagemDAO(); // criando banco de data
        inicializacaoCampos();

        Button botaosalvar = findViewById(R.id.button_salvar); // pegando botão para colocar ações
        botaosalvar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // convertendo os textos
                String nome = campoNome.getText().toString();
                String nascimento = campoNascimento.getText().toString();
                String altura = campoAltura.getText().toString();

                Personagem personagemSalvo = new Personagem(nome, altura, nascimento);
                dao.salva(personagemSalvo); // utilizar o metodo salvar para salvar os personagens
                finish(); // finalizou e fechou

                // buscando para modificar informações
                personagemSalvo.setNome(nome);
                personagemSalvo.setNome(altura);
                personagemSalvo.setNome(nascimento);
                dao.editar(personagemSalvo);



                Intent dados = getIntent(); // instanciei meus dados
                if(dados.hasExtra("personagem")) {
                    Personagem personagem = (Personagem) dados.getSerializableExtra("personagem");
                    //buscando as 3 informações
                    campoNome.setText(personagem.getNome());
                    campoAltura.setText(personagem.getAltura());
                    campoNascimento.setText(personagem.getNascimento());
                } else{
                    Personagem = new Personagem();
                }


            }
        });
    }

    private void inicializacaoCampos() {
        // pegando os id's
        campoNome = findViewById(R.id.editText_nome);
        campoNascimento = findViewById(R.id.editText_nascimento);
        campoAltura = findViewById(R.id.editText_altura);
    }


}