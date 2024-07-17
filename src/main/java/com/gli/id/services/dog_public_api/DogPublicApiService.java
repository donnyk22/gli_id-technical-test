package com.gli.id.services.dog_public_api;

import java.io.IOException;

public interface DogPublicApiService {
    String getData(String breed) throws IOException;

}
