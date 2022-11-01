package se.selimkose.labb3.Model;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.Socket;

public class ChatModel {

    StringProperty message = new SimpleStringProperty();

    //ArrayList that broadcasts messages to listeners when updated
    ObservableList<String> observableList = FXCollections.observableArrayList();



    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;


    public ChatModel() {

        try {
            socket = new Socket("localhost", 8000);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // sends outgoing stream
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // listens to incoming stream


            //New thread that runs as long as socket connection is up. The thread enables the program
            //to listen to incoming messages while also writing messages in a different thread
            var thread = new Thread(() -> {
                try {
                    while (socket.isConnected()) {
                        String line = reader.readLine();

                        //Runs on the javafx thread when the thread is not busy.
                        // Without Platform.runLater program crashes because running javafx operations on different threads is snot allowed
                        Platform.runLater(() -> observableList.add(line));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            //Daemon true ser till att inte hindra programmet att avslutas.
            thread.setDaemon(true);
            thread.start();

        } catch (IOException e) {
            System.out.println("Error connecting to server");
            e.printStackTrace();
        }

    }

    public String getMessage() {
        return message.get();
    }

    public StringProperty messageProperty() {
        return message;
    }

    public void setMessage(String message) {
        this.message.set(message);
    }

    public ObservableList<String> getObservableList() {
        return observableList;
    }

    public void setObservableList(ObservableList<String> observableList) {
        this.observableList = observableList;
    }
    public Socket getSocket() {
        return socket;
    }

    public void sendMessage() {
        try {

            //Writes messages that has been input from GUI
            //BufferedReader must have newLine and flush after each message
            writer.write(getMessage());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setMessage(""); // Sets message to empty.Clears messageTextField and hinders button to send in GUI
    }
}
