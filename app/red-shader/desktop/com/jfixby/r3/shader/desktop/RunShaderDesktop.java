
package com.jfixby.r3.shader.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.FokkerLwjglApplication;
import com.badlogic.gdx.backends.lwjgl.FokkerLwjglApplicationConfiguration;
import com.jfixby.r3.engine.core.FokkerStarter;
import com.jfixby.r3.engine.core.FokkerStarterConfig;
import com.jfixby.r3.fokker.adaptor.GdxAdaptor;
import com.jfixby.r3.fokker.api.FokkerEngineAssembler;
import com.jfixby.r3.fokker.api.UnitsMachineExecutor;
import com.jfixby.r3.shader.ui.ShaderUI;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.input.UserInput;
import com.jfixby.scarabei.red.input.RedInput;

public class RunShaderDesktop {
	public static void main (final String[] arg) {

		setupBasicComponents();
		final FokkerStarterConfig config = FokkerStarter.newRedTriplaneConfig();

		final FokkerEngineAssembler engine_assembler = new ShaderDesktopAssembler();
		config.setEngineAssembler(engine_assembler);

		final FokkerStarter triplane_starter = FokkerStarter.newRedTriplane(config);
		final UnitsMachineExecutor machine = triplane_starter.getUnitsMachineExecutor();

		final GdxAdaptor adaptor = new GdxAdaptor(machine);

		final FokkerLwjglApplicationConfiguration cfg = new FokkerLwjglApplicationConfiguration();
		cfg.title = "Red Shader Viewer [" + ShaderUI.shader_id1 + "]";
		cfg.useGL30 = true;
		cfg.width = 1024;
		cfg.height = 768;
// cfg.vSyncEnabled = false;qw
// cfg.r = 1;
// cfg.g = 1;
// cfg.b = 1;
// cfg.a = 1;
// cfg.overrideDensity = 10;
// cfg.foregroundFPS = 60;

		final ApplicationListener gdx_listener = adaptor.getGDXApplicationListener();

		// gdx_listener = new HttpRequestTest();
		// GdxEntryPoint point = new GdxEntryPoint();
		// new LwjglApplication(point, cfg);
		new FokkerLwjglApplication(gdx_listener, cfg);

	}

	private static void setupBasicComponents () {
		ScarabeiDesktop.deploy();
		UserInput.installComponent(new RedInput());

// L.installComponent(new DesktopLogger());
// Collections.installComponent(new DesktopCollections());
// Err.installComponent(new RedError());
// Debug.installComponent(new RedDebug());
// JUtils.installComponent(new RedJUtils());
// FloatMath.installComponent(new DesktopFloatMath());
// TaskManager.installComponent(new RedTaskManager());
// Sys.installComponent(new DesktopSystem());
// SystemSettings.installComponent(new RedSystemSettings());
//
// IntegerMath.installComponent(new RedIntegerMath());
// Names.installComponent(new RedAssetsNamespace());
// IO.installComponent(new RedIO());
// Graphs.installComponent(new RedGraphs());
// SimpleTriangulator.installComponent(new GdxSimpleTriangulator());
// Angles.installComponent(new RedAngles());
//

//
// final RedGeometry geometry = new RedGeometry();
// Geometry.installComponent(geometry);
// Colors.installComponent(new RedColors());
// MathTools.installComponent(new RedMathTools());
// // --
// Json.installComponent(new RedJson());
// Base64.installComponent(new GdxBase64());
// MD5.installComponent(new RSADataSecurityIncMD5());
	}
}
