package com.neusoft.elmboot.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

import io.github.jonathanlink.PDFLayoutTextStripper;

@RestController
@RequestMapping("/chatgpt")
public class ChatgptController {
    /*private static final String SAMPLE_PDF_FILE = "中国城镇居民的房地产税纳税意愿——基于不同减免方案的模拟分析_张平.pdf";*/
    private static final String OPENAI_API_KEY = "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";
    //注意：这里要换成你自己的OPENAI的代理地址
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    /*private static final String QUERY = "里面有哪些单词";*/
    private Map<String, List<String>> userSessions = new HashMap<>();
    @PostMapping
    public String chatWithGPT(@RequestBody Map<String,Object> chatRequest) throws IOException {
/*
        //获取数据集（pdf）
            var pdf = extractPDFContent();
            var apiResponse = callOpenAIAPI(pdf);
            var results = extractAnswer(apiResponse);
        //获取用户的标识和消息*/
        String userId = chatRequest.get("userId").toString();
        String message = chatRequest.get("message").toString();
        // 检查用户会话是否存在
        if (!userSessions.containsKey(userId)) {
            //第一次进来肯定不存在会话中，这时候我们放进去
            userSessions.put(userId, new ArrayList<>());
        }
        //把发送的消息扔进这个人的list中
        List<String> sessionMessages = userSessions.get(userId);
        sessionMessages.add(message);

        // 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(OPENAI_API_KEY);

        // 构建请求体
        String requestBody = buildRequestBody(userId, sessionMessages);

        // 发送请求
        RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //我用的呆里端口33210，这个要改
        factory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 33210)));
        restTemplate.setRequestFactory(factory);

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(OPENAI_API_URL,request, String.class);
        // 提取回复消息
        String responseBody = response.getBody();
        String reply = extractReplyFromResponse(responseBody);
        System.out.println("-------------------"+reply+"--------------------");

        //把回复消息也存进当前用户的的list中，方便上下文记忆
        sessionMessages.add(reply);

        return reply;
    }

    /*private static String extractPDFContent() throws IOException {
        ClassLoader classLoader = Main.class.getClassLoader();
//        InputStream stream = new FileInputStream(SAMPLE_PDF_FILE);
//        PDFParser pdfParser = new PDFParser(new RandomAccessBuffer(stream));
        InputStream stream = classLoader.getResourceAsStream(SAMPLE_PDF_FILE);
        PDFParser pdfParser = new PDFParser(new RandomAccessBuffer(stream));
        pdfParser.parse();
        PDDocument pdDocument = new PDDocument(pdfParser.parse().getDocument());
        PDFTextStripper pdfTextStripper = new PDFLayoutTextStripper();
        return pdfTextStripper.getText(pdDocument);
    }
    private static String callOpenAIAPI(String pdf) throws IOException {
        URL obj = new URL(OPENAI_API_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Authorization", String.format("Bearer %s", OPENAI_API_KEY));
        con.setDoOutput(true);

        Map<String, Object> json = new HashMap<>();
        json.put("model", "text-davinci-003");
        json.put("prompt", String.format(QUERY, pdf));
        json.put("temperature", 0.5);
        json.put("max_tokens", 2048);

        Gson gson = new Gson();
        String jsonString = gson.toJson(json);
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(jsonString);
            wr.flush();
        }
        String retVal;
        try (InputStream inputStream = con.getInputStream()) {
            retVal = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        }
        return retVal;
    }*/

    private static Map<String, String> extractAnswer(String apiResponse) {
        Gson gson = new Gson();
        Map<String, Object> map = gson.fromJson(apiResponse, Map.class);
        if (!(map.get("choices") instanceof List)) {
            return Collections.emptyMap();
        }
        List<Map<String, Object>> choices = (List<Map<String, Object>>)map.get("choices");
        if (choices.isEmpty() || !choices.get(0).containsKey("text")) {
            return Collections.emptyMap();
        }
        String answer = (String)choices.get(0).get("text");
        return gson.fromJson(answer, Map.class);
    }

    private String buildRequestBody(String userId, List<String> sessionMessages) {
        JSONArray messagesArray = new JSONArray();
        for (String message : sessionMessages) {
            JSONObject messageObj = new JSONObject();
            messageObj.put("role", "user");
            messageObj.put("content", message);
            messagesArray.add(messageObj);
        }

        JSONObject requestBodyObj = new JSONObject();
        requestBodyObj.put("model", "gpt-3.5-turbo");
        requestBodyObj.put("messages", messagesArray);

        return requestBodyObj.toString();
    }

    private String extractReplyFromResponse(String response) {
        JSONObject jsonObject = JSON.parseObject(response);
        JSONArray choices = jsonObject.getJSONArray("choices");
        JSONObject firstChoice = choices.getJSONObject(0);
        JSONObject message = firstChoice.getJSONObject("message");
        String reply = message.getString("content");

        return reply;
    }

}
