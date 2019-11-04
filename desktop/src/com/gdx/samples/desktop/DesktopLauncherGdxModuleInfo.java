package com.gdx.samples.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gdx.samples.GdxModuleInfoSample;

public class DesktopLauncherGdxModuleInfo {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new GdxModuleInfoSample(), config);
	}
}
