
package com.jfixby.r3.shader.pack;

import java.io.IOException;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.jfixby.r3.api.shader.srlz.R3_SHADER_SETTINGS;
import com.jfixby.r3.api.shader.srlz.ShaderInfo;
import com.jfixby.r3.api.shader.srlz.ShadersContainer;
import com.jfixby.r3.engine.core.unit.raster.FOKKER_SYSTEM_ASSETS;
import com.jfixby.scarabei.adopted.gdx.json.red.RedGdxJson;
import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.json.JsonString;
import com.jfixby.scarabei.api.log.L;

public class CreateDefaultGdxShader {

	static String vertexShader = "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
		+ "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
		+ "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
		+ "uniform mat4 u_projTrans;\n" //
		+ "varying vec4 v_color;\n" //
		+ "varying vec2 v_texCoords;\n" //
		+ "\n" //
		+ "void main()\n" //
		+ "{\n" //
		+ "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
		+ "   v_color.a = v_color.a * (255.0/254.0);\n" //
		+ "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
		+ "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
		+ "}\n";
	static String fragmentShader = "#ifdef GL_ES\n" //
		+ "#define LOWP lowp\n" //
		+ "precision mediump float;\n" //
		+ "#else\n" //
		+ "#define LOWP \n" //
		+ "#endif\n" //
		+ "varying LOWP vec4 v_color;\n" //
		+ "varying vec2 v_texCoords;\n" //
		+ "uniform sampler2D u_texture;\n" //
		+ "void main()\n"//
		+ "{\n" //
		+ "  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n" //
		+ "}";

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();
		Json.installComponent(new RedGdxJson());
		L.d("creating shader", FOKKER_SYSTEM_ASSETS.SHADER_GDX_DEFAULT);
		final File output_folder = LocalFileSystem.ApplicationHome().child("shaders")
			.child("" + FOKKER_SYSTEM_ASSETS.SHADER_GDX_DEFAULT.parent());
		output_folder.makeFolder();
		output_folder.clearFolder();
		final File content = output_folder;
		content.makeFolder();

		final String name = FOKKER_SYSTEM_ASSETS.SHADER_GDX_DEFAULT.getLastStep();

		final File pack = content.child(name);
		pack.makeFolder();
		final File frag_file = pack.child(R3_SHADER_SETTINGS.FRAG_FILE_NAME);

		final File vert_file = pack.child(R3_SHADER_SETTINGS.VERT_FILE_NAME);

		vert_file.writeString(vertexShader);
		frag_file.writeString(fragmentShader);

		final File root_file = output_folder.child(R3_SHADER_SETTINGS.ROOT_FILE_NAME);
		final ShadersContainer container = new ShadersContainer();

		add(name, container);

		final ByteArray params_data = IO.serialize(container);
		root_file.writeBytes(params_data);
// L.d("shader writing done", params_data);
		final JsonString debugString = Json.serializeToString(container);
		L.d(debugString);

	}

	private static void add (final String name, final ShadersContainer container) {
		final ShaderInfo info = new ShaderInfo();
		final ID shader_id = FOKKER_SYSTEM_ASSETS.SHADER_GDX_DEFAULT;
		info.shader_id = shader_id.toString();
		info.shader_folder_name = name;
		info.isOverlay = false;
		container.shaders.add(info);
	}

}
