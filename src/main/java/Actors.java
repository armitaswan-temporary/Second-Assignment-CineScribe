import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
public class Actors {
    public static final String API_KEY = "JR+XmdvUVA4gHkJuIvQppg==ivBToE60MqreDITx";
    double netWorth;
    Boolean isAlive;
    String name;
    String gender;
    String nationality;
    double height;
    String birthday;
    int age;
    String death;

    public Actors(double netWorth, boolean isAlive) {
        this.isAlive = isAlive;
        this.netWorth = netWorth;
    }
    @SuppressWarnings({"deprecation"})
    /**
     * Retrieves data for the specified actor.
     * @param name for which Actor should be retrieved
     * @return a string representation of the Actors info or null if an error occurred
     */
    public String getActorData(String name) {
        try {
            URL url = new URL("https://api.api-ninjas.com/v1/celebrity?name="+
                    name.replace(" ", "+")+"&apikey="+API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Api-Key", API_KEY);
            System.out.println(connection);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                return response.toString();
            } else {
                return "Error: " + connection.getResponseCode() + " " + connection.getResponseMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean actorErrorHandling(String actorsInfoJson){
        String actorsInfo = actorsInfoJson.substring(1, actorsInfoJson.length()-1);
        if (actorsInfo == ""){
            return false;
        }
        return true;
    }

    public double getNetWorthViaApi(String actorsInfoJson) {
        String actorsInfo = actorsInfoJson.substring(1, actorsInfoJson.length()-1);
        JSONObject actorNetWorth = new JSONObject(actorsInfo);
        netWorth = actorNetWorth.getInt("net_worth");
        return netWorth;
    }

    public boolean isAlive(String actorsInfoJson) {
        String actorsInfo = actorsInfoJson.substring(1, actorsInfoJson.length()-1);
        JSONObject alive = new JSONObject(actorsInfo);
        isAlive = alive.getBoolean("is_alive");
        return isAlive;
    }

    public String getDateOfDeathViaApi(String actorsInfoJson) {
        if (!isAlive(actorsInfoJson)) {
            String actorsInfo = actorsInfoJson.substring(1, actorsInfoJson.length()-1);
            JSONObject dateofDeath = new JSONObject(actorsInfo);
            death = dateofDeath.getString("death");
            return death;
        }
        else {
            return "-";
        }
    }

    public String getNameViaApi(String actorsInfoJson){
        String actorsInfo = actorsInfoJson.substring(1 , actorsInfoJson.length()-1);
        JSONObject jsonObject = new JSONObject(actorsInfo);
        name = jsonObject.getString("name");
        return name;
    }

    public String getGenderViaAPi(String actorsInfoJson) {
        String actorsInfo = actorsInfoJson.substring(1, actorsInfoJson.length()-1);
        JSONObject jsonObject = new JSONObject(actorsInfo);
        gender = jsonObject.getString("gender");
        return gender;
    }

    public String getNationalityViaApi(String actorsInfoJson) {
        String actorsInfo = actorsInfoJson.substring(1, actorsInfoJson.length()-1);
        JSONObject jsonObject = new JSONObject(actorsInfo);
        nationality = jsonObject.getString("nationality");
        return nationality;
    }

    public double getHeightViaAPi(String actorsInfoJson) {
        String actorsInfo = actorsInfoJson.substring(1, actorsInfoJson.length()-1);
        JSONObject jsonObject = new JSONObject(actorsInfo);
        height = jsonObject.getDouble("height");
        return height;
    }

    public String getBirthdayViaApi(String actorsInfoJson) {
        String actorsInfo = actorsInfoJson.substring(1, actorsInfoJson.length()-1);
        JSONObject jsonObject = new JSONObject(actorsInfo);
        birthday = jsonObject.getString("birthday");
        return birthday;
    }

    public int getAgeViaAPi(String actorsInfoJson) {
        String actorsInfo = actorsInfoJson.substring(1, actorsInfoJson.length()-1);
        JSONObject jsonObject = new JSONObject(actorsInfo);
        age = jsonObject.getInt("age");
        return age;
    }
}