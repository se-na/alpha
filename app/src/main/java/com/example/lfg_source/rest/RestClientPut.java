package com.example.lfg_source.rest;

import android.os.AsyncTask;


import com.example.lfg_source.entity.User;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;



public class RestClientPut extends AsyncTask<String, Void, ResponseEntity<String>> {

    private boolean message;

    public void setMessage(boolean message) {
        this.message = message;
    }

    public boolean getMessage() {
        return message;
    }

    @Override
    protected ResponseEntity<String> doInBackground(String... uri) {
        final String url = uri[0];
        RestTemplate restTemplate = new RestTemplate();
        try{
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            HttpHeaders headers =new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<String>(headers);

            restTemplate.put(url, message);
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response;
        }
        catch (Exception e){
            String answer = e.getMessage();
            return null;
        }
    }
    protected void onPostExecute(ResponseEntity<User[]> result){
        HttpStatus statusCode = result.getStatusCode();
    }
}