package com.cg.Server;

import static spark.Spark.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.cg.Constructors.RandomValues;

public class Main {

	static RandomValues<Integer> randomValues = new RandomValues<Integer>();

	public static void main(String[] args) {
		randomValues.add(0.4, 0).add(0.3, 1).add(0.2, 2).add(0.1, 3);
		serve();
	}

	public static void serve() {

		Gson GSON = new Gson();
		Type type = new TypeToken<Map<String, String>>() {}.getType();

		Map<String, String> responseBody = new HashMap<String, String>();

		port(8008);
		post("/serve", (req, res) -> {
			switch (req.body()) {
			case "Hello":
				return hello();
			case "Table":
				return randomValues.next();
			default:
				return "Error! No or invalid request name specified! (" + req.body() + ")";
			}
		});

		post("/json", (req, res) -> {
			res.type("application/json");
			Map<String, String> requestBody = GSON.fromJson(req.body(), type);

			switch (requestBody.get("request")) {

			case "Hello":

				responseBody.put("response", hello());
				return GSON.toJson(responseBody);

			case "Table":

				responseBody.put("response", Integer.toString(randomValues.next()));
				return GSON.toJson(responseBody);

			default:
				responseBody.put("response", "Wrong request");
				return GSON.toJson(responseBody);
			}
		});
	}

	public static String hello() {
		return "Hello stranger!";
	}

}
