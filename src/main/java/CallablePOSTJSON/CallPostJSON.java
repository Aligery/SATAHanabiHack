package CallablePOSTJSON;

import Bean.User;
import com.google.gson.Gson;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallPostJSON {
    public CallPostJSON() {
        System.out.println("Отправляем JSON в POST на http://localhost:8080/GenerateUsersFromClient, параметров нет");
        HttpURLConnection urlConnection = null;
        BufferedOutputStream bos = null;
        try{
            URL url = new URL("http://localhost:8080/GenerateUsersFromClient");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            User user = new User(25,"fname1", "sname2", "something@mail.ru");
            //
            //тут мы объект должны перевести в JSON формат
            Gson gson = new Gson();
            String jo = gson.toJson(user);
            System.out.println(jo);
            //
            bos = new BufferedOutputStream(urlConnection.getOutputStream());
            bos.write(jo.getBytes());
            String result = urlConnection.getResponseMessage();
           // Log.d("", "server response: " + result);

        } catch (IOException e) {
            e.printStackTrace();
        } finally
        {
            try
            {
                bos.flush();
                bos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
