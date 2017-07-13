package com.missionbit.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Created by missionbit on 6/26/17.
 */

public class Plank extends InteractiveTileObject{
    public Plank(World world, TiledMap tiledMap, Rectangle bounds){
        super(world, tiledMap, bounds);
    }
//
//    @Override
//    public void onLadderHit() {
//        Gdx.app.log("Plank Ladder", "Ladder Hit");
//    }
//
//    @Override
//    public void onDoorHit() {
//        Gdx.app.log("Plank Door", "Ladder Hit");
//    }

    @Override
    public void onCollisionDetected() {
        Gdx.app.log("Plank", "Plank Hit");
    }
}
