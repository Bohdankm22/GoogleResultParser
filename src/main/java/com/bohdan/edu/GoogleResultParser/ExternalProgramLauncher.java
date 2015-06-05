package com.bohdan.edu.GoogleResultParser;

import java.io.IOException;

public class ExternalProgramLauncher {

	public static void main(String[] args) throws IOException,
			InterruptedException {

		Runtime.getRuntime().exec("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
	}
}
