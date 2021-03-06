package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.Farmer;
import com.missionbit.game.sprites.Sheep;

/**
 * Created by MissionBit on 6/22/17.
 */

public class MenuState extends State {


    private Texture background;
    private Texture playBtn;
    private Sheep sheep;
    private Farmer farmer;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("MenuScreen.png");
        playBtn = new Texture("PlayBtn.png");
        sheep = new Sheep(100,60);
        farmer = new Farmer(20,60);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new Level4(gsm));

        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        sheep.update(dt);
        farmer.update(dt);
        if (sheep.getPosition().x > 750){
            sheep = new Sheep(20,60);
        }
        if (farmer.getPosition().x > 750){
            farmer = new Farmer(20,60);
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, GameTutorial.WIDTH, GameTutorial.HEIGHT);
        sb.draw(playBtn,(GameTutorial.WIDTH - playBtn.getWidth())/2, (GameTutorial.HEIGHT - playBtn.getHeight())/2);
        sb.draw(sheep.getSheep(),sheep.getPosition().x,sheep.getPosition().y,70,60);
        sb.draw(farmer.getFarmer(),farmer.getPosition().x,farmer.getPosition().y,90,100);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        sheep.dispose();
        farmer.dispose();
    }
}
