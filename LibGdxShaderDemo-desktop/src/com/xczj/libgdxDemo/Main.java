package com.xczj.libgdxDemo;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "LibGdxShaderDemo";
		cfg.useGL20 = true;
		cfg.width = 480;
		cfg.height = 880;
		new LwjglApplication(new ShaderDemoApplication(), cfg);
	}
}
