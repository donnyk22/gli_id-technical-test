package com.gli.id.services.dog_public_api;

import com.gli.id.tools.JsonFormatter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Random;

@Service
public class DogPublicApiServiceImpl implements DogPublicApiService{

    @Override
    public String getData(String breed) throws IOException {
        if (StringUtils.hasLength(breed)){
            Connection.Response response = Jsoup.connect("https://dog.ceo/api/breed/"+breed+"/list")
                    .method(org.jsoup.Connection.Method.GET)
                    .ignoreContentType(true)
                    .timeout(2000)
                    .execute();

            JsonObject jsonObject = JsonParser.parseString(response.body())
                    .getAsJsonObject();

            //start challenge
            JsonArray messageArray = jsonObject.getAsJsonArray("message");
            jsonObject.remove("message");

            //sheepdog challenge
            if (breed.equalsIgnoreCase("sheepdog")){
                findSheepdog(jsonObject, messageArray);

            //terrier challenge
            }else if(breed.equalsIgnoreCase("terrier")){
                findTerrier(jsonObject, messageArray);

            //shiba challenge
            }else if(breed.equalsIgnoreCase("shiba")){
                findShiba(jsonObject);
            }

            return JsonFormatter.format(jsonObject.toString());
        }

        Connection.Response response = Jsoup.connect("https://dog.ceo/api/breeds/list/all")
                .method(org.jsoup.Connection.Method.GET)
                .ignoreContentType(true)
                .timeout(5000)
                .execute();

        JsonObject jsonObject = JsonParser.parseString(response.body())
                .getAsJsonObject();

        //start challenge
        JsonObject messageObject = jsonObject.getAsJsonObject("message");
        jsonObject.remove("message");

        //sheepdog challenge
        findAllSheepdog(messageObject);

        //terrier challenge
        findAllTerrier(messageObject);

        //shiba challenge
        findAllShiba(messageObject);

        jsonObject.add("message", messageObject);

        return JsonFormatter.format(jsonObject.toString());
    }

    private void findAllSheepdog(JsonObject messageObject){
        JsonArray sheepdogArray = messageObject.getAsJsonArray("sheepdog");
        messageObject.remove("sheepdog");
        for (int i = 0; i < sheepdogArray.size(); i++){
            messageObject.add("sheepdog-"+sheepdogArray.get(i).getAsString(), new JsonArray());
        }
    }

    private void findSheepdog(JsonObject jsonObject, JsonArray sheepdogArray){
        jsonObject.add("message", new JsonObject());
        JsonObject messageObject = jsonObject.getAsJsonObject("message");
        for (int i = 0; i < sheepdogArray.size(); i++){
            messageObject.add("sheepdog-"+sheepdogArray.get(i).getAsString(), new JsonArray());
        }
    }

    private void findAllTerrier(JsonObject messageObject) throws IOException {
        JsonArray terrierArray = messageObject.getAsJsonArray("terrier");
        messageObject.remove("terrier");
        for (int i = 0; i < terrierArray.size(); i++){
            JsonArray imagesArray = JsonParser.parseString(
                    findRandomImages("terrier", terrierArray.get(i).getAsString(), 3))
                    .getAsJsonObject()
                    .getAsJsonArray("message");
            messageObject.add("terrier-"+terrierArray.get(i).getAsString(), imagesArray);
        }
    }

    private void findTerrier(JsonObject jsonObject, JsonArray terrierArray) throws IOException {
        jsonObject.add("message", new JsonObject());
        JsonObject messageObject = jsonObject.getAsJsonObject("message");
        for (int i = 0; i < terrierArray.size(); i++){
            JsonArray imagesArray = JsonParser.parseString(
                    findRandomImages("terrier", terrierArray.get(i).getAsString(), 3))
                    .getAsJsonObject()
                    .getAsJsonArray("message");
            messageObject.add("terrier-"+terrierArray.get(i).getAsString(), imagesArray);
        }
    }

    private void findAllShiba(JsonObject messageObject) throws IOException {
        messageObject.remove("shiba");
        int max = 22;
        Random random = new Random();
        int oddNumber = random.nextInt(max / 2) * 2 + 1;
        JsonArray imagesArray = JsonParser.parseString(
                findRandomImages("shiba", null, oddNumber))
                .getAsJsonObject()
                .getAsJsonArray("message");
        messageObject.add("shiba", imagesArray);
    }

    private void findShiba(JsonObject jsonObject) throws IOException {
        int max = 22;
        Random random = new Random();
        int oddNumber = random.nextInt(max / 2) * 2 + 1;
        JsonArray imagesArray = JsonParser.parseString(
                findRandomImages("shiba", null, oddNumber))
                .getAsJsonObject()
                .getAsJsonArray("message");
        jsonObject.add("message", imagesArray);
    }

    private String findRandomImages(String breed, String sub_breed, Integer total) throws IOException {
        String url = "https://dog.ceo/api/breed/"+breed+"/images/random/"+total;
        if (StringUtils.hasLength(sub_breed))
            url = "https://dog.ceo/api/breed/"+breed+"/"+sub_breed+"/images/random/"+total;
        Connection.Response response = Jsoup.connect(url)
                .method(org.jsoup.Connection.Method.GET)
                .ignoreContentType(true)
                .execute();
        return response.body();
    }
}
