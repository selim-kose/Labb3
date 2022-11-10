package se.selimkose.labb3.Model;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.selimkose.labb3.Controller.Controller;
import se.selimkose.labb3.Model.Shape.Shape;
import se.selimkose.labb3.Model.Shape.ShapeType;

import java.io.*;
import java.net.Socket;

public class ChatModel {


    StringProperty message = new SimpleStringProperty();

    //ArrayList that broadcasts messages to listeners when updated
    ObservableList<String> observableList = FXCollections.observableArrayList();

    ObservableList<Shape> observableShapeList = FXCollections.observableArrayList();



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

                        String[] lines = line.split(" ");
                        if (!lines[0].equals("[you]") && lines[1].startsWith("<")) {
                            convertFromSvgToCanvas(line);
                        } else {
                            //Runs on the javafx thread when the thread is not busy.
                            // Without Platform.runLater program crashes because running javafx operations on different threads is snot allowed
                            Platform.runLater(() -> observableList.add(line));
                        }

                    }
                } catch (IOException e) {
                    System.out.println("Could not read incoming message");
                    e.printStackTrace();
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

    public void convertFromSvgToCanvas(String svgFormat) {

        String[] words = svgFormat.split(" ");
        String svgType = words[1].substring(1);
        int x = Integer.valueOf(words[2].replaceAll("[a-z=\"]",""));
        int y = Integer.valueOf(words[3].replaceAll("[a-z=\"]",""));
        double size = Double.valueOf(words[4].replaceAll("[a-z=\"]",""));
        String colorHex = svgFormat.replaceAll(".*[^A-Fa-f0-9]{6}","").substring(0,6);
        Color color = Color.web(colorHex);
        System.out.println(words[0]+ svgType+ " "+ x+ " " + y+ " "+ size + " " + colorHex);


        Position mousePosition = new Position(x, y);

        switch (svgType) {
            case "circle" -> {
                Shape shape1 = Shape.createShape(ShapeType.CIRCLE, mousePosition, color, size*2);
                observableShapeList.add(shape1);}
            case "rect" -> {
                Shape shape2 = Shape.createShape(ShapeType.RECTANGLE, mousePosition, color, size);
                observableShapeList.add(shape2);
            }
        }
    }

    public void renderShapes(GraphicsContext graphicsContext) {
        for(Shape i: observableShapeList){
            i.drawCanvas(graphicsContext);
        }
    }

    public ObservableList<Shape> getObservableShapeList() {
        return observableShapeList;
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
