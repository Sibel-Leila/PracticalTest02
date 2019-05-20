package ro.pub.cs.systems.eim.practocaltest02.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ro.pub.cs.systems.eim.practocaltest02.R;
import ro.pub.cs.systems.eim.practocaltest02.model.Operations;
import ro.pub.cs.systems.eim.practocaltest02.network.ClientThread;
import ro.pub.cs.systems.eim.practocaltest02.network.ServerThread;

public class PracticalTest02MainActivity extends AppCompatActivity {

    // Server widgets
    private EditText serverPortEditText = null;
    private Button connectButton = null;

    // Client widgets
    private EditText clientAddressEditText = null;
    private EditText clientPortEditText = null;
    private EditText operator1EditText = null;
    private EditText operator2EditText = null;
    private Button addButton = null;
    private Button mulButton = null;
    private TextView resultTextView = null;

    private ServerThread serverThread = null;

    private ClientThread clientThread = null;

    private AddButtonClickListener addButtonClickListener = new AddButtonClickListener();
    private class AddButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            String serverPort = serverPortEditText.getText().toString();

            serverThread = new ServerThread(Integer.parseInt(serverPort), 0);

            serverThread.start();
        }
    }

    private MulButtonClickListener  mulButtonClickListener = new MulButtonClickListener();
    private class MulButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            String clientAddress = clientAddressEditText.getText().toString();
            String clientPort = clientPortEditText.getText().toString();

            String operator1 = operator1EditText.getText().toString();
            String operator2 = operator2EditText.getText().toString();

            Operations operations = new Operations(Integer.parseInt(operator1), Integer.parseInt(operator2));

            clientThread = new ClientThread(
                    clientAddress, Integer.parseInt(clientPort), operator1, operator2, resultTextView, 1);

            clientThread.start();

        }
    }

    private ConnectButtonClickListener connectButtonClickListener = new ConnectButtonClickListener();
    private class ConnectButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            String serverPort = serverPortEditText.getText().toString();

            serverThread = new ServerThread(Integer.parseInt(serverPort), 1);

            serverThread.start();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serverPortEditText = (EditText)findViewById(R.id.server_port_edit_text);
        connectButton = (Button) findViewById(R.id.connect_button);

        connectButton.setOnClickListener(connectButtonClickListener);

        clientAddressEditText = (EditText)findViewById(R.id.client_address_edit_text);
        clientPortEditText = (EditText)findViewById(R.id.client_port_edit_text);
        operator1EditText=(EditText)findViewById(R.id.operator1_editText);
        operator2EditText=(EditText)findViewById(R.id.operator2_editText);

        addButton = (Button)findViewById(R.id.add_button);
        addButton.setOnClickListener(addButtonClickListener);

        mulButton = (Button)findViewById(R.id.mul_button);
        mulButton.setOnClickListener(mulButtonClickListener);

        resultTextView = (TextView)findViewById(R.id.result_text_view);
    }
}
