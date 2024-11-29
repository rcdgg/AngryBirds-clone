package birds.angry.Screens;

import birds.angry.AngryBirds;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LevelSelect extends BaseScreen{
    private final Image background;
    public LevelSelect(Game game){
        super(game);
        System.out.println(AngryBirds.save_slot);
        shapeRenderer = new ShapeRenderer();
        background = new Image(new Texture("screens/levelselect/level select.png"));

        Button one = new Button(invisibleButtonStyle);
        one.setPosition(300,stage.getHeight() / 2 - 50);
        one.setSize(100,120);
        TextButton one_score = new TextButton(AngryBirds.score.getFirst().toString(), skin);
        one_score.setPosition(one.getX() + 10, one.getY() - 1.5f * grid_size);
        one.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                game.setScreen(new Level1(game, "assets/save_slot"+ AngryBirds.save_slot +"/level1_save.ser"));
            }
        });

        Button two = new Button(invisibleButtonStyle);
        two.setPosition(530,stage.getHeight() / 2 - 50);
        two.setSize(100,120);
        TextButton two_score = new TextButton(AngryBirds.score.get(1).toString(), skin);
        two_score.setPosition(two.getX() + 10, two.getY() - 1.5f * grid_size);
        two.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                game.setScreen(new Level2(game, "assets/save_slot"+ AngryBirds.save_slot +"/level2_save.ser"));
            }
        });


        Button three = new Button(invisibleButtonStyle);
        three.setPosition(750,stage.getHeight() / 2 - 50);
        three.setSize(100,120);
        TextButton three_score = new TextButton(AngryBirds.score.get(2).toString(), skin);
        three_score.setPosition(three.getX() + 10, three.getY() - 1.5f * grid_size);
        three.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                game.setScreen(new Level3(game, "assets/save_slot"+ AngryBirds.save_slot +"/level3_save.ser"));
            }
        });


        Button back = new Button(invisibleButtonStyle);
        back.setSize(100,120);
        back.setPosition(50, 15);

        stage.addActor(one);
        stage.addActor(one_score);
        stage.addActor(two);
        stage.addActor(two_score);
        stage.addActor(three);
        stage.addActor(three_score);
        stage.addActor(back);

//        Skin skin = new Skin(Gdx.files.internal("screens/mainmenu/skin/comic-ui.json"));
//        TextButton check = new TextButton("Level Select", skin);
//        stage.addActor(check);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                game.setScreen(new StartingMenu(game));
            }
        });

//        Path directory = Paths.get("assets/save_slot" + AngryBirds.save_slot);
//
//        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
//            int i = 0;
//            AngryBirds.score.clear();
//            for (Path path : stream) {
//                if (!Files.isDirectory(path)) {
//                    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path.toFile().getCanonicalPath()))){
//                        GameState gameState =(GameState) in.readObject();
//                        AngryBirds.score.add(i++, gameState.score);
//                    } catch (Exception e){
//                        System.out.println(e.getMessage());
//                    }
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        System.out.println("score total:" + AngryBirds.score);
    }
    public void render(float delta) {
//        System.out.println("2nd screen");
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(stage.getCamera().combined);
        batch.begin();
        background.draw(batch,1);
        batch.end();
        super.render(delta);
    }
}
