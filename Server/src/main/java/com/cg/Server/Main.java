package com.cg.Server;

import static spark.Spark.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.cg.Constructors.RandomValues;

public class Main {

	// Initiation of RandomValues class for Table's task weighted probability
	static RandomValues<Integer> tableValues = new RandomValues<Integer>();

	// Initiation of 3x3 matrix for Spin task
	static String[][] spinValues = new String[3][3];

	// Is used in spin() method
	static Random rand = new Random();

	public static void main(String[] args) {
		// Represents required Table task's values
		tableValues.add(0.4, 0).add(0.3, 1).add(0.2, 2).add(0.1, 3);
		serve();
	}

	public static void serve() {

		Gson GSON = new Gson();
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();

		Map<String, String> responseBody = new HashMap<String, String>();
		Map<String, String[][]> spinBody = new HashMap<String, String[][]>();

		port(8008);
		post("/serve", (req, res) -> {
			switch (req.body()) {
			case "Hello":
				return hello();
			case "Table":
				return tableValues.next();
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

				responseBody.put("response", Integer.toString(tableValues.next()));
				return GSON.toJson(responseBody);

			case "Spin":
				spin();
				spinBody.put("response", spinValues);
				return GSON.toJson(spinBody);
			default:
				responseBody.put("response", "Wrong request");
				return GSON.toJson(responseBody);
			}
		});
	}

	public static String hello() {
		return "Hello stranger!";
	}

	/**
	 * Spin task implementation. It is traversing 3x3 matrix and depending on which
	 * value has been rolled by Random, picks a symbol for slot.
	 */
	public static void spin() {
		int symbolValue;
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				symbolValue = rand.nextInt(4);
				System.out.println(symbolValue);
				switch (symbolValue) {
				case 0:
					spinValues[i][j] = "ACE";
					break;
				case 1:
					spinValues[i][j] = "KING";
					break;
				case 2:
					spinValues[i][j] = "QUEEN";
					break;
				case 3:
					spinValues[i][j] = "JACK";
					break;
				}
			}
		}
	}

}
