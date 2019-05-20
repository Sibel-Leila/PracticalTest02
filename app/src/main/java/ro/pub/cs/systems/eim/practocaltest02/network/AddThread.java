package ro.pub.cs.systems.eim.practocaltest02.network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import ro.pub.cs.systems.eim.practocaltest02.general.Constants;
import ro.pub.cs.systems.eim.practocaltest02.general.Utilities;
import ro.pub.cs.systems.eim.practocaltest02.model.Operations;

public class AddThread extends Thread {

    private ServerThread serverThread;
    private Socket socket;

    public AddThread(ServerThread serverThread, Socket socket) {
        this.serverThread = serverThread;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = Utilities.getReader(socket);
            PrintWriter printWriter = Utilities.getWriter(socket);

            if (bufferedReader == null || printWriter == null) {
                Log.e(Constants.TAG, "[COMMUNICATION THREAD] Buffered Reader / Print Writer are null!");
                return;
            }

            Log.i(Constants.TAG, "[COMMUNICATION THREAD] Waiting for parameters from client!");

            String operator1 = bufferedReader.readLine();
            String operator2 = bufferedReader.readLine();

            if (operator1.isEmpty() || operator1 == null || operator2.isEmpty() || operator2 == null) {
                Log.e(Constants.TAG, "[COMMUNICATION THREAD] Error receiving parameters from client (city / information type!");
                return;
            }

            Operations operations = new Operations(Integer.parseInt(operator1), Integer.parseInt(operator2));

            int result = operations.add();


            printWriter.println(result);
            printWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
