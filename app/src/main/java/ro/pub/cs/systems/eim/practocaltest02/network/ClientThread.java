package ro.pub.cs.systems.eim.practocaltest02.network;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import ro.pub.cs.systems.eim.practocaltest02.general.Constants;
import ro.pub.cs.systems.eim.practocaltest02.general.Utilities;

public class ClientThread extends Thread {
    private String address;
    private int port;
    private String operator1;
    private String operator2;
    private TextView operationsTextView;
    private int operation;

    private Socket socket = null;

    public ClientThread(String address, int port, String operator1, String operator2, TextView operationsTextView, int operation) {
        this.address = address;
        this.port = port;
        this.operator1 = operator1;
        this.operator2 = operator2;
        this.operation = operation;
        this.operationsTextView = operationsTextView;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(address, port);
            if (socket == null) {
                Log.e(Constants.TAG, "[CLIENT THREAD] Could not create socket!");
                return;
            }
            BufferedReader bufferedReader = Utilities.getReader(socket);
            PrintWriter printWriter = Utilities.getWriter(socket);
            if (bufferedReader == null || printWriter == null) {
                Log.e(Constants.TAG, "[CLIENT THREAD] Buffered Reader / Print Writer are null!");
                return;
            }
            printWriter.println(operator1);
            printWriter.flush();
            printWriter.println(operator2);
            printWriter.flush();

            String result;

            while ((result = bufferedReader.readLine()) != null) {
                final String finalizedInformation = result;
                operationsTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        operationsTextView.setText(finalizedInformation);
                    }
                });

            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
