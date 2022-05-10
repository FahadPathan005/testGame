package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import game.AirWaterScreen;
import game.Score;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EndScreen implements Screen {

    MyGdxGame game;
    Score totalScore ;

    Texture endscreen1 ;
    Texture endscreen2 ;

    public boolean higestScore ;
    public int currentScore ;

    public EndScreen(MyGdxGame game) {

        this.game = game;
        totalScore = new Score(game.batch);

        endscreen1 = new Texture("EndScreen\\2.png");
        endscreen2 = new Texture("EndScreen\\4.png");

       // higestScore = true;
    }


    @Override
    public void show() {



    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(1, 1, 1, 1);

        // * set position
        //totalScore.score = 0 ;
        totalScore.minusHeight = 470 ;

        button();
        getScore();
        compareScore();

        game.batch.begin();

        if(!higestScore)game.batch.draw(endscreen1,0,0);
        else game.batch.draw(endscreen2,0,0);

        totalScore.render(game.batch);

        game.batch.end();

    }


    public void getScore() {
        File file1 = new File("score.txt");

        try {

                Scanner readInto = new Scanner(file1);
                while (readInto.hasNextLine()) {

                    String pastData = readInto.nextLine();

                    totalScore.score = Integer.parseInt(pastData) ;
                    //System.out.println(totalScore);
                    currentScore = Integer.parseInt(pastData) ;
                    //System.out.println(currentScore);

                }
                readInto.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void compareScore(){
        File file = new File("highestScore.txt");

        try {
            // * check if file exists or not
            if (file.exists())
            {
                Scanner readInto = new Scanner(file);
                int pastScore = 0;
                while (readInto.hasNextLine()) {

                    String pastData = readInto.nextLine();

                    pastScore = Integer.parseInt(pastData) ;
                    //System.out.println(pastScore);

                }
                if(currentScore >= pastScore) higestScore = true ;
                else higestScore = false;

                readInto.close();


            } else {

                higestScore = true ;
                // file not exists

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void button()
    {
        if (Gdx.input.getX() >= 621 && Gdx.input.getX() <= 1105 &&
                MyGdxGame.SCREEN_HEIGHT - Gdx.input.getY() >= 300
                && MyGdxGame.SCREEN_HEIGHT - Gdx.input.getY() <= 385) {
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new AirWaterScreen(game));
            }
        }
        if (Gdx.input.getX() >= 621 && Gdx.input.getX() <= 1105 &&
                MyGdxGame.SCREEN_HEIGHT - Gdx.input.getY() >= 180
                && MyGdxGame.SCREEN_HEIGHT - Gdx.input.getY() <= 265) {
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new GameMenuScreen(game));
            }
        }
        if (Gdx.input.getX() >= 621 && Gdx.input.getX() <= 1105 &&
                MyGdxGame.SCREEN_HEIGHT - Gdx.input.getY() >= 65
                && MyGdxGame.SCREEN_HEIGHT - Gdx.input.getY() <= 150) {
            if (Gdx.input.isTouched()) {
                System.exit(0);
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
