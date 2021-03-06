package com.AngryStickStudios.StickFlick.Screens;

import com.AngryStickStudios.StickFlick.StickFlick;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class Options implements Screen {
	StickFlick game;
	Stage stage;
	BitmapFont white;
	TextureAtlas atlas;
	Skin skin;
	SpriteBatch batch;
	TextButton howToPlayButton, practiceButton, backButton;
	
	
	public Options(StickFlick game){
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		
		batch.begin();
		stage.draw();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		stage = new Stage(width, height, true);
		stage.clear();
		
		Gdx.input.setInputProcessor(stage);
		
		// Styles for text buttons, slider and labels
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("LightButton");
		style.down = skin.getDrawable("DarkButton");
		style.font = white;
		SliderStyle slidStyle = new SliderStyle(skin.getDrawable("Slider"), skin.getDrawable("SliderKnob"));
		LabelStyle labelStyle = new LabelStyle(white, Color.WHITE);
		
		// Sliders
		// Music Slider
		Label musicVolumeLabel = new Label("Music Volume", labelStyle);
		musicVolumeLabel.setX(Gdx.graphics.getWidth() / 4);
		musicVolumeLabel.setY(Gdx.graphics.getHeight() /3);
		
		Slider musicVolume = new Slider(1, 100, 1, false, slidStyle);
		musicVolume.setWidth(Gdx.graphics.getWidth() / 6);
		musicVolume.setX(Gdx.graphics.getWidth() / 2 - musicVolume.getWidth() / 2);
		musicVolume.setY(Gdx.graphics.getHeight() /3);
		
		stage.addActor(musicVolumeLabel);
		stage.addActor(musicVolume);
		
		//SFX Slider
		Label SFXVolumeLabel = new Label("SFX Volume", labelStyle);
		SFXVolumeLabel.setX(Gdx.graphics.getWidth() / 4);
		SFXVolumeLabel.setY(Gdx.graphics.getHeight() /4);
		
		Slider SFXVolume = new Slider(1, 100, 1, false, slidStyle);
		SFXVolume.setWidth(Gdx.graphics.getWidth() / 6);
		SFXVolume.setX(Gdx.graphics.getWidth() / 2 - SFXVolume.getWidth() / 2);
		SFXVolume.setY(Gdx.graphics.getHeight() /4);
		
		stage.addActor(SFXVolumeLabel);
		stage.addActor(SFXVolume);
		
		
		// BUTTON INITIATION
		//How To Play Button
		howToPlayButton = new TextButton("How To Play", style);
		howToPlayButton.setWidth(Gdx.graphics.getWidth() / 6);
		howToPlayButton.setHeight(Gdx.graphics.getWidth() / 24);
		howToPlayButton.setX(Gdx.graphics.getWidth() / 2 - howToPlayButton.getWidth() / 2 - Gdx.graphics.getWidth() / 4);
		howToPlayButton.setY(Gdx.graphics.getHeight() /2 - howToPlayButton.getHeight() / 2);
		stage.addActor(howToPlayButton);
		//Practice Button
		practiceButton = new TextButton("Practice", style);
		practiceButton.setWidth(Gdx.graphics.getWidth() / 6);
		practiceButton.setHeight(Gdx.graphics.getWidth() / 24);
		practiceButton.setX(Gdx.graphics.getWidth() / 2 - practiceButton.getWidth() / 2);
		practiceButton.setY(Gdx.graphics.getHeight() /2 - practiceButton.getHeight() / 2);
		stage.addActor(practiceButton);
		//Back Button
		backButton = new TextButton("Main Menu", style);
		backButton.setWidth(Gdx.graphics.getWidth() / 6);
		backButton.setHeight(Gdx.graphics.getWidth() / 24);
		backButton.setX(Gdx.graphics.getWidth() / 2 - backButton.getWidth() / 2 + Gdx.graphics.getWidth() / 4);
		backButton.setY(Gdx.graphics.getHeight() /2 - backButton.getHeight() / 2);
		stage.addActor(backButton);
		
		stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1)));
		
		backButton.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("down");
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("up");
				stage.addAction(Actions.sequence(Actions.fadeOut(.3f), Actions.run(new Runnable() {
					@Override
					public void run() {
						((StickFlick) Gdx.app.getApplicationListener()).setScreen(new MainMenu(game));
					}
				})));
			}
		});
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		atlas = new TextureAtlas("data/Textures.atlas");
		skin = new Skin();
		skin.addRegions(atlas);
		white = new BitmapFont(Gdx.files.internal("data/whiteFont.fnt"), false);
	}

	@Override
	public void hide() {
		dispose();
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		skin.dispose();
		atlas.dispose();
		white.dispose();
		stage.dispose();
	}
	
}
