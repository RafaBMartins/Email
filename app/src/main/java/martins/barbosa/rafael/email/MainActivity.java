package martins.barbosa.rafael.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //pegando o botão enviar
        Button btnEnviar = findViewById(R.id.btnEnviar);
        //setando um ouvidor de cliques para o botão enviar
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            //metodo executado ao clique
            @Override
            public void onClick(View view) {
                //pegando os campos email, assunto e texto e armazenando em variaveis
                EditText etEmail = findViewById(R.id.etEmail);
                String email = etEmail.getText().toString();
                EditText etAssunto = findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString();
                EditText etTexto = findViewById(R.id.etTexto);
                String texto = etTexto.getText().toString();

                //criando uma intenção implicita para resolver a ação sendto
                Intent i = new Intent(Intent.ACTION_SENDTO);

                //setando o protocolo de envio de email para a intenção
                i.setData(Uri.parse("mailto:"));

                //inserindo na intenção as informações para envio do email(emails, assunto e texto)
                String[] emails = new String[]{email};
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);

                //tentando iniciar a intenção, verificando os apps que atendem a ação e protocolo
                try {
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                }
                //se não achar nenhum app emite um aviso
                catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Não há nenhum app que posso realizar essa operação", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}