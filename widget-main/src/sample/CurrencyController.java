package sample;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.URLConnection;
import javafx.scene.control.Button;
import org.json.JSONObject;



public class CurrencyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ConvertButton;

    @FXML
    private Text EUR;

    @FXML
    private Text KGZ;

    @FXML
    private TextField KGZconvert;

    @FXML
    private Text RUB;

    @FXML
    private Text USD;

    @FXML
    void initialize() {
        ConvertButton.setOnAction(this::handle);

    }

    private static String getUrlContetn(String urlAdress){
        StringBuffer content = new StringBuffer();
        try{
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("it works");
        }
        return content.toString();
    }


    private void handle(ActionEvent event) {

        String output = getUrlContetn("http://api.exchangeratesapi.io/v1/latest?access_key=d2bc9262291f2166afcd7a2e9a12ad9f&format=1");

        if (!output.isEmpty()) {

            Double getCurrency = Double.parseDouble(KGZconvert.getText().trim());
            JSONObject obj = new JSONObject(output);

            double USD1 = obj.getJSONObject("rates").getDouble("USD");
            double RUB1 = obj.getJSONObject("rates").getDouble("RUB");
            double KGS1 = obj.getJSONObject("rates").getDouble("KGS");

            double calculatedUSD = USD1 * getCurrency;
            double calculatedRUB = RUB1 * getCurrency;
            double calculatedKGS = KGS1 * getCurrency;


            USD.setText(String.format("USD: %.2f", calculatedUSD));
            RUB.setText(String.format("RUB: %.2f", calculatedRUB));
            KGZ.setText(String.format("KGS: %.2f", calculatedKGS));



        }
    }
}







/*
package sample;

        import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.net.URL;
        import java.net.URLConnection;
        import java.util.ResourceBundle;

        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextField;
        import javafx.scene.text.Text;
        import org.json.JSONObject;

        import javax.swing.text.html.ImageView;

public class Controller {

   */
/*@FXML
   private ResourceBundle resources;

   @FXML
   private URL location;*//*


    @FXML
    private TextField city;

    @FXML
    private Button start_weather;

    @FXML
    private Text temp_info;


    public void initialize() {
        start_weather.setOnAction(event -> {
            String getUserCity = city.getText().trim();
            if (!getUserCity.equals("")) {
                String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=f51990e7149906aa689774767bf390da&unit=metric");
                if (!output.isEmpty()) {
                    JSONObject obj = new JSONObject(output);
                    double b = obj.getJSONObject("main").getDouble("temp");
                    double m = b - 274.15;
                    temp_info.setText("Temperature is : "+Math.floor(m) + "°C");
                }
            }


        });

    }
    private static String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("asrfa");
        }
        return content.toString();
    }

}
*/
