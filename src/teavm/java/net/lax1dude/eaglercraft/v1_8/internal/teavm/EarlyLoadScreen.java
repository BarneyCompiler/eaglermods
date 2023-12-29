package net.lax1dude.eaglercraft.v1_8.internal.teavm;

import net.lax1dude.eaglercraft.v1_8.internal.IBufferArrayGL;
import net.lax1dude.eaglercraft.v1_8.internal.IBufferGL;
import net.lax1dude.eaglercraft.v1_8.internal.IProgramGL;
import net.lax1dude.eaglercraft.v1_8.internal.IShaderGL;
import net.lax1dude.eaglercraft.v1_8.internal.ITextureGL;
import net.lax1dude.eaglercraft.v1_8.internal.PlatformAssets;
import net.lax1dude.eaglercraft.v1_8.internal.PlatformInput;
import net.lax1dude.eaglercraft.v1_8.internal.PlatformRuntime;
import net.lax1dude.eaglercraft.v1_8.internal.buffer.ByteBuffer;
import net.lax1dude.eaglercraft.v1_8.internal.buffer.FloatBuffer;
import net.lax1dude.eaglercraft.v1_8.internal.buffer.IntBuffer;
import net.lax1dude.eaglercraft.v1_8.opengl.ImageData;

import static net.lax1dude.eaglercraft.v1_8.internal.PlatformOpenGL.*;
import static net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums.*;

import net.lax1dude.eaglercraft.v1_8.Base64;
import net.lax1dude.eaglercraft.v1_8.EagUtils;

/**
 * Copyright (c) 2022-2023 LAX1DUDE. All Rights Reserved.
 * 
 * WITH THE EXCEPTION OF PATCH FILES, MINIFIED JAVASCRIPT, AND ALL FILES
 * NORMALLY FOUND IN AN UNMODIFIED MINECRAFT RESOURCE PACK, YOU ARE NOT ALLOWED
 * TO SHARE, DISTRIBUTE, OR REPURPOSE ANY FILE USED BY OR PRODUCED BY THE
 * SOFTWARE IN THIS REPOSITORY WITHOUT PRIOR PERMISSION FROM THE PROJECT AUTHOR.
 * 
 * NOT FOR COMMERCIAL OR MALICIOUS USE
 * 
 * (please read the 'LICENSE' file this repo's root directory for more info)
 * 
 */
public class EarlyLoadScreen {

	public static final String loadScreen = "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";
	public static final String enableScreen = "iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAYAAADDPmHLAAAACXBIWXMAAC4jAAAuIwF4pT92AAAEAklEQVR42u2dvXbjIBBG7T0+xw+gTp06v//LmE6dO/VR5a3wGZNh+BGSFeveJgkIBrDy8TGKds8/Pz/PExyW8/P55AY4MP9YgmNzmeeZVUABAA8AKADgAQAFADwAoACABwAUAPAAgAIAHgBQAMADAAoAeABAAY7LOI7fpQDX65VPtZCt18w5d7rdbigAbOgBxnE8DcPwJnnDMCTrNJlsUVcizTnj9HWxeVvINfN9y361OdTEk30551ZZt3PsvYDYxOSChoPQ6sJ21mRLBm61jY0lpy61gDKWNdfcNcv5wErWLbfPF88I9/s9WtayzopXS85YtPqcMeT23SqedV1pucal1V4iTUooV/IaWSfbWHU5JmkvpmzrsayaB9DqfJnVTpMff72sc869/WzVlcjjOI7mOOVYfBzfT05exLfT5pqae008a71Ly6tPASV79CfPylvFjpm+teLH+tXiF5nA2LOAUMpCibckWpPBUOJT20btFuDjyK8p+S45Z4fX+ti+LDb3pef62PosWbfkDbBW8mFPhB/gt8Vr7gG+kZK9+C/GM2+ArffnnKRHbT5gSdJoK0+ydrziGyCW115LolLxnHOr59q3lt89b6U8Czg4pgdI5bUtKY3VzfOclGBtTLVSmmqn1cdyC7Iud+5791KX1MLJDz3Mg2s59pK6sM/asdTmLrRx5pzjS+e+awWw9lstVeuv1/a10rqwT8sn5LQr8RzaMVfmKrR2qfnFjs57/puLS0nyoTZp0fL8XGq+ap8v4AES+3Msx74kN2/tmblewWoXPl9o+RykZH5/5hTQYv+y+vj084XcPHpJbHmt1s7yGbV1q+UBnHO/gnoZje2RmuzK/Vr2F3sWEF6TGkvutqH5CG08qTmk5u77tLyK5Qtq62rgxRA8AO8FHBkygQeHLQAFADwAoACABwAUAPAAgAIAHgBQAMADAAoAeABAAQAPACgA4AEABQA8AKAAgAcAFAC+3gNM03Tqum7VQSyN4dtvMdZDKcBWC9oqhr8JoIEHeDwep77vf5VJfL0vl9fLa/u+f+vPfx9eszSGNXZo5AH6vlcXW36gsqykrzViwAIPYL3r3nXd63v5m6i9J2+VaT8viWGNHZQbYE97+KdjHPIGKH0XPSyL7eXSjPk2YZlsN03Tq21OjLAs598ZggIT2MpMbW3IMICFN0Dsv4xpfUbfAvIAK9wAcOAtAMgDwJHzAIACAB4AUADAAwAKAHgAQAEADwAoAOABAAUAPACgAIAHABQA8ACAAgAeAFAAwAMACgB4AEABAA8AKADgAQAFADwAoACABwAUAPAAgAIAHgBQAMADAAoAeABAAQAPACgA4AEABQA8AKAAgAcAFADwANCe/0of1jQ8XY5YAAAAAElFTkSuQmCC";

	private static IBufferGL vbo = null;
	private static IProgramGL program = null;
	
	public static void paintScreen() {
		
		ITextureGL tex = _wglGenTextures();
		_wglActiveTexture(GL_TEXTURE0);
		_wglBindTexture(GL_TEXTURE_2D, tex);
		_wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		_wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		_wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		_wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		
		ImageData img = PlatformAssets.loadImageFile(Base64.decodeBase64(loadScreen));
		ByteBuffer upload = PlatformRuntime.allocateByteBuffer(192*192*4);
		IntBuffer pixelUpload = upload.asIntBuffer();
		pixelUpload.put(img.pixels);
		pixelUpload.flip();
		_wglTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, 192, 192, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixelUpload);
		
		FloatBuffer vertexUpload = upload.asFloatBuffer();
		vertexUpload.clear();
		vertexUpload.put(0.0f); vertexUpload.put(0.0f);
		vertexUpload.put(0.0f); vertexUpload.put(1.0f);
		vertexUpload.put(1.0f); vertexUpload.put(0.0f);
		vertexUpload.put(1.0f); vertexUpload.put(0.0f);
		vertexUpload.put(0.0f); vertexUpload.put(1.0f);
		vertexUpload.put(1.0f); vertexUpload.put(1.0f);
		vertexUpload.flip();
			
		vbo = _wglGenBuffers();
		_wglBindBuffer(GL_ARRAY_BUFFER, vbo);
		_wglBufferData(GL_ARRAY_BUFFER, vertexUpload, GL_STATIC_DRAW);
		
		PlatformRuntime.freeByteBuffer(upload);

		IShaderGL vert = _wglCreateShader(GL_VERTEX_SHADER);
		_wglShaderSource(vert, "#version 300 es\nprecision lowp float; in vec2 a_pos; out vec2 v_pos; void main() { gl_Position = vec4(((v_pos = a_pos) - 0.5) * vec2(2.0, -2.0), 0.0, 1.0); }");
		_wglCompileShader(vert);
		
		IShaderGL frag = _wglCreateShader(GL_FRAGMENT_SHADER);
		_wglShaderSource(frag, "#version 300 es\nprecision lowp float; in vec2 v_pos; out vec4 fragColor; uniform sampler2D tex; uniform vec2 aspect; void main() { fragColor = vec4(texture(tex, clamp(v_pos * aspect - ((aspect - 1.0) * 0.5), 0.02, 0.98)).rgb, 1.0); }");
		_wglCompileShader(frag);
		
		program = _wglCreateProgram();
		
		_wglAttachShader(program, vert);
		_wglAttachShader(program, frag);
		_wglBindAttribLocation(program, 0, "a_pos");
		_wglLinkProgram(program);
		_wglDetachShader(program, vert);
		_wglDetachShader(program, frag);
		_wglDeleteShader(vert);
		_wglDeleteShader(frag);
		
		_wglUseProgram(program);
		_wglUniform1i(_wglGetUniformLocation(program, "tex"), 0);

		int width = PlatformInput.getWindowWidth();
		int height = PlatformInput.getWindowHeight();
		float x, y;
		if(width > height) {
			x = (float)width / (float)height;
			y = 1.0f;
		}else {
			x = 1.0f;
			y = (float)height / (float)width;
		}
		
		_wglActiveTexture(GL_TEXTURE0);
		_wglBindTexture(GL_TEXTURE_2D, tex);
		
		_wglViewport(0, 0, width, height);
		_wglClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		_wglClear(GL_COLOR_BUFFER_BIT);
		
		_wglUniform2f(_wglGetUniformLocation(program, "aspect"), x, y);
		
		IBufferArrayGL vao = _wglGenVertexArrays();
		_wglBindVertexArray(vao);
		_wglEnableVertexAttribArray(0);
		_wglVertexAttribPointer(0, 2, GL_FLOAT, false, 8, 0);
		_wglDrawArrays(GL_TRIANGLES, 0, 6);
		_wglDisableVertexAttribArray(0);
		
		PlatformInput.update();
		EagUtils.sleep(50l); // allow webgl to flush

		_wglUseProgram(null);
		_wglBindBuffer(GL_ARRAY_BUFFER, null);
		_wglBindTexture(GL_TEXTURE_2D, null);
		_wglDeleteTextures(tex);
		_wglDeleteVertexArrays(vao);
	}
	
	public static void paintEnable() {
		
		ITextureGL tex = _wglGenTextures();
		_wglActiveTexture(GL_TEXTURE0);
		_wglBindTexture(GL_TEXTURE_2D, tex);
		_wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		_wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		_wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		_wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		ImageData img = PlatformAssets.loadImageFile(Base64.decodeBase64(enableScreen));
		IntBuffer upload = PlatformRuntime.allocateIntBuffer(128*128);
		upload.put(img.pixels);
		upload.flip();
		_wglTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, 128, 128, 0, GL_RGBA, GL_UNSIGNED_BYTE, upload);
		
		PlatformRuntime.freeIntBuffer(upload);
		
		_wglUseProgram(program);

		int width = PlatformInput.getWindowWidth();
		int height = PlatformInput.getWindowHeight();
		float x, y;
		if(width > height) {
			x = (float)width / (float)height;
			y = 1.0f;
		}else {
			x = 1.0f;
			y = (float)height / (float)width;
		}
		
		_wglActiveTexture(GL_TEXTURE0);
		_wglBindTexture(GL_TEXTURE_2D, tex);
		
		_wglViewport(0, 0, width, height);
		_wglClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		_wglClear(GL_COLOR_BUFFER_BIT);
		
		_wglUniform2f(_wglGetUniformLocation(program, "aspect"), x, y);

		IBufferArrayGL vao = _wglGenVertexArrays();
		_wglBindVertexArray(vao);
		_wglBindBuffer(GL_ARRAY_BUFFER, vbo);
		_wglEnableVertexAttribArray(0);
		_wglVertexAttribPointer(0, 2, GL_FLOAT, false, 8, 0);
		_wglDrawArrays(GL_TRIANGLES, 0, 6);
		_wglDisableVertexAttribArray(0);
		
		PlatformInput.update();
		EagUtils.sleep(50l); // allow webgl to flush

		_wglUseProgram(null);
		_wglBindBuffer(GL_ARRAY_BUFFER, null);
		_wglBindTexture(GL_TEXTURE_2D, null);
		_wglDeleteTextures(tex);
		_wglDeleteVertexArrays(vao);
		
	}
	
	public static void paintFinal(byte[] image) {
		ITextureGL tex = _wglGenTextures();
		_wglActiveTexture(GL_TEXTURE0);
		_wglBindTexture(GL_TEXTURE_2D, tex);
		_wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		_wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		_wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		_wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		ImageData img = PlatformAssets.loadImageFile(image);
		IntBuffer upload = PlatformRuntime.allocateIntBuffer(256*256);
		upload.put(img.pixels);
		upload.flip();
		_wglTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, 256, 256, 0, GL_RGBA, GL_UNSIGNED_BYTE, upload);
		
		PlatformRuntime.freeIntBuffer(upload);
		
		_wglUseProgram(program);

		int width = PlatformInput.getWindowWidth();
		int height = PlatformInput.getWindowHeight();
		float x, y;
		if(width > height) {
			x = (float)width / (float)height;
			y = 1.0f;
		}else {
			x = 1.0f;
			y = (float)height / (float)width;
		}
		
		_wglActiveTexture(GL_TEXTURE0);
		_wglBindTexture(GL_TEXTURE_2D, tex);
		
		_wglViewport(0, 0, width, height);
		_wglClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		_wglClear(GL_COLOR_BUFFER_BIT);
		
		_wglUniform2f(_wglGetUniformLocation(program, "aspect"), x, y);

		IBufferArrayGL vao = _wglGenVertexArrays();
		_wglBindVertexArray(vao);
		_wglBindBuffer(GL_ARRAY_BUFFER, vbo);
		_wglEnableVertexAttribArray(0);
		_wglVertexAttribPointer(0, 2, GL_FLOAT, false, 8, 0);
		_wglDrawArrays(GL_TRIANGLES, 0, 6);
		_wglDisableVertexAttribArray(0);
		
		PlatformInput.update();
		EagUtils.sleep(50l); // allow webgl to flush

		_wglUseProgram(null);
		_wglBindBuffer(GL_ARRAY_BUFFER, null);
		_wglBindTexture(GL_TEXTURE_2D, null);
		_wglDeleteTextures(tex);
		_wglDeleteVertexArrays(vao);
		_wglDeleteBuffers(vbo);
		_wglDeleteProgram(program);
	}
	
}
