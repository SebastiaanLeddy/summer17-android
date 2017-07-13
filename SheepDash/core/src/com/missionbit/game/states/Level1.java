package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.Farmer;
import com.missionbit.game.sprites.animals.Sheep;
import com.missionbit.game.sprites.obstacles.Barrel;
import com.missionbit.game.sprites.obstacles.Cherry;
import com.missionbit.game.sprites.obstacles.Poop;

/**
 * Created by missionbit on 6/26/17.
 */

public class Level1 extends State{
    private Barrel barrel;
    private Cherry cherry;
    private Poop poop;
    private Sheep sheep;
    private Farmer farmer;
    private Texture ground;
    private Texture trees;
    private Texture trees2;
    private Texture trees3;
    private Texture barn;
    private Texture shed;
    private Texture background;
    private Vector2 groundPos1;
    private Vector2 groundPos2;
    private Vector2 treePos1;
    private Vector2 treePos2;
    private Vector2 treePos3;
    private Vector2 barnPos;
    private Vector2 shedPos;
    private Vector2 bgPos1;
    private Vector2 bgPos2;
    private static final int bg_width = 1000;
    private static final int GROUND_Y_OFFSET = -80;
    private static final int ground_width = 800;
    private static final int CHERRY_WIDTH = 30;
    private static final int CHERRY_SPACING = 800;
    private static final int POOP_SPACING = 250;
    private static final int POOP_WIDTH = 30;
    private static final int BARREL_SPACING = 400;
    private static final int BARREL_WIDTH = 100;

    public Level1(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, GameTutorial.WIDTH / 2, GameTutorial.HEIGHT / 2);
        barrel = new Barrel(1000,60);
        poop = new Poop(260,60);
        cherry = new Cherry(500,50);
        sheep = new Sheep(150,60);
        farmer = new Farmer(-50,60);
        ground = new Texture("FarmBG6.png");
        trees = new Texture("FarmBG3.png");
        trees2 = new Texture("FarmBG4.png");
        trees3 = new Texture("FarmBG5.png");
        barn = new Texture("FarmBG1.png");
        shed = new Texture("FarmBG2.png");
        background = new Texture("FarmBG7.png");
        groundPos1 = new Vector2(cam.position.x-cam.viewportWidth/2,GROUND_Y_OFFSET);
        groundPos2 = new Vector2(ground.getWidth() + groundPos1.x,GROUND_Y_OFFSET);
        treePos1 = new Vector2(cam.position.x-cam.viewportWidth/2,0);
        treePos2 = new Vector2(trees.getWidth()+treePos1.x,0);
        treePos3 = new Vector2(trees.getWidth()+treePos2.x,0);
        barnPos = new Vector2(50,0);
        shedPos = new Vector2(350,0);
        bgPos1 = new Vector2(cam.position.x - cam.viewportWidth/2,0);
        bgPos2 = new Vector2(background.getWidth()+bgPos1.x,0);

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            sheep.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        sheep.update(dt);
        cherry.update(dt);
        cam.position.x = sheep.getPosition().x + 80;
        farmer.update(dt);
        updateBg();
        updateGround();
        updateTrees();
        updateBarrels();
        updateCherrys();
        updatePoops();
        collisionCheck2();
        timerCheck(dt);
        changeLevels();
        cam.update();

    }

    public void collisionCheck2(){
        if (poop.collides(sheep.getBounds1())){
            sheep.reduceSpd();
            sheep.startTimer();
            System.out.println(sheep.getBounds1());
            System.out.println(poop.getBoundsPoop());
        }
        if (cherry.collides(sheep.getBounds1())){
            sheep.increaseSpd();
            sheep.startTimer();
        }
    }

    public void timerCheck(float timePassed){
        sheep.updateTimer(timePassed);
        if (sheep.isTimerDone()){
            sheep.resetSpd();
        }
    }

    public void updateBarrels(){
        if (barrel.getPosBarrel().x + BARREL_WIDTH <= cam.position.x-cam.viewportWidth/2){
            barrel.getPosBarrel().add(2*BARREL_SPACING,0);
            barrel.getBarrelBounds().setPosition(barrel.getPosBarrel().x,barrel.getPosBarrel().y);
        }
    }

    public void updatePoops(){
        if (poop.getPosPoop().x + POOP_WIDTH <= cam.position.x-cam.viewportWidth/2){
            poop.getPosPoop().add(2*POOP_SPACING,0);
            poop.getBoundsPoop().setPosition(poop.getPosPoop().x,poop.getPosPoop().y);
            poop.setCollided(false);
        }
    }

    public void updateCherrys(){
        if (cherry.getPosCherry().x + CHERRY_WIDTH <= cam.position.x-cam.viewportWidth/2){
            cherry.getPosCherry().add(2*CHERRY_SPACING,0);
            cherry.getCherryBounds().setPosition(cherry.getPosCherry().x,cherry.getPosCherry().y);
            cherry.setCollided(false);
        }
    }


    public void updateBg() {
        if (bgPos1.x + background.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            bgPos1.add(2 * background.getWidth(), 0);
        }
        if (bgPos2.x + background.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            bgPos2.add(2 * background.getWidth(), 0);
        }
    }

    public void updateGround(){
        if(groundPos1.x+ground.getWidth() <= cam.position.x-cam.viewportWidth/2){
            groundPos1.add(2*ground.getWidth(),0);
        }
        if(groundPos2.x+ground.getWidth() <= cam.position.x-cam.viewportWidth/2){
            groundPos2.add(2*ground.getWidth(),0);
        }

    }

    public void changeLevels(){
        if (sheep.getPosition().x > 3000){
            gsm.set(new Level2(gsm));
        }
    }

    public void updateTrees(){
        if(treePos1.x+trees.getWidth() <= cam.position.x-cam.viewportWidth/2){
            treePos1.add(3*trees.getWidth(),0);
        }
        if(treePos2.x+trees2.getWidth() <= cam.position.x-cam.viewportWidth/2){
            treePos2.add(3*trees2.getWidth(),0);
        }
        if(treePos3.x+trees3.getWidth() <= cam.position.x-cam.viewportWidth/2){
            treePos3.add(3*trees3.getWidth(),0);
        }


    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.setProjectionMatrix(cam.combined);
        sb.draw(background,bgPos1.x,0,bg_width,800);
        sb.draw(background,bgPos2.x,0,bg_width,800);
        sb.draw(ground,groundPos1.x,0,ground_width,1200);
        sb.draw(ground,groundPos2.x,0,ground_width,1200);
        sb.draw(trees,treePos1.x,50,270,250);
        sb.draw(trees2,treePos2.x,50,270,250);
        sb.draw(trees3,treePos3.x,50,270,250);
        sb.draw(barn,barnPos.x,70);
        sb.draw(shed,shedPos.x,60);
        sb.draw(cherry.getCherry(),cherry.getPosCherry().x,cherry.getPosCherry().y,90,50);
        sb.draw(poop.getPoop(),poop.getPosPoop().x,poop.getPosPoop().y,30,30);
        sb.draw(barrel.getBarrel(),barrel.getPosBarrel().x,barrel.getPosBarrel().y,60,50);
        if (farmer.collides(sheep.getBounds1())) {
            sb.draw(sheep.getSheepDead(), sheep.getPosition().x, sheep.getPosition().y,70,45);
        }
        else {
            sb.draw(sheep.getSheep(), sheep.getPosition().x, sheep.getPosition().y, 70,45);
        }
        sb.draw(farmer.getFarmer(),farmer.getPosition().x,farmer.getPosition().y,120,110);
        sb.end();

    }

    @Override
    public void dispose() {
        sheep.dispose();
        barn.dispose();
        shed.dispose();
        farmer.dispose();
        trees.dispose();
        ground.dispose();
        background.dispose();
        cherry.dispose();
        poop.dispose();
        barrel.dispose();

    }
}
