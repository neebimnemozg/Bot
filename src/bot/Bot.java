/*
 * @neebimemozg property, don't touch my ta-la-la
 */
package bot;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author ksmirnov
 */
public class Bot extends TelegramLongPollingBot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ApiContextInitializer.init(); // Инициализируем апи
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "Sliska_bot";
        //возвращаем юзера
    }

    @Override
    public void onUpdateReceived(Update e) {
        Message msg = e.getMessage();
        String txt = msg.getText();
        if (txt.equals("/start")) {
            sendMsg(msg, "Здарова! Я - Слиська-дриська, очень жирная и нелепая! Не шути со мной, у меня Фрадков голый с утра по кухне бегает!");
        } else if (txt.contains("а")) {
            sendMsg(msg, "Фрадков - друг братков!");
        } else if (txt.contains("о")) {
            sendMsg(msg, "Говорю же, я - Слиська! Родилась в Саратове, а потом в столовке думской прописалась");
        } else if (txt.contains("у")) {
            URL url;
            try {
                url = new URL("http://zampolit.com/upload/iblock/823/823a357c98bb3c4e970a7a4b33f7bc6e.jpg");
                URLConnection conn = url.openConnection();
                InputStream in = conn.getInputStream();
                SendPhoto sendPhoto = new SendPhoto().setChatId(msg.getChatId());
                sendPhoto.setPhoto("Вот она я!!!", in);
                this.execute(sendPhoto);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TelegramApiException ex) {
                Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (txt.contains("е")) {
            URL url;
            try {
                url = new URL("https://file.liga.net/images/general/2018/10/11/20181011171344-6321.jpg");
                URLConnection conn = url.openConnection();
                InputStream in = conn.getInputStream();
                SendPhoto sendPhoto = new SendPhoto().setChatId(msg.getChatId());
                sendPhoto.setPhoto("Вот она я!!!", in);
                this.execute(sendPhoto);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TelegramApiException ex) {
                Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            sendMsg(msg, "Слииииська!!!!");
        }
    }

    @Override
    public String getBotToken() {
        return "902525906:AAFX-kNEtZkHUl8NuwuaGIGDxJYzIurXntI";
        //Токен бота
    }

    @SuppressWarnings("deprecation")

    private void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId());
        s.setText(text);
        try { //Чтобы не крашнулась программа при вылете Exception 
            execute(s);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
