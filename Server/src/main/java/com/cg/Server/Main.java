package com.cg.Server;

import static spark.Spark.*;
import com.cg.Constructors.RandomValues;

public class Main {
	
	static RandomValues<Integer> randomValues = new RandomValues<Integer>();
	
	public static void main(String[] args) {
		randomValues.add(0.4, 0).add(0.3, 1).add(0.2, 2).add(0.1, 3);
		serve();
	}

	public static void serve() {
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
	}

	public static String hello() {
		return "Hello stranger!";
	}
	
	
}
