package se.selimkose.labb3.Model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatModelTest {

    ChatModel chatModel = new ChatModel();

    @Test
    void messageIsEqualToNullOnStartup() {
        assertNull(chatModel.getMessage());

    }
    @Test
    void chatModelHasCorrectSocketPort(){
        assertEquals(8000,chatModel.getSocket().getPort());
    }

   @Test
   @DisplayName("Check if the textField is emptied after message is sent")
    void messageTextFieldIsClearedAfterMessageIsSent(){

        chatModel.message.set("Tjeeena!");
        chatModel.sendMessage();

        assertEquals("",chatModel.getMessage());
   }
}