package ro.pub.cs.systems.eim.practocaltest02.network;

import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import ro.pub.cs.systems.eim.practocaltest02.general.Constants;

public class ServerThread extends Thread {
    private int port = 0;
    private ServerSocket serverSocket = null;
    private int operation = 0;

    public ServerThread(int port, int operation) {
        this.port = port; try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException ioException) {
            Log.e(Constants.TAG, "An exception has occurred: " + ioException.getMessage());
            if (Constants.DEBUG) {
                ioException.printStackTrace();
            }
        }
        this.operation = operation;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            Log.i(Constants.TAG, "[SERVER THREAD] Waiting for a client invocation...");
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.i(Constants.TAG, "[SERVER THREAD] A connection request was received from " + socket.getInetAddress() + ":" + socket.getLocalPort());
            if (operation == 0) {
                AddThread addThread = new AddThread(this, socket);
                addThread.start();
            }

            if (operation == 1) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MulThread mulThread = new MulThread(this, socket);
                mulThread.start();
            }

        }
    }
}
